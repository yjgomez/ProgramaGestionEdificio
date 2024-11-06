package administracion.tpo.controller;

import administracion.tpo.dao.PersonaDAO;
import administracion.tpo.dao.UnidadDAO;
import administracion.tpo.exceptions.UnidadException;
import administracion.tpo.modelo.Persona;
import administracion.tpo.modelo.Unidad;
import administracion.tpo.repository.IRepositoryEdificio;
import administracion.tpo.repository.IRepositoryPersona;
import administracion.tpo.repository.IRepositoryUnidad;
import administracion.tpo.views.PersonaView;
import administracion.tpo.views.UnidadView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/unidades")
public class UnidadController {


    @Autowired
    IRepositoryUnidad repositoriounidad;

    @Autowired
    IRepositoryEdificio repositoryEdificio;

    @Autowired
    IRepositoryPersona repopersona;

    @GetMapping
    public List<UnidadView> getAll() {
        List<Unidad> unidades= UnidadDAO.getInstance().getAll(repositoriounidad);
        List<UnidadView> unidadesview=new ArrayList<UnidadView>();
        for(Unidad e:unidades) {
            unidadesview.add(e.toView());
        }
        return unidadesview;
    }

    @GetMapping("/disponibles")
    public List<UnidadView> getAllDisponibles() {
        List<Unidad> unidades=UnidadDAO.getInstance().getAll(repositoriounidad);
        List<UnidadView> unidadesview =new ArrayList<>();
        for(Unidad e:unidades) {
            if(!e.estaHabitado()) {
                unidadesview.add(e.toView());
            }

        }
        return unidadesview;
    }

    @GetMapping("/{id}")
    public UnidadView getById( @PathVariable("id") int id) {
        //la rta es http://localhost:8080/api/edificios/2
        Optional<Unidad> e =UnidadDAO.getInstance().getById(id, repositoriounidad);
        if(e.isPresent()) {
            Unidad ed=e.get();
            return ed.toView();
        }else {
            System.out.println("no existe esa unidad");
            return null;
        }
    }
    @PostMapping("/{id}/crear") // se le envia por parametro a la url el id del edificio
    public ResponseEntity<UnidadView> crearUnidad(@PathVariable Integer id,@RequestBody Unidad unidad) {
        UnidadDAO.getInstance().save(unidad, repositoriounidad);
        return ResponseEntity.ok(unidad.toView());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUnidad(@RequestBody UnidadView unidadView) throws UnidadException {
        UnidadDAO.getInstance().delete(unidadView, repositoriounidad);
        return ResponseEntity.ok("Unidad eliminada");
    }
    @PostMapping("/alquilar")
    public ResponseEntity<UnidadView> alquilarUnidad (@RequestBody Unidad unidad, @RequestBody PersonaView personaView) throws UnidadException {
        Persona persona = PersonaDAO.getInstance().getById(personaView.getDocumento(), repopersona).orElseThrow();
        Unidad unidadPorAlquilarse = UnidadDAO.getInstance().alquilar(unidad, repositoriounidad, persona);
        return ResponseEntity.ok(unidadPorAlquilarse.toView());
    }
    @PostMapping("/transferir")
    public ResponseEntity<UnidadView> crearCompra (@RequestBody UnidadView unidadView, @RequestBody PersonaView personaView) throws UnidadException {
        Persona persona = PersonaDAO.getInstance().getById(personaView.getDocumento(), repopersona).orElseThrow();
        Unidad unidad = UnidadDAO.getInstance().getById(unidadView.getId(), repositoriounidad).orElseThrow();
        UnidadDAO.getInstance().transferir(unidad, persona, repositoriounidad);
        return ResponseEntity.ok(unidad.toView());
    }
    @PostMapping("/deleteInquilino")
    public ResponseEntity<String> borrarInquilino (@RequestBody UnidadView unidadView, @RequestBody PersonaView personaView) throws UnidadException {
        Persona persona = PersonaDAO.getInstance().getById(personaView.getDocumento(), repopersona).orElseThrow();
        Unidad unidad = UnidadDAO.getInstance().getById(unidadView.getId(), repositoriounidad).orElseThrow();
        UnidadDAO.getInstance().borrarInquilino(unidad, persona, repositoriounidad);
        return ResponseEntity.ok("Inquilino eliminado");
    }
    @PostMapping("/liberarUnidad")
    public ResponseEntity<String> liberarUnidad (@RequestBody UnidadView unidadView, @RequestBody PersonaView personaView) throws UnidadException {
        Unidad unidad = UnidadDAO.getInstance().getById(unidadView.getId(), repositoriounidad).orElseThrow();
        Persona persona = PersonaDAO.getInstance().getById(personaView.getDocumento(), repopersona).orElseThrow();
        UnidadDAO.getInstance().liberarUnidad(unidad,persona, repositoriounidad);
        return ResponseEntity.ok("Unidad liberada");
    }
}

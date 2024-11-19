package administracion.tpo.controller;

import administracion.tpo.dao.EdificioDAO;
import administracion.tpo.dao.PersonaDAO;
import administracion.tpo.dao.ReclamoDAO;
import administracion.tpo.dao.UnidadDAO;
import administracion.tpo.exceptions.UnidadException;
import administracion.tpo.modelo.Edificio;
import administracion.tpo.modelo.Persona;
import administracion.tpo.modelo.Unidad;
import administracion.tpo.repository.IRepositoryEdificio;
import administracion.tpo.repository.IRepositoryPersona;
import administracion.tpo.repository.IRepositoryReclamo;
import administracion.tpo.repository.IRepositoryUnidad;
import administracion.tpo.views.PersonaView;
import administracion.tpo.views.UnidadView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")  // Permite solicitudes de cualquier origen
@RestController
@RequestMapping("api/unidades")
public class UnidadController {


    @Autowired
    UnidadDAO repositoriounidad;

    @Autowired
    EdificioDAO repositoryEdificio;

    @Autowired
    PersonaDAO repopersona;
    @Autowired
    ReclamoDAO repositoryReclamo;

    @GetMapping("/")
    public ResponseEntity<List<Unidad>> getAll(){
        List<Unidad> unidades = repositoriounidad.getAll();
        return new ResponseEntity<>(unidades, HttpStatus.OK);
    }

    @GetMapping("/disponibles")
    public List<Unidad> getAllDisponibles() {
        List<Unidad> unidades=repositoriounidad.getAll();
        List<Unidad> disponibles =new ArrayList<>();

        for(Unidad e:unidades) {
            if(!e.estaHabitado()) {
                disponibles.add(e);
            }

        }
        return disponibles;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Unidad> getById( @PathVariable("id") int id) {
        //la rta es http://localhost:8080/api/edificios/2
        Optional<Unidad> e =repositoriounidad.getById(id);
        if(e.isPresent()) {
            return new ResponseEntity<>(e.get(), HttpStatus.OK);
        }else {
            System.out.println("no existe esa unidad");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    /*
    @PostMapping("/crear") // se le envia por parametro a la url el id del edificio
    public ResponseEntity<Unidad> crearUnidad(@RequestBody Unidad unidad) {
        repositoriounidad.save(unidad);
        return new ResponseEntity<>(unidad, HttpStatus.CREATED);
    }

     */

    //SE CREA UNA UNIDAD Y SE LE ASIGNA A UN EDIFICIO DIRECTAMENTE
    @PostMapping("/crear/{ideficio}") // se le envia por parametro a la url el id del edificio
    public ResponseEntity<Unidad> crearUnidad(@RequestBody Unidad unidad, @PathVariable int ideficio) {
        Optional<Edificio>e=repositoryEdificio.getById(ideficio);
        Edificio e2=null;

        if(e.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }
        e2=e.get();

        unidad.setEdificio(e2);

        repositoryEdificio.save(e2);
        repositoriounidad.save(unidad);

        return new ResponseEntity<>(unidad, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUnidad(@RequestBody Unidad unidad) throws UnidadException {
        repositoriounidad.delete(unidad);
        repositoryReclamo.deleteReclamosByUnidadId(unidad.getId());
        return ResponseEntity.ok("Unidad eliminada por request");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUnidadById (@PathVariable Integer id) {
        repositoriounidad.deleteById(id);
        return ResponseEntity.ok("Unidad eliminada por request");
    }

    @PutMapping("/{id}/alquilar")
    public ResponseEntity<Unidad> alquilarUnidad (@PathVariable int id,@RequestBody Persona persona) throws UnidadException {
        Optional<Unidad> uOpt = repositoriounidad.getById(id);
        if(uOpt.isPresent()) {
            Unidad unidad = uOpt.get();
            unidad.alquilar(persona);
            System.out.println(unidad.estaHabitado());
            repositoriounidad.save(unidad);
        return new ResponseEntity<>(unidad, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}/transferir")
    public ResponseEntity<Unidad> transferir (@PathVariable int id, @RequestBody Persona persona) throws UnidadException {
        Optional<Unidad> uOpt = repositoriounidad.getById(id);
        
        Optional<Persona> p=repopersona.getById(persona.getDocumento());
        Persona p2=null;
        if(p.isPresent()){
            p2=p.get();
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(uOpt.isPresent()) {
            Unidad unidad = uOpt.get();
            repositoriounidad.transferir(unidad, p2);
            return new ResponseEntity<>(unidad, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{id}/deleteInquilino")
    public ResponseEntity<String> borrarInquilino (@PathVariable int id, @RequestBody Persona persona) throws UnidadException {
        Optional<Unidad> uOpt = repositoriounidad.getById(id);
        if(uOpt.isPresent()) {
            Unidad unidad = uOpt.get();
            repositoriounidad.borrarInquilino(unidad, persona);
             return new ResponseEntity<>("Inquilino eliminado", HttpStatus.OK);
        }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{id}/liberarUnidad")
    public ResponseEntity<String> liberarUnidad (@PathVariable int id, @RequestParam String dni) throws UnidadException {
        Optional<Unidad> uOpt = repositoriounidad.getById(id);

        Optional<Persona>persona=repopersona.getById(dni);
        Persona persona1=null;
        if(persona.isPresent()) {
            persona1=persona.get();
        }

        if(uOpt.isPresent()) {
            Unidad unidad = uOpt.get();
            repositoriounidad.liberarUnidad(unidad,persona1);
            return new ResponseEntity<>("unidad liberada",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/inquilinos/{id}")
    public ResponseEntity<List<Persona>> getInquilinosByUnidadId (@PathVariable Integer id) {
        Optional<Unidad> uOpt = repositoriounidad.getById(id);
        if(uOpt.isPresent()) {
            List<Persona> inquilinos = uOpt.get().getInquilinos();
            return new ResponseEntity<>(inquilinos, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /*
    public ResponseEntity<List<Persona>> getInquilinos () {
        List<Unidad> uOpt = repositoriounidad.getAll();
        List<Persona>personas=new ArrayList<>();
        for(Unidad u: uOpt){
            for(Persona p: u.getInquilinos()){
                personas.add(p);
            }
        }
        return new ResponseEntity<>(personas, HttpStatus.OK);




    }
     */

    @PutMapping("/{id}/agregarduenio")
    public ResponseEntity<String> agregarduenio (@PathVariable int id, @RequestBody Persona persona) throws UnidadException {
        Optional<Unidad> uOpt = repositoriounidad.getById(id);
        if(uOpt.isPresent()) {
            Unidad unidad = uOpt.get();
            unidad.agregarDuenio(persona);
            repositoriounidad.save(unidad);
            return new ResponseEntity<>("Dueño añadido", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}

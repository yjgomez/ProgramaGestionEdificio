package administracion.tpo.controller;

import administracion.tpo.dao.ReclamoDAO;
import administracion.tpo.dao.UnidadDAO;
import administracion.tpo.modelo.Reclamo;
import administracion.tpo.modelo.Unidad;
import administracion.tpo.repository.IRepositoryReclamo;
import administracion.tpo.repository.IRepositoryUnidad;
import administracion.tpo.views.ReclamoView;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/reclamos")
public class ReclamoController {
    @Autowired
    IRepositoryReclamo repositoryReclamo;
    @Autowired
    IRepositoryUnidad repositoryUnidad;
    @PostMapping
    public ResponseEntity<ReclamoView> generarReclamo (@RequestBody Reclamo reclamo){
        Integer idUnidad = reclamo.getUnidad().getId();
        Unidad unidad = UnidadDAO.getInstance().getById(idUnidad, repositoryUnidad).get();
        // checkeamos que si no esta alquilada, el del reclamo sea unicamente el dueño
        if(!unidad.estaHabitado() && reclamo.getUsuario() != unidad.getDuenios()) {
            throw new RuntimeException("La unidad no esta habitada, solamente el dueño puede hacer el reclamo");
        }
        else if (unidad.estaHabitado() && (reclamo.getUsuario() != unidad.getInquilinos() && reclamo.getUsuario() != unidad.getDuenios())) {
            throw new RuntimeException("La unidad esta habitada, solamente el inquilino o el dueño puede hacer el reclamo");
        } else {
            // guardamos el reclamo
            ReclamoDAO.getInstance().save(reclamo, repositoryReclamo);
        }
        return ResponseEntity.ok(reclamo.toView());
    }
}

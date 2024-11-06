package administracion.tpo.controller;

import administracion.tpo.dao.*;
import administracion.tpo.modelo.*;
import administracion.tpo.repository.*;
import administracion.tpo.views.ReclamoView;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/reclamos")
public class ReclamoController {
    @Autowired
    IRepositoryReclamo repositoryReclamo;
    @Autowired
    IRepositoryUnidad repositoryUnidad;
    @Autowired
    IRepositoryImagen repositoryImagen;
    @Autowired
    IRepositoryPersona repositoryPersona;
    @Autowired
    IRepositoryEdificio repositoryEdificio;
    private final String uploadDir = "uploads/";
@PostMapping("/crear")
public ResponseEntity<Integer> generarReclamo(@RequestBody Reclamo reclamo) {
    Unidad unidad = UnidadDAO.getInstance().getById(reclamo.getUnidad().getId(), repositoryUnidad).orElseThrow();
    Persona persona = PersonaDAO.getInstance().getById(reclamo.getUsuario().getDocumento(), repositoryPersona).orElseThrow();
    if (!unidad.estaHabitado() && !unidad.esDuenio(persona)) {
        throw new RuntimeException("La unidad no esta habitada, solamente el dueño puede hacer el reclamo");
    } else if (unidad.estaHabitado() && !unidad.esInquilino(persona)) {
        throw new RuntimeException("La unidad esta habitada, solamente el inquilino puede hacer el reclamo");
    }
    else {
        ReclamoDAO.getInstance().save(reclamo, repositoryReclamo);
        return ResponseEntity.ok(reclamo.getNumero());
        // retorna el id del reclamo para su posterior consulta
    }
}

@PostMapping("/crearParteComun")
    public ResponseEntity<Integer> generarReclamoParteComun(@RequestBody Reclamo reclamo) {
        Edificio edificio = EdificioDAO.getInstance().getById(reclamo.getEdificio().getCodigo(), repositoryEdificio).orElseThrow();
        Persona persona = PersonaDAO.getInstance().getById(reclamo.getUsuario().getDocumento(), repositoryPersona).orElseThrow();
        Set<Persona> habitantes = edificio.habitantes();
        // Verificar si la persona tiene alguna relación con el edificio (dueño o inquilino de alguna unidad)
        if (!habitantes.contains(persona)) {
            throw new RuntimeException("Solo las personas relacionadas con el edificio pueden hacer un reclamo de parte común.");
        } else {
            ReclamoDAO.getInstance().save(reclamo, repositoryReclamo);
            return ResponseEntity.ok(reclamo.getNumero()); // Retorna el ID del reclamo
        }
    }

    // recibimos el id como parametro en la url, para edificio y para unidad.
    @GetMapping("/edificio/{id}")
    public ResponseEntity<List<ReclamoView>> getReclamosPorEdificio (@PathVariable("id") int idEdificio){
        List<Reclamo> reclamos = ReclamoDAO.getInstance().getByEdificio(idEdificio,repositoryReclamo);
        return ResponseEntity.ok(reclamos.stream().map(Reclamo::toView).toList());
    }

    @GetMapping("/unidad/{id}")
    public ResponseEntity<List<ReclamoView>> getReclamosPorUnidad (@PathVariable("id") int idUnidad){
        List<Reclamo> reclamos = ReclamoDAO.getInstance().getByUnidad(idUnidad, repositoryReclamo);
        return ResponseEntity.ok(reclamos.stream().map(Reclamo::toView).toList());
    }

    /// reveer esto, no se si esta bien
    @PostMapping("/{id}/imagenes")
    public ResponseEntity<String> cargarImagen(@PathVariable int id, @RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("File is empty");
            }

            String fileName = file.getOriginalFilename();
            Path path = Paths.get(uploadDir + fileName);
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());

            Imagen imagen = new Imagen(fileName, file.getContentType());
            Reclamo reclamo = ReclamoDAO.getInstance().getById(id, repositoryReclamo).orElseThrow();
            reclamo.agregarImagen(imagen.getDireccion(), imagen.getTipo());
            ImagenDAO.getInstance().save(imagen, repositoryImagen);

            return ResponseEntity.ok("Image uploaded successfully: " + fileName);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error al subir la imagen: " + e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReclamoView> getReclamo(@PathVariable int id) {
        Optional<Reclamo> reclamo = ReclamoDAO.getInstance().getById(id, repositoryReclamo);
        if (reclamo.isPresent()) {
            return ResponseEntity.ok(reclamo.get().toView());
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<ReclamoView>> getAllReclamos () {
        List<Reclamo> reclamos = ReclamoDAO.getInstance().getAll(repositoryReclamo);
        return ResponseEntity.ok(reclamos.stream().map(Reclamo::toView).toList());
    }

}

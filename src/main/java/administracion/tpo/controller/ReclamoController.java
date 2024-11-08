package administracion.tpo.controller;

import administracion.tpo.dao.*;
import administracion.tpo.modelo.*;
import administracion.tpo.repository.*;
import administracion.tpo.views.ReclamoView;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Id;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/reclamos")
public class ReclamoController {
    @Autowired
    ReclamoDAO repositoryReclamo;
    @Autowired
    UnidadDAO repositoryUnidad;
    @Autowired
    ImagenDAO repositoryImagen;
    @Autowired
    PersonaDAO repositoryPersona;
    @Autowired
    EdificioDAO repositoryEdificio;

    // ruta base para el manejo de imagenes
    @Value("")
    private String uploadDir;


    private Reclamo generarReclamoAbstract (Reclamo reclamo) {
        Optional<Unidad> uni = repositoryUnidad.getById(reclamo.getUnidad().getId());
        Optional<Persona> per = repositoryPersona.getById(reclamo.getUsuario().getDocumento());
        if (!uni.isPresent()) {
            throw new RuntimeException("La unidad no existe");
        }
        if (!per.isPresent()) {
            throw new RuntimeException("La persona no existe");
        }
        Unidad unidad = uni.get();
        Persona persona = per.get();
        if (!unidad.estaHabitado() && !unidad.esDuenio(persona)) {
            throw new RuntimeException("La unidad no esta habitada, solamente el dueño puede hacer el reclamo");
        } else if (unidad.estaHabitado() && !unidad.esInquilino(persona)) {
            throw new RuntimeException("La unidad esta habitada, solamente el inquilino puede hacer el reclamo");
        }
        else {
            reclamo.setUnidad(unidad); // Asegurarse de que la unidad esté gestionada por la sesión actual
            repositoryReclamo.save(reclamo);
            return reclamo;
    }
    }
    @PostMapping("/crearSinImagen")
    public ResponseEntity<Integer> generarReclamoSinImagen(@RequestBody Reclamo reclamo) {
            Reclamo reclamoGenerado = generarReclamoAbstract(reclamo);
            return ResponseEntity.ok(reclamoGenerado.getNumero());
        // retorna el id del reclamo para su posterior consulta
    }
    @PostMapping("/crearConImagen")
    public ResponseEntity<Integer> generarReclamoConImagen (@RequestPart("reclamo") Reclamo reclamo,
                                                            @RequestPart("file") MultipartFile file) {
        Reclamo reclamoGenerado = generarReclamoAbstract(reclamo);
        cargarImagen(file, reclamoGenerado);
        return ResponseEntity.ok(reclamoGenerado.getNumero());
    }


    @PostMapping("/crearParteComun")
    public ResponseEntity<Integer> generarReclamoParteComun(@RequestBody Reclamo reclamo) {
        Edificio edificio = repositoryEdificio.getById(reclamo.getEdificio().getCodigo()).orElseThrow();
        Persona persona = repositoryPersona.getById(reclamo.getUsuario().getDocumento()).orElseThrow();
        Set<Persona> habitantes = edificio.habitantes();
        // Verificar si la persona tiene alguna relación con el edificio (dueño o inquilino de alguna unidad)
        if (!habitantes.contains(persona)) {
            throw new RuntimeException("Solo las personas relacionadas con el edificio pueden hacer un reclamo de parte común.");
        } else {
            repositoryReclamo.save(reclamo);
            return ResponseEntity.ok(reclamo.getNumero()); // Retorna el ID del reclamo
        }
    }

    // recibimos el id como parametro en la url, para edificio y para unidad.
    @GetMapping("/edificio/{id}")
    public ResponseEntity<List<ReclamoView>> getReclamosPorEdificio (@PathVariable("id") int idEdificio){
        List<Reclamo> reclamos = repositoryReclamo.getByEdificio(idEdificio);
        return ResponseEntity.ok(reclamos.stream().map(Reclamo::toView).toList());
    }

    @GetMapping("/unidad/{id}")
    public ResponseEntity<List<ReclamoView>> getReclamosPorUnidad (@PathVariable("id") int idUnidad){
        List<Reclamo> reclamos = repositoryReclamo.getByUnidad(idUnidad);
        return ResponseEntity.ok(reclamos.stream().map(Reclamo::toView).toList());
    }

    /// reveer esto, no se si esta bien
    @GetMapping("/{id}")
    public ResponseEntity<ReclamoView> getReclamo(@PathVariable int id) {
        Optional<Reclamo> reclamo = repositoryReclamo.getById(id);
        if (reclamo.isPresent()) {
            return ResponseEntity.ok(reclamo.get().toView());
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<ReclamoView>> getAllReclamos () {
        List<Reclamo> reclamos = repositoryReclamo.getAll();
        return ResponseEntity.ok(reclamos.stream().map(Reclamo::toView).toList());
    }

    private Imagen cargarImagen (MultipartFile file, Reclamo reclamo) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("El archivo esta vacio");
            }
            // Generar un nombre único para el archivo
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path path = Paths.get(uploadDir + File.separator + fileName);
            // Crear el directorio si no existe
            Files.createDirectories(path.getParent());
            // Guardar el archivo en el sistema de archivos
            Files.write(path, file.getBytes());

            // Crear la URL de la imagen
            String imageUrl = "/imagenes/" + fileName; // La URL accesible para la imagen

            // Crear el objeto Imagen y asociarlo con el reclamo
            Imagen imagen = new Imagen(imageUrl, file.getContentType());
            imagen.setReclamo(reclamo); // Asocia la imagen con el reclamo

            // Guardar la imagen en la base de datos
            repositoryImagen.save(imagen);

            // Añadir la imagen al reclamo
            reclamo.agregarImagen(imagen.getDireccion(), imagen.getTipo());
            return imagen;
        } catch (IOException e) {
            throw new RuntimeException("Error al subir la imagen: " + e.getMessage());
        }
    }
    @PostMapping("/{id}/imagenes/cargar")
    public ResponseEntity<String> cargarImagenAReclamo(@PathVariable int id, @RequestParam("file") MultipartFile file) {
        Optional<Reclamo> re = repositoryReclamo.getById(id);
        if (!re.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reclamo no encontrado");
        } else {
            Reclamo reclamo = re.get();
            cargarImagen(file, reclamo);
            return ResponseEntity.ok("Imagen cargada exitosamente");
        }
    }

}

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
import java.net.FileNameMap;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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

    @PostMapping("generareclamo/{persona}/{unidad}/{edificio}")
    public ResponseEntity<Integer> generarReclamo(
            @PathVariable String persona,@PathVariable int unidad,@PathVariable int edificio,
            @RequestParam String descripcion, @RequestParam int tiporeclamo) {

        Optional<Persona> personaobtener = repositoryPersona.getById(persona);
        Persona personafinal = null;
        if (personaobtener.isPresent()) {
            personafinal = personaobtener.get();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Persona no encontrada
        }

        Optional<Unidad> unidadobtener = repositoryUnidad.getById(unidad);
        Unidad unidadfinal = null;
        if (unidadobtener.isPresent()) {
            unidadfinal = unidadobtener.get();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Unidad no encontrada
        }

        Optional<Edificio> edificioobtener = repositoryEdificio.getById(edificio);
        Edificio edificiofinal = null;
        if (edificioobtener.isPresent()) {
            edificiofinal = edificioobtener.get();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Edificio no encontrado
        }


        if (unidadfinal.esInquilino(personafinal) ) {
            Reclamo reclamo = new Reclamo(personafinal, edificiofinal, edificiofinal.getDireccion(), descripcion, unidadfinal, tiporeclamo);
            repositoryReclamo.save(reclamo);
            return ResponseEntity.status(HttpStatus.CREATED).body(reclamo.getNumero()); // Reclamo creado exitosamente
        } else if (unidadfinal.esDuenio(personafinal) && !unidadfinal.estaHabitado()) {
            Reclamo reclamo = new Reclamo(personafinal, edificiofinal, edificiofinal.getDireccion(), descripcion, unidadfinal, tiporeclamo);
            repositoryReclamo.save(reclamo);
            return ResponseEntity.status(HttpStatus.CREATED).body(reclamo.getNumero()); // Reclamo creado exitosamente
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // Error 403, no se guarda el reclamo
        }
    }

    @PutMapping("/agregarimagen/{idreclamo}")
    public ResponseEntity<List<Imagen>> agregarImagen(@PathVariable int idreclamo, @RequestBody Imagen imagen) {
        Optional<Reclamo> reclamo = repositoryReclamo.getById(idreclamo);
        if (reclamo.isPresent()) {
            Reclamo reclamofinal = reclamo.get();
            System.out.println(imagen.toString());
            imagen.setReclamo(reclamofinal);
            repositoryImagen.save(imagen);
            return ResponseEntity.status(HttpStatus.OK).body(reclamofinal.getImagenes());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping("/cambiarestado/{idreclamo}")
    public ResponseEntity<Reclamo> cambiarestado(@PathVariable int idreclamo, @RequestParam Estado estado){
        Optional<Reclamo> reclamo = repositoryReclamo.getById(idreclamo);
        if (reclamo.isPresent()) {
            Reclamo reclamofinal = reclamo.get();
            reclamofinal.cambiarEstado(estado);
            repositoryReclamo.save(reclamofinal);
            return ResponseEntity.status(HttpStatus.CREATED).body(reclamofinal);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    @GetMapping("/obtenereclamos")
    public ResponseEntity<List<Reclamo>> obtenerReclamos() {
        List<Reclamo> reclamos = repositoryReclamo.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(reclamos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reclamo> obtenerReclamo(@PathVariable int id) {
        Optional<Reclamo> reclamo = repositoryReclamo.getById(id);
        if (reclamo.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(reclamo.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    /*
    @PutMapping
    public ResponseEntity<Reclamo>actualizarReclamo( @PathVariable String persona,@PathVariable int unidad,@PathVariable int edificio,
                                                     @RequestParam String descripcion, @RequestParam int tiporeclamo) {
        Optional<Persona> personaobtener = repositoryPersona.getById(persona);
        Persona personafinal = null;
        if (personaobtener.isPresent()) {
            personafinal = personaobtener.get();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Persona no encontrada
        }

        Optional<Unidad> unidadobtener = repositoryUnidad.getById(unidad);
        Unidad unidadfinal = null;
        if (unidadobtener.isPresent()) {
            unidadfinal = unidadobtener.get();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Unidad no encontrada
        }

        Optional<Edificio> edificioobtener = repositoryEdificio.getById(edificio);
        Edificio edificiofinal = null;
        if (edificioobtener.isPresent()) {
            edificiofinal = edificioobtener.get();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Edificio no encontrado
        }
        Reclamo reclamo = new Reclamo(personafinal, edificiofinal, edificiofinal.getDireccion(), descripcion, unidadfinal, tiporeclamo);
        repositoryReclamo.update(reclamo);
        return ResponseEntity<Reclamo>(reclamo,HttpStatus.OK); // Reclamo creado exitosamente
    }

     */



    }

package administracion.tpo.controller;

import administracion.tpo.dao.PersonaDAO;
import administracion.tpo.modelo.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.security.PublicKey;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")  // Permite solicitudes de cualquier origen
@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    @Autowired
    private PersonaDAO personaDAO;
    
  //recibe esto http://localhost:8080/api/personas/login/DNI 89231201/Jorge y devuelve true o false
    
  //login, devuelve true o false
      @GetMapping("/login/{documento}/{nombre}")
      public boolean login(@PathVariable String documento,@PathVariable String nombre){
          Optional<Persona> persona = personaDAO.getById(documento);
          if(persona.isPresent()) {
          Persona esta=persona.get();
          if(esta.getNombre().equalsIgnoreCase(nombre)) {
          return true;
          }
          }
       
          return false;
      }


    @GetMapping("/obtenerpersonas")
    public ResponseEntity<List<Persona>>obtenerpersonas(){
        List<Persona>personas=personaDAO.getAll();
        return new ResponseEntity<>(personas, HttpStatus.OK);
    }

    @GetMapping("/obtenerporid/{dni}")
    public ResponseEntity<Persona>obtenerporid(@PathVariable String dni){
        Optional<Persona>p=personaDAO.getById(dni);
        Persona nueva=null;
        if(p.isPresent()){
            nueva = p.get();
            return new ResponseEntity<>(nueva,HttpStatus.OK);
        }
        return new ResponseEntity<>(nueva,HttpStatus.NOT_FOUND);
    }

    @PostMapping("/guardarpersona")
    public ResponseEntity<Persona>guardarpersonas(@RequestBody Persona persona){
        List<Persona>personas=personaDAO.getAll();

        for(Persona p:personas){
            if(p.getDocumento().equals(persona.getDocumento())){
                return new ResponseEntity<>(persona,HttpStatus.CONFLICT);
            }
        }

        personaDAO.save(persona);
        return new ResponseEntity<>(persona,HttpStatus.CREATED);
    }


    @DeleteMapping("/{dni}")
    public  ResponseEntity<Void>eliminar(@PathVariable String dni){
        Optional<Persona>p=personaDAO.getById(dni);
        if(p.isPresent()){
            personaDAO.delete(p.get().getDocumento());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}

package administracion.tpo.controller;


import administracion.tpo.dao.PersonaDAO;
import administracion.tpo.dao.UsuarioLogeadoDAO;
import administracion.tpo.modelo.LoginRequest;
import administracion.tpo.modelo.Persona;
import administracion.tpo.modelo.UsuarioLogeado;
import administracion.tpo.repository.IRepositoryPersona;
import administracion.tpo.repository.IRepositoryUsuarioLogeado;
import administracion.tpo.views.PersonaView;
import administracion.tpo.views.UsuarioLogeadoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private IRepositoryPersona repositoryPersona;
    @Autowired
    private IRepositoryUsuarioLogeado repositoryUsuarioLogeado;

    @PostMapping("/crear")
    public ResponseEntity<PersonaView> createUser(@RequestBody Persona persona, @RequestBody UsuarioLogeado usuarioLogeado) {

        PersonaDAO.getInstance().save(persona, repositoryPersona);
        return ResponseEntity.ok(persona.toView());
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<PersonaView> getUser(@PathVariable String nombre) {
        Persona persona = PersonaDAO.getInstance().getByNombre(nombre, repositoryPersona).orElseThrow();
        return ResponseEntity.ok(persona.toView());
    }
    @GetMapping("/documento/{documento}")
    public ResponseEntity<PersonaView> getUserByDocumento (@PathVariable String documento) {
        Persona p = PersonaDAO.getInstance().getById(documento, repositoryPersona).orElseThrow();
        return ResponseEntity.ok(p.toView());
    }
    @PostMapping("/createAccount")
    public ResponseEntity<String> crearcuenta (@RequestBody UsuarioLogeado usuarioLogeado) {
        UsuarioLogeado userCreado = new UsuarioLogeado(usuarioLogeado.getUsername(), usuarioLogeado.getPassword(), usuarioLogeado.getPersona());
        UsuarioLogeadoDAO.getInstance().save(userCreado, repositoryUsuarioLogeado);
        return ResponseEntity.ok("Cuenta creada con exito");
    }
    @PostMapping("/login")
    public ResponseEntity<UsuarioLogeadoView> login(@RequestBody LoginRequest loginRequest) {
        UsuarioLogeado user = UsuarioLogeadoDAO.getInstance().getUser(loginRequest.getUsername(), loginRequest.getPassword(), repositoryUsuarioLogeado);
        if (user == null) {
            throw new RuntimeException("Usuario o contrasenia incorrectas");
        }
        return ResponseEntity.ok(user.toView());
    }
}
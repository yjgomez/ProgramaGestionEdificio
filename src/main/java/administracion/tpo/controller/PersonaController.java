package administracion.tpo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import administracion.tpo.dao.EdificioDAO;
import administracion.tpo.dao.PersonaDAO;
import administracion.tpo.modelo.Edificio;
import administracion.tpo.modelo.Persona;
import administracion.tpo.repository.IRepositoryPersona;
import administracion.tpo.views.EdificioView;

@RestController
@RequestMapping("api/personas")
public class PersonaController {

	@Autowired
	private IRepositoryPersona repositoriopersona;
	
	@GetMapping
	public List<Persona> getAll() {
		List<Persona> personas=PersonaDAO.getInstance().getAll(repositoriopersona);
		return personas;
	}
	
	@GetMapping("/{documento}")
	public Persona getById(@PathVariable("documento") String documento) {
		//la rta es http://localhost:8080/api/edificios/2
		Optional<Persona> e =PersonaDAO.getInstance().getById(documento, repositoriopersona);
		if(e.isPresent()) {
			Persona ed=e.get();
			
			return ed;
			//return ed;
		}else {
			System.out.println("no existe ese persona");
			return null;
		}
	}
	
	@GetMapping("/iniciarsesion")
	public Persona iniciarSesion(@RequestBody Persona persona) {
		//iniciar sesion con dni + clave
		Persona per =PersonaDAO.getInstance().iniciarSesion(persona, repositoriopersona);
		return per;
	}
	
	//se puede borar?solo si no figura en otras tablas
	@DeleteMapping("/{documento}")
	public void deleteById(@PathVariable("documento") String documento) {
		PersonaDAO.getInstance().delete(documento,repositoriopersona);
	}
	//create. todo
	@PostMapping
	public Persona crear(@RequestBody Persona persona) {
		//iniciar sesion con dni + clave
		Persona per =PersonaDAO.getInstance().create(persona, repositoriopersona);
		return per;
	}
	
	//update. todo

	
}

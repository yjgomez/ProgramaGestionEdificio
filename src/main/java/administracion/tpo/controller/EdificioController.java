package administracion.tpo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import administracion.tpo.dao.EdificioDAO;
import administracion.tpo.modelo.Edificio;
import administracion.tpo.repository.IRepositoryEdificio;
import administracion.tpo.views.EdificioView;
import administracion.tpo.views.UnidadView;

@RestController
@RequestMapping("api/edificios")
public class EdificioController {
	//la ruta es http://localhost:8080/api/edificios
	
	@Autowired
	 IRepositoryEdificio repositoryEdificio;
	
	@GetMapping
	public List<EdificioView> getAll() {
		List<Edificio> edificios=EdificioDAO.getInstance().getAll(repositoryEdificio);
		List<EdificioView> edificioview=new ArrayList<EdificioView>();
		for(Edificio e:edificios) {
			edificioview.add(e.toView());
		}
		return edificioview;
	}
	
	@GetMapping("/{id}")
	public EdificioView getById(@PathVariable("id") int id) {
		//la rta es http://localhost:8080/api/edificios/2
		Optional<Edificio> e =EdificioDAO.getInstance().getById(id, repositoryEdificio);
		if(e.isPresent()) {
			Edificio ed=e.get();
			EdificioView edview=ed.toView();
			
			return edview;
			//return ed;
		}else {
			System.out.println("no existe ese edificio");
			return null;
		}
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") int id) {
		EdificioDAO.getInstance().delete(id,repositoryEdificio);
	}
	
	@PostMapping
	public void createEdificio(@RequestBody Edificio edi) {
		EdificioDAO.getInstance().save(edi, repositoryEdificio);
	}
	
	//metodo de prueba
	@GetMapping("/saludo")  //http://localhost:8080/api/edificios/saludo
	public String saludar() {
		return "hola ";
	}
	
	//update
	@PutMapping("/{id}")
	public void updateEdificio(@PathVariable("id") int id,@RequestBody Edificio edi) {
		
		EdificioDAO.getInstance().update(edi, repositoryEdificio,id);
	}
	
}

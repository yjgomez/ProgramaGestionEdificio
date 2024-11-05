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
import administracion.tpo.dao.UnidadDAO;
import administracion.tpo.modelo.Edificio;
import administracion.tpo.modelo.Unidad;
import administracion.tpo.repository.IRepositoryEdificio;
import administracion.tpo.repository.IRepositoryPersona;
import administracion.tpo.repository.IRepositoryUnidad;
import administracion.tpo.views.EdificioView;
import administracion.tpo.views.UnidadPersonaView;
import administracion.tpo.views.UnidadView;

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
		List<Unidad> unidades=UnidadDAO.getInstance().getAll(repositoriounidad);
		List<UnidadView> unidadesview=new ArrayList<UnidadView>();
		for(Unidad e:unidades) {
			unidadesview.add(new UnidadView(e));
		}
		return unidadesview;
	}
	
	@GetMapping("/disponibles")
	public List<UnidadView> getAllDisponibles() {
		List<Unidad> unidades=UnidadDAO.getInstance().getAll(repositoriounidad);
		List<UnidadView> unidadesview=new ArrayList<UnidadView>();
		for(Unidad e:unidades) {
			if(!e.isHabitado()) {
				unidadesview.add(new UnidadView(e));
			}
			
		}
		return unidadesview;
	}
	
	@GetMapping("/{id}")
	public UnidadView getById(@PathVariable("id") int id) {
		//la rta es http://localhost:8080/api/edificios/2
		Optional<Unidad> e =UnidadDAO.getInstance().getById(id, repositoriounidad);
		if(e.isPresent()) {
			Unidad ed=e.get();
			
			//UnidadView edview=ed.toView();
			UnidadView uniview=new UnidadView(ed);
			
			return uniview;
			//return ed;
		}else {
			System.out.println("no existe esa unidad");
			return null;
		}
	}
	//create
	//Unidad uni1=crearUnidad(edificio,"2do","24",false);
	@PostMapping("/{idedificio}")
	public UnidadView createUnidad(@PathVariable("idedificio") int id,@RequestBody Unidad uni) {
		Unidad creada=UnidadDAO.getInstance().crearUnidad(id ,uni, repositoriounidad,repositoryEdificio);
		if(creada!=null) {
			return new UnidadView(creada);
		}
		return null;
	}
	
	//TO DO
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") int id) {
		UnidadDAO.getInstance().delete(id,repositoriounidad);
		//ver que se puede borrar y que no!!!
	}
	
	//update, 
		//tablas: alquila y duenios
		//liberar persona-unidad
		//liberar edificio-unidad???
	@PostMapping("/alquilar")
	public UnidadView crearAlquiler(@RequestBody UnidadPersonaView uniper) {
		Unidad creada=UnidadDAO.getInstance().crearAlquiler(uniper, repositoriounidad,repopersona);
		if(creada!=null) {
			return new UnidadView(creada);
		}
		return null;
	}
	
	@PostMapping("/comprar")
	public UnidadView crearCompra(@RequestBody UnidadPersonaView uniper) {
		Unidad creada=UnidadDAO.getInstance().crearCompra(uniper, repositoriounidad,repopersona);
		if(creada!=null) {
			return new UnidadView(creada);
		}
		return null;
	}
	
	@PostMapping("/liberarunidad")
	public UnidadView liberarunidad(@RequestBody UnidadPersonaView uniper) {
		Unidad creada=UnidadDAO.getInstance().liberarunidad(uniper, repositoriounidad);
		if(creada!=null) {
			return new UnidadView(creada);
		}
		return null;
	}
	
}

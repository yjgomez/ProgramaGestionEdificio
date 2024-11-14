package administracion.tpo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import administracion.tpo.dao.ReclamoDAO;
import administracion.tpo.dao.UnidadDAO;
import administracion.tpo.exceptions.EdificioException;
import administracion.tpo.exceptions.UnidadException;
import administracion.tpo.modelo.Persona;
import administracion.tpo.modelo.Reclamo;
import administracion.tpo.modelo.Unidad;
import administracion.tpo.repository.IRepositoryReclamo;
import administracion.tpo.repository.IRepositoryUnidad;
import administracion.tpo.views.EdificioView;
import administracion.tpo.views.ReclamoView;
import administracion.tpo.views.UnidadView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import administracion.tpo.dao.EdificioDAO;
import administracion.tpo.modelo.Edificio;
import administracion.tpo.repository.IRepositoryEdificio;

@RestController
@RequestMapping("/api/edificios")
public class EdificioController {
	//la ruta es http://localhost:8080/api/edificios
	
	@Autowired
	 EdificioDAO repositoryEdificio;
	@Autowired
	UnidadDAO repositoryUnidad;
	@Autowired
	ReclamoDAO repositoryReclamo;
	
	@GetMapping
	public List<Edificio> getAll() {
		return repositoryEdificio.getAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Edificio> getById(@PathVariable("id") int id) {//cambiar view por el objeto Edificio
		//la rta es http://localhost:8080/api/edificios/2
		Optional<Edificio> e =repositoryEdificio.getById(id);
		if(e.isPresent()) {
			Edificio ed=e.get();
			return new ResponseEntity<>(ed, HttpStatus.OK);
		}else {
			throw new RuntimeException("No existe el edificio con id: "+id);
		}
	}
	@GetMapping("/{id}/habitantes")
	public ResponseEntity<List<Persona>> getHabitantesByEdificio (@PathVariable Integer id) {
		Optional<Edificio> e = repositoryEdificio.getById(id);
		if(e.isPresent()) {
			Edificio ed = e.get();
			List<Persona> habitantes = ed.habitantes().stream().toList();
			return ResponseEntity.ok(habitantes);
		}else {
			throw new RuntimeException("No existe el edificio con id: "+id);
		}
	}

	@GetMapping("/{id}/unidades")
	public ResponseEntity<List<UnidadView>> getUnidadesByEdificio (@PathVariable Integer id) {
		Optional<Edificio> e = repositoryEdificio.getById(id);
		if(e.isPresent()) {
			Edificio ed = e.get();
			List<UnidadView> unidadesViews = repositoryUnidad.getByIdEdificio(ed.getCodigo()).stream().map(Unidad::toView).toList();
			return ResponseEntity.ok(unidadesViews);
		}else {
			throw new RuntimeException("No existe el edificio con id: "+id);
		}
	}

	@PostMapping("/crear")
	public ResponseEntity<Edificio> crearEdificio(@RequestBody Edificio edi) {
		List<Edificio>edificios=repositoryEdificio.getAll();
		for(Edificio e: edificios){
			if(e.getNombre().equals(edi.getNombre()) || e.getDireccion().equals(edi.getDireccion())) {
				return new ResponseEntity<>(HttpStatus.IM_USED);
			}
		}
		repositoryEdificio.save(edi);
		return new ResponseEntity<>(edi, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") int id) {
		repositoryEdificio.delete(id);
		return ResponseEntity.ok("Edificio eliminado");
	}
	
	@PutMapping ("/agregarunidad/{id}/{numerounidad}") //este metodo sirve para asignar una unidad a un edificio
	public ResponseEntity<Edificio> agregarUnidad(@PathVariable int id,@PathVariable int numerounidad) {
		Optional<Edificio>e=repositoryEdificio.getById(id);
		Edificio e2=null;
		
		if(e.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		}
		e2=e.get();

		Optional<Unidad>u=repositoryUnidad.getById(numerounidad);
		Unidad u2=null;

		if(u.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		}
		u2=u.get();

		e2.agregarUnidad(u2);

		u2.setEdificio(e2);
		repositoryEdificio.save(e2);
		repositoryUnidad.save(u2);
		return new ResponseEntity<>(e2,HttpStatus.OK);
	}
		
		

	@GetMapping("/{id}/duenios")
	public ResponseEntity<Set<Persona>> duenios(@PathVariable int id) {
		Set<Persona>duenios=repositoryEdificio.duenios();
		return new ResponseEntity<Set<Persona>>(duenios,HttpStatus.OK);
	}

	@GetMapping("/{id}/habilitados")
	//agregar metodo de habilitados o algo asi
	public ResponseEntity<Set<Persona>> habilitados(@PathVariable int id) {
		Set<Persona>habilitados=repositoryEdificio.habilitados(id);
		return new ResponseEntity<Set<Persona>>(habilitados,HttpStatus.OK);
	}




}

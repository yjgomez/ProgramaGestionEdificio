package administracion.tpo.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import administracion.tpo.dao.ReclamoDAO;
import administracion.tpo.dao.UnidadDAO;
import administracion.tpo.modelo.Reclamo;
import administracion.tpo.modelo.Unidad;
import administracion.tpo.repository.IRepositoryReclamo;
import administracion.tpo.repository.IRepositoryUnidad;
import administracion.tpo.views.EdificioView;
import administracion.tpo.views.ReclamoView;
import administracion.tpo.views.UnidadView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import administracion.tpo.dao.EdificioDAO;
import administracion.tpo.modelo.Edificio;
import administracion.tpo.repository.IRepositoryEdificio;

@RestController
@RequestMapping("api/edificios")
public class EdificioController {
	//la ruta es http://localhost:8080/api/edificios
	
	@Autowired
	 IRepositoryEdificio repositoryEdificio;
	@Autowired
	IRepositoryUnidad repositoryUnidad;
	@Autowired
	IRepositoryReclamo repositoryReclamo;
	
	@GetMapping
	public List<Edificio> getAll() {
		return EdificioDAO.getInstance().getAll(repositoryEdificio);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EdificioView> getById(@PathVariable("id") int id) {
		//la rta es http://localhost:8080/api/edificios/2
		Optional<Edificio> e =EdificioDAO.getInstance().getById(id, repositoryEdificio);
		if(e.isPresent()) {
			Edificio ed=e.get();
			return ResponseEntity.ok(ed.toView());
		}else {
			throw new RuntimeException("No existe el edificio con id: "+id);
		}
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") int id) {
		EdificioDAO.getInstance().delete(id,repositoryEdificio);
		return ResponseEntity.ok("Edificio eliminado");
	}
	
	@PostMapping
	public void createProducto(@RequestBody EdificioView edi) {
		EdificioDAO.getInstance().save(edi.toEntity(), repositoryEdificio);
	}
	@GetMapping("/{id}/unidades")
	public ResponseEntity<List<UnidadView>> getUnidadesByEdificio (@PathVariable Integer id) {
		Optional<Edificio> e = EdificioDAO.getInstance().getById(id, repositoryEdificio);
		if(e.isPresent()) {
			Edificio ed = e.get();
			List<UnidadView> unidadesViews = UnidadDAO.getInstance().getByIdEdificio(ed.getCodigo(), repositoryUnidad).stream().map(Unidad::toView).toList();
			return ResponseEntity.ok(unidadesViews);
		}else {
			throw new RuntimeException("No existe el edificio con id: "+id);
		}
	}
	
}

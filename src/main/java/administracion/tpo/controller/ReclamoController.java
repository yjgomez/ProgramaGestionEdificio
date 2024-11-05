package administracion.tpo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import administracion.tpo.modelo.Persona;

import administracion.tpo.dao.EdificioDAO;
import administracion.tpo.dao.ReclamoDAO;
import administracion.tpo.dao.UnidadDAO;
import administracion.tpo.modelo.Edificio;
import administracion.tpo.modelo.Imagen;
import administracion.tpo.modelo.Reclamo;
import administracion.tpo.modelo.Unidad;
import administracion.tpo.repository.IRepositoryEdificio;
import administracion.tpo.repository.IRepositoryImagen;
import administracion.tpo.repository.IRepositoryPersona;
import administracion.tpo.repository.IRepositoryReclamo;
import administracion.tpo.repository.IRepositoryUnidad;
import administracion.tpo.views.CrearReclamoView;
import administracion.tpo.views.EstadoReclamoView;
import administracion.tpo.views.ReclamoView;
import administracion.tpo.views.UnidadView;

@RestController
@RequestMapping("api/reclamos")
public class ReclamoController {
	
	@Autowired
	 IRepositoryReclamo repositorioreclamo;
	
	@Autowired
	private Cloudinary cloudinary;
	
	@Autowired
	private IRepositoryImagen imagenrepo;
	
	@Autowired
	 IRepositoryEdificio repoedi;
	
	@Autowired
	 IRepositoryPersona repopersona;
	
	@Autowired
	 IRepositoryUnidad repounidad;
	
	@GetMapping("/{id}")
	public Reclamo getbyId(@PathVariable("id") int id) {
		Optional<Reclamo> rec=ReclamoDAO.getInstance().getById(id, repositorioreclamo);
		if(rec.isPresent()) {
			//tenia problemas de recursividad
			Reclamo reclamo=rec.get();
			reclamo.setEdificio(null);
			reclamo.getUnidad().setEdificio(null);
			return reclamo;
		}
		return null;
		
	}
	
	@GetMapping
	public List<Reclamo> getAll() {
		List<Reclamo> reclamos=ReclamoDAO.getInstance().getAll(repositorioreclamo);
		
		//List<ReclamoView> vistas=new ArrayList<ReclamoView>();
		for(Reclamo r: reclamos) {
			//vistas.add( r.toView() );
			r.setEdificio(null);
			r.getUnidad().setEdificio(null);
			
		}
		return reclamos;
	}
	
	//Reclamo reclamo=crearReclamo("cochera","gotera en techo",p1,ed1,uni1);
	
	
	@PostMapping
	public Reclamo crearReclamo(@RequestBody CrearReclamoView crearReclamovista) {
		Reclamo creado=ReclamoDAO.getInstance().crearReclamo(crearReclamovista, repoedi, repositorioreclamo, repopersona, repounidad);
		if(creado!=null) {
			creado.setEdificio(null);
			creado.getUnidad().setEdificio(null);
		}
		
		return creado;// es null
		
	}
	@PutMapping("/{idreclamo}")
	public Reclamo subir(@PathVariable("idreclamo") int idreclamo,@RequestBody MultipartFile file) {
		//TODA LA LOGICA DEBERIA ESTAR EN IMAGESERVICE!!!
		return ReclamoDAO.getInstance().addImagen(imagenrepo, repositorioreclamo, idreclamo,cloudinary,file);
		
	}
	
	@PutMapping
	public Reclamo cambiarestado(@RequestBody EstadoReclamoView estadoreclamoview) {
		//TODA LA LOGICA DEBERIA ESTAR EN IMAGESERVICE!!!
		Reclamo reclamo =ReclamoDAO.getInstance().cambiarestado(repositorioreclamo, estadoreclamoview );
		if(reclamo!=null) {
			reclamo.setEdificio(null);
			reclamo.getUnidad().setEdificio(null);
		}
		
		return reclamo;
		
	}
	
	// to do:
		//
		//cambiar estado de reclamo(SOLO ADMIN)
		//borrar reclamo??
	
	 
	

}

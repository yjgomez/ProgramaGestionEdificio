package administracion.tpo;

import administracion.tpo.dao.*;
import administracion.tpo.modelo.*;
import administracion.tpo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.List;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class TpoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TpoApplication.class, args);
	}

	@Autowired
	 IRepositoryPersona repositoryPersona;
	@Autowired
	 IRepositoryEdificio repositoryEdificio;
	@Autowired
	 IRepositoryUnidad repositoryUnidad;
	@Autowired
	 IRepositoryImagen repositoryImagen;
	@Autowired
	 IRepositoryReclamo repositoryReclamo;
	
	

	@Override
	public void run(String... args) throws Exception {
		
		precargardatos();
		/*
		List<Persona> list = PersonaDAO.getInstance().getAll(repositoryPersona);
		for(Persona p : list) {
			System.out.println(p.toString());
		}
		*/

		/*
		Edificio ed = new Edificio("edificio1", "direccion1");
		EdificioDAO.getInstance().save(ed, repositoryEdificio);
		*/
		
		
		/*
		List<Edificio> listEdificio = EdificioDAO.getInstance().getAll(repositoryEdificio);
		for(Edificio e : listEdificio) {
			System.out.println(e.toString());
		}
		*/
		
		/*
		List<Unidad>uni=UnidadDAO.getInstance().getAll(repositoryUnidad);
		Unidad un=uni.get(10);
		for(Persona u: un.getInquilinos()) {
			System.out.println(u.toString());
		}
		*/
		
		Optional<Edificio> e =EdificioDAO.getInstance().getById(1, repositoryEdificio);
		if(e.isPresent()) {
			Edificio ed=e.get();
			
			System.out.println(ed.toString());
		}else {
			System.out.println("no existe ese edificio");
		}
		
		/*
		Optional<Unidad>un=UnidadDAO.getInstance().getById(2, repositoryUnidad);
		Unidad unidad=un.get();
		
		System.out.println(unidad.toString());
		
		Imagen i=new Imagen(ed.getDireccion(),"PNG");
		System.out.println(i.toString());
		
		Optional<Persona>p=PersonaDAO.getInstance().getById("CI 13230978", repositoryPersona);
		Persona pe=p.get();
		
		System.out.println(pe.toString());
		
		Reclamo r=new Reclamo(pe,ed,ed.getDireccion(),"Gotera",unidad,1);
		ReclamoDAO.getInstance().save(r, repositoryReclamo);
		
			*/
		
		
		/*
		Edificio ed = new Edificio("edificio1", "direccion1");
		EdificioDAO.getInstance().save(ed, repositoryEdificio);
		
		Unidad un = new Unidad("3","4", ed);
		UnidadDAO.getInstance().save(un, repositoryUnidad);
		 */
		
		
		/*
		Optional<Edificio> ed=EdificioDAO.getInstance().getById(1, repositoryEdificio);
		Edificio ed2=ed.get();
		Unidad un = new Unidad("3","4", ed2);
		UnidadDAO.getInstance().save(un, repositoryUnidad);

		List<Unidad>listauni=UnidadDAO.getInstance().getAll(repositoryUnidad);
		System.out.println(listauni.get(2).estaHabitado());
		
		for(Unidad u: listauni) {
			System.out.println(u.toString());
		}*/
		
		/*
		List<Persona>listaUnidad = PersonaDAO.getInstance().getAll(repositoryPersona);
		for(Persona p: listaUnidad) {
			System.out.println(p.toString());
		}
		*/
		
		
		/*
		Optional<Unidad>u=UnidadDAO.getInstance().getById(1, repositoryUnidad);
		Unidad un=u.get();
		
		for(Persona p: un.getDuenios()) {
			System.out.println(p.toString());
		}
		*/
		
		/*
		List<Reclamo>reclamos=ReclamoDAO.getInstance().getAll(repositoryReclamo);
		for(Reclamo r: reclamos) {
			System.out.println(r.toString());
		}
		*/

		}



	private void precargardatos() {
		//carga algunos datos en la base
		Persona p1=crearPersona("homero simpson","1234","40500201","homero@homer.com");
		Persona p2=crearPersona("marge simpson","1234","30122565","marge@marge.com");
		Edificio ed1=crearEdificio("casa simpson","siempre viva 1234");
		
		Unidad uni1=crearUnidad(ed1,"2do","24",false);
		Unidad uni2=crearUnidad(ed1,"4to","401",false);
		//reclamo, imagen,
		//la imagen se puede crear sola pero el reclamo debe conocer a la imagen
		Reclamo reclamo=crearReclamo("cochera","gotera en techo",p1,ed1,uni1);
		Reclamo reclamoDos=crearReclamo("cocina","mancha de humedad",p1,ed1,uni1);
		  //los reclamos anteriores no se crean por falta de permisos!!!
		
		
		//persona alquila(cambiar estado unidad)
				//TO DO
		personaAlquilaUnidad(p1, uni1);
		
		
		//persona compra(cambiar estado unidad)
			//TO DO
		personaCompraUnidad(p2,uni2);
		
		//persona habilitada para crear reclamo???
		Reclamo reclamo3=crearReclamo("cochera","gotera en techo",p1,ed1,uni1); 
				//este reclamo SI SE CREA
		Imagen img=new Imagen();
		img.setDireccion("el path");
		img.setTipo("jpg");
		agregarImagenAReclamo(reclamo3, img);
		//otra imagen
		Imagen imgdos=new Imagen();
		imgdos.setDireccion("otro path");
		imgdos.setTipo("jpg");
		agregarImagenAReclamo(reclamo3, imgdos);
	}



	private void personaCompraUnidad(Persona persona, Unidad unidad) {
		// TODO Auto-generated method stub
		if(!unidad.getDuenios().contains(persona)) {
			unidad.getDuenios().add(persona);
			System.out.println("    --------------> dueño fue agregado");
			repositoryUnidad.save(unidad);
		}
		
	}



	private void personaAlquilaUnidad(Persona persona, Unidad unidad) {
		if(!unidad.getInquilinos().contains(persona)) {
			unidad.getInquilinos().add(persona);
			System.out.println("      --------------> inquilino agregado");
			unidad.setHabitado(true);
			repositoryUnidad.save(unidad);
		}
		
	}



	private void agregarImagenAReclamo(Reclamo reclamo, Imagen img) {
		// TODO Auto-generated method stub
		if(reclamo!=null) {
			Imagen saved=repositoryImagen.save(img);  //save img
			reclamo.getImagenes().add(saved); //add
			repositoryReclamo.save(reclamo);  //save reclamo
		}

	}



	private Reclamo crearReclamo(String ubicacion, String descripcion, Persona persona, Edificio edificio, Unidad unidad) {
		
		boolean bandera=checkHabilitadoParaCrearReclamo(persona,unidad);
		if(bandera) {
			Reclamo reclamo=new Reclamo();
			reclamo.setDescripcion(descripcion);
			reclamo.setUbicacion(ubicacion);
			//aqui!!!!!
			reclamo.setUsuario(persona);
			reclamo.setEdificio(edificio);
			reclamo.setUnidad(unidad);
			return repositoryReclamo.save(reclamo);
		}
		System.out.println("                      **************************");
		System.out.println("    id unidad: "+unidad.getId()+"*************************");
		System.out.println("    persona: "+persona.getNombre() +"**************************");
		System.out.println("esa persona no tiene permiso para reclamar sobre esa unidad");
		return null;
		
	}



	private boolean checkHabilitadoParaCrearReclamo(Persona persona, Unidad unidad) {
		// TODO Auto-generated method stub
		if(unidad.getInquilinos().contains(persona)) {
			System.out.println(" es inquilino");
			return true;
		}
		if(unidad.getDuenios().contains(persona)) {
			System.out.println(" es dueño");
			return true;
		}
		System.out.println(" NO es dueño ni inquilino");
		return false;
		
	}



	private Unidad crearUnidad(Edificio ed1, String piso,
	 String numerounidad,boolean habitado) {
		// TODO Auto-generated method stub
		Unidad unidad= new Unidad();
		unidad.setEdificio(ed1);  //conoce
		unidad.setHabitado(habitado);
		unidad.setPiso(piso);
		unidad.setNumero(numerounidad);
		return repositoryUnidad.save(unidad);
	}



	private Edificio crearEdificio(String nombre, String direccion) {
		// TODO Auto-generated method stub
		Edificio edi=new Edificio();
		edi.setNombre(nombre);
		edi.setDireccion(direccion);
		return repositoryEdificio.save(edi);
	}



	private Persona crearPersona(String nombre, String clave, String documento, String email ) {
		// TODO Auto-generated method stub
		Persona per=new Persona();
		per.setNombre(nombre);
		per.setClave(clave);
		per.setDocumento(documento);
		per.setEmail(email);
		return repositoryPersona.save(per);
	}
	

	
	

}

package administracion.tpo.dao;

import administracion.tpo.modelo.Edificio;
import administracion.tpo.modelo.Persona;
import administracion.tpo.modelo.Reclamo;
import administracion.tpo.modelo.Unidad;
import administracion.tpo.repository.IRepositoryEdificio;
import administracion.tpo.repository.IRepositoryPersona;
import administracion.tpo.repository.IRepositoryReclamo;
import administracion.tpo.repository.IRepositoryUnidad;
import administracion.tpo.views.UnidadPersonaView;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

public class UnidadDAO {
    private static UnidadDAO instance;
    
    //@Autowired
    //private IRepositoryEdificio edificiorepo;

    private UnidadDAO(){
    }

    public static UnidadDAO getInstance() {
        if(instance==null){
            instance = new UnidadDAO();
        }
        return instance;
    }

    public List<Unidad> getAll(IRepositoryUnidad iRepositoryUnidad){
        return iRepositoryUnidad.findAll();
    }
    public Optional<Unidad> getById(int numero, IRepositoryUnidad iRepositoryUnidad){
        return iRepositoryUnidad.findById(numero);
    }

    public void save(Unidad unidad, IRepositoryUnidad iRepositoryUnidad){
        iRepositoryUnidad.save(unidad);
    }

    public void delete(Unidad unidad, IRepositoryUnidad iRepositoryUnidad){
    	//ACA TENGO ACCESO A LA UNIDAD COMPLETA!!!!
    	//(liberar aca?)
    	//
        iRepositoryUnidad.delete(unidad);
    }
    
    public void delete(Integer idunidad, IRepositoryUnidad iRepositoryUnidad){
        iRepositoryUnidad.deleteById(idunidad);
    }
    
    public void update(Unidad unidad, IRepositoryUnidad iRepositoryUnidad){
        if (iRepositoryUnidad.existsById(unidad.getId())) {
        	iRepositoryUnidad.save(unidad);
        }
    }

	public Unidad crearUnidad(int idedificio, Unidad uni, IRepositoryUnidad repositoriounidad,
			IRepositoryEdificio repositoryEdificio) {
		// TODO Auto-generated method stub
		if(repositoryEdificio.existsById(idedificio)) {
			Edificio edi=repositoryEdificio.findById(idedificio).get();
			uni.setEdificio(edi);
			uni.setHabitado(false);
			Unidad creada=repositoriounidad.save(uni);
			return creada;
		}else {
			System.out.println(" no existe ese edificio");
			return null;
		}
		
	}

	public Unidad crearAlquiler(UnidadPersonaView uniper, IRepositoryUnidad repositoriounidad,
			IRepositoryPersona repopersona) {
		
		Unidad unibuscada=buscarUnidad(uniper.getIdunidad(), repositoriounidad);
		
		Persona personabuscada=buscarPersona(uniper.getDocumentopersona(),repopersona);
		
		if(unibuscada!=null && personabuscada!=null) {
			if(!unibuscada.getInquilinos().contains(personabuscada)) {
				
				unibuscada.getInquilinos().add(personabuscada);
				System.out.println("      --------------> inquilino agregado");
				unibuscada.setHabitado(true);
				return repositoriounidad.save(unibuscada);
			}
		}
		return null;
	}

	private Persona buscarPersona(String documentopersona, IRepositoryPersona repopersona) {
		// TODO Auto-generated method stub
		Optional<Persona> optioanl=repopersona.findById(documentopersona);
	    if(optioanl.isPresent()) {
	    	return optioanl.get();
	    }
	    return null;
	}

	private Unidad buscarUnidad(Integer idunidad, IRepositoryUnidad repositoriounidad) {
		// TODO Auto-generated method stub
		Optional<Unidad> optioanl=getById(idunidad,repositoriounidad);
	    if(optioanl.isPresent()) {
	    	return optioanl.get();
	    }
	    return null;
	}

	public Unidad crearCompra(UnidadPersonaView uniper, IRepositoryUnidad repositoriounidad,
			IRepositoryPersona repopersona) {
		Unidad unibuscada=buscarUnidad(uniper.getIdunidad(), repositoriounidad);
		
		Persona personabuscada=buscarPersona(uniper.getDocumentopersona(),repopersona);
		
		if(unibuscada!=null && personabuscada!=null) {
			if(!unibuscada.getDuenios().contains(personabuscada)) {
				
				unibuscada.getDuenios().add(personabuscada);
				System.out.println("    --------------> dueño fue agregado");
				return repositoriounidad.save(unibuscada);
			}
		}
		return null;
	}

	public Unidad liberarunidad(UnidadPersonaView uniper, IRepositoryUnidad repositoriounidad) {
		Unidad unibuscada=buscarUnidad(uniper.getIdunidad(), repositoriounidad);
		if(unibuscada!=null) {
			unibuscada.getDuenios().clear();
			unibuscada.getInquilinos().clear();
			unibuscada.setHabitado(false);
			return repositoriounidad.save(unibuscada);
		}
		return null;
	}


}

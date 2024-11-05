package administracion.tpo.dao;

import administracion.tpo.modelo.Persona;
import administracion.tpo.repository.IRepositoryPersona;

import java.util.List;
import java.util.Optional;

public class PersonaDAO {
    private static PersonaDAO instance;



    private PersonaDAO(){
    }

    public static PersonaDAO getInstance() {
        if(instance==null){
            instance = new PersonaDAO();
        }
        return instance;
    }

    public List<Persona> getAll(IRepositoryPersona iRepositoryPersona){
        return iRepositoryPersona.findAll();
    }
    public Optional<Persona> getById(String documento, IRepositoryPersona iRepositoryPersona){
        return iRepositoryPersona.findById(documento);
    }

    public  Persona create (Persona persona, IRepositoryPersona iRepositoryPersona){
    	return iRepositoryPersona.save(persona);
    }

    public void delete(String documento, IRepositoryPersona iRepositoryPersona){
    	iRepositoryPersona.deleteById(documento);
    }
    
    public void update(Persona p, IRepositoryPersona iRepositoryPersona){
        if (iRepositoryPersona.existsById(p.getDocumento())) {
        	iRepositoryPersona.save(p);
        }
    }

	public Persona iniciarSesion(Persona persona, IRepositoryPersona repositoriopersona) {
		// TODO Auto-generated method stub
		Optional<Persona> buscada=repositoriopersona.findById(persona.getDocumento());
		if(buscada.isPresent()) {
			Persona encontrada=buscada.get();
			if(encontrada.getClave().equalsIgnoreCase(persona.getClave())) {
				return encontrada;
			}
			System.out.println("clave incorrecta");
		}
		System.out.println("no existe esa persona en la bbdd");
		return null;
	}


}

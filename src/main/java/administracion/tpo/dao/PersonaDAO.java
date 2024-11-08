package administracion.tpo.dao;

import administracion.tpo.modelo.Persona;
import administracion.tpo.repository.IRepositoryEdificio;
import administracion.tpo.repository.IRepositoryPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PersonaDAO {


    @Autowired
    IRepositoryPersona iRepositoryPersona;
    public List<Persona> getAll( ){
        return iRepositoryPersona.findAll();
    }
    public Optional<Persona> getById(String documento){
        return iRepositoryPersona.findById(documento);
    }

    public void save(Persona persona ){
    iRepositoryPersona.save(persona);
    }

    public void delete(String documento){
        iRepositoryPersona.deleteById(documento);
    }
    
    public void update(Persona p){
        if (iRepositoryPersona.existsById(p.getDocumento())) {
        	iRepositoryPersona.save(p);
        }
    }
    public Optional<Persona> getByNombre (String nombre){
        return iRepositoryPersona.findByNombre(nombre);
    }


}

package administracion.tpo.dao;

import administracion.tpo.modelo.Edificio;
import administracion.tpo.modelo.Persona;
import administracion.tpo.repository.IRepositoryEdificio;
import administracion.tpo.repository.IRepositoryPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EdificioDAO {
    @Autowired
    private IRepositoryEdificio iRepositoryEdificio;
    public List<Edificio> getAll(){
        return iRepositoryEdificio.findAll();
    }
    public Optional<Edificio> getById(int codigo){
        return iRepositoryEdificio.findById(codigo);
    }

    public void save(Edificio edificio ){
        iRepositoryEdificio.save(edificio);
    }

    public void delete(int codigo){
    	        if (iRepositoryEdificio.existsById(codigo)) {
        	iRepositoryEdificio.deleteById(codigo);
        } else {
        	throw new RuntimeException("No existe el edificio con id: "+codigo);
        }
    }
    
    public void update(Edificio ed){
        if (iRepositoryEdificio.existsById(ed.getCodigo())) {
        	iRepositoryEdificio.save(ed);
        }
    }

}

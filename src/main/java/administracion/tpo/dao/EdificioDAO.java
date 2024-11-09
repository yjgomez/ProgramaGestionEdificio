package administracion.tpo.dao;

import administracion.tpo.exceptions.EdificioException;
import administracion.tpo.modelo.Edificio;
import administracion.tpo.modelo.Persona;
import administracion.tpo.modelo.Unidad;
import administracion.tpo.repository.IRepositoryEdificio;
import administracion.tpo.repository.IRepositoryPersona;
import administracion.tpo.repository.IRepositoryUnidad;
import administracion.tpo.views.UnidadView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class EdificioDAO {
    @Autowired
    private IRepositoryEdificio iRepositoryEdificio;
    
    @Autowired
    private IRepositoryUnidad iunidad;
    
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
    

    public Set<Persona> duenios() {
    	List<Unidad>unidades=iunidad.findAll();
    	
		Set<Persona> resultado = new HashSet<Persona>();
		for(Unidad unidad : unidades) {
			List<Persona> duenios = unidad.getDuenios();
			for(Persona p : duenios)
				resultado.add(p);
		}
		return resultado;
	}

}

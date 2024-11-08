package administracion.tpo.dao;


import administracion.tpo.modelo.Imagen;
import administracion.tpo.modelo.Reclamo;
import administracion.tpo.repository.IRepositoryImagen;
import administracion.tpo.repository.IRepositoryReclamo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReclamoDAO {
    @Autowired
    IRepositoryReclamo iRepositoryReclamo;
    public List<Reclamo> getAll( ){
        return iRepositoryReclamo.findAll();
    }
    public Optional<Reclamo> getById(int numero ){
        return iRepositoryReclamo.findById(numero);
    }
    public List<Reclamo> getByEdificio(int codigo ){
        return iRepositoryReclamo.findByEdificio(codigo);
    }
    public List<Reclamo> getByUnidad(int identificador){
        return iRepositoryReclamo.findByUnidad(identificador);
    }

    public void save(Reclamo reclamo ){
        iRepositoryReclamo.save(reclamo);
    }

    public void delete(int numero ){
        iRepositoryReclamo.deleteById(numero);
    }
    
    public void update(Reclamo reclamo){
        if (iRepositoryReclamo.existsById(reclamo.getNumero())) {
        	iRepositoryReclamo.save(reclamo);
        }
    }
    public void deleteReclamosByUnidadId(int identificador){
        iRepositoryReclamo.deleteReclamosByUnidadId(identificador);
    }

}

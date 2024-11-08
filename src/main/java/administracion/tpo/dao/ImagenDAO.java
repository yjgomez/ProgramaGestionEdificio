package administracion.tpo.dao;

import administracion.tpo.modelo.Edificio;
import administracion.tpo.modelo.Imagen;
import administracion.tpo.repository.IRepositoryEdificio;
import administracion.tpo.repository.IRepositoryImagen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ImagenDAO {
    @Autowired
    IRepositoryImagen iRepositoryImagen;

    public List<Imagen> getAll(){
        return iRepositoryImagen.findAll();
    }
    public Optional<Imagen> getById(int numero){
        return iRepositoryImagen.findById(numero);
    }

    public void save(Imagen imagen){
        iRepositoryImagen.save(imagen);
    }

    public void delete(int numero){
        iRepositoryImagen.deleteById(numero);
    }
    
    public void update(Imagen im){
        if (iRepositoryImagen.existsById(im.getNumero())) {
        	iRepositoryImagen.save(im);
        }
    }

}

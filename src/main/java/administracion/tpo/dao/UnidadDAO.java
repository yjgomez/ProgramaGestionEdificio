package administracion.tpo.dao;

import administracion.tpo.exceptions.UnidadException;
import administracion.tpo.modelo.Persona;
import administracion.tpo.modelo.Reclamo;
import administracion.tpo.modelo.Unidad;
import administracion.tpo.repository.IRepositoryReclamo;
import administracion.tpo.repository.IRepositoryUnidad;
import administracion.tpo.views.UnidadView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UnidadDAO {
    @Autowired
    IRepositoryUnidad iRepositoryUnidad;
    public List<Unidad> getAll(){
        return iRepositoryUnidad.findAll();
    }
    public Optional<Unidad> getById(int numero){
        return iRepositoryUnidad.findById(numero);
    }

    public void save(Unidad unidad ){
        iRepositoryUnidad.save(unidad);
    }

    public void delete(Unidad unidad) throws UnidadException {
        Optional<Unidad> unidadOpt = getById(unidad.getId());
        if (unidadOpt.isPresent()) {
            iRepositoryUnidad.delete(unidadOpt.get());
        } else {
            throw new UnidadException("Unidad no encontrada" + unidad.getId());
        }
    }
    public void deleteById (Integer id) {
        iRepositoryUnidad.deleteById(id);
    }
    
    public void update(Unidad unidad){
        if (iRepositoryUnidad.existsById(unidad.getId())) {
        	iRepositoryUnidad.save(unidad);
        }
    }
    public List<Unidad> getByIdEdificio(int codigo){
        return iRepositoryUnidad.findByEdificio(codigo);
    }

    public Unidad alquilar(Unidad unidad, Persona persona) throws UnidadException {
        if (unidad.estaHabitado()) {
            throw new UnidadException("La unidad ya esta habitada");
        } else {
            unidad.estaHabitado();
            unidad.alquilar(persona);
            return iRepositoryUnidad.save(unidad);
        }
    }
    public Unidad transferir (Unidad unidad, Persona nuevoduenio) {
        unidad.transferir(nuevoduenio);
        iRepositoryUnidad.save(unidad);
        return unidad;
    }
     // un inquilino deja la unidad
    public Unidad borrarInquilino (Unidad unidad, Persona persona) {
        if (unidad.getInquilinos().contains(persona)) {
            unidad.getInquilinos().remove(persona);
            update(unidad);
            return unidad;
        } else throw new RuntimeException("El inquilino no vive en la unidad");
    }

    // solamente el duenio puede liberar la unidad
    public Unidad liberarUnidad (Unidad unidad,  Persona persona) {
        if (unidad.getDuenios().contains(persona)) {
            unidad.liberar();
            update(unidad);
            //update para guardar los datos
            return unidad;
        } else throw new RuntimeException("La persona que quiere liberar la unidad no es el duenio");
    }
}

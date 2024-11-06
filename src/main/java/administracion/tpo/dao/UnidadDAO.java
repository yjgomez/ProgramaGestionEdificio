package administracion.tpo.dao;

import administracion.tpo.exceptions.UnidadException;
import administracion.tpo.modelo.Persona;
import administracion.tpo.modelo.Reclamo;
import administracion.tpo.modelo.Unidad;
import administracion.tpo.repository.IRepositoryReclamo;
import administracion.tpo.repository.IRepositoryUnidad;
import administracion.tpo.views.UnidadView;

import java.util.List;
import java.util.Optional;

public class UnidadDAO {
    private static UnidadDAO instance;



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

    public void delete(UnidadView unidadView, IRepositoryUnidad iRepositoryUnidad) throws UnidadException {
        Optional<Unidad> unidadOpt = getById(unidadView.getId(), iRepositoryUnidad);
        if (unidadOpt.isPresent()) {
            iRepositoryUnidad.delete(unidadOpt.get());
        } else {
            throw new UnidadException("Unidad no encontrada" + unidadView.getId());
        }
    }
    
    public void update(Unidad unidad, IRepositoryUnidad iRepositoryUnidad){
        if (iRepositoryUnidad.existsById(unidad.getId())) {
        	iRepositoryUnidad.save(unidad);
        }
    }
    public List<Unidad> getByIdEdificio(int codigo, IRepositoryUnidad iRepositoryUnidad){
        return iRepositoryUnidad.findByEdificio(codigo);
    }

    public Unidad alquilar(Unidad unidad, IRepositoryUnidad iRepositoryUnidad, Persona persona) throws UnidadException {
        if (unidad.estaHabitado()) {
            throw new UnidadException("La unidad ya esta habitada");
        } else {
            unidad.estaHabitado();
            unidad.alquilar(persona);
            return iRepositoryUnidad.save(unidad);
        }
    }
    public Unidad transferir (Unidad unidad, Persona nuevoduenio, IRepositoryUnidad repositoryUnidad) {
        unidad.transferir(nuevoduenio);
        repositoryUnidad.save(unidad);
        return unidad;
    }
     // un inquilino deja la unidad
    public Unidad borrarInquilino (Unidad unidad, Persona persona, IRepositoryUnidad repositoryUnidad) {
        if (unidad.getInquilinos().contains(persona)) {
            unidad.getInquilinos().remove(persona);
            update(unidad, repositoryUnidad);
            return unidad;
        } else throw new RuntimeException("El inquilino no vive en la unidad");
    }

    // solamente el duenio puede liberar la unidad
    public Unidad liberarUnidad (Unidad unidad,  Persona persona, IRepositoryUnidad repositoryUnidad) {
        if (unidad.getDuenios().contains(persona)) {
            unidad.liberar();
            update(unidad, repositoryUnidad);
            return unidad;
        } else throw new RuntimeException("La persona que quiere liberar la unidad no es el duenio");
    }
}

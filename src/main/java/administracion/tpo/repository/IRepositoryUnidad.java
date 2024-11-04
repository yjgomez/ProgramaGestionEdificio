package administracion.tpo.repository;

import administracion.tpo.modelo.Unidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IRepositoryUnidad extends JpaRepository<Unidad,Integer> {
    @Query("SELECT u FROM Unidad u WHERE u.edificio.codigo = :codigo")
    public List<Unidad> findByEdificio(int codigo);
}

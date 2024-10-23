package administracion.tpo.repository;

import administracion.tpo.modelo.Reclamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryReclamo extends JpaRepository<Reclamo,Integer> {
}

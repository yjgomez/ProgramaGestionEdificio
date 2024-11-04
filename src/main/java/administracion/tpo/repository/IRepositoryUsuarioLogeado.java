package administracion.tpo.repository;

import administracion.tpo.modelo.UsuarioLogeado;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryUsuarioLogeado extends JpaRepository<UsuarioLogeado, String> {
}

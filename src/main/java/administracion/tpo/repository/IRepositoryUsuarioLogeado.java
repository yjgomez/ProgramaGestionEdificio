package administracion.tpo.repository;

import administracion.tpo.modelo.UsuarioLogeado;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IRepositoryUsuarioLogeado extends JpaRepository<UsuarioLogeado, String> {
    @Query("SELECT u FROM UsuarioLogeado u WHERE u.username = :username AND u.password = :password")
    UsuarioLogeado findBy(String username, String password);
}

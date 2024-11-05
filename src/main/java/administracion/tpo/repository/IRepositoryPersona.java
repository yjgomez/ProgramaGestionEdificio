package administracion.tpo.repository;

import administracion.tpo.modelo.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryPersona extends JpaRepository<Persona, String> {

}

package administracion.tpo.modelo;

import jakarta.persistence.*;

@Entity
public class UsuarioLogeado {
    @Id
    private String username;
    private String password;
    @OneToOne
    @JoinColumn(name="documento")
    private Persona persona;
}

package administracion.tpo.modelo;

import administracion.tpo.dao.UsuarioLogeadoDAO;
import administracion.tpo.views.UsuarioLogeadoView;
import jakarta.persistence.*;

@Entity
@Table(name="usuarioslogeados")
public class UsuarioLogeado {
    @Id
    private String username;
    private String password;
    @OneToOne
    @JoinColumn(name="documento")
    private Persona persona;

    public UsuarioLogeado(String username, String password, Persona persona) {
        this.username = username;
        this.password = password;
        this.persona = persona;
    }

    public UsuarioLogeado() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    public UsuarioLogeadoView toView () {
        return new UsuarioLogeadoView(username, persona.getDocumento());
    }
}

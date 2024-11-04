package administracion.tpo.dao;

import administracion.tpo.modelo.UsuarioLogeado;
import administracion.tpo.repository.IRepositoryUsuarioLogeado;

import java.util.Optional;

public class UsuarioLogeadoDAO {
    private static UsuarioLogeadoDAO instance;
    public UsuarioLogeadoDAO(){}
    public static UsuarioLogeadoDAO getInstance() {
        if(instance==null){
            instance = new UsuarioLogeadoDAO();
        }
        return instance;
    }
    public Optional<UsuarioLogeado> getByUsername(String username, IRepositoryUsuarioLogeado iRepositoryUsuarioLogeado){
        return iRepositoryUsuarioLogeado.findById(username);
    }
}

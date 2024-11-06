package administracion.tpo.views;

public class UsuarioLogeadoView {
    private String documento;
    private String username;

    public UsuarioLogeadoView(String documento, String username) {
        this.documento = documento;
        this.username = username;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

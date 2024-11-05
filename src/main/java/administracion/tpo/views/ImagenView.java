package administracion.tpo.views;

import administracion.tpo.modelo.Imagen;

public class ImagenView {
	
	private int numero;
	private String direccion;
	private String tipo;
	
	public ImagenView() {}

	public ImagenView(int numero, String direccion, String tipo) {
		this.numero = numero;
		this.direccion = direccion;
		this.tipo = tipo;
	}
	
	public ImagenView(Imagen img) {
		this.numero = img.getNumero();
		this.direccion = img.getDireccion();
		this.tipo = img.getTipo();
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}

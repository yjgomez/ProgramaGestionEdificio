package administracion.tpo.views;

import administracion.tpo.modelo.Estado;

public class EstadoReclamoView {
	Integer idreclamo;
	String estadoString;
	Estado estado;
	
	public EstadoReclamoView() {
		
	}
	public Integer getIdreclamo() {
		return idreclamo;
	}
	public void setIdreclamo(Integer idreclamo) {
		this.idreclamo = idreclamo;
	}
	public String getEstadoreclamo() {
		return estadoString;
	}
	public void setEstadoreclamo(String estadoreclamo) {
		this.estadoString = estadoreclamo;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	
}

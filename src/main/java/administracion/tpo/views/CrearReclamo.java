package administracion.tpo.views;

import org.springframework.web.bind.annotation.PathVariable;

import administracion.tpo.modelo.Estado;

public class CrearReclamo {
	String persona;
	int unidad;
	int edificio;
	String descripcion;
	Estado estado; 
	int tiporeclamo; //creo que lo tendriamos que sacar de las tablas. no ahce nada
	public CrearReclamo() {
		
	}
	
	
	public String getPersona() {
		return persona;
	}
	public void setPersona(String persona) {
		this.persona = persona;
	}
	public int getUnidad() {
		return unidad;
	}
	public void setUnidad(int unidad) {
		this.unidad = unidad;
	}
	public int getEdificio() {
		return edificio;
	}
	public void setEdificio(int edificio) {
		this.edificio = edificio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}


	public int getTiporeclamo() {
		return tiporeclamo;
	}


	public void setTiporeclamo(int tiporeclamo) {
		this.tiporeclamo = tiporeclamo;
	}
	
}

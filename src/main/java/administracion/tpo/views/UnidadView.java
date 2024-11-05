package administracion.tpo.views;

import java.util.ArrayList;
import java.util.List;

import administracion.tpo.modelo.Persona;
import administracion.tpo.modelo.Unidad;

public class UnidadView {

	private int id;
	private String piso;
	private String numero;
	private boolean habitado;
	//private EdificioView edificio;
	private Integer idedificio;
	List<Persona> inquilinos=new ArrayList<Persona>();
	List<Persona> duenios=new ArrayList<Persona>();
	
	public UnidadView() {}

	public UnidadView(int id, String piso, String numero, boolean habitado, EdificioView edificio) {
		this.id = id;
		this.piso = piso;
		this.numero = numero;
		this.habitado = habitado;
		//this.edificio = edificio;
	}
	public UnidadView(int id, String piso, String numero, boolean habitado, Integer codigoedificio) {
		this.id = id;
		this.piso = piso;
		this.numero = numero;
		this.habitado = habitado;
		//this.edificio = edificio;
		idedificio=codigoedificio;
		
	}
	
	public UnidadView(Unidad unidad) {
		
		this.id = unidad.getId();
		this.piso = unidad.getPiso();
		this.numero = unidad.getNumero();
		this.habitado = unidad.estaHabitado();
		//this.edificio = new EdificioView(unidad.getEdificio());
		inquilinos=unidad.getInquilinos();
		duenios=unidad.getDuenios();
	}

	public UnidadView(int id2, String piso2, String numero2, boolean habitado2, int codigoedificio, List<Persona> duenios,
			List<Persona> inquilinos) {
		this.id = id;
		this.piso = piso;
		this.numero = numero;
		this.habitado = habitado;
		//this.edificio = edificio;
		idedificio=codigoedificio;
		inquilinos=inquilinos;
		duenios=duenios;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public boolean isHabitado() {
		return habitado;
	}

	public void setHabitado(boolean habitado) {
		this.habitado = habitado;
	}

	/*
	public EdificioView getEdificio() {
		return edificio;
	}

	public void setEdificio(EdificioView edificio) {
		this.edificio = edificio;
	}
	*/
	public String toString() {
		return piso + " " + numero;
	}

	public Integer getIdedificio() {
		return idedificio;
	}

	public void setIdedificio(Integer idedificio) {
		this.idedificio = idedificio;
	}

	public List<Persona> getInquilinos() {
		return inquilinos;
	}

	public void setInquilinos(List<Persona> inquilinos) {
		this.inquilinos = inquilinos;
	}

	public List<Persona> getDuenios() {
		return duenios;
	}

	public void setDuenios(List<Persona> duenios) {
		this.duenios = duenios;
	}
	
	
}

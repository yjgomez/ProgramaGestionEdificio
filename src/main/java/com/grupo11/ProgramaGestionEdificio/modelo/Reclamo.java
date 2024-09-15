package com.grupo11.ProgramaGestionEdificio.modelo;

import java.util.ArrayList;
import java.util.List;

public class Reclamo {
	private int numero;
	private Persona usuario;
	private Edificio edificio;
	private String ubicación;
	private String descripcion;
	private Unidad unidad;
	private String estado="";
	private List<Imagen> imagenes;
	
	
	public Reclamo(Persona usuario, Edificio edificio, String ubicación, String descripcion, Unidad unidad) {
		this.usuario = usuario;
		this.edificio = edificio;
		this.ubicación = ubicación;
		this.descripcion = descripcion;
		this.unidad = unidad;
		
		imagenes = new ArrayList<Imagen>();
	}

	
}

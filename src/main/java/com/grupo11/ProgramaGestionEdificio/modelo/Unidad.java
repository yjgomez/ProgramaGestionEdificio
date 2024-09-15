package com.grupo11.ProgramaGestionEdificio.modelo;

import java.util.List;

public class Unidad {

	private int id;
	private String piso;
	private String numero;
	private boolean habitado;
	private Edificio edificio;
	private List<Persona> duenios;
	private List<Persona> inquilinos;
}

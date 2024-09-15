package com.grupo11.ProgramaGestionEdificio.controlador;

import java.util.List;

import com.grupo11.ProgramaGestionEdificio.views.EdificioView;


public class Controlador {
private static Controlador instancia;
	
	private Controlador() { }
	
	public static Controlador getInstancia() {
		if(instancia == null)
			instancia = new Controlador();
		return instancia;
	}
	
	public List<EdificioView> getEdificios(){
		return null;
	}
}

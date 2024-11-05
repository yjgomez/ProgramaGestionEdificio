package administracion.tpo.views;

import java.util.ArrayList;
import java.util.List;

import administracion.tpo.modelo.Edificio;

public class EdificioView {

	private int codigo;
	private String nombre;
	private String direccion;
	private List<UnidadView> unidadesview=new ArrayList<UnidadView>();
	
	public EdificioView () {}
	
	public EdificioView(int codigo, String nombre, String direccion) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.direccion = direccion;
	}
	
	public EdificioView(Edificio edi) {
		this.codigo = edi.getCodigo();
		this.nombre = edi.getNombre();
		this.direccion = edi.getDireccion();
		this.unidadesview=edi.getUnidadesView();
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String toString() {
		return codigo + " " + nombre; 
	}

	public List<UnidadView> getUnidadesview() {
		return unidadesview;
	}

	public void setUnidadesview(List<UnidadView> unidadesview) {
		this.unidadesview = unidadesview;
	}
	
	
}

package administracion.tpo.modelo;

import administracion.tpo.views.PersonaView;
import jakarta.persistence.*;

@Entity
@Table(name="personas")
public class Persona {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String documento;
	
	@Column(name = "nombre")
	private String nombre;
	
	private String email;
	
	private String clave;
	

	//----------------------------------------------------------
	public Persona(){

	}
	public Persona(String documento, String nombre) {
		this.documento = documento;
		this.nombre = nombre;
	}

	//----------------------------------------------------------
	public String getDocumento() {
		return documento;
	}

	public String getNombre() {
		return nombre;
	}

	public PersonaView toView() {
		return new PersonaView(documento, nombre);
	}

	public void save() {

	}

	public void delete() {

	}
	
	

	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	@Override
	public String toString() {
		return "Persona{" +
				"documento='" + documento + '\'' +
				", nombre='" + nombre + '\'' +
				'}';
	}
}

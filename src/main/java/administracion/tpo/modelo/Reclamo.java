package administracion.tpo.modelo;
import administracion.tpo.modelo.*;
import administracion.tpo.views.ReclamoView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="reclamos")
public class Reclamo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idreclamo;

	@ManyToOne
	@JoinColumn(name = "documento")
	@JsonIgnore
	private Persona usuario;

	@ManyToOne
	@JoinColumn(name = "codigo")
	//@JsonIgnoreProperties("reclamo")
	@JsonIgnore
	private Edificio edificio;

	private String ubicacion;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "identificador")
	@JsonIgnore
	private Unidad unidad;

	private String descripcion;


	//@Column(name="identificador")
	@Enumerated(EnumType.STRING)
	private Estado estado;


	@Column(name = "imagen")
	@OneToMany(mappedBy = "reclamo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties("reclamo")
	private List<Imagen> imagenes = new ArrayList<>();

	//fijarse si realmente sirve. Sino, sacarlo
	private int idtiporeclamo;

	public void setUsuario(Persona usuario) {
		this.usuario = usuario;
	}


	public Reclamo() {

	}

	public Reclamo(Persona usuario, Edificio edificio, String ubicacion, String descripcion, Unidad unidad, int idtiporeclamo) {
		this.usuario = usuario;
		this.edificio = edificio;
		this.ubicacion = ubicacion;
		this.descripcion = descripcion;
		this.unidad = unidad;
		this.estado = Estado.nuevo;
		this.idtiporeclamo = idtiporeclamo;
		imagenes = new ArrayList<>();
	}


	public int getNumero() {
		return idreclamo;
	}

	public void setNumero(int idreclamo) {
		this.idreclamo = idreclamo;
	}

	public Persona getUsuario() {
		return usuario;
	}

	public Edificio getEdificio() {
		return edificio;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Unidad getUnidad() {
		return unidad;
	}

	public Estado getEstado() {
		return estado;
	}

	public List<Imagen> getImagenes() {
		return this.imagenes;
	}

	public void addImagenes(Imagen imagen) {
		this.imagenes.add(imagen);
	}

	public void cambiarEstado(Estado estado) {
		this.estado = estado;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	public void save() {

	}

	public void update() {

	}

	@Override
	public String toString() {
		return "Reclamo [idreclamo=" + idreclamo + ", usuario=" + usuario + ", edificio=" + edificio + ", ubicacion="
				+ ubicacion + ", unidad=" + unidad + ", descripcion=" + descripcion + ", estado=" + estado
				+ ", imagenes=" + imagenes + ", idtiporeclamo=" + idtiporeclamo + "]";
	}


	public int getIdtiporeclamo() {
		return idtiporeclamo;
	}


	//fijarse si debería poder actualizar algo del reclamo
	public void setIdtiporeclamo(int idtiporeclamo) {
		this.idtiporeclamo = idtiporeclamo;
	}


	public void agregarImagen(String direccion, String tipo) {
		System.out.println("este metodo NO existia");
		
	}

}
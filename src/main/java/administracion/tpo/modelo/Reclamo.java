package administracion.tpo.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import administracion.tpo.views.ReclamoView;
import jakarta.persistence.*;

@Entity
@Table(name="reclamos")

public class Reclamo implements Serializable {
    private static final long serialVersionUID = 1L; // Recommended for Serializable classes
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idreclamo;

	@ManyToOne
	@JoinColumn(name = "documento")
	private Persona usuario;
	
	@ManyToOne
	@JoinColumn(name="id_edificio")
	private Edificio edificio;
	
	private String ubicacion;
	
	@ManyToOne
	@JoinColumn(name = "id_unidad")
	private Unidad unidad;
	
	private String descripcion;
	
	
	//@Column(name="identificador")
	@Enumerated(EnumType.STRING)
	private Estado estado;
	
	
	@Column(name = "imagen")
	@OneToMany
	@JoinColumn(name = "id_reclamo")
	private List<Imagen> imagenes;
	
	
	public void setUsuario(Persona usuario) {
		this.usuario = usuario;
	}
	
	//----------------------------------------------------------
	public Reclamo(){
		imagenes = new ArrayList<Imagen>();
		this.estado = Estado.nuevo; //OJO
	}
	
	
	public Reclamo(Persona usuario, Edificio edificio, String ubicacion, String descripcion, Unidad unidad,int idtiporeclamo) {
		this.usuario = usuario;
		this.edificio = edificio;
		this.ubicacion = ubicacion;
		this.descripcion = descripcion;
		this.unidad = unidad;
		this.estado = Estado.nuevo;
		imagenes = new ArrayList<Imagen>();
	}
	//----------------------------------------------------------
	public void agregarImagen(String direccion, String tipo) {
		Imagen imagen = new Imagen(direccion, tipo);
		imagenes.add(imagen);
		imagen.save(idreclamo);
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
	
	public List<Imagen> getImagenes(){
		return this.imagenes;
	}
	
	public void cambiarEstado(Estado estado) {
		this.estado = estado;
	}

	public void save() {
		
	}
	
	public void update() {
		
	}

	@Override
	public String toString() {
		return "Reclamo [idreclamo=" + idreclamo + ", usuario=" + usuario + ", edificio=" + edificio + ", ubicacion="
				+ ubicacion + ", unidad=" + unidad + ", descripcion=" + descripcion + ", estado=" + estado
				+ ", imagenes=" + imagenes;
	}


	public int getIdreclamo() {
		return idreclamo;
	}

	public void setIdreclamo(int idreclamo) {
		this.idreclamo = idreclamo;
	}

	public void setEdificio(Edificio edificio) {
		this.edificio = edificio;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public ReclamoView toView() {
		// TODO Auto-generated method stub
		
		return new ReclamoView(this.descripcion, this.estado, this.ubicacion, this.unidad, this.usuario);
	}
	
	
	
	
}

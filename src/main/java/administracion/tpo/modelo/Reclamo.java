package administracion.tpo.modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="reclamos")
public class Reclamo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idreclamo;

	@ManyToOne
	@JoinColumn(name = "documento")
	private Persona usuario;
	
	@ManyToOne
	@JoinColumn(name="codigo")
	private Edificio edificio;
	
	private String ubicacion;
	
	@ManyToOne
	@JoinColumn(name = "identificador")
	private Unidad unidad;
	
	private String descripcion;
	
	
	//@Column(name="identificador")
	@Enumerated(EnumType.STRING)
	private Estado estado;
	
	
	@Column(name = "imagen")
	@OneToMany
	@JoinColumn(name = "imagen_numero")
	private List<Imagen> imagenes;
	
	//fijarse si realmente sirve. Sino, sacarlo
	private int idtiporeclamo;
	
	public void setUsuario(Persona usuario) {
		this.usuario = usuario;
	}
	
	
	public Reclamo(){

	}
	
	
	public Reclamo(Persona usuario, Edificio edificio, String ubicacion, String descripcion, Unidad unidad,int idtiporeclamo) {
		this.usuario = usuario;
		this.edificio = edificio;
		this.ubicacion = ubicacion;
		this.descripcion = descripcion;
		this.unidad = unidad;
		this.estado = Estado.nuevo;
		this.idtiporeclamo=idtiporeclamo;
		imagenes = new ArrayList<Imagen>();
	}

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
				+ ", imagenes=" + imagenes + ", idtiporeclamo=" + idtiporeclamo + "]";
	}


	public int getIdtiporeclamo() {
		return idtiporeclamo;
	}


	public void setIdtiporeclamo(int idtiporeclamo) {
		this.idtiporeclamo = idtiporeclamo;
	}
	
	
}

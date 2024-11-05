package administracion.tpo.modelo;

import java.util.ArrayList;
import java.util.List;

import administracion.tpo.exceptions.UnidadException;
import administracion.tpo.views.EdificioView;
import administracion.tpo.views.UnidadView;
import jakarta.persistence.*;

@Entity
@Table(name = "unidades")
public class Unidad {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_unidad")
	private int id;
	
	private String piso;
	private String numero;
	private boolean habitado;
	/*
	@ManyToOne
	@JoinColumn(name = "codigoedificio")
	private Edificio edificio;
	*/
	@ManyToOne
    @JoinColumn(name = "edificio_id")
    private Edificio edificio;
	
	//poner el LAZY
	@ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(
        name = "duenios",
        joinColumns = @JoinColumn(name = "id_unidad"),
        inverseJoinColumns = @JoinColumn(name = "documento")
    )
	private List<Persona> duenios;
	
	@ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(
        name = "inquilinos",
        joinColumns = @JoinColumn(name = "id_unidad"),
        inverseJoinColumns = @JoinColumn(name = "documento")
    )
	private List<Persona> inquilinos;

	//----------------------------------------------------------
	public Unidad(){
		this.habitado = false;
		this.duenios = new ArrayList<Persona>();
		this.inquilinos = new ArrayList<Persona>();
	}
	
	public Unidad( String piso, String numero, Edificio edificio) {
		
		this.piso = piso;
		this.numero = numero;
		this.habitado = false;
		this.edificio = edificio;
		this.duenios = new ArrayList<Persona>();
		this.inquilinos = new ArrayList<Persona>();
	}

	public void transferir(Persona nuevoDuenio) {
		duenios = new ArrayList<Persona>();
		duenios.add(nuevoDuenio);
	}
	
	public void agregarDuenio(Persona duenio) {
		duenios.add(duenio);
	}
	
	public void alquilar(Persona inquilino) throws UnidadException {
		if(!this.habitado) {
			this.habitado = true;
			inquilinos = new ArrayList<Persona>();
			inquilinos.add(inquilino);
		}
		else
			throw new UnidadException("La unidad esta ocupada");
	}

	public void agregarInquilino(Persona inquilino) {
		inquilinos.add(inquilino);
	}
	
	public boolean estaHabitado() {
		return habitado;
	}
	
	public void liberar() {
		this.inquilinos = new ArrayList<Persona>();
		this.habitado = false;
	}
	
	public void habitar() throws UnidadException {
		if(this.habitado)
			throw new UnidadException("La unidad ya esta habitada");
		else
			this.habitado = true;
	}
	
	public int getId() {
		return id;
	}

	public String getPiso() {
		return piso;
	}

	public String getNumero() {
		return numero;
	}

	
	public Edificio getEdificio() {
		return edificio;
	}

	public List<Persona> getDuenios() {
		return duenios;
	}

	public List<Persona> getInquilinos() {
		return inquilinos;
	}
	
	
	//----------------------------------------------------------

	public void setPiso(String piso) {
		this.piso = piso;
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

	public UnidadView toView() {
		//EdificioView auxEdificio = edificio.toView();
		return new UnidadView(id, piso, numero, habitado,edificio.getCodigo(),duenios, inquilinos);
	}
	
	@Override
	public String toString() {
		return "Unidad [id=" + id + ", piso=" + piso + ", numero=" + numero + ", habitado=" + habitado + ", edificio="
				+ edificio + "]";
	}

	public void setEdificio(Edificio edificio) {
		this.edificio = edificio;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}

package ar.edu.unju.edm.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table (name="mozos")
@NamedQuery(name ="verMozos", query="SELECT m FROM Mozo m")
public class Mozo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="LEGAJO")
	private int legajo;
	@Column(name="ESTADO_MOZO")
	private boolean estadoMozo;
	@Column(name="NRO_MESAS")
	private int nroReservas;
	@Column(name="NOMBRE")
	private String nombre;
	@Column(name="APELLIDO")
	private String apellido;
	
	public Mozo() {
		
	}

	public Mozo(int legajo, boolean estadoMozo, int nroReservas, String nombre, String apellido) {
		super();
		this.legajo = legajo;
		this.estadoMozo = estadoMozo;
		this.nroReservas = nroReservas;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public int getLegajo() {
		return legajo;
	}

	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}

	public boolean isEstadoMozo() {
		return estadoMozo;
	}

	public void setEstadoMozo(boolean estadoMozo) {
		this.estadoMozo = estadoMozo;
	}

	public int getNroReservas() {
		return nroReservas;
	}

	public void setNroReservas(int nroReservas) {
		this.nroReservas = nroReservas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@Override
	public String toString() {
		return "Mozo [Legajo=" + legajo + ", Estado=" + estadoMozo + ", Mesas Atendidas=" + nroReservas + ", Nombre=" + nombre
				+ ", Apellido=" + apellido + "]";
	}
	
	
}

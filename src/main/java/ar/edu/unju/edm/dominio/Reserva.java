package ar.edu.unju.edm.dominio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="reservas")
@NamedQuery(name ="verReservas", query="SELECT r FROM Reserva r")
public class Reserva {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="NRO_RESERVA")
	private int nroReserva;
	@Column(name="NRO_PERSONAS")
	private int nroPersonas;
	@Column(name="HORA")
	private LocalTime hora;
	@Column(name="FECHA")
	private LocalDate fecha;
	@Column(name="ESTADO_RESERVA")
	private boolean estadoReserva;
	@Column(name="TOTAL")
	private double total;
	@OneToOne
	@JoinColumn(name="CLIENTE")
	private Cliente cliente;
	@OneToOne//(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name="SALON")
	private Salon salon;
	@OneToOne//(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name="MOZO")
	private Mozo mozo;
	@OneToMany(mappedBy = "reserva")
	private List<Mesa> mesas;
	
	public Reserva() {
		
	}

	public Reserva(int nroReserva, int nroPersonas, LocalTime hora, LocalDate fecha, boolean estadoReserva,
			double total, Cliente cliente, Salon salon, Mozo mozo, List<Mesa> mesas) {
		super();
		this.nroReserva = nroReserva;
		this.nroPersonas = nroPersonas;
		this.hora = hora;
		this.fecha = fecha;
		this.estadoReserva = estadoReserva;
		this.total = total;
		this.cliente = cliente;
		this.salon = salon;
		this.mozo = mozo;
		this.mesas = mesas;
	}

	public int getNroReserva() {
		return nroReserva;
	}

	public void setNroReserva(int nroReserva) {
		this.nroReserva = nroReserva;
	}

	public int getNroPersonas() {
		return nroPersonas;
	}

	public void setNroPersonas(int nroPersonas) {
		this.nroPersonas = nroPersonas;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public boolean isEstadoReserva() {
		return estadoReserva;
	}

	public void setEstadoReserva(boolean estadoReserva) {
		this.estadoReserva = estadoReserva;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Salon getSalon() {
		return salon;
	}

	public void setSalon(Salon salon) {
		this.salon = salon;
	}

	public Mozo getMozo() {
		return mozo;
	}

	public void setMozo(Mozo mozo) {
		this.mozo = mozo;
	}

	public List<Mesa> getMesas() {
		return mesas;
	}

	public void setMesas(List<Mesa> mesas) {
		this.mesas = mesas;
	}

	@Override
	public String toString() {
		return "Reserva [Nro = " + nroReserva + ", Personas = " + nroPersonas + ", Hora = " + hora + ", Fecha = "
				+ fecha + ", Estado = " + estadoReserva + ", Total = " + total + ", Cliente = " + cliente.getNombre() + ", Salon = "
				+ salon + ", Mozo = " + mozo.getLegajo() + ", Mesas = " + mesas + "]";
	}
	
}

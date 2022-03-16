package ar.edu.unju.edm.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table (name="mesas")
@NamedQuery(name ="verMesas", query="SELECT a FROM Mesa a")
public class Mesa {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="COD_MESA")
	private int codMesa;
	@Column(name="ESTADO_MESA")
	private boolean estadoMesa;
	@ManyToOne
	@JoinColumn(name="salon")
	private Salon salon;
	@ManyToOne
	@JoinColumn(name="reserva")
	private Reserva reserva;
	
	public Mesa() {
		
	}

	public Mesa(int codMesa, boolean estadoMesa, Salon salon, Reserva reserva) {
		super();
		this.codMesa = codMesa;
		this.estadoMesa = estadoMesa;
		this.salon = salon;
		this.reserva = reserva;
	}
	
	public int getCodMesa() {
		return codMesa;
	}

	public void setCodMesa(int codMesa) {
		this.codMesa = codMesa;
	}

	public boolean isEstadoMesa() {
		return estadoMesa;
	}

	public void setEstadoMesa(boolean estadoMesa) {
		this.estadoMesa = estadoMesa;
	}

	public Salon getSalon() {
		return salon;
	}

	public void setSalon(Salon salon) {
		this.salon = salon;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	@Override
	public String toString() {
		return "Mesa [Nro = " + codMesa + ", Estado = " + estadoMesa + ", Salon = " + salon + "]";
	}
	
	
	
}

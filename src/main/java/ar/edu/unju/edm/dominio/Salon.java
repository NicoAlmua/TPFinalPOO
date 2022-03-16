package ar.edu.unju.edm.dominio;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name="salones")
public class Salon {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="NRO_SALON")
	private int nroSalon;
	//private int capacidad;
	@OneToMany(mappedBy = "salon", fetch=FetchType.EAGER)
	private List<Mesa> mesas;
	
	
	public Salon() {
		
	}
	
	public Salon(int nroSalon, List<Mesa> mesas) {
		super();
		this.nroSalon = nroSalon;
		this.mesas = mesas;
	}
	
	public int getNroSalon() {
		return nroSalon;
	}
	
	public void setNroSalon(int nroSalon) {
		this.nroSalon = nroSalon;
	}
	
	public List<Mesa> getMesas() {
		return mesas;
	}

	public void setMesas(List<Mesa> mesas) {
		this.mesas = mesas;
	}

	@Override
	public String toString() {
		return "[Nro de Salon = " + nroSalon + "]";
	}
	
}

package ar.edu.unju.edm.dominio;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@DiscriminatorValue(value="turista")
public class ClienteTurista extends Cliente {
	
	@Column(name="CUIT")
	private long cuit;
	
	public ClienteTurista() {
		
	}

	public ClienteTurista(String email, String nombre, long telefono, long cuit) {
		super(email, nombre, telefono);
		this.cuit = cuit;
	}

	public long getCuit() {
		return cuit;
	}

	public void setCuit(long cuit) {
		this.cuit = cuit;
	}

	@Override
	public String toString() {
		return "Cliente [Email = " + getEmail() + ", Nombre = " + getNombre() + ", Telefono = " + getTelefono() + "Cuit = " + cuit + "]";
	}
	
	
}

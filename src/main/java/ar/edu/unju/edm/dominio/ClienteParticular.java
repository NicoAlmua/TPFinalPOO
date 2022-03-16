package ar.edu.unju.edm.dominio;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@DiscriminatorValue(value="particular")
public class ClienteParticular extends Cliente {
	
	@Column(name="DNI")
	private long dni;
	
	public ClienteParticular() {
		
	}

	public ClienteParticular(String email, String nombre, long telefono, long cuit) {
		super(email, nombre, telefono);
		this.dni = cuit;
	}

	public long getCuit() {
		return dni;
	}

	public void setCuit(long cuit) {
		this.dni = cuit;
	}

	@Override
	public String toString() {
		return "Cliente [Email = " + getEmail() + ", Nombre = " + getNombre() + ", Telefono = " + getTelefono() + "Dni = " + dni + "]";
	}
	
	
	
}

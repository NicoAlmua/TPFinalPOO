package ar.edu.unju.edm.dominio;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table (name="clientes")
@NamedQuery(name ="verClientes", query="SELECT c FROM Cliente c")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo", discriminatorType=DiscriminatorType.STRING)
public class Cliente {

	@Id
	//@GeneratedValue
	@Column(name="EMAIL")
	private String email;
	@Column(name="NOMBRE")
	private String nombre;
	@Column(name="TELEFONO")
	private long telefono;
	
	public Cliente() {

	}

	public Cliente(String email, String nombre, long telefono) {
		super();
		this.email = email;
		this.nombre = nombre;
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getTelefono() {
		return telefono;
	}

	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Cliente [Email = " + email + ", Nombre = " + nombre + ", Telefono = " + telefono + "]";
	}
	
	
	
}

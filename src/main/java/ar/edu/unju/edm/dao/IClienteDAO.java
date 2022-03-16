package ar.edu.unju.edm.dao;

import ar.edu.unju.edm.dominio.Cliente;

public interface IClienteDAO {

	public void guardarCliente(Cliente cliente);
	public Cliente consultarDatos(long idCliente);
}

package ar.edu.unju.edm.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ar.edu.unju.edm.dao.IClienteDAO;
import ar.edu.unju.edm.dao.imp.ClienteDAOImp;
import ar.edu.unju.edm.dominio.Cliente;

public class ClienteTest {

	@Test
	public void testConsultarCliente() {
		IClienteDAO clienteDAO = new ClienteDAOImp();
		int id = 111;
		Cliente cliente = clienteDAO.consultarDatos(id);
		assertTrue(cliente != null);
		//si existe el cliente en la base de datos devuelve verdadero
	}
}

package ar.edu.unju.edm.test;

import org.junit.Test;

import ar.edu.unju.edm.dao.IMesaDAO;
import ar.edu.unju.edm.dao.imp.MesaDAOImp;
import junit.framework.TestCase;

public class MesaTest extends TestCase{

	@Test
	public void testConsultarCant() {
		IMesaDAO mesaDAO = new MesaDAOImp();
		int comensales = 8;
		assertTrue(mesaDAO.cosultarCant(comensales));
		//si existen dos o mas mesas disponibles da verdadero
	}
}

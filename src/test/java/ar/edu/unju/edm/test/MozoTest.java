package ar.edu.unju.edm.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ar.edu.unju.edm.dao.IMozoDAO;
import ar.edu.unju.edm.dao.imp.MozoDAOImp;

public class MozoTest {

	@Test
	public void testCantMozos() {
		IMozoDAO mozoDAO = new MozoDAOImp();
		assertFalse(mozoDAO.cantMozos());
		
		//verifica que la cantidad de mozos no sea superior a 6
	}
}

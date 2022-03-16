package ar.edu.unju.edm.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import ar.edu.unju.edm.config.EmfSingleton;
import ar.edu.unju.edm.dao.ISalonDAO;
import ar.edu.unju.edm.dominio.Mesa;
import ar.edu.unju.edm.dominio.Salon;

public class SalonDAOImp implements ISalonDAO{

	private static EntityManager manager = EmfSingleton.getInstance().getEmf().createEntityManager();
	
	@Override
	public Salon asignarSalon(int nroSalon) {
		
		Query query = manager.createQuery("FROM Salon a WHERE id = :salon");
		query.setParameter("salon", nroSalon);
		Salon salon = (Salon)query.getSingleResult();
		return salon;
	}
	
}

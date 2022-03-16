package ar.edu.unju.edm.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import ar.edu.unju.edm.config.EmfSingleton;
import ar.edu.unju.edm.dao.IMozoDAO;
import ar.edu.unju.edm.dominio.Mesa;
import ar.edu.unju.edm.dominio.Mozo;

public class MozoDAOImp implements IMozoDAO {

	private static EntityManager manager = EmfSingleton.getInstance().getEmf().createEntityManager();
	
	@Override
	public void guardarMozo(Mozo mozo) {
		
		manager.getTransaction().begin();
		manager.persist(mozo);
		manager.getTransaction().commit();
	}

	@Override
	public void borrarMozo(Mozo mozo) {
		
		manager.getTransaction().begin();
		manager.remove(mozo);
		manager.getTransaction().commit();
	}

	@Override
	public List<Mozo> listarMozos() {
		
		@SuppressWarnings("unchecked")
		List<Mozo> mozos = (List<Mozo>) manager.createQuery("SELECT m FROM Mozo m").getResultList();
		return mozos;
	}

	@Override
	public boolean cantMozos() {
		
		boolean cant;
		int i;
		@SuppressWarnings("unchecked")
		List<Mozo> mozos = (List<Mozo>) manager.createQuery("SELECT m FROM Mozo m").getResultList();
		
		i = mozos.size();
		
		if(i == 6)
			cant = true;
		else
			cant = false;
		return cant;
	}
	
	
	@Override
	public Mozo asignarMozo() {
		
		Query query = manager.createQuery("FROM Mozo m WHERE m.estadoMozo = :estado");
		query.setParameter("estado", false);
		List<Mozo> mozos = (List<Mozo>)query.getResultList();
		Mozo mozo = mozos.stream().filter(m -> m.getNroReservas() < 4).findFirst().orElse(null);
		return mozo;
	}

	@Override
	public void ocuparMozo(Mozo mozo) {
		
		mozo.setEstadoMozo(true);
		mozo.setNroReservas(mozo.getNroReservas() + 1);
		manager.getTransaction().begin();
		manager.merge(mozo);
		manager.getTransaction().commit();
	}

	@Override
	public void desocuparMozo(Mozo mozo) {
		manager.getTransaction().begin();
		manager.merge(mozo);
		manager.getTransaction().commit();
	}

}

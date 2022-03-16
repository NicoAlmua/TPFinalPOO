package ar.edu.unju.edm.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import ar.edu.unju.edm.config.EmfSingleton;
import ar.edu.unju.edm.dao.IMesaDAO;
import ar.edu.unju.edm.dominio.Mesa;
import ar.edu.unju.edm.dominio.Mozo;
import ar.edu.unju.edm.dominio.Reserva;

public class MesaDAOImp implements IMesaDAO{

	private static EntityManager manager = EmfSingleton.getInstance().getEmf().createEntityManager();
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Mesa> consultarSalon(int nro) {
		
		Query query = manager.createQuery("FROM Mesa a WHERE a.salon.id = :salon AND a.estadoMesa = :estado");
		query.setParameter("salon", nro);
		query.setParameter("estado", false);
		List<Mesa> mesas = (List<Mesa>)query.getResultList();
		return mesas;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mesa> cosultarMesaOcup() {

		Query query = manager.createQuery("FROM Mesa a WHERE a.estadoMesa = :estado");
		query.setParameter("estado", true);
		return (List<Mesa>)query.getResultList();
	}

	@Override
	public boolean cosultarCant(int comensales) {
		
		boolean c;
		int cant;
		int nro = 1;
		Query query = manager.createQuery("FROM Mesa a WHERE a.salon.id = :salon AND a.estadoMesa = :estado");
		query.setParameter("salon", nro);
		query.setParameter("estado", false);
		List<Mesa> mesas = (List<Mesa>)query.getResultList();
		cant = mesas.size() * 4;
		if(comensales <= cant)
			c = true;
		else {
			nro = 2;
			query = manager.createQuery("FROM Mesa a WHERE a.salon.id = :salon AND a.estadoMesa = :estado");
			query.setParameter("salon", nro);
			query.setParameter("estado", false);
			mesas = (List<Mesa>)query.getResultList();
			cant = mesas.size() * 4;
			if(comensales <= cant)
				c = true;
			else
				c = false;
		}
		return c;
	}

	@Override
	public int asignarNroSalon(int comensales) {
		int nroSalon = 1;
		int cant;
		Query query = manager.createQuery("FROM Mesa a WHERE a.salon.id = :salon AND a.estadoMesa = :estado");
		query.setParameter("salon", nroSalon);
		query.setParameter("estado", false);
		List<Mesa> mesas = (List<Mesa>)query.getResultList();
		cant = mesas.size() * 4;
		if(comensales > cant) {
			nroSalon = 2;
			query = manager.createQuery("FROM Mesa a WHERE a.salon.id = :salon AND a.estadoMesa = :estado");
			query.setParameter("salon", nroSalon);
			query.setParameter("estado", false);
			mesas = (List<Mesa>)query.getResultList();
			cant = mesas.size() * 4;
		}
		return nroSalon;
	}

	@Override
	public void asignarMesas(int comensales, int nroSalon, Reserva reserva) {
		
		int cant, nroMesas;
		double ocup;
		Query query = manager.createQuery("FROM Mesa a WHERE a.salon.id = :salon");
		query.setParameter("salon", nroSalon);
		List<Mesa> mesas = (List<Mesa>)query.getResultList();
		ocup = (double)comensales / 4;
		nroMesas = (int) Math.ceil(ocup);
		
		do {
			Mesa mesa = mesas.stream().filter(m -> m.isEstadoMesa() == false).findFirst().orElse(null);
			mesa.setEstadoMesa(true);
			mesa.setReserva(reserva);
			manager.getTransaction().begin();
			manager.merge(mesa);
			manager.getTransaction().commit();
			nroMesas--;
		}while(nroMesas>0);
	}

	@Override
	public void desocuparMesas(List<Mesa> mesas) {
		
		for(Mesa m : mesas) {
			m.setEstadoMesa(false);
			m.setReserva(null);
			manager.getTransaction().begin();
			manager.merge(m);
			manager.getTransaction().commit();
		}
	}
	
}

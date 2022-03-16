package ar.edu.unju.edm.dao.imp;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ar.edu.unju.edm.config.EmfSingleton;
import ar.edu.unju.edm.dao.IReservaDAO;
import ar.edu.unju.edm.dominio.Cliente;
import ar.edu.unju.edm.dominio.Mesa;
import ar.edu.unju.edm.dominio.Reserva;

public class ReservaDAOImp implements IReservaDAO {

	private static EntityManager manager = EmfSingleton.getInstance().getEmf().createEntityManager();
	
	@Override
	public void guardarReserva(Reserva reserva) {
		
		manager.getTransaction().begin();
		manager.persist(reserva);
		manager.getTransaction().commit();
	}

	@Override
	public void eliminarRerserva(Reserva reserva) {
		
		manager.getTransaction().begin();
		manager.remove(reserva);
		manager.getTransaction().commit();
	}

	@Override
	public List<Reserva> listarReservas() {
		
		@SuppressWarnings("unchecked")
		List<Reserva> reservas = (List<Reserva>) manager.createQuery("SELECT r FROM Reserva r").getResultList();
		return reservas;
	}

	@Override
	public void pedirCuenta(Reserva reserva) {
		
		manager.getTransaction().begin();
		manager.merge(reserva);
		manager.getTransaction().commit();
	}

	@Override
	public Reserva buscarReserva(int nroReserva) {
		
		Query query = manager.createQuery("SELECT r FROM Reserva r " + "WHERE r.id = :reserva");
		query.setParameter("reserva", nroReserva);
		Reserva reserva;
		try {
			reserva = (Reserva)query.getSingleResult();
		}catch(NoResultException n) {
			reserva = null;
		}
		return reserva;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reserva> listarReservasSinCobrar() {
		
		Query query = manager.createQuery("FROM Reserva r WHERE r.estadoReserva = :estado");
		query.setParameter("estado", false);
		return (List<Reserva>)query.getResultList();
	}

}

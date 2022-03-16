package ar.edu.unju.edm.dao.imp;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ar.edu.unju.edm.config.EmfSingleton;
import ar.edu.unju.edm.dao.IClienteDAO;
import ar.edu.unju.edm.dominio.Cliente;

public class ClienteDAOImp implements IClienteDAO{

	private static EntityManager manager = EmfSingleton.getInstance().getEmf().createEntityManager();
	
	@Override
	public void guardarCliente(Cliente cliente) {
		
		manager.getTransaction().begin();
		manager.persist(cliente);
		manager.getTransaction().commit();	
	}

	@Override
	public Cliente consultarDatos(long idCliente) {
		
		Query query = manager.createQuery("FROM Cliente c WHERE c.dni = :cliente OR c.cuit = :cliente");
		query.setParameter("cliente", idCliente);
		Cliente cliente;
		try {
			cliente = (Cliente)query.getSingleResult();
		}catch(NoResultException n) {
			cliente = null;
		}
		return cliente;
	}

	
}

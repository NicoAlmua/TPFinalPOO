package ar.edu.unju.edm.dao;

import java.util.List;

import ar.edu.unju.edm.dominio.Reserva;

public interface IReservaDAO {

	public void guardarReserva(Reserva reserva);
	public void eliminarRerserva(Reserva reserva);
	public List<Reserva> listarReservas();
	public void pedirCuenta(Reserva reserva);
	public Reserva buscarReserva(int nroReserva);
	public List<Reserva> listarReservasSinCobrar();
}

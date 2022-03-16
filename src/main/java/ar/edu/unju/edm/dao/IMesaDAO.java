package ar.edu.unju.edm.dao;

import java.util.List;

import ar.edu.unju.edm.dominio.Mesa;
import ar.edu.unju.edm.dominio.Reserva;

public interface IMesaDAO {
	
	public List<Mesa> consultarSalon(int nro);
	public List<Mesa> cosultarMesaOcup();
	public boolean cosultarCant(int comensales);
	public int asignarNroSalon(int comensales);
	public void asignarMesas(int comensales, int nroSalon, Reserva reserva);
	public void desocuparMesas(List<Mesa> mesas);
}

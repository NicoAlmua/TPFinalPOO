package ar.edu.unju.edm.dao;

import java.util.List;

import ar.edu.unju.edm.dominio.Mozo;

public interface IMozoDAO {

	public void guardarMozo(Mozo mozo);
	public void borrarMozo(Mozo mozo);
	public List<Mozo> listarMozos();
	public boolean cantMozos();
	public Mozo asignarMozo(); 
	public void ocuparMozo(Mozo mozo);
	public void desocuparMozo(Mozo mozo);
}

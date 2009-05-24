package br.iav.ac.dao;

import br.iav.ac.negocio.ListaObjeto;

public interface DaoInterface {

	public void delete();
	public void edit();
	public void insert();
	public ListaObjeto load();
	
}

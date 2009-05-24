package br.iav.ac.negocio;

public interface ObjetoInterface {

	public void setCodigo(int codigo);
	public int getCodigo();
	public Objeto clone();
	
	//Métodos para inserção, edição, remoção e para carregar os dados do DB
	public void delete();
	public void edit();
	public void insert();
	public ListaObjeto load();
	
}
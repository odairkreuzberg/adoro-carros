package br.iav.ac.negocio;

public class Objeto implements ObjetoInterface {

	private int codigo;

	public Objeto() {
		this.codigo = 0;
	}
	
	public Objeto(int codigo) {
		this.codigo = codigo;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public Objeto clone() {
		Objeto obj = new Objeto();
		obj.setCodigo(this.getCodigo());
		return obj;
	}
	
	public void delete() {}
	
	public void edit() {}
	
	public void insert() {}

	public ListaObjeto load() {return null;}
	
}
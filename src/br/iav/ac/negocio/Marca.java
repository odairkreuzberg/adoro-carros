package br.iav.ac.negocio;

import br.iav.ac.dao.DaoMarca;

public class Marca extends Objeto implements ObjetoInterface {

	private String nome;
	
	public Marca() {
		this.nome = new String("");
	}
	
	public Marca(int codigo, String nome) {
		this.setCodigo(codigo);
		this.nome = new String(nome);
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Marca clone() {
		Marca marca = new Marca();
		marca.setCodigo(this.getCodigo());
		marca.setNome(this.getNome());
		return marca;
	}
	
	public String toString() {
		return this.getNome();
	}

	public void delete() {
		DaoMarca dao = new DaoMarca();
		dao.setMarca(this);
		dao.delete();
	}
	
	public void edit() {
		DaoMarca dao = new DaoMarca();
		dao.setMarca(this);
		dao.edit();
	}
	
	public void insert() {
		DaoMarca dao = new DaoMarca();
		dao.setMarca(this);
		dao.insert();
	}
	
	public ListaObjeto load() {
		DaoMarca dao = new DaoMarca();
		return dao.load();
	}
	
}
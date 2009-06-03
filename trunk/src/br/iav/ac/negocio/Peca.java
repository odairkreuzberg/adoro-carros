package br.iav.ac.negocio;

import br.iav.ac.dao.DaoPeca;

public class Peca extends Objeto implements ObjetoInterface {

	private String nome;
	
	public Peca() {
		this.nome = new String("");
	}
	
	public Peca(int codigo, String nome) {
		this.setCodigo(codigo);
		this.nome = new String(nome);
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Peca clone() {
		Peca peca = new Peca();
		peca.setCodigo(this.getCodigo());
		peca.setNome(this.getNome());
		return peca;
	}
	
	public String toString() {
		return this.getNome();
	}

	public void delete() {
		DaoPeca dao = new DaoPeca();
		dao.setPeca(this);
		dao.delete();
	}
	
	public void edit() {
		DaoPeca dao = new DaoPeca();
		dao.setPeca(this);
		dao.edit();
	}
	
	public void insert() {
		DaoPeca dao = new DaoPeca();
		dao.setPeca(this);
		dao.insert();
	}
	
	public ListaObjeto load() {
		DaoPeca dao = new DaoPeca();
		return dao.load();
	}
	
	public ListaObjeto search(String campo, String operador, String valor){
		DaoPeca dao = new DaoPeca();
		return dao.search(campo, operador, valor);		
	}
	
}
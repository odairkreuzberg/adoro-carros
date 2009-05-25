package br.iav.ac.negocio;

import br.iav.ac.dao.DaoStatus;

public class Status extends Objeto implements ObjetoInterface {

	private String nome;
	
	public Status() {
		this.nome = new String("");
	}
	
	public Status(int codigo, String nome) {
		this.setCodigo(codigo);
		this.nome = new String(nome);
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Status clone() {
		Status status = new Status();
		status.setCodigo(this.getCodigo());
		status.setNome(this.getNome());
		return status;
	}
	
	public String toString() {
		return this.getNome();
	}

	public void delete() {
		DaoStatus dao = new DaoStatus();
		dao.setStatus(this);
		dao.delete();
	}
	
	public void edit() {
		DaoStatus dao = new DaoStatus();
		dao.setStatus(this);
		dao.edit();
	}
	
	public void insert() {
		DaoStatus dao = new DaoStatus();
		dao.setStatus(this);
		dao.insert();
	}
	
	public ListaObjeto load() {
		DaoStatus dao = new DaoStatus();
		return dao.load();
	}
	
}
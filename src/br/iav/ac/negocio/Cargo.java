package br.iav.ac.negocio;

import br.iav.ac.dao.DaoCargo;

public class Cargo  extends Objeto implements ObjetoInterface {

	private String nome;
	private String descricao;
	
	public Cargo() {
		this.nome = new String("");
		this.descricao = new String("");
	}
	
	public Cargo(int codigo, String nome, String descricao) {
		this.setCodigo(codigo);
		this.nome = new String(nome);
		this.descricao = new String(descricao);
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Cargo clone() {
		Cargo cargo = new Cargo();
		cargo.setCodigo(this.getCodigo());
		cargo.setNome(this.getNome());
		cargo.setDescricao(this.getDescricao());
		return cargo;
	}
	
	public String toString() {
		return this.getNome();
	}

	public void delete() {
		DaoCargo dao = new DaoCargo();
		dao.setCargo(this);
		try {
			dao.delete();			
		} catch (RuntimeException e) {	
			throw new RuntimeException("Este Cargo " 
				+ "est� vinculado com um Funcion�rio e nao pode ser excluido!");
		}
		
		
	}
	
	public void edit() {
		DaoCargo dao = new DaoCargo();
		dao.setCargo(this);
		dao.edit();
	}
	
	public void insert() {
		DaoCargo dao = new DaoCargo();
		dao.setCargo(this);
		dao.insert();
	}
	
	public ListaObjeto load() {
		DaoCargo dao = new DaoCargo();
		return dao.load();
	}

	public ListaObjeto search(String campo, String operador, String valor){
		DaoCargo dao = new DaoCargo();
		return dao.search(campo, operador, valor);		
	}	
	
	public boolean existeCargo(Cargo cargo){
		DaoCargo dao = new DaoCargo();
		return dao.existeCargo(cargo);		
	}
	
}
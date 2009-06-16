package br.iav.ac.negocio;

import br.iav.ac.dao.DaoCidade;

public class Cidade extends Objeto implements ObjetoInterface {

	private String nome;
	private int ddd;
	
	public Cidade() {
		this.nome = new String("");
		this.ddd = 0;
	}
	
	public Cidade(int codigo, String nome, int ddd) {
		this.setCodigo(codigo);
		this.nome = new String(nome);
		this.ddd = ddd;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getDdd() {
		return ddd;
	}

	public void setDdd(int ddd) {
		this.ddd = ddd;
	}

	public Cidade clone() {
		Cidade cidade = new Cidade();
		cidade.setCodigo(this.getCodigo());
		cidade.setNome(this.getNome());
		cidade.setDdd(this.getDdd());
		return cidade;
	}
	
	public String toString() {
		return this.getNome();
	}

	public void delete() {
		DaoCidade dao = new DaoCidade();
		dao.setCidade(this);
		try {
			dao.delete();			
		} catch (RuntimeException e) {	
			throw new RuntimeException("Existem vinculos com esta Cidade que " +
				"não permitem sua exclusão!");
		}
	}
	
	public void edit() {
		DaoCidade dao = new DaoCidade();
		dao.setCidade(this);
		dao.edit();
	}
	
	public void insert() {
		DaoCidade dao = new DaoCidade();
		dao.setCidade(this);
		dao.insert();
	}
	
	public ListaObjeto load() {
		DaoCidade dao = new DaoCidade();
		return dao.load();
	}
	
	public ListaObjeto search(String campo, String operador, String valor){
		DaoCidade dao = new DaoCidade();
		return dao.search(campo, operador, valor);		
	}

}
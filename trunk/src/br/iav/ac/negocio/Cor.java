package br.iav.ac.negocio;

import br.iav.ac.dao.DaoCor;

public class Cor extends Objeto implements ObjetoInterface {

	private String nome;
	
	public Cor() {
		this.nome = new String("");
	}
	
	public Cor(int codigo, String nome) {
		this.setCodigo(codigo);
		this.nome = new String(nome);
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Cor clone() {
		Cor cor = new Cor();
		cor.setCodigo(this.getCodigo());
		cor.setNome(this.getNome());
		return cor;
	}
	
	public String toString() {
		return this.getNome();
	}

	public void delete() {
		DaoCor dao = new DaoCor();
		dao.setCor(this);
		try {
			dao.delete();			
		} catch (RuntimeException e) {	
			throw new RuntimeException("Esta Cor " 
				+ "está vinculada com um Carro e não pode ser excluida!");
		}
	}
	
	public void edit() {
		DaoCor dao = new DaoCor();
		dao.setCor(this);
		dao.edit();
	}
	
	public void insert() {
		DaoCor dao = new DaoCor();
		dao.setCor(this);
		dao.insert();
	}
	
	public ListaObjeto load() {
		DaoCor dao = new DaoCor();
		return dao.load();
	}
	
	public ListaObjeto search(String campo, String operador, String valor){
		DaoCor dao = new DaoCor();
		return dao.search(campo, operador, valor);		
	}
	
}
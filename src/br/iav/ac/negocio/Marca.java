package br.iav.ac.negocio;

import javax.swing.JOptionPane;

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
		try {
			dao.delete();			
		} catch (RuntimeException e) {	
			throw new RuntimeException("Esta Marca "
				+ "esta vinculada com um Modelo e nao pode ser excluido!");
		}
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
	
	public ListaObjeto search(String campo, String operador, String valor){
		DaoMarca dao = new DaoMarca();
		return dao.search(campo, operador, valor);		
	}

	public boolean existeMarca(Marca marca) {
		DaoMarca dao = new DaoMarca();
		return dao.existeMarca(marca);
	}
	
}
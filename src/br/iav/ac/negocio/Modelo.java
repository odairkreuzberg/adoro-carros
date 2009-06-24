package br.iav.ac.negocio;

import br.iav.ac.dao.DaoModelo;

public class Modelo extends Objeto implements ObjetoInterface {

	private String nome;
	private Marca marca;
	
	public Modelo() {
		this.nome = new String("");
		this.marca = new Marca();
	}
	
	public Modelo(int codigo, String nome, Marca marca) {
		this.setCodigo(codigo);
		this.nome = new String(nome);
		this.marca = marca.clone();
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Marca getMarca() {
		return marca.clone();
	}

	public void setMarca(Marca marca) {
		this.marca = marca.clone();
	}

	public Modelo clone() {
		Modelo modelo = new Modelo();
		modelo.setCodigo(this.getCodigo());
		modelo.setNome(this.getNome());
		modelo.setMarca(this.getMarca());
		return modelo;
	}
	
	public String toString() {
		return this.getNome();
	}

	public void delete() {
		DaoModelo dao = new DaoModelo();
		dao.setModelo(this);
		try {
			dao.delete();			
		} catch (RuntimeException e) {	
			throw new RuntimeException("Este Modelo " 
				+ "está vinculado com um Carro e não pode ser excluido!");
		}
	}
	
	public void edit() {
		DaoModelo dao = new DaoModelo();
		dao.setModelo(this);
		dao.edit();
	}
	
	public void insert() {
		DaoModelo dao = new DaoModelo();
		dao.setModelo(this);
		dao.insert();
	}
	
	public ListaObjeto load() {
		DaoModelo dao = new DaoModelo();
		return dao.load();
	}
	
	public ListaObjeto search(String campo, String operador, String valor){
		DaoModelo dao = new DaoModelo();
		return dao.search(campo, operador, valor);		
	}

	public boolean existeModelo(Modelo modelo) {
		DaoModelo dao = new DaoModelo();
		return dao.existeModelo(modelo);
	}
	
}
package br.iav.ac.negocio;

import java.util.Date;

import br.iav.ac.dao.DaoCargo;
import br.iav.ac.dao.DaoCarro;

public class Carro extends Objeto implements ObjetoInterface {

	private String placa;
	private Cliente cliente;
	private Modelo modelo;
	private Cor cor;
	private Date anoFabricacao;
	
	public Carro() {
		this.placa = new String("");
		this.cliente = new Cliente();
		this.modelo = new Modelo();
		this.cor = new Cor();
		this.anoFabricacao = new Date();
	}
	
	public Carro(int codigo, String placa, Cliente cliente, Modelo modelo, Cor cor, Date anoFabricacao) {
		this.setCodigo(codigo);
		this.placa = new String(placa);
		this.cliente = cliente.clone();
		this.modelo = modelo.clone();
		this.cor = cor.clone();
		this.anoFabricacao = anoFabricacao;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Cliente getCliente() {
		return cliente.clone();
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente.clone();
	}

	public Modelo getModelo() {
		return modelo.clone();
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo.clone();
	}

	public Cor getCor() {
		return cor.clone();
	}

	public void setCor(Cor cor) {
		this.cor = cor.clone();
	}

	public Date getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(Date anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public Carro clone() {
		Carro carro = new Carro();
		carro.setCodigo(this.getCodigo());
		carro.setPlaca(this.getPlaca());
		carro.setCliente(this.getCliente());
		carro.setModelo(this.getModelo());
		carro.setCor(this.getCor());
		carro.setAnoFabricacao(this.getAnoFabricacao());
		return carro;
	}
	
	public String toString() {
		return (this.getPlaca() + " - " + this.getModelo().getNome());
	}

	public void delete() {
		DaoCarro dao = new DaoCarro();
		dao.setCarro(this);
		dao.delete();
	}
	
	public void edit() {
		DaoCarro dao = new DaoCarro();
		dao.setCarro(this);
		dao.edit();
	}
	
	public void insert() {
		DaoCarro dao = new DaoCarro();
		dao.setCarro(this);
		dao.insert();
	}
	
	public ListaObjeto load() {
		DaoCarro dao = new DaoCarro();
		return dao.load();
	}

	public ListaObjeto search(String campo, String operador, String valor){
		DaoCargo dao = new DaoCargo();
		return dao.search(campo, operador, valor);		
	}	
	
}
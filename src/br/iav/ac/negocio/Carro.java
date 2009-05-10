package br.iav.ac.negocio;

import java.util.Date;

public class Carro {
 
	private String placa;
	private Cliente cliente;
	private Modelo modelo;
	private Cor cor;
	private Date anoFabricacao;
	
	public String getPlaca() {
		return placa;
	}
	
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Modelo getModelo() {
		return modelo;
	}
	
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	
	public Cor getCor() {
		return cor;
	}
	
	public void setCor(Cor cor) {
		this.cor = cor;
	}
	
	public Date getAnoFabricacao() {
		return anoFabricacao;
	}
	
	public void setAnoFabricacao(Date anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

}
 

package br.iav.ac.negocio;

import java.util.Date;

public class Servico {

	private Date dataExecucao;
	private Carro carro;
	private ListaFuncionario listaFuncionario;
	private ListaAtividade listaAtividade;
	private float preco;
	
	public Date getDataExecucao() {
		return dataExecucao;
	}
	
	public void setDataExecucao(Date dataExecucao) {
		this.dataExecucao = dataExecucao;
	}
	
	public Carro getCarro() {
		return carro;
	}
	
	public void setCarro(Carro carro) {
		this.carro = carro;
	}
	
	public ListaFuncionario getListaFuncionario() {
		return listaFuncionario;
	}
	
	public void setListaFuncionario(ListaFuncionario listaFuncionario) {
		this.listaFuncionario = listaFuncionario;
	}
	
	public ListaAtividade getListaAtividade() {
		return listaAtividade;
	}
	
	public void setListaAtividade(ListaAtividade listaAtividade) {
		this.listaAtividade = listaAtividade;
	}
	
	public float getPreco() {
		return preco;
	}
	
	public void setPreco(float preco) {
		this.preco = preco;
	}

}
 

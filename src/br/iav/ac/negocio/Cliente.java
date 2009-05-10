package br.iav.ac.negocio;

public class Cliente extends Pessoa {

	private String profissao;
	private int numeroSocio;
	private Carro carro;
	
	public String getProfissao() {
		return profissao;
	}
	
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	
	public int getNumeroSocio() {
		return numeroSocio;
	}
	
	public void setNumeroSocio(int numeroSocio) {
		this.numeroSocio = numeroSocio;
	}
	
	public Carro getCarro() {
		return carro;
	}
	
	public void setCarro(Carro carro) {
		this.carro = carro;
	}

}
 

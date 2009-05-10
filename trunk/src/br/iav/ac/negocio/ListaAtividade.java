package br.iav.ac.negocio;

public class ListaAtividade {

	private Atividade atividade;
	private Atividade proxAtividade;
	
	public Atividade getAtividade() {
		return atividade;
	}
	
	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}
	
	public Atividade getProxAtividade() {
		return proxAtividade;
	}
	
	public void setProxAtividade(Atividade proxAtividade) {
		this.proxAtividade = proxAtividade;
	}

}
 

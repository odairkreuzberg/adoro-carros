package br.iav.ac.negocio;

import br.iav.ac.dao.DaoAtividadePeca;

public class AtividadePeca extends Objeto {
	
	private Peca peca;
	
	private int quantidadePeca;
	
	private Atividade atividade;
	
	
	public AtividadePeca() {
		this.atividade = new Atividade();
		this.peca = new Peca();
		this.quantidadePeca = 0;
	}
	
	
	public AtividadePeca(Peca peca, Atividade atividade, int quantidadePeca) {
		super();
		this.atividade = atividade;
		this.peca  = peca;
		this.quantidadePeca = quantidadePeca;
	}

	public Peca getPeca() {
		return peca;
	}

	public void setPeca(Peca peca) {
		this.peca = peca;
	}

	public int getQuantidadePeca() {
		return quantidadePeca;
	}

	public void setQuantidadePeca(int quantidadePeca) {
		this.quantidadePeca = quantidadePeca;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}
	

	
	public Objeto clone(){
		AtividadePeca atividadePeca = new AtividadePeca();
		atividadePeca.setAtividade(this.getAtividade());
		atividadePeca.setPeca(this.getPeca());
		atividadePeca.setQuantidadePeca(this.getQuantidadePeca());
		return(atividadePeca);
	}


	public ListaObjeto getListaPeca(int cod) {
		DaoAtividadePeca dao = new DaoAtividadePeca();
		return dao.getListaPeca(cod);		
	}

}

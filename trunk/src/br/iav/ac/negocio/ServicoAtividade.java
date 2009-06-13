package br.iav.ac.negocio;

import br.iav.ac.dao.DaoServicoAtividade;

public class ServicoAtividade extends Objeto {
	
	private Servico servico;
	
	private Atividade atividade;
	
	private float precoAtividade;
	
	public ServicoAtividade() {
		this.servico = new Servico();
		this.atividade = new Atividade();
		this.precoAtividade = 0;
	}
	
	public ServicoAtividade(Servico servico, Atividade atividade, float precoAtividade){
		super();
		this.servico =servico;
		this.atividade = atividade;
		this.precoAtividade = precoAtividade;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public float getPrecoAtividade() {
		return precoAtividade;
	}

	public void setPrecoAtividade(float precoAtividade) {
		this.precoAtividade = precoAtividade;
	}
	
	public Objeto clone(){
		ServicoAtividade servicoAtividade = new ServicoAtividade();
		servicoAtividade.setAtividade(this.getAtividade());
		servicoAtividade.setPrecoAtividade(this.getPrecoAtividade());
		servicoAtividade.setServico(this.getServico());
		return (servicoAtividade);
	}
	
	public ListaObjeto getListaAtividade(int cod){
		DaoServicoAtividade dao = new DaoServicoAtividade();
		return dao.getListaAtividade(cod);
	}
	

}

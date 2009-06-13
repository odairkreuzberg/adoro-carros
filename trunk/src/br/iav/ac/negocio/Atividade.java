package br.iav.ac.negocio;

import br.iav.ac.dao.DaoAtividade;
import br.iav.ac.dao.DaoAtividadePeca;

public class Atividade extends Objeto {
	
	private String nome;
	
	private String tipo;
	
	private Funcionario funcionario;
	
	private ListaObjeto listaAtividadePeca;
	
	public Atividade() {
		super();		
		this.nome = "";
		this.tipo = "";
		this.funcionario = new Funcionario();
		this.listaAtividadePeca = new ListaObjeto(); 		
	}
	
	public Atividade(int codigo, String nome, String tipo, Funcionario funcionario) {
		super(codigo);		
		this.nome = nome;
		this.tipo = tipo;
		this.funcionario = funcionario; 		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public ListaObjeto getListaAtividadePeca() {
		return listaAtividadePeca;
	}

	public void setListaAtividadePeca(ListaObjeto listaAtividadePeca) {
		this.listaAtividadePeca = listaAtividadePeca;
	}
	
	public Objeto clone(){
		Atividade atividade = new Atividade();
		atividade.setCodigo(this.getCodigo());
		atividade.setFuncionario(this.getFuncionario());
		atividade.setListaAtividadePeca(this.getListaAtividadePeca());
		atividade.setNome(this.getNome());
		atividade.setTipo(this.getTipo());
		return(atividade);
	}
	
	@Override
	public String toString() {		
		return this.getNome();
	}
	

	
	public void insert(){
		DaoAtividade dao = new DaoAtividade();
		dao.setAtividade(this);
		dao.insert();
		this.setCodigo(dao.obterAtividade().getCodigo());
		DaoAtividadePeca daoAtividadePeca = new DaoAtividadePeca();
		for(int i=0;i<this.getListaAtividadePeca().getSize();i++){
			((AtividadePeca)this.getListaAtividadePeca().getObjeto(i)).getAtividade().setCodigo(this.getCodigo());
			daoAtividadePeca.setAtividadePeca(((AtividadePeca)this.getListaAtividadePeca().getObjeto(i)));
			daoAtividadePeca.insert();
		}
	}

	public void delete(){
		DaoAtividadePeca daoAtividadePeca = new DaoAtividadePeca();
		AtividadePeca atividadePeca = new AtividadePeca();
		for(int i=0;i<this.getListaAtividadePeca().getSize();i++){
			atividadePeca.getAtividade().setCodigo(this.getCodigo());
			atividadePeca.getPeca().setCodigo(this.getListaAtividadePeca().getObjeto(i).getCodigo());
			daoAtividadePeca.setAtividadePeca(atividadePeca);
			daoAtividadePeca.delete();
		}
		DaoAtividade dao = new DaoAtividade();
		dao.setAtividade(this);
		dao.delete();
	}
	
	public void edit(){
		DaoAtividadePeca daoAtividadePeca = new DaoAtividadePeca();
		AtividadePeca atividadePeca = new AtividadePeca();
		ListaObjeto listaAux = daoAtividadePeca.load();
		for(int i=0;i<listaAux.getSize();i++){
			atividadePeca.getAtividade().setCodigo(this.getCodigo());
			atividadePeca.getPeca().setCodigo(listaAux.getObjeto(i).getCodigo());
			daoAtividadePeca.setAtividadePeca(atividadePeca);
			daoAtividadePeca.delete();
		}
		for(int i=0;i<this.getListaAtividadePeca().getSize();i++){
			((AtividadePeca)this.getListaAtividadePeca().getObjeto(i)).getAtividade().setCodigo(this.getCodigo());
			daoAtividadePeca.setAtividadePeca(((AtividadePeca)this.getListaAtividadePeca().getObjeto(i)));
			daoAtividadePeca.insert();
		}
		DaoAtividade dao = new DaoAtividade();
		dao.setAtividade(this);
		dao.edit();
	}

	public ListaObjeto load(){
		DaoAtividade daoAtividade = new DaoAtividade();
		DaoAtividadePeca daoAtividadePeca = new DaoAtividadePeca();
		ListaObjeto listaAux = new ListaObjeto();
		listaAux = daoAtividade.load();
		for(int i=0;i < listaAux.getSize(); i++){
			((Atividade)listaAux.getObjeto(i)).setListaAtividadePeca(daoAtividadePeca.load());
			
		}
		return(listaAux);
	}
	
	public ListaObjeto search(String campo, String operador, String valor){
		DaoAtividade dao = new DaoAtividade();
		return dao.search(campo, operador, valor);		
	}

	public ListaObjeto getListaPeca(int  cod) {
		DaoAtividade dao = new DaoAtividade();
		return dao.getListaPeca(cod);
	}
	

}

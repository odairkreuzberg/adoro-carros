package br.iav.ac.negocio;

import br.iav.ac.dao.DaoAtividade;
import br.iav.ac.dao.DaoAtividadePeca;
import br.iav.ac.dao.DaoModelo;

public class Atividade extends Objeto {
	
	private String nome;
	
	private String tipo;
	
	private Funcionario funcionario;
	
	private ListaObjeto listaPeca;
	
	public Atividade() {
		super();		
		this.nome = "";
		this.tipo = "";
		this.funcionario = new Funcionario();
		this.listaPeca = new ListaObjeto(); 		
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

	public ListaObjeto getListaPeca() {
		return listaPeca;
	}

	public void setListaPeca(ListaObjeto listaPeca) {
		this.listaPeca = listaPeca;
	}
	
	public Objeto clone(){
		Atividade atividade = new Atividade();
		atividade.setCodigo(this.getCodigo());
		atividade.setFuncionario(this.getFuncionario());
		atividade.setListaPeca(this.getListaPeca());
		atividade.setNome(this.getNome());
		atividade.setTipo(this.getTipo());
		return(atividade);
	}
	

	
	public void insert(){
		DaoAtividade dao = new DaoAtividade();
		dao.setAtividade(this);
		dao.insert();
		this.setCodigo(dao.obterAtividade().getCodigo());
		DaoAtividadePeca daoAtividadePeca = new DaoAtividadePeca();
		AtividadePeca atividadePeca = new AtividadePeca();
		for(int i=0;i<this.getListaPeca().getSize();i++){
			atividadePeca.getAtividade().setCodigo(this.getCodigo());
			//atividadePeca.getQuantidadePeca();
			atividadePeca.getPeca().setCodigo(this.getListaPeca().getObjeto(i).getCodigo());
			daoAtividadePeca.setAtividadePeca(atividadePeca);
			daoAtividadePeca.insert();
		}
	}

	public void delete(){
		DaoAtividadePeca daoAtividadePeca = new DaoAtividadePeca();
		AtividadePeca atividadePeca = new AtividadePeca();
		for(int i=0;i<this.getListaPeca().getSize();i++){
			atividadePeca.getAtividade().setCodigo(this.getCodigo());
			//atividadePeca.getQuantidadePeca();
			atividadePeca.getPeca().setCodigo(this.getListaPeca().getObjeto(i).getCodigo());
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
		ListaObjeto listaAux = daoAtividadePeca.load(this.getCodigo());
		for(int i=0;i<listaAux.getSize();i++){
			atividadePeca.getAtividade().setCodigo(this.getCodigo());
			//atividadePeca.getQuantidadePeca();
			atividadePeca.getPeca().setCodigo(listaAux.getObjeto(i).getCodigo());
			daoAtividadePeca.setAtividadePeca(atividadePeca);
			daoAtividadePeca.delete();
		}
		for(int i=0;i<this.getListaPeca().getSize();i++){
			atividadePeca.getAtividade().setCodigo(this.getCodigo());
			//atividadePeca.getQuantidadePeca();
			atividadePeca.getPeca().setCodigo(this.getListaPeca().getObjeto(i).getCodigo());
			daoAtividadePeca.setAtividadePeca(atividadePeca);
			daoAtividadePeca.insert();
		}
		DaoAtividade dao = new DaoAtividade();
		dao.setAtividade(this);
		dao.delete();
	}

	public ListaObjeto load(){
		DaoAtividade daoAtividade = new DaoAtividade();
		DaoAtividadePeca daoAtividadePeca = new DaoAtividadePeca();
		ListaObjeto listaAux = new ListaObjeto();
		listaAux = daoAtividade.load();
		for(int i=0;i < listaAux.getSize(); i++){
			((Atividade)listaAux.getObjeto(i)).setListaPeca(daoAtividadePeca.load(((Atividade)listaAux.getObjeto(i)).getCodigo()));
			
		}
		return(listaAux);
	}
	
	public ListaObjeto search(String campo, String operador, String valor){
		DaoAtividade dao = new DaoAtividade();
		return dao.search(campo, operador, valor);		
	}
	

}
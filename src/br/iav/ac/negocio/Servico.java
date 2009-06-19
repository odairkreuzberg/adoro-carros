package br.iav.ac.negocio;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.iav.ac.dao.DaoFornecedorPeca;
import br.iav.ac.dao.DaoServico;
import br.iav.ac.dao.DaoServicoAtividade;

public class Servico extends Objeto {
	
	private Date dataInicio;
	
	private Date dataFim;
	
	private Carro carro;
	
	private Status status;
	
	private float valorTotal;
	
	private float valorAtividade;
	
	private float valorPeca;
	
	private float valorDesconto;
	
	private ListaObjeto listaServicoAtividade;
	
	public Servico() {
		super();
		this.dataFim  = new Date();
		this.dataInicio  = new Date();
		this.carro = new Carro();
		this.status = new Status();
		this.valorAtividade = 0;
		this.valorDesconto = 0;
		this.valorPeca = 0;
		this.valorTotal = 0;
	}
	
	public Servico(int codigo, Date dataFim, Date dataInicio, Carro carro, Status status, float valorAtividade, float valorDesconto, float valorPeca, float valorTotal){
		super(codigo);
		this.dataFim = dataFim;
		this.dataInicio = dataInicio;
		this.carro = carro;
		this.status = status;
		this.valorAtividade = valorAtividade;
		this.valorDesconto = valorDesconto;
		this.valorPeca = valorPeca;
		this.valorTotal = valorTotal;
		
	
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public float getValorAtividade() {
		return valorAtividade;
	}

	public void setValorAtividade(float valorAtividade) {
		this.valorAtividade = valorAtividade;
	}

	public float getValorPeca() {
		return valorPeca;
	}

	public void setValorPeca(float valorPeca) {
		this.valorPeca = valorPeca;
	}

	public float getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(float valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public ListaObjeto getListaServicoAtividade() {
		return listaServicoAtividade;
	}

	public void setListaServicoAtividade(ListaObjeto listaServicoAtividade) {
		this.listaServicoAtividade = listaServicoAtividade;
	}
	
	public Objeto clone(){
		Servico servico = new Servico();
		servico.setDataFim(this.getDataFim());
		servico.setDataInicio(this.getDataInicio());
		servico.setCodigo(this.getCodigo());
		servico.setCarro(this.getCarro());
		servico.setStatus(this.getStatus());
		servico.setValorAtividade(this.getValorAtividade());
		servico.setValorDesconto(this.getValorDesconto());
		servico.setValorPeca(this.getValorTotal());
		servico.setValorTotal(this.getValorTotal());
		servico.setListaServicoAtividade(this.getListaServicoAtividade());
		return (servico);
	}
	
	public void insert(ListaObjeto lista){
		DaoServico dao = new DaoServico();
		dao.setServico(this);
		dao.insert();
		setCodigo(dao.obterServido().getCodigo());
		DaoServicoAtividade daoServicoAtividade = new DaoServicoAtividade();
		for(int i = 0; i < this.getListaServicoAtividade().getSize(); i++){
			((ServicoAtividade)this.getListaServicoAtividade().getObjeto(i)).getServico().setCodigo(this.getCodigo());
			daoServicoAtividade.setServicoAtividade(((ServicoAtividade)this.getListaServicoAtividade().getObjeto(i)));
			daoServicoAtividade.insert();
		}
		if(this.getStatus().getNome().equals("Concluido")){
			this.editEstoque(lista);
		}			
	}
	
	public void edit(ListaObjeto lista) {
		DaoServicoAtividade daoServicoAtividade = new DaoServicoAtividade();
		ServicoAtividade servicoAtividade = new ServicoAtividade();
		ListaObjeto listaAux = daoServicoAtividade.load(this.getCodigo());
		for(int i=0;i<listaAux.getSize();i++){
			servicoAtividade.getServico().setCodigo(this.getCodigo());
			servicoAtividade.getAtividade().setCodigo(listaAux.getObjeto(i).getCodigo());
			daoServicoAtividade.setServicoAtividade(servicoAtividade);
			daoServicoAtividade.delete();		
		}
		for(int i = 0; i < this.getListaServicoAtividade().getSize(); i++){
			((ServicoAtividade)this.getListaServicoAtividade().getObjeto(i)).getServico().setCodigo(this.getCodigo());
			daoServicoAtividade.setServicoAtividade(((ServicoAtividade)this.getListaServicoAtividade().getObjeto(i)));
			daoServicoAtividade.insert();
		}
		DaoServico dao = new DaoServico();
		dao.setServico(this);
		dao.edit();
		if(this.getStatus().getNome().equals("Concluido")){
			this.editEstoque(lista);
		}
	}
	
	@Override
	public void delete() {
		DaoServicoAtividade daoServicoAtividade = new DaoServicoAtividade();
		ServicoAtividade servicoAtividade = new ServicoAtividade();
		ListaObjeto listaAux = daoServicoAtividade.load(this.getCodigo());
		for(int i=0;i<listaAux.getSize();i++){
			servicoAtividade.getServico().setCodigo(this.getCodigo());
			servicoAtividade.getAtividade().setCodigo(listaAux.getObjeto(i).getCodigo());
			daoServicoAtividade.setServicoAtividade(servicoAtividade);
			daoServicoAtividade.delete();		
		}
		DaoServico dao = new DaoServico();
		dao.setServico(this);
		dao.delete();
		
	}

	public ListaObjeto load(){
		DaoServico dao = new DaoServico();
		DaoServicoAtividade daoServicoAtividade = new DaoServicoAtividade();
		ListaObjeto listaAux = new ListaObjeto();
		listaAux = dao.load();
		for(int i=0;i < listaAux.getSize(); i++){
			((Servico)listaAux.getObjeto(i)).setListaServicoAtividade(daoServicoAtividade.load(((Servico)listaAux.getObjeto(i)).getCodigo()));
			
		}
		return(listaAux);
	}
	
	public ListaObjeto search(String campo, String operador, String valor){
		DaoServico dao = new DaoServico();
		return dao.search(campo, operador, valor);		
	}
	
	private void editEstoque(ListaObjeto lista){

		DaoFornecedorPeca dao = new DaoFornecedorPeca();
		int qtd = 0;
		for (int i = 0; i < lista.getSize(); i++) {
			PecaEstoque pe = ((PecaEstoque)lista.getObjeto(i));
			ListaObjeto listaFP = dao.getListaFornecedorPeca(((PecaEstoque) lista.getObjeto(i)).getCodigo());
			for (int j = 0; j < listaFP.getSize(); j++) {
				FornecedorPeca fp = (FornecedorPeca)listaFP.getObjeto(j);
				if(fp.getQtd() > pe.getQuantidade()){
					qtd = fp.getQtd() - pe.getQuantidade();
					fp.setQtd(qtd);
					fp.setPreco(fp.getPreco());
					dao.setFornecedorPeca(fp);
					dao.edit();
					break;
				}else{					
					qtd = 0;
					pe.setQuantidade(pe.getQuantidade() - fp.getQtd());
					fp.setQtd(qtd);
					fp.setPreco(0);
					dao.setFornecedorPeca(fp);
					dao.editEstoque();
				}
			}
		} 	
	}
	
	public boolean validarPecas(ListaObjeto lista){		
		for(int i=0;i<lista.getSize();i++){
			PecaEstoque pe = ((PecaEstoque)lista.getObjeto(i));
			int cod = ((PecaEstoque)lista.getObjeto(i)).getCodigo();
			int qtd = pe.getQtdEstoque();	
			for(int j=i;j<lista.getSize();j++){
				if(pe.getCodigo() == cod){
					if (qtd < pe.getQuantidade()){
						return false;
					}else{
						qtd = qtd - pe.getQuantidade();
						
					}					
				}				
			}
		}	
		return true;		
	}
}

package br.iav.ac.negocio;

import br.iav.ac.dao.DaoFornecedor;
import br.iav.ac.dao.DaoFornecedorPeca;
import br.iav.ac.dao.DaoModelo;
import br.iav.ac.dao.DaoPeca;

public class FornecedorPeca extends Objeto implements ObjetoInterface {
	
	/*----------------------------------------------------------
	 * ATTRIBUTOS
	 *----------------------------------------------------------*/

	private Peca peca;
	private Fornecedor fornecedor;
	private int qtd;
	private float preco;

	/*----------------------------------------------------------
	 * FIM DE ATTRIBUTOS
	 *----------------------------------------------------------*/
	
	/*----------------------------------------------------------
	 * CONSTRUTOR PADRAO
	 *----------------------------------------------------------*/

	public FornecedorPeca() {
		this.peca = new Peca();
		this.fornecedor = new Fornecedor();
		this.preco = 0;
		this.qtd = 0;
		
	}

	/*----------------------------------------------------------
	 * FIM DE CONSTRUTOR PADRAO
	 *----------------------------------------------------------*/
	
	/*----------------------------------------------------------
	 * CONSTRUTOR COM PARAMETROS
	 *----------------------------------------------------------*/
	
	public FornecedorPeca(Peca peca, Fornecedor fornecedor, int qtd, float preco) {
		this.peca = peca.clone();
		this.fornecedor = fornecedor.clone();
		this.preco = preco;
		this.qtd = qtd;
		
	}

	/*----------------------------------------------------------
	 * FIM DE CONSTRUTOR COM PARAMETROS
	 *----------------------------------------------------------*/

	/*----------------------------------------------------------
	 * GETS E SETS
	 *----------------------------------------------------------*/

	public Peca getPeca() {
		return peca;
	}

	public void setPeca(Peca peca) {
		this.peca = peca;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	/*----------------------------------------------------------
	 * FIM DE GETS E SETS
	 *----------------------------------------------------------*/


	/*----------------------------------------------------------
	 * PERSISTENCIA
	 *----------------------------------------------------------*/

	public FornecedorPeca clone(){
		FornecedorPeca fornecedorPeca = new FornecedorPeca();
		fornecedorPeca.setFornecedor(this.getFornecedor());
		fornecedorPeca.setPeca(getPeca());
		fornecedorPeca.setQtd(getQtd());
		fornecedorPeca.setPreco(getPreco());
		return fornecedorPeca;		
	}

	public void delete() {
		DaoFornecedorPeca dao = new DaoFornecedorPeca();
		dao.setFornecedorPeca(this);
		dao.delete();
	}
	
	public void edit(){
		DaoFornecedorPeca dao = new DaoFornecedorPeca();
		dao.setFornecedorPeca(this);
		dao.edit();
		
	}
	public void insert() {
		DaoFornecedorPeca dao = new DaoFornecedorPeca();
		dao.setFornecedorPeca(this);
		dao.insert();
		
	}
	public ListaObjeto load(){
		DaoFornecedorPeca dao = new DaoFornecedorPeca();
		return dao.load();
		
	}
	public ListaObjeto getPecas(){
		DaoFornecedorPeca dao = new DaoFornecedorPeca();
		return dao.getPecas();
		
	}
	
	public ListaObjeto search(String campo, String operador, String preco){
		DaoFornecedorPeca dao = new DaoFornecedorPeca();
		return dao.search(campo, operador, preco);		
	}

	public FornecedorPeca temFornecedorMarca(FornecedorPeca fornecedorPeca) {
		DaoFornecedorPeca dao = new DaoFornecedorPeca();
		return dao.temFornecedorMarca(fornecedorPeca);
	}
	
	/*----------------------------------------------------------
	 * FIM DE PERSISTENCIA
	 *----------------------------------------------------------*/


}

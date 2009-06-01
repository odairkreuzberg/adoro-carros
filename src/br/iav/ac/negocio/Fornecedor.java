package br.iav.ac.negocio;

import br.iav.ac.dao.DaoCliente;
import br.iav.ac.dao.DaoFornecedor;

public class Fornecedor extends Pessoa implements ObjetoInterface {

	private String cnpj;
	private String razaoSocial;
	private String fax;
	
	public Fornecedor() {
		this.cnpj = new String("");
		this.razaoSocial = new String("");
		this.fax = new String("");
	}
	
	public Fornecedor(int codigo, String nomeFantazia, String razaoSocial, String cnpj, String telefone,   String fax,Endereco endereco) {
		this.setCodigo(codigo);
		this.setNome(nomeFantazia);
		this.setEndereco(endereco);
		this.setTelefone(telefone);
		this.cnpj = new String(cnpj);
		this.razaoSocial = new String(razaoSocial);
		this.fax = new String(fax);
	}
	
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Fornecedor clone() {
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setCodigo(this.getCodigo());
		fornecedor.setNome(this.getNome());
		fornecedor.setEndereco(this.getEndereco());
		fornecedor.setTelefone(this.getTelefone());
		fornecedor.setCpf(this.getCpf());
		fornecedor.setCnpj(this.getCnpj());
		fornecedor.setRazaoSocial(this.getRazaoSocial());
		return fornecedor;
	}
	
	public String toString() {
		return this.getNome();
	}

	public void delete() {
		DaoFornecedor dao = new DaoFornecedor();
		dao.setFornecedor(this);
		dao.delete();
	}
	
	public void edit() {
		DaoFornecedor dao = new DaoFornecedor();
		dao.setFornecedor(this);
		dao.edit();
	}
	
	public void insert() {
		DaoFornecedor dao = new DaoFornecedor();
		dao.setFornecedor(this);
		dao.insert();
	}
	
	public ListaObjeto load() {
		DaoFornecedor dao = new DaoFornecedor();
		return dao.load();
	}
	
	public ListaObjeto search(String campo, String operador, String valor){
		DaoFornecedor dao = new DaoFornecedor();
		return dao.search(campo, operador, valor);		
	}
	
}
package br.iav.ac.negocio;

import br.iav.ac.dao.DaoFornecedor;

public class Fornecedor extends Pessoa implements ObjetoInterface {

	private String cnpj;
	
	public Fornecedor() {
		this.cnpj = new String("");
	}
	
	public Fornecedor(int codigo, String nome, Endereco endereco, String telefone, String cpf, String cnpj) {
		this.setCodigo(codigo);
		this.setNome(nome);
		this.setEndereco(endereco);
		this.setTelefone(telefone);
		this.setCpf(cpf);
		this.cnpj = new String(cnpj);
	}
	
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Fornecedor clone() {
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setCodigo(this.getCodigo());
		fornecedor.setNome(this.getNome());
		fornecedor.setEndereco(this.getEndereco());
		fornecedor.setTelefone(this.getTelefone());
		fornecedor.setCpf(this.getCpf());
		fornecedor.setCnpj(this.getCnpj());
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
	
}
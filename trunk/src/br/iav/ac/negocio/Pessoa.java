package br.iav.ac.negocio;

public class Pessoa extends Objeto {

	private String nome;
	private Endereco endereco;
	private String telefone;
	private String cpf;
	
	public Pessoa() {
		this.nome = new String("");
		this.endereco = new Endereco();
		this.telefone = new String("");
		this.cpf = new String("");
	}
	
	public Pessoa(int codigo, String nome, Endereco endereco, String telefone, String cpf) {
		this.setCodigo(codigo);
		this.nome = new String(nome);
		this.endereco = endereco.clone();
		this.telefone = new String(telefone);
		this.cpf = new String(cpf);
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Endereco getEndereco() {
		return endereco.clone();
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco.clone();
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Pessoa clone() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCodigo(this.getCodigo());
		pessoa.setNome(this.getNome());
		pessoa.setEndereco(this.getEndereco());
		pessoa.setTelefone(this.getTelefone());
		pessoa.setCpf(this.getCpf());
		return pessoa;
	}
	
	public String toString() {
		return this.getNome();
	}
	
}
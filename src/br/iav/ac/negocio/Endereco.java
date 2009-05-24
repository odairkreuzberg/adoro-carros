package br.iav.ac.negocio;

public class Endereco extends Objeto {

	private String rua;
	private int numero;
	private String bairro;
	private Cidade cidade;
	private String cep;
	private String complemento;
	
	public Endereco() {
		this.rua = new String("");
		this.numero = 0;
		this.bairro = new String("");
		this.cidade = new Cidade();
		this.cep = new String("");
		this.complemento = new String("");
	}
	
	public Endereco(String rua, int numero, String bairro, Cidade cidade, String cep, String complemento) {
		this.rua = new String(rua);
		this.numero = numero;
		this.bairro = new String(bairro);
		this.cidade = cidade.clone();
		this.cep = new String(cep);
		this.complemento = new String(complemento);
	}
	
	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Cidade getCidade() {
		return cidade.clone();
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade.clone();
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Endereco clone() {
		Endereco endereco = new Endereco();
		endereco.setCodigo(this.getCodigo());
		endereco.setRua(this.getRua());
		endereco.setNumero(this.getNumero());
		endereco.setBairro(this.getBairro());
		endereco.setCidade(this.getCidade());
		endereco.setCep(this.getCep());
		endereco.setComplemento(this.getComplemento());
		return endereco;
	}
	
	public String toString() {
		return (this.getRua() + ", " + this.getNumero() + ", " + this.getBairro() + " - " + this.getCidade());
	}
	
}
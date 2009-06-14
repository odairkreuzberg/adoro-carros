package br.iav.ac.negocio;
public class PecaEstoque extends Objeto{

	private String nome;

	private int quantidade;
	
	private int qtdEstoque;
	
	private int codAtividade;	
	
	private float preco;

	public PecaEstoque() {
	}

	public PecaEstoque(int codigo, String nome, int quantidade ,float preco) {

		super(codigo);
		this.nome = nome;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	public int getCodAtividade() {
		return codAtividade;
	}

	public void setCodAtividade(int codAtividade) {
		this.codAtividade = codAtividade;
	}

	public int getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(int qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public float getPreco() {
		return this.preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

}
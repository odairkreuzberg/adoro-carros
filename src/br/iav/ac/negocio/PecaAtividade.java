package br.iav.ac.negocio;


public class PecaAtividade extends Objeto{
	
	private String peca;
	
	private int qtd;

	public PecaAtividade(int codigo, String peca, int qtd, String fornecedor,  float preco) {
		super(codigo);
		this.peca = peca;
		this.qtd = qtd;
	}
	
	public PecaAtividade() {
		super();
	}

	public String getPeca() {
		return peca;
	}

	public void setPeca(String peca) {
		this.peca = peca;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

}

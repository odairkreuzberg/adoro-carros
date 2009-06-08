package br.iav.ac.negocio;

import javax.swing.text.StyledEditorKit.ForegroundAction;

public class PecaDetalhe extends Objeto{
	
	private String peca;
	
	private String fornecedor;
	
	private int qtd;
	
	private float preco;

	public PecaDetalhe(int codigo, String peca, int qtd, String fornecedor,  float preco) {
		super(codigo);
		this.peca = peca;
		this.fornecedor = fornecedor;
		this.qtd = qtd;
		this.preco = preco;
	}
	
	public PecaDetalhe() {
		super();
	}

	public String getPeca() {
		return peca;
	}

	public void setPeca(String peca) {
		this.peca = peca;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
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

}

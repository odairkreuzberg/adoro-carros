package br.iav.ac.negocio;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.iav.ac.dao.DaoCliente;

public class Cliente extends Pessoa implements ObjetoInterface {

	private String rg;
	private Date dataNascimento;
	private String profissao;
	private ListaObjeto listaCarro;
	
	public Cliente() {
		this.rg = new String("");
		this.dataNascimento = new Date();
		this.profissao = new String("");
		this.listaCarro = null;
	}
	
	public Cliente(int codigo, String nome, Endereco endereco, String telefone, String cpf, String rg, Date dataNascimento, String profissao, ListaObjeto listaCarro) {
		this.setCodigo(codigo);
		this.setNome(nome);
		this.setEndereco(endereco);
		this.setTelefone(telefone);
		this.setCpf(cpf);
		this.rg = new String(rg);
		this.dataNascimento = dataNascimento;
		this.profissao = new String(profissao);
		this.listaCarro = listaCarro;
	}
	
	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public String dataNascimentoToString() {
		SimpleDateFormat converterDate = new SimpleDateFormat("dd/MM/yyyy");
		String data = converterDate.format(dataNascimento);
		return data; 
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public ListaObjeto getListaCarro() {
		return listaCarro;
	}

	public void setListaCarro(ListaObjeto listaCarro) {
		this.listaCarro = listaCarro;
	}

	public Cliente clone() {
		Cliente cliente = new Cliente();
		cliente.setCodigo(this.getCodigo());
		cliente.setNome(this.getNome());
		cliente.setEndereco(this.getEndereco());
		cliente.setTelefone(this.getTelefone());
		cliente.setCpf(this.getCpf());
		cliente.setRg(this.getRg());
		cliente.setDataNascimento(this.getDataNascimento());
		cliente.setProfissao(this.getProfissao());
		cliente.setListaCarro(this.getListaCarro());
		return cliente;
	}
	
	public String toString() {
		return this.getNome();
	}

	public void delete() {
		DaoCliente dao = new DaoCliente();
		dao.setCliente(this);
		try {
			dao.delete();			
		} catch (RuntimeException e) {	
			throw new RuntimeException("Este Cliente " 
				+ "est� vinculado com um Carro e nao pode ser excluido!");
		}
	}
	
	public void edit() {
		DaoCliente dao = new DaoCliente();
		dao.setCliente(this);
		dao.edit();
	}
	
	public void insert() {
		DaoCliente dao = new DaoCliente();
		dao.setCliente(this);
		dao.insert();
	}
	
	public ListaObjeto load() {
		DaoCliente dao = new DaoCliente();
		return dao.load();
	}
	
	public ListaObjeto search(String campo, String operador, String valor){
		DaoCliente dao = new DaoCliente();
		return dao.search(campo, operador, valor);		
	}
	
	public ListaObjeto searchGrafico(){
		DaoCliente dao = new DaoCliente();
		return dao.searchGrafico();		
	}
	
}
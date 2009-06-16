package br.iav.ac.negocio;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.iav.ac.dao.DaoFuncionario;

public class Funcionario extends Pessoa implements ObjetoInterface {

	private String rg;
	private Date dataNascimento;
	private Cargo cargo;
	private float salario;
	
	public Funcionario() {
		this.rg = new String("");
		this.dataNascimento = new Date();
		this.cargo = new Cargo();
		this.salario = 0;
	}
	
	public Funcionario(int codigo, String nome, Endereco endereco, String telefone, String cpf, String rg, Date dataNascimento, Cargo cargo, float salario) {
		this.setCodigo(codigo);
		this.setNome(nome);
		this.setEndereco(endereco);
		this.setTelefone(telefone);
		this.setCpf(cpf);
		this.rg = new String(rg);
		this.dataNascimento = dataNascimento;
		this.cargo = cargo.clone();
		this.salario = salario;
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

	public Cargo getCargo() {
		return cargo.clone();
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo.clone();
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

	public Funcionario clone() {
		Funcionario funcionario = new Funcionario();
		funcionario.setCodigo(this.getCodigo());
		funcionario.setNome(this.getNome());
		funcionario.setEndereco(this.getEndereco());
		funcionario.setTelefone(this.getTelefone());
		funcionario.setCpf(this.getCpf());
		funcionario.setRg(this.getRg());
		funcionario.setDataNascimento(this.getDataNascimento());
		funcionario.setCargo(this.getCargo());
		funcionario.setSalario(this.getSalario());
		return funcionario;
	}
	
	public String toString() {
		return this.getNome();
	}

	public void delete() {
		DaoFuncionario dao = new DaoFuncionario();
		dao.setFuncionario(this);
		try {
			dao.delete();			
		} catch (RuntimeException e) {	
			throw new RuntimeException("Este Funcionário " 
				+ "está vinculado com uma Atividade e não pode ser excluido!");
		}
	}
	
	public void edit() {
		DaoFuncionario dao = new DaoFuncionario();
		dao.setFuncionario(this);
		dao.edit();
	}
	
	public void insert() {
		DaoFuncionario dao = new DaoFuncionario();
		dao.setFuncionario(this);
		dao.insert();
	}
	
	public ListaObjeto load() {
		DaoFuncionario dao = new DaoFuncionario();
		return dao.load();
	}
	
	public ListaObjeto search(String campo, String operador, String valor){
		DaoFuncionario dao = new DaoFuncionario();
		return dao.search(campo, operador, valor);		
	}	
	
}
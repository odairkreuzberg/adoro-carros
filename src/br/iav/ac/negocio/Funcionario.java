package br.iav.ac.negocio;

public class Funcionario extends Pessoa {

	private Cargo cargo;
	private float salario;
	
	public Cargo getCargo() {
		return cargo;
	}
	
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	public float getSalario() {
		return salario;
	}
	
	public void setSalario(float salario) {
		this.salario = salario;
	}

}
 

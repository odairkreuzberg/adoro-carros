package br.iav.ac.dao;

import br.iav.ac.database.DB;
import br.iav.ac.negocio.Cargo;
import br.iav.ac.negocio.Cidade;
import br.iav.ac.negocio.Endereco;
import br.iav.ac.negocio.Funcionario;
import br.iav.ac.negocio.ListaObjeto;

public class DaoFuncionario implements DaoInterface {

	private DB db = new DB("jdbc:postgresql://localhost:5432/postgres","root","");
	private Funcionario funcionario;
	
	//Nome da tabela e nome do sufixo do código
	String tableName = "funcionario";
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public void delete() {
		if (db.connect()) {
			db.update("delete from " + tableName + " where cod_" + tableName + " = " + funcionario.getCodigo());
			db.disconnect();
		}
	}
	
	public void edit() {
		if (db.connect()) {
			db.update("update " + tableName + " set nome = '" + funcionario.getNome() + "', " +
													"rua = '" + funcionario.getEndereco().getRua() + "', " +
													"numero = " + funcionario.getEndereco().getNumero() + ", " +
													"bairro = '" + funcionario.getEndereco().getBairro() + "', " +
													"cod_cidade = " + funcionario.getEndereco().getCidade().getCodigo() + ", " +
													"cep = '" + funcionario.getEndereco().getCep() + "', " +
													"complemento = '" + funcionario.getEndereco().getComplemento() + "', " +
													"telefone = '" + funcionario.getTelefone() + "', " +
													"cpf = '" + funcionario.getCpf() + "', " +
													"rg = '" + funcionario.getRg() + "', " +
													"data_nascimento = " + funcionario.getDataNascimento() + ", " +
													"cod_cargo = " + funcionario.getCargo().getCodigo() + ", " +
													"salario = " + funcionario.getSalario() + " " +
													"where cod_" + tableName + " = " + funcionario.getCodigo());
			db.disconnect();
		}
	}
	
	public void insert() {
		if (db.connect()) {
			db.update("insert into " + tableName + " (nome, rua, numero, bairro, cod_cidade, cep, complemento, telefone, cpf, rg, data_nascimento, cod_cargo, salario) values " +
													"('" + funcionario.getNome() + "', " +
													"'" + funcionario.getEndereco().getRua() + "', " +
													funcionario.getEndereco().getNumero() + ", " +
													"'" + funcionario.getEndereco().getBairro() + "', " +
													funcionario.getEndereco().getCidade().getCodigo() + ", " +
													"'" + funcionario.getEndereco().getCep() + "', " +
													"'" + funcionario.getEndereco().getComplemento() + "', " +
													"'" + funcionario.getTelefone() + "', " +
													"'" + funcionario.getCpf() + "', " +
													"'" + funcionario.getRg() + "', " +
													funcionario.getDataNascimento() + ", " +
													funcionario.getCargo().getCodigo() + ", " +
													funcionario.getSalario() + ")");
			db.disconnect();
		}
	}
	
	public ListaObjeto load() {
		ListaObjeto lista = new ListaObjeto();
		if (db.connect()) {
			db.select("select f.cod_" + tableName + ", f.nome, f.rua, f.numero, f.bairro, f.cod_cidade, c1.cod_cidade, c1.nome, c1.ddd, f.cep, f.complemento, f.telefone, f.cpf, f.rg, f.data_nascimento, f.cod_cargo, c2.cod_cargo, c2.nome, c2.descricao, f.salario from " + tableName + " f, cidade c1, cargo c2 where f.cod_cidade = c1.cod_cidade and f.cod_cargo = c2.cod_cargo");
			while (db.moveNext()) {
				Cidade cidade = new Cidade(db.getInt("c1.cod_cidade"), db.getString("c1.nome"), db.getInt("c1.ddd"));
				Endereco endereco = new Endereco(db.getString("f.rua"), db.getInt("f.numero"), db.getString("f.bairro"), cidade, db.getString("f.cep"), db.getString("f.complemento"));
				Cargo cargo = new Cargo(db.getInt("c2.cod_cargo"), db.getString("c2.nome"), db.getString("c2.descricao"));
				lista.insertWhitoutPersist(new Funcionario(db.getInt("f.cod_" + tableName), db.getString("f.nome"), endereco, db.getString("f.telefone"), db.getString("f.cpf"), db.getString("f.rg"), db.getDate("f.data_nascimento"), cargo, db.getFloat("f.salario")));
			}
			db.disconnect();
		}
		return lista;
	}

}
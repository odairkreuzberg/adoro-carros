package br.iav.ac.dao;

import br.iav.ac.database.DB;
import br.iav.ac.database.PostgreSQL;
import br.iav.ac.negocio.Cargo;
import br.iav.ac.negocio.Cidade;
import br.iav.ac.negocio.Endereco;
import br.iav.ac.negocio.Funcionario;
import br.iav.ac.negocio.ListaObjeto;

public class DaoFuncionario implements DaoInterface {

	private DB db = PostgreSQL.create();

	private Funcionario funcionario;
	
	//Nome da tabela e nome do sufixo do código
	private final static String tableName = "funcionario";
	
	private final static String SELECT = "select * from " + tableName;
	private final static String SELECT_COM_CIDADE = "select funcionario.* from " + tableName + ", cidade";
	private final static String SELECT_COM_CARGO = "select funcionario.* from " + tableName + ", cargo";

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
													"data_nascimento = '" + funcionario.getDataNascimento() + "', " +
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
													"'" + funcionario.getRg() + "', '" +
													funcionario.dataNascimentoToString() + "', " +
													funcionario.getCargo().getCodigo() + ", " +
													funcionario.getSalario() + ")");
			db.disconnect();
		}
	}
	
	public ListaObjeto load(String sql) {
		ListaObjeto lista = new ListaObjeto();
		DaoCidade daoCidade = new DaoCidade();
		DaoCargo daoCargo = new DaoCargo();
		if (db.connect()) {
			db.select(sql);
			while (db.moveNext()) {
				/*Cidade cidade = daoCidade.searchWithCodigo(db.getInt("cod_cidade"));
				Endereco endereco = new Endereco(db.getString("rua"), db.getInt("numero"), db.getString("bairro"), cidade, db.getString("cep"), db.getString("complemento"));
				Cargo cargo = daoCargo.searchWithCodigo(db.getInt("cod_cargo"));
				*/
				//lista.insertWhitoutPersist(new Funcionario(db.getInt("cod_" + tableName), db.getString("nome"), new Endereco(), new Cidade(), db.getString("cep"), db.getString("complemento"), db.getString("telefone"), db.getString("cpf"), db.getString("rg"), db.getDate("data_nascimento"), cargo, db.getFloat("salario")));
			}
			db.disconnect();
		}
		return lista;
	}

	public ListaObjeto load() {
		return this.load(SELECT);
	}
	
	public ListaObjeto search(String campo, String operador, String valor) {
		String campoSQL = campo;
		String operadorSQL = null;
		String sql = SELECT;
		String valorSQL = "'" + valor + "'";
		if (campo.equals("Código")) {
			campoSQL = "CAST(cod_"+tableName+" as VARCHAR)";
		} else if (campo.equals("Nome")) {
			campoSQL = "nome";
		} else if (campo.equals("Telefone")) {
			campoSQL = "telefone";
		} else if (campo.equals("CPF")) {
			campoSQL = "CPF";
		} else if (campo.equals("RG")) {
			campoSQL = "RG";
		} else if (campo.equals("Data de Nascimento")) {
			campoSQL = "data_nascimento";
		} else if (campo.equals("Cidade")) {
			sql  = SELECT_COM_CIDADE;
			campoSQL = "cidade.cod_cidade = funcionario.cod_cidade and cidade.nome ";
		} else if (campo.equals("Cargo")) {
			sql  = SELECT_COM_CARGO;
			campoSQL = "cargo.cod_cargo = funcionario.cod_cargo and cargo.nome ";
		}
		if (operador.equals("Igual")) {
			operadorSQL = "=";
		} else if (operador.equals("Diferente")) {
			operadorSQL = "!=";
		} else if (operador.equals("Maior")) {
			operadorSQL = ">";
		} else if (operador.equals("Menor")) {
			operadorSQL = "<";
		} else if (operador.equals("Contem")) {
			operadorSQL = "like";
			valorSQL = "('%" + valor + "%')";
		}

		sql += " where " + campoSQL + " " + operadorSQL + valorSQL;
		return this.load(sql);
	}	

}
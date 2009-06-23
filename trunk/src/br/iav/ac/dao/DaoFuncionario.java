package br.iav.ac.dao;

import br.iav.ac.database.DB;
import br.iav.ac.database.PostgreSQL;
import br.iav.ac.negocio.Cargo;
import br.iav.ac.negocio.Cidade;
import br.iav.ac.negocio.Cliente;
import br.iav.ac.negocio.Endereco;
import br.iav.ac.negocio.Funcionario;
import br.iav.ac.negocio.ListaObjeto;

public class DaoFuncionario implements DaoInterface {

	private DB db = PostgreSQL.create();

	private Funcionario funcionario;
	
	//Nome da tabela e nome do sufixo do código
	private final static String tableName = "funcionario";
	
	private final static String SELECT = "select funcionario.*," +
		" cidade.*, cargo.*, cidade.nome" +
		" as ci_nome, cargo.nome as ca_nome from cidade inner join funcionario" +
		" on (funcionario.cod_cidade = cidade.cod_cidade)inner join cargo on" +
		" cargo.cod_cargo = funcionario.cod_cargo ";
	
	private  final static String SELECT_TEM_ATIVIDADE ="select " +
		"funcionario.cod_funcionario from atividade, funcionario where " +
		"atividade.cod_funcionario = funcionario.cod_funcionario and " +
		"funcionario.cod_funcionario =  ";
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public void delete() {

		String sql = SELECT_TEM_ATIVIDADE + funcionario.getCodigo();

		if (this.load(sql).getSize() > 0) {
			throw new RuntimeException();
		}
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
		if (db.connect()) {
			db.select(sql);
			while (db.moveNext()) {
				Cidade cidade = new Cidade();
				Funcionario funcionario = new Funcionario();
				Endereco endereco = new Endereco();
				Cargo cargo = new Cargo();
				
				cidade.setCodigo(db.getInt("cod_cidade"));
				cidade.setNome(db.getString("ci_nome"));
				
				cargo.setCodigo(db.getInt("cod_cargo"));
				cargo.setDescricao(db.getString("descricao"));
				cargo.setNome(db.getString("ca_nome"));
				
				endereco.setCidade(cidade);				
				endereco.setBairro(db.getString("bairro"));
				endereco.setCep(db.getString("cep"));
				endereco.setComplemento(db.getString("complemento"));
				endereco.setNumero(db.getInt("numero"));
				endereco.setRua(db.getString("rua"));
				
				funcionario.setEndereco(endereco);
				funcionario.setCargo(cargo);
				funcionario.setCodigo(db.getInt("cod_funcionario"));
				funcionario.setCpf(db.getString("cpf"));
				funcionario.setDataNascimento(db.getDate("data_nascimento"));
				funcionario.setNome(db.getString("nome"));
				funcionario.setRg(db.getString("rg"));
				funcionario.setSalario(db.getFloat("salario"));
				funcionario.setTelefone(db.getString("telefone"));
				
				lista.insertWhitoutPersist(funcionario);
			}
			db.disconnect();
		}
		return lista;
	}

	public ListaObjeto load() {
		return this.load(SELECT);
	}
	
	public ListaObjeto search(String campo, String operador, String valor) {
		
		String sql = SELECT;
		
		String campoSQL = null;
		String operadorSQL = null;
		String valorSQL = "'" + valor + "'";
		if (campo.equals("Código")) {
			valorSQL = valor;
			campoSQL = "where cod_funcionario ";
			if (operador.equals("Contem")) {
				operador = "Igual";
				valorSQL = "-1";
			}
		} else if (campo.equals("Nome")) {
			campoSQL = "where funcionario.nome ";
		} else if (campo.equals("Telefone")) {
			campoSQL = "where funcionario.telefone ";
		} else if (campo.equals("CPF")) {
			campoSQL = "where funcionario.cpf ";
		} else if (campo.equals("RG")) {
			campoSQL = "where funcionario.rg ";
		} else if (campo.equals("Data de Nascimento")) {
			campoSQL = "where funcionario.data_nascimento ";
		} else if (campo.equals("Cidade")) {
			campoSQL = "where cidade.nome ";
		} else if (campo.equals("Cargo")) {
			campoSQL = "where cargo.nome ";
		}
		if (operador.equals("Igual")) {
			operadorSQL = "=";
		} else if (operador.equals("Diferente")) {
			operadorSQL = "!=";
		} else if (operador.equals("Maior")) {
			operadorSQL = ">";
		} else if (operador.equals("Menor")) {
			operadorSQL = "<";
		} else if (operador.equals("Contém")) {
			operadorSQL = "like";
			valorSQL = "('%" + valor + "%')";
		}

		sql += campoSQL + " " + operadorSQL + valorSQL;
		return this.load(sql);
	}	

}
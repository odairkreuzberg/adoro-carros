package br.iav.ac.dao;

import br.iav.ac.database.DB;
import br.iav.ac.database.PostgreSQL;
import br.iav.ac.negocio.Cidade;
import br.iav.ac.negocio.Cliente;
import br.iav.ac.negocio.Endereco;
import br.iav.ac.negocio.ListaObjeto;

public class DaoCliente implements DaoInterface {


	private DB db = PostgreSQL.create();
	private Cliente cliente;
	
	//Nome da tabela e nome do sufixo do código
	private final static String tableName = "cliente";
	
	private final static String SELECT = "select * from " + tableName;
	private final static String SELECT_COM_CIDADE = "select cliente.* from " + tableName + ", cidade";
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void delete() {
		if (db.connect()) {
			db.update("delete from " + tableName + " where cod_" + tableName + " = " + cliente.getCodigo());
			db.disconnect();
		}
	}
	
	public void edit() {
		if (db.connect()) {
			db.update("update " + tableName + " set nome = '" + cliente.getNome() + "', " +
													"rua = '" + cliente.getEndereco().getRua() + "', " +
													"numero = " + cliente.getEndereco().getNumero() + ", " +
													"bairro = '" + cliente.getEndereco().getBairro() + "', " +
													"cod_cidade = " + cliente.getEndereco().getCidade().getCodigo() + ", " +
													"cep = '" + cliente.getEndereco().getCep() + "', " +
													"complemento = '" + cliente.getEndereco().getComplemento() + "', " +
													"telefone = '" + cliente.getTelefone() + "', " +
													"cpf = '" + cliente.getCpf() + "', " +
													"rg = '" + cliente.getRg() + "', " +
													"data_nascimento = '" + cliente.dataNascimentoToString() + "', " +
													"profissao = '" + cliente.getProfissao() + "' " +
													"where cod_" + tableName + " = " + cliente.getCodigo());
			db.disconnect();
		}
	}
	
	public void insert() {
		if (db.connect()) {
			db.update("insert into " + tableName + " (nome, rua, numero, bairro, cod_cidade, cep, complemento, telefone, cpf, rg, data_nascimento, profissao) values " +
													"('" + cliente.getNome() + "', " +
													"'" + cliente.getEndereco().getRua() + "', " +
													cliente.getEndereco().getNumero() + ", " +
													"'" + cliente.getEndereco().getBairro() + "', " +
													cliente.getEndereco().getCidade().getCodigo() + ", " +
													"'" + cliente.getEndereco().getCep() + "', " +
													"'" + cliente.getEndereco().getComplemento() + "', " +
													"'" + cliente.getTelefone() + "', " +
													"'" + cliente.getCpf() + "', " +
													"'" + cliente.getRg() + "', " +
													"'" + cliente.dataNascimentoToString() + "', " +
													"'" + cliente.getProfissao() + "')");
			db.disconnect();
		}
	}
	
	public ListaObjeto load(String sql) {
		DaoCidade daoCidade = new DaoCidade();
		ListaObjeto lista = new ListaObjeto();
		if (db.connect()) {
			db.select(sql);
			while (db.moveNext()) {
				Cidade cidade = daoCidade.searchWithCodigo(db.getInt("cod_cidade"));
				Endereco endereco = new Endereco(db.getString("rua"), db.getInt("numero"), db.getString("bairro"), cidade, db.getString("cep"), db.getString("complemento"));
				lista.insertWhitoutPersist(new Cliente(db.getInt("cod_" + tableName), db.getString("nome"), endereco, db.getString("telefone"), db.getString("cpf"), db.getString("rg"), db.getDate("data_nascimento"), db.getString("profissao"), null));
				//Cidade cidade = new Cidade(db.getInt("c1.cod_cidade"), db.getString("c1.nome"), db.getInt("c1.ddd"));
				//Endereco endereco = new Endereco(db.getString("c.rua"), db.getInt("c.numero"), db.getString("c.bairro"), cidade, db.getString("c.cep"), db.getString("c.complemento"));
				//lista.insertWhitoutPersist(new Cliente(db.getInt("c.cod_" + tableName), db.getString("c.nome"), endereco, db.getString("c.telefone"), db.getString("c.cpf"), db.getString("c.rg"), db.getString("c.data_nascimento"), db.getString("c.profissao"), null));
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
		String valorSQL = "'" + valor + "'";
		String sql = SELECT;
		if (campo.equals("Código")) {
			campoSQL = "cast (cod_"+tableName+" as varchar)";
		} else if (campo.equals("Nome")) {
			campoSQL = "nome";
		} else if (campo.equals("Telefone")) {
			campoSQL = "telefone";
		} else if (campo.equals("CPF")) {
			campoSQL = "cpf";
		} else if (campo.equals("RG")) {
			campoSQL = "rg";
		} else if (campo.equals("Profissão")) {
			campoSQL = "profissao";
		} else if (campo.equals("Data de Nascimento")) {
			campoSQL = "data_nascimento";
		} else if (campo.equals("Rua")) {
			campoSQL = "rua";
		} else if (campo.equals("Número")) {
			campoSQL = "cast (numero as varchar)";
		} else if (campo.equals("Bairro")) {
			campoSQL = "bairro";
		} else if (campo.equals("CEP")) {
			campoSQL = "cep";
		} else if (campo.equals("Complemento")) {
			campoSQL = "complemento";
		} else if (campo.equals("Cidade")) {
			sql = SELECT_COM_CIDADE;
			campoSQL = "cidade.cod_cidade = cliente.cod_cidade and cidade.nome ";
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
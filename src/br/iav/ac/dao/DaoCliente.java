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
	
	private final static String SELECT = "select c.cod_" + tableName + ", c.nome, c.rua, c.numero, c.bairro, c.cod_cidade, c1.cod_cidade, c1.nome, c1.ddd, c.cep, c.complemento, c.telefone, c.cpf, c.rg, c.data_nascimento, c.profissao from " + tableName + " c, cidade c1 where c.cod_cidade = c1.cod_cidade";
	
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
													"data_nascimento = " + cliente.getDataNascimento() + ", " +
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
													cliente.getDataNascimento() + ", " +
													"'" + cliente.getProfissao() + "')");
			db.disconnect();
		}
	}
	
	public ListaObjeto load(String sql) {
		ListaObjeto lista = new ListaObjeto();
		if (db.connect()) {
			db.select(sql);
			while (db.moveNext()) {
				Cidade cidade = new Cidade(db.getInt("c1.cod_cidade"), db.getString("c1.nome"), db.getInt("c1.ddd"));
				Endereco endereco = new Endereco(db.getString("c.rua"), db.getInt("c.numero"), db.getString("c.bairro"), cidade, db.getString("c.cep"), db.getString("c.complemento"));
				lista.insertWhitoutPersist(new Cliente(db.getInt("c.cod_" + tableName), db.getString("c.nome"), endereco, db.getString("c.telefone"), db.getString("c.cpf"), db.getString("c.rg"), db.getDate("c.data_nascimento"), db.getString("c.profissao"), null));
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
		if (campo.equals("Código")) {
			campoSQL = "cod_" + tableName;
		} else {
			campoSQL = "nome";
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
		String sql = SELECT;
		sql += " where " + campoSQL + " " + operadorSQL + valorSQL;
		return this.load(sql);
	}

}
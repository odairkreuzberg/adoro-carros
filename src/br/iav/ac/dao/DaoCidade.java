package br.iav.ac.dao;

import br.iav.ac.database.DB;
import br.iav.ac.database.PostgreSQL;
import br.iav.ac.negocio.Cidade;
import br.iav.ac.negocio.ListaObjeto;

public class DaoCidade implements DaoInterface {

	private DB db = PostgreSQL.create();
	private Cidade cidade;
	
	//Nome da tabela e nome do sufixo do c�digo
	private final static String tableName = "cidade";
	
	private final static String SELECT = "select cod_" + tableName + ", nome, ddd from " + tableName;
	
	private final static String SELECT_TEM_FUNCIONARIO = "select cidade.cod_cidade from cidade, funcionario where cidade.cod_cidade = funcionario.cod_cidade and cidade.cod_cidade = ";
	
	private final static String SELECT_TEM_CLIENTE = "select cidade.cod_cidade from cidade, cliente where cidade.cod_cidade = cliente.cod_cidade and cidade.cod_cidade = ";
	
	private final static String SELECT_TEM_FORNECEDOR = "select cidade.cod_cidade from cidade, fornecedor where cidade.cod_cidade = fornecedor.cod_cidade and cidade.cod_cidade = ";
	
	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public void delete() {

		String sql = SELECT_TEM_FUNCIONARIO + cidade.getCodigo();

		if (this.load(sql).getSize() > 0) {
			throw new RuntimeException();
		}

		sql = SELECT_TEM_CLIENTE + cidade.getCodigo();

		if (this.load(sql).getSize() > 0) {
			throw new RuntimeException();
		}

		sql = SELECT_TEM_FORNECEDOR + cidade.getCodigo();

		if (this.load(sql).getSize() > 0) {
			throw new RuntimeException();
		}
		if (db.connect()) {
			db.update("delete from " + tableName + " where cod_" + tableName + " = " + cidade.getCodigo());
			db.disconnect();
		}
	}
	
	public void edit() {
		if (db.connect()) {
			db.update("update " + tableName + " set nome = '" + cidade.getNome() + "', ddd = " + cidade.getDdd() + " where cod_" + tableName + " = " + cidade.getCodigo());
			db.disconnect();
		}
	}
	
	public void insert() {
		if (db.connect()) {
			db.update("insert into " + tableName + " (nome, ddd) values ('" + cidade.getNome() + "', " + cidade.getDdd() + ")");
			db.disconnect();
		}
	}
	
	private ListaObjeto load(String sql) {
		ListaObjeto lista = new ListaObjeto();
		if (db.connect()) {
			db.select(sql);
			while (db.moveNext()) {
				lista.insertWhitoutPersist(new Cidade(db.getInt("cod_" + tableName), db.getString("nome"), db.getInt("ddd")));
			}
			db.disconnect();
		}
		return lista;
	}
	
	public ListaObjeto load() {
		return this.load(SELECT);
	}
	
	public Cidade searchWithCodigo(int codigo){
		ListaObjeto listaObjeto = this.search("C�digo", "Igual", String.valueOf(codigo));
		if (listaObjeto.getSize() == 1 ) {
			return (Cidade) listaObjeto.getObjeto(0);
		}
		return null;
	}
	
	public ListaObjeto search(String campo, String operador, String valor) {
		String sql = SELECT;
		String campoSQL = null;
		String operadorSQL = null;
		String valorSQL = "'" + valor + "'";
		if (campo.equals("C�digo")) {
			valorSQL = valor;
			campoSQL = " where cod_cidade";
			if (operador.equals("Cont�m")) {
				operador = "Igual";
				valorSQL = "-1";
			}
		} else if (campo.equals("Nome")) {
			campoSQL = " where cidade.nome";
		} else if (campo.equals("DDD")) {
			campoSQL = " where cidade.ddd";
			valorSQL = valor;
			if (operador.equals("Cont�m")) {
				operador = "Igual";
				valorSQL = "-1";
			}
		}
		if (operador.equals("Igual")) {
			operadorSQL = "=";
		} else if (operador.equals("Diferente")) {
			operadorSQL = "!=";
		} else if (operador.equals("Maior")) {
			operadorSQL = ">";
		} else if (operador.equals("Menor")) {
			operadorSQL = "<";
		} else if (operador.equals("Cont�m")) {
			operadorSQL = "like";
			valorSQL = "('%" + valor + "%')";
		}
		sql += campoSQL + " " + operadorSQL + valorSQL;
		return this.load(sql);
	}

}
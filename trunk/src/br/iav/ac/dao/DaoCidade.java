package br.iav.ac.dao;

import br.iav.ac.database.DB;
import br.iav.ac.database.PostgreSQL;
import br.iav.ac.negocio.Cidade;
import br.iav.ac.negocio.ListaObjeto;

public class DaoCidade {

	private DB db = PostgreSQL.create();
	private Cidade cidade;
	
	//Nome da tabela e nome do sufixo do código
	private final static String tableName = "cidade";
	
	private final static String SELECT = "select cod_" + tableName + ", nome, ddd from " + tableName;
	
	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public void delete() {
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
	
	public ListaObjeto load(String sql) {
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
	
	public ListaObjeto search(String campo, String operador, String valor) {
		String campoSQL = campo;
		String operadorSQL = null;
		String valorSQL = "'" + valor + "'";
		if (campo.equals("Código")) {
			campoSQL = "cod_" + tableName;
		} else if (campo.equals("Nome")) {
			campoSQL = "nome";
		} else {
			campoSQL = "ddd";
		}
		if (operador.equals("Igual")) {
			operadorSQL = "= ";
		} else if (operador.equals("Diferente")) {
			operadorSQL = "!= ";
		} else if (operador.equals("Maior")) {
			operadorSQL = "> ";
		} else if (operador.equals("Menor")) {
			operadorSQL = "< ";
		} else if (operador.equals("Contem")) {
			operadorSQL = "like ";
			valorSQL = "('%" + valor + "%')";
		}
		String sql = SELECT;
		sql += " where " + campoSQL + " " + operadorSQL + valorSQL;
		return this.load(sql);
	}

}
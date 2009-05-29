package br.iav.ac.dao;

import br.iav.ac.database.DB;
import br.iav.ac.database.PostgreSQL;
import br.iav.ac.negocio.Status;
import br.iav.ac.negocio.ListaObjeto;

public class DaoStatus implements DaoInterface {

	private DB db = PostgreSQL.create();
	private Status status;
	
	//Nome da tabela e nome do sufixo do código
	private final static String tableName = "status";
	
	private final static String SELECT = "select cod_" + tableName + ", nome from " + tableName;
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void delete() {
		if (db.connect()) {
			db.update("delete from " + tableName + " where cod_" + tableName + " = " + status.getCodigo());
			db.disconnect();
		}
	}
	
	public void edit() {
		if (db.connect()) {
			db.update("update " + tableName + " set nome = '" + status.getNome() + "' where cod_" + tableName + " = " + status.getCodigo());
			db.disconnect();
		}
	}
	
	public void insert() {
		if (db.connect()) {
			db.update("insert into " + tableName + " (nome) values ('" + status.getNome() + "')");
			db.disconnect();
		}
	}
	
	private ListaObjeto load(String sql) {
		ListaObjeto lista = new ListaObjeto();
		if (db.connect()) {
			db.select(sql);
			while (db.moveNext()) {
				lista.insertWhitoutPersist(new Status(db.getInt("cod_" + tableName), db.getString("nome")));
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
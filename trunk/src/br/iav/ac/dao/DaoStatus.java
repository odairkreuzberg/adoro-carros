package br.iav.ac.dao;

import br.iav.ac.database.DB;
import br.iav.ac.database.PostgreSQL;
import br.iav.ac.negocio.Status;
import br.iav.ac.negocio.ListaObjeto;

public class DaoStatus implements DaoInterface {


	private DB db = PostgreSQL.novaInstancia();

	private Status status;
	
	//Nome da tabela e nome do sufixo do código
	String tableName = "status";
	
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
	
	public ListaObjeto load() {
		ListaObjeto lista = new ListaObjeto();
		if (db.connect()) {
			db.select("select cod_" + tableName + ", nome from " + tableName);
			while (db.moveNext()) {
				lista.insertWhitoutPersist(new Status(db.getInt("cod_" + tableName), db.getString("nome")));
			}
			db.disconnect();
		}
		return lista;
	}

}
package br.iav.ac.dao;

import br.iav.ac.database.DB;
import br.iav.ac.database.PostgreSQL;
import br.iav.ac.negocio.Marca;
import br.iav.ac.negocio.ListaObjeto;

public class DaoMarca implements DaoInterface {

	private DB db = new DB("jdbc:" + PostgreSQL.DATABASE + "://" + PostgreSQL.LOCAL + ":" + PostgreSQL.PORT + "/" + PostgreSQL.SCHEMA, PostgreSQL.USER, PostgreSQL.PASS);
	private Marca marca;
	
	//Nome da tabela e nome do sufixo do código
	String tableName = "marca";
	
	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public void delete() {
		if (db.connect()) {
			db.update("delete from " + tableName + " where cod_" + tableName + " = " + marca.getCodigo());
			db.disconnect();
		}
	}
	
	public void edit() {
		if (db.connect()) {
			db.update("update " + tableName + " set nome = '" + marca.getNome() + "' where cod_" + tableName + " = " + marca.getCodigo());
			db.disconnect();
		}
	}
	
	public void insert() {
		if (db.connect()) {
			db.update("insert into " + tableName + " (nome) values ('" + marca.getNome() + "')");
			db.disconnect();
		}
	}
	
	public ListaObjeto load() {
		ListaObjeto lista = new ListaObjeto();
		if (db.connect()) {
			db.select("select cod_" + tableName + ", nome from " + tableName);
			while (db.moveNext()) {
				lista.insertWhitoutPersist(new Marca(db.getInt("cod_" + tableName), db.getString("nome")));
			}
			db.disconnect();
		}
		return lista;
	}

}
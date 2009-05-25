package br.iav.ac.dao;

import br.iav.ac.database.DB;
import br.iav.ac.database.PostgreSQL;
import br.iav.ac.negocio.Cor;
import br.iav.ac.negocio.ListaObjeto;

public class DaoCor implements DaoInterface {

	private DB db = new DB("jdbc:" + PostgreSQL.DATABASE + "://" + PostgreSQL.LOCAL + ":" + PostgreSQL.PORT + "/" + PostgreSQL.SCHEMA, PostgreSQL.USER, PostgreSQL.PASS);
	private Cor cor;
	
	//Nome da tabela e nome do sufixo do código
	String tableName = "cor";
	
	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

	public void delete() {
		if (db.connect()) {
			db.update("delete from " + tableName + " where cod_" + tableName + " = " + cor.getCodigo());
			db.disconnect();
		}
	}
	
	public void edit() {
		if (db.connect()) {
			db.update("update " + tableName + " set nome = '" + cor.getNome() + "' where cod_" + tableName + " = " + cor.getCodigo());
			db.disconnect();
		}
	}
	
	public void insert() {
		if (db.connect()) {
			db.update("insert into " + tableName + " (nome) values ('" + cor.getNome() + "')");
			db.disconnect();
		}
	}
	
	public ListaObjeto load() {
		ListaObjeto lista = new ListaObjeto();
		if (db.connect()) {
			db.select("select cod_" + tableName + ", nome from " + tableName);
			while (db.moveNext()) {
				lista.insertWhitoutPersist(new Cor(db.getInt("cod_" + tableName), db.getString("nome")));
			}
			db.disconnect();
		}
		return lista;
	}

}
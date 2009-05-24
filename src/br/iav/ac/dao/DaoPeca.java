package br.iav.ac.dao;

import br.iav.ac.database.DB;
import br.iav.ac.negocio.Peca;
import br.iav.ac.negocio.ListaObjeto;

public class DaoPeca implements DaoInterface {

	private DB db = new DB("jdbc:postgresql://localhost:5432/postgres","root","");
	private Peca peca;
	
	//Nome da tabela e nome do sufixo do código
	String tableName = "peca";
	
	public Peca getPeca() {
		return peca;
	}

	public void setPeca(Peca peca) {
		this.peca = peca;
	}

	public void delete() {
		if (db.connect()) {
			db.update("delete from " + tableName + " where cod_" + tableName + " = " + peca.getCodigo());
			db.disconnect();
		}
	}
	
	public void edit() {
		if (db.connect()) {
			db.update("update " + tableName + " set nome ='" + peca.getNome() + "' where cod_" + tableName + " = " + peca.getCodigo());
			db.disconnect();
		}
	}
	
	public void insert() {
		if (db.connect()) {
			db.update("insert into " + tableName + " (nome) values ('" + peca.getNome() + "')");
			db.disconnect();
		}
	}
	
	public ListaObjeto load() {
		ListaObjeto lista = new ListaObjeto();
		if (db.connect()) {
			db.select("select cod_" + tableName + ", nome from " + tableName);
			while (db.moveNext()) {
				lista.insertWhitoutPersist(new Peca(db.getInt("cod_" + tableName), db.getString("nome")));
			}
			db.disconnect();
		}
		return lista;
	}

}
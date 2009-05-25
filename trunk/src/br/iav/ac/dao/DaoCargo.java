package br.iav.ac.dao;

import br.iav.ac.database.DB;
import br.iav.ac.database.PostgreSQL;
import br.iav.ac.negocio.Cargo;
import br.iav.ac.negocio.ListaObjeto;

public class DaoCargo  implements DaoInterface {

	private DB db = PostgreSQL.novaInstancia();
	private Cargo cargo;
	
	//Nome da tabela e nome do sufixo do código
	String tableName = "cargo";
	
	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public void delete() {
		if (db.connect()) {
			db.update("delete from " + tableName + " where cod_" + tableName + " = " + cargo.getCodigo());
			db.disconnect();
		}
	}
	
	public void edit() {
		if (db.connect()) {
			db.update("update " + tableName + " set nome = '" + cargo.getNome() + "', descricao = '" + cargo.getDescricao() + "' where cod_" + tableName + " = " + cargo.getCodigo());
			db.disconnect();
		}
	}
	
	public void insert() {
		if (db.connect()) {
			db.update("insert into " + tableName + " (nome, descricao) values ('" + cargo.getNome() + "', '" + cargo.getDescricao() + "')");
			db.disconnect();
		}
	}
	
	public ListaObjeto load() {
		ListaObjeto lista = new ListaObjeto();
		if (db.connect()) {
			db.select("select cod_" + tableName + ", nome, descricao from " + tableName);
			while (db.moveNext()) {
				lista.insertWhitoutPersist(new Cargo(db.getInt("cod_" + tableName), db.getString("nome"), db.getString("descricao")));
			}
			db.disconnect();
		}
		return lista;
	}

}
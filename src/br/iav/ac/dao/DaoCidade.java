package br.iav.ac.dao;

import br.iav.ac.database.DB;
import br.iav.ac.database.PostgreSQL;
import br.iav.ac.negocio.Cidade;
import br.iav.ac.negocio.ListaObjeto;

public class DaoCidade {

	private DB db = PostgreSQL.novaInstancia();
	private Cidade cidade;
	
	//Nome da tabela e nome do sufixo do código
	String tableName = "cidade";
	
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
	
	public ListaObjeto load() {
		ListaObjeto lista = new ListaObjeto();
		if (db.connect()) {
			db.select("select cod_" + tableName + ", nome, ddd from " + tableName);
			while (db.moveNext()) {
				lista.insertWhitoutPersist(new Cidade(db.getInt("cod_" + tableName), db.getString("nome"), db.getInt("ddd")));
			}
			db.disconnect();
		}
		return lista;
	}

}
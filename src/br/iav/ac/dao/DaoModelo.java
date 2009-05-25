package br.iav.ac.dao;

import br.iav.ac.database.DB;
import br.iav.ac.database.PostgreSQL;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.negocio.Marca;
import br.iav.ac.negocio.Modelo;

public class DaoModelo implements DaoInterface {


	private DB db = PostgreSQL.novaInstancia();
	private Modelo modelo;
	
	//Nome da tabela e nome do sufixo do código
	String tableName = "modelo";
	
	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public void delete() {
		if (db.connect()) {
			db.update("delete from " + tableName + " where cod_" + tableName + " = " + modelo.getCodigo());
			db.disconnect();
		}
	}
	
	public void edit() {
		if (db.connect()) {
			db.update("update " + tableName + " set nome = '" + modelo.getNome() + "', cod_marca = " + modelo.getMarca().getCodigo() + " where cod_" + tableName + " = " + modelo.getCodigo());
			db.disconnect();
		}
	}
	
	public void insert() {
		if (db.connect()) {
			db.update("insert into " + tableName + " (nome, cod_marca) values ('" + modelo.getNome() + "', " + modelo.getMarca().getCodigo() + ")");
			db.disconnect();
		}
	}
	
	public ListaObjeto load() {
		ListaObjeto lista = new ListaObjeto();
		if (db.connect()) {
			db.select("select mo.cod_" + tableName + ", mo.nome, mo.cod_marca, ma.cod_marca, ma.nome from " + tableName + " mo, marca ma where mo.cod_marca = ma.cod_marca");
			while (db.moveNext()) {
				Marca marca = new Marca(db.getInt("ma.cod_marca"), db.getString("ma.nome"));
				lista.insertWhitoutPersist(new Modelo(db.getInt("mo.cod_" + tableName), db.getString("mo.nome"), marca));
			}
			db.disconnect();
		}
		return lista;
	}

}
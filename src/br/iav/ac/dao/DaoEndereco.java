package br.iav.ac.dao;

import br.iav.ac.database.DB;
import br.iav.ac.negocio.Cidade;
import br.iav.ac.negocio.Endereco;
import br.iav.ac.negocio.ListaObjeto;

public class DaoEndereco implements DaoInterface {

	private DB db = new DB("jdbc:postgresql://localhost:5432/postgres","root","");
	private Endereco endereco;
	
	//Nome da tabela e nome do sufixo do código
	String tableName = "endereco";
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void delete() {
		if (db.connect()) {
			db.update("delete from " + tableName + " where cod_" + tableName + " = " + endereco.getCodigo());
			db.disconnect();
		}
	}
	
	public void edit() {
		if (db.connect()) {
			db.update("update " + tableName + " set rua ='" + endereco.getRua() + "', numero ='" + endereco.getNumero() + "', bairro ='" + endereco.getBairro() + "', cod_cidade ='" + endereco.getCidade().getCodigo() + "', cep ='" + endereco.getCep() + "', complemento ='" + endereco.getComplemento() + "' where cod_" + tableName + " = " + endereco.getCodigo());
			db.disconnect();
		}
	}
	
	public void insert() {
		if (db.connect()) {
			db.update("insert into " + tableName + " (rua, numero, bairro, cod_cidade, cep, complemento) values ('" + endereco.getRua() + "', '" + endereco.getNumero() + "', '" + endereco.getBairro() + "', '" + endereco.getCidade().getCodigo() + "', '" + endereco.getCep() + "', '" + endereco.getComplemento() + "')");
			db.disconnect();
		}
	}
	
	public ListaObjeto load() {
		ListaObjeto lista = new ListaObjeto();
		if (db.connect()) {
			db.select("select e.cod_" + tableName + ", e.rua, e.numero, e.bairro, e.cod_cidade, e.cep, e.complemento, c.cod_cidade, c.nome, c.ddd from " + tableName + " e, cidade c where c.cod_cidade=e.cod_cidade");
			while (db.moveNext()) {
				Cidade cidade = new Cidade(db.getInt("c.cod_cidade"), db.getString("c.nome"), db.getInt("c.ddd"));
				lista.insertWhitoutPersist(new Endereco(db.getInt("e.cod_" + tableName), db.getString("e.rua"), db.getInt("e.numero"), db.getString("e.bairro"), cidade, db.getString("e.cep"), db.getString("e.complemento")));
			}
			db.disconnect();
		}
		return lista;
	}

}
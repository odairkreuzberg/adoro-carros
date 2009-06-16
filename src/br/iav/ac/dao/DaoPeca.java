package br.iav.ac.dao;

import br.iav.ac.database.DB;
import br.iav.ac.database.PostgreSQL;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.negocio.Peca;

public class DaoPeca implements DaoInterface {


	private DB db = PostgreSQL.create();
	private Peca peca;
	
	//Nome da tabela e nome do sufixo do c�digo
	private final static String tableName = "peca";
	
	private final static String SELECT = "select * from " + tableName;
	private final static String SELECT_TEM_FORNECEDOR_PECA = "select " +
		"peca.cod_peca from fornecedor_peca, peca where " +
		"fornecedor_peca.cod_peca = peca.cod_peca and peca.cod_peca =  ";
	private final static String SELECT_TEM_ATIVIDADE_PECA = "select " +
		"peca.cod_peca from atividade_peca, peca where " +
		"atividade_peca.cod_peca = peca.cod_peca and peca.cod_peca =  ";
	
	public Peca getPeca() {
		return peca;
	}

	public void setPeca(Peca peca) {
		this.peca = peca;
	}

	public void delete() {

		String sql = SELECT_TEM_FORNECEDOR_PECA + peca.getCodigo();

		if (this.load(sql).getSize() > 0) {
			throw new RuntimeException();
		}

		sql = SELECT_TEM_ATIVIDADE_PECA + peca.getCodigo();

		if (this.load(sql).getSize() > 0) {
			throw new RuntimeException();
		}
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
	
	public ListaObjeto load(String sql) {
		ListaObjeto lista = new ListaObjeto();
		if (db.connect()) {
			db.select(sql);
			while (db.moveNext()) {
				lista.insertWhitoutPersist(new Peca(db.getInt("cod_"	+ tableName), db.getString("nome")));
			}
			db.disconnect();
		}
		return lista;
	}

	public ListaObjeto load() {
		return this.load(SELECT);
	}

	public Peca searchWithCodigo(int codigo) {
		ListaObjeto listaObjeto = this.search("C�digo", "Igual", String
				.valueOf(codigo));
		if (listaObjeto.getSize() == 1) {
			return (Peca) listaObjeto.getObjeto(0);
		}
		return null;
	}
	
	public ListaObjeto search(String campo, String operador, String valor) {
		String sql = SELECT;
		String campoSQL = campo;
		String operadorSQL = null;
		String valorSQL = "'" + valor + "'";
		String outroSQL = "";
		if (campo.equals("C�digo")) {
			campoSQL = "cod_peca";
			valorSQL = valor;
			if (operador.equals("Contem")) {
				operador = "Igual";
				valorSQL = "-1";
			}
		} else if (campo.equals("Nome")) {
			campoSQL = " nome";
		}
		if (operador.equals("Igual")) {
			operadorSQL = "=";
		} else if (operador.equals("Diferente")) {
			operadorSQL = "!=";
		} else if (operador.equals("Maior")) {
			operadorSQL = ">";
		} else if (operador.equals("Menor")) {
			operadorSQL = "<";
		} else if (operador.equals("Contem")) {
			operadorSQL = "like";
			valorSQL = " '%" + valor + "%'";
		}
		sql += " where " + outroSQL + campoSQL + " " + operadorSQL + valorSQL;
		return this.load(sql);
	}
	
}
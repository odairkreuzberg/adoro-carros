package br.iav.ac.dao;

import br.iav.ac.database.DB;
import br.iav.ac.database.PostgreSQL;
import br.iav.ac.negocio.Cargo;
import br.iav.ac.negocio.Cidade;
import br.iav.ac.negocio.Cor;
import br.iav.ac.negocio.ListaObjeto;

public class DaoCargo implements DaoInterface {

	private DB db = PostgreSQL.create();

	// Nome da tabela e nome do sufixo do código
	private final static String tableName = "cargo";

	private final static String SELECT = "select cod_" + tableName
			+ ", nome, descricao from " + tableName;

	private Cargo cargo;

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public void delete() {
		if (db.connect()) {
			db.update("delete from " + tableName + " where cod_" + tableName
					+ " = " + cargo.getCodigo());
			db.disconnect();
		}
	}

	public void edit() {
		if (db.connect()) {
			db.update("update " + tableName + " set nome = '" + cargo.getNome()
					+ "', descricao = '" + cargo.getDescricao()
					+ "' where cod_" + tableName + " = " + cargo.getCodigo());
			db.disconnect();
		}
	}

	public void insert() {
		if (db.connect()) {
			db.update("insert into " + tableName
					+ " (nome, descricao) values ('" + cargo.getNome() + "', '"
					+ cargo.getDescricao() + "')");
			db.disconnect();
		}
	}

	public ListaObjeto load(String sql) {
		ListaObjeto lista = new ListaObjeto();
		if (db.connect()) {
			db.select(sql);
			while (db.moveNext()) {
				lista.insertWhitoutPersist(new Cargo(db.getInt("cod_"
						+ tableName), db.getString("nome"), db
						.getString("descricao")));
			}
			db.disconnect();
		}
		return lista;
	}

	public ListaObjeto load() {
		return this.load(SELECT);
	}

	public Cargo searchWithCodigo(int codigo) {
		ListaObjeto listaObjeto = this.search("Código", "Igual", String
				.valueOf(codigo));
		if (listaObjeto.getSize() == 1) {
			return (Cargo) listaObjeto.getObjeto(0);
		}
		return null;
	}

	public ListaObjeto search(String campo, String operador, String valor) {
		String campoSQL = campo;
		String operadorSQL = null;
		String valorSQL;
		if (campo.equals("Código")) {
			campoSQL = "cod_cargo";
			valorSQL = valor;
			if (operador.equals("Contem")) {
				operador = "Igual";
				valorSQL = "-1";
			}
		} else if (campo.equals("Nome")) {
			valorSQL = "'" + valor + "'";
			campoSQL = "nome";
		} else {
			valorSQL = "'" + valor + "'";
			campoSQL = "descricao";
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
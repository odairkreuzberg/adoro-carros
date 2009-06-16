package br.iav.ac.dao;

import br.iav.ac.database.DB;
import br.iav.ac.database.PostgreSQL;
import br.iav.ac.negocio.Marca;
import br.iav.ac.negocio.ListaObjeto;

public class DaoMarca implements DaoInterface {

	private DB db = PostgreSQL.create();

	// Nome da tabela e nome do sufixo do código
	private final static String tableName = "marca";

	private final static String SELECT = "select * from " + tableName;
	
	private final static String SELECT_TEM_MODELO = "select marca.cod_marca from marca inner join modelo on (modelo.cod_marca = marca.cod_marca) where marca.cod_marca = ";

	private Marca marca;

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public void delete() {
		
		String sql = SELECT_TEM_MODELO + marca.getCodigo();

		if (this.load(sql).getSize() > 0) {

			throw new RuntimeException();
		}

		if (db.connect()) {
			db.update("delete from " + tableName + " where cod_" + tableName
					+ " = " + marca.getCodigo());
			db.disconnect();
		}
	}

	public void edit() {
		if (db.connect()) {
			db.update("update " + tableName + " set nome = '" + marca.getNome()
					+ "' where cod_" + tableName + " = " + marca.getCodigo());
			db.disconnect();
		}
	}

	public void insert() {
		if (db.connect()) {
			db.update("insert into " + tableName + " (nome) values ('"
					+ marca.getNome() + "')");
			db.disconnect();
		}
	}

	private ListaObjeto load(String sql) {
		ListaObjeto lista = new ListaObjeto();
		if (db.connect()) {
			db.select(sql);
			while (db.moveNext()) {
				lista.insertWhitoutPersist(new Marca(db.getInt("cod_"
						+ tableName), db.getString("nome")));
			}
			db.disconnect();
		}
		return lista;
	}

	public ListaObjeto load() {
		return this.load(SELECT);
	}

	public Marca searchWithCodigo(int codigo) {
		ListaObjeto listaObjeto = this.search("Código", "Igual", String
				.valueOf(codigo));
		if (listaObjeto.getSize() == 1) {
			return (Marca) listaObjeto.getObjeto(0);
		}
		return null;
	}

	public ListaObjeto search(String campo, String operador, String valor) {
		String campoSQL = campo;
		String operadorSQL = null;
		String valorSQL = "'" + valor + "'";
		if (campo.equals("Código")) {
			valorSQL = valor;
			campoSQL = "cod_marca";
			if (operador.equals("Contem")) {
				operador = "Igual";
				valorSQL = "-1";
			}
		} else {
			campoSQL = "nome";
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
		String sql = SELECT;
		sql += " where " + campoSQL + " " + operadorSQL + valorSQL;
		return this.load(sql);
	}

}
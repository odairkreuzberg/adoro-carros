package br.iav.ac.dao;

import br.iav.ac.database.DB;
import br.iav.ac.database.PostgreSQL;
import br.iav.ac.negocio.Cor;
import br.iav.ac.negocio.ListaObjeto;

public class DaoCor implements DaoInterface {

	private DB db = PostgreSQL.create();
	private Cor cor;

	//Nome da tabela e nome do sufixo do código
	private final static String tableName = "cor";

	private final static String SELECT = "select cod_" + tableName + ", nome from " + tableName;

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

	private ListaObjeto load(String sql) {
		ListaObjeto lista = new ListaObjeto();
		if (db.connect()) {
			db.select(sql);
			while (db.moveNext()) {
				lista.insertWhitoutPersist(new Cor(db.getInt("cod_"	+ tableName), db.getString("nome")));
			}
			db.disconnect();
		}
		return lista;
	}

	public ListaObjeto load() {
		return this.load(SELECT);
	}

	public Cor searchWithCodigo(int codigo){
		ListaObjeto listaObjeto = this.search("Código", "Igual", String.valueOf(codigo));
		if (listaObjeto.getSize() == 1 ) {
			return (Cor) listaObjeto.getObjeto(0);
		}
		return null;
	}
	
	public ListaObjeto search(String campo, String operador, String valor) {
		String sql = SELECT;
		String campoSQL = null;
		String operadorSQL = null;
		String valorSQL = "'" + valor + "'";
		if (campo.equals("Código")) {
			valorSQL = valor;
			campoSQL = "where cod_cor";
			if (operador.equals("Contem")) {
				operador = "Igual";
				valorSQL = "-1";
			}
		} else {
			campoSQL = "where cor.nome";
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
			valorSQL = "('%" + valor + "%')";
		}
		sql += campoSQL + " " + operadorSQL + valorSQL;
		return this.load(sql);
	}

}
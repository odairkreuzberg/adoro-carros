package br.iav.ac.dao;

import br.iav.ac.database.DB;
import br.iav.ac.database.PostgreSQL;
import br.iav.ac.negocio.Cor;
import br.iav.ac.negocio.ListaObjeto;

public class DaoCor implements DaoInterface {

	private DB db = PostgreSQL.novaInstancia();

	// Nome da tabela e nome do sufixo do c�digo
	private final static String tableName = "cor";

	private final static String SELECT = "select cod_" + tableName
			+ ", descricao from " + tableName;

	private Cor cor;

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

	public void delete() {
		if (db.connect()) {
			db.update("delete from " + tableName + " where cod_" + tableName
					+ " = " + cor.getCodigo());
			db.disconnect();
		}
	}

	public void edit() {
		if (db.connect()) {
			db.update("update " + tableName + " set descricao = '"
					+ cor.getNome() + "' where cod_" + tableName + " = "
					+ cor.getCodigo());
			db.disconnect();
		}
	}

	public void insert() {
		if (db.connect()) {
			db.update("insert into " + tableName + " (descricao) values ('"
					+ cor.getNome() + "')");
			db.disconnect();
		}
	}

	private ListaObjeto load(String sql) {
		ListaObjeto lista = new ListaObjeto();
		if (db.connect()) {
			db.select(sql);
			while (db.moveNext()) {
				lista
						.insertWhitoutPersist(new Cor(db.getInt("cod_"
								+ tableName), db.getString("descricao")));
			}
			db.disconnect();
		}
		return lista;
	}

	public ListaObjeto load() {
		return this.load(SELECT);
	}

	public ListaObjeto buscarPorDescricao(String descricao) {

		return this.load(SELECT + " where descricao like('%" + descricao
				+ "%')");
	}

	public ListaObjeto buscar(String campo, String operador, String valor) {

		String campoSQL = campo;
		String operadorSQL = null;
		String valorSQL = "'" + valor + "'";

		if (campo.equals("C�digo")) {

			campoSQL = "cod_" + tableName;
		}else{
			campoSQL = "descricao";
		}

		if (operador.equals("Igual")) {

			operadorSQL = "=";
		}
		else if ( operador.equals("Diferente")){
			
			operadorSQL = "!=";
		}
		else if ( operador.equals("Maior")){
			
			operadorSQL = ">";
		}
		else if ( operador.equals("Menor")){
			
			operadorSQL = "<";
		}
		else if ( operador.equals("Contem")){
			
			operadorSQL = "like";
			valorSQL = " '%" + valor + "%'";
		}

		String sql = SELECT;

		sql += " where " + campoSQL + " " + operadorSQL + valorSQL;

		return this.load(sql);
	}

}
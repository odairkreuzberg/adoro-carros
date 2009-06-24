package br.iav.ac.dao;

import br.iav.ac.database.DB;
import br.iav.ac.database.PostgreSQL;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.negocio.Marca;
import br.iav.ac.negocio.Modelo;

public class DaoModelo implements DaoInterface {

	private DB db = PostgreSQL.create();
	private Modelo modelo;

	// Nome da tabela e nome do sufixo do código
	private final static String tableName = "modelo";

	private final static String SELECT = "select modelo.*, marca.nome as ma_nome" + " from marca inner join modelo on (modelo.cod_marca = marca.cod_marca) ";
	private final static String SELECT_TEM_CARRO = "select modelo.cod_modelo from carro, modelo where carro.cod_modelo = modelo.cod_modelo and modelo.cod_modelo =  ";
	
	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public void delete() {

		String sql = SELECT_TEM_CARRO + modelo.getCodigo();

		if (this.load(sql).getSize() > 0) {
			throw new RuntimeException();
		}
		if (db.connect()) {
			db.update("delete from " + tableName + " where cod_" + tableName + " = " + modelo.getCodigo());
			db.disconnect();
		}
	}

	public void edit() {
		if (db.connect()) {
			db.update("update " + tableName + " set " +
											  "nome = '" + modelo.getNome() + "', " +
											  "cod_marca = " + modelo.getMarca().getCodigo() + " " +
											  "where cod_" + tableName	+ " = " + modelo.getCodigo());
			db.disconnect();
		}
	}

	public void insert() {
		if (db.connect()) {
			db.update("insert into " + tableName + " (nome, cod_marca) values ('" + modelo.getNome() + "', " + modelo.getMarca().getCodigo() + ")");
			db.disconnect();
		}
	}

	public ListaObjeto load(String sql) {
		ListaObjeto lista = new ListaObjeto();
		if (db.connect()) {
			db.select(sql);
			while (db.moveNext()) {
				Modelo modelo = new Modelo();
				Marca marca = new Marca();
				modelo.setCodigo(db.getInt("cod_modelo"));
				modelo.setNome(db.getString("nome"));
				marca.setCodigo(db.getInt("cod_marca"));
				marca.setNome(db.getString("ma_nome"));
				modelo.setMarca(marca);
				lista.insertWhitoutPersist(modelo);
			}
			db.disconnect();
		}
		return lista;
	}

	public ListaObjeto load() {
		return this.load(SELECT);
	}

	public Modelo searchWithCodigo(int codigo) {
		ListaObjeto listaObjeto = this.search("Código", "Igual", String.valueOf(codigo));
		if (listaObjeto.getSize() == 1) {
			return (Modelo) listaObjeto.getObjeto(0);
		}
		return null;
	}
	
	public ListaObjeto search(String campo, String operador, String valor) {
		String sql = SELECT;
		String campoSQL = null;
		String operadorSQL = null;
		String valorSQL = "'" + valor + "'";
		if (campo.equals("Código")) {
			campoSQL = " where cod_modelo ";
			valorSQL = valor;
			if (operador.equals("Contém")) {
				operador = "Igual";
				valorSQL = "-1";
			}
		} else if (campo.equals("Marca")) {
			campoSQL = " where marca.nome ";
		} else if (campo.equals("Modelo")) {
			campoSQL = " where modelo.nome ";
		}
		if (operador.equals("Igual")) {
			operadorSQL = "=";
		} else if (operador.equals("Diferente")) {
			operadorSQL = "!=";
		} else if (operador.equals("Maior")) {
			operadorSQL = ">";
		} else if (operador.equals("Menor")) {
			operadorSQL = "<";
		} else if (operador.equals("Contém")) {
			operadorSQL = "like";
			valorSQL = " '%" + valor + "%'";
		}
		sql += campoSQL + " " + operadorSQL + valorSQL;
		return this.load(sql);
	}

	public boolean existeModelo(Modelo modelo) {
		String sql = "select modelo.* from modelo where '" + modelo.getNome() +
		"' = modelo.nome and modelo.cod_modelo != " + modelo.getCodigo();
	System.out.println(this.load(sql).getSize());
	return this.load(sql).getSize() > 0;
	}

}
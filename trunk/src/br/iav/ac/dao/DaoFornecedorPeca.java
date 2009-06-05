package br.iav.ac.dao;

import br.iav.ac.database.DB;
import br.iav.ac.database.PostgreSQL;
import br.iav.ac.negocio.Fornecedor;
import br.iav.ac.negocio.FornecedorPeca;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.negocio.Marca;
import br.iav.ac.negocio.Modelo;
import br.iav.ac.negocio.Peca;
import br.iav.ac.negocio.PecaFornecedor;

public class DaoFornecedorPeca implements DaoInterface {

	private DB db = PostgreSQL.create();
	private FornecedorPeca fornecedorPeca = new FornecedorPeca();

	// Nome da tabela e nome do sufixo do código
	private final static String tableName = "fornecedor_peca";

	private final static String SELECT = "select * from " + tableName;
	private final static String SELECT_COM_FORNECEDOR = "select fornecedorPeca.* from "
			+ tableName + ", fornecedor";
	private final static String SELECT_COM_PECAS = "select  p.nome, fp.cod_peca, "
			+ "sum(fp.quantidade) as quantidade, avg(fp.preco) as preco from fornecedor_peca as fp, peca "
			+ "as p where fp.cod_peca = p.cod_peca group by fp.cod_peca, p.nome";

	public FornecedorPeca getFornecedorPeca() {
		return fornecedorPeca;
	}

	public void setFornecedorPeca(FornecedorPeca fornecedorPeca) {
		this.fornecedorPeca = fornecedorPeca;
	}

	@Override
	public void delete() {
		if (db.connect()) {
			db
					.update("delete from " + tableName
							+ " where cod_fornecedor = "
							+ fornecedorPeca.getFornecedor().getCodigo()
							+ " and cod_peca = "
							+ fornecedorPeca.getPeca().getCodigo());
			db.disconnect();
		}

	}

	@Override
	public void edit() {
		if (db.connect()) {
			db
					.update("update " + tableName + " set quantidade = "
							+ fornecedorPeca.getQtd() + ", preco = "
							+ fornecedorPeca.getPreco()
							+ " where cod_fornecedor = "
							+ fornecedorPeca.getFornecedor().getCodigo()
							+ " and cod_peca = "
							+ fornecedorPeca.getPeca().getCodigo());
			db.disconnect();
		}

	}

	@Override
	public void insert() {
		if (db.connect()) {
			db.update("insert into " + tableName
					+ " (quantidade, preco, cod_fornecedor, cod_peca) values ("
					+ fornecedorPeca.getQtd() + ", "
					+ fornecedorPeca.getPreco() + ", "
					+ fornecedorPeca.getFornecedor().getCodigo() + ", "
					+ fornecedorPeca.getPeca().getCodigo() + ")");
			db.disconnect();
		}

	}

	public Modelo searchWithCodigo(int codigo) {
		ListaObjeto listaObjeto = this.search("Código", "Igual", String
				.valueOf(codigo));
		if (listaObjeto.getSize() == 1) {
			return (Modelo) listaObjeto.getObjeto(0);
		}
		return null;
	}

	public ListaObjeto getPecas() {
		ListaObjeto lista = new ListaObjeto();
		if (db.connect()) {
			db.select(SELECT_COM_PECAS);
			while (db.moveNext()) {
				lista.insertWhitoutPersist(new PecaFornecedor(db.getInt("cod_peca"), db
						.getString("nome"), db.getInt("quantidade"), db
						.getFloat("preco")));
			}
			db.disconnect();
		}
		return lista;

	}

	public ListaObjeto load(String sql) {
		ListaObjeto lista = new ListaObjeto();
		DaoFornecedor daoFornecedor = new DaoFornecedor();
		DaoPeca daoPeca = new DaoPeca();
		if (db.connect()) {
			db.select(sql);
			while (db.moveNext()) {
				Peca peca = daoPeca.searchWithCodigo(db.getInt("cod_peca"));
				Fornecedor fornecedor = daoFornecedor.searchWithCodigo(db
						.getInt("cod_fornecedor"));
				lista.insertWhitoutPersist(new FornecedorPeca(peca, fornecedor,
						db.getInt("quantidade"), db.getFloat("preco")));
			}
			db.disconnect();
		}
		return lista;
	}

	@Override
	public ListaObjeto load() {
		return this.load(SELECT);
	}

	public ListaObjeto search(String campo, String operador, String valor) {

		//String sql = SELECT;
/*
		String campoSQL = campo;
		String operadorSQL = null;
		String valorSQL = "'" + valor + "'";
		String outroSQL = "";

		if (campo.equals("Código")) {
			campoSQL = "CAST(cod_" + tableName + " as VARCHAR)";
		} else if (campo.equals("Marca")) {
			// sql = SELECT_COM_MARCA;
			campoSQL = " marca.cod_marca = modelo.cod_marca and marca.nome";

		} else if (campo.equals("Modelo")) {
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

		sql += " where " + outroSQL + campoSQL + " " + operadorSQL + valorSQL;*/
		String sql = "select * from fornecedor_peca where fornecedor_peca.cod_peca = " +valor; 
		return this.load(sql);
	}

	public FornecedorPeca temFornecedorMarca(FornecedorPeca fornecedorPeca) {
		String sql = "select * from fornecedor_peca where cod_fornecedor = "
				+ fornecedorPeca.getFornecedor().getCodigo()
				+ " and cod_peca = " + fornecedorPeca.getPeca().getCodigo();
		if (this.load(sql).getSize() == 1) {
			return (FornecedorPeca) this.load(sql).getObjeto(0);
		}
		return null;
	}

}

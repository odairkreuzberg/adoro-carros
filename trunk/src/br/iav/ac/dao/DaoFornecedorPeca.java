package br.iav.ac.dao;

import br.iav.ac.database.DB;
import br.iav.ac.database.PostgreSQL;
import br.iav.ac.negocio.Fornecedor;
import br.iav.ac.negocio.FornecedorPeca;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.negocio.Peca;
import br.iav.ac.negocio.PecaDetalhe;
import br.iav.ac.negocio.PecaEstoque;

public class DaoFornecedorPeca implements DaoInterface {

	private DB db = PostgreSQL.create();
	private FornecedorPeca fornecedorPeca = new FornecedorPeca();
	private final static String SELECT_PECAS = "select fornecedor_peca.*, peca.nome" +
	" as p_nome, fornecedor.nome_fantasia as f_nome, fornecedor.cod_fornecedor" +
	" from(fornecedor_peca inner join fornecedor on fornecedor_peca.cod_fornecedor = " +
	"fornecedor.cod_fornecedor) inner join peca on " +
	"fornecedor_peca.cod_peca = peca.cod_peca where fornecedor_peca.cod_peca = ";
	
	private final static String SELECT_ESTOQUE = "select p.cod_peca, p.nome, " +
		"sum(fp.quantidade) as quantidade, avg(fp.preco) as preco  " +
		"from(fornecedor_peca as fp inner join peca as p on fp.cod_peca = " +
		"p.cod_peca)group by  p.cod_peca, p.nome";
	

	// Nome da tabela e nome do sufixo do código
	private final static String tableName = "fornecedor_peca";

	public FornecedorPeca getFornecedorPeca() {
		return fornecedorPeca;
	}

	public void setFornecedorPeca(FornecedorPeca fornecedorPeca) {
		this.fornecedorPeca = fornecedorPeca;
	}

	@Override
	public void delete() {
		if (db.connect()) {
			db.update("delete from " + tableName
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
			db.update("update " + tableName + " set quantidade = "
				+ fornecedorPeca.getQtd() + ", preco = "
				+ fornecedorPeca.getPreco()
				+ " where cod_fornecedor = "
				+ fornecedorPeca.getFornecedor().getCodigo()
				+ " and cod_peca = "
				+ fornecedorPeca.getPeca().getCodigo());
			db.disconnect();
		}

	}

	public void editEstoque() {
		if (db.connect()) {
			db.update("update " + tableName + " set quantidade = "
				+ fornecedorPeca.getQtd()
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

	public ListaObjeto getPecas() {
		ListaObjeto lista = new ListaObjeto();
		if (db.connect()) {
			db.select(SELECT_ESTOQUE);
			while (db.moveNext()) {
				lista.insertWhitoutPersist(new PecaEstoque(
					db.getInt("cod_peca"),
					db.getString("nome"), 
					db.getInt("quantidade"), 
					db.getFloat("preco")));
			}
			db.disconnect();
		}
		return lista;

	}

	public ListaObjeto load(String sql) {
		ListaObjeto lista = new ListaObjeto();
		if (db.connect()) {
			db.select(sql);
			while (db.moveNext()) {				
				lista.insertWhitoutPersist(new PecaDetalhe(
					db.getInt("cod_fornecedor"),
					db.getString("p_nome"),
					db.getInt("quantidade"),
					db.getString("f_nome"),
					db.getFloat("preco")));
			}
			db.disconnect();
		}
		return lista;
	}

	public ListaObjeto loadFP(String sql) {
		ListaObjeto lista = new ListaObjeto();
		if (db.connect()) {
			db.select(sql);
			while (db.moveNext()) {		
				FornecedorPeca fp = new FornecedorPeca();
				Fornecedor f = new Fornecedor();
				Peca p = new Peca();
				fp.setQtd(db.getInt("quantidade"));
				f.setCodigo(db.getInt("cod_fornecedor"));
				p.setCodigo(db.getInt("cod_peca"));
				fp.setPreco(db.getFloat("preco"));
				fp.setFornecedor(f);
				fp.setPeca(p);
				lista.insertWhitoutPersist(fp);
			}
			db.disconnect();
		}
		return lista;
	}

	@Override
	public ListaObjeto load() {
		return null;
	}

	public FornecedorPeca temFornecedorPeca(FornecedorPeca fornecedorPeca) {
		String sql = "select * from fornecedor_peca where cod_fornecedor = "
				+ fornecedorPeca.getFornecedor().getCodigo()
				+ " and cod_peca = " + fornecedorPeca.getPeca().getCodigo();
		if (this.loadFP(sql).getSize() == 1) {
			return ((FornecedorPeca)this.loadFP(sql).getObjeto(0)) ;
		}
		return null;
	}

	public ListaObjeto getListaPecaFornecedor(String cod) {
		String sql = SELECT_PECAS + cod; 
		return this.load(sql);
	}

	public ListaObjeto getListaFornecedorPeca(int codigo) {
		// TODO Auto-generated method stub
		return this.loadFP("select fornecedor_peca.*, peca.* from fornecedor_peca, peca where fornecedor_peca.cod_peca = peca.cod_peca and peca.cod_peca = " +codigo);
	}

}

package br.iav.ac.dao;

import br.iav.ac.database.DB;
import br.iav.ac.database.PostgreSQL;
import br.iav.ac.negocio.Cidade;
import br.iav.ac.negocio.Endereco;
import br.iav.ac.negocio.Fornecedor;
import br.iav.ac.negocio.ListaObjeto;

public class DaoFornecedor implements DaoInterface {

	private DB db = PostgreSQL.create();
	private Fornecedor fornecedor;
	
	//Nome da tabela e nome do sufixo do código
	private final static String tableName = "fornecedor";
	private final static String SELECT = "select fornecedor.*, cidade.nome as ci_nome, cidade.ddd as ci_ddd from cidade inner join fornecedor on (fornecedor.cod_cidade = cidade.cod_cidade) ";	
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public void delete() {
		if (db.connect()) {
			db.update("delete from " + tableName + " where cod_" + tableName + " = " + fornecedor.getCodigo());
			db.disconnect();
		}
	}
	
	public void edit() {
		if (db.connect()) {
			db.update("update " + tableName + " set nome_fantasia = '" + fornecedor.getNome() + "', " +
													"razao_social = '" + fornecedor.getRazaoSocial() + "', " +
													"cnpj = " + fornecedor.getCnpj() + ", " +
													"telefone = '" + fornecedor.getTelefone() + "', " +
													"fax = " + fornecedor.getFax() + ", " +
													"rua = '" + fornecedor.getEndereco().getRua() + "', " +
													"numero = '" + fornecedor.getEndereco().getNumero() + "', " +
													"bairro = '" + fornecedor.getEndereco().getBairro() + "', " +
													"cod_cidade = '" + fornecedor.getEndereco().getCidade().getCodigo() + "', " +
													"dep = '" + fornecedor.getEndereco().getCep() + "' " +
													"complemento = '" + fornecedor.getEndereco().getComplemento() + "' " +
													"where cod_" + tableName + " = " + fornecedor.getCodigo());
			db.disconnect();
		}
	}
	
	public void insert() {
		if (db.connect()) {
			db.update("insert into " + tableName + " (nome_fantasia, razao_social, cnpj, telefone, fax, rua, numero, bairro, cod_cidade, cep, complemento) values " +
													"('" + fornecedor.getNome() + "', " +
													"'" + fornecedor.getRazaoSocial() + "', " +
													"'" + fornecedor.getCnpj() + "', " +
													"'" + fornecedor.getTelefone() + "', " +
													"'" + fornecedor.getFax() + "', " +
													"'" + fornecedor.getEndereco().getRua() + "', " +
													fornecedor.getEndereco().getNumero() + ", " +
													"'" + fornecedor.getEndereco().getBairro() + "', " +
													fornecedor.getEndereco().getCidade().getCodigo() + ", " +
													"'" + fornecedor.getEndereco().getCep() + "', " +
													"'" + fornecedor.getEndereco().getComplemento() + "')");
			

			 
			
			
			db.disconnect();
		}
	}
	
	public ListaObjeto load(String sql) {
		ListaObjeto lista = new ListaObjeto();
		if (db.connect()) {
			db.select(sql);
			while (db.moveNext()) {
				lista.insertWhitoutPersist(new Fornecedor(db.getInt("cod_" + tableName),
														  db.getString("nome_fantasia"),
														  db.getString("razao_social"),
														  db.getString("cnpj"),
														  db.getString("telefone"),
														  db.getString("fax"),
														  new Endereco(db.getString("rua"),
																  	   db.getInt("numero"),
																  	   db.getString("bairro"),
																  	   new Cidade(db.getInt("cod_cidade"),
																  			   	  db.getString("ci_nome"),
																  			   	  db.getInt("ci_ddd")
																  	   ),
																  	   db.getString("cep"),
																  	   db.getString("complemento")
														  )
											)
				);
			}
			db.disconnect();
		}
		return lista;
	}	
	
	public ListaObjeto load() {
		return this.load(SELECT);
	}

	public Fornecedor searchWithCodigo(int codigo) {
		ListaObjeto listaObjeto = this.search("Código", "Igual", String.valueOf(codigo));
		if (listaObjeto.getSize() == 1) {
			return (Fornecedor) listaObjeto.getObjeto(0);
		}
		return null;
	}
	
	public ListaObjeto search(String campo, String operador, String valor) {
		String sql = SELECT;
		String campoSQL = null;
		String operadorSQL = null;
		String valorSQL = "'" + valor + "'";
		if (campo.equals("Código")) {
			campoSQL = "where cod_fornecedor";
			if (operador.equals("Contem")) {
				operador = "Igual";
				valorSQL = "-1";
			}
		} else if (campo.equals("Nome Fantasia")) {
			campoSQL = "where fornecedor.nome_fantasia ";
		} else if (campo.equals("Razão Social")) {
			campoSQL = "where fornecedor.razao_social ";
		} else if (campo.equals("CNPJ")) {
			campoSQL = "where fornecedor.cnpj ";
		} else if (campo.equals("Telefone")) {
			campoSQL = "where fornecedor.telefone ";
		} else if (campo.equals("Fax")) {
			campoSQL = "where fornecedor.fax ";
		} else if (campo.equals("Rua")) {
			campoSQL = "where fornecedor.rua ";
		} else if (campo.equals("Número")) {
			campoSQL = "where fornecedor.numero";
			if (operador.equals("Contem")) {
				operador = "Igual";
				valorSQL = "-1";
			}
		} else if (campo.equals("Bairro")) {
			campoSQL = "where fornecedor.bairro ";
		} else if (campo.equals("CEP")) {
			campoSQL = "where fornecedor.cep ";
		} else if (campo.equals("Complemento")) {
			campoSQL = "where fornecedor.complemento ";
		} else if (campo.equals("Cidade")) {
			campoSQL = "where cidade.nome ";
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
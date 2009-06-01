package br.iav.ac.dao;

import br.iav.ac.database.DB;
import br.iav.ac.database.PostgreSQL;
import br.iav.ac.negocio.Cidade;
import br.iav.ac.negocio.Cliente;
import br.iav.ac.negocio.Endereco;
import br.iav.ac.negocio.Fornecedor;
import br.iav.ac.negocio.ListaObjeto;

public class DaoFornecedor implements DaoInterface {

	private DB db = PostgreSQL.create();
	private Fornecedor fornecedor;
	
	//Nome da tabela e nome do sufixo do código
	private final static String tableName = "fornecedor";
	
	private final static String SELECT = "select * from " + tableName;
	private final static String SELECT_COM_CIDADE = "select cliente.* from " + tableName + ", cidade";
	
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
		DaoCidade daoCidade = new DaoCidade();
		ListaObjeto lista = new ListaObjeto();
		if (db.connect()) {
			db.select(sql);
			while (db.moveNext()) {
				Cidade cidade = daoCidade.searchWithCodigo(db.getInt("cod_cidade"));
				Endereco endereco = new Endereco(db.getString("rua"), db.getInt("numero"), db.getString("bairro"), cidade, db.getString("cep"), db.getString("complemento"));
				lista.insertWhitoutPersist(new Fornecedor(db.getInt("cod_" + tableName), db.getString("nome_fantasia"), db.getString("razao_social"), db.getString("cnpj"), db.getString("telefone"), db.getString("fax"),endereco));
				
			
			}
			db.disconnect();
		}
		return lista;
	}	
	
	public ListaObjeto load() {
		return this.load(SELECT);
	}
	
	public ListaObjeto search(String campo, String operador, String valor) {
		String campoSQL = campo;
		String operadorSQL = null;
		String valorSQL = "'" + valor + "'";
		String sql = SELECT;
		if (campo.equals("Código")) {
			campoSQL = "cast (cod_"+tableName+" as varchar)";
		} else if (campo.equals("Nome Fantasia")) {
			campoSQL = "nome_fantasia";
		} else if (campo.equals("Razão Social")) {
			campoSQL = "razao_social";
		} else if (campo.equals("CNPJ")) {
			campoSQL = "cnpj";
		} else if (campo.equals("Telefone")) {
			campoSQL = "telefone";
		} else if (campo.equals("Fax")) {
			campoSQL = "fax";
		} else if (campo.equals("Rua")) {
			campoSQL = "rua";
		} else if (campo.equals("Número")) {
			campoSQL = "cast (numero as varchar)";
		} else if (campo.equals("Bairro")) {
			campoSQL = "bairro";
		} else if (campo.equals("CEP")) {
			campoSQL = "cep";
		} else if (campo.equals("Complemento")) {
			campoSQL = "complemento";
		} else if (campo.equals("Cidade")) {
			sql = SELECT_COM_CIDADE;
			campoSQL = "cidade.cod_cidade = fornecedor.cod_cidade";
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
		sql += " where " + campoSQL + " " + operadorSQL + valorSQL;
		return this.load(sql);
	}

}
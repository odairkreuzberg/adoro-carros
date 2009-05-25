package br.iav.ac.dao;

import br.iav.ac.database.DB;
import br.iav.ac.database.PostgreSQL;
import br.iav.ac.negocio.Cidade;
import br.iav.ac.negocio.Endereco;
import br.iav.ac.negocio.Fornecedor;
import br.iav.ac.negocio.ListaObjeto;

public class DaoFornecedor implements DaoInterface {

	private DB db = PostgreSQL.novaInstancia();
	private Fornecedor fornecedor;
	
	//Nome da tabela e nome do sufixo do código
	String tableName = "fornecedor";
	
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
			db.update("update " + tableName + " set nome = '" + fornecedor.getNome() + "', " +
													"rua = '" + fornecedor.getEndereco().getRua() + "', " +
													"numero = " + fornecedor.getEndereco().getNumero() + ", " +
													"bairro = '" + fornecedor.getEndereco().getBairro() + "', " +
													"cod_cidade = " + fornecedor.getEndereco().getCidade().getCodigo() + ", " +
													"cep = '" + fornecedor.getEndereco().getCep() + "', " +
													"complemento = '" + fornecedor.getEndereco().getComplemento() + "', " +
													"telefone = '" + fornecedor.getTelefone() + "', " +
													"cpf = '" + fornecedor.getCpf() + "', " +
													"cnpj = '" + fornecedor.getCnpj() + "' " +
													"where cod_" + tableName + " = " + fornecedor.getCodigo());
			db.disconnect();
		}
	}
	
	public void insert() {
		if (db.connect()) {
			db.update("insert into " + tableName + " (nome, rua, numero, bairro, cod_cidade, cep, complemento, telefone, cpf, rg, data_nascimento, cod_cargo, salario) values " +
													"('" + fornecedor.getNome() + "', " +
													"'" + fornecedor.getEndereco().getRua() + "', " +
													fornecedor.getEndereco().getNumero() + ", " +
													"'" + fornecedor.getEndereco().getBairro() + "', " +
													fornecedor.getEndereco().getCidade().getCodigo() + ", " +
													"'" + fornecedor.getEndereco().getCep() + "', " +
													"'" + fornecedor.getEndereco().getComplemento() + "', " +
													"'" + fornecedor.getTelefone() + "', " +
													"'" + fornecedor.getCpf() + "', " +
													"'" + fornecedor.getCnpj() + "')");
			db.disconnect();
		}
	}
	
	public ListaObjeto load() {
		ListaObjeto lista = new ListaObjeto();
		if (db.connect()) {
			db.select("select f.cod_" + tableName + ", f.nome, f.rua, f.numero, f.bairro, f.cod_cidade, c.cod_cidade, c.nome, c.ddd, f.cep, f.complemento, f.telefone, f.cpf, f.cnpj from " + tableName + " f, cidade c where f.cod_cidade = c.cod_cidade");
			while (db.moveNext()) {
				Cidade cidade = new Cidade(db.getInt("c.cod_cidade"), db.getString("c.nome"), db.getInt("c.ddd"));
				Endereco endereco = new Endereco(db.getString("f.rua"), db.getInt("f.numero"), db.getString("f.bairro"), cidade, db.getString("f.cep"), db.getString("f.complemento"));
				lista.insertWhitoutPersist(new Fornecedor(db.getInt("f.cod_" + tableName), db.getString("f.nome"), endereco, db.getString("f.telefone"), db.getString("f.cpf"), db.getString("f.cnpj")));
			}
			db.disconnect();
		}
		return lista;
	}

}
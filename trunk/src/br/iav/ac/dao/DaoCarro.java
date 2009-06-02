package br.iav.ac.dao;

import br.iav.ac.database.DB;
import br.iav.ac.database.PostgreSQL;
import br.iav.ac.negocio.Carro;
import br.iav.ac.negocio.Cidade;
import br.iav.ac.negocio.Cliente;
import br.iav.ac.negocio.Cor;
import br.iav.ac.negocio.Endereco;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.negocio.Marca;
import br.iav.ac.negocio.Modelo;

public class DaoCarro implements DaoInterface {

	private DB db = PostgreSQL.create();
	private Carro carro;
	
	//Nome da tabela e nome do sufixo do código
	private final static String tableName = "carro";

	private final static String SELECT = "select * from " + tableName;
	private final static String SELECT_COM_COR = "select carro.* from carro, cor";
	private final static String SELECT_COM_MARCA = "select carro.* from carro, marca";
	private final static String SELECT_COM_MODELO = "select carro.* from carro, modelo";
	private final static String SELECT_COM_CLIENTE = "select carro.* from carro, cliente";
	
	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public void delete() {
		if (db.connect()) {
			db.update("delete from " + tableName + " where cod_" + tableName + " = " + carro.getCodigo());
			db.disconnect();
		}
	}
	
	public void edit() {
		if (db.connect()) {
			db.update("update " + tableName + " set placa = '" + carro.getPlaca() + "', " +
													"cod_cliente = " + carro.getCliente().getCodigo() + ", " +
													"cod_modelo = " + carro.getModelo().getCodigo() + ", " +
													"cod_cor = " + carro.getCor().getCodigo() + ", " +
													"ano_fabricacao = " + carro.getAnoFabricacao() + " " +
													"where cod_" + tableName + " = " + carro.getCodigo());
			db.disconnect();
		}
	}
	
	public void insert() {
		if (db.connect()) {
			db.update("insert into " + tableName + " (placa, cod_cliente, cod_modelo, cod_cor, ano_fabricacao) values " +
													"('" + carro.getPlaca() + "', " +
													carro.getCliente().getCodigo() + ", " +
													carro.getModelo().getCodigo() + ", " +
													carro.getCor().getCodigo() + ", " +
													carro.getAnoFabricacao() + ")");
			db.disconnect();
		}
	}
	
	public ListaObjeto load(String sql) {
		DaoModelo daoModelo = new DaoModelo();
		DaoCliente daoCliente = new DaoCliente();
		DaoCor daoCor = new DaoCor();
		ListaObjeto lista = new ListaObjeto();
		if (db.connect()) {
			db.select(sql);
			while (db.moveNext()) {
			    Modelo modelo = daoModelo.searchWithCodigo(db.getInt("cod_modelo"));
			    Cliente cliente = daoCliente.searchWithCodigo(db.getInt("cod_cliente"));
			    Cor cor = daoCor.searchWithCodigo(db.getInt("cod_cor"));
				lista.insertWhitoutPersist(new Carro(db.getInt("cod_"	+ tableName), db.getString("placa"),cliente, modelo,cor,db.getString("ano_fabricacao")));
			    }
			db.disconnect();
		}
		return lista;
	}

	public ListaObjeto load() {
		return this.load(SELECT);
	}

	public ListaObjeto search(String campo, String operador, String valor) {

		String sql = SELECT;
		
		String campoSQL = campo;
		String operadorSQL = null;
		String valorSQL = "'" + valor + "'";
		
		if (campo.equals("Código")) {
			campoSQL = "CAST(cod_" + tableName + " as VARCHAR)";
		} else if (campo.equals("Marca")) {
			sql = SELECT_COM_MARCA;
			campoSQL = "marca.cod_marca = carro.cod_marca and marca.nome";
		} else if (campo.equals("Modelo")) {
			sql = SELECT_COM_MODELO;
			campoSQL = " modelo.cod_modelo = carro.cod_modelo and modelo.nome";
		} else if (campo.equals("Cor")) {
			sql = SELECT_COM_COR;
			campoSQL = "cor.cod_cor = carro.cod_cor and cor.nome";
		} else if (campo.equals("Marca")) {
			sql = SELECT_COM_CLIENTE;
			campoSQL = "cliente.cod_cliente = carro.cod_cliente and cliente.nome";
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
		
		sql += " where " + campoSQL + " " + operadorSQL + valorSQL;
		return this.load(sql);
	}
	
	/*public ListaObjeto load() {
		ListaObjeto lista = new ListaObjeto();
		if (db.connect()) {
			//c = carro, c1 = cliente, c1c = cidade do cliente, m = modelo, mm = marca do modelo, c2 = cor
			db.select("select c.cod_" + tableName + ", c.placa, c.cod_cliente, c.cod_modelo, c.cod_cor, c.ano_fabricacao, " +
							 "c1.cod_cliente, c1.nome, c1.rua, c1.numero, c1.bairro, c1.cod_cidade, c1c.cod_cidade, c1c.nome, c1c.ddd, c1.cep, c1.complemento, c1.telefone, c1.cpf, c1.rg, c1.data_nascimento, c1.profissao, " +
							 "m.cod_modelo, m.nome, m.cod_marca, mm.cod_marca, mm.nome, " +
							 "c2.cod_cor, c2.nome " +
							 "from " + tableName + " c, cliente c1, cidade c1c, modelo m, marca mm, cor c2 " +
							 "where c.cod_cliente = c1.cod_cliente and c.cod_modelo = m.cod_modelo and c.cod_cor = c2.cod_cor and c1.cod_cidade = c1c.cod_cidade and m.cod_marca = mm.cod_marca");
			while (db.moveNext()) {
				Cidade cidade = new Cidade(db.getInt("c1c.cod_cidade"), db.getString("c1c.nome"), db.getInt("c1c.ddd"));
				Endereco endereco = new Endereco(db.getString("c1.rua"), db.getInt("c1.numero"), db.getString("c1.bairro"), cidade, db.getString("c1.cep"), db.getString("c1.complemento"));
				Cliente cliente = new Cliente(db.getInt("c1.cod_cliente"), db.getString("c1.nome"), endereco, db.getString("c1.telefone"), db.getString("c1.cpf"), db.getString("c1.rg"), db.getDate("c1.data_nascimento"), db.getString("c1.profissao"), null);
				Marca marca = new Marca(db.getInt("mm.cod_marca"), db.getString("mm.nome"));
				Modelo modelo = new Modelo(db.getInt("m.cod_modelo"), db.getString("m.nome"), marca);
				Cor cor = new Cor(db.getInt("c2.cod_cor"), db.getString("c2.nome"));
				lista.insertWhitoutPersist(new Carro(db.getInt("c.cod_" + tableName), db.getString("c.placa"), cliente, modelo, cor, db.getDate("c.ano_fabricacao")));
			}
			db.disconnect();
		}
		return lista;
	}*/

}
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
	private final static String SELECT_COM_MARCA = "select carro.* from carro, modelo, marca";
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
													"ano_fabricacao = '" + carro.getAnoFabricacao() + "' " +
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
													carro.getCor().getCodigo() + ", '" +
													carro.getAnoFabricacao() + "')");
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
			campoSQL = "marca.cod_marca = modelo.cod_marca and modelo.cod_modelo = carro.cod_modelo and marca.nome";
		} else if (campo.equals("Modelo")) {
			sql = SELECT_COM_MODELO;
			campoSQL = " modelo.cod_modelo = carro.cod_modelo and modelo.nome";
		} else if (campo.equals("Cor")) {
			sql = SELECT_COM_COR;
			campoSQL = "cor.cod_cor = carro.cod_cor and cor.nome";
		} else if (campo.equals("Cliente")) {
			sql = SELECT_COM_CLIENTE;
			campoSQL = "cliente.cod_cliente = carro.cod_cliente and cliente.nome";
		} else if (campo.equals("Placa")) {
			campoSQL = "placa";
		} else if (campo.equals("Ano")) {
			campoSQL = "ano_fabricacao";
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

}
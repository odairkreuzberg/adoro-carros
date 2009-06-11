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

	private final static String SELECT = "select carro.cod_carro, carro.placa, " +
			"carro.ano_fabricacao, modelo.nome as mo_nome, marca.nome as ma_nome, " +
			"cor.nome as c_nome, cliente.nome as cli_nome from(carro " +
			"inner join modelo on carro.cod_modelo = modelo.cod_modelo) " +
			"inner join marca on modelo.cod_marca = marca.cod_marca " +
			"inner join cor on carro.cod_cor = cor.cod_cor " +
			"inner join cliente on carro.cod_cliente = cliente.cod_cliente";
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
		ListaObjeto lista = new ListaObjeto();
		if (db.connect()) {
			db.select(sql);
			while (db.moveNext()) {
				Modelo modelo = new Modelo();
				Marca marca = new Marca();
				Carro carro = new Carro();
				Cor cor = new Cor(); 
				Cliente cliente = new Cliente();
				
				modelo.setCodigo(db.getInt("cod_modelo"));
				modelo.setNome(db.getString("mo_nome"));
				
				marca.setCodigo(db.getInt("cod_marca"));
				marca.setNome(db.getString("ma_nome"));
				modelo.setMarca(marca);
				
				cliente.setCodigo(db.getInt("cod_cliente"));
				cliente.setNome(db.getString("cli_nome"));
				
				cor.setCodigo(db.getInt("cod_cor"));
				cor.setNome(db.getString("c_nome"));
				
				carro.setCodigo(db.getInt("cod_carro"));
				carro.setAnoFabricacao(db.getString("ano_fabricacao"));
				carro.setPlaca(db.getString("placa"));
				carro.setCliente(cliente);
				carro.setCor(cor);
				carro.setModelo(modelo);				
				lista.insertWhitoutPersist(carro);
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
		
		String campoSQL = null;
		String operadorSQL = null;
		String valorSQL = null;
		
		if (campo.equals("Código")) {
			valorSQL = valor;
			campoSQL = " where cod_carro ";
			if (operador.equals("Contem")) {
				operador = "Igual";
				valorSQL = "-1";
			}
		} else if (campo.equals("Marca")) {
			valorSQL = "'" + valor + "'";
			campoSQL = " where marca.nome ";
		} else if (campo.equals("Modelo")) {
			valorSQL = "'" + valor + "'";
			campoSQL = " where modelo.nome ";
		} else if (campo.equals("Cor")) {
			valorSQL = "'" + valor + "'";
			campoSQL = " where cor.nome ";
		} else if (campo.equals("Cliente")) {
			valorSQL = "'" + valor + "'";
			campoSQL = " where cliente.nome ";
		} else if (campo.equals("Placa")) {
			valorSQL = "'" + valor + "'";
			campoSQL = " where placa ";
		} else if (campo.equals("Ano")) {
			valorSQL = "'" + valor + "'";
			campoSQL = " where ano_fabricacao ";
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
		
		sql += campoSQL + " " + operadorSQL + valorSQL;
		return this.load(sql);
	}

}
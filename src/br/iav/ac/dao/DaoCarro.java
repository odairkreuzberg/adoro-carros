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

	private DB db = PostgreSQL.novaInstancia();
	private Carro carro;
	
	//Nome da tabela e nome do sufixo do código
	String tableName = "carro";
	
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
	
	public ListaObjeto load() {
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
	}

}
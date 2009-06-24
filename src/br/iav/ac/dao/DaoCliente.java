package br.iav.ac.dao;

import br.iav.ac.database.DB;
import br.iav.ac.database.PostgreSQL;
import br.iav.ac.negocio.Cidade;
import br.iav.ac.negocio.Cliente;
import br.iav.ac.negocio.Endereco;
import br.iav.ac.negocio.ListaObjeto;

public class DaoCliente implements DaoInterface {


	private DB db = PostgreSQL.create();
	private Cliente cliente;
	
	//Nome da tabela e nome do sufixo do código
	private final static String tableName = "cliente";
	private final static String SELECT = "select cliente.*, cidade.nome as "
		+ "ci_nome from cidade inner join cliente on "
		+ "(cliente.cod_cidade = cidade.cod_cidade) ";
	private final static String SELECT_TEM_CARRO = "select cliente.cod_cliente from carro, cliente where carro.cod_cliente = cliente.cod_cliente and cliente.cod_cliente = ";
	
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void delete() {
		String sql = SELECT_TEM_CARRO + cliente.getCodigo();

		if (this.load(sql).getSize() > 0) {
			throw new RuntimeException();
		}
		if (db.connect()) {
			db.update("delete from " + tableName + " where cod_" + tableName + " = " + cliente.getCodigo());
			db.disconnect();
		}
	}
	
	public void edit() {
		if (db.connect()) {
			db.update("update " + tableName + " set nome = '" + cliente.getNome() + "', " +
													"rua = '" + cliente.getEndereco().getRua() + "', " +
													"numero = " + cliente.getEndereco().getNumero() + ", " +
													"bairro = '" + cliente.getEndereco().getBairro() + "', " +
													"cod_cidade = " + cliente.getEndereco().getCidade().getCodigo() + ", " +
													"cep = '" + cliente.getEndereco().getCep() + "', " +
													"complemento = '" + cliente.getEndereco().getComplemento() + "', " +
													"telefone = '" + cliente.getTelefone() + "', " +
													"cpf = '" + cliente.getCpf() + "', " +
													"rg = '" + cliente.getRg() + "', " +
													"data_nascimento = '" + cliente.dataNascimentoToString() + "', " +
													"profissao = '" + cliente.getProfissao() + "' " +
													"where cod_" + tableName + " = " + cliente.getCodigo());
			db.disconnect();
		}
	}
	
	public void insert() {
		if (db.connect()) {
			db.update("insert into " + tableName + " (nome, rua, numero, bairro, cod_cidade, cep, complemento, telefone, cpf, rg, data_nascimento, profissao) values " +
													"('" + cliente.getNome() + "', " +
													"'" + cliente.getEndereco().getRua() + "', " +
													cliente.getEndereco().getNumero() + ", " +
													"'" + cliente.getEndereco().getBairro() + "', " +
													cliente.getEndereco().getCidade().getCodigo() + ", " +
													"'" + cliente.getEndereco().getCep() + "', " +
													"'" + cliente.getEndereco().getComplemento() + "', " +
													"'" + cliente.getTelefone() + "', " +
													"'" + cliente.getCpf() + "', " +
													"'" + cliente.getRg() + "', " +
													"'" + cliente.dataNascimentoToString() + "', " +
													"'" + cliente.getProfissao() + "')");
			db.disconnect();
		}
	}
	
	public ListaObjeto load(String sql) {
		ListaObjeto lista = new ListaObjeto();
		if (db.connect()) {
			db.select(sql);
			while (db.moveNext()) {
				Cidade cidade = new Cidade();
				Cliente cliente = new Cliente();
				Endereco endereco = new Endereco();
				
				cidade.setCodigo(db.getInt("cod_cidade"));
				cidade.setNome(db.getString("ci_nome"));
				endereco.setCidade(cidade);				
				endereco.setBairro(db.getString("bairro"));
				endereco.setCep(db.getString("cep"));
				endereco.setComplemento(db.getString("complemento"));
				endereco.setNumero(db.getInt("numero"));
				endereco.setRua(db.getString("rua"));
				
				cliente.setEndereco(endereco);				
				cliente.setCodigo(db.getInt("cod_cliente"));
				cliente.setCpf(db.getString("cpf"));
				cliente.setDataNascimento(db.getDate("data_nascimento"));
				cliente.setNome(db.getString("nome"));
				cliente.setProfissao(db.getString("profissao"));
				cliente.setRg(db.getString("rg"));
				cliente.setTelefone(db.getString("telefone"));
				
				 
				lista.insertWhitoutPersist(cliente);
			}
			db.disconnect();
		}
		return lista;
	}
	
	public ListaObjeto load() {
		return this.load(SELECT);
	}
	
	public Cliente searchWithCodigo(int codigo) {
		ListaObjeto listaObjeto = this.search("Código", "Igual", String.valueOf(codigo));
		if (listaObjeto.getSize() == 1) {
			return (Cliente) listaObjeto.getObjeto(0);
		}
		return null;
	}
	
	public ListaObjeto search(String campo, String operador, String valor) {
		
		String sql = SELECT;
		
		String campoSQL = null;
		String operadorSQL = null;
		String valorSQL = null;
		if (campo.equals("Código")) {
			valorSQL = valor;
			campoSQL = " where cod_cliente ";
			if (operador.equals("Contém")) {
				operador = "Igual";
				valorSQL = "-1";
			}
		} else if (campo.equals("Nome")) {
			valorSQL = "'" + valor + "'";
			campoSQL = " where cliente.nome ";
		} else if (campo.equals("Telefone")) {
			valorSQL = "'" + valor + "'";
			campoSQL = "where cliente.telefone ";
		} else if (campo.equals("CPF")) {
			valorSQL = "'" + valor + "'";
			campoSQL = "where cliente.cpf ";
		} else if (campo.equals("RG")) {
			valorSQL = "'" + valor + "'";
			campoSQL = "where cliente.rg ";
		} else if (campo.equals("Profissão")) {
			valorSQL = "'" + valor + "'";
			campoSQL = "where cliente.profissao ";
		} else if (campo.equals("Data de Nascimento")) {
			valorSQL = "'" + valor + "'";
			campoSQL = "where cliente.data_nascimento ";
		} else if (campo.equals("Rua")) {
			valorSQL = "'" + valor + "'";
			campoSQL = "where cliente.rua ";
		} else if (campo.equals("Número")) {
			valorSQL = valor;
			campoSQL = "where cliente.numero";
			if (operador.equals("Contém")) {
				operador = "Igual";
				valorSQL = "-1";
			}
		} else if (campo.equals("Bairro")) {
			valorSQL = "'" + valor + "'";
			campoSQL = "where cliente.bairro ";
		} else if (campo.equals("CEP")) {
			valorSQL = "'" + valor + "'";
			campoSQL = "where cliente.cep ";
		} else if (campo.equals("Complemento")) {
			valorSQL = "'" + valor + "'";
			campoSQL = "where cliente.complemento ";
		} else if (campo.equals("Cidade")) {
			valorSQL = "'" + valor + "'";
			campoSQL = "where cidade.nome ";
		}
		if (operador.equals("Igual")) {
			operadorSQL = "= ";
		} else if (operador.equals("Diferente")) {
			operadorSQL = "!= ";
		} else if (operador.equals("Maior")) {
			operadorSQL = "> ";
		} else if (operador.equals("Menor")) {
			operadorSQL = "< ";
		} else if (operador.equals("Contém")) {
			operadorSQL = "like";
			valorSQL = "('%" + valor + "%')";
		}
		sql += campoSQL + " " + operadorSQL + valorSQL;
		return this.load(sql);
	}
	
	public ListaObjeto searchGrafico() {
		StringBuffer sql = new StringBuffer();
		//Selecionando
		sql.append("SELECT COUNT(servico.cod_servico) AS cod_servico, cliente.nome AS nome, cliente.cod_cliente ");
		//Agrupando as tabelas
		sql.append("FROM servico INNER JOIN carro ON carro.cod_carro = servico.cod_carro INNER JOIN cliente ON cliente.cod_cliente = carro.cod_cliente ");
		//Gerando grupos
		sql.append("GROUP BY nome, cliente.cod_cliente ");
		//Ordenando
		sql.append("ORDER BY cod_servico DESC LIMIT 5 ");
		return this.load(sql.toString());
	}

}
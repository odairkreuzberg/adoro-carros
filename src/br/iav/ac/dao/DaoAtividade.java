package br.iav.ac.dao;

import br.iav.ac.database.DB;
import br.iav.ac.database.PostgreSQL;
import br.iav.ac.negocio.Atividade;
import br.iav.ac.negocio.Funcionario;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.negocio.PecaEstoque;

public class DaoAtividade implements DaoInterface {

	private DB db = PostgreSQL.create();
	
	private Atividade atividade;
	
	private final static String SELECT = "select funcionario.*, atividade.*, " +
		"funcionario.nome as f_nome, atividade.nome as a_nome from funcionario" +
		" inner join atividade on (atividade.cod_funcionario = funcionario.cod_funcionario)";
	
	private final static String SELECT_TEM_SERVICO_ATIVIDADE = "select atividade.cod_atividade from servico_atividade, atividade where servico_atividade.cod_atividade = atividade.cod_atividade and atividade.cod_atividade =  ";
	

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	@Override
	public void delete() {

		String sql = SELECT_TEM_SERVICO_ATIVIDADE + atividade.getCodigo();

		if (this.load(sql).getSize() > 0) {
			throw new RuntimeException();
		}
		if (db.connect()) {
			db.update("delete from atividade where cod_atividade = "
					+ atividade.getCodigo());
			db.disconnect();
		}

	}

	@Override
	public void edit() {		
		if(db.connect()){
			db.update("update atividade set nome = '"+ atividade.getNome() 
				+"', tipo = '"+ atividade.getTipo() 
				+"', cod_funcionario = " + atividade.getFuncionario().getCodigo() 
				+ " where cod_atividade = "+ atividade.getCodigo());			
			db.disconnect();
		}	
		
	}

	@Override
	public void insert() {		
		if(db.connect()){					
			db.update("insert into atividade (nome, tipo, " +
				"cod_funcionario)values (" 
				+"'"+ atividade.getNome() +"','" 
				+ atividade.getTipo() +"'," 
				+ atividade.getFuncionario().getCodigo()+ ")");			
			db.disconnect();
		}	
		
	}
	public ListaObjeto load(String sql) {
		ListaObjeto listaObjeto = new ListaObjeto();
		if(db.connect()){
			db.select(sql);
				while(db.moveNext()){ 
					Atividade atividade = new Atividade();
					Funcionario funcionario = new Funcionario();
					funcionario.setCodigo(db.getInt("cod_funcionario"));
					funcionario.setNome(db.getString("f_nome"));
					atividade.setCodigo(db.getInt("cod_atividade"));
					atividade.setFuncionario(funcionario);
					atividade.setNome(db.getString("a_nome"));
					atividade.setTipo(db.getString("tipo"));
					
				listaObjeto.insertWhitoutPersist(atividade);				
			}			
			db.disconnect();
		}
		return(listaObjeto);	
	}

	@Override
	public ListaObjeto load() {
		return this.load(SELECT);
	}

	public Atividade obterAtividade() {		
		if(db.connect()){
			db.select("select max(atividade.cod_atividade) as cod from atividade");
			db.moveNext();
			Atividade atividade = new Atividade();
			atividade.setCodigo(db.getInt("cod"));
			db.disconnect();
			return(atividade);
		}
		else{
			return(null);
		}	
	}

	public ListaObjeto search(String campo, String operador, String valor) {

		String sql = SELECT;

		String campoSQL = null;
		String operadorSQL = null;
		String valorSQL = null;

		if (campo.equals("Código")) {
			campoSQL = "where cod_atividade ";
			valorSQL = valor;
			if (operador.equals("Contém")) {
				operador = "Igual";
				valorSQL = "-1";
			}
		} else if (campo.equals("Atividade")) {
			valorSQL = "'" + valor + "'";
			campoSQL = "where atividade.nome ";
		} else if (campo.equals("Tipo")) {
			valorSQL = "'" + valor + "'";
			campoSQL = "where tipo ";
		} else if (campo.equals("Funcionario")) {
			valorSQL = "'" + valor + "'";
			campoSQL = " where funcionario.nome ";
		}

		if (operador.equals("Igual")) {
			operadorSQL = "=";
		} else if (operador.equals("Diferente")) {
			operadorSQL = "!=";
		} else if (operador.equals("Maior")) {
			operadorSQL = ">";
		} else if (operador.equals("Menor")) {
			operadorSQL = "<";
		} else if (operador.equals("Contém")) {
			operadorSQL = "like";
			valorSQL = " '%" + valor + "%'";
		}

		sql += campoSQL + " " + operadorSQL + valorSQL;
		return this.load(sql);
	}

	public ListaObjeto getListaPeca(int codigo) {
		String sql = "select p.cod_peca, ap.quantidade_peca, p.nome," +
			"sum(fp.quantidade) as quantidade, avg(fp.preco) as preco from" +
			" (fornecedor_peca as fp inner join peca as p on " +
			"fp.cod_peca = p.cod_peca) inner join atividade_peca as ap on " +
			"ap.cod_peca = fp.cod_peca and ap.cod_atividade = "+codigo+" " +
			"group by  p.cod_peca, p.nome, ap.quantidade_peca";
		return this.carregaListaPeca(sql);
		
	}

	private ListaObjeto carregaListaPeca(String sql) {
		ListaObjeto listaObjeto = new ListaObjeto();
		if(db.connect()){
			db.select(sql);
				while(db.moveNext()){ 
					PecaEstoque pe = new PecaEstoque();
					pe.setCodigo(db.getInt("cod_peca"));
					pe.setNome(db.getString("nome"));
					pe.setPreco(db.getFloat("preco"));
					pe.setQtdEstoque(db.getInt("quantidade"));
					pe.setQuantidade(db.getInt("quantidade_peca"));					
				listaObjeto.insertWhitoutPersist(pe);				
			}			
			db.disconnect();
		}
		return(listaObjeto);	
	}
	
	public ListaObjeto searchGrafico() {
		StringBuffer sql = new StringBuffer();
		//Selecionando
		sql.append("SELECT COUNT(atividade.cod_atividade) AS cod_atividade, atividade.nome AS a_nome ");
		//Agrupando as tabelas
		sql.append("FROM servico_atividade INNER JOIN atividade ON servico_atividade.cod_atividade = atividade.cod_atividade ");
		//Gerando grupos
		sql.append("GROUP BY a_nome ");
		//Ordenando
		sql.append("ORDER BY cod_atividade DESC LIMIT 5 ");
		return this.load(sql.toString());
	}

}

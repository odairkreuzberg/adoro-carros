package br.iav.ac.dao;

import br.iav.ac.database.DB;
import br.iav.ac.database.PostgreSQL;
import br.iav.ac.negocio.Atividade;
import br.iav.ac.negocio.Funcionario;
import br.iav.ac.negocio.ListaObjeto;

public class DaoAtividade implements DaoInterface {

	private DB db = PostgreSQL.create();
	
	private Atividade atividade;
	
	private final static String SELECT = "select funcionario.*, atividade.*, " +
		"funcionario.nome as f_nome, atividade.nome as a_nome from funcionario" +
		" inner join atividade on (atividade.cod_funcionario = funcionario.cod_funcionario)";
	

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	@Override
	public void delete() {
		if(db.connect()){			
			db.update("delete from atividade where cod_atividade = " + atividade.getCodigo());			
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
			db.select("select funcionario.*, atividade.*, funcionario.nome as " +
				"f_nome from funcionario inner join atividade on " +
				"(atividade.cod_funcionario = funcionario.cod_funcionario) " +
				"and atividade.nome = '" + atividade.getNome()+"'");
			db.moveNext();
			Atividade atividade = new Atividade();
			Funcionario funcionario = new Funcionario();
			funcionario.setCodigo(db.getInt("cod_funcionario"));
			funcionario.setNome(db.getString("f_nome"));
			atividade.setCodigo(db.getInt("cod_atividade"));
			atividade.setFuncionario(funcionario);
			atividade.setNome(db.getString("nome"));
			atividade.setTipo(db.getString("tipo"));
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
		String valorSQL = "'" + valor + "'";

		if (campo.equals("Código")) {
			campoSQL = "where cod_atividade ";
			if (operador.equals("Contem")) {
				operador = "Igual";
				valorSQL = "-1";
			}
		} else if (campo.equals("Atividade")) {
			campoSQL = "where atividade.nome ";
		} else if (campo.equals("Tipo")) {
			campoSQL = "where tipo ";
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

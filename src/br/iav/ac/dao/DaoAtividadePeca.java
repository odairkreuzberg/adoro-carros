package br.iav.ac.dao;

import br.iav.ac.database.DB;
import br.iav.ac.database.PostgreSQL;
import br.iav.ac.negocio.Atividade;
import br.iav.ac.negocio.AtividadePeca;
import br.iav.ac.negocio.Funcionario;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.negocio.Peca;

public class DaoAtividadePeca implements DaoInterface{

	private DB db = PostgreSQL.create();
	private final static String SELECT_PECAS = "select atividade_peca.quantidade_peca,peca.*, peca.nome as p_nome, atividade.* from  (atividade_peca inner join peca on atividade_peca.cod_peca = peca.cod_peca) inner join atividade on atividade_peca.cod_atividade = atividade.cod_atividade where atividade.cod_atividade = ";
	
	private AtividadePeca atividadePeca;
	

	public AtividadePeca getAtividadePeca() {
		return atividadePeca;
	}

	public void setAtividadePeca(AtividadePeca atividadePeca) {
		this.atividadePeca = atividadePeca;
	}

	@Override
	public void delete() {
		if(db.connect()){			
			db.update("delete from atividade_peca where cod_atividade = " 
				+ atividadePeca.getAtividade().getCodigo() 
				+ " and cod_peca = " + atividadePeca.getPeca().getCodigo());	
			System.out.println(atividadePeca.getAtividade().getCodigo()+ " Atividade" );		
			System.out.println(atividadePeca.getPeca().getCodigo()+ " Peca" );		
			db.disconnect();
		}		
		
	}

	@Override
	public void edit() {
		
	}

	@Override
	public void insert() {		
		if(db.connect()){					
			db.update("insert into atividade_peca (cod_atividade,cod_peca, " +
				"quantidade_peca) values (" 
				+ atividadePeca.getAtividade().getCodigo() 
				+ ","+ atividadePeca.getPeca().getCodigo()
				+ ","+ atividadePeca.getQuantidadePeca() + ")");			
			db.disconnect();
		}		
	}

	public ListaObjeto load(String sql) {
		ListaObjeto lista = new ListaObjeto();
		if (db.connect()) {
			db.select(sql);
			while (db.moveNext()) {				
				lista.insertWhitoutPersist(new AtividadePeca(
					new Peca(
						db.getInt("cod_peca"),
						db.getString("p_nome")),
					new Atividade(
						db.getInt("cod_atividade"),
						db.getString("a_nome"),
						db.getString("tipo"),
						new Funcionario()),						
					db.getInt("quantidade_peca")));
			}
			db.disconnect();
		}
		return lista;
	}

	
	public ListaObjeto load() {
		ListaObjeto listaObjeto = new ListaObjeto();
		if(db.connect()){
			db.select("select peca.cod_peca, peca.nome, atividade.cod_atividade, atividade.nome FROM atividade_peca,peca, atividade where atividade_peca.cod_peca = peca.cod_peca and atividade_peca.cod_atividade = atividade.cod_atividade ");
			while(db.moveNext()){ 
				listaObjeto.insertWhitoutPersist(new Peca(db.getInt("cod_peca"), db.getString("nome")));				
			}			
			db.disconnect();
		}
		return(listaObjeto);		
	}

	public ListaObjeto getListaPeca(int cod) {
		String sql = SELECT_PECAS + cod+""; 
		return this.load(sql);
	}

}

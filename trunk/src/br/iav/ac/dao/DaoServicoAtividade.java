package br.iav.ac.dao;

import br.iav.ac.database.DB;
import br.iav.ac.database.PostgreSQL;
import br.iav.ac.negocio.Atividade;
import br.iav.ac.negocio.AtividadePeca;
import br.iav.ac.negocio.Carro;
import br.iav.ac.negocio.Cliente;
import br.iav.ac.negocio.Cor;
import br.iav.ac.negocio.Endereco;
import br.iav.ac.negocio.Funcionario;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.negocio.Modelo;
import br.iav.ac.negocio.Peca;
import br.iav.ac.negocio.Servico;
import br.iav.ac.negocio.ServicoAtividade;
import br.iav.ac.negocio.Status;

public class DaoServicoAtividade implements DaoInterface{


	private DB db = PostgreSQL.create();
	private ServicoAtividade servicoAtividade;
	private final static String SELECT_ATIVIDADE = "select servico.*, atividade.*, servico_atividade.*,  funcionario.*, status.*," +
		" atividade.nome as a_nome, funcionario.nome as f_nome, status.nome as st_nome from " +
		" (servico_atividade inner join atividade on servico_atividade.cod_atividade = atividade.cod_atividade)" +
		" inner join funcionario on atividade.cod_funcionario = funcionario.cod_funcionario" +
		" inner join servico on servico_atividade.cod_servico = servico.cod_servico" +
		" inner join status on servico.cod_status = status.cod_status and  servico.cod_servico = ";
	

	public ServicoAtividade getServicoAtividade() {
		return servicoAtividade;
	}

	public void setServicoAtividade(ServicoAtividade servicoAtividade) {
		this.servicoAtividade = servicoAtividade;
	}

	@Override
	public void delete() {
		if(db.connect()){			
			db.update("delete from servico_atividade where cod_servico = " 
				+ servicoAtividade.getServico().getCodigo() 
				+ " and cod_atividade = " + servicoAtividade.getAtividade().getCodigo());
			db.disconnect();
		}
		
	}

	@Override
	public void edit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert() {		
		if(db.connect()){					
			db.update("insert into servico_atividade (cod_servico,cod_atividade, " +
				"preco_atividade) values (" 
				+ servicoAtividade.getServico().getCodigo() 
				+ ","+ servicoAtividade.getAtividade().getCodigo()
				+ ","+ servicoAtividade.getPrecoAtividade() + ")");			
			db.disconnect();
		}
		
	}

	public ListaObjeto load(String sql) {
		ListaObjeto lista = new ListaObjeto();
		if (db.connect()) {
			db.select(sql);
			while (db.moveNext()) {				
				ServicoAtividade sa = new ServicoAtividade();
				Servico s = new Servico();
				Atividade a = new Atividade();
				Funcionario f = new Funcionario();
				f.setNome(db.getString("f_nome"));
				f.setCodigo(db.getInt("cod_funcionario"));				
				
				a.setFuncionario(f);
				a.setCodigo(db.getInt("cod_atividade"));
				a.setNome(db.getString("a_nome"));
				a.setTipo(db.getString("tipo"));
				
				s.setCodigo(db.getInt("cod_servico"));
				s.setDataFim(db.getDate("data_fim"));
				s.setDataInicio(db.getDate("data_inicio"));
				s.setValorAtividade(db.getFloat("valor_atividade"));
				s.setValorDesconto(db.getFloat("valor_desconto"));
				s.setValorPeca(db.getFloat("valor_peca"));
				s.setValorTotal(db.getFloat("valor_total"));
				s.setStatus(new Status(db.getInt("cod_status"),db.getString("st_nome")));
				
				sa.setAtividade(a);
				sa.setServico(s);
				sa.setPrecoAtividade(db.getFloat("preco_atividade"));		
				
				lista.insertWhitoutPersist(sa);
			}
			db.disconnect();
		}
		return lista;
	}

	@Override
	public ListaObjeto load() {
		// TODO Auto-generated method stub
		return null;
	}

	public ListaObjeto getListaAtividade(int cod) {
		String sql = SELECT_ATIVIDADE + cod+""; 
		return this.load(sql);
	}

	public ListaObjeto load(int codigo) {
		ListaObjeto listaObjeto = new ListaObjeto();
		if(db.connect()){
			db.select("select atividade.cod_atividade, atividade.nome, atividade.tipo FROM servico_atividade,servico, atividade where servico_atividade.cod_servico = servico.cod_servico and servico_atividade.cod_atividade = atividade.cod_atividade");
			while(db.moveNext()){ 
				listaObjeto.insertWhitoutPersist(new Atividade(db.getInt("cod_atividade"), db.getString("nome"),db.getString("tipo"),new Funcionario()));				
			}			
			db.disconnect();
		}
		return(listaObjeto);	
	}

}

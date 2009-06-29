package br.iav.ac.dao;

import java.text.SimpleDateFormat;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import br.iav.ac.database.DB;
import br.iav.ac.database.PostgreSQL;
import br.iav.ac.negocio.Carro;
import br.iav.ac.negocio.Cliente;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.negocio.Modelo;
import br.iav.ac.negocio.Servico;
import br.iav.ac.negocio.Status;

public class DaoServico implements DaoInterface{
	
	private final static String SELECT = "select servico.*,status.*,carro.*, carro.cod_carro, carro.placa," +
			" status.nome as s_nome, servico.cod_servico, "+
			"carro.ano_fabricacao, modelo.nome as mo_nome, marca.nome as ma_nome, "+
			"cor.nome as c_nome, cliente.nome as cli_nome from(servico "+
			"inner join carro on servico.cod_carro = carro.cod_carro) "+ 
			"inner join status on servico.cod_status = status.cod_status "+
			"inner join modelo on carro.cod_modelo = modelo.cod_modelo "+
			"inner join marca on modelo.cod_marca = marca.cod_marca "+ 
			"inner join cor on carro.cod_cor = cor.cod_cor "+ 
			"inner join cliente on carro.cod_cliente = cliente.cod_cliente";
	private DB db = PostgreSQL.create();
	Servico servico = new Servico();
	

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	@Override
	public void delete() {
		if(db.connect()){			
			db.update("delete from servico where cod_servico = " + servico.getCodigo());			
			db.disconnect();
		}	
		
	}

	@Override
	public void edit() {		
		if(db.connect()){			
			SimpleDateFormat formatador = new SimpleDateFormat("dd-MM-yyyy");			
			db.update("update servico set " 
				+"valor_atividade = "+ servico.getValorAtividade() 
				+", valor_desconto = "+ servico.getValorDesconto() 
				+", valor_peca = "+ servico.getValorPeca() 
				+", valor_total = "+ servico.getValorTotal() 
				+", cod_carro = " + servico.getCarro().getCodigo() 
				+", data_fim = '" + formatador.format(servico.getDataFim()) 
				+"', data_inicio = '" + formatador.format(servico.getDataInicio()) 
				+"', cod_status = " + servico.getStatus().getCodigo() 
				+" where cod_servico = "+ servico.getCodigo());			
			db.disconnect();
		}
	}

	@Override
	public void insert() {		
		if(db.connect()){					
			SimpleDateFormat formatador = new SimpleDateFormat("dd-MM-yyyy");			
			db.update("insert into servico (valor_atividade, valor_desconto," +
				" valor_peca, valor_total, cod_carro, data_fim, data_inicio," +
				" cod_status) values (" 
				+ servico.getValorAtividade() 
				+", "+ servico.getValorDesconto() 
				+", "+ servico.getValorPeca() 
				+", "+ servico.getValorTotal() 
				+", " + servico.getCarro().getCodigo() 
				+", '" + formatador.format(servico.getDataFim()) 
				+"', '" + formatador.format(servico.getDataInicio()) 
				+"', " + servico.getStatus().getCodigo() + ")");			
			db.disconnect();
		}
		
	}
	public ListaObjeto load(String sql) {
		ListaObjeto listaObjeto = new ListaObjeto();
		if(db.connect()){
			db.select(sql);
				while(db.moveNext()){ 
					Servico servico = new Servico();
					Carro carro = new Carro();
					Status status = new Status();
					Modelo modelo = new Modelo();
					Cliente cliente = new Cliente();
					carro.setPlaca(db.getString("placa"));
					carro.setCodigo(db.getInt("cod_carro"));
					modelo.setNome(db.getString("mo_nome"));
					cliente.setNome(db.getString("cli_nome"));
					carro.setModelo(modelo);
					carro.setCliente(cliente);
					status.setNome(db.getString("s_nome"));
					status.setCodigo(db.getInt("cod_status"));
					servico.setCodigo(db.getInt("cod_servico"));
					servico.setValorAtividade(db.getFloat("valor_atividade"));
					servico.setValorDesconto(db.getFloat("valor_desconto"));
					servico.setValorPeca(db.getFloat("valor_peca"));
					servico.setValorTotal(db.getFloat("valor_total"));
					servico.setCarro(carro);
					servico.setStatus(status);	
					servico.setDataFim(db.getDate("data_inicio"));	
					servico.setDataInicio(db.getDate("data_fim"));
					listaObjeto.insertWhitoutPersist(servico);				
			}			
			db.disconnect();
		}
		return(listaObjeto);	
	}

	@Override
	public ListaObjeto load() {
		
		return load(SELECT);
	}

	public ListaObjeto search(String campo, String operador, String valor) {

		String sql = SELECT;

		String campoSQL = null;
		String operadorSQL = null;
		String valorSQL = null;
		String outroValorSQL = "";
		
		if (campo.equals("Código")) {
			campoSQL = " where cod_servico";
			valorSQL = valor;
			if (operador.equals("Contém")) {
				operador = "Igual";
				valorSQL = "-1";
			}
		}else if (campo.equals("Cliente")) {
			valorSQL = "'" + valor + "'";
			campoSQL = " where cliente.nome ";
		}else if (campo.equals("Cliente orçamento")) {
			valorSQL = "'" + valor + "'";
			campoSQL = " where cliente.nome ";
			outroValorSQL = " and status.nome = 'Orçamento'";
		}else if (campo.equals("Cliente andamento")) {
			valorSQL = "'" + valor + "'";
			campoSQL = " where cliente.nome ";
			outroValorSQL = " and status.nome = 'Em andamento'";
		}else if (campo.equals("Cliente todos")) {
			valorSQL = "'" + valor + "'";
			campoSQL = " where cliente.nome ";
			outroValorSQL = " and status.nome != 'Concluido'";
		}else if (campo.equals("Cliente Concluido")) {
			valorSQL = "'" + valor + "'";
			campoSQL = " where cliente.nome ";
			outroValorSQL = " and status.nome = 'Concluido'";
		}else if (campo.equals("Carro orçamento")) {
			valorSQL = "'" + valor + "'";
			campoSQL = " where modelo.nome ";
			outroValorSQL = " and status.nome = 'Orçamento'";
		}else if (campo.equals("Carro andamento")) {
			valorSQL = "'" + valor + "'";
			campoSQL = " where modelo.nome ";
			outroValorSQL = " and status.nome = 'Em andamento'";
		}else if (campo.equals("Carro todos")) {
			valorSQL = "'" + valor + "'";
			campoSQL = " where modelo.nome ";
			outroValorSQL = " and status.nome != 'Concluido'";
		}else if (campo.equals("Carro Concluido")) {
			valorSQL = "'" + valor + "'";
			campoSQL = " where modelo.nome ";
			outroValorSQL = " and status.nome = 'Concluido'";
		}else if (campo.equals("Carro")) {
			valorSQL = "'" + valor + "'";
			campoSQL = " where modelo.nome ";
		}else if (campo.equals("Status")) {
			valorSQL = "'" + valor + "'";
			campoSQL = " where status.nome ";
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

		sql += campoSQL + " " + operadorSQL + valorSQL + outroValorSQL;
		return this.load(sql);
	}

	public Servico obterServido() {
		if(db.connect()){
			db.select("select max(servico.cod_servico) as cod from servico");
			db.moveNext();
			Servico servico = new Servico();
			servico.setCodigo(db.getInt("cod"));
			db.disconnect();
			return(servico);
		}
		else{
			return(null);
		}
	}

	public JasperPrint gerarRelatorioServicoAtrasado(){
		try{
			DB db = PostgreSQL.create();
			if(db.connect()){
				JasperPrint print = JasperFillManager.fillReport("relServicoAtrasado.jasper",null,db.getConnection());
				db.disconnect();
				return print;
			}
		}catch(JRException e){
			e.printStackTrace();
		}
		return null;
	}
}

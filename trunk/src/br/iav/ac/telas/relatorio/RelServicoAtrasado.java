package br.iav.ac.telas.relatorio;

import java.util.HashMap;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import br.iav.ac.database.DB;
import br.iav.ac.database.PostgreSQL;
import br.iav.ac.negocio.Servico;

public class RelServicoAtrasado {
	private Servico servico;
	
	public RelServicoAtrasado(){
		servico = new Servico();
	}
	
	public void exibirRelatorio(){
		try{
			DB db = PostgreSQL.create();
			if(db.connect()){
				JasperPrint print = JasperFillManager.fillReport("relServicoAtrasado.jasper",null,db.getConnection());
				JasperViewer.viewReport(print,false);
				db.disconnect();
			}
		}catch(JRException e){
			e.printStackTrace();
		}
	}
}

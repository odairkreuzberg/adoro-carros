package br.iav.ac.telas.relatorio;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import br.iav.ac.negocio.Servico;

public class RelServicoAtrasado {
	private Servico servico;
	
	public RelServicoAtrasado(){
		servico = new Servico();
	}
	
	public void exibirRelatorio(){
		JasperPrint print = servico.gerarRelatorioServicoAtrasado();
		JasperViewer.viewReport(print,false);
	}
}

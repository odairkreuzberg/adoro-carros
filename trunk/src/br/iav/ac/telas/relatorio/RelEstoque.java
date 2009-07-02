package br.iav.ac.telas.relatorio;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import br.iav.ac.negocio.FornecedorPeca;

public class RelEstoque {
	private FornecedorPeca fornecedorPeca;
	
	public RelEstoque(){
		fornecedorPeca = new FornecedorPeca();
	}
	
	public void exibirRelatorio(){
		JasperPrint print = fornecedorPeca.gerarRelatorioPecas();
		JasperViewer.viewReport(print,false);
	}	

}


package br.iav.ac.telas.relatorio;

import java.awt.Color;
import java.awt.GradientPaint;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import br.iav.ac.negocio.Carro;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.telas.padrao.RelPadrao;

public class RelCarro extends RelPadrao {

	private ArrayList dados;
	
	public RelCarro() {
		dados = this.gerarDados();
		try {
			this.add(gerarGrafico("Relat�rio de Carros", "Carros", "Quantidade"));
		} catch (Exception e) {
			
		}
	}

	public ChartPanel gerarGrafico(String tituloGrafico,
								   String tituloEixoX,
								   String tituloEixoY) throws Exception {
		ChartPanel chartPanel = null;
		try {
			DefaultCategoryDataset defaultCategoryDataset = new DefaultCategoryDataset(); //Dataset necess�rio para gerar o gr�fico
			for (int i = 0; i < dados.size(); i++) {
				Carro carro = (Carro) dados.get(i); //Pega o dado na posi��o 'i' para adicionar ao Dataset
				if (carro != null) {
					if (carro.getCodigo() != 0 &&
						!carro.getModelo().getNome().equals("") &&
						!carro.getModelo().getMarca().getNome().equals("")) {
						//Adicionando os dados no Dataset
						defaultCategoryDataset.addValue(carro.getCodigo(), //passando o c�digo do carro 
														"Quantidade", //Deixando o label "Quantidade" para ele n�o fazer compara��es
														carro.getModelo().getMarca().getNome() + " " + carro.getModelo().getNome()); //Colocando o nome do dado Ex: "Renault Clio"
					}
				}
			}
			//Criando o Gr�fico
			JFreeChart chart = ChartFactory.createBarChart(tituloGrafico,				//T�tulo do Gr�fico
												  		   tituloEixoX,					//T�tulo no Eixo X
												  		   tituloEixoY,					//T�tulo no Eixo Y
												  		   defaultCategoryDataset,		//Dataset com os dados do gr�fico
												  		   PlotOrientation.VERTICAL,	//Orienta��o do Gr�fico na Vertical
												  		   false,						//Legendas?
												  		   false,						//Tooltips?
												  		   false);						//URLs?
			chart.setBackgroundPaint(new Color(205, 205, 205)); //Colocando a cor do fundo
			CategoryPlot plot = chart.getCategoryPlot();
			CategoryAxis domainAxis = plot.getDomainAxis();
			domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); //Deixando os labels em 45�
			NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
			rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits()); //Deixa aparecer apenas unidades inteiras Ex: 0, 1, 2.
			BarRenderer renderer = (BarRenderer) plot.getRenderer();
			GradientPaint corDaBarra = new GradientPaint(0, 0, Color.blue, 0, 0, Color.black); //Define uma cor para colocar nas barras
			renderer.setSeriesPaint(0, corDaBarra); //Coloca a cor na barra 0
			chartPanel = new ChartPanel(chart); //Cria um panel passando o gr�fico que foi criado
			chartPanel.setPreferredSize(new java.awt.Dimension(549, 550)); //Cria o espa�amento para o gr�fico
		} catch (Exception e) {
			JOptionPane.showMessageDialog(RelCarro.this, "Erro na constru��o do gr�fico: Relat�rio de Carros!");
		}
		return chartPanel;
	}
	
	private ArrayList gerarDados() {
		Carro carro = new Carro();
		ArrayList arrayList = new ArrayList();
		ListaObjeto listaObjeto = carro.searchGrafico();
		if (listaObjeto.getSize() != 0) {
			for (int i = 0; i <= listaObjeto.getSize(); i++) {
				arrayList.add(listaObjeto.getObjeto(i));
			}
		}
		return arrayList;
	}

}
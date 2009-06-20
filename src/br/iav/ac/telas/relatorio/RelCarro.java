package br.iav.ac.telas.relatorio;

import java.awt.Color;
import java.awt.GradientPaint;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
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
		//super();
		dados = this.gerarDados();
		try {
			this.add(gerarGrafico("Relatório de Carros Cadastrados", "Carros", "Quantidade"));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(RelCarro.this, "Erro na construção do gráfico: Relatório de Carros!");
		}
	}

	public ChartPanel gerarGrafico(String tituloGrafico,
								   String tituloEixoX,
								   String tituloEixoY) throws Exception {
		ChartPanel chartPanel = null;
		try {
			DefaultCategoryDataset defaultCategoryDataset = new DefaultCategoryDataset();
			for (int i = 0; i < dados.size(); i++) {
				Carro carro = (Carro) dados.get(i);
				if (carro != null) {
					defaultCategoryDataset.addValue(gerarQuantidade(carro),
													"Quantidade",
													carro.getModelo().getMarca().getNome() + "\n" + carro.getModelo().getNome());
				}
			}
			JFreeChart chart = ChartFactory.createBarChart(tituloGrafico,
												  tituloEixoX,
												  tituloEixoY,
												  defaultCategoryDataset,
												  PlotOrientation.VERTICAL,
												  false,
												  false,
												  false);
			chart.setBackgroundPaint(new Color(205, 205, 205));
			CategoryPlot plot = chart.getCategoryPlot();
			NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
			rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			BarRenderer renderer = (BarRenderer) plot.getRenderer();
			renderer.setMaximumBarWidth(10);
			renderer.setDrawBarOutline(false);
			GradientPaint corDaBarra = new GradientPaint(0.0f, 0.0f, Color.blue, 0.0f, 0.0f, Color.black);
			renderer.setSeriesPaint(0, corDaBarra);
			chartPanel = new ChartPanel(chart);
			chartPanel.setPreferredSize(new java.awt.Dimension(549, 550));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(RelCarro.this, "Erro na construção do gráfico: Relatório de Carros!");
		}
		return chartPanel;
	}
	
	private ArrayList gerarDados() {
		Carro carro = new Carro();
		ArrayList arrayList = new ArrayList();
		ListaObjeto listaObjeto = carro.load();
		for (int i = 0; i <= listaObjeto.getSize(); i++) {
			arrayList.add(listaObjeto.getObjeto(i));
		}
		return arrayList;
	}

	private int gerarQuantidade(Carro carro) {
		ListaObjeto listaObjeto = carro.search("Modelo", "Igual", carro.getModelo().getNome());
		return listaObjeto.getSize();
	}

}
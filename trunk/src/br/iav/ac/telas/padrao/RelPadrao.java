package br.iav.ac.telas.padrao;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class RelPadrao extends JPanel {

	/**
	* Creates a new demo instance.
	*
	* @param title the frame title.
	*/
	public RelPadrao() {
		super();
		CategoryDataset dataset = createDataset();
		JFreeChart chart = createChart(dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(549, 553));
		this.add(chartPanel);
		this.setSize(549, 553);
	}

	/**
	* Retorna os dados que serão passados para o gráfico.
	*
	* @return Os dados do gráfico.
	*/
	private CategoryDataset createDataset() {
		// row keys...
		String carro = "Modelo";
		// column keys...
		String modelos[] = { "Honda", "Renault", "Fiat", "Chevrolet", "Nissan" };
		// create the dataset...
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(543, carro, modelos[0]);
		dataset.addValue(367, carro, modelos[1]);
		dataset.addValue(489, carro, modelos[2]);
		dataset.addValue(321, carro, modelos[3]);
		dataset.addValue(186, carro, modelos[4]);
		return dataset;
	}
	
	/**
	* Cria um gráfico.
	*
	* @param dataset the dataset.
	*
	* @return Um Gráfico.
	*/
	private JFreeChart createChart(CategoryDataset dataset) {
		// create the chart...
		JFreeChart chart = ChartFactory.createBarChart(
			"Relatório de Carros", // chart title
			"Modelo", // domain axis label
			"Quantidade", // range axis label
			dataset, // data
			PlotOrientation.VERTICAL,
			false, // include legend
			false, // tooltips?
			false // URLs?
		);
		// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
		// set the background color for the chart...
		chart.setBackgroundPaint(new Color(210, 210, 210));
		// get a reference to the plot for further customisation...
		CategoryPlot plot = chart.getCategoryPlot();
		// set the range axis to display integers only...
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		// disable bar outlines...
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setDrawBarOutline(false);
		// set up gradient paints for series...
		GradientPaint corDaBarra = new GradientPaint(0.0f, 0.0f, Color.blue, 0.0f, 0.0f, Color.black);
		renderer.setSeriesPaint(0, corDaBarra);
		// OPTIONAL CUSTOMISATION COMPLETED.
		return chart;
	}
	
}
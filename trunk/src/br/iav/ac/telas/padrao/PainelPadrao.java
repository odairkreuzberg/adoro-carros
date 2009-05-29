package br.iav.ac.telas.padrao;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PainelPadrao extends JPanel {

	private JButton botaoNovo;
	private JButton botaoExcluir;
	private JButton botaoEditar;
	private JButton botaoBuscar;
	private JButton botaoAtualizar;
	private JTable gridTabela;
	private JScrollPane scrollTabela;
	private JTextField textBuscar;
	private JComboBox comboTipoBuscar; //Contem, Igual, Diferente, Maior, Menor
	private JComboBox comboAtributoBuscar; //Atributos da classe para buscar
	private JLabel labelFiltro;
	private String[] camposClasse; 

	public PainelPadrao(String[] campos) {
		this.camposClasse = campos;
		this.setSize(549, 553);
		this.setLayout(null);
		try {
			{
				botaoNovo = new JButton();
				this.add(botaoNovo);
				botaoNovo.setText("Novo");
				botaoNovo.setBounds(12, 11, 119, 27);
			}
			{
				botaoEditar = new JButton();
				this.add(botaoEditar);
				botaoEditar.setText("Editar");
				botaoEditar.setBounds(150, 11, 116, 27);
			}
			{
				botaoExcluir = new JButton();
				this.add(botaoExcluir);
				botaoExcluir.setText("Excluir");
				botaoExcluir.setBounds(287, 11, 113, 27);
			}
			{
				botaoBuscar = new JButton();
				this.add(botaoBuscar);
				botaoBuscar.setText("Buscar");
				botaoBuscar.setBounds(457, 49, 75, 24);
			}
			{
				botaoAtualizar = new JButton();
				this.add(botaoAtualizar);
				botaoAtualizar.setText("Atualizar");
				botaoAtualizar.setBounds(419, 11, 113, 27);
			}
			{
				textBuscar = new JTextField();
				this.add(textBuscar);
				textBuscar.setBounds(298, 50, 154, 21);
			}
			{
				labelFiltro = new JLabel();
				this.add(labelFiltro);
				labelFiltro.setText("Filtrar Por:");
				labelFiltro.setBounds(12, 53, 51, 14);
			}
			{
				ComboBoxModel comboOperadorModel = new DefaultComboBoxModel(new String[] { "Contem", "Igual", "Diferente", "Maior",	"Menor" });
				comboTipoBuscar = new JComboBox();
				this.add(comboTipoBuscar);
				comboTipoBuscar.setModel(comboOperadorModel);
				comboTipoBuscar.setBounds(190, 47, 96, 27);
			}
			{
				ComboBoxModel coboCampoModel = new DefaultComboBoxModel(camposClasse);
				comboAtributoBuscar = new JComboBox();
				this.add(comboAtributoBuscar);
				comboAtributoBuscar.setModel(coboCampoModel);
				comboAtributoBuscar.setBounds(75, 47, 96, 27);
			}
			{
				DefaultTableModel model = new DefaultTableModel(new Object[0][0], camposClasse);
				gridTabela = new JTable(model);
				gridTabela.setShowVerticalLines(true);
				scrollTabela = new JScrollPane();
				scrollTabela.setHorizontalScrollBar(new JScrollBar(0));
				scrollTabela.setViewportView(gridTabela);
				gridTabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				this.add(scrollTabela);
				scrollTabela.setBounds(12, 87, 525, 454);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JLabel getLabelFiltro() {
		return labelFiltro;
	}

	public JComboBox getComboCampo() {
		return comboAtributoBuscar;
	}

	public JComboBox getComboOperador() {
		return comboTipoBuscar;
	}

	public JTextField getTextValor() {
		return textBuscar;
	}

	public JButton getBotaoBuscar() {
		return botaoBuscar;
	}

	public JButton getBotaoAtualizar() {
		return botaoAtualizar;
	}
	
	public JButton getBotaoNovo() {
		return botaoNovo;
	}

	public JButton getBotaoExcluir() {
		return botaoExcluir;
	}

	public JButton getBotaoEditar() {
		return botaoEditar;
	}

	public JTextField getTextBuscar() {
		return textBuscar;
	}

	public void setTextValor(JTextField textBuscar) {
		this.textBuscar = textBuscar;
	}

	public JComboBox getComboTipoBuscar() {
		return comboTipoBuscar;
	}

	public void setComboOperador(JComboBox comboTipoBuscar) {
		this.comboTipoBuscar = comboTipoBuscar;
	}

	public JComboBox getComboAtributoBuscar() {
		return comboAtributoBuscar;
	}

	public void setComboAtributoBuscar(JComboBox comboAtributoBuscar) {
		this.comboAtributoBuscar = comboAtributoBuscar;
	}

	public JTable getGridTabela() {
		return gridTabela;
	}

}
package br.iav.ac.telas.estoque;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.iav.ac.negocio.FornecedorPeca;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.negocio.PecaDetalhe;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
/**
 * Formulário de Compra
 * 
 * @author Odair Kreuzberg
 */
public class DialogoDetalhe extends JDialog {

	/*----------------------------------------------------------
	 * ATTRIBUTOS
	 *----------------------------------------------------------*/

	private JLabel labelPeca;
	private JLabel labelValor;
	private JTextField textQtdTotal;
	private JTextField textPeca;
	private JTable gridTabela;
	private JScrollPane scrollTabela;
	private String cod;

	/*----------------------------------------------------------
	 * FIM DE ATTRIBUTOS
	 *----------------------------------------------------------*/

	/*----------------------------------------------------------
	 * CONSTRUTOR
	 *----------------------------------------------------------*/

	public DialogoDetalhe(JFrame frame, String titulo, boolean modal, String cod) {
		super(frame, titulo, modal);
		this.cod = cod;
		getContentPane().setLayout(null);
		{
			textQtdTotal = new JTextField();
			getContentPane().add(textQtdTotal);
			textQtdTotal.setBounds(75, 39, 256, 21);
			textQtdTotal.setEditable(false);
		}
		{
			labelValor = new JLabel();
			getContentPane().add(labelValor);
			labelValor.setText("Qtd. Total:");
			labelValor.setBounds(12, 42, 63, 14);
		}
		{
			labelPeca = new JLabel();
			getContentPane().add(labelPeca);
			labelPeca.setText("Peça:");
			labelPeca.setBounds(12, 15, 60, 14);
		}
		{
			textPeca = new JTextField();
			getContentPane().add(textPeca);
			textPeca.setBounds(75, 12, 256, 21);
			textPeca.setEditable(false);
		}
		{
			gridTabela = new JTable();
			gridTabela.setShowVerticalLines(true);
			scrollTabela = new JScrollPane();
			scrollTabela.setHorizontalScrollBar(new JScrollBar(0));
			scrollTabela.setViewportView(gridTabela);
			this.add(scrollTabela);
			scrollTabela.setBounds(12, 72, 319, 285);
		}
		this.carregarGrid();
		this.setSize(351, 396);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/*----------------------------------------------------------
	 * FIM DE CONSTRUTOR
	 *----------------------------------------------------------*/

	/*----------------------------------------------------------
	 * METODOS DA CLASSE
	 *----------------------------------------------------------*/
	

	
	
	/**
	 * Carrega a Grid.
	 */
	private void carregarGrid() {
		FornecedorPeca fornecedorPeca = new FornecedorPeca();
		ListaObjeto listaObjeto = fornecedorPeca.getListaPecaFornecedor(cod);
		PecaDetalhe pecaDetalhe = new PecaDetalhe();
		Object[][] gridArray = new Object[listaObjeto.getSize()][4];
		int qtd = 0;
		for (int i = 0; i < listaObjeto.getSize(); i++) {
			pecaDetalhe = (PecaDetalhe) listaObjeto.getObjeto(i);
			gridArray[i][0] = pecaDetalhe.getCodigo();
			gridArray[i][1] = pecaDetalhe.getFornecedor();
			gridArray[i][2] = pecaDetalhe.getQtd();
			qtd = qtd + pecaDetalhe.getQtd();
			gridArray[i][3] = pecaDetalhe.getPreco();
		}
		String[] campos = { "Codigo", "Fornecedor", "Quantidade", "Preço" };
		DefaultTableModel model = new DefaultTableModel(gridArray, campos);
		gridTabela.setModel(model);
		gridTabela.setShowVerticalLines(true);
		gridTabela.getColumnModel().getColumn(0).setPreferredWidth(50);
		gridTabela.getColumnModel().getColumn(1).setPreferredWidth(135);
		gridTabela.getColumnModel().getColumn(2).setPreferredWidth(70);
		gridTabela.getColumnModel().getColumn(3).setPreferredWidth(50);
		
		textPeca.setText(pecaDetalhe.getPeca());
		textQtdTotal.setText(qtd+"");
		
		
	}
	
	
	

	/*----------------------------------------------------------
	 * FIM DE METODOS DA CLASSE
	 *----------------------------------------------------------*/

}
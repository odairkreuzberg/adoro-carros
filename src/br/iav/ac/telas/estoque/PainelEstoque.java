package br.iav.ac.telas.estoque;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.iav.ac.negocio.FornecedorPeca;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.negocio.PecaEstoque;
import br.iav.ac.telas.TelaPrincipal;

/*
 */

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
public class PainelEstoque extends JPanel {

	private JButton botaoCompra;
	private JButton botaoDetalhe;
	private JTable gridTabela;
	private JLabel labelAviso;
	private JScrollPane scrollTabela;
	private EstoqueHandler estoqueHandler;
	private JButton botaoAtulisar;

	public PainelEstoque() {
		this.setSize(549, 553);
		this.setLayout(null);
		try {
			{
				botaoCompra = new JButton();
				this.add(botaoCompra);
				botaoCompra.setText("Comprar");
				botaoCompra.setBounds(12, 12, 119, 27);
			}
			{
				botaoDetalhe = new JButton();
				this.add(botaoDetalhe);
				botaoDetalhe.setText("Detalhe");
				botaoDetalhe.setBounds(142, 12, 116, 27);
			}
			{
				gridTabela = new JTable();
				gridTabela.setShowVerticalLines(true);
				scrollTabela = new JScrollPane();
				scrollTabela.setHorizontalScrollBar(new JScrollBar(0));
				scrollTabela.setViewportView(gridTabela);
				gridTabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				this.add(scrollTabela);
				scrollTabela.setBounds(12, 50, 525, 491);
			}
			{
				labelAviso = new JLabel();
				this.add(labelAviso);
				labelAviso
						.setText("Para Abrira a Janela de Detalhe � preciso selecionar um item na Tabela!");
				labelAviso.setBounds(12, 154, 525, 14);
				labelAviso.setFont(new java.awt.Font("Tahoma", 1, 11));
				labelAviso.setVisible(false);
			}
			{
				botaoAtulisar = new JButton();
				this.add(botaoAtulisar);
				botaoAtulisar.setText("Atualizar");
				botaoAtulisar.setBounds(269, 12, 116, 23);
				botaoAtulisar.setSize(116, 27);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		inicializarHandlers();
	} /*----------------------------------------------------------
	 * METODOS DA CLASSE
	 *----------------------------------------------------------*/

	private void inicializarHandlers() {
		this.estoqueHandler = new EstoqueHandler();
		this.botaoCompra.addActionListener(estoqueHandler);
		this.botaoDetalhe.addActionListener(estoqueHandler);
		this.botaoAtulisar.addActionListener(estoqueHandler);
	}

	/*----------------------------------------------------------
	 * FIM DE METODOS DA CLASSE
	 *----------------------------------------------------------*/

	/*----------------------------------------------------------
	 * CLASSE LIMITROFE
	 *----------------------------------------------------------*/

	class EstoqueHandler implements ActionListener {
		private FornecedorPeca fornecedorPeca;

		public EstoqueHandler() {
			super();
			fornecedorPeca = new FornecedorPeca();
			carregarGrid(fornecedorPeca.getPecas());
		}

		/**
		 * Carrega a Grid.
		 */
		private void carregarGrid(ListaObjeto listaObjeto) {
			Object[][] gridArray = new Object[listaObjeto.getSize()][4];
			for (int i = 0; i < listaObjeto.getSize(); i++) {
				PecaEstoque pecaEstoque = (PecaEstoque) listaObjeto
						.getObjeto(i);
				gridArray[i][0] = pecaEstoque.getCodigo();
				gridArray[i][1] = pecaEstoque.getNome();
				gridArray[i][2] = pecaEstoque.getQuantidade();
				gridArray[i][3] = pecaEstoque.getPreco();
			}
			String[] campos = { "Codigo", "Pe�a", "Quantidade", "Pre�o" };
			DefaultTableModel model = new DefaultTableModel(gridArray, campos);
			gridTabela.setModel(model);
			gridTabela.setShowVerticalLines(true);
			// Defini��o do tamanho das colunas da grid
			// TAMANHO DA GRID: 521
			gridTabela.getColumnModel().getColumn(0).setPreferredWidth(50);
			gridTabela.getColumnModel().getColumn(1).setPreferredWidth(271);
			gridTabela.getColumnModel().getColumn(2).setPreferredWidth(100);
			gridTabela.getColumnModel().getColumn(3).setPreferredWidth(100);
		}

		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == botaoCompra) {
				new DialogoFornecedorPeca(TelaPrincipal.instancia,
						"Aquisi��o de Material", true);
				carregarGrid(fornecedorPeca.getPecas());
			}
			else if (e.getSource() == botaoDetalhe) {
				if (gridTabela.getSelectedRow() >= 0) {
					labelAviso.setVisible(false);
					String cod = gridTabela.getValueAt(gridTabela
							.getSelectedRow(), 0)
							+ "";
					new DialogoDetalhe(TelaPrincipal.instancia, "Detalhes",
							true, cod);
				} else {
					labelAviso.setVisible(true);

				}

			}
			else if (e.getSource() == botaoAtulisar) {
				carregarGrid(fornecedorPeca.getPecas());				
			}
		}
	}

	/*----------------------------------------------------------
	 * FIM DE CLASSE LIMITROFE
	 *----------------------------------------------------------*/

}
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
public class PainelEstoque extends JPanel {

	private JButton botaoCompra;
	private JButton botaoDetalhe;
	private JTable gridTabela;
	private JLabel labelEstoque;
	private JLabel labelAviso;
	private JScrollPane scrollTabela;
	private EstoqueHandler estoqueHandler;

	public PainelEstoque() {
		this.setSize(549, 553);
		this.setLayout(null);
		try {
			{
				botaoCompra = new JButton();
				this.add(botaoCompra);
				botaoCompra.setText("Comprar");
				botaoCompra.setBounds(12, 127, 119, 27);
			}
			{
				botaoDetalhe = new JButton();
				this.add(botaoDetalhe);
				botaoDetalhe.setText("Detalhe");
				botaoDetalhe.setBounds(136, 127, 116, 27);
			}
			{
				gridTabela = new JTable();
				gridTabela.setShowVerticalLines(true);
				scrollTabela = new JScrollPane();
				scrollTabela.setHorizontalScrollBar(new JScrollBar(0));
				scrollTabela.setViewportView(gridTabela);
				gridTabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				this.add(scrollTabela);
				scrollTabela.setBounds(12, 171, 525, 370);
			}
			{
				labelEstoque = new JLabel();
				this.add(labelEstoque);
				labelEstoque.setText("Estoque");
				labelEstoque.setBounds(12, 12, 525, 89);
				labelEstoque.setEnabled(false);
				labelEstoque.setFont(new java.awt.Font("Tahoma", 1, 72));
			}
			{
				labelAviso = new JLabel();
				this.add(labelAviso);
				labelAviso
						.setText("Para Abrira a Janela de Detalhe é preciso selecionar um item na Tabela!");
				labelAviso.setBounds(12, 154, 525, 14);
				labelAviso.setFont(new java.awt.Font("Tahoma", 1, 11));
				labelAviso.setVisible(false);
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
				gridArray[i][2] = pecaEstoque.getQunatidade();
				gridArray[i][3] = pecaEstoque.getPreco();
			}
			String[] campos = { "Codigo", "Peça", "Quantidade", "Preço" };
			DefaultTableModel model = new DefaultTableModel(gridArray, campos);
			gridTabela.setModel(model);
			gridTabela.setShowVerticalLines(true);
			// Definição do tamanho das colunas da grid
			// TAMANHO DA GRID: 521
			gridTabela.getColumnModel().getColumn(0).setPreferredWidth(50);
			gridTabela.getColumnModel().getColumn(1).setPreferredWidth(271);
			gridTabela.getColumnModel().getColumn(2).setPreferredWidth(100);
			gridTabela.getColumnModel().getColumn(3).setPreferredWidth(100);
		}

		public void actionPerformed(ActionEvent e) {
			/**
			 * 
			 **/
			if (e.getSource() == botaoCompra) {
				new DialogoFornecedorPeca(TelaPrincipal.instancia,
						"Compra de Peça", true);
				carregarGrid(fornecedorPeca.getPecas());

			}
			/**
			 * 
			 */
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
		}
	}

	/*----------------------------------------------------------
	 * FIM DE CLASSE LIMITROFE
	 *----------------------------------------------------------*/

}
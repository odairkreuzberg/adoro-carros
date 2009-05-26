package br.iav.ac.telas.cor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import br.iav.ac.negocio.Cor;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.telas.TelaPrincipal;
import br.iav.ac.telas.core.PainelGrid;

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
 * Area de Consulta, Inserção, Exclusão e alteração de Cor de um Veículo
 * 
 * @author Odair Kreuzberg
 */
public class CorGrid extends PainelGrid {

	/*----------------------------------------------------------
	 * ATTRIBUTOS
	 *----------------------------------------------------------*/

	private static final long serialVersionUID = 1L;
	private JButton botaoNovo;
	private JTable gridCor;
	private JButton botaorExcluir;
	private JButton botaoEditar;
	private JScrollPane scrolCor;
	private CadastroHandle cadastroHandle;
	private JButton botaoBuscar;
	private JButton botaoAtualizar;
	private JTextField textValor;
	private JComboBox comboOperador;
	private JComboBox coboCampo;
	private JLabel labelFiltro;
	private Cor cor;

	/*----------------------------------------------------------
	 * FIM DE ATTRIBUTOS
	 *----------------------------------------------------------*/

	/*----------------------------------------------------------
	 * CONSTRUTOR
	 *----------------------------------------------------------*/

	public CorGrid() {
		super();
		initGUI();
		inicializarHandlers();
	}

	/*----------------------------------------------------------
	 * FIM DE CONSTRUTOR
	 *----------------------------------------------------------*/

	/*----------------------------------------------------------
	 * INTERFACE
	 *----------------------------------------------------------*/

	private void inicializarHandlers() {

		this.cadastroHandle = new CadastroHandle();

		botaoNovo.addActionListener(cadastroHandle);

		botaoEditar.addActionListener(cadastroHandle);

		botaorExcluir.addActionListener(cadastroHandle);

		botaoBuscar.addActionListener(cadastroHandle);

		botaoAtualizar.addActionListener(cadastroHandle);
	}

	@Override
	public JTable getGrid() {
		return this.gridCor;
	}

	private void initGUI() {
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
				botaorExcluir = new JButton();
				this.add(botaorExcluir);
				botaorExcluir.setText("Excluir");
				botaorExcluir.setBounds(287, 11, 113, 27);
			}
			{
				String col[] = { "ID", "Cor" };
				DefaultTableModel model = new DefaultTableModel(
						new Object[0][0], col);

				gridCor = new JTable(model);

				gridCor.setShowVerticalLines(true);
				TableColumn column = gridCor.getColumnModel().getColumn(0);
				column.setPreferredWidth(100);
				column = gridCor.getColumnModel().getColumn(1);
				column.setPreferredWidth(500);
				scrolCor = new JScrollPane();
				scrolCor.setViewportView(gridCor);
				this.add(scrolCor);
				this.add(getLabelFiltro());
				this.add(getCoboCampo());
				this.add(getComboOperador());
				this.add(getTextValor());
				this.add(getBotaoBuscar());
				this.add(getBotaoAtualizar());
				scrolCor.setBounds(12, 87, 525, 454);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JLabel getLabelFiltro() {
		if (labelFiltro == null) {
			labelFiltro = new JLabel();
			labelFiltro.setText("Filtrar Por:");
			labelFiltro.setBounds(12, 53, 51, 14);
		}
		return labelFiltro;
	}

	private JComboBox getCoboCampo() {
		if (coboCampo == null) {
			ComboBoxModel coboCampoModel = new DefaultComboBoxModel(
					new String[] { "cor", "Código" });
			coboCampo = new JComboBox();
			coboCampo.setModel(coboCampoModel);
			coboCampo.setBounds(75, 47, 96, 27);
		}
		return coboCampo;
	}

	private JComboBox getComboOperador() {
		if (comboOperador == null) {
			ComboBoxModel comboOperadorModel = new DefaultComboBoxModel(
					new String[] { "Contem", "Igual", "Diferente", "Maior",
							"Menor" });
			comboOperador = new JComboBox();
			comboOperador.setModel(comboOperadorModel);
			comboOperador.setBounds(190, 47, 96, 27);
		}
		return comboOperador;
	}

	private JTextField getTextValor() {
		if (textValor == null) {
			textValor = new JTextField();
			textValor.setBounds(298, 50, 154, 21);
		}
		return textValor;
	}

	private JButton getBotaoBuscar() {
		if (botaoBuscar == null) {
			botaoBuscar = new JButton();
			botaoBuscar.setText("Buscar");
			botaoBuscar.setBounds(457, 49, 75, 24);
		}
		return botaoBuscar;
	}

	private JButton getBotaoAtualizar() {
		if (botaoAtualizar == null) {
			botaoAtualizar = new JButton();
			botaoAtualizar.setText("Atualizar");
			botaoAtualizar.setBounds(419, 11, 113, 27);
		}
		return botaoAtualizar;
	}

	/*----------------------------------------------------------
	 * FIM DE INTERFACE
	 *----------------------------------------------------------*/

	/*----------------------------------------------------------
	 * CLASSE LIMITROFE
	 *----------------------------------------------------------*/

	class CadastroHandle implements ActionListener {

		public CadastroHandle() {
			super();

			carregarGrid(getCor().load());
		}

		private Cor getCor() {
			if (cor == null) {
				cor = new Cor();
			}
			return cor;
		}

		private void carregarGrid(ListaObjeto listaObjeto) {

			Object[][] gridArray = new Object[listaObjeto.getSize()][2];

			for (int i = 0; i < listaObjeto.getSize(); i++) {

				Cor cor = (Cor) listaObjeto.getObjeto(i);

				gridArray[i][0] = cor.getCodigo();
				gridArray[i][1] = cor.getNome();
			}

			String col[] = { "ID", "Cor" };

			DefaultTableModel model = new DefaultTableModel(gridArray, col);

			gridCor.setModel(model);
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == botaoNovo) {
				
				new CorForm(TelaPrincipal.instancia,"Cadastro de Cor", true, this.getCor());
				carregarGrid(getCor().load());
				
			} else if (e.getSource() == botaoEditar) {
				
				if (gridCor.getSelectedRow() > 0) {
					
					this.getCor().setCodigo((Integer) gridCor.getValueAt(gridCor.getSelectedRow(), 0));
					this.getCor().setNome(gridCor.getValueAt(gridCor.getSelectedRow(), 1)+ "");
					new CorForm(null, "Cadastro de Cor", true, this.getCor());
					carregarGrid(getCor().load());
					
				} else {
					JOptionPane.showMessageDialog(CorGrid.this,
							"Para Editar é preciso Selecionar uma cor na Grid");
				}

			} else if (e.getSource() == botaorExcluir) {
				if (gridCor.getSelectedRow() > 0) {
					if (JOptionPane.showConfirmDialog(null,"Deseja mesmo Excluir a cor "
							+ gridCor.getValueAt(gridCor.getSelectedRow(), 1) + " ?",
							"Exclusão", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						
						getCor().setCodigo((Integer) gridCor.getValueAt(gridCor.getSelectedRow(), 0));
						getCor().delete();
						carregarGrid(getCor().load());
						
					}
				} else {
					JOptionPane.showMessageDialog(CorGrid.this,
									"Para Remover é preciso Selecionar uma cor na Grid");
				}

			} else if (e.getSource() == botaoAtualizar) {
				
				carregarGrid(getCor().load());

			} else if (e.getSource() == botaoBuscar) {
				
				carregarGrid(getCor().buscar(
						(String) coboCampo.getSelectedItem(),
						(String) comboOperador.getSelectedItem(),
						textValor.getText()));

			}
		}
	}

	/*----------------------------------------------------------
	 * FIM DE CLASSE LIMITROFE
	 *----------------------------------------------------------*/

}

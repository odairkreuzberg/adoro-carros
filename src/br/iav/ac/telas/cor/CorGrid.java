package br.iav.ac.telas.cor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import br.iav.ac.negocio.Cor;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.telas.TelaPrincipal;
import br.iav.ac.telas.core.PainelPadrao;

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
public class CorGrid extends PainelPadrao {

	/*----------------------------------------------------------
	 * ATTRIBUTOS
	 *----------------------------------------------------------*/

	private static final long serialVersionUID = 1L;
	private CadastroHandle cadastroHandle;
	private Cor cor;
	private static String[] CAMPOS = { "Código", "Cor" };

	/*----------------------------------------------------------
	 * FIM DE ATTRIBUTOS
	 *----------------------------------------------------------*/

	/*----------------------------------------------------------
	 * CONSTRUTOR
	 *----------------------------------------------------------*/

	public CorGrid() {
		super(CAMPOS);
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
		this.getBotaoNovo().addActionListener(cadastroHandle);
		this.getBotaoEditar().addActionListener(cadastroHandle);
		this.getBotaoExcluir().addActionListener(cadastroHandle);
		this.getBotaoBuscar().addActionListener(cadastroHandle);
		this.getBotaoAtualizar().addActionListener(cadastroHandle);
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
			DefaultTableModel model = new DefaultTableModel(gridArray, CAMPOS);
			getGridTabela().setModel(model);
			getGridTabela().setShowVerticalLines(true);
			TableColumn column = getGridTabela().getColumnModel().getColumn(0);
			column.setPreferredWidth(100);
			column = getGridTabela().getColumnModel().getColumn(1);
			column.setPreferredWidth(500);
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == getBotaoNovo()) {
				new CorForm(TelaPrincipal.instancia, "Cadastro de Cor", true, this.getCor());
				carregarGrid(getCor().load());
			} else if (e.getSource() == getBotaoEditar()) {
				if (getGridTabela().getSelectedRow() > 0) {
					this.getCor().setCodigo((Integer) getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 0));
					this.getCor().setNome(getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 1)+ "");
					new CorForm(null, "Cadastro de Cor", true, this.getCor());
					carregarGrid(getCor().load());	
				} else {
					JOptionPane.showMessageDialog(CorGrid.this,
							"Para Editar é preciso Selecionar uma cor na Grid");
				}
			} else if (e.getSource() == getBotaoExcluir()) {
				if (getGridTabela().getSelectedRow() > 0) {
					if (JOptionPane.showConfirmDialog(null,"Deseja mesmo Excluir a cor "
							+ getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 1) + " ?",
							"Exclusão", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						getCor().setCodigo((Integer) getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 0));
						getCor().delete();
						carregarGrid(getCor().load());
					}
				} else {
					JOptionPane.showMessageDialog(CorGrid.this,
									"Para Remover é preciso Selecionar uma cor na Grid");
				}
			} else if (e.getSource() == getBotaoAtualizar()) {
				carregarGrid(getCor().load());
			} else if (e.getSource() == getBotaoBuscar()) {		
				carregarGrid(getCor().buscar(
						(String) getComboAtributoBuscar().getSelectedItem(),
						(String) getComboTipoBuscar().getSelectedItem(),
						getTextBuscar().getText()));
			}
		}
	}

	/*----------------------------------------------------------
	 * FIM DE CLASSE LIMITROFE
	 *----------------------------------------------------------*/

}
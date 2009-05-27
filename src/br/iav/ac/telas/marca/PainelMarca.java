package br.iav.ac.telas.marca;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.negocio.Marca;
import br.iav.ac.telas.padrao.PainelPadrao;

/**
 * Area de Consulta, Inserção, Exclusão e alteração de Marca de um Veículo
 * 
 * @author Odair Kreuzberg
 */
public class PainelMarca extends PainelPadrao {

	/*----------------------------------------------------------
	 * ATTRIBUTOS
	 *----------------------------------------------------------*/

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CadastroHandle cadastroHandle;
	private Marca marca;
	private static String[] CAMPOS = { "Código", "Marca" };

	/*----------------------------------------------------------
	 * FIM DE ATTRIBUTOS
	 *----------------------------------------------------------*/

	/*----------------------------------------------------------
	 * CONSTRUTOR
	 *----------------------------------------------------------*/

	public PainelMarca() {
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
			carregarGrid(getMarca().load());
		}

		private Marca getMarca() {
			if (marca == null) {
				marca = new Marca();
			}
			return marca;
		}

		private void carregarGrid(ListaObjeto listaObjeto) {
			Object[][] gridArray = new Object[listaObjeto.getSize()][2];
			for (int i = 0; i < listaObjeto.getSize(); i++) {
				Marca marca = (Marca) listaObjeto.getObjeto(i);
				gridArray[i][0] = marca.getCodigo();
				gridArray[i][1] = marca.getNome();
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
				this.getMarca().setCodigo(0);
				this.getMarca().setNome("");
				new DialogoMarca(null, "Cadastro de Marca", true, this.getMarca());
				carregarGrid(getMarca().load());
			} else if (e.getSource() == getBotaoEditar()) {
				if (getGridTabela().getSelectedRow() >= 0) {
					this.getMarca().setCodigo((Integer) getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 0));
					this.getMarca().setNome(getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 1)+ "");
					new DialogoMarca(null, "Cadastro de Marca", true, this.getMarca());
					carregarGrid(getMarca().load());	
				} else {
					JOptionPane.showMessageDialog(PainelMarca.this, "Para editar é preciso selecionar uma marca na tabela!");
				}
			} else if (e.getSource() == getBotaoExcluir()) {
				if (getGridTabela().getSelectedRow() > 0) {
					if (JOptionPane.showConfirmDialog(null,"Deseja mesmo excluir a marca " + getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 1) + " ?", "Exclusão", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						getMarca().setCodigo((Integer) getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 0));
						getMarca().delete();
						carregarGrid(getMarca().load());
					}
				} else {
					JOptionPane.showMessageDialog(PainelMarca.this, "Para remover é preciso selecionar uma marca na tabela!");
				}
			} else if (e.getSource() == getBotaoAtualizar()) {
				carregarGrid(getMarca().load());
			} else if (e.getSource() == getBotaoBuscar()) {		
				carregarGrid(getMarca().search((String) getComboAtributoBuscar().getSelectedItem(), (String) getComboTipoBuscar().getSelectedItem(), getTextBuscar().getText()));
			}
		}
	}

	/*----------------------------------------------------------
	 * FIM DE CLASSE LIMITROFE
	 *----------------------------------------------------------*/

}
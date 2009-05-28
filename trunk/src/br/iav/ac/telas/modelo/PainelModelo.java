package br.iav.ac.telas.modelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.negocio.Modelo;
import br.iav.ac.telas.TelaPrincipal;
import br.iav.ac.telas.padrao.PainelPadrao;

/**
 * Area de Consulta, Inserção, Exclusão e alteração de Modelo de um Veículo
 * 
 * @author Odair Kreuzberg
 */
public class PainelModelo extends PainelPadrao {

	/*----------------------------------------------------------
	 * ATTRIBUTOS
	 *----------------------------------------------------------*/

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CadastroHandle cadastroHandle;
	private Modelo modelo;
	private static String[] CAMPOS = { "Código", "Modelo", "Marca" };

	/*----------------------------------------------------------
	 * FIM DE ATTRIBUTOS
	 *----------------------------------------------------------*/

	/*----------------------------------------------------------
	 * CONSTRUTOR
	 *----------------------------------------------------------*/

	public PainelModelo() {
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
			carregarGrid(getModelo().load());
		}

		private Modelo getModelo() {
			if (modelo == null) {
				modelo = new Modelo();
			}
			return modelo;
		}

		private void carregarGrid(ListaObjeto listaObjeto) {
			Object[][] gridArray = new Object[listaObjeto.getSize()][3];
			for (int i = 0; i < listaObjeto.getSize(); i++) {
				Modelo modelo = (Modelo) listaObjeto.getObjeto(i);
				gridArray[i][0] = modelo.getCodigo();
				gridArray[i][1] = modelo.getNome();
				gridArray[i][2] = modelo.getMarca().getNome();
			}
			DefaultTableModel model = new DefaultTableModel(gridArray, CAMPOS);
			getGridTabela().setModel(model);
			getGridTabela().setShowVerticalLines(true);
			TableColumn column = getGridTabela().getColumnModel().getColumn(0);
			column.setPreferredWidth(100);
			column = getGridTabela().getColumnModel().getColumn(1);
			column.setPreferredWidth(250);
			column = getGridTabela().getColumnModel().getColumn(2);
			column.setPreferredWidth(250);
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == getBotaoNovo()) {
				this.getModelo().setCodigo(0);
				this.getModelo().setNome("");
				new DialogoModelo(TelaPrincipal.instancia, "Cadastro de Modelo", true, this.getModelo());
				carregarGrid(getModelo().load());
			} else if (e.getSource() == getBotaoEditar()) {
				if (getGridTabela().getSelectedRow() >= 0) {
					this.getModelo().setCodigo((Integer) getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 0));
					this.getModelo().setNome(getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 1)+ "");
					new DialogoModelo(null, "Cadastro de Modelo", true, this.getModelo());
					carregarGrid(getModelo().load());	
				} else {
					JOptionPane.showMessageDialog(PainelModelo.this, "Para editar é preciso selecionar uma modelo na tabela!");
				}
			} else if (e.getSource() == getBotaoExcluir()) {
				if (getGridTabela().getSelectedRow() > 0) {
					if (JOptionPane.showConfirmDialog(null,"Deseja mesmo excluir a modelo " + getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 1) + " ?", "Exclusão", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						getModelo().setCodigo((Integer) getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 0));
						getModelo().delete();
						carregarGrid(getModelo().load());
					}
				} else {
					JOptionPane.showMessageDialog(PainelModelo.this, "Para remover é preciso selecionar uma modelo na tabela!");
				}
			} else if (e.getSource() == getBotaoAtualizar()) {
				carregarGrid(getModelo().load());
			} else if (e.getSource() == getBotaoBuscar()) {		
				carregarGrid(getModelo().search((String) getComboAtributoBuscar().getSelectedItem(), (String) getComboTipoBuscar().getSelectedItem(), getTextBuscar().getText()));
			}
		}
	}

	/*----------------------------------------------------------
	 * FIM DE CLASSE LIMITROFE
	 *----------------------------------------------------------*/

}
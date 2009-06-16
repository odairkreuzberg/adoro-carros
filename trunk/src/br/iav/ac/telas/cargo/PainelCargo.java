package br.iav.ac.telas.cargo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


import br.iav.ac.negocio.Cargo;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.telas.padrao.PainelPadrao;

/**
 * Area de Consulta, Inserção, Exclusão e alteração do Cargo de um Funcionário
 * 
 * @author Fernando Medeiros
 */
public class PainelCargo extends PainelPadrao {

	private static final long serialVersionUID = 1L;
	private CadastroHandle cadastroHandle;
	private static String[] CAMPOS = { "Código", "Nome", "Descrição" };

	public PainelCargo() {
		super(CAMPOS);
		inicializarHandlers();
	}

	private void inicializarHandlers() {
		this.cadastroHandle = new CadastroHandle();
		this.getBotaoNovo().addActionListener(cadastroHandle);
		this.getBotaoEditar().addActionListener(cadastroHandle);
		this.getBotaoExcluir().addActionListener(cadastroHandle);
		this.getBotaoBuscar().addActionListener(cadastroHandle);
		this.getBotaoAtualizar().addActionListener(cadastroHandle);
	}

	
	/*----------------------------------------------------------
	 * CLASSE LIMITROFE
	 *----------------------------------------------------------*/

	class CadastroHandle implements ActionListener {
		private Cargo cargo;
		
		public CadastroHandle() {
			super();
			cargo = new Cargo();
			carregarGrid(cargo.load());
		}

		private void carregarGrid(ListaObjeto listaObjeto) {
			Object[][] gridArray = new Object[listaObjeto.getSize()][3];
			for (int i = 0; i < listaObjeto.getSize(); i++) {
				Cargo cargo = (Cargo) listaObjeto.getObjeto(i);
				gridArray[i][0] = cargo.getCodigo();
				gridArray[i][1] = cargo.getNome();
				gridArray[i][2] = cargo.getDescricao();
			}
			DefaultTableModel model = new DefaultTableModel(gridArray, CAMPOS);
			getGridTabela().setModel(model);
			getGridTabela().setShowVerticalLines(true);
			//Definição do tamanho das colunas da grid
			//TAMANHO DA GRID: 521
			getGridTabela().getColumnModel().getColumn(0).setPreferredWidth(50);
			getGridTabela().getColumnModel().getColumn(1).setPreferredWidth(236);
			getGridTabela().getColumnModel().getColumn(2).setPreferredWidth(235);
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == getBotaoNovo()) {
				this.cargo.setCodigo(0);
				this.cargo.setNome("");
				this.cargo.setDescricao("");
				new DialogoCargo(null, "Cadastro de Cargo", true, this.cargo);
				carregarGrid(this.cargo.load());
			} else if (e.getSource() == getBotaoEditar()) {
				if (getGridTabela().getSelectedRow() >= 0) {
					this.cargo.setCodigo((Integer) getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 0));
					this.cargo.setNome(getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 1)+ "");
					this.cargo.setDescricao(getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 2)+ "");
					new DialogoCargo(null, "Cadastro de Cargo", true, this.cargo);
					carregarGrid(this.cargo.load());	
				} else {
					JOptionPane.showMessageDialog(PainelCargo.this, "Para editar é preciso selecionar um cargo na tabela!");
				}
			} else if (e.getSource() == getBotaoExcluir()) {
				if (getGridTabela().getSelectedRow() >= 0) {
					if (JOptionPane.showConfirmDialog(null,"Deseja mesmo excluir o cargo " + getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 1) + " ?", "Exclusão", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						this.cargo.setCodigo((Integer) getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 0));
						
						try {

							this.cargo.delete();
							
						} catch (RuntimeException e2) {

							JOptionPane.showMessageDialog(null, e2.getMessage(), "Erro ao excluir",JOptionPane.ERROR_MESSAGE);

							return;
						}
						carregarGrid(this.cargo.load());
					}
				} else {
					JOptionPane.showMessageDialog(PainelCargo.this, "Para remover é preciso selecionar um cargo na tabela!");
				}
			} else if (e.getSource() == getBotaoAtualizar()) {
				carregarGrid(this.cargo.load());
			} else if (e.getSource() == getBotaoBuscar()) {		
				carregarGrid(this.cargo.search((String) getComboAtributoBuscar().getSelectedItem(), (String) getComboTipoBuscar().getSelectedItem(), getTextBuscar().getText()));
			}
		}
	}

	/*----------------------------------------------------------
	 * FIM DE CLASSE LIMITROFE
	 *----------------------------------------------------------*/

}
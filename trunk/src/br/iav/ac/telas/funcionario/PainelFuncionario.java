package br.iav.ac.telas.funcionario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


import br.iav.ac.negocio.Funcionario;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.telas.padrao.PainelPadrao;

/**
 * Area de Consulta, Inserção, Exclusão e alteração do Cargo de um Funcionário
 * 
 * @author Fernando Medeiros
 */
public class PainelFuncionario extends PainelPadrao {

	private static final long serialVersionUID = 1L;
	private CadastroHandle cadastroHandle;
	private static String[] CAMPOS = { "Código", "Nome"};

	public PainelFuncionario() {
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
		private Funcionario funcionario;
		
		public CadastroHandle() {
			super();
			funcionario = new Funcionario();
			carregarGrid(funcionario.load());
		}

		private void carregarGrid(ListaObjeto listaObjeto) {
			Object[][] gridArray = new Object[listaObjeto.getSize()][2];
			for (int i = 0; i < listaObjeto.getSize(); i++) {
				Funcionario funcionario = (Funcionario) listaObjeto.getObjeto(i);
				gridArray[i][0] = funcionario.getCodigo();
				gridArray[i][1] = funcionario.getNome();
			}
			DefaultTableModel model = new DefaultTableModel(gridArray, CAMPOS);
			getGridTabela().setModel(model);
			getGridTabela().setShowVerticalLines(true);
			TableColumn column = getGridTabela().getColumnModel().getColumn(0);
			column.setPreferredWidth(1);
			column = getGridTabela().getColumnModel().getColumn(1);
			column.setPreferredWidth(100);
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == getBotaoNovo()) {
				this.funcionario.setCodigo(0);
				this.funcionario.setNome("");
				new DialogoFuncionario(null, "Cadastro de Funcionários", true, this.funcionario);
				carregarGrid(this.funcionario.load());
			} else if (e.getSource() == getBotaoEditar()) {
				if (getGridTabela().getSelectedRow() >= 0) {
					this.funcionario.setCodigo((Integer) getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 0));
					this.funcionario.setNome(getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 1)+ "");
					new DialogoFuncionario(null, "Cadastro de Cargo", true, this.funcionario);
					carregarGrid(this.funcionario.load());	
				} else {
					JOptionPane.showMessageDialog(PainelFuncionario.this, "Para editar é preciso selecionar um funcionário na tabela!");
				}
			} else if (e.getSource() == getBotaoExcluir()) {
				if (getGridTabela().getSelectedRow() >= 0) {
					if (JOptionPane.showConfirmDialog(null,"Deseja mesmo excluir o funcionário " + getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 1) + " ?", "Exclusão", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						this.funcionario.setCodigo((Integer) getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 0));
						this.funcionario.delete();
						carregarGrid(this.funcionario.load());
					}
				} else {
					JOptionPane.showMessageDialog(PainelFuncionario.this, "Para remover é preciso selecionar um Funcionário na tabela!");
				}
			} else if (e.getSource() == getBotaoAtualizar()) {
				carregarGrid(this.funcionario.load());
			} else if (e.getSource() == getBotaoBuscar()) {		
				carregarGrid(this.funcionario.search((String) getComboAtributoBuscar().getSelectedItem(), (String) getComboTipoBuscar().getSelectedItem(), getTextBuscar().getText()));
			}
		}
	}

	/*----------------------------------------------------------
	 * FIM DE CLASSE LIMITROFE
	 *----------------------------------------------------------*/

}
package br.iav.ac.telas.funcionario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


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
	private static String[] CAMPOS = { "Código", "Nome", "Telefone", "CPF", "RG", "Data de Nascimento", "Cidade", "Cargo" };

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
		private Funcionario funcionario = new Funcionario();
		
		public CadastroHandle() {
			super();
			carregarGrid(funcionario.load());
		}

		private Funcionario buscarFuncionario(){
			String cod = getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 0)+ "";
			ListaObjeto listaObjeto = funcionario.search("Código", "Igual", cod);
			if (listaObjeto.getSize() > 0) {
				return (Funcionario) listaObjeto.getObjeto(0);				
			}	
			return null;			
		}		
		
		private void carregarGrid(ListaObjeto listaObjeto) {
			Object[][] gridArray = new Object[listaObjeto.getSize()][8];
			for (int i = 0; i < listaObjeto.getSize(); i++) {
				Funcionario funcionario = (Funcionario) listaObjeto.getObjeto(i);
				gridArray[i][0] = funcionario.getCodigo();
				gridArray[i][1] = funcionario.getNome();
				gridArray[i][2] = funcionario.getTelefone();
				gridArray[i][3] = funcionario.getCpf();
				gridArray[i][4] = funcionario.getRg();
				gridArray[i][5] = funcionario.getDataNascimento();
				gridArray[i][6] = funcionario.getEndereco().getCidade().getNome();
				gridArray[i][7] = funcionario.getCargo().getNome();
			}
			DefaultTableModel model = new DefaultTableModel(gridArray, CAMPOS);
			getGridTabela().setModel(model);
			getGridTabela().setShowVerticalLines(true);
			//Definição do tamanho das colunas da grid
			//TAMANHO DA GRID: 521
			getGridTabela().getColumnModel().getColumn(0).setPreferredWidth(50);
			getGridTabela().getColumnModel().getColumn(1).setPreferredWidth(200);
			getGridTabela().getColumnModel().getColumn(2).setPreferredWidth(80);
			getGridTabela().getColumnModel().getColumn(3).setPreferredWidth(80);
			getGridTabela().getColumnModel().getColumn(4).setPreferredWidth(80);
			getGridTabela().getColumnModel().getColumn(5).setPreferredWidth(150);
			getGridTabela().getColumnModel().getColumn(6).setPreferredWidth(150);
			getGridTabela().getColumnModel().getColumn(7).setPreferredWidth(150);
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == getBotaoNovo()) {
				this.funcionario = new Funcionario();
				new DialogoFuncionario(null, "Cadastro de Funcionários", true, this.funcionario);
				carregarGrid(this.funcionario.load());
			} else if (e.getSource() == getBotaoEditar()) {
				if (getGridTabela().getSelectedRow() >= 0) {
					this.funcionario = buscarFuncionario();
					if (this.funcionario != null) {
						new DialogoFuncionario(null, "Cadastro de Funcionário", true, this.funcionario);
						carregarGrid(this.funcionario.load());	
					} else {
						JOptionPane.showMessageDialog(PainelFuncionario.this, "Erro ao buscar este Funcionario na base de dados!");
						funcionario = new Funcionario();
					}
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
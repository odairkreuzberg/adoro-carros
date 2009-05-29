package br.iav.ac.telas.status;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import br.iav.ac.negocio.Cor;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.negocio.Status;
import br.iav.ac.telas.cor.DialogoCor;
import br.iav.ac.telas.cor.PainelCor;
import br.iav.ac.telas.padrao.PainelPadrao;

public class PainelStatus extends PainelPadrao {
	
	private CadastroHandle cadastroHandle;
	private static String[] CAMPOS = { "Código", "Nome" };

	public PainelStatus() {
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

	class CadastroHandle implements ActionListener {
		
		private Status status;
				
		public CadastroHandle() {
			super();
			status = new Status();
			carregarGrid(status.load());
		}		

		/**
		 * Retorna um status se existir, caso contrario retorna null.
		 * 
		 * @return Cor
		 */
		private Status buscarStatus(){
			String nome = getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 1)+ "";
			ListaObjeto listaObjeto = status.search("Nome", "Igual", nome);
			if (listaObjeto.getSize() > 0) {
				return (Status) listaObjeto.getObjeto(0);				
			}	
			return null;			
		}
		
		/**
		 * Carrega a grid com todos os status já cadastrados.
		 */
		private void carregarGrid(ListaObjeto listaObjeto) {
			Object[][] gridArray = new Object[listaObjeto.getSize()][2];
			for (int i = 0; i < listaObjeto.getSize(); i++) {
				Status status = (Status) listaObjeto.getObjeto(i);
				gridArray[i][0] = status.getCodigo();
				gridArray[i][1] = status.getNome();
			}
			DefaultTableModel model = new DefaultTableModel(gridArray, CAMPOS);
			getGridTabela().setModel(model);
			getGridTabela().setShowVerticalLines(true);
			getGridTabela().getColumnModel().getColumn(0).setPreferredWidth(100);
			getGridTabela().getColumnModel().getColumn(1).setPreferredWidth(500);
		}

		public void actionPerformed(ActionEvent e) {
			/**
			 * Chama o formulário de status para fazer a inserção de um novo status.  
			 **/
			if (e.getSource() == getBotaoNovo()) {
				status.setCodigo(0);
				status.setNome("");
				new DialogoStatus(null, "Cadastro de Status", true, status);
				carregarGrid(status.load());
			} 			
			/**
			 * Chama o formulário de status para fazer a edição de um status.
			 */	
			else if (e.getSource() == getBotaoEditar()) {
				// verifica se existe uma uma linha selecionada na Grid.
				if (getGridTabela().getSelectedRow() >= 0) {
					status = buscarStatus();
					//se retornar uma Cor existente, entao sera instanciado o formulario de Edição.
					if(status != null){
						new DialogoStatus(null, "Cadastro de Status", true, status);	
						carregarGrid(status.load());				
					}
					else{
						JOptionPane.showMessageDialog(PainelStatus.this, "Erro ao buscar este status na base de dados!");
						status = new Status();
					}	
				} else {
					JOptionPane.showMessageDialog(PainelStatus.this, "Para editar é preciso selecionar um status na tabela!");
				}
			}			
			/**
			 * Faz a remoção de um status.
			 */
			else if (e.getSource() == getBotaoExcluir()) {
				// verifica se existe uma uma linha selecionada na Grid.
				if (getGridTabela().getSelectedRow() >= 0) {
					status = buscarStatus();
					//se retornar uma Cor existente, essa cor sera Excluida.
					if (status != null) {
						int resp = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir a modelo " + status.getNome() + " ?", "Exclusão", JOptionPane.YES_NO_OPTION);
						if (resp == 0) {
							status.delete();
							carregarGrid(status.load());
						}
					} else {
						JOptionPane.showMessageDialog(PainelStatus.this, "Erro ao buscar este status na base de dados!");
						status = new Status();
					}
				} else {
					JOptionPane.showMessageDialog(PainelStatus.this, "Para remover é preciso selecionar um status na tabela!");
				}
			} else if (e.getSource() == getBotaoAtualizar()) {
				carregarGrid(status.load());
			} else if (e.getSource() == getBotaoBuscar()) {
				carregarGrid(status.search((String) getComboAtributoBuscar().getSelectedItem(), (String) getComboTipoBuscar().getSelectedItem(), getTextBuscar().getText()));
			}
		}
		
	}

}
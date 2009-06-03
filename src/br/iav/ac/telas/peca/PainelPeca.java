package br.iav.ac.telas.peca;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.negocio.Peca;
import br.iav.ac.telas.TelaPrincipal;
import br.iav.ac.telas.padrao.PainelPadrao;

/**
 * Area de Consulta, Inserção, Exclusão e alteração de Peca de um Veículo
 * 
 * @author Odair Kreuzberg
 */
public class PainelPeca extends PainelPadrao {

	private CadastroHandle cadastroHandle;
	private Peca peca;
	private static String[] CAMPOS = { "Código", "Nome" };

	public PainelPeca() {
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
		
	class CadastroHandle implements ActionListener {

		public CadastroHandle() {
			super();
			peca = new Peca();
			carregarGrid(peca.load());
		}
		
		/**
		 * retorna um Peca se existir caso contrario retorna null.
		 * 
		 * @return Peca
		 */
		private Peca buscarPeca(){
			String nome = getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 1)+ "";
			ListaObjeto listaObjeto = peca.search("Nome", "Igual", nome);
			if (listaObjeto.getSize() > 0) {
				return (Peca) listaObjeto.getObjeto(0);				
			}	
			return null;			
		}
		
		/**
		 * Carrega a Grid com todas as Fornecedores já Cadastradas.
		 */
		private void carregarGrid(ListaObjeto listaObjeto) {
			Object[][] gridArray = new Object[listaObjeto.getSize()][2];
			for (int i = 0; i < listaObjeto.getSize(); i++) {
				Peca peca = (Peca) listaObjeto.getObjeto(i);
				gridArray[i][0] = peca.getCodigo();
				gridArray[i][1] = peca.getNome();
			}
			DefaultTableModel model = new DefaultTableModel(gridArray, CAMPOS);
			getGridTabela().setModel(model);
			getGridTabela().setShowVerticalLines(true);
			//Definição do tamanho das colunas da grid
			//TAMANHO DA GRID: 521
			getGridTabela().getColumnModel().getColumn(0).setPreferredWidth(50);
			getGridTabela().getColumnModel().getColumn(1).setPreferredWidth(471);
		}

		public void actionPerformed(ActionEvent e) {
			/**
			 * Chama o Formulário de peça para fazer a inserção de uma nova peça.  
			 **/
			if (e.getSource() == getBotaoNovo()) {
				peca.setCodigo(0);
				peca.setNome("");
				new DialogoPeca(TelaPrincipal.instancia, "Cadastro de Peca", true, peca);
				carregarGrid(peca.load());
			}  			
			/**
			 * Chama o Formulário de peça para fazer a Edição de uma fornecedor.
			 */	
			else if (e.getSource() == getBotaoEditar()) {
				//Verifica se existe uma uma linha selecionada na grid.
				if (getGridTabela().getSelectedRow() >= 0) {
					peca = buscarPeca();
					//Se retornar uma peça existente, então sera instanciado o formulário de edição.
					if (peca != null){
						new DialogoPeca(null, "Cadastro de Peça", true, peca);	
						carregarGrid(peca.load());				
					} else {
						JOptionPane.showMessageDialog(PainelPeca.this, "Erro ao buscar esta peça na base de dados!");
						peca = new Peca();
					}	
				} else {
					JOptionPane.showMessageDialog(PainelPeca.this, "Para editar é preciso selecionar uma peça na tabela!");
				}
			} 			
			/**
			 * Faz a remoção de uma peça.
			 */
			else if (e.getSource() == getBotaoExcluir()) {
				//Verifica se existe uma linha selecionada na grid.
				if (getGridTabela().getSelectedRow() >= 0) {
					peca = buscarPeca();
					//Se retornar uma peça existente, essa peça sera excluída.
					if (peca != null) {
						int resp = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir a peça " + peca.getNome()+ " ?", "Exclusão", JOptionPane.YES_NO_OPTION);
						if (resp == 0) {
							peca.delete();
							carregarGrid(peca.load());
						}
					} else {
						JOptionPane.showMessageDialog(PainelPeca.this, "Erro ao buscar esta peça na base de dados!");
						peca = new Peca();
					}
				} else {
					JOptionPane.showMessageDialog(PainelPeca.this, "Para remover é preciso selecionar uma peça na tabela!");
				}
			} 
			/**
			 * Faz a atualização da grid.
			 */
			else if (e.getSource() == getBotaoAtualizar()) {
				carregarGrid(peca.load());
			} 
			/**
			 * Faz uma busca com parametros passado pelo usuario.
			 */
			else if (e.getSource() == getBotaoBuscar()) {
				carregarGrid(peca.search((String) getComboAtributoBuscar().getSelectedItem(), (String) getComboTipoBuscar().getSelectedItem(), getTextBuscar().getText()));
			}
		}
	}

}
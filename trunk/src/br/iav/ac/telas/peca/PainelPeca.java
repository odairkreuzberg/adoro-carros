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
 * Area de Consulta, Inser��o, Exclus�o e altera��o de Peca de um Ve�culo
 * 
 * @author Odair Kreuzberg
 */
public class PainelPeca extends PainelPadrao {

	private CadastroHandle cadastroHandle;
	private Peca peca;
	private static String[] CAMPOS = { "C�digo", "Nome" };

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
		 * Carrega a Grid com todas as Fornecedores j� Cadastradas.
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
			//Defini��o do tamanho das colunas da grid
			//TAMANHO DA GRID: 521
			getGridTabela().getColumnModel().getColumn(0).setPreferredWidth(50);
			getGridTabela().getColumnModel().getColumn(1).setPreferredWidth(471);
		}

		public void actionPerformed(ActionEvent e) {
			/**
			 * Chama o Formul�rio de pe�a para fazer a inser��o de uma nova pe�a.  
			 **/
			if (e.getSource() == getBotaoNovo()) {
				peca.setCodigo(0);
				peca.setNome("");
				new DialogoPeca(TelaPrincipal.instancia, "Cadastro de Peca", true, peca);
				carregarGrid(peca.load());
			}  			
			/**
			 * Chama o Formul�rio de pe�a para fazer a Edi��o de uma fornecedor.
			 */	
			else if (e.getSource() == getBotaoEditar()) {
				//Verifica se existe uma uma linha selecionada na grid.
				if (getGridTabela().getSelectedRow() >= 0) {
					peca = buscarPeca();
					//Se retornar uma pe�a existente, ent�o sera instanciado o formul�rio de edi��o.
					if (peca != null){
						new DialogoPeca(null, "Cadastro de Pe�a", true, peca);	
						carregarGrid(peca.load());				
					} else {
						JOptionPane.showMessageDialog(PainelPeca.this, "Erro ao buscar esta pe�a na base de dados!");
						peca = new Peca();
					}	
				} else {
					JOptionPane.showMessageDialog(PainelPeca.this, "Para editar � preciso selecionar uma pe�a na tabela!");
				}
			} 			
			/**
			 * Faz a remo��o de uma pe�a.
			 */
			else if (e.getSource() == getBotaoExcluir()) {
				//Verifica se existe uma linha selecionada na grid.
				if (getGridTabela().getSelectedRow() >= 0) {
					peca = buscarPeca();
					//Se retornar uma pe�a existente, essa pe�a sera exclu�da.
					if (peca != null) {
						int resp = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir a pe�a " + peca.getNome()+ " ?", "Exclus�o", JOptionPane.YES_NO_OPTION);
						if (resp == 0) {
							peca.delete();
							carregarGrid(peca.load());
						}
					} else {
						JOptionPane.showMessageDialog(PainelPeca.this, "Erro ao buscar esta pe�a na base de dados!");
						peca = new Peca();
					}
				} else {
					JOptionPane.showMessageDialog(PainelPeca.this, "Para remover � preciso selecionar uma pe�a na tabela!");
				}
			} 
			/**
			 * Faz a atualiza��o da grid.
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
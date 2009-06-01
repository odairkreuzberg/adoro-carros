package br.iav.ac.telas.cor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import br.iav.ac.negocio.Cor;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.telas.padrao.PainelPadrao;

/**
 * Area de Consulta, Inser��o, Exclus�o e altera��o de Cor de um Ve�culo
 * 
 * @author Odair Kreuzberg
 */
public class PainelCor extends PainelPadrao {

	/*----------------------------------------------------------
	 * ATTRIBUTOS
	 *----------------------------------------------------------*/
	
	private static final long serialVersionUID = 1L;
	private CadastroHandle cadastroHandle;
	private static String[] CAMPOS = { "C�digo", "Cor" };

	/*----------------------------------------------------------
	 * FIM DE ATTRIBUTOS
	 *----------------------------------------------------------*/

	/*----------------------------------------------------------
	 * CONSTRUTOR
	 *----------------------------------------------------------*/

	public PainelCor() {
		super(CAMPOS);
		inicializarHandlers();
	}

	/*----------------------------------------------------------
	 * FIM DE CONSTRUTOR
	 *----------------------------------------------------------*/

	/*----------------------------------------------------------
	 * METODOS DA CLASSE
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
	 * FIM DE METODOS DA CLASSE
	 *----------------------------------------------------------*/

	/*----------------------------------------------------------
	 * CLASSE LIMITROFE
	 *----------------------------------------------------------*/

	class CadastroHandle implements ActionListener {
		private Cor cor;
		
		
		public CadastroHandle() {
			super();
			cor = new Cor();
			carregarGrid(cor.load());
		}
		

		/**
		 * retorna uma Cor se existir caso contrario retorna null.
		 * 
		 * @return Cor
		 */
		private Cor buscarCor(){
			String nome = getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 1)+ "";
			ListaObjeto listaObjeto = cor.search("Cor","Igual",nome);
			if (listaObjeto.getSize() > 0) {
				return (Cor) listaObjeto.getObjeto(0);				
			}	
			return null;			
		}
		
		/**
		 * Carrega a Grid com todas as Cores j� Cadastradas.
		 */
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
			//Defini��o do tamanho das colunas da grid
			//TAMANHO DA GRID: 521
			getGridTabela().getColumnModel().getColumn(0).setPreferredWidth(50);
			getGridTabela().getColumnModel().getColumn(1).setPreferredWidth(471);
		}

		public void actionPerformed(ActionEvent e) {
			/**
			 * Chama o Forul�rio de Cor para fazer a Inser��o de uma nova cor.  
			 **/
			if (e.getSource() == getBotaoNovo()) {
				Cor cor = new Cor();
				new DialogoCor(null, "Cadastro de Cor", true, cor);
				carregarGrid(cor.load());
			} 			
			/**
			 * Chama o Forul�rio de Cor para fazer a Edi��o de uma cor.
			 */	
			else if (e.getSource() == getBotaoEditar()) {
				// verifica se existe uma uma linha selecionada na Grid.
				if (getGridTabela().getSelectedRow() >= 0) {
					Cor cor = buscarCor();
					//se retornar uma Cor existente, entao sera instanciado o formulario de Edi��o.
					if(cor != null){
						new DialogoCor(null, "Cadastro de Cor", true, cor);	
						carregarGrid(cor.load());				
					}
					else{
						JOptionPane.showMessageDialog(PainelCor.this,
								"Erro ao buscar esta Cor na base de dados!");
						cor = new Cor();
					}	
				} else {
					JOptionPane.showMessageDialog(PainelCor.this, 
							"Para editar � preciso selecionar uma cor na tabela!");
				}
				
			}			
			/**
			 * Faz a Remo��o de uma cor.
			 */
			else if (e.getSource() == getBotaoExcluir()) {
				// verifica se existe uma uma linha selecionada na Grid.
				if (getGridTabela().getSelectedRow() > 0) {
					cor = buscarCor();
					//se retornar uma Cor existente, essa cor sera Excluida.
					if (cor != null) {
						int resp = JOptionPane.showConfirmDialog(null,"Deseja mesmo excluir a modelo "
								+ cor.getNome()+ " ?", "Exclus�o",JOptionPane.YES_NO_OPTION);
						if (resp == 0) {
							cor.delete();
							carregarGrid(cor.load());
						}

					} else {
						JOptionPane.showMessageDialog(PainelCor.this,
								"Erro ao buscar esta Cor na base de dados!");
						//cor = new Cor();
					}

				} else {
					JOptionPane
							.showMessageDialog(PainelCor.this,
									"Para remover � preciso selecionar uma cor na tabela!");
				}
			} else if (e.getSource() == getBotaoAtualizar()) {
				carregarGrid(cor.load());
			} else if (e.getSource() == getBotaoBuscar()) {
				carregarGrid(cor.search((String) getComboAtributoBuscar()
						.getSelectedItem(), (String) getComboTipoBuscar()
						.getSelectedItem(), getTextBuscar().getText()));
			}
		}
	}

	/*----------------------------------------------------------
	 * FIM DE CLASSE LIMITROFE
	 *----------------------------------------------------------*/

}
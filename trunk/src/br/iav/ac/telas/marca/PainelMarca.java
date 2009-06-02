package br.iav.ac.telas.marca;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import br.iav.ac.negocio.Marca;
import br.iav.ac.negocio.ListaObjeto;
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
	
	private static final long serialVersionUID = 1L;
	private CadastroHandle cadastroHandle;
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
		private Marca marca;
		
		
		public CadastroHandle() {
			super();
			marca = new Marca();
			carregarGrid(marca.load());
		}
		

		/**
		 * retorna uma Marca se existir caso contrario retorna null.
		 * 
		 * @return Marca
		 */
		private Marca buscarMarca(){
			String nome = getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 1)+ "";
			ListaObjeto listaObjeto = marca.search("Marca","Igual",nome);
			if (listaObjeto.getSize() > 0) {
				return (Marca) listaObjeto.getObjeto(0);				
			}	
			return null;			
		}
		
		/**
		 * Carrega a Grid com todas as Marcaes já Cadastradas.
		 */
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
			//Definição do tamanho das colunas da grid
			//TAMANHO DA GRID: 521
			getGridTabela().getColumnModel().getColumn(0).setPreferredWidth(50);
			getGridTabela().getColumnModel().getColumn(1).setPreferredWidth(471);
		}

		public void actionPerformed(ActionEvent e) {
			/**
			 * Chama o Forulário de Marca para fazer a Inserção de uma nova marca.  
			 **/
			if (e.getSource() == getBotaoNovo()) {
				marca.setCodigo(0);
				marca.setNome("");
				new DialogoMarca(null, "Cadastro de Marca", true, marca);
				carregarGrid(marca.load());
			} 			
			/**
			 * Chama o Forulário de Marca para fazer a Edição de uma marca.
			 */	
			else if (e.getSource() == getBotaoEditar()) {
				// verifica se existe uma uma linha selecionada na Grid.
				if (getGridTabela().getSelectedRow() >= 0) {
					marca = buscarMarca();
					//se retornar uma Marca existente, entao sera instanciado o formulario de Edição.
					if(marca != null){
						new DialogoMarca(null, "Cadastro de Marca", true, marca);	
						carregarGrid(marca.load());				
					}
					else{
						JOptionPane.showMessageDialog(PainelMarca.this,
								"Erro ao buscar esta Marca na base de dados!");
						marca = new Marca();
					}	
				} else {
					JOptionPane.showMessageDialog(PainelMarca.this, 
							"Para editar é preciso selecionar uma marca na tabela!");
				}
				
			}			
			/**
			 * Faz a Remoção de uma marca.
			 */
			else if (e.getSource() == getBotaoExcluir()) {
				// verifica se existe uma uma linha selecionada na Grid.
				if (getGridTabela().getSelectedRow() >= 0) {
					marca = buscarMarca();
					//se retornar uma Marca existente, essa marca sera Excluida.
					if (marca != null) {
						int resp = JOptionPane.showConfirmDialog(null,"Deseja mesmo excluir a modelo "+ marca.getNome()+ " ?", "Exclusão",JOptionPane.YES_NO_OPTION);
						if (resp == 0) {
							
							try {

								marca.delete();
								
							} catch (RuntimeException e2) {

								JOptionPane.showMessageDialog(null, e2.getMessage(), "Erro ao excluir",JOptionPane.ERROR_MESSAGE);

								return;
							}
							
							carregarGrid(marca.load());
						}

					} else {
						JOptionPane.showMessageDialog(PainelMarca.this,
								"Erro ao buscar esta Marca na base de dados!");
						marca = new Marca();
					}

				} else {
					JOptionPane
							.showMessageDialog(PainelMarca.this,
									"Para remover é preciso selecionar uma marca na tabela!");
				}
			} else if (e.getSource() == getBotaoAtualizar()) {
				carregarGrid(marca.load());
			} else if (e.getSource() == getBotaoBuscar()) {
				carregarGrid(marca.search((String) getComboAtributoBuscar()
						.getSelectedItem(), (String) getComboTipoBuscar()
						.getSelectedItem(), getTextBuscar().getText()));
			}
		}
	}

	/*----------------------------------------------------------
	 * FIM DE CLASSE LIMITROFE
	 *----------------------------------------------------------*/

}
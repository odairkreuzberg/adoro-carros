package br.iav.ac.telas.modelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.negocio.Marca;
import br.iav.ac.negocio.Modelo;
import br.iav.ac.telas.TelaPrincipal;
import br.iav.ac.telas.marca.DialogoMarca;
import br.iav.ac.telas.marca.PainelMarca;
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
			modelo = new Modelo();
			carregarGrid(modelo.load());
		}
		

		/**
		 * retorna um Modelo se existir caso contrario retorna null.
		 * 
		 * @return Modelo
		 */
		private Modelo buscarModelo(){
			String nome = getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 1)+ "";
			ListaObjeto listaObjeto = modelo.search("Modelo","Igual",nome);
			if (listaObjeto.getSize() > 0) {
				return (Modelo) listaObjeto.getObjeto(0);				
			}	
			return null;			
		}
		
		/**
		 * Carrega a Grid com todas as Marcaes já Cadastradas.
		 */
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
			//Definição do tamanho das colunas da grid
			//TAMANHO DA GRID: 521
			getGridTabela().getColumnModel().getColumn(0).setPreferredWidth(50);
			getGridTabela().getColumnModel().getColumn(1).setPreferredWidth(236);
			getGridTabela().getColumnModel().getColumn(2).setPreferredWidth(235);
		}

		public void actionPerformed(ActionEvent e) {
			/**
			 * Chama o Forulário de Modelo para fazer a Inserção de uma nova marca.  
			 **/
			if (e.getSource() == getBotaoNovo()) {
				modelo.setCodigo(0);
				modelo.setNome("");
				new DialogoModelo(TelaPrincipal.instancia, "Cadastro de Modelo", true, modelo);
				carregarGrid(modelo.load());
			}  			
			/**
			 * Chama o Forulário de modelo para fazer a Edição de uma marca.
			 */	
			else if (e.getSource() == getBotaoEditar()) {
				// verifica se existe uma uma linha selecionada na Grid.
				if (getGridTabela().getSelectedRow() >= 0) {
					modelo = buscarModelo();
					//se retornar uma Marca existente, entao sera instanciado o formulario de Edição.
					if(modelo != null){
						new DialogoModelo(null, "Cadastro de Marca", true, modelo);	
						carregarGrid(modelo.load());				
					}
					else{
						JOptionPane.showMessageDialog(PainelModelo.this,
								"Erro ao buscar esta Marca na base de dados!");
						modelo = new Modelo();
					}	
				} else {
					JOptionPane.showMessageDialog(PainelModelo.this, 
							"Para editar é preciso selecionar uma marca na tabela!");
				}
			} 			
			/**
			 * Faz a Remoção de uma marca.
			 */
			else if (e.getSource() == getBotaoExcluir()) {
				// verifica se existe uma uma linha selecionada na Grid.
				if (getGridTabela().getSelectedRow() > 0) {
					modelo = buscarModelo();
					//se retornar uma Marca existente, essa marca sera Excluida.
					if (modelo != null) {
						int resp = JOptionPane.showConfirmDialog(null,"Deseja mesmo excluir a modelo "
								+ modelo.getNome()+ " ?", "Exclusão",JOptionPane.YES_NO_OPTION);
						if (resp == 0) {
							modelo.delete();
							carregarGrid(modelo.load());
						}

					} else {
						JOptionPane.showMessageDialog(PainelModelo.this,
								"Erro ao buscar esta Marca na base de dados!");
						modelo = new Modelo();
					}
				} else {
					JOptionPane.showMessageDialog(PainelModelo.this, 
							"Para remover é preciso selecionar uma modelo na tabela!");
				}
			} 
			/**
			 * Faz a atualização da Grid
			 */
			else if (e.getSource() == getBotaoAtualizar()) {
				carregarGrid(modelo.load());
			} 
			/**
			 * Faz uma busca com parametros passado pelo usuario
			 */
			else if (e.getSource() == getBotaoBuscar()) {
				carregarGrid(modelo.search((String) getComboAtributoBuscar()
						.getSelectedItem(), (String) getComboTipoBuscar()
						.getSelectedItem(), getTextBuscar().getText()));
			}
		}
	}

	/*----------------------------------------------------------
	 * FIM DE CLASSE LIMITROFE
	 *----------------------------------------------------------*/

}
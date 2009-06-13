package br.iav.ac.telas.carro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import br.iav.ac.negocio.Carro;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.telas.TelaPrincipal;
import br.iav.ac.telas.padrao.PainelPadrao;

/**
 * Area de Consulta, Inserção, Exclusão e alteração de Carro de um Veículo
 * 
 * @author Odair Kreuzberg
 */
public class PainelCarro extends PainelPadrao {

	/*----------------------------------------------------------
	 * ATTRIBUTOS
	 *----------------------------------------------------------*/

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CadastroHandle cadastroHandle;
	private Carro carro;
	private static String[] CAMPOS = { "Código", "Cliente","Marca", "Modelo","Cor","Ano","Placa" };

	/*----------------------------------------------------------
	 * FIM DE ATTRIBUTOS
	 *----------------------------------------------------------*/

	/*----------------------------------------------------------
	 * CONSTRUTOR
	 *----------------------------------------------------------*/

	public PainelCarro() {
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
			carro = new Carro();
			carregarGrid(carro.load());
		}
		

		/**
		 * retorna um Carro se existir caso contrario retorna null.
		 * 
		 * @return Carro
		 */
		private Carro buscarCarro() {
			String codigo = String.valueOf(getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 0));
			ListaObjeto listaObjeto = carro.search("Código", "Igual", codigo);
			if (listaObjeto.getSize() > 0) {
				//JOptionPane.showMessageDialog(PainelCarro.this, (Carro)listaObjeto.getObjeto(0));
			
				return (Carro) listaObjeto.getObjeto(0);				
			}	
			return null;

		}
		
		/**
		 * Carrega a Grid com todas as Marcaes já Cadastradas.
		 */
		private void carregarGrid(ListaObjeto listaObjeto) {
			Object[][] gridArray = new Object[listaObjeto.getSize()][7];
			for (int i = 0; i < listaObjeto.getSize(); i++) {
				Carro carro = (Carro) listaObjeto.getObjeto(i);
				gridArray[i][0] = carro.getCodigo();
				gridArray[i][1] = carro.getCliente().getNome();
				gridArray[i][2] = carro.getModelo().getMarca().getNome();
				gridArray[i][3] = carro.getModelo().getNome();
				gridArray[i][4] = carro.getCor().getNome();
				gridArray[i][5] = carro.getAnoFabricacao();
				gridArray[i][6] = carro.getPlaca();
			}
			DefaultTableModel model = new DefaultTableModel(gridArray, CAMPOS);
			getGridTabela().setModel(model);
			getGridTabela().setShowVerticalLines(true);
			//Definição do tamanho das colunas da grid
			//TAMANHO DA GRID: 521
			getGridTabela().getColumnModel().getColumn(0).setPreferredWidth(40);
			getGridTabela().getColumnModel().getColumn(1).setPreferredWidth(116);
			getGridTabela().getColumnModel().getColumn(2).setPreferredWidth(100);
			getGridTabela().getColumnModel().getColumn(3).setPreferredWidth(100);
			getGridTabela().getColumnModel().getColumn(4).setPreferredWidth(55);
			getGridTabela().getColumnModel().getColumn(5).setPreferredWidth(55);
			getGridTabela().getColumnModel().getColumn(6).setPreferredWidth(55);
		
		}

		public void actionPerformed(ActionEvent e) {
			/**
			 * Chama o Forulário de Carro para fazer a Inserção de uma nova marca.  
			 **/
			if (e.getSource() == getBotaoNovo()) {
				carro.setCodigo(0);
				carro.setPlaca("");
				carro.setAnoFabricacao(null);
				new DialogoCarro(TelaPrincipal.instancia, "Cadastro de Carro", true, carro);
				carregarGrid(carro.load());
			}  			
			/**
			 * Chama o Forulário de carro para fazer a Edição de uma marca.
			 */	
			else if (e.getSource() == getBotaoEditar()) {
				// verifica se existe uma uma linha selecionada na Grid.
				if (getGridTabela().getSelectedRow() >= 0) {
					carro = buscarCarro();
					//se retornar uma Marca existente, entao sera instanciado o formulario de Edição.
					if(carro != null){
						new DialogoCarro(null, "Cadastro de Marca", true, carro);	
						carregarGrid(carro.load());				
					}
					else{
						JOptionPane.showMessageDialog(PainelCarro.this,
								"Erro ao buscar esta Marca na base de dados!");
						carro = new Carro();
					}	
				} else {
					JOptionPane.showMessageDialog(PainelCarro.this, 
							"Para editar é preciso selecionar uma marca na tabela!");
				}
			} 			
			/**
			 * Faz a Remoção de uma marca.
			 */
			else if (e.getSource() == getBotaoExcluir()) {
				if (getGridTabela().getSelectedRow() >= 0) {
					carro = buscarCarro();
					if (carro != null) {
						int resp = JOptionPane.showConfirmDialog(null,
								"Deseja mesmo excluir " + carro.getModelo()
								+ " ?", "Exclusão",
								JOptionPane.YES_NO_OPTION);
						if (resp == 0) {
							carro.delete();
							carregarGrid(carro.load());
						}
					} else {
						JOptionPane.showMessageDialog(PainelCarro.this,
								"Erro ao buscar esta Marca na base de dados!");
						carro = new Carro();
					}
				} else {
					JOptionPane.showMessageDialog(PainelCarro.this,
						"Para remover é preciso selecionar uma carro na tabela!");
				}
			}
			/**
			 * Faz a atualização da Grid
			 */
			else if (e.getSource() == getBotaoAtualizar()) {
				carregarGrid(carro.load());
			}
			/**
			 * Faz uma busca com parametros passado pelo usuario
			 */
			else if (e.getSource() == getBotaoBuscar()) {
				carregarGrid(carro.search((String) getComboAtributoBuscar()
						.getSelectedItem(), (String) getComboTipoBuscar()
						.getSelectedItem(), getTextBuscar().getText()));
			}
		}
	}

	/*----------------------------------------------------------
	 * FIM DE CLASSE LIMITROFE
	 *----------------------------------------------------------*/

}
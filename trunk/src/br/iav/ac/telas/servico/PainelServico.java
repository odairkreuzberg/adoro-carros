package br.iav.ac.telas.servico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import br.iav.ac.negocio.Servico;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.telas.TelaPrincipal;
import br.iav.ac.telas.padrao.PainelPadrao;

/**
 * Area de Consulta, Inserção, Exclusão e alteração de Servico.
 * 
 * @author Odair Kreuzberg
 */
public class PainelServico extends PainelPadrao {

	/*----------------------------------------------------------
	 * ATTRIBUTOS
	 *----------------------------------------------------------*/

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CadastroHandle cadastroHandle;
	//private Servico servico;
	private static String[] CAMPOS = { "Tem que arruma", "Tem que arruma", "Tem que arruma" };

	/*----------------------------------------------------------
	 * FIM DE ATTRIBUTOS
	 *----------------------------------------------------------*/

	/*----------------------------------------------------------
	 * CONSTRUTOR
	 *----------------------------------------------------------*/

	public PainelServico() {
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
			Servico servico = new Servico();
			carregarGrid(servico.load());
		}
		

		/**
		 * retorna uma Servico se existir caso contrario retorna null.
		 * 
		 * @return Marca
		 */
		private Servico buscarServico(){
			Servico servico = new Servico();
			String cod = getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 0)+ "";
			ListaObjeto listaObjeto = servico.search("Código","Igual",cod);
			if (listaObjeto.getSize() > 0) {
				return (Servico) listaObjeto.getObjeto(0);				
			}	
			return null;			
		}
		
		/**
		 * Carrega a Grid com todas as Marcaes já Cadastradas.
		 */
		private void carregarGrid(ListaObjeto listaObjeto) {
			//Object[][] gridArray = new Object[listaObjeto.getSize()][3];
			//for (int i = 0; i < listaObjeto.getSize(); i++) {
				//Servico servico = (Servico) listaObjeto.getObjeto(i);
				//gridArray[i][0] = servico.getCodigo();
				//gridArray[i][1] = servico.getNome();
				//gridArray[i][2] = servico.getTipo();
			//}
			DefaultTableModel model = new DefaultTableModel(/*gridArray*/null, CAMPOS);
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
			 * Chama o Forulário de ServicoPeca para fazer a Inserção de uma nova marca.  
			 **/
			if (e.getSource() == getBotaoNovo()) {
				Servico servico = new Servico();
				new DialogoServico(TelaPrincipal.instancia, "Cadastro de Servico", true, servico);
				carregarGrid(servico.load());
			}  			
			/**
			 * Chama o Forulário de servicoPeca para fazer a Edição de uma marca.
			 */	
			else if (e.getSource() == getBotaoEditar()) {
				// verifica se existe uma uma linha selecionada na Grid.
				if (getGridTabela().getSelectedRow() >= 0) {
					Servico servico = new Servico();
					servico = buscarServico();
					//se retornar uma Marca existente, entao sera instanciado o formulario de Edição.
					if(servico != null){
						new DialogoServico(TelaPrincipal.instancia, "Cadastro de Orçamento", true, servico);	
						carregarGrid(servico.load());				
					}
					else{
						JOptionPane.showMessageDialog(PainelServico.this,
								"Erro ao buscar esta Marca na base de dados!");
					}	
				} else {
					JOptionPane.showMessageDialog(PainelServico.this, 
							"Para editar é preciso selecionar uma marca na tabela!");
				}
			} 			
			/**
			 * Faz a Remoção de uma marca.
			 */
			else if (e.getSource() == getBotaoExcluir()) {
				// verifica se existe uma uma linha selecionada na Grid.
				if (getGridTabela().getSelectedRow() >= 0) {
					Servico  servico = buscarServico();
					//se retornar uma Marca existente, essa marca sera Excluida.
					if (servico!= null) {
						/*int resp = JOptionPane.showConfirmDialog(null,"Deseja mesmo excluir a servicoPeca "
								+ servico.getNome()+ " ?", "Exclusão",JOptionPane.YES_NO_OPTION);
						if (resp == 0) {
							servico.delete();
							carregarGrid(servico.load());
						}*/

					} else {
						JOptionPane.showMessageDialog(PainelServico.this,
								"Erro ao buscar esta Marca na base de dados!");
						servico = new Servico();
					}
				} else {
					JOptionPane.showMessageDialog(PainelServico.this, 
							"Para remover é preciso selecionar uma servicoPeca na tabela!");
				}
			} 
			/**
			 * Faz a atualização da Grid
			 */
			else if (e.getSource() == getBotaoAtualizar()) {
				Servico servico = new Servico();
				carregarGrid(servico.load());
			} 
			/**
			 * Faz uma busca com parametros passado pelo usuario
			 */
			else if (e.getSource() == getBotaoBuscar()) {
				Servico servico = new Servico();
				carregarGrid(servico.search((String) getComboAtributoBuscar()
						.getSelectedItem(), (String) getComboTipoBuscar()
						.getSelectedItem(), getTextBuscar().getText()));

			}
		}
	}

	/*----------------------------------------------------------
	 * FIM DE CLASSE LIMITROFE
	 *----------------------------------------------------------*/

}

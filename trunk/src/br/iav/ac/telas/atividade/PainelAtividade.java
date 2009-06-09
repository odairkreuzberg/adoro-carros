package br.iav.ac.telas.atividade;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import br.iav.ac.negocio.Atividade;
import br.iav.ac.negocio.AtividadePeca;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.telas.TelaPrincipal;
import br.iav.ac.telas.padrao.PainelPadrao;

/**
 * Area de Consulta, Inserção, Exclusão e alteração de AtividadePeca.
 * 
 * @author Odair Kreuzberg
 */
public class PainelAtividade extends PainelPadrao {

	/*----------------------------------------------------------
	 * ATTRIBUTOS
	 *----------------------------------------------------------*/

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CadastroHandle cadastroHandle;
	private AtividadePeca atividadePeca;
	private static String[] CAMPOS = { "Código", "AtividadePeca", "Tipo" };

	/*----------------------------------------------------------
	 * FIM DE ATTRIBUTOS
	 *----------------------------------------------------------*/

	/*----------------------------------------------------------
	 * CONSTRUTOR
	 *----------------------------------------------------------*/

	public PainelAtividade() {
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
			atividadePeca = new AtividadePeca();
			carregarGrid(atividadePeca.getAtividade().load());
		}
		

		/**
		 * retorna um AtividadePeca se existir caso contrario retorna null.
		 * 
		 * @return AtividadePeca
		 */
		private AtividadePeca buscarAtividadePeca(){
			String nome = getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 1)+ "";
			ListaObjeto listaObjeto = atividadePeca.getAtividade().search("AtividadePeca","Igual",nome);
			if (listaObjeto.getSize() > 0) {
				return (AtividadePeca) listaObjeto.getObjeto(0);				
			}	
			return null;			
		}
		
		/**
		 * Carrega a Grid com todas as Marcaes já Cadastradas.
		 */
		private void carregarGrid(ListaObjeto listaObjeto) {
			Object[][] gridArray = new Object[listaObjeto.getSize()][3];
			for (int i = 0; i < listaObjeto.getSize(); i++) {
				Atividade atividade = (Atividade) listaObjeto.getObjeto(i);
				gridArray[i][0] = atividade.getCodigo();
				gridArray[i][1] = atividade.getNome();
				gridArray[i][2] = atividade.getTipo();
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
			 * Chama o Forulário de AtividadePeca para fazer a Inserção de uma nova marca.  
			 **/
			if (e.getSource() == getBotaoNovo()) {
				//atividadePeca.setCodigo(0);
				//atividadePeca.setNome("");
				new DialogoAtividade(TelaPrincipal.instancia, "Cadastro de Atividade", true, atividadePeca);
				carregarGrid(atividadePeca.getAtividade().load());
			}  			
			/**
			 * Chama o Forulário de atividadePeca para fazer a Edição de uma marca.
			 */	
			else if (e.getSource() == getBotaoEditar()) {
				// verifica se existe uma uma linha selecionada na Grid.
				if (getGridTabela().getSelectedRow() >= 0) {
					atividadePeca = buscarAtividadePeca();
					//se retornar uma Marca existente, entao sera instanciado o formulario de Edição.
					if(atividadePeca != null){
						new DialogoAtividade(TelaPrincipal.instancia, "Cadastro de Marca", true, atividadePeca);	
						carregarGrid(atividadePeca.getAtividade().load());				
					}
					else{
						JOptionPane.showMessageDialog(PainelAtividade.this,
								"Erro ao buscar esta Marca na base de dados!");
						atividadePeca = new AtividadePeca();
					}	
				} else {
					JOptionPane.showMessageDialog(PainelAtividade.this, 
							"Para editar é preciso selecionar uma marca na tabela!");
				}
			} 			
			/**
			 * Faz a Remoção de uma marca.
			 */
			else if (e.getSource() == getBotaoExcluir()) {
				// verifica se existe uma uma linha selecionada na Grid.
				if (getGridTabela().getSelectedRow() >= 0) {
					atividadePeca = buscarAtividadePeca();
					//se retornar uma Marca existente, essa marca sera Excluida.
					if (atividadePeca != null) {
						int resp = JOptionPane.showConfirmDialog(null,"Deseja mesmo excluir a atividadePeca "
								+ atividadePeca.getAtividade().getNome()+ " ?", "Exclusão",JOptionPane.YES_NO_OPTION);
						if (resp == 0) {
							atividadePeca.delete();
							carregarGrid(atividadePeca.load());
						}

					} else {
						JOptionPane.showMessageDialog(PainelAtividade.this,
								"Erro ao buscar esta Marca na base de dados!");
						atividadePeca = new AtividadePeca();
					}
				} else {
					JOptionPane.showMessageDialog(PainelAtividade.this, 
							"Para remover é preciso selecionar uma atividadePeca na tabela!");
				}
			} 
			/**
			 * Faz a atualização da Grid
			 */
			else if (e.getSource() == getBotaoAtualizar()) {
				carregarGrid(atividadePeca.load());
			} 
			/**
			 * Faz uma busca com parametros passado pelo usuario
			 */
			else if (e.getSource() == getBotaoBuscar()) {
				carregarGrid(atividadePeca.getAtividade().search((String) getComboAtributoBuscar()
						.getSelectedItem(), (String) getComboTipoBuscar()
						.getSelectedItem(), getTextBuscar().getText()));

			}
		}
	}

	/*----------------------------------------------------------
	 * FIM DE CLASSE LIMITROFE
	 *----------------------------------------------------------*/

}
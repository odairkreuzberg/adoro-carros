package br.iav.ac.telas.atividade;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import br.iav.ac.negocio.Atividade;
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
	private static String[] CAMPOS = { "Código","Funcionário", "Atividade", "Tipo" };

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
			Atividade atividade = new Atividade();
			carregarGrid(atividade.load());
		}
		

		/**
		 * retorna uma Atividade se existir caso contrario retorna null.
		 * 
		 * @return Marca
		 */
		private Atividade buscarAtividade(){
			Atividade atividade = new Atividade();
			String cod = getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 0)+ "";
			ListaObjeto listaObjeto = atividade.search("Código","Igual",cod);
			if (listaObjeto.getSize() > 0) {
				return (Atividade) listaObjeto.getObjeto(0);				
			}	
			return null;			
		}
		
		/**
		 * Carrega a Grid com todas as Marcaes já Cadastradas.
		 */
		private void carregarGrid(ListaObjeto listaObjeto) {
			Object[][] gridArray = new Object[listaObjeto.getSize()][4];
			for (int i = 0; i < listaObjeto.getSize(); i++) {
				Atividade atividade = (Atividade) listaObjeto.getObjeto(i);
				gridArray[i][0] = atividade.getCodigo();
				gridArray[i][1] = atividade.getFuncionario().getNome();
				gridArray[i][2] = atividade.getNome();
				gridArray[i][3] = atividade.getTipo();
			}
			DefaultTableModel model = new DefaultTableModel(gridArray, CAMPOS);
			getGridTabela().setModel(model);
			getGridTabela().setShowVerticalLines(true);
			//Definição do tamanho das colunas da grid
			//TAMANHO DA GRID: 521
			getGridTabela().getColumnModel().getColumn(0).setPreferredWidth(50);
			getGridTabela().getColumnModel().getColumn(1).setPreferredWidth(171);
			getGridTabela().getColumnModel().getColumn(2).setPreferredWidth(150);
			getGridTabela().getColumnModel().getColumn(3).setPreferredWidth(150);
		}

		public void actionPerformed(ActionEvent e) {
			/**
			 * Chama o Forulário de AtividadePeca para fazer a Inserção de uma nova marca.  
			 **/
			if (e.getSource() == getBotaoNovo()) {
				Atividade atividade = new Atividade();
				new DialogoAtividade(TelaPrincipal.instancia, "Cadastro de Atividade", true, atividade);
				carregarGrid(atividade.load());
			}  			
			/**
			 * Chama o Forulário de atividadePeca para fazer a Edição de uma marca.
			 */	
			else if (e.getSource() == getBotaoEditar()) {
				// verifica se existe uma uma linha selecionada na Grid.
				if (getGridTabela().getSelectedRow() >= 0) {
					Atividade atividade = new Atividade();
					atividade = buscarAtividade();
					//se retornar uma Marca existente, entao sera instanciado o formulario de Edição.
					if(atividade != null){
						new DialogoAtividade(TelaPrincipal.instancia, "Cadastro de Atividae", true, atividade);	
						carregarGrid(atividade.load());				
					}
					else{
						JOptionPane.showMessageDialog(PainelAtividade.this,
								"Erro ao buscar esta Marca na base de dados!");
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
					Atividade  atividade = buscarAtividade();
					//se retornar uma Marca existente, essa marca sera Excluida.
					if (atividade!= null) {
						int resp = JOptionPane.showConfirmDialog(null,"Deseja mesmo excluir a atividadePeca "
								+ atividade.getNome()+ " ?", "Exclusão",JOptionPane.YES_NO_OPTION);
						if (resp == 0) {
							atividade.delete();
							carregarGrid(atividade.load());
						}

					} else {
						JOptionPane.showMessageDialog(PainelAtividade.this,
								"Erro ao buscar esta Marca na base de dados!");
						atividade = new Atividade();
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
				Atividade atividade = new Atividade();
				carregarGrid(atividade.load());
			} 
			/**
			 * Faz uma busca com parametros passado pelo usuario
			 */
			else if (e.getSource() == getBotaoBuscar()) {
				Atividade atividade = new Atividade();
				carregarGrid(atividade.search((String) getComboAtributoBuscar()
						.getSelectedItem(), (String) getComboTipoBuscar()
						.getSelectedItem(), getTextBuscar().getText()));

			}
		}
	}

	/*----------------------------------------------------------
	 * FIM DE CLASSE LIMITROFE
	 *----------------------------------------------------------*/

}
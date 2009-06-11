package br.iav.ac.telas.cor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import br.iav.ac.negocio.Cor;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.telas.TelaPrincipal;
import br.iav.ac.telas.padrao.PainelPadrao;

/**
 * Área de Consulta, Inserção, Exclusão e Alteração da Cor de um Veículo.
 * 
 * @author Odair Kreuzberg
 */
public class PainelCor extends PainelPadrao {

	private CadastroHandle cadastroHandle;
	private static String[] CAMPOS = { "Código", "Nome" };

	public PainelCor() {
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
		
		private Cor cor;
				
		public CadastroHandle() {
			super();
			cor = new Cor();
			carregarGrid(cor.load());
		}
		
		/**
		 * Retorna uma Cor se existir caso contrário retorna null.
		 * 
		 * @return Cor
		 */
		private Cor buscarCor(){
			String nome = getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 1)+ "";
			ListaObjeto listaObjeto = cor.search("Nome", "Igual", nome);
			if (listaObjeto.getSize() > 0) {
				return (Cor) listaObjeto.getObjeto(0);				
			}	
			return null;			
		}
		
		/**
		 * Carrega a Grid com todas as Cores já Cadastradas.
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
			//Definição do tamanho das colunas da grid
			//TAMANHO DA GRID: 521
			getGridTabela().getColumnModel().getColumn(0).setPreferredWidth(50);
			getGridTabela().getColumnModel().getColumn(1).setPreferredWidth(471);
		}

		public void actionPerformed(ActionEvent e) {
			/**
			 * Chama o formulário de Cor para fazer a inserção de uma nova cor.  
			 **/
			if (e.getSource() == getBotaoNovo()) {
				Cor cor = new Cor();
				new DialogoCor(TelaPrincipal.instancia, "Cadastro", true, cor);
				carregarGrid(cor.load());
			} 			
			/**
			 * Chama o formulário de Cor para fazer a edição de uma cor.
			 */	
			else if (e.getSource() == getBotaoEditar()) {
				//verifica se existe uma uma linha selecionada na Grid.
				if (getGridTabela().getSelectedRow() >= 0) {
					Cor cor = buscarCor();
					//se retornar uma cor existente, então será instanciado o formulário de edição.
					if (cor != null) {
						new DialogoCor(TelaPrincipal.instancia, "Edição", true, cor);	
						carregarGrid(cor.load());				
					} else {
						JOptionPane.showMessageDialog(PainelCor.this, "Erro ao buscar esta cor na base de dados!");
						cor = new Cor();
					}	
				} else {
					JOptionPane.showMessageDialog(PainelCor.this, "Para editar é preciso selecionar uma cor na tabela!");
				}
			}			
			/**
			 * Faz a remoção de uma cor.
			 */
			else if (e.getSource() == getBotaoExcluir()) {
				//verifica se existe uma uma linha selecionada na grid.
				if (getGridTabela().getSelectedRow() >= 0) {
					cor = buscarCor();
					//se retornar uma cor existente, essa cor sera excluída.
					if (cor != null) {
						int resp = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir a cor " + cor.getNome()+ " ?", "Exclusão", JOptionPane.YES_NO_OPTION);
						if (resp == 0) {
							cor.delete();
							carregarGrid(cor.load());
						}
					} else {
						JOptionPane.showMessageDialog(PainelCor.this, "Erro ao buscar esta cor na base de dados!");
					}
				} else {
					JOptionPane.showMessageDialog(PainelCor.this, "Para remover é preciso selecionar uma cor na tabela!");
				}
			} else if (e.getSource() == getBotaoAtualizar()) {
				carregarGrid(cor.load());
			} else if (e.getSource() == getBotaoBuscar()) {
				carregarGrid(cor.search((String) getComboAtributoBuscar().getSelectedItem(),
										(String) getComboTipoBuscar().getSelectedItem(),
												 getTextBuscar().getText()));
			}
		}
	}

}
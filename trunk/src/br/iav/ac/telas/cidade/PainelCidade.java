package br.iav.ac.telas.cidade;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import br.iav.ac.negocio.Cidade;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.telas.padrao.PainelPadrao;

public class PainelCidade extends PainelPadrao {
	
	private CadastroHandle cadastroHandle;
	private static String[] CAMPOS = { "Código", "Nome", "DDD" };

	public PainelCidade() {
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
		
		private Cidade cidade;
				
		public CadastroHandle() {
			super();
			cidade = new Cidade();
			carregarGrid(cidade.load());
		}		

		/**
		 * Retorna uma cidade se existir, caso contrario retorna null.
		 * 
		 * @return Cor
		 */
		private Cidade buscarCidade(){
			String nome = getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 1)+ "";
			ListaObjeto listaObjeto = cidade.search("Nome", "Igual", nome);
			if (listaObjeto.getSize() > 0) {
				return (Cidade) listaObjeto.getObjeto(0);				
			}	
			return null;			
		}
		
		/**
		 * Carrega a grid com todas as cidades já cadastradas.
		 */
		private void carregarGrid(ListaObjeto listaObjeto) {
			Object[][] gridArray = new Object[listaObjeto.getSize()][3];
			for (int i = 0; i < listaObjeto.getSize(); i++) {
				Cidade cidade = (Cidade) listaObjeto.getObjeto(i);
				gridArray[i][0] = cidade.getCodigo();
				gridArray[i][1] = cidade.getNome();
				gridArray[i][2] = cidade.getDdd();
			}
			DefaultTableModel model = new DefaultTableModel(gridArray, CAMPOS);
			getGridTabela().setModel(model);
			getGridTabela().setShowVerticalLines(true);
			//Definição do tamanho das colunas da grid
			//TAMANHO DA GRID: 521
			getGridTabela().getColumnModel().getColumn(0).setPreferredWidth(50);
			getGridTabela().getColumnModel().getColumn(1).setPreferredWidth(371);
			getGridTabela().getColumnModel().getColumn(2).setPreferredWidth(100);
		}

		public void actionPerformed(ActionEvent e) {
			/**
			 * Chama o formulário de cidade para fazer a inserção de uma nova cidade.  
			 **/
			if (e.getSource() == getBotaoNovo()) {
				cidade.setCodigo(0);
				cidade.setNome("");
				cidade.setDdd(0);
				new DialogoCidade(null, "Cadastro de Cidade", true, cidade);
				carregarGrid(cidade.load());
			} 			
			/**
			 * Chama o formulário de cidade para fazer a edição de uma cidade.
			 */	
			else if (e.getSource() == getBotaoEditar()) {
				//verifica se existe uma uma linha selecionada na grid.
				if (getGridTabela().getSelectedRow() >= 0) {
					cidade = buscarCidade();
					//se retornar uma cidade existente, então será instanciado o formulario de edição.
					if(cidade != null){
						new DialogoCidade(null, "Cadastro de Cidade", true, cidade);	
						carregarGrid(cidade.load());				
					}
					else{
						JOptionPane.showMessageDialog(PainelCidade.this, "Erro ao buscar esta cidade na base de dados!");
						cidade = new Cidade();
					}	
				} else {
					JOptionPane.showMessageDialog(PainelCidade.this, "Para editar é preciso selecionar uma cidade na tabela!");
				}
			}			
			/**
			 * Faz a remoção de uma cidade.
			 */
			else if (e.getSource() == getBotaoExcluir()) {
				//verifica se existe uma uma linha selecionada na grid.
				if (getGridTabela().getSelectedRow() >= 0) {
					cidade = buscarCidade();
					//se retornar uma cidade existente, essa cidade sera excluída.
					if (cidade != null) {
						int resp = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir a cidade " + cidade.getNome() + " ?", "Exclusão", JOptionPane.YES_NO_OPTION);
						if (resp == 0) {
							cidade.delete();
							carregarGrid(cidade.load());
						}
					} else {
						JOptionPane.showMessageDialog(PainelCidade.this, "Erro ao buscar esta cidade na base de dados!");
						cidade = new Cidade();
					}
				} else {
					JOptionPane.showMessageDialog(PainelCidade.this, "Para remover é preciso selecionar uma cidade na tabela!");
				}
			} else if (e.getSource() == getBotaoAtualizar()) {
				carregarGrid(cidade.load());
			} else if (e.getSource() == getBotaoBuscar()) {
				carregarGrid(cidade.search((String) getComboAtributoBuscar().getSelectedItem(), (String) getComboTipoBuscar().getSelectedItem(), getTextBuscar().getText()));
			}
		}
		
	}

}
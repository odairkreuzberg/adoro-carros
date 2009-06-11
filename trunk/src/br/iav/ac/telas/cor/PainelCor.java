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
 * �rea de Consulta, Inser��o, Exclus�o e Altera��o da Cor de um Ve�culo.
 * 
 * @author Odair Kreuzberg
 */
public class PainelCor extends PainelPadrao {

	private CadastroHandle cadastroHandle;
	private static String[] CAMPOS = { "C�digo", "Nome" };

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
		 * Retorna uma Cor se existir caso contr�rio retorna null.
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
			 * Chama o formul�rio de Cor para fazer a inser��o de uma nova cor.  
			 **/
			if (e.getSource() == getBotaoNovo()) {
				Cor cor = new Cor();
				new DialogoCor(TelaPrincipal.instancia, "Cadastro", true, cor);
				carregarGrid(cor.load());
			} 			
			/**
			 * Chama o formul�rio de Cor para fazer a edi��o de uma cor.
			 */	
			else if (e.getSource() == getBotaoEditar()) {
				//verifica se existe uma uma linha selecionada na Grid.
				if (getGridTabela().getSelectedRow() >= 0) {
					Cor cor = buscarCor();
					//se retornar uma cor existente, ent�o ser� instanciado o formul�rio de edi��o.
					if (cor != null) {
						new DialogoCor(TelaPrincipal.instancia, "Edi��o", true, cor);	
						carregarGrid(cor.load());				
					} else {
						JOptionPane.showMessageDialog(PainelCor.this, "Erro ao buscar esta cor na base de dados!");
						cor = new Cor();
					}	
				} else {
					JOptionPane.showMessageDialog(PainelCor.this, "Para editar � preciso selecionar uma cor na tabela!");
				}
			}			
			/**
			 * Faz a remo��o de uma cor.
			 */
			else if (e.getSource() == getBotaoExcluir()) {
				//verifica se existe uma uma linha selecionada na grid.
				if (getGridTabela().getSelectedRow() >= 0) {
					cor = buscarCor();
					//se retornar uma cor existente, essa cor sera exclu�da.
					if (cor != null) {
						int resp = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir a cor " + cor.getNome()+ " ?", "Exclus�o", JOptionPane.YES_NO_OPTION);
						if (resp == 0) {
							cor.delete();
							carregarGrid(cor.load());
						}
					} else {
						JOptionPane.showMessageDialog(PainelCor.this, "Erro ao buscar esta cor na base de dados!");
					}
				} else {
					JOptionPane.showMessageDialog(PainelCor.this, "Para remover � preciso selecionar uma cor na tabela!");
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
package br.iav.ac.telas.modelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.negocio.Modelo;
import br.iav.ac.telas.TelaPrincipal;
import br.iav.ac.telas.padrao.PainelPadrao;

/**
 * Area de Consulta, Inserção, Exclusão e alteração de Modelo de um Veículo
 * 
 * @author Odair Kreuzberg
 */
public class PainelModelo extends PainelPadrao {

	private CadastroHandle cadastroHandle;
	private static String[] CAMPOS = { "Código", "Modelo", "Marca" };

	public PainelModelo() {
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

		private Modelo modelo;
		
		public CadastroHandle() {
			super();
			modelo = new Modelo();
			carregarGrid(modelo.load());
		}
		

		/**
		 * Retorna um modelo se existir, caso contrário retorna null.
		 * 
		 * @return Modelo
		 */
		private Modelo buscarModelo(){
			String nome = getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 1)+ "";
			ListaObjeto listaObjeto = modelo.search("Modelo", "Igual", nome);
			if (listaObjeto.getSize() > 0) {
				return (Modelo) listaObjeto.getObjeto(0);				
			}	
			return null;			
		}
		
		/**
		 * Carrega a grid com todas os modelos já cadastrados.
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
			 * Chama o formulário de modelo para fazer a inserção de um novo modelo.  
			 **/
			if (e.getSource() == getBotaoNovo()) {
				Modelo modelo = new Modelo();
				new DialogoModelo(TelaPrincipal.instancia, "Cadastro", true, modelo);
				carregarGrid(modelo.load());
			}  			
			/**
			 * Chama o Forulário de modelo para fazer a Edição de uma marca.
			 */	
			else if (e.getSource() == getBotaoEditar()) {
				//verifica se existe uma uma linha selecionada na grid.
				if (getGridTabela().getSelectedRow() >= 0) {
					Modelo modelo = buscarModelo();
					//se retornar um modelo existente, então será instanciado o formulario de edição.
					if (modelo != null) {
						new DialogoModelo(TelaPrincipal.instancia, "Edição", true, modelo);	
						carregarGrid(modelo.load());				
					} else {
						JOptionPane.showMessageDialog(PainelModelo.this, "Erro ao buscar este modelo na base de dados!");
						modelo = new Modelo();
					}	
				} else {
					JOptionPane.showMessageDialog(PainelModelo.this, "Para editar é preciso selecionar um modelo na tabela!");
				}
			} 			
			/**
			 * Faz a remoção de um modelo.
			 */
			else if (e.getSource() == getBotaoExcluir()) {
				// verifica se existe uma linha selecionada na Grid.
				if (getGridTabela().getSelectedRow() >= 0) {
					modelo = buscarModelo();
					//se retornar um modelo existente, esse modelo sera excluído.
					if (modelo != null) {
						int resp = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir a modelo "	+ modelo.getNome()+ " ?", "Exclusão", JOptionPane.YES_NO_OPTION);
						if (resp == 0) {
							modelo.delete();
							carregarGrid(modelo.load());
						}
					} else {
						JOptionPane.showMessageDialog(PainelModelo.this, "Erro ao buscar este modelo na base de dados!");
					}
				} else {
					JOptionPane.showMessageDialog(PainelModelo.this, "Para remover é preciso selecionar um modelo na tabela!");
				}
			} else if (e.getSource() == getBotaoAtualizar()) {
				carregarGrid(modelo.load());
			} else if (e.getSource() == getBotaoBuscar()) {
				carregarGrid(modelo.search((String) getComboAtributoBuscar().getSelectedItem(),
										   (String) getComboTipoBuscar().getSelectedItem(),
										   			getTextBuscar().getText()));
			}
		}
		
	}

}
package br.iav.ac.telas.fornecedor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import br.iav.ac.negocio.Fornecedor;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.telas.padrao.PainelPadrao;

public class PainelFornecedor extends PainelPadrao {

	private CadastroHandle cadastroHandle;
	private static String[] CAMPOS = { "Código", "Nome Fantasia", "Razão Social", "CNPJ", "Telefone", "Fax", "Rua", "Número", "Bairro", "CEP", "Complemento", "Cidade" };

	public PainelFornecedor() {
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

		private Fornecedor fornecedor = new Fornecedor(); 
		
		public CadastroHandle() {
			super();
			carregarGrid(fornecedor.load());
		}

		private Fornecedor buscarFornecedor(){
			Integer id = (Integer)getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 0);
			ListaObjeto listaObjeto = fornecedor.search("Código", "Igual", id+"");
			if (listaObjeto.getSize() > 0) {
				return (Fornecedor) listaObjeto.getObjeto(0);				
			}	
			return null;			
		}
		
		private void carregarGrid(ListaObjeto listaObjeto) {
			Object[][] gridArray = new Object[listaObjeto.getSize()][12];
			for (int i = 0; i < listaObjeto.getSize(); i++) {
				Fornecedor fornecedor = (Fornecedor) listaObjeto.getObjeto(i);
				gridArray[i][0] = fornecedor.getCodigo();
				gridArray[i][1] = fornecedor.getNome();
				gridArray[i][2] = fornecedor.getRazaoSocial();
				gridArray[i][3] = fornecedor.getCnpj();
				gridArray[i][4] = fornecedor.getTelefone();
				gridArray[i][5] = fornecedor.getFax();
				gridArray[i][6] = fornecedor.getEndereco().getRua();
				gridArray[i][7] = fornecedor.getEndereco().getNumero();
				gridArray[i][8] = fornecedor.getEndereco().getBairro();
				gridArray[i][9] = fornecedor.getEndereco().getCep();
				gridArray[i][10] = fornecedor.getEndereco().getComplemento();
				gridArray[i][11] = fornecedor.getEndereco().getCidade().getNome();
			}
			DefaultTableModel model = new DefaultTableModel(gridArray, CAMPOS);
			getGridTabela().setModel(model);
			getGridTabela().setShowVerticalLines(true);
			//Definição do tamanho das colunas da grid
			//TAMANHO DA GRID: 521
			getGridTabela().getColumnModel().getColumn(0).setPreferredWidth(40);
			getGridTabela().getColumnModel().getColumn(1).setPreferredWidth(150);
			getGridTabela().getColumnModel().getColumn(2).setPreferredWidth(150);
			getGridTabela().getColumnModel().getColumn(3).setPreferredWidth(105);
			getGridTabela().getColumnModel().getColumn(4).setPreferredWidth(75);
			getGridTabela().getColumnModel().getColumn(5).setPreferredWidth(75);
			getGridTabela().getColumnModel().getColumn(6).setPreferredWidth(150);
			getGridTabela().getColumnModel().getColumn(7).setPreferredWidth(50);
			getGridTabela().getColumnModel().getColumn(8).setPreferredWidth(150);
			getGridTabela().getColumnModel().getColumn(9).setPreferredWidth(60);
			getGridTabela().getColumnModel().getColumn(10).setPreferredWidth(150);
			getGridTabela().getColumnModel().getColumn(11).setPreferredWidth(100);
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == getBotaoNovo()) {
				Fornecedor fornecedor = new Fornecedor();
				new DialogoFornecedor(null, "Cadastro de Fornecedor", true, fornecedor);
				carregarGrid(fornecedor.load());
			} else if (e.getSource() == getBotaoEditar()) {
				if (getGridTabela().getSelectedRow() >= 0) {
					Fornecedor fornecedor = buscarFornecedor();
					if (fornecedor != null) {
						new DialogoFornecedor(null, "Cadastro de Fornecedor", true, fornecedor);
						carregarGrid(fornecedor.load());	
					} else {
						JOptionPane.showMessageDialog(PainelFornecedor.this, "Erro ao buscar este fornecedor na base de dados!");
						
					}
				} else {
					JOptionPane.showMessageDialog(PainelFornecedor.this, "Para editar é preciso selecionar um fornecedor na tabela!");
				}
			} else if (e.getSource() == getBotaoExcluir()) {
				if (getGridTabela().getSelectedRow() >= 0) {
					fornecedor = buscarFornecedor();
					if (fornecedor != null) {
						int resp = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir o fornecedor " + fornecedor.getNome()+ " ?", "Exclusão", JOptionPane.YES_NO_OPTION);
						if (resp == 0) {
							try {

								fornecedor.delete();
								
							} catch (RuntimeException e2) {

								JOptionPane.showMessageDialog(null, e2.getMessage(), "Erro ao excluir",JOptionPane.ERROR_MESSAGE);

								return;
							}
							carregarGrid(fornecedor.load());
						}
					} else {
						JOptionPane.showMessageDialog(PainelFornecedor.this, "Erro ao buscar este fornecedor na base de dados!");
						fornecedor = new Fornecedor();
					}
				} else {
					JOptionPane.showMessageDialog(PainelFornecedor.this, "Para remover é preciso selecionar uma cor na tabela!");
				}
			} else if (e.getSource() == getBotaoAtualizar()) {
				carregarGrid(fornecedor.load());
			} else if (e.getSource() == getBotaoBuscar()) {		
				carregarGrid(fornecedor.search((String) getComboAtributoBuscar().getSelectedItem(), (String) getComboTipoBuscar().getSelectedItem(), getTextBuscar().getText()));
			}
		}
	}

}
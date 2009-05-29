package br.iav.ac.telas.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import br.iav.ac.negocio.Cliente;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.telas.padrao.PainelPadrao;

public class PainelCliente extends PainelPadrao {

	private CadastroHandle cadastroHandle;
	private static String[] CAMPOS = { "Código", "Nome", "Telefone", "CPF", "RG", "Profissão", "Rua", "Número", "Bairro", "CEP", "Complemento", "Cidade" };

	public PainelCliente() {
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

		private Cliente cliente = new Cliente(); 
		
		public CadastroHandle() {
			super();
			carregarGrid(cliente.load());
		}

		private Cliente buscarCliente(){
			String nome = getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 1)+ "";
			ListaObjeto listaObjeto = cliente.search("Nome", "Igual", nome);
			if (listaObjeto.getSize() > 0) {
				return (Cliente) listaObjeto.getObjeto(0);				
			}	
			return null;			
		}
		
		private void carregarGrid(ListaObjeto listaObjeto) {
			Object[][] gridArray = new Object[listaObjeto.getSize()][2];
			for (int i = 0; i < listaObjeto.getSize(); i++) {
				Cliente cliente = (Cliente) listaObjeto.getObjeto(i);
				gridArray[i][0] = cliente.getCodigo();
				gridArray[i][1] = cliente.getNome();
			}
			DefaultTableModel model = new DefaultTableModel(gridArray, CAMPOS);
			getGridTabela().setModel(model);
			getGridTabela().setShowVerticalLines(true);
			//Definição do tamanho das colunas da grid
			//TAMANHO DA GRID: 521
			getGridTabela().getColumnModel().getColumn(0).setPreferredWidth(50);
			getGridTabela().getColumnModel().getColumn(1).setPreferredWidth(200);
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == getBotaoNovo()) {
				this.cliente.setCodigo(0);
				this.cliente.setNome("");
				new DialogoCliente(null, "Cadastro de Cliente", true, this.cliente);
				carregarGrid(cliente.load());
			} else if (e.getSource() == getBotaoEditar()) {
				if (getGridTabela().getSelectedRow() >= 0) {
					cliente = buscarCliente();
					if (cliente != null) {
						new DialogoCliente(null, "Cadastro de Cliente", true, this.cliente);
						carregarGrid(cliente.load());	
					} else {
						JOptionPane.showMessageDialog(PainelCliente.this, "Erro ao buscar este cliente na base de dados!");
						cliente = new Cliente();
					}
				} else {
					JOptionPane.showMessageDialog(PainelCliente.this, "Para editar é preciso selecionar um cliente na tabela!");
				}
			} else if (e.getSource() == getBotaoExcluir()) {
				if (getGridTabela().getSelectedRow() >= 0) {
					cliente = buscarCliente();
					if (cliente != null) {
						int resp = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir o cliente " + cliente.getNome()+ " ?", "Exclusão", JOptionPane.YES_NO_OPTION);
						if (resp == 0) {
							cliente.delete();
							carregarGrid(cliente.load());
						}
					} else {
						JOptionPane.showMessageDialog(PainelCliente.this, "Erro ao buscar este cliente na base de dados!");
						cliente = new Cliente();
					}
				} else {
					JOptionPane.showMessageDialog(PainelCliente.this, "Para remover é preciso selecionar uma cor na tabela!");
				}
			} else if (e.getSource() == getBotaoAtualizar()) {
				carregarGrid(cliente.load());
			} else if (e.getSource() == getBotaoBuscar()) {		
				carregarGrid(cliente.search((String) getComboAtributoBuscar().getSelectedItem(), (String) getComboTipoBuscar().getSelectedItem(), getTextBuscar().getText()));
			}
		}
	}

}
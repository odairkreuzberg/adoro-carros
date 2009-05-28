package br.iav.ac.telas.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import br.iav.ac.negocio.Cliente;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.telas.padrao.PainelPadrao;

public class PainelCliente extends PainelPadrao {

		/*----------------------------------------------------------
		 * ATTRIBUTOS
		 *----------------------------------------------------------*/

		private CadastroHandle cadastroHandle;
		private Cliente cliente;
		private static String[] CAMPOS = { "Código", "Nome" };

		/*----------------------------------------------------------
		 * FIM DE ATTRIBUTOS
		 *----------------------------------------------------------*/

		/*----------------------------------------------------------
		 * CONSTRUTOR
		 *----------------------------------------------------------*/

		public PainelCliente() {
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
				carregarGrid(getCliente().load());
			}

			private Cliente getCliente() {
				if (cliente == null) {
					cliente = new Cliente();
				}
				return cliente;
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
				TableColumn column = getGridTabela().getColumnModel().getColumn(0);
				column.setPreferredWidth(100);
				column = getGridTabela().getColumnModel().getColumn(1);
				column.setPreferredWidth(500);
			}

			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == getBotaoNovo()) {
					this.getCliente().setCodigo(0);
					this.getCliente().setNome("");
					new DialogoCliente(null, "Cadastro de Cliente", true, this.getCliente());
					carregarGrid(getCliente().load());
				} else if (e.getSource() == getBotaoEditar()) {
					if (getGridTabela().getSelectedRow() >= 0) {
						this.getCliente().setCodigo((Integer) getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 0));
						this.getCliente().setNome(getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 1)+ "");
						new DialogoCliente(null, "Cadastro de Cliente", true, this.getCliente());
						carregarGrid(getCliente().load());	
					} else {
						JOptionPane.showMessageDialog(PainelCliente.this, "Para editar é preciso selecionar um cliente na tabela!");
					}
				} else if (e.getSource() == getBotaoExcluir()) {
					if (getGridTabela().getSelectedRow() > 0) {
						if (JOptionPane.showConfirmDialog(null,"Deseja mesmo excluir o cliente " + getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 1) + " ?", "Exclusão", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
							getCliente().setCodigo((Integer) getGridTabela().getValueAt(getGridTabela().getSelectedRow(), 0));
							getCliente().delete();
							carregarGrid(getCliente().load());
						}
					} else {
						JOptionPane.showMessageDialog(PainelCliente.this, "Para remover é preciso selecionar uma cor na tabela!");
					}
				} else if (e.getSource() == getBotaoAtualizar()) {
					carregarGrid(getCliente().load());
				} else if (e.getSource() == getBotaoBuscar()) {		
					carregarGrid(getCliente().search((String) getComboAtributoBuscar().getSelectedItem(), (String) getComboTipoBuscar().getSelectedItem(), getTextBuscar().getText()));
				}
			}
		}

		/*----------------------------------------------------------
		 * FIM DE CLASSE LIMITROFE
		 *----------------------------------------------------------*/

	}
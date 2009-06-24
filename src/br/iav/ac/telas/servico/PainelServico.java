package br.iav.ac.telas.servico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.negocio.Servico;
import br.iav.ac.telas.TelaPrincipal;



/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class PainelServico extends JPanel {

	private JButton botaoVisualisar;
	private JButton botaoBuscar;
	private JTable gridTabela;
	private JScrollPane scrollTabela;
	private JTextField textBuscar;
	private JComboBox comboTipoBuscar; //Contém, Igual, Diferente, Maior, Menor
	private JComboBox comboAtributoBuscar; //Atributos da classe para buscar
	private JLabel labelFiltro;
	private static final String[] CAMPOS = {"Código","Cliente", "Carro", "Status"};
	private CadastroHandle cadastroHandle;
	private JButton botaoAtualisar;

	public PainelServico() {
		this.setSize(549, 553);
		this.setLayout(null);
		try {
			{
				botaoVisualisar = new JButton();
				this.add(botaoVisualisar);
				botaoVisualisar.setText("Visualizar");
				botaoVisualisar.setBounds(12, 12, 113, 60);
			}
			{
				botaoBuscar = new JButton();
				this.add(botaoBuscar);
				botaoBuscar.setText("Buscar");
				botaoBuscar.setBounds(457, 49, 80, 24);
			}
			{
				textBuscar = new JTextField();
				this.add(textBuscar);
				textBuscar.setBounds(264, 50, 188, 21);
			}
			{
				labelFiltro = new JLabel();
				this.add(labelFiltro);
				labelFiltro.setText("Filtrar Por:");
				labelFiltro.setBounds(264, 18, 51, 14);
			}
			{
				ComboBoxModel comboOperadorModel = new DefaultComboBoxModel(new String[] { "Contém", "Igual", "Diferente", "Maior",	"Menor" });
				comboTipoBuscar = new JComboBox();
				this.add(comboTipoBuscar);
				comboTipoBuscar.setModel(comboOperadorModel);
				comboTipoBuscar.setBounds(441, 11, 96, 27);
			}
			{
				ComboBoxModel coboCampoModel = new DefaultComboBoxModel(new String[] { "Cliente", "Carro" });
				comboAtributoBuscar = new JComboBox();
				this.add(comboAtributoBuscar);
				comboAtributoBuscar.setModel(coboCampoModel);
				comboAtributoBuscar.setBounds(333, 12, 96, 27);
			}
			{
				DefaultTableModel model = new DefaultTableModel(new Object[0][0], null);
				gridTabela = new JTable(model);
				gridTabela.setShowVerticalLines(true);
				scrollTabela = new JScrollPane();
				scrollTabela.setHorizontalScrollBar(new JScrollBar(0));
				scrollTabela.setViewportView(gridTabela);
				gridTabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				this.add(scrollTabela);
				scrollTabela.setBounds(12, 87, 525, 454);
			}
			{
				botaoAtualisar = new JButton();
				this.add(botaoAtualisar);
				botaoAtualisar.setText("Atualizar");
				botaoAtualisar.setBounds(141, 12, 112, 60);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.inicializarHandlers();
	}
	
	/*----------------------------------------------------------
	 * INTERFACE
	 *----------------------------------------------------------*/

	private void inicializarHandlers() {
		this.cadastroHandle = new CadastroHandle();
		this.botaoBuscar.addActionListener(cadastroHandle);
		this.botaoVisualisar.addActionListener(cadastroHandle);
		this.botaoAtualisar.addActionListener(cadastroHandle);
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
			carregarGrid(servico.search("Status", "Igual", "Concluido"));
		}
		
		/**
		 * Carrega a Grid com todas as Marcaes já Cadastradas.
		 */
		private void carregarGrid(ListaObjeto listaObjeto) {
			Object[][] gridArray = new Object[listaObjeto.getSize()][4];
			for (int i = 0; i < listaObjeto.getSize(); i++) {
				Servico servico = (Servico) listaObjeto.getObjeto(i);
				gridArray[i][0] = servico.getCodigo();
				gridArray[i][1] = servico.getCarro().getCliente().getNome();
				gridArray[i][2] = servico.getCarro().getModelo().getNome();
				gridArray[i][3] = servico.getStatus();
			}			
			DefaultTableModel model = new DefaultTableModel(gridArray, CAMPOS);
			gridTabela.setModel(model);
			gridTabela.setShowVerticalLines(true);
			//Definição do tamanho das colunas da grid
			//TAMANHO DA GRID: 521
			gridTabela.getColumnModel().getColumn(0).setPreferredWidth(50);
			gridTabela.getColumnModel().getColumn(1).setPreferredWidth(181);
			gridTabela.getColumnModel().getColumn(2).setPreferredWidth(180);
			gridTabela.getColumnModel().getColumn(3).setPreferredWidth(110);
		}
		

		/**
		 * retorna uma Servico se existir caso contrario retorna null.
		 * 
		 * @return Marca
		 */
		private Servico buscarServico(){
			Servico servico = new Servico();
			String cod = gridTabela.getValueAt(gridTabela.getSelectedRow(), 0)+ "";
			ListaObjeto listaObjeto = servico.search("Código","Igual",cod);
			if (listaObjeto.getSize() > 0) {
				return (Servico) listaObjeto.getObjeto(0);				
			}	
			return null;			
		}

		public void actionPerformed(ActionEvent e) {
			 			
			if(e.getSource() == botaoVisualisar) {
				if (gridTabela.getSelectedRow() >= 0) {
					Servico servico = new Servico();
					servico = buscarServico();
					if(servico != null){
						new DialogoServico(TelaPrincipal.instancia, "Ordem de Serviço", true, servico);
						carregarGrid(servico.search("Status", "Igual", "Concluido"));
					}
					else{
						JOptionPane.showMessageDialog(null,
								"Erro ao buscar este Servico na base de dados!");
					}	
				} else {
					JOptionPane.showMessageDialog(null, 
							"Para editar é preciso selecionar uma marca na tabela!");
				}
			} else if (e.getSource() == botaoBuscar) {
				Servico servico = new Servico();
				carregarGrid(servico.search((String) comboAtributoBuscar.getSelectedItem()
						+ " Concluido", (String) comboTipoBuscar
						.getSelectedItem(), textBuscar.getText()));

			}else if(e.getSource() == botaoAtualisar) {
				Servico servico = new Servico();
				carregarGrid(servico.search("Status", "Igual", "Concluido"));
			}
		}
	}

	/*----------------------------------------------------------
	 * FIM DE CLASSE LIMITROFE
	 *----------------------------------------------------------*/

}

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
import javax.swing.JRadioButton;
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
public class PainelOrcamento extends JPanel {

	private JButton botaoOrdemServico;
	private JRadioButton botaoTodos;
	private JButton botaoEditar;
	private JButton botaoBuscar;
	private JTable gridTabela;
	private JScrollPane scrollTabela;
	private JTextField textBuscar;
	private JComboBox comboTipoBuscar; //Contém, Igual, Diferente, Maior, Menor
	private JComboBox comboAtributoBuscar; //Atributos da classe para buscar
	private JLabel labelFiltro;
	private static final String[] CAMPOS = {"Código","Cliente", "Carro", "Status"};
	private CadastroHandle cadastroHandle;
	private JRadioButton botaoAndamento;
	private JRadioButton botaoOrcamento;
	private ButtonGroup grupo;
	private JButton botaoAtualisar;

	public PainelOrcamento() {
		this.setSize(549, 553);
		this.setLayout(null);
		try {
			{
				botaoOrdemServico = new JButton();
				this.add(botaoOrdemServico);
				botaoOrdemServico.setText("Novo");
				botaoOrdemServico.setBounds(12, 11, 81, 27);
			}
			{
				botaoEditar = new JButton();
				this.add(botaoEditar);
				botaoEditar.setText("Visualizar");
				botaoEditar.setBounds(104, 11, 81, 27);
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
				textBuscar.setBounds(298, 50, 154, 21);
			}
			{
				labelFiltro = new JLabel();
				this.add(labelFiltro);
				labelFiltro.setText("Filtrar Por:");
				labelFiltro.setBounds(12, 53, 51, 14);
			}
			{
				ComboBoxModel comboOperadorModel = new DefaultComboBoxModel(new String[] { "Contém", "Igual", "Diferente", "Maior",	"Menor" });
				comboTipoBuscar = new JComboBox();
				this.add(comboTipoBuscar);
				comboTipoBuscar.setModel(comboOperadorModel);
				comboTipoBuscar.setBounds(190, 47, 96, 27);
			}
			{
				ComboBoxModel coboCampoModel = new DefaultComboBoxModel(new String[] { "Cliente", "Carro" });
				comboAtributoBuscar = new JComboBox();
				this.add(comboAtributoBuscar);
				comboAtributoBuscar.setModel(coboCampoModel);
				comboAtributoBuscar.setBounds(75, 47, 96, 27);
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
				botaoOrcamento = new JRadioButton();
				this.add(botaoOrcamento);
				botaoOrcamento.setText("Orçamento");
				botaoOrcamento.setBounds(350, 14, 83, 20);
			}
			{
				botaoAndamento = new JRadioButton();
				this.add(botaoAndamento);
				botaoAndamento.setText("Em andamento");
				botaoAndamento.setBounds(439, 14, 99, 20);
			}
			{
				botaoTodos = new JRadioButton();
				this.add(botaoTodos);
				botaoTodos.setText("Todos");
				botaoTodos.setBounds(288, 14, 57, 20);
				botaoTodos.setSelected(true);
			}
			{
				grupo = new ButtonGroup();
				grupo.add(botaoOrcamento);
				grupo.add(botaoTodos);
				{
					botaoAtualisar = new JButton();
					this.add(botaoAtualisar);
					botaoAtualisar.setText("Atualizar");
					botaoAtualisar.setBounds(196, 11, 81, 27);
				}
				grupo.add(botaoAndamento);
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
		this.botaoAndamento.addActionListener(cadastroHandle);
		this.botaoTodos.addActionListener(cadastroHandle);
		this.botaoOrcamento.addActionListener(cadastroHandle);
		this.botaoBuscar.addActionListener(cadastroHandle);
		this.botaoEditar.addActionListener(cadastroHandle);
		this.botaoOrdemServico.addActionListener(cadastroHandle);
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
			carregarGrid(servico.search("Status", "Diferente", "Concluido"));
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

		public void actionPerformed(ActionEvent e) {
			/**
			 * Chama o Forulário de ServicoPeca para fazer a Inserção de uma nova marca.  
			 **/
			if (e.getSource() == botaoOrdemServico) {
				Servico servico = new Servico();
				new DialogoOrcamento(TelaPrincipal.instancia, "Ordem de Seriço", true, servico);
				carregarGrid(servico.search("Status", "Diferente", "Concluido"));
			}  			
			/**
			 * Chama o Forulário de servicoPeca para fazer a Edição de uma marca.
			 */	
			else if (e.getSource() == botaoEditar) {
				// verifica se existe uma uma linha selecionada na Grid.
				if (gridTabela.getSelectedRow() >= 0) {
					Servico servico = new Servico();
					servico = buscarServico();
					//se retornar uma Marca existente, entao sera instanciado o formulario de Edição.
					if(servico != null){
						new DialogoOrcamento(TelaPrincipal.instancia, "Ordem de Serviço", true, servico);
						carregarGrid(servico.search("Status", "Diferente", "Concluido"));
					}
					else{
						JOptionPane.showMessageDialog(null,
								"Erro ao buscar este Servico na base de dados!");
					}	
				} else {
					JOptionPane.showMessageDialog(null, 
							"Para editar é preciso selecionar uma marca na tabela!");
				}
			}
			else if (e.getSource() == botaoAndamento) {
				Servico servico = new Servico();
				carregarGrid(servico.search("Status", "Igual", "Em andamento"));
			}
			else if (e.getSource() == botaoOrcamento) {
				Servico servico = new Servico();
				carregarGrid(servico.search("Status", "Igual", "Orçamento"));
			}
			else if (e.getSource() == botaoTodos) {
				Servico servico = new Servico();
				carregarGrid(servico.search("Status", "Diferente", "Concluido"));
			} 
			else if (e.getSource() == botaoAtualisar) {
				Servico servico = new Servico();
				carregarGrid(servico.search("Status", "Diferente", "Concluido"));
			} 
			/**
			 * Faz uma busca com parametros passado pelo usuario
			 */
			else if (e.getSource() == botaoBuscar) {
				Servico servico = new Servico();
				if (botaoOrcamento.isSelected()){
					carregarGrid(servico.search((String) comboAtributoBuscar
							.getSelectedItem()+ " orçamento", (String) comboTipoBuscar
							.getSelectedItem(), textBuscar.getText()));
					
				}else if(botaoAndamento.isSelected()){
					carregarGrid(servico.search((String) comboAtributoBuscar
							.getSelectedItem()+ " andamento", (String) comboTipoBuscar
							.getSelectedItem(), textBuscar.getText()));
				}else{
					carregarGrid(servico.search((String) comboAtributoBuscar
							.getSelectedItem()+ " todos", (String) comboTipoBuscar
							.getSelectedItem(), textBuscar.getText()));
					
				}

			}
		}
	}

	/*----------------------------------------------------------
	 * FIM DE CLASSE LIMITROFE
	 *----------------------------------------------------------*/

}

package br.iav.ac.telas.servico;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.iav.ac.negocio.Atividade;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.negocio.PecaEstoque;
import br.iav.ac.negocio.Servico;
import br.iav.ac.negocio.ServicoAtividade;
import br.iav.ac.telas.TelaPrincipal;
import br.iav.ac.telas.atividade.DialogoAtividade;


public class DialogoServico extends JDialog{

	private JLabel labelDataInicio;
	private JLabel labelDesconto;
	private JTable gridTabela;
	private JTextField TextCarro;
	private JTextField textCliente;
	private JTable gridTabelaPeca;
	private JPanel painelPeca;
	private JTextField textTotalAtividade;
	private JLabel labelTotalAtividade;
	private JLabel labeTotalPeca;
	private JTextField textTotalPeca;
	private JPanel painelAtividade;
	private JButton botaoCancelar;
	private JLabel labelDataFim;
	private JTextField textDescontos;
	private JLabel labelTotal;
	private JLabel labelCliente;
	private JLabel labelCarro;
	private JTextField textValorTotal;
	private JTextField textDataFim;
	private JScrollPane scrollTabela;
	private JScrollPane scrollTabelaPeca;
	private JTextField textDataInicio;
	private Servico servico;
	private FormHandle formHandle;
	private ButtonGroup grupo;

	public DialogoServico(TelaPrincipal frame, String titulo, boolean modal, Servico servico) {
		super(frame, titulo, modal);
		this.servico = servico;
		this.initGUI();
		this.inicializarHandlers();
		this.setSize(559, 664);
		this.setDefaultCloseOperation(DialogoAtividade.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	

	private void inicializarHandlers() {
		this.formHandle = new FormHandle();
		this.botaoCancelar.addActionListener(formHandle);
	}

	private void initGUI() {
		try {
			{
				this.setLayout(null);
				{
					labelDataInicio = new JLabel();
					this.add(labelDataInicio);
					labelDataInicio.setText("Data de Início:");
					labelDataInicio.setBounds(12, 15, 70, 14);
				}
				{
					MaskFormatter mascaraDtNascimento = new MaskFormatter("##/##/####");	            
		            mascaraDtNascimento.setPlaceholderCharacter('_');  
		            textDataInicio = new JFormattedTextField(mascaraDtNascimento);
					this.add(textDataInicio);
					textDataInicio.setBounds(90, 12, 166, 21);
					textDataInicio.setEditable(false);
				}
				{
					labelDataFim = new JLabel();
					this.add(labelDataFim);
					labelDataFim.setText("Data de Término:");
					labelDataFim.setBounds(274, 15, 83, 14);
				}
				{
					MaskFormatter mascaraDtNascimento = new MaskFormatter("##/##/####");	            
		            mascaraDtNascimento.setPlaceholderCharacter('_');  
		            textDataFim = new JFormattedTextField(mascaraDtNascimento);
					this.add(textDataFim);
					textDataFim.setBounds(369, 12, 168, 21);
					textDataFim.setEditable(false);
				}
				{
					botaoCancelar = new JButton();
					this.add(botaoCancelar);
					botaoCancelar.setText("Fechar");
					botaoCancelar.setBounds(354, 573, 183, 21);
				}
				{
					painelAtividade = new JPanel();
					this.add(painelAtividade);
					painelAtividade.setLayout(null);
					painelAtividade.setBounds(12, 97, 525, 274);
					painelAtividade.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
					painelAtividade.setLayout(null);
					{
						gridTabela = new JTable();
						gridTabela.setShowVerticalLines(true);
						scrollTabela = new JScrollPane();
						painelAtividade.add(scrollTabela);
						scrollTabela.setHorizontalScrollBar(new JScrollBar(0));
						scrollTabela.setViewportView(gridTabela);
						scrollTabela.setBounds(14, 13, 497, 229);
					}
					{
						textTotalAtividade = new JTextField("0.0");
						painelAtividade.add(textTotalAtividade);
						textTotalAtividade.setBounds(374, 248, 138, 21);
						textTotalAtividade.setEditable(false);
					}
					{
						labelTotalAtividade = new JLabel();
						painelAtividade.add(labelTotalAtividade);
						labelTotalAtividade.setText("Total Atividade: R$");
						labelTotalAtividade.setBounds(277, 251, 92, 14);
					}
				}
				{
					labelCarro = new JLabel();
					this.add(labelCarro);
					labelCarro.setText("Carro:");
					labelCarro.setBounds(12, 72, 31, 14);
				}
				{
					labelCliente = new JLabel();
					this.add(labelCliente);
					labelCliente.setText("Cliente:");
					labelCliente.setBounds(12, 43, 37, 14);
				}
				{
					textValorTotal = new JTextField("0.0");
					getContentPane().add(textValorTotal);
					textValorTotal.setBounds(253, 573, 95, 21);
					textValorTotal.setEditable(false);
				}
				{
					textDescontos = new JTextField("0.0");
					getContentPane().add(textDescontos);
					textDescontos.setText("0.0");
					textDescontos.setBounds(90, 573, 95, 21);
					textDescontos.setEditable(false);
				}
				{
					labelTotal = new JLabel();
					getContentPane().add(labelTotal);
					labelTotal.setText("Total: R$");
					labelTotal.setBounds(197, 576, 44, 14);
				}
				{
					labelDesconto = new JLabel();
					getContentPane().add(labelDesconto);
					labelDesconto.setText("Desconto: R$");
					labelDesconto.setBounds(12, 576, 65, 14);
				}
				{
					painelPeca = new JPanel();
					getContentPane().add(painelPeca);
					painelPeca.setLayout(null);
					painelPeca.setBounds(12, 383, 525, 178);
					painelPeca.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
					{
						textTotalPeca = new JTextField("0.0");
						painelPeca.add(textTotalPeca);
						textTotalPeca.setBounds(374, 151, 138, 21);
						textTotalPeca.setEditable(false);
					}
					{
						labeTotalPeca = new JLabel();
						painelPeca.add(labeTotalPeca);
						labeTotalPeca.setText("Total Peças: R$");
						labeTotalPeca.setBounds(294, 154, 75, 14);
					}
					{						
						
						gridTabelaPeca = new JTable();
						gridTabelaPeca.setShowVerticalLines(true);
						scrollTabelaPeca = new JScrollPane();	
						painelPeca.add(scrollTabelaPeca);		
						scrollTabelaPeca.setHorizontalScrollBar(new JScrollBar(0));	
						scrollTabelaPeca.setViewportView(gridTabelaPeca);		
						scrollTabelaPeca.setBounds(13, 10, 499, 132);
						String[] campos = { "Codigo", "Peca", "quantidade", "Valor R$"};
						DefaultTableModel model = new DefaultTableModel(null, campos);
						gridTabelaPeca.setModel(model);
					}
				}
				{
					textCliente = new JTextField();
					getContentPane().add(textCliente);
					textCliente.setBounds(90, 39, 447, 23);
					textCliente.setEditable(false);
				}
				{
					TextCarro = new JTextField();
					getContentPane().add(TextCarro);
					TextCarro.setBounds(90, 68, 447, 23);
					TextCarro.setEditable(false);
				}
			}
			pack();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/*----------------------------------------------------------
	 * CLASSE LIMITROFE
	 *----------------------------------------------------------*/

	class FormHandle implements ActionListener {
		
		ListaObjeto lista = new ListaObjeto();
		ListaObjeto listaP = new ListaObjeto();
		boolean carregar = false; 
		
		public FormHandle() {
			super();
				
				SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
				carregar = true;
				textCliente.setText(servico.getCarro().getCliente().getNome());
				TextCarro.setText(servico.getCarro().getModelo().getNome());
				if (servico.getDataFim() != null)
				textDataFim.setText(formatador.format(servico.getDataFim()));
				if(servico.getDataInicio() != null)
				textDataInicio.setText(formatador.format(servico.getDataInicio()));
				textDescontos.setText(servico.getValorDesconto()+"");
				textTotalAtividade.setText(servico.getValorAtividade()+"");
				textTotalPeca.setText(servico.getValorPeca()+"");
				textValorTotal.setText(servico.getValorTotal()+"");				
				ServicoAtividade sa = new ServicoAtividade();
				lista = sa.getListaAtividade(servico.getCodigo());
				
			
			carregarGridAtividade();

		}
		
		// Carrega a Grid de Atividades
		private void carregarGridAtividade() {
			Object[][] gridArray = new Object[lista.getSize()][3];
			for (int i = 0; i < lista.getSize(); i++) {
				ServicoAtividade sa = (ServicoAtividade)lista.getObjeto(i);
				gridArray[i][0] = sa.getAtividade().getNome();
				gridArray[i][1] = sa.getAtividade().getFuncionario().getNome();
				gridArray[i][2] = sa.getPrecoAtividade();
				this.carregaPecaAtividade(sa.getAtividade());
				
			}
			String[] campos = { "Atividade", "Funcionário", "Valor R$"};
			DefaultTableModel model = new DefaultTableModel(gridArray, campos);
			gridTabela.setModel(model);
			gridTabela.setShowVerticalLines(true);
			gridTabela.getColumnModel().getColumn(0).setPreferredWidth(165);
			gridTabela.getColumnModel().getColumn(1).setPreferredWidth(150);	
			gridTabela.getColumnModel().getColumn(2).setPreferredWidth(150);
			if (carregar){
				this.carregarGridPeca(listaP);
				carregar = false;
			}
						
		}

		// Carrega a Grid de Peças	
		private void carregarGridPeca(ListaObjeto listaPeca) {
			Object[][] gridArray = new Object[listaPeca.getSize()][3];
			for (int i = 0; i < listaPeca.getSize(); i++) {
				PecaEstoque pe = (PecaEstoque)listaPeca.getObjeto(i);
				gridArray[i][0] = pe.getNome();
				gridArray[i][1] = pe.getQuantidade();
				gridArray[i][2] = pe.getPreco();
			}
			String[] campos = {"Peca", "quantidade", "Valor R$"};
			DefaultTableModel model = new DefaultTableModel(gridArray, campos);
			gridTabelaPeca.setModel(model);
			gridTabelaPeca.setShowVerticalLines(true);
			gridTabelaPeca.getColumnModel().getColumn(0).setPreferredWidth(265);
			gridTabelaPeca.getColumnModel().getColumn(1).setPreferredWidth(100);	
			gridTabelaPeca.getColumnModel().getColumn(2).setPreferredWidth(100);			
		}
		
		private void carregaPecaAtividade(Atividade atividade){
			ListaObjeto pecas = atividade.getListaPeca(atividade.getCodigo());
			for (int i = 0; i < pecas.getSize(); i++) {
				((PecaEstoque)pecas.getObjeto(i)).setCodAtividade(atividade.getCodigo());
				listaP.insertWhitoutPersist(pecas.getObjeto(i));
			}			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
		
		
	}
	/*----------------------------------------------------------
	 * FIM CLASSE LIMITROFE
	 *----------------------------------------------------------*/

}

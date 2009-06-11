package br.iav.ac.telas.servico;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import br.iav.ac.negocio.Atividade;
import br.iav.ac.negocio.Carro;
import br.iav.ac.negocio.Cliente;
import br.iav.ac.negocio.Funcionario;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.negocio.Marca;
import br.iav.ac.negocio.Objeto;
import br.iav.ac.negocio.Servico;
import br.iav.ac.negocio.Status;
import br.iav.ac.telas.TelaPrincipal;
import br.iav.ac.telas.atividade.DialogoAtividade;
import br.iav.ac.telas.atividade.PainelAtividade;
import br.iav.ac.telas.carro.PainelCarro;
import br.iav.ac.telas.cliente.PainelCliente;
import br.iav.ac.telas.padrao.DialogoCRUD;
import br.iav.ac.telas.padrao.PainelPadrao;

public class DialogoServico extends JDialog{

	private JLabel labelDataInicio;
	private JLabel labelDesconto;
	private JLabel labelFuncionario;
	private JButton botaoExcluir;
	private JButton botaoAtividade;
	private JLabel labelAtividade;
	private JComboBox comboAtividade;
	private JComboBox comboFuncionario;
	private JTable gridTabela;
	private JTextField textTotalAtividade;
	private JLabel labelTotalAtividade;
	private JLabel labeTotalPeca;
	private JTextField textTotalPeca;
	private JPanel painelAtividade;
	private JButton botaoSalvar;
	private JButton botaoCancelar;
	private JLabel labelDataFim;
	private JTextField textDescontos;
	private JLabel labelTotal;
	private JTextField textVAlorAtividade;
	private JLabel labelValorAtividade;
	private JLabel labelOrcamento;
	private JLabel jLabel1;
	private JTextField labelCodigo;
	private JButton botaoCarro;
	private JButton botaoCliente;
	private JComboBox comboCliente;
	private JLabel labelCliente;
	private JComboBox comboCarro;
	private JLabel labelCarro;
	private JLabel labelAviso;
	private JButton botaoAdicionar;
	private JButton botaoEditar;
	private JTextField textValorTotal;
	private JComboBox comboStatus;
	private JLabel labelStatus;
	private JTextField textDataFim;
	private JTextField textDataInicio;
	private Servico servico;
	private FormHandle formHandle;
	
	public DialogoServico(TelaPrincipal frame, String titulo, boolean modal) {
		super(frame, titulo, modal);
		this.servico = new Servico();
		this.initGUI();
		this.inicializarHandlers();
		this.setSize(559, 581);
		this.setDefaultCloseOperation(DialogoAtividade.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public DialogoServico(TelaPrincipal frame, String titulo, boolean modal, Servico servico) {
		super(frame, titulo, modal);
		this.servico = servico;
		this.initGUI();
		this.inicializarHandlers();
		this.setSize(559, 581);
		this.setDefaultCloseOperation(DialogoAtividade.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	

	private void inicializarHandlers() {
		this.formHandle = new FormHandle();
		this.botaoAdicionar.addActionListener(formHandle);
		this.botaoAtividade.addActionListener(formHandle);
		this.botaoCancelar.addActionListener(formHandle);
		this.botaoCarro.addActionListener(formHandle);
		this.botaoCliente.addActionListener(formHandle);
		this.botaoEditar.addActionListener(formHandle);
		this.botaoExcluir.addActionListener(formHandle);
		this.botaoSalvar.addActionListener(formHandle);
		this.comboFuncionario.addItemListener(formHandle);
		this.comboCliente.addItemListener(formHandle);
	}

	private void initGUI() {
		try {
			{
				this.setLayout(null);
				{
					labelDataInicio = new JLabel();
					this.add(labelDataInicio);
					labelDataInicio.setText("Data de Início:");
					labelDataInicio.setBounds(280, 115, 70, 14);
				}
				{
					textDataInicio = new JTextField();
					this.add(textDataInicio);
					textDataInicio.setBounds(358, 112, 179, 21);
				}
				{
					labelDataFim = new JLabel();
					this.add(labelDataFim);
					labelDataFim.setText("Data de Término:");
					labelDataFim.setBounds(267, 141, 83, 14);
				}
				{
					textDataFim = new JTextField();
					this.add(textDataFim);
					textDataFim.setBounds(358, 138, 179, 21);
				}
				{
					labelStatus = new JLabel();
					this.add(labelStatus);
					labelStatus.setText("Status:");
					labelStatus.setBounds(312, 88, 38, 14);
				}
				{
					comboStatus = new JComboBox();
					this.add(comboStatus);
					comboStatus.setBounds(358, 85, 179, 21);
				}
				{
					botaoCancelar = new JButton();
					this.add(botaoCancelar);
					botaoCancelar.setText("Cancelar");
					botaoCancelar.setBounds(437, 521, 102, 21);
				}
				{
					botaoSalvar = new JButton();
					this.add(botaoSalvar);
					botaoSalvar.setText("Concluir");
					botaoSalvar.setBounds(331, 521, 101, 21);
				}
				{
					painelAtividade = new JPanel();
					this.add(painelAtividade);
					painelAtividade.setLayout(null);
					painelAtividade.setBounds(12, 166, 525, 343);
					painelAtividade.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
					painelAtividade.setLayout(null);
					{
						textValorTotal = new JTextField();
						painelAtividade.add(textValorTotal);
						textValorTotal.setBounds(356, 309, 156, 21);
					}
					{
						labelTotal = new JLabel();
						painelAtividade.add(labelTotal);
						labelTotal.setText("Total: R$");
						labelTotal.setBounds(294, 312, 44, 14);
					}
					{
						textDescontos = new JTextField();
						painelAtividade.add(textDescontos);
						textDescontos.setBounds(356, 282, 156, 21);
					}
					{
						labelDesconto = new JLabel();
						painelAtividade.add(labelDesconto);
						labelDesconto.setText("Desconto: R$");
						labelDesconto.setBounds(273, 285, 65, 14);
					}
					{
						textTotalPeca = new JTextField();
						painelAtividade.add(textTotalPeca);
						textTotalPeca.setBounds(117, 309, 138, 21);
					}
					{
						labeTotalPeca = new JLabel();
						painelAtividade.add(labeTotalPeca);
						labeTotalPeca.setText("Total Peças: R$");
						labeTotalPeca.setBounds(30, 312, 75, 14);
					}
					{
						labelTotalAtividade = new JLabel();
						painelAtividade.add(labelTotalAtividade);
						labelTotalAtividade.setText("Total Atividade: R$");
						labelTotalAtividade.setBounds(13, 285, 92, 14);
					}
					{
						textTotalAtividade = new JTextField();
						painelAtividade.add(textTotalAtividade);
						textTotalAtividade.setBounds(117, 282, 138, 21);
					}
					{
						TableModel gridTabelaModel = 
							new DefaultTableModel(
									new String[][] { { "One", "Two" }, { "Three", "Four" } },
									new String[] { "Column 1", "Column 2" });
						gridTabela = new JTable();
						painelAtividade.add(gridTabela);
						gridTabela.setModel(gridTabelaModel);
						gridTabela.setBounds(14, 77, 497, 196);
					}
					{
						labelFuncionario = new JLabel();
						painelAtividade.add(labelFuncionario);
						labelFuncionario.setText("Funcionário:");
						labelFuncionario.setBounds(13, 13, 59, 14);
					}
					{
						comboFuncionario = new JComboBox();
						painelAtividade.add(comboFuncionario);
						comboFuncionario.setBounds(84, 7, 161, 21);
					}
					{
						comboAtividade = new JComboBox();
						painelAtividade.add(comboAtividade);
						comboAtividade.setBounds(318, 10, 160, 21);
					}
					{
						labelAtividade = new JLabel();
						painelAtividade.add(labelAtividade);
						labelAtividade.setText("Atividade:");
						labelAtividade.setBounds(257, 13, 49, 14);
					}
					{
						botaoAtividade = new JButton();
						painelAtividade.add(botaoAtividade);
						botaoAtividade.setText("+");
						botaoAtividade.setBounds(490, 10, 22, 21);
					}
					{
						botaoExcluir = new JButton();
						painelAtividade.add(botaoExcluir);
						botaoExcluir.setText("Excluir");
						botaoExcluir.setBounds(433, 36, 79, 21);
					}
					{
						botaoEditar = new JButton();
						painelAtividade.add(botaoEditar);
						botaoEditar.setText("Editar");
						botaoEditar.setBounds(349, 36, 79, 21);
					}
					{
						botaoAdicionar = new JButton();
						painelAtividade.add(botaoAdicionar);
						botaoAdicionar.setText("Adicionar");
						botaoAdicionar.setBounds(265, 36, 79, 21);
					}
					{
						labelAviso = new JLabel();
						painelAtividade.add(labelAviso);
						labelAviso.setText("Aviso");
						labelAviso.setBounds(14, 63, 497, 14);
					}
					{
						labelValorAtividade = new JLabel();
						painelAtividade.add(labelValorAtividade);
						labelValorAtividade.setText("Atividade: R$");
						labelValorAtividade.setBounds(14, 35, 70, 21);
					}
					{
						textVAlorAtividade = new JTextField();
						painelAtividade.add(textVAlorAtividade);
						textVAlorAtividade.setBounds(84, 35, 161, 21);
					}
				}
				{
					labelCarro = new JLabel();
					this.add(labelCarro);
					labelCarro.setText("Carro:");
					labelCarro.setBounds(12, 141, 31, 14);
				}
				{
					comboCarro = new JComboBox();
					this.add(comboCarro);
					comboCarro.setBounds(49, 138, 173, 21);
				}
				{
					labelCliente = new JLabel();
					this.add(labelCliente);
					labelCliente.setText("Cliente:");
					labelCliente.setBounds(12, 115, 37, 14);
				}
				{
					comboCliente = new JComboBox();
					this.add(comboCliente);
					comboCliente.setBounds(49, 112, 173, 21);
				}
				{
					botaoCliente = new JButton();
					this.add(botaoCliente);
					botaoCliente.setText("+");
					botaoCliente.setBounds(228, 112, 22, 21);
				}
				{
					botaoCarro = new JButton();
					this.add(botaoCarro);
					botaoCarro.setText("+");
					botaoCarro.setBounds(228, 138, 22, 21);
				}
				{
					labelCodigo = new JTextField();
					this.add(labelCodigo);
					labelCodigo.setBounds(50, 85, 200, 21);
				}
				{
					jLabel1 = new JLabel();
					this.add(jLabel1);
					jLabel1.setText("Código:");
					jLabel1.setBounds(12, 88, 38, 14);
				}
				{
					labelOrcamento = new JLabel();
					this.add(labelOrcamento);
					labelOrcamento.setText("ORÇAMENTO");
					labelOrcamento.setBounds(12, 0, 525, 82);
					labelOrcamento.setHorizontalAlignment(SwingConstants.CENTER);
					labelOrcamento.setFont(new java.awt.Font("Arial",1,48));
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

	class FormHandle implements ActionListener, ItemListener  {
		Cliente cliente = new Cliente();
		Carro carro = new Carro();
		Status status = new Status();
		Funcionario funcionario = new Funcionario();
		Atividade atividade = new Atividade();			
		
		public FormHandle() {
			super();
			this.carregarComboCliente(cliente.load());			
			if (comboCliente.getSelectedIndex() != -1){
				this.carregarComboCarro(carro.search("Cliente", "Igual", ((Cliente)comboCliente.getSelectedItem()).getNome()));				
			}
			this.carregarComboFuncionario(funcionario.load());
			if(comboFuncionario.getSelectedIndex() != -1){
				this.carregarComboAtividade(atividade.search("Funcionario", "Igual", ((Funcionario)comboFuncionario.getSelectedItem()).getNome()));
			}
			this.carregarComboStatus(status.load());
			
			if (servico.getCodigo() != 0) {
				comboCliente.setEditable(true);
				//comboCliente.setSelectedItem((Object) servico.getCarro().getCliente());
				comboCliente.setEditable(false);
				comboFuncionario.setEditable(true);
				//comboFuncionario.setSelectedItem((Object) carro.getModelo().getMarca());
				comboFuncionario.setEditable(false);
				comboCarro.setEditable(true);
				//comboCarro.setSelectedItem((Object) carro.getModelo());
				comboCarro.setEditable(false);
				comboStatus.setEditable(true);
				//comboStatus.setSelectedItem((Object) carro.getCor());
				comboAtividade.setEditable(false);
				//comboAtividade.setSelectedItem((Object) carro.getCor());
				comboAtividade.setEditable(false);
			}

		}

		/**
		 * Carrega o Combobox com os nomes dos Clientes.
		 * 
		 * @param listaObjeto
		 */
		private void carregarComboCliente(ListaObjeto listaObjeto) {
			Objeto[] comboArray = new Objeto[listaObjeto.getSize()];
			for (int i = 0; i < listaObjeto.getSize(); i++) {
				Cliente cliente = (Cliente) listaObjeto.getObjeto(i);
				comboArray[i] = cliente;
			}
			ComboBoxModel comboModeloModel = new DefaultComboBoxModel(comboArray);
			comboCliente.setModel(comboModeloModel);
		}

		/**
		 * Carrega o Combobox com os nomes dos Carros.
		 * 
		 * @param listaObjeto
		 */
		private void carregarComboCarro(ListaObjeto listaObjeto) {
			Objeto[] comboArray = new Objeto[listaObjeto.getSize()];
			for (int i = 0; i < listaObjeto.getSize(); i++) {
				Carro carro = (Carro) listaObjeto.getObjeto(i);
				comboArray[i] = carro;
			}
			ComboBoxModel comboModeloModel = new DefaultComboBoxModel(comboArray);
			comboCarro.setModel(comboModeloModel);
		}

		/**
		 * Carrega o Combobox com os nomes dos Status.
		 * 
		 * @param listaObjeto
		 */
		private void carregarComboStatus(ListaObjeto listaObjeto) {
			Objeto[] comboArray = new Objeto[listaObjeto.getSize()];
			for (int i = 0; i < listaObjeto.getSize(); i++) {
				Status status = (Status) listaObjeto.getObjeto(i);
				comboArray[i] = status;
			}
			ComboBoxModel comboModeloModel = new DefaultComboBoxModel(comboArray);
			comboStatus.setModel(comboModeloModel);
		}

		/**
		 * Carrega o Combobox com os nomes das Atividades.
		 * 
		 * @param listaObjeto
		 */
		private void carregarComboAtividade(ListaObjeto listaObjeto) {
			Objeto[] comboArray = new Objeto[listaObjeto.getSize()];
			for (int i = 0; i < listaObjeto.getSize(); i++) {
				Atividade atividade = (Atividade) listaObjeto.getObjeto(i);
				comboArray[i] = atividade;
			}
			ComboBoxModel comboModeloModel = new DefaultComboBoxModel(comboArray);
			comboAtividade.setModel(comboModeloModel);
		}

		/**
		 * Carrega o Combobox com os nomes dos Funcionarios.
		 * 
		 * @param listaObjeto
		 */
		private void carregarComboFuncionario(ListaObjeto listaObjeto) {
			Objeto[] comboArray = new Objeto[listaObjeto.getSize()];
			for (int i = 0; i < listaObjeto.getSize(); i++) {
				Funcionario funcionario = (Funcionario) listaObjeto.getObjeto(i);
				comboArray[i] = funcionario;
			}
			ComboBoxModel comboModeloModel = new DefaultComboBoxModel(comboArray);
			comboFuncionario.setModel(comboModeloModel);
		}

		/**
		 * Instancia o Caso de Uso.
		 * 
		 * @param painelPadrao
		 * @param titulo
		 */
		private void showPainel(PainelPadrao painelPadrao, String titulo) {

			DialogoCRUD dialogoCRUD = new DialogoCRUD(TelaPrincipal.instancia,
					titulo, true);
			dialogoCRUD.setPainel(painelPadrao);

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == botaoAdicionar) {
			}else if (e.getSource() == botaoAtividade) {	
				showPainel(new PainelAtividade(), "Cadastro de Atividade");	
				this.carregarComboAtividade(atividade.search("Funcionario", "Igual", ((Funcionario)comboFuncionario.getSelectedItem()).getNome()));
			}else if (e.getSource() == botaoCancelar) {				
			}else if (e.getSource() == botaoCarro) {	
				showPainel(new PainelCarro(), "Cadastro de Carro");	
				this.carregarComboCarro(carro.search("Cliente", "Igual", ((Cliente)comboCliente.getSelectedItem()).getNome()));
			}else if (e.getSource() == botaoCliente) {	
				showPainel(new PainelCliente(), "Cadastro de Cliente");
				this.carregarComboCliente(cliente.load());			
			}else if (e.getSource() == botaoEditar) {				
			}else if (e.getSource() == botaoExcluir) {				
			}else if (e.getSource() == botaoSalvar) {				
			}
			
		}


		@Override
		public void itemStateChanged(ItemEvent e) {
			
			if(e.getSource() == comboFuncionario){
				if(comboFuncionario.getSelectedIndex() != -1){
					this.carregarComboAtividade(atividade.search("Funcionario", "Igual", ((Funcionario)comboFuncionario.getSelectedItem()).getNome()));
				}				
			}else if(e.getSource() == comboCliente){
				if (comboCliente.getSelectedIndex() != -1){
					this.carregarComboCarro(carro.search("Cliente", "Igual", ((Cliente)comboCliente.getSelectedItem()).getNome()));				
				}
			}			
			
		}
		
		
	}
	/*----------------------------------------------------------
	 * FIM CLASSE LIMITROFE
	 *----------------------------------------------------------*/

}

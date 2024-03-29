package br.iav.ac.telas.servico;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.iav.ac.negocio.Atividade;
import br.iav.ac.negocio.Carro;
import br.iav.ac.negocio.Cliente;
import br.iav.ac.negocio.Funcionario;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.negocio.Objeto;
import br.iav.ac.negocio.PecaEstoque;
import br.iav.ac.negocio.Servico;
import br.iav.ac.negocio.ServicoAtividade;
import br.iav.ac.negocio.Status;
import br.iav.ac.telas.TelaPrincipal;
import br.iav.ac.telas.atividade.DialogoAtividade;
import br.iav.ac.telas.atividade.PainelAtividade;
import br.iav.ac.telas.carro.PainelCarro;
import br.iav.ac.telas.cliente.PainelCliente;
import br.iav.ac.telas.padrao.DialogoCRUD;
import br.iav.ac.telas.padrao.PainelPadrao;



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
public class DialogoOrcamento extends JDialog{

	private JLabel labelDataInicio;
	private JLabel labelDesconto;
	private JLabel labelFuncionario;
	private JButton botaoExcluir;
	private JButton botaoAtividade;
	private JLabel labelAtividade;
	private JComboBox comboAtividade;
	private JComboBox comboFuncionario;
	private JTable gridTabela;
	private JRadioButton botaoConcluido;
	private JRadioButton botaoAndamento;
	private JRadioButton botaoOrcamento;
	private JTable gridTabelaPeca;
	private JPanel painelPeca;
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
	private JTextField textDataFim;
	private JScrollPane scrollTabela;
	private JScrollPane scrollTabelaPeca;
	private JTextField textDataInicio;
	private Servico servico;
	private FormHandle formHandle;
	private ButtonGroup grupo;

	
	public DialogoOrcamento(TelaPrincipal frame, String titulo, boolean modal) {
		super(frame, titulo, modal);
		this.servico = new Servico();
		this.initGUI();
		this.inicializarHandlers();
		this.setSize(568,704);
		this.setDefaultCloseOperation(DialogoAtividade.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public DialogoOrcamento(TelaPrincipal frame, String titulo, boolean modal, Servico servico) {
		super(frame, titulo, modal);
		this.servico = servico;
		this.initGUI();
		this.inicializarHandlers();
		this.setSize(568,704);
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
		this.botaoOrcamento.addActionListener(formHandle);
		this.botaoAndamento.addActionListener(formHandle);
		this.botaoConcluido.addActionListener(formHandle);
		this.comboFuncionario.addItemListener(formHandle);
		this.comboCliente.addItemListener(formHandle);
		this.textDescontos.addFocusListener(formHandle);
	}

	private void initGUI() {
		try {
			{
				this.setLayout(null);
				{
					labelDataInicio = new JLabel();
					this.add(labelDataInicio);
					labelDataInicio.setText("Data de In�cio:");
					labelDataInicio.setBounds(12, 88, 70, 14);
				}
				{
					MaskFormatter mascaraDtNascimento = new MaskFormatter("##/##/####");	            
		            mascaraDtNascimento.setPlaceholderCharacter('_');  
		            textDataInicio = new JFormattedTextField(mascaraDtNascimento);
					this.add(textDataInicio);
					textDataInicio.setBounds(82, 85, 120, 21);
				}
				{
					labelDataFim = new JLabel();
					this.add(labelDataFim);
					labelDataFim.setText("Data de T�rmino:");
					labelDataFim.setBounds(214, 88, 83, 14);
				}
				{
					MaskFormatter mascaraDtNascimento = new MaskFormatter("##/##/####");	            
		            mascaraDtNascimento.setPlaceholderCharacter('_');  
		            textDataFim = new JFormattedTextField(mascaraDtNascimento);
					this.add(textDataFim);
					textDataFim.setBounds(305, 85, 122, 21);
				}
				{
					botaoCancelar = new JButton();
					this.add(botaoCancelar);
					botaoCancelar.setText("Cancelar");
					botaoCancelar.setBounds(454, 633, 86, 21);
				}
				{
					botaoSalvar = new JButton();
					this.add(botaoSalvar);
					botaoSalvar.setText("Concluir");
					botaoSalvar.setBounds(357, 633, 86, 21);
				}
				{
					painelAtividade = new JPanel();
					this.add(painelAtividade);
					painelAtividade.setLayout(null);
					painelAtividade.setBounds(12, 166, 525, 274);
					painelAtividade.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
					painelAtividade.setLayout(null);
					{
						gridTabela = new JTable();
						gridTabela.setShowVerticalLines(true);
						scrollTabela = new JScrollPane();
						painelAtividade.add(scrollTabela);
						scrollTabela.setHorizontalScrollBar(new JScrollBar(0));
						scrollTabela.setViewportView(gridTabela);
						scrollTabela.setBounds(14, 77, 497, 165);
					}
					{
						labelFuncionario = new JLabel();
						painelAtividade.add(labelFuncionario);
						labelFuncionario.setText("Funcion�rio:");
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
						labelAviso.setFont(new java.awt.Font("Segoe UI",1,12));
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
					labelCarro.setBounds(12, 142, 31, 14);
				}
				{
					comboCarro = new JComboBox();
					this.add(comboCarro);
					comboCarro.setBounds(82, 139, 312, 21);
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
					comboCliente.setBounds(82, 112, 312, 21);
				}
				{
					botaoCliente = new JButton();
					this.add(botaoCliente);
					botaoCliente.setText("+");
					botaoCliente.setBounds(406, 112, 22, 21);
				}
				{
					botaoCarro = new JButton();
					this.add(botaoCarro);
					botaoCarro.setText("+");
					botaoCarro.setBounds(406, 139, 22, 21);
				}
				{
					labelOrcamento = new JLabel();
					this.add(labelOrcamento);
					labelOrcamento.setText("ORDEM DE SERVI�O");
					labelOrcamento.setBounds(7, 0, 525, 82);
					labelOrcamento.setHorizontalAlignment(SwingConstants.CENTER);
					labelOrcamento.setFont(new java.awt.Font("Arial",1,48));
				}
				{
					textValorTotal = new JTextField("0.0");
					getContentPane().add(textValorTotal);
					textValorTotal.setBounds(250, 633, 95, 21);
					textValorTotal.setEditable(false);
				}
				{
					textDescontos = new JTextField("0.0");
					getContentPane().add(textDescontos);
					textDescontos.setText("0.0");
					textDescontos.setBounds(89, 633, 95, 21);
				}
				{
					labelTotal = new JLabel();
					getContentPane().add(labelTotal);
					labelTotal.setText("Total: R$");
					labelTotal.setBounds(194, 636, 44, 14);
				}
				{
					labelDesconto = new JLabel();
					getContentPane().add(labelDesconto);
					labelDesconto.setText("Desconto: R$");
					labelDesconto.setBounds(12, 636, 65, 14);
				}
				{
					painelPeca = new JPanel();
					getContentPane().add(painelPeca);
					painelPeca.setLayout(null);
					painelPeca.setBounds(12, 446, 525, 178);
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
						labeTotalPeca.setText("Total Pe�as: R$");
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
						String[] campos = {"Peca", "quantidade", "Valor R$","Valor Total R$"};
						DefaultTableModel model = new DefaultTableModel(null, campos);
						gridTabelaPeca.setModel(model);
					}
				}
				{
					botaoOrcamento = new JRadioButton();
					getContentPane().add(botaoOrcamento);
					botaoOrcamento.setText("Or�amento");
					botaoOrcamento.setBounds(433, 85, 78, 20);
					botaoOrcamento.setSelected(true);
				}
				{
					botaoAndamento = new JRadioButton();
					getContentPane().add(botaoAndamento);
					botaoAndamento.setText("Em andamento");
					botaoAndamento.setBounds(433, 112, 99, 20);
				}
				{
					botaoConcluido = new JRadioButton();
					getContentPane().add(botaoConcluido);
					botaoConcluido.setText("Concluido");
					botaoConcluido.setBounds(433, 139, 73, 20);
				}
				{
					grupo = new ButtonGroup();
					grupo.add(botaoOrcamento);
					grupo.add(botaoConcluido);
					grupo.add(botaoAndamento);
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

	class FormHandle implements ActionListener, ItemListener,  FocusListener {
		Cliente cliente = new Cliente();
		Carro carro = new Carro();
		Status status = new Status();
		Funcionario funcionario = new Funcionario();
		Atividade atividade = new Atividade();		
		
		ListaObjeto lista = new ListaObjeto();
		ListaObjeto listaP = new ListaObjeto();
		ListaObjeto listaStatus = new ListaObjeto();
		boolean carregar = false; 
		
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
			
			listaStatus = status.load();
			
			if (servico.getCodigo() != 0) {
				
				SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
				carregar = true;
				
				comboCliente.setEditable(true);
				comboCliente.setSelectedItem((Object) servico.getCarro().getCliente());
				comboCliente.setEditable(false);
				comboCarro.setEditable(true);
				comboCarro.setSelectedItem((Object) servico.getCarro());
				comboCarro.setEditable(false);
				if (servico.getDataFim() != null)
				textDataFim.setText(formatador.format(servico.getDataFim()));
				if(servico.getDataInicio() != null)
				textDataInicio.setText(formatador.format(servico.getDataInicio()));
				textDescontos.setText(servico.getValorDesconto()+"");
				textTotalAtividade.setText(servico.getValorAtividade()+"");
				System.out.println(servico.getValorAtividade());
				textTotalPeca.setText(servico.getValorPeca()+"");
				textValorTotal.setText(servico.getValorTotal()+"");				
				ServicoAtividade sa = new ServicoAtividade();
				lista = sa.getListaAtividade(servico.getCodigo());
				if (servico.getStatus().getNome().equals("Or�amento")){
					botaoOrcamento.setSelected(true);
				}else if (servico.getStatus().getNome().equals("Em andamento")){
					botaoAndamento.setSelected(true);
				}else if (servico.getStatus().getNome().equals("Concluido")){
					botaoConcluido.setSelected(true);
				}
			}
			carregaID();
			carregarGridAtividade();

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
		
		// Carrega a Grid de Atividades
		private void carregarGridAtividade() {
			Object[][] gridArray = new Object[lista.getSize()][4];
			for (int i = 0; i < lista.getSize(); i++) {
				ServicoAtividade sa = (ServicoAtividade)lista.getObjeto(i);
				gridArray[i][0] = sa.getCodigo();
				gridArray[i][1] = sa.getAtividade().getNome();
				gridArray[i][2] = sa.getAtividade().getFuncionario().getNome();
				gridArray[i][3] = sa.getPrecoAtividade();
				if (carregar){
					this.carregaPecaAtividade(sa.getAtividade());
				}
				
			}
			String[] campos = { "Codigo", "Atividade", "Funcion�rio", "Valor R$"};
			DefaultTableModel model = new DefaultTableModel(gridArray, campos);
			gridTabela.setModel(model);
			gridTabela.setShowVerticalLines(true);
			gridTabela.getColumnModel().getColumn(0).setPreferredWidth(40);
			gridTabela.getColumnModel().getColumn(1).setPreferredWidth(165);
			gridTabela.getColumnModel().getColumn(2).setPreferredWidth(150);	
			gridTabela.getColumnModel().getColumn(3).setPreferredWidth(150);
			if (carregar){
				this.carregarGridPeca(listaP);
				carregar = false;
			}
						
		}

		// Carrega a Grid de Pe�as	
		private void carregarGridPeca(ListaObjeto listaPeca) {
			Object[][] gridArray = new Object[listaPeca.getSize()][4];
			for (int i = 0; i < listaPeca.getSize(); i++) {
				PecaEstoque pe = (PecaEstoque)listaPeca.getObjeto(i);
				//gridArray[i][0] = pe.getCodAtividade();
				gridArray[i][0] = pe.getNome();
				gridArray[i][1] = pe.getQuantidade();
				gridArray[i][2] = pe.getPreco();
				gridArray[i][3] = pe.getPreco()*pe.getQuantidade();
			}
			String[] campos = {"Peca", "quantidade", "Valor R$","Valor Total R$"};
			DefaultTableModel model = new DefaultTableModel(gridArray, campos);
			gridTabelaPeca.setModel(model);
			gridTabelaPeca.setShowVerticalLines(true);
			//gridTabelaPeca.getColumnModel().getColumn(0).setPreferredWidth(40);
			gridTabelaPeca.getColumnModel().getColumn(0).setPreferredWidth(265);
			gridTabelaPeca.getColumnModel().getColumn(1).setPreferredWidth(100);	
			gridTabelaPeca.getColumnModel().getColumn(2).setPreferredWidth(100);	
			gridTabelaPeca.getColumnModel().getColumn(3).setPreferredWidth(100);			
		}
		
		//GAMBIARRA. Carrega o ID de um ServicoAtividade com O ID da Atividade
		private void carregaID(){
			for (int i = 0; i < lista.getSize(); i++) {				
				int cod = ((ServicoAtividade)lista.getObjeto(i)).getAtividade().getCodigo();
				((ServicoAtividade)lista.getObjeto(i)).setCodigo(cod);
			}
		}
		
		private void carregaPecaAtividade(Atividade atividade){
			ListaObjeto pecas = atividade.getListaPeca(atividade.getCodigo());
			for (int i = 0; i < pecas.getSize(); i++) {
				((PecaEstoque)pecas.getObjeto(i)).setCodAtividade(atividade.getCodigo());
				listaP.insertWhitoutPersist(pecas.getObjeto(i));
			}			
		}
		
		// Adiciona uma Atividade na Grid
		private void addAtividade(){
			if(textVAlorAtividade.getText().equals("")){
				labelAviso.setText("O Campo Valor Da Atividade � Obrigat�rio!");	
			}else{
				try {
					float valor = Float.parseFloat(textVAlorAtividade.getText());
					ServicoAtividade sv = new ServicoAtividade();
					sv.setAtividade(((Atividade)comboAtividade.getSelectedItem()));
					sv.setCodigo(((Atividade)comboAtividade.getSelectedItem()).getCodigo());
					sv.setPrecoAtividade(valor);
					if(lista.search(sv)== null){
						this.carregaPecaAtividade((Atividade)comboAtividade.getSelectedItem());	
						this.carregarGridPeca(listaP);
						lista.insertWhitoutPersist(sv);
						carregarGridAtividade();	
						textVAlorAtividade.setText("");
						comboAtividade.setSelectedIndex(0);
						labelAviso.setText("");
						this.CalcularValor();
					}else{
						labelAviso.setText("Esta Pe�a j� se encontra na Lista!");			
					}
				} catch (Exception e2) {
					labelAviso.setText(textVAlorAtividade.getText()+ " n�o � um valor v�lido!");
					textVAlorAtividade.setText("");
				}
			}
		}
		
		// Ao remover um Arividade este metodo eh invocado para remover as Pe�as da Atividade removida.
		private void removerPeca() {
			if (gridTabela.getSelectedRow() >= 0) {
				int cod =(Integer) gridTabela.getValueAt(gridTabela.getSelectedRow(), 0);
				ServicoAtividade sv = new ServicoAtividade();
				sv.setCodigo(cod);
				lista.delete(sv);
				this.carregarGridAtividade();				
				labelAviso.setText("");					
				ListaObjeto lAux = new ListaObjeto();				
				for (int i = 0; i < listaP.getSize(); i++) {
					if(cod != ((PecaEstoque)listaP.getObjeto(i)).getCodAtividade()){
						lAux.insertWhitoutPersist(listaP.getObjeto(i));	
					}					
				}
				listaP = lAux;
				carregarGridPeca(listaP);
			}else{
				labelAviso.setText("Para Remover uma Pe�a � preciso selecionar um item na Tabela!");
			}
			this.CalcularValor();
		}
		
		// Faz o Calculo do Or�amento
		private void CalcularValor(){
			float valorA = 0;
			for (int i = 0; i < lista.getSize(); i++) {
				valorA += ((ServicoAtividade)lista.getObjeto(i)).getPrecoAtividade();				
			}
			textTotalAtividade.setText(valorA+"");	
			float valorP = 0;

			for (int i = 0; i < listaP.getSize(); i++) {
				PecaEstoque pe = (PecaEstoque)listaP.getObjeto(i);
				valorP += pe.getPreco()*pe.getQuantidade() ;				
			}
			textTotalPeca.setText(valorP+"");
			float valorT = valorA + valorP;
			valorT = valorT - Float.parseFloat(textDescontos.getText());
			textValorTotal.setText(valorT + "");
		}

		//  Seleciona os itens da Grid p/ alterar uma Atividade
		private void editarAtividade() {
			if (gridTabela.getSelectedRow() >= 0) {
				Atividade atividade = new Atividade();
				atividade.setNome(gridTabela.getValueAt(gridTabela.getSelectedRow(),1)+"");
				atividade.setCodigo((Integer)gridTabela.getValueAt(gridTabela.getSelectedRow(),0));
				comboAtividade.setEditable(true);
				comboAtividade.setSelectedItem((Object)atividade);
				comboAtividade.setEditable(false);				
				textVAlorAtividade.setText(gridTabela.getValueAt(gridTabela.getSelectedRow(),3)+"");
				this.removerPeca();				
			}else{
				labelAviso.setText("Para Editar uma Atividade � preciso selecionar um item na Tabela!");
			}
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == botaoAdicionar) {
				this.addAtividade();
			}else if (e.getSource() == botaoAtividade) {	
				this.showPainel(new PainelAtividade(), "Cadastro de Atividade");	
				this.carregarComboAtividade(atividade.search("Funcionario", "Igual", ((Funcionario)comboFuncionario.getSelectedItem()).getNome()));
			}else if (e.getSource() == botaoCancelar) {	
				dispose();
			}else if (e.getSource() == botaoCarro) {	
				this.showPainel(new PainelCarro(), "Cadastro de Carro");	
				this.carregarComboCarro(carro.search("Cliente", "Igual", ((Cliente)comboCliente.getSelectedItem()).getNome()));
			}else if (e.getSource() == botaoCliente) {	
				this.showPainel(new PainelCliente(), "Cadastro de Cliente");
				this.carregarComboCliente(cliente.load());			
			}else if (e.getSource() == botaoEditar) {	
				this.editarAtividade();
			}else if (e.getSource() == botaoExcluir) {	
				this.removerPeca();
			}else if (e.getSource() == botaoSalvar) {
				if (textDataInicio.getText().equals("__/__/____")){
					labelAviso.setText("O Campo Data de In�cio � Obrigatorio!");
				}else if (textDataFim.getText().equals("__/__/____")){
					labelAviso.setText("O Campo Data de Fim � Obrigatorio!");
				}else{
					if (servico.getCodigo() == 0) {
						
						this.inserir();
					} 
					else {
						this.editar();
					}
				}				
			}			
		}

		private void editar() {
			servico.setCarro((Carro) comboCarro.getSelectedItem());
			SimpleDateFormat converterDate = new SimpleDateFormat("dd/MM/yyyy");
			try {
				if (this.validarPecas()) {
					servico.setDataFim(converterDate.parse(textDataFim.getText()));
					servico.setDataInicio(converterDate.parse(textDataInicio.getText()));
					servico.setValorAtividade(Float.parseFloat(textTotalAtividade.getText()));
					servico.setValorDesconto(Float.parseFloat(textDescontos.getText()));
					servico.setValorPeca(Float.parseFloat(textTotalPeca.getText()));
					servico.setValorTotal(Float.parseFloat(textValorTotal.getText()));
					servico.setListaServicoAtividade(lista);
					this.addStatus();
					servico.edit(listaP);
					dispose();
				} else {
					labelAviso
							.setText("N�o Existe as Pe�a necessarias em Estoque para efetuar este Or�amento!");
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
		private boolean validarPecas(){
			if(botaoConcluido.isSelected()){
				return servico.validarPecas(listaP);
			}
			return true;
		}

		private void inserir() {
			try {
				if (this.validarPecas()){				
					SimpleDateFormat converterDate = new SimpleDateFormat("dd/MM/yyyy");
					servico.setCarro((Carro) comboCarro.getSelectedItem());
					servico.setDataFim(converterDate.parse(textDataFim.getText()));
					servico.setDataInicio(converterDate.parse(textDataInicio.getText()));
					servico.setValorAtividade(Float.parseFloat(textTotalAtividade.getText()));
					servico.setValorDesconto(Float.parseFloat(textDescontos.getText()));
					servico.setValorPeca(Float.parseFloat(textTotalPeca.getText()));
					servico.setValorTotal(Float.parseFloat(textValorTotal.getText()));
					servico.setListaServicoAtividade(lista);
					this.addStatus();
					servico.insert(listaP);
					dispose();
				} else {
					labelAviso.setText("N�o Existe as Pe�a necessarias em Estoque para efetuar este Or�amento!");
				}

			} catch (ParseException e) {
				e.printStackTrace();
				
			}
		}

		private void addStatus() {
			if(botaoOrcamento.isSelected()){
				servico.setStatus((Status)status.search("Nome", "Igual", "Or�amento").getObjeto(0));
				
			}else if(botaoAndamento.isSelected()){
				servico.setStatus((Status)status.search("Nome", "Igual", "Em andamento").getObjeto(0));
				
			}else if(botaoConcluido.isSelected()){
				servico.setStatus((Status)status.search("Nome", "Igual", "Concluido").getObjeto(0));
				
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

		@Override
		public void focusGained(FocusEvent arg0) {
			textDescontos.setText("");
			
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			try {
				float i = Integer.parseInt(textDescontos.getText());
				this.CalcularValor();
			} catch (Exception e) {
				labelAviso.setText(textDescontos.getText() + " n�o � um valor valido!");
				textDescontos.setText("0.0");
			}			
		}
		
		
	}
	/*----------------------------------------------------------
	 * FIM CLASSE LIMITROFE
	 *----------------------------------------------------------*/

}

package br.iav.ac.telas.atividade;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import br.iav.ac.negocio.AtividadePeca;
import br.iav.ac.negocio.Funcionario;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.negocio.Objeto;
import br.iav.ac.negocio.Peca;
import br.iav.ac.telas.TelaPrincipal;
import br.iav.ac.telas.cliente.PainelCliente;
import br.iav.ac.telas.padrao.DialogoCRUD;
import br.iav.ac.telas.padrao.PainelPadrao;


public final class DialogoAtividade extends JDialog {
	private JLabel labelAtividade;
	private JLabel labelCodigo;
	private JLabel labelFuncionario;
	private JButton botaoSalvar;
	private JButton botaoCancelar;
	private JPanel painelAtividade;
	private JPanel painelPecas;
	private JButton botaoRemovePeca;
	private JButton botaoAddPeca;
	private JTextField textQuantidade;
	private JLabel labelQuantidade;
	private JTextField textTipo;
	private JButton botaoFuncionario;
	private JButton botaoPeca;
	private JComboBox comboPeca;
	private JComboBox comboFuncionario;
	private JTextField textAtividade;
	private JTextField textCodigo;
	private JLabel labelPeca;
	private JLabel labelTipo;
	private AtividadePeca atividadePeca;
	private JTable gridTabela;
	private JScrollPane scrollTabela;
	private FormHandle formHandle;
	
	public DialogoAtividade(TelaPrincipal frame, String titulo, boolean modal, AtividadePeca atividadePeca  ) {
		super(frame, titulo, modal);
		this.atividadePeca = atividadePeca;
		this.initGUI();
		this.inicializarHandlers();
		this.setSize(356, 510);
		this.setDefaultCloseOperation(DialogoAtividade.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	private void inicializarHandlers() {
		this.formHandle = new FormHandle();
		this.botaoAddPeca.addActionListener(formHandle);
		this.botaoCancelar.addActionListener(formHandle);
		this.botaoFuncionario.addActionListener(formHandle);
		this.botaoPeca.addActionListener(formHandle);
		this.botaoRemovePeca.addActionListener(formHandle);
		this.botaoSalvar.addActionListener(formHandle);
	}

	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				{
					painelPecas = new JPanel();
					getContentPane().add(painelPecas);
					painelPecas.setBounds(12, 151, 331, 293);
					painelPecas.setLayout(null);
					painelPecas.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
					{
						botaoRemovePeca = new JButton();
						painelPecas.add(botaoRemovePeca);
						botaoRemovePeca.setText("Remover Peça");
						botaoRemovePeca.setBounds(236, 63, 83, 21);
					}
					{
						botaoAddPeca = new JButton();
						painelPecas.add(botaoAddPeca);
						botaoAddPeca.setText("Adicionar Peça");
						botaoAddPeca.setBounds(141, 63, 84, 21);
					}
					{
						labelPeca = new JLabel();
						painelPecas.add(labelPeca);
						labelPeca.setText("Peça:");
						labelPeca.setBounds(13, 13, 27, 14);
					}
					{
						labelQuantidade = new JLabel();
						painelPecas.add(labelQuantidade);
						labelQuantidade.setText("Quantidade:");
						labelQuantidade.setBounds(13, 39, 60, 14);
					}
					{
						textQuantidade = new JTextField();
						painelPecas.add(textQuantidade);
						textQuantidade.setBounds(79, 36, 239, 21);
					}
					{
						botaoPeca = new JButton();
						painelPecas.add(botaoPeca);
						botaoPeca.setText("+");
						botaoPeca.setBounds(297, 10, 22, 21);
					}
					{
						comboPeca = new JComboBox();
						painelPecas.add(comboPeca);
						comboPeca.setBounds(79, 10, 212, 21);
					}
					{
						gridTabela = new JTable();
						gridTabela.setShowVerticalLines(true);
						scrollTabela = new JScrollPane();
						painelPecas.add(scrollTabela);
						scrollTabela.setHorizontalScrollBar(new JScrollBar(0));
						scrollTabela.setViewportView(gridTabela);
						scrollTabela.setBounds(13, 89, 305, 185);
					}
				}
				{
					painelAtividade = new JPanel();
					getContentPane().add(painelAtividade);
					painelAtividade.setBounds(12, 6, 331, 133);
					painelAtividade.setLayout(null);
					painelAtividade.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
					{
						textCodigo = new JTextField();
						painelAtividade.add(textCodigo);
						textCodigo.setBounds(76, 13, 242, 21);
					}
					{
						labelCodigo = new JLabel();
						painelAtividade.add(labelCodigo);
						labelCodigo.setText("Código:");
						labelCodigo.setBounds(13, 16, 37, 14);
					}
					{
						textAtividade = new JTextField();
						painelAtividade.add(textAtividade);
						textAtividade.setBounds(74, 40, 244, 21);
					}
					{
						labelAtividade = new JLabel();
						painelAtividade.add(labelAtividade);
						labelAtividade.setText("Atividade:");
						labelAtividade.setBounds(13, 43, 49, 14);
					}
					{
						textTipo = new JTextField();
						painelAtividade.add(textTipo);
						textTipo.setBounds(76, 67, 242, 21);
					}
					{
						labelTipo = new JLabel();
						painelAtividade.add(labelTipo);
						labelTipo.setText("Tipo:");
						labelTipo.setBounds(13, 70, 24, 14);
					}
					{
						comboFuncionario = new JComboBox();
						painelAtividade.add(comboFuncionario);
						comboFuncionario.setBounds(76, 94, 214, 21);
					}
					{
						botaoFuncionario = new JButton();
						painelAtividade.add(botaoFuncionario);
						botaoFuncionario.setText("+");
						botaoFuncionario.setBounds(296, 94, 22, 21);
					}
					{
						labelFuncionario = new JLabel();
						painelAtividade.add(labelFuncionario);
						labelFuncionario.setText("Funcionário:");
						labelFuncionario.setBounds(13, 97, 59, 14);
					}
				}
				{
					botaoCancelar = new JButton();
					getContentPane().add(botaoCancelar);
					botaoCancelar.setText("Cancelar");
					botaoCancelar.setBounds(270, 450, 73, 21);
				}
				{
					botaoSalvar = new JButton();
					getContentPane().add(botaoSalvar);
					botaoSalvar.setText("Salvar");
					botaoSalvar.setBounds(192, 450, 73, 21);
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

		public FormHandle() {
			super();
			Peca peca = new Peca();
			Funcionario funcionario = new Funcionario();
			this.carregarComboFuncionario(funcionario.load());
			this.carregarComboPeca(peca.load());
			
			
			if (atividadePeca != null) {
				textCodigo.setText(atividadePeca.getAtividade().getCodigo()+"");
				textAtividade.setText(atividadePeca.getAtividade().getNome());
				textTipo.setText(atividadePeca.getAtividade().getTipo());
				comboPeca.setEditable(true);
				comboPeca.setSelectedItem((Object) atividadePeca.getAtividade().getFuncionario() );
				comboPeca.setEditable(false);
				comboFuncionario.setEditable(true);
				comboFuncionario.setSelectedItem((Object) atividadePeca.getPeca());
				comboFuncionario.setEditable(false);
				int cod = atividadePeca.getAtividade().getCodigo();
				carregarGrid(atividadePeca.getListaPeca(cod));				
			}
	}
		/**
		 * Carrega a Grid.
		 */
		private void carregarGrid(ListaObjeto listaObjeto) {
			Peca peca = new Peca();
			Object[][] gridArray = new Object[atividadePeca.getAtividade().getListaPeca().getSize()][3];
			for (int i = 0; i < atividadePeca.getAtividade().getListaPeca().getSize(); i++) {
				peca = (Peca) atividadePeca.getAtividade().getListaPeca().getObjeto(i);
				gridArray[i][0] = peca.getCodigo();
				gridArray[i][1] = peca.getNome();
				gridArray[i][2] = atividadePeca.getQuantidadePeca();
			}
			String[] campos = { "Codigo", "Peça", "Quantidade"};
			DefaultTableModel model = new DefaultTableModel(gridArray, campos);
			gridTabela.setModel(model);
			gridTabela.setShowVerticalLines(true);
			gridTabela.getColumnModel().getColumn(0).setPreferredWidth(50);
			gridTabela.getColumnModel().getColumn(1).setPreferredWidth(135);
			gridTabela.getColumnModel().getColumn(2).setPreferredWidth(70);			
		}
		private void addPeca(){
			Peca peca = (Peca) comboPeca.getSelectedItem();
			atividadePeca.getAtividade().getListaPeca().insertWhitoutPersist(peca);
			atividadePeca.setQuantidadePeca(Integer.parseInt(textQuantidade.getText()));


			Object[][] gridArray = new Object[atividadePeca.getAtividade().getListaPeca().getSize()][3];
			for (int i = 0; i < atividadePeca.getAtividade().getListaPeca().getSize(); i++) {
				peca = (Peca) atividadePeca.getAtividade().getListaPeca().getObjeto(i);
				gridArray[i][0] = peca.getCodigo();
				gridArray[i][1] = peca.getNome();
				gridArray[i][2] = atividadePeca.getQuantidadePeca();
			}
			String[] campos = { "Codigo", "Peça", "Quantidade"};
			DefaultTableModel model = new DefaultTableModel(gridArray, campos);
			gridTabela.setModel(model);
			gridTabela.setShowVerticalLines(true);
			gridTabela.getColumnModel().getColumn(0).setPreferredWidth(50);
			gridTabela.getColumnModel().getColumn(1).setPreferredWidth(135);
			gridTabela.getColumnModel().getColumn(2).setPreferredWidth(70);	
			
		}

		/**
		 * Carrega o Combobox com os Funcionarios Cadasrados.
		 * 
		 * @param listaObjeto
		 */
		private void carregarComboFuncionario(ListaObjeto listaObjeto) {
			Objeto[] comboArray = new Objeto[listaObjeto.getSize()];
			for (int i = 0; i < listaObjeto.getSize(); i++) {
				Funcionario funcionario = (Funcionario) listaObjeto.getObjeto(i);
				comboArray[i] = funcionario;
			}
			ComboBoxModel comboMarcaModel = new DefaultComboBoxModel(comboArray);
			comboFuncionario.setModel(comboMarcaModel);
		}

		/**
		 * Carrega o Combobox com as Peças cadastradas.
		 * 
		 * @param listaObjeto
		 */
		private void carregarComboPeca(ListaObjeto listaObjeto) {
			Objeto[] comboArray = new Objeto[listaObjeto.getSize()];
			for (int i = 0; i < listaObjeto.getSize(); i++) {
				Peca peca = (Peca) listaObjeto.getObjeto(i);
				comboArray[i] = peca;
			}
			ComboBoxModel comboMarcaModel = new DefaultComboBoxModel(comboArray);
			comboPeca.setModel(comboMarcaModel);
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
			if (e.getSource() == botaoCancelar) {
				dispose();				
			}else if (e.getSource() == botaoFuncionario) {
				showPainel(new PainelCliente(), "Cadastro de Funcionário");
				this.carregarComboFuncionario(atividadePeca.getAtividade().getFuncionario().load());				
			}else if (e.getSource() == botaoPeca) {
				showPainel(new PainelCliente(), "Cadastro de Peças");
				this.carregarComboFuncionario(atividadePeca.getPeca().load());				
			}else if (e.getSource() == botaoRemovePeca) {
			}else if (e.getSource() == botaoSalvar) {
			}else if (e.getSource() == botaoAddPeca) {
				this.addPeca();
			} 
			
		}

	/*----------------------------------------------------------
	 * FIM DE CLASSE LIMITROFE
	 *----------------------------------------------------------*/

}

}

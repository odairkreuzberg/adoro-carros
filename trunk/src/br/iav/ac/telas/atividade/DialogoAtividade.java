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

import br.iav.ac.negocio.Atividade;
import br.iav.ac.negocio.AtividadePeca;
import br.iav.ac.negocio.Funcionario;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.negocio.Objeto;
import br.iav.ac.negocio.Peca;
import br.iav.ac.negocio.PecaAtividade;
import br.iav.ac.telas.TelaPrincipal;
import br.iav.ac.telas.funcionario.PainelFuncionario;
import br.iav.ac.telas.padrao.DialogoCRUD;
import br.iav.ac.telas.padrao.PainelPadrao;
import br.iav.ac.telas.peca.PainelPeca;


public final class DialogoAtividade extends JDialog {
	private JLabel labelAtividade;
	private JLabel labelCodigo;
	private JLabel labelFuncionario;
	private JButton bodatoEditar;
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
	private Atividade atividade;
	private JTable gridTabela;
	private JLabel labelAviso;
	private JScrollPane scrollTabela;
	private FormHandle formHandle;
	
	public DialogoAtividade(TelaPrincipal frame, String titulo, boolean modal, Atividade atividade  ) {
		super(frame, titulo, modal);
		this.atividade = atividade;
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
		this.bodatoEditar.addActionListener(formHandle);
	}

	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				{
					painelPecas = new JPanel();
					getContentPane().add(painelPecas);
					painelPecas.setBounds(12, 151, 331, 299);
					painelPecas.setLayout(null);
					painelPecas.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
					{
						botaoRemovePeca = new JButton();
						painelPecas.add(botaoRemovePeca);
						botaoRemovePeca.setText("Remover");
						botaoRemovePeca.setBounds(235, 63, 83, 21);
					}
					{
						botaoAddPeca = new JButton();
						painelPecas.add(botaoAddPeca);
						botaoAddPeca.setText("Adicionar");
						botaoAddPeca.setBounds(45, 63, 84, 21);
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
						scrollTabela.setBounds(13, 104, 305, 185);
					}
					{
						bodatoEditar = new JButton();
						painelPecas.add(bodatoEditar);
						bodatoEditar.setText("Editar");
						bodatoEditar.setBounds(140, 63, 84, 21);
					}
					{
						labelAviso = new JLabel();
						painelPecas.add(labelAviso);
						labelAviso.setBounds(13, 84, 305, 14);
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
						textCodigo.setEditable(false);
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
					botaoCancelar.setBounds(152, 456, 90, 21);
				}
				{
					botaoSalvar = new JButton();
					getContentPane().add(botaoSalvar);
					botaoSalvar.setText("Salvar");
					botaoSalvar.setBounds(253, 456, 90, 21);
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
		ListaObjeto listaPeca = new ListaObjeto();
		PecaAtividade pecaAtividade = new PecaAtividade();
		

		public FormHandle() {
			super();
			Peca peca = new Peca();
			Funcionario funcionario = new Funcionario();
			this.carregarComboFuncionario(funcionario.load());
			this.carregarComboPeca(peca.load());			
			if (atividade.getCodigo() != 0) {
				textCodigo.setText(atividade.getCodigo()+"");
				textAtividade.setText(atividade.getNome());
				textTipo.setText(atividade.getTipo());
				comboFuncionario.setEditable(true);
				comboFuncionario.setSelectedItem((Object)atividade.getFuncionario());
				comboFuncionario.setEditable(false);
				AtividadePeca ap = new AtividadePeca();
				lista = ap.getListaPeca(atividade.getCodigo());
			}
			carregaLista();
			carregarGridPeca();				
	}
		
		private void carregaLista(){
			for (int i = 0; i < lista.getSize(); i++) {
				PecaAtividade pecaAtividade = new PecaAtividade();
				AtividadePeca atividadePeca = (AtividadePeca)lista.getObjeto(i);
				pecaAtividade.setCodigo(atividadePeca.getPeca().getCodigo());
				pecaAtividade.setPeca(atividadePeca.getPeca().getNome());
				pecaAtividade.setQtd(atividadePeca.getQuantidadePeca());
				listaPeca.insertWhitoutPersist(pecaAtividade);
			}	
			lista = null;
			lista = new ListaObjeto();
		}
		
		private void reCarregaLista(){
			for (int i = 0; i < listaPeca.getSize(); i++) {
				AtividadePeca atividadePeca = new AtividadePeca();
				PecaAtividade pecaAtividade = (PecaAtividade)listaPeca.getObjeto(i);
				atividadePeca.getPeca().setCodigo(pecaAtividade.getCodigo());
				atividadePeca.getPeca().setNome(pecaAtividade.getPeca());
				atividadePeca.setQuantidadePeca(pecaAtividade.getQtd());
				lista.insertWhitoutPersist(atividadePeca);
			}			
		}
		/**
		 * Carrega a Grid.
		 */
		private void carregarGridPeca() {
			PecaAtividade pecaAtividade = new PecaAtividade();
			Object[][] gridArray = new Object[listaPeca.getSize()][3];
			for (int i = 0; i < listaPeca.getSize(); i++) {
				pecaAtividade = (PecaAtividade)listaPeca.getObjeto(i);
				gridArray[i][0] = pecaAtividade.getCodigo();
				gridArray[i][1] = pecaAtividade.getPeca();
				gridArray[i][2] = pecaAtividade.getQtd();
			}
			String[] campos = { "Codigo", "Peça", "Quantidade"};
			DefaultTableModel model = new DefaultTableModel(gridArray, campos);
			gridTabela.setModel(model);
			gridTabela.setShowVerticalLines(true);
			gridTabela.getColumnModel().getColumn(0).setPreferredWidth(40);
			gridTabela.getColumnModel().getColumn(1).setPreferredWidth(165);
			gridTabela.getColumnModel().getColumn(2).setPreferredWidth(50);			
		}
		
		private void addPeca() {
			if(textQuantidade.getText().equals("")){
				labelAviso.setText("O Campo Quantidade é obrigatório!");	
			}else{
				try {
					int qtd = Integer.parseInt(textQuantidade.getText());
					PecaAtividade pecaAtividade = new PecaAtividade();
					pecaAtividade.setPeca(((Peca)comboPeca.getSelectedItem()).getNome());
					pecaAtividade.setCodigo(((Peca)comboPeca.getSelectedItem()).getCodigo());
					pecaAtividade.setQtd(Integer.parseInt(textQuantidade.getText()));
					if(listaPeca.search(pecaAtividade)== null){								
						listaPeca.insertWhitoutPersist(pecaAtividade);
						carregarGridPeca();	
						textQuantidade.setText("");
						comboPeca.setSelectedIndex(0);
						labelAviso.setText("");
					}else{
						labelAviso.setText("Esta Peça já se encontra na Lista!");			
					}
				} catch (Exception e2) {
					labelAviso.setText(textQuantidade.getText()+ " não é um valor válido!");
					textQuantidade.setText("");
				}
			}			
		}
		
		private void removerPeca() {
			if (gridTabela.getSelectedRow() >= 0) {
				int cod =(Integer) gridTabela.getValueAt(gridTabela.getSelectedRow(), 0);
				PecaAtividade pecaAtividade = new PecaAtividade();
				pecaAtividade.setCodigo(cod);
				listaPeca.delete(pecaAtividade);
				this.carregarGridPeca();				
				labelAviso.setText("");									
			}else{
				labelAviso.setText("Para Remover uma Peça é preciso selecionar um item na Tabela!");
			}
			
		}
		
		private void editarPeca() {
			if (gridTabela.getSelectedRow() >= 0) {
				Peca peca = new Peca();
				peca.setNome(gridTabela.getValueAt(gridTabela.getSelectedRow(),1)+"");
				peca.setCodigo((Integer)gridTabela.getValueAt(gridTabela.getSelectedRow(),0));
				comboPeca.setEditable(true);
				comboPeca.setSelectedItem((Object)peca);
				comboPeca.setEditable(false);				
				textQuantidade.setText(gridTabela.getValueAt(gridTabela.getSelectedRow(),2)+"");
				this.removerPeca();				
			}else{
				labelAviso.setText("Para Editar uma Peça é preciso selecionar um item na Tabela!");
			}
			
		}
		
		private void editar() {
			this.reCarregaLista();
			atividade.setFuncionario((Funcionario)comboFuncionario.getSelectedItem());
			atividade.setNome(textAtividade.getText());
			atividade.setTipo(textTipo.getText());
			atividade.setListaAtividadePeca(lista);			
			atividade.edit();
			
		}
		
		private void inserir() {
			this.reCarregaLista();
			atividade.setFuncionario((Funcionario)comboFuncionario.getSelectedItem());
			atividade.setNome(textAtividade.getText());
			atividade.setTipo(textTipo.getText());
			atividade.setListaAtividadePeca(lista);			
			atividade.insert();
			
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
				showPainel(new PainelFuncionario(), "Cadastro de Funcionário");
				this.carregarComboFuncionario(new Funcionario().load());				
			}else if (e.getSource() == botaoPeca) {
				showPainel(new PainelPeca(), "Cadastro de Peças");
				this.carregarComboFuncionario(new Peca().load());				
			}else if (e.getSource() == botaoRemovePeca) {
				this.removerPeca();
			}else if (e.getSource() == botaoAddPeca) {
				this.addPeca();
			}else if (e.getSource() == bodatoEditar) {
				this.editarPeca();
			}else if (e.getSource() == botaoSalvar) {
				if (atividade.getCodigo() == 0) {
					inserir();
				} 
				else {
					editar();
				}
				dispose();
			} 
			
		}

	/*----------------------------------------------------------
	 * FIM DE CLASSE LIMITROFE
	 *----------------------------------------------------------*/

}

}

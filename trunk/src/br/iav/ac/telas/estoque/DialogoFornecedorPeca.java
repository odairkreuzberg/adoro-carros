package br.iav.ac.telas.estoque;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.iav.ac.negocio.Fornecedor;
import br.iav.ac.negocio.FornecedorPeca;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.negocio.Objeto;
import br.iav.ac.negocio.Peca;
import br.iav.ac.telas.TelaPrincipal;
import br.iav.ac.telas.fornecedor.PainelFornecedor;
import br.iav.ac.telas.padrao.DialogoCRUD;
import br.iav.ac.telas.padrao.PainelPadrao;
import br.iav.ac.telas.peca.PainelPeca;


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
/**
 * Formulário de Compra
 * 
 * @author Odair Kreuzberg
 */
public class DialogoFornecedorPeca extends JDialog {

	/*----------------------------------------------------------
	 * ATTRIBUTOS
	 *----------------------------------------------------------*/
	private FormHandle formHandle;
	private JButton botaoPeca;
	private JButton botaoFornecedor;
	private JTextField textQtd;
	private JComboBox comboFornecedor;
	private JButton botaoCancelar;
	private JButton botaoComprar;
	private JLabel labelQtd;
	private JLabel labelValor;
	private JTextField textValor;
	private JLabel labelPeca;
	private JComboBox comboPeca;
	private JLabel labelFornecedor;

	/*----------------------------------------------------------
	 * FIM DE ATTRIBUTOS
	 *----------------------------------------------------------*/

	/*----------------------------------------------------------
	 * CONSTRUTOR
	 *----------------------------------------------------------*/

	public DialogoFornecedorPeca(JFrame frame, String titulo, boolean modal) {
		super(frame, titulo, modal);
		//25 de espaçamento entre cada atributo (JTextField e JLabel)
		int espacoEntreLinhas = 10;
		//Espaçamento dos JTextField
		int espacoDoTextField = 70;
		getContentPane().setLayout(null);
		{
			labelFornecedor = new JLabel();
			getContentPane().add(labelFornecedor);
			labelFornecedor.setText("Fornecedor:");
			labelFornecedor.setBounds(10, espacoEntreLinhas, 80, 20);
		}
		{
			comboFornecedor = new JComboBox();
			getContentPane().add(comboFornecedor);
			comboFornecedor.setBounds(espacoDoTextField, espacoEntreLinhas, 240, 20);
		}
		{
			botaoFornecedor = new JButton();
			getContentPane().add(botaoFornecedor);
			botaoFornecedor.setText("+");
			botaoFornecedor.setBounds(318, espacoEntreLinhas, 22, 20);
		}
		{
			espacoEntreLinhas = espacoEntreLinhas + 25;
			labelPeca = new JLabel();
			getContentPane().add(labelPeca);
			labelPeca.setText("Peça:");
			labelPeca.setBounds(10, espacoEntreLinhas, 80, 20);
		}
		{
			comboPeca = new JComboBox();
			getContentPane().add(comboPeca);
			comboPeca.setBounds(espacoDoTextField, espacoEntreLinhas, 240, 20);
		}
		{
			botaoPeca = new JButton();
			getContentPane().add(botaoPeca);
			botaoPeca.setText("+");
			botaoPeca.setBounds(318, espacoEntreLinhas, 22, 20);
		}
		{
			espacoEntreLinhas = espacoEntreLinhas + 25;
			labelValor = new JLabel();
			getContentPane().add(labelValor);
			labelValor.setText("Valor:");
			labelValor.setBounds(10, espacoEntreLinhas, 80, 20);
		}
		{
			textValor = new JTextField();
			getContentPane().add(textValor);
			textValor.setBounds(espacoDoTextField, espacoEntreLinhas, 270, 20);
		}
		{
			espacoEntreLinhas = espacoEntreLinhas + 25;
			labelQtd = new JLabel();
			getContentPane().add(labelQtd);
			labelQtd.setText("Quantidade:");
			labelQtd.setBounds(10, espacoEntreLinhas, 80, 20);
		}
		{
			textQtd = new JTextField();
			getContentPane().add(textQtd);
			textQtd.setBounds(espacoDoTextField, espacoEntreLinhas, 270, 20);
		}
		{
			espacoEntreLinhas = espacoEntreLinhas + 35;
			botaoComprar = new JButton();
			getContentPane().add(botaoComprar);
			botaoComprar.setText("Coprar");
			botaoComprar.setBounds(91, espacoEntreLinhas, 80, 21);
		}
		{
			botaoCancelar = new JButton();
			getContentPane().add(botaoCancelar);
			botaoCancelar.setText("Cancelar");
			botaoCancelar.setBounds(185, espacoEntreLinhas, 80, 21);
		}
		espacoEntreLinhas = espacoEntreLinhas + 60;
		this.setSize(356, espacoEntreLinhas);
		inicializarHandlers();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		inicializarHandlers();
	}

	/*----------------------------------------------------------
	 * FIM DE CONSTRUTOR
	 *----------------------------------------------------------*/

	/*----------------------------------------------------------
	 * METODOS DA CLASSE
	 *----------------------------------------------------------*/

	private void inicializarHandlers() {
		this.formHandle = new FormHandle();
		botaoComprar.addActionListener(formHandle);
		botaoCancelar.addActionListener(formHandle);
		botaoFornecedor.addActionListener(formHandle);
		botaoPeca.addActionListener(formHandle);
	}

	/*----------------------------------------------------------
	 * FIM DE METODOS DA CLASSE
	 *----------------------------------------------------------*/

	/*----------------------------------------------------------
	 * CLASSE LIMITROFE
	 *----------------------------------------------------------*/

	class FormHandle implements ActionListener {
		Fornecedor  fornecedor = new Fornecedor();
		Peca peca = new Peca();

		public FormHandle() {
			super();
			this.carregarComboFornecedor(this.fornecedor.load());
			this.carregarComboPeca(peca.load());

		}

		/**
		 * Carrega o Combobox com os Fornecedores Cadastrados.
		 * 
		 * @param listaObjeto
		 */
		private void carregarComboFornecedor(ListaObjeto listaObjeto) {
			Objeto[] comboArray = new Objeto[listaObjeto.getSize()];
			for (int i = 0; i < listaObjeto.getSize(); i++) {
				Fornecedor fornecedor = (Fornecedor) listaObjeto.getObjeto(i);
				comboArray[i] = fornecedor;
			}
			ComboBoxModel comboModeloModel = new DefaultComboBoxModel(
					comboArray);
			comboFornecedor.setModel(comboModeloModel);
		}

		/**
		 * Carrega o Combobox com as Peças Cadastradas.
		 * 
		 * @param listaObjeto
		 */
		private void carregarComboPeca(ListaObjeto listaObjeto) {
			Objeto[] comboArray = new Objeto[listaObjeto.getSize()];
			for (int i = 0; i < listaObjeto.getSize(); i++) {
				Peca peca = (Peca) listaObjeto.getObjeto(i);
				comboArray[i] = peca;
			}
			ComboBoxModel comboModeloModel = new DefaultComboBoxModel(
					comboArray);
			comboPeca.setModel(comboModeloModel);
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

		/**
		 * Busca no banco um Fornecedor.
		 * 
		 * @return Fornecedor
		 */
		private Fornecedor buscarFornecedor() {
			String codigo = String.valueOf(((Fornecedor) comboFornecedor.getSelectedItem()).getCodigo());
			ListaObjeto listaObjeto = this.fornecedor.search("Código", "Igual", codigo);
			this.fornecedor = (Fornecedor) listaObjeto.getObjeto(0);
			return this.fornecedor;

		}

		/**
		 * Busca no banco uma Peça.
		 * 
		 * @return Peca
		 */
		private Peca buscarPeca() {
			String codigo = String.valueOf(((Peca) comboPeca.getSelectedItem()).getCodigo());
			ListaObjeto listaObjeto = this.peca.search("Código", "Igual", codigo);
			this.peca = (Peca) listaObjeto.getObjeto(0);
			return this.peca;

		}
		
		private FornecedorPeca temFornecedorMarca(){
			FornecedorPeca fornecedorPeca = new FornecedorPeca();
			fornecedorPeca.setFornecedor((Fornecedor)comboFornecedor.getSelectedItem());
			fornecedorPeca.setPeca((Peca)comboPeca.getSelectedItem());
			return fornecedorPeca.temFornecedorMarca(fornecedorPeca) ;			
			
		}
		
		/**
		 * Insere uma Compra.
		 */
		private void inserir() {
			FornecedorPeca fornecedorPeca = temFornecedorMarca();
			if (fornecedorPeca!= null) {
				fornecedorPeca.setPreco(Float.parseFloat(textValor.getText()));
				int qtd = Integer.parseInt(textQtd.getText());
				fornecedorPeca.setQtd(fornecedorPeca.getQtd() + qtd);
				fornecedorPeca.edit();
			} else {
				fornecedorPeca = new FornecedorPeca();
				fornecedorPeca.setFornecedor(buscarFornecedor());
				fornecedorPeca.setPeca(buscarPeca());				
				fornecedorPeca.setPreco(Float.parseFloat(textValor.getText()));
				fornecedorPeca.setQtd(Integer.parseInt(textQtd.getText()));
				fornecedorPeca.insert();
			}
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == botaoFornecedor) {
				showPainel(new PainelFornecedor(), "Cadastro de Fornecedores");
				this.carregarComboFornecedor(this.fornecedor.load());
			} else if (e.getSource() == botaoPeca) {
				showPainel(new PainelPeca(), "Cadastro de Peças");
				this.carregarComboPeca(this.peca.load());
			} else if (e.getSource() == botaoComprar) {
				this.inserir();
				dispose();
			} else if (e.getSource() == botaoCancelar) {
				dispose();
			}
		}
	}

	/*----------------------------------------------------------
	 * FIM DE CLASSE LIMITROFE
	 *----------------------------------------------------------*/

}
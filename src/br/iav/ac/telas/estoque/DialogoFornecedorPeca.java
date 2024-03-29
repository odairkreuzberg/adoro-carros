package br.iav.ac.telas.estoque;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

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
 * Formul�rio de Compra
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
	private  JFormattedTextField textQtd;
	private JComboBox comboFornecedor;
	private JButton botaoCancelar;
	private JButton botaoComprar;
	private JLabel labelQtd;
	private JLabel labelValor;
	private JFormattedTextField textValor;
	private JLabel labelPeca;
	private JComboBox comboPeca;
	private JLabel labelFornecedor;
	private JLabel labelAviso;
	MaskFormatter mascQtd ;

	/*----------------------------------------------------------
	 * FIM DE ATTRIBUTOS
	 *----------------------------------------------------------*/

	/*----------------------------------------------------------
	 * CONSTRUTOR
	 *----------------------------------------------------------*/

	public DialogoFornecedorPeca(JFrame frame, String titulo, boolean modal) {
		super(frame, titulo, modal);
		//25 de espa�amento entre cada atributo (JTextField e JLabel)
		int espacoEntreLinhas = 10;
		//Espa�amento dos JTextField
		int espacoDoTextField = 80;
		getContentPane().setLayout(null);
		{
			labelFornecedor = new JLabel();
			getContentPane().add(labelFornecedor);
			labelFornecedor.setText("Fornecedor:*");
			labelFornecedor.setBounds(10, espacoEntreLinhas, 80, 20);
		}
		{
			comboFornecedor = new JComboBox();
			getContentPane().add(comboFornecedor);
			comboFornecedor.setBounds(espacoDoTextField, espacoEntreLinhas, 230, 20);
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
			labelPeca.setText("Pe�a:*");
			labelPeca.setBounds(10, espacoEntreLinhas, 80, 20);
		}
		{
			comboPeca = new JComboBox();
			getContentPane().add(comboPeca);
			comboPeca.setBounds(espacoDoTextField, espacoEntreLinhas, 230, 20);
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
			labelValor.setText("Valor:*");
			labelValor.setBounds(10, espacoEntreLinhas, 80, 20);
		}
		{  
			DecimalFormat  decimal = new DecimalFormat();  
		    NumberFormatter numFormatter = new NumberFormatter(decimal);  
		    numFormatter.setFormat(decimal);  
		    numFormatter.setAllowsInvalid(false);  
		    textValor = new JFormattedTextField();  
		    textValor .setFormatterFactory( new DefaultFormatterFactory(numFormatter)); 
			getContentPane().add(textValor);
			textValor.setBounds(espacoDoTextField, espacoEntreLinhas, 260, 20);
			
		}
		{
			espacoEntreLinhas = espacoEntreLinhas + 25;
			labelQtd = new JLabel();
			getContentPane().add(labelQtd);
			labelQtd.setText("Quantidade:*");
			labelQtd.setBounds(10, espacoEntreLinhas, 80, 20);
		}
		{
			textQtd = new JFormattedTextField(mascQtd);
			getContentPane().add(textQtd);
			textQtd.setBounds(espacoDoTextField, espacoEntreLinhas, 260, 20);
		}
		{
			labelAviso = new JLabel();
			getContentPane().add(labelAviso);
			labelAviso.setBounds(espacoDoTextField, espacoEntreLinhas+20, 270, 20);
		}
		{
			espacoEntreLinhas = espacoEntreLinhas + 45;
			botaoComprar = new JButton();
			getContentPane().add(botaoComprar);
			botaoComprar.setText("Comprar");
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
		 * Carrega o Combobox com as Pe�as Cadastradas.
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
			ListaObjeto listaObjeto = this.fornecedor.search("C�digo", "Igual", codigo);
			this.fornecedor = (Fornecedor) listaObjeto.getObjeto(0);
			return this.fornecedor;

		}

		/**
		 * Busca no banco uma Pe�a.
		 * 
		 * @return Peca
		 */
		private Peca buscarPeca() {
			String codigo = String.valueOf(((Peca) comboPeca.getSelectedItem()).getCodigo());
			ListaObjeto listaObjeto = this.peca.search("C�digo", "Igual", codigo);
			this.peca = (Peca) listaObjeto.getObjeto(0);
			return this.peca;

		}
		
		private FornecedorPeca temFornecedorPeca(){
			FornecedorPeca fornecedorPeca = new FornecedorPeca();
			fornecedorPeca.setFornecedor((Fornecedor)comboFornecedor.getSelectedItem());
			fornecedorPeca.setPeca((Peca)comboPeca.getSelectedItem());
			return fornecedorPeca.temFornecedorPeca(fornecedorPeca) ;			
			
		}
		
		/**
		 * Insere uma Compra.
		 */
		private void inserir() {
			FornecedorPeca fornecedorPeca = temFornecedorPeca();
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
				showPainel(new PainelPeca(), "Cadastro de Pe�as");
				this.carregarComboPeca(this.peca.load());
			} else if (e.getSource() == botaoComprar) {
				if (textValor.getText().equals("")) {
					labelAviso.setText("O Campo Valor � Obrigatotrio!");
				} else if (textQtd.getText().equals("")) {
					labelAviso.setText("O Campo Quantidade � Obrigatotrio!");
				}else{
					//try {
						int qtd = Integer.parseInt(textQtd.getText());
						this.inserir();
						dispose();
				//	} catch (Exception e2) {
						labelAviso.setText(textQtd.getText()+ " n�o � um N�mero V�lido");
				//	}
				}
			} else if (e.getSource() == botaoCancelar) {
				dispose();
			}
		}
	}

	/*----------------------------------------------------------
	 * FIM DE CLASSE LIMITROFE
	 *----------------------------------------------------------*/

}
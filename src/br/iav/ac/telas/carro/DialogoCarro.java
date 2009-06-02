package br.iav.ac.telas.carro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.iav.ac.negocio.Cliente;
import br.iav.ac.negocio.Cor;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.negocio.Marca;
import br.iav.ac.negocio.Modelo;
import br.iav.ac.negocio.Carro;
import br.iav.ac.negocio.Objeto;
import br.iav.ac.telas.TelaPrincipal;
import br.iav.ac.telas.cliente.PainelCliente;
import br.iav.ac.telas.cor.PainelCor;
import br.iav.ac.telas.marca.PainelMarca;
import br.iav.ac.telas.modelo.PainelModelo;
import br.iav.ac.telas.padrao.DialogoCRUD;
import br.iav.ac.telas.padrao.DialogoPadrao;
import br.iav.ac.telas.padrao.PainelPadrao;

/**
 * Formulário de Cadastro de Carro de um Veículo
 * 
 * @author Odair Kreuzberg
 */
public class DialogoCarro extends DialogoPadrao {

	/*----------------------------------------------------------
	 * ATTRIBUTOS
	 *----------------------------------------------------------*/

	private FormHandle formHandle;
	private Carro carro;
	private Modelo modelo;
	private Marca marca;
	private JTextField textCodigo;
	private JButton botaoModelo;
	private JButton botaoCor;
	private JComboBox comboModelo;
	private JComboBox comboCliente;
	private JLabel labelCor;
	private JComboBox comboCor;
	private JLabel labelCliente;
	private JLabel labelModelo;
	private JComboBox comboMarca;
	private JLabel labelMarca;
	private JButton botaoMarca;
	private JButton botaoCliente;
	private JLabel labelAno;
	private JTextField textAno;
	private JTextField textPlaca;
	private JLabel labelPlaca;
	private Cor cor;
	private Cliente cliente;

	/*----------------------------------------------------------
	 * FIM DE ATTRIBUTOS
	 *----------------------------------------------------------*/

	/*----------------------------------------------------------
	 * CONSTRUTOR
	 *----------------------------------------------------------*/

	public DialogoCarro(JFrame frame, String titulo, boolean modal, Carro carro) {
		super(frame, titulo, modal);
		this.carro = carro;
		//25 de espaçamento entre cada atributo (JTextField e JLabel)
		int espacoEntreLinhas = 10;
		//Espaçamento dos JTextField
		int espacoDoTextField = 60;
		try {
			{
				textCodigo = new JTextField();
				getPanelPrincipal().add(textCodigo);
				textCodigo.setBounds(espacoDoTextField, espacoEntreLinhas, 35, 20);
				textCodigo.setEditable(false);
			}
			{
				espacoEntreLinhas = espacoEntreLinhas + 25;
				labelCliente = new JLabel();
				getPanelPrincipal().add(labelCliente);
				labelCliente.setText("Cliente:");
				labelCliente.setBounds(10, espacoEntreLinhas, 80, 20);
			}
			{
				comboCliente = new JComboBox();
				getPanelPrincipal().add(comboCliente);
				comboCliente.setBounds(espacoDoTextField, espacoEntreLinhas, 233, 20);
			}
			{
				botaoCliente = new JButton();
				getPanelPrincipal().add(botaoCliente);
				botaoCliente.setText("+");
				botaoCliente.setBounds(306, espacoEntreLinhas, 22, 20);
			}
			{
				espacoEntreLinhas = espacoEntreLinhas + 25;
				labelCor = new JLabel();
				getPanelPrincipal().add(labelCor);
				labelCor.setText("Cor:");
				labelCor.setBounds(10, espacoEntreLinhas, 80, 20);
			}
			{
				comboCor = new JComboBox();
				getPanelPrincipal().add(comboCor);
				comboCor.setBounds(espacoDoTextField, espacoEntreLinhas, 233, 20);
			}
			{
				botaoCor = new JButton();
				getPanelPrincipal().add(botaoCor);
				botaoCor.setText("+");
				botaoCor.setBounds(306, espacoEntreLinhas, 22, 20);
			}
			{
				espacoEntreLinhas = espacoEntreLinhas + 25;
				labelMarca = new JLabel();
				getPanelPrincipal().add(labelMarca);
				labelMarca.setText("Marca:");
				labelMarca.setBounds(10, espacoEntreLinhas, 80, 20);
			}
			{
				comboMarca = new JComboBox();
				getPanelPrincipal().add(comboMarca);
				comboMarca.setBounds(espacoDoTextField, espacoEntreLinhas, 233, 20);
			}
			{
				botaoMarca = new JButton();
				getPanelPrincipal().add(botaoMarca);
				botaoMarca.setText("+");
				botaoMarca.setBounds(306, espacoEntreLinhas, 22, 20);
			}
			{
				espacoEntreLinhas = espacoEntreLinhas + 25;
				labelModelo = new JLabel();
				getPanelPrincipal().add(labelModelo);
				labelModelo.setText("Modelo:");
				labelModelo.setBounds(10, espacoEntreLinhas, 80, 20);
			}
			{
				comboModelo = new JComboBox();
				getPanelPrincipal().add(comboModelo);
				comboModelo.setBounds(espacoDoTextField, espacoEntreLinhas, 233, 20);
			}
			{
				botaoModelo = new JButton();
				getPanelPrincipal().add(botaoModelo);
				botaoModelo.setText("+");
				botaoModelo.setBounds(306, espacoEntreLinhas, 22, 20);
			}
			{
				espacoEntreLinhas = espacoEntreLinhas + 25;
				labelPlaca = new JLabel();
				getPanelPrincipal().add(labelPlaca);
				labelPlaca.setText("Placa:");
				labelPlaca.setBounds(10, espacoEntreLinhas, 80, 20);
			}
			{
				textPlaca = new JTextField();
				getPanelPrincipal().add(textPlaca);
				textPlaca.setBounds(espacoDoTextField, espacoEntreLinhas, 267, 20);
			}
			{
				espacoEntreLinhas = espacoEntreLinhas + 25;
				labelAno = new JLabel();
				getPanelPrincipal().add(labelAno);
				labelAno.setText("Ano:");
				labelAno.setBounds(10, espacoEntreLinhas, 80, 20);
			}
			{
				textAno = new JTextField();
				getPanelPrincipal().add(textAno);
				textAno.setBounds(espacoDoTextField, espacoEntreLinhas, 267, 20);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		inicializarHandlers();
		this.setSize(348, 246);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/*----------------------------------------------------------
	 * FIM DE CONSTRUTOR
	 *----------------------------------------------------------*/

	/*----------------------------------------------------------
	 * INTERFACE
	 *----------------------------------------------------------*/

	private void inicializarHandlers() {
		this.formHandle = new FormHandle();
		getBotaoCancelar().addActionListener(formHandle);
		getBotaoConfirmar().addActionListener(formHandle);
		botaoCliente.addActionListener(formHandle);
		botaoCor.addActionListener(formHandle);
		botaoMarca.addActionListener(formHandle);
		botaoModelo.addActionListener(formHandle);
		comboMarca.addItemListener(formHandle);
	}

	/*----------------------------------------------------------
	 * FIM DE INTERFACE
	 *----------------------------------------------------------*/

	/*----------------------------------------------------------
	 * CLASSE LIMITROFE
	 *----------------------------------------------------------*/

	class FormHandle implements ActionListener, ItemListener {

		public FormHandle() {
			super();
			marca = new Marca();
			modelo = new Modelo();
			cliente = new Cliente();
			cor = new Cor();
			this.carregarComboMarca(marca.load());
			this.carregarComboModelo(modelo.search("Marca", "Igual", ((Marca)comboMarca.getSelectedItem()).getNome()) );/*
			
			if (comboMarca.getSelectedIndex() != -1){
				this.carregarComboModelo(modelo.search("Marca", "Igual", ((Marca)comboMarca.getSelectedItem()).getNome()) );
			}else{
				this.carregarComboModelo(modelo.search("Marca", "Igual", ""));
			}*/
			this.carregarComboCor(cor.load());
			this.carregarComboCliente(cliente.load());
			if (carro.getCodigo() != 0) {
				textCodigo.setText(String.valueOf(carro.getCodigo()));
				textAno.setText(carro.getAnoFabricacao());
				textPlaca.setText(carro.getPlaca());
				comboCliente.setEditable(true);
				comboCliente.setSelectedItem((Object) carro.getCliente());
				comboCliente.setEditable(false);
				comboMarca.setEditable(true);
				comboMarca.setSelectedItem((Object) carro.getModelo().getMarca());
				comboMarca.setEditable(false);
				comboModelo.setEditable(true);
				comboModelo.setSelectedItem((Object) carro.getModelo());
				comboModelo.setEditable(false);
				comboCor.setEditable(true);
				comboCor.setSelectedItem((Object) carro.getCor());
				comboCor.setEditable(false);
			}

		}
		
		/*----------------------------------------------------------
		 * METODOS QUE CARREGAM OS COMBOBOX
		 *----------------------------------------------------------*/

		/**
		 * Carrega o Combobox com os nomes de todas as modelos cadastradas.
		 * 
		 * @param listaObjeto
		 */
		private void carregarComboMarca(ListaObjeto listaObjeto) {
			Objeto[] comboArray = new Objeto[listaObjeto.getSize()];
			for (int i = 0; i < listaObjeto.getSize(); i++) {
				Marca marca = (Marca) listaObjeto.getObjeto(i);
				comboArray[i] = marca;
			}
			ComboBoxModel comboMarcaModel = 
				new DefaultComboBoxModel(comboArray);
			comboMarca.setModel(comboMarcaModel);
		}

		/**
		 * Carrega o Combobox com os nomes dos modelos que pertencem a uma Marca.
		 * 
		 * @param listaObjeto
		 */
		private void carregarComboModelo(ListaObjeto listaObjeto) {
			Objeto[] comboArray = new Objeto[listaObjeto.getSize()];
			for (int i = 0; i < listaObjeto.getSize(); i++) {
				Modelo modelo = (Modelo) listaObjeto.getObjeto(i);
				comboArray[i] = modelo;
			}
			ComboBoxModel comboModeloModel = new DefaultComboBoxModel(
					comboArray);
			comboModelo.setModel(comboModeloModel);
		}

		/**
		 * Carrega o Combobox com os nomes dos modelos que pertencem a uma Marca.
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
		 * Carrega o Combobox com os nomes dos modelos que pertencem a uma Marca.
		 * 
		 * @param listaObjeto
		 */
		private void carregarComboCor(ListaObjeto listaObjeto) {
			Objeto[] comboArray = new Objeto[listaObjeto.getSize()];
			for (int i = 0; i < listaObjeto.getSize(); i++) {
				Cor cor = (Cor) listaObjeto.getObjeto(i);
				comboArray[i] = cor;
			}
			ComboBoxModel comboModeloModel = new DefaultComboBoxModel(comboArray);
			comboCor.setModel(comboModeloModel);
		}

		/*----------------------------------------------------------
		 * FIM DE METODOS QUE CARREGAM OS COMBOBOX
		 *----------------------------------------------------------*/


		/**
		 * Retorna true se encontrar um carro e false se nao encontrar.
		 * 
		 * @return boolean
		 */
		private boolean existeCarro() {
			ListaObjeto listaObjeto = carro.search("Carro", "Igual", textPlaca.getText().trim());
			if (listaObjeto.getSize() > 0) {
				return false;
			}
			return true;
		}

		/**
		 * Faz a Inserção de um Carro.
		 */
		private void inserir() {
			carro.setAnoFabricacao(textAno.getText().trim());
			carro.setPlaca(textPlaca.getText().trim());
			carro.setCliente(buscarCliente());
			carro.setCor(buscarCor());
			carro.setModelo(buscarModelo());
			carro.insert();
		}

		/**
		 * Faz a Edição de um Carro.
		 */
		private void editar() {
			carro.setAnoFabricacao(textAno.getText().trim());
			carro.setPlaca(textPlaca.getText().trim());
			carro.setCliente(buscarCliente());
			carro.setCor(buscarCor());
			carro.setModelo(buscarModelo());
			carro.edit();
		}

		/**
		 * Busca no banco uma Modelo
		 * 
		 * @return Modelo
		 */
		private Modelo buscarModelo() {
			String codigo = String.valueOf(((Modelo) comboModelo.getSelectedItem()).getCodigo());
			ListaObjeto listaObjeto = modelo.search("Código", "Igual", codigo);
			modelo = (Modelo) listaObjeto.getObjeto(0);
			return modelo;

		}

		/**
		 * Busca no banco uma Cor
		 * 
		 * @return Modelo
		 */
		private Cor buscarCor() {
			String codigo = String.valueOf(((Cor) comboCor.getSelectedItem()).getCodigo());
			ListaObjeto listaObjeto = cor.search("Código", "Igual", codigo);
			cor = (Cor) listaObjeto.getObjeto(0);
			return cor;

		}

		/**
		 * Busca no banco uma Cor
		 * 
		 * @return Modelo
		 */
		private Cliente buscarCliente() {
			String codigo = String.valueOf(((Cliente) comboCliente.getSelectedItem()).getCodigo());
			ListaObjeto listaObjeto = cliente.search("Código", "Igual", codigo);
			cliente = (Cliente) listaObjeto.getObjeto(0);
			return cliente;

		}

		/**
		 * Instancia o Caso de Uso Modelo.
		 * 
		 * @param painelPadrao
		 * @param titulo
		 */
		private void showPainel(PainelPadrao painelPadrao, String titulo) {

			DialogoCRUD dialogoCRUD = new DialogoCRUD(TelaPrincipal.instancia,
					titulo, true);
			dialogoCRUD.setPainel(painelPadrao);

		}

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == botaoCliente) {
				showPainel(new PainelCliente(), "Cadastros de Cliente");
				this.carregarComboCliente(cliente.load());

			}else if (e.getSource() == botaoCor) {
				showPainel(new PainelCor(), "Cadastros de Cor");
				this.carregarComboCor(cor.load());

			}else if (e.getSource() == botaoMarca) {
				showPainel(new PainelMarca(), "Cadastros de Marca");
				this.carregarComboMarca(marca.load());

			}else if (e.getSource() == botaoModelo) {
				showPainel(new PainelModelo(), "Cadastros de Modelo");
				this.carregarComboModelo(modelo.search("Marca", "Igual", ((Marca) comboMarca.getSelectedItem()).getNome()));

			} else if (e.getSource() == getBotaoCancelar()) {
				dispose();
			} else if (e.getSource() == getBotaoConfirmar()) {
				if (textPlaca.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoCarro.this,
							"O campo Carro é obrigatório!");
				} else {
					if (carro.getCodigo() == 0) {
						inserir();
					} else {
						editar();
					}
					dispose();
				}
			}
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (comboMarca.getSelectedIndex() != -1){
				carregarComboModelo(modelo.search("Marca", "Igual",	((Marca) comboMarca.getSelectedItem()).getNome()));
			}else{
				this.carregarComboModelo(modelo.search("Marca", "Igual", ""));
			}
		}
	}

	/*----------------------------------------------------------
	 * FIM DE CLASSE LIMITROFE
	 *----------------------------------------------------------*/

}
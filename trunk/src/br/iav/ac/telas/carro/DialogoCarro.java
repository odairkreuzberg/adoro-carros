package br.iav.ac.telas.carro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.negocio.Marca;
import br.iav.ac.negocio.Modelo;
import br.iav.ac.negocio.Carro;
import br.iav.ac.telas.TelaPrincipal;
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
	private JButton botaoModelo;
	private JComboBox comboModelo;
	private JComboBox comboCliente;
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

	/*----------------------------------------------------------
	 * FIM DE ATTRIBUTOS
	 *----------------------------------------------------------*/

	/*----------------------------------------------------------
	 * CONSTRUTOR
	 *----------------------------------------------------------*/

	public DialogoCarro(JFrame frame, String titulo, boolean modal, Carro carro) {
		super(frame, titulo, modal);
		this.carro = carro;
		try {
			{
				labelCliente = new JLabel();
				getPanelPrincipal().add(labelCliente);
				labelCliente.setText("Cliente:");
				labelCliente.setBounds(12, 38, 37, 14);
			}
			{
				comboCliente = new JComboBox();
				getPanelPrincipal().add(comboCliente);
				comboCliente.setBounds(61, 35, 233, 21);
			}
			{
				botaoCliente = new JButton();
				getPanelPrincipal().add(botaoCliente);
				botaoCliente.setText("+");
				botaoCliente.setBounds(306, 35, 22, 21);
			}
			{
				labelMarca = new JLabel();
				getPanelPrincipal().add(labelMarca);
				labelMarca.setText("Marca:");
				labelMarca.setBounds(17, 72, 33, 14);
			}
			{
				comboMarca = new JComboBox();
				getPanelPrincipal().add(comboMarca);
				comboMarca.setBounds(61, 67, 233, 21);
			}
			{
				botaoMarca = new JButton();
				getPanelPrincipal().add(botaoMarca);
				botaoMarca.setText("+");
				botaoMarca.setBounds(306, 67, 22, 21);
			}
			{
				labelModelo = new JLabel();
				getPanelPrincipal().add(labelModelo);
				labelModelo.setText("Modelo:");
				labelModelo.setBounds(11, 100, 38, 14);
			}
			{
				comboModelo = new JComboBox();
				getPanelPrincipal().add(comboModelo);
				;
				comboModelo.setBounds(61, 97, 233, 21);
			}
			{
				botaoModelo = new JButton();
				getPanelPrincipal().add(botaoModelo);
				botaoModelo.setText("+");
				botaoModelo.setBounds(306, 97, 22, 21);
			}
			{
				labelPlaca = new JLabel();
				getPanelPrincipal().add(labelPlaca);
				labelPlaca.setText("Placa:");
				labelPlaca.setBounds(20, 133, 29, 14);
			}
			{
				textPlaca = new JTextField();
				getPanelPrincipal().add(textPlaca);
				textPlaca.setBounds(61, 130, 267, 21);
			}
			{
				labelAno = new JLabel();
				getPanelPrincipal().add(labelAno);
				labelAno.setText("Ano:");
				labelAno.setBounds(26, 163, 23, 14);
			}
			{
				textAno = new JTextField();
				getPanelPrincipal().add(textAno);
				textAno.setBounds(61, 160, 267, 21);
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
		botaoModelo.addActionListener(formHandle);
	}

	/*----------------------------------------------------------
	 * FIM DE INTERFACE
	 *----------------------------------------------------------*/

	/*----------------------------------------------------------
	 * CLASSE LIMITROFE
	 *----------------------------------------------------------*/

	class FormHandle implements ActionListener {

		public FormHandle() {
			super();
			marca = new Marca();
			this.carregarComboModelo(marca.load());
			// comboMarca.setSelectedItem((Object) carro.getModelo().getNome());

			if (carro.getCodigo() != 0) {
				getTextCodigo().setText(String.valueOf(carro.getCodigo()));
			}

		}

		/**
		 * Retorna true se encontrar um carro e false se nao encontrar.
		 * 
		 * @return boolean
		 */
		private boolean existeCarro() {
			ListaObjeto listaObjeto = carro.search("Carro", "Igual", textPlaca
					.getText().trim());
			if (listaObjeto.getSize() > 0) {
				return false;
			}
			return true;
		}

		/**
		 * Faz a Inserção de um Carro.
		 */
		private void inserir() {
			if (existeCarro()) {
				// carro.setNome(textCarro.getText());
				carro.setModelo(buscarModelo());
				carro.insert();
				carregarComboModelo(modelo.load());
			} else {
				JOptionPane.showMessageDialog(DialogoCarro.this,
						"Essa cor já se encontra na Base de Dados!");
			}
		}

		/**
		 * Faz a Edição de um Carro.
		 */
		private void editar() {
			if (existeCarro()) {
				// carro.setNome(textCarro.getText().trim());
				carro.setModelo(buscarModelo());
				JOptionPane.showMessageDialog(DialogoCarro.this, buscarModelo()
						.getNome());

				carro.edit();
			} else {
				JOptionPane.showMessageDialog(DialogoCarro.this,
						"Essa cor já se encontra na Base de Dados!");
			}
		}

		/**
		 * Carrega o Combobox com os nomes de todas as modelos cadastradas.
		 * 
		 * @param listaObjeto
		 */
		private void carregarComboModelo(ListaObjeto listaObjeto) {
			String[] comboArray = new String[listaObjeto.getSize()];
			for (int i = 0; i < listaObjeto.getSize(); i++) {
				Marca marca = (Marca) listaObjeto.getObjeto(i);
				comboArray[i] = marca.getNome();
			}
			ComboBoxModel comboModeloModel = new DefaultComboBoxModel(
					comboArray);
			comboMarca.setModel(comboModeloModel);
		}

		/**
		 * Busca no banco uma Modelo
		 * 
		 * @return Modelo
		 */
		private Modelo buscarModelo() {
			ListaObjeto listaObjeto = modelo.search("modelo", "Igual",
					(String) comboModelo.getSelectedItem());
			modelo = (Modelo) listaObjeto.getObjeto(0);
			return modelo;

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

			if (e.getSource() == botaoMarca) {
				showPainel(new PainelModelo(), "Cadastros de Marca");
				this.carregarComboModelo(marca.load());

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
	}

	/*----------------------------------------------------------
	 * FIM DE CLASSE LIMITROFE
	 *----------------------------------------------------------*/

}
package br.iav.ac.telas.modelo;

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
import br.iav.ac.negocio.Objeto;
import br.iav.ac.telas.TelaPrincipal;
import br.iav.ac.telas.cor.DialogoCor;
import br.iav.ac.telas.marca.PainelMarca;
import br.iav.ac.telas.padrao.DialogoCRUD;
import br.iav.ac.telas.padrao.DialogoPadrao;
import br.iav.ac.telas.padrao.PainelPadrao;

/**
 * Formulário de Cadastro de Modelo de um Veículo
 * 
 * @author Odair Kreuzberg
 */
public class DialogoModelo extends DialogoPadrao {

	private FormHandle formHandle;
	private Modelo modelo;
	private Marca marca;
	private JTextField textCodigo;
	private JButton botaoMarca;
	private JTextField textModelo;
	private JLabel labelModelo;
	private JComboBox comboMarca;
	private JLabel labelMarca;

	public DialogoModelo(JFrame frame, String titulo, boolean modal, Modelo modelo) {
		super(frame, titulo, modal);
		this.modelo = modelo;
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
				labelMarca = new JLabel();
				getPanelPrincipal().add(labelMarca);
				labelMarca.setText("Marca:*");
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
				labelModelo.setText("Modelo:*");
				labelModelo.setBounds(10, espacoEntreLinhas, 80, 20);
			}
			{
				textModelo = new JTextField();
				getPanelPrincipal().add(textModelo);
				textModelo.setText(modelo.getNome());
				textModelo.setBounds(espacoDoTextField, espacoEntreLinhas, 267, 20);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		inicializarHandlers();
		this.setSize(350, 160);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void inicializarHandlers() {
		this.formHandle = new FormHandle();
		getBotaoCancelar().addActionListener(formHandle);
		getBotaoConfirmar().addActionListener(formHandle);
		botaoMarca.addActionListener(formHandle);
	}
	
	class FormHandle implements ActionListener {

		private DialogoCRUD dialogoCRUD;
		
		public FormHandle() {
			super();
			marca = new Marca();
			this.carregarComboMarca(marca.load());
			comboMarca.setEditable(true);
			comboMarca.setSelectedItem((Object) modelo.getMarca());
			comboMarca.setEditable(false);
			if (modelo.getCodigo() != 0) {
				textCodigo.setText(String.valueOf(modelo.getCodigo()));
			}
		}

		/**
		 * Carrega o Combobox com os nomes de todas as marcas cadastradas.
		 * 
		 * @param listaObjeto
		 */
		private void carregarComboMarca(ListaObjeto listaObjeto) {
			Objeto[] comboArray = new Objeto[listaObjeto.getSize()];
			for (int i = 0; i < listaObjeto.getSize(); i++) {
				Marca marca = (Marca) listaObjeto.getObjeto(i);
				comboArray[i] = marca;
			}
			ComboBoxModel comboMarcaModel = new DefaultComboBoxModel(comboArray);
			comboMarca.setModel(comboMarcaModel);
		}
		
		/**
		 * Busca no banco uma Marca
		 * @return Marca
		 */
		private Marca buscarMarca(){
			ListaObjeto listaObjeto = marca.search("marca","Igual",((Marca)comboMarca.getSelectedItem()).getNome());
			marca = (Marca)listaObjeto.getObjeto(0);
			return marca;
		}
		
		private boolean validarCampos() {
		if (textModelo.getText().trim().length()>50) {
			JOptionPane.showMessageDialog(DialogoModelo.this, "O campo Nome estourou o limite de Caracter!");
			textModelo.requestFocus();
			return false;
		}else if (comboMarca.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(DialogoModelo.this, "O campo Marca é obrigatorio!");
			comboMarca.requestFocus();
			return false;
		}else if (textModelo.getText().equals("")) {
			JOptionPane.showMessageDialog(DialogoModelo.this, "O campo Nome é obrigatorio!");
			textModelo.requestFocus();
			return false;
		}
		return true;
			
		}

		//Faz a Inserção de um Modelo.
		private void inserir(){	
			if(validarCampos()){
				modelo.setNome(textModelo.getText().trim());
				modelo.setMarca(buscarMarca());
				if(modelo.search("Modelo", "Igual", modelo.getNome()).getSize() == 0){					
					modelo.insert();
					dispose();
				} else {
					JOptionPane.showMessageDialog(DialogoModelo.this, "Este Modelo já se Encontra na Base de Dados!");					
				}
			}
		}

		//Faz a Edição de um Modelo.
		private void editar() {
			if (validarCampos()) {
				modelo.setNome(textModelo.getText().trim());
				modelo.setMarca(buscarMarca());
				if (!modelo.existeModelo(modelo)) {
					modelo.edit();
					dispose();
				} else {
					JOptionPane.showMessageDialog(DialogoModelo.this, "Este Modelo já se Encontra na Base de Dados!");					
				}
			}
		}
		
		/**
		 * Instancia o Caso de Uso Marca.
		 * @param painelPadrao
		 * @param titulo
		 */
		private void showPainel(PainelPadrao painelPadrao, String titulo) {
			dialogoCRUD = new DialogoCRUD(TelaPrincipal.instancia,titulo,true);
			dialogoCRUD.setPainel(painelPadrao);
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == botaoMarca) {				
				showPainel(new PainelMarca(), "Cadastro de Marcas");	
				this.carregarComboMarca(marca.load());
			}
			else if (e.getSource() == getBotaoCancelar()) {
				dispose();
			} else if (e.getSource() == getBotaoConfirmar()) {
				if (modelo.getCodigo() == 0) {
					inserir();
				} else {
					editar();
				}
			}
		}
	}
}
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

	/*----------------------------------------------------------
	 * ATTRIBUTOS
	 *----------------------------------------------------------*/

	private FormHandle formHandle;
	private Modelo modelo;
	private Marca marca;
	private JTextField textCodigo;
	private JButton botaoMarca;
	private JTextField textModelo;
	private JLabel labelModelo;
	private JComboBox comboMarca;
	private JLabel labelMarca;

	/*----------------------------------------------------------
	 * FIM DE ATTRIBUTOS
	 *----------------------------------------------------------*/

	/*----------------------------------------------------------
	 * CONSTRUTOR
	 *----------------------------------------------------------*/

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
		botaoMarca.addActionListener(formHandle);
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
			this.carregarComboMarca(marca.load());
			comboMarca.setEditable(true);
			comboMarca.setSelectedItem((Object) modelo.getMarca());
			comboMarca.setEditable(false);
			if (modelo.getCodigo() != 0) {
				textCodigo.setText(String.valueOf(modelo.getCodigo()));
			}
			
		}
		private DialogoCRUD dialogoCRUD;
		
		/**
		 * Retorna true se encontrar um modelo e false se nao encontrar.
		 * 
		 * @return boolean
		 */
		private boolean existeModelo() {
			ListaObjeto listaObjeto = modelo.search("Modelo", "Igual", textModelo
					.getText().trim());
			if (listaObjeto.getSize() > 0) {
				return false;
			}
			return true;
		}
		
		/**
		 * Faz a Inserção de um Modelo.
		 */		
		private void inserir(){
			if (existeModelo()) {
				modelo.setNome(textModelo.getText());
				modelo.setMarca(buscarMarca());
				modelo.insert();		
				carregarComboMarca(marca.load());							
			} else {
				JOptionPane.showMessageDialog(DialogoModelo.this, 
						"Esse modelo já se encontra na Base de Dados!");
			}			
		}
		
		/**
		 * Faz a Edição de um Modelo.
		 */
		private void editar(){
			if (!existeModelo()) {
				modelo.setNome(textModelo.getText().trim());
				modelo.setMarca(buscarMarca());
				JOptionPane.showMessageDialog(DialogoModelo.this,buscarMarca().getNome());
				modelo.edit();	
			} else {
				JOptionPane.showMessageDialog(DialogoModelo.this, 
						"Esse modelo já se encontra na Base de Dados!");
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
				showPainel(new PainelMarca(),"Cadastros de Marcas");	
				this.carregarComboMarca(marca.load());
				
			}
			else if (e.getSource() == getBotaoCancelar()) {
			} 
			else if (e.getSource() == getBotaoConfirmar()) {
//				for (int i = 0; i<comboMarca.getItemCount(); i++) {
//					if (modelo.getMarca() == comboMarca.getItemAt(i)) {
//						comboMarca.setSelectedIndex(i);
//					}
//				}
				if (textModelo.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoModelo.this,"O campo Modelo é obrigatório!");
				} 
				else if ( (( modelo.getCodigo() == 0 ) && (comboMarca.getSelectedIndex() == -1)) || 
						   ((modelo.getCodigo() != 0 ) && (comboMarca.getSelectedItem() == null)) ) {
					JOptionPane.showMessageDialog(DialogoModelo.this,"O campo Marca é obrigatório!");
				} 				
				else {
					if (modelo.getCodigo() == 0) {
						inserir();
					} 
					else {
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
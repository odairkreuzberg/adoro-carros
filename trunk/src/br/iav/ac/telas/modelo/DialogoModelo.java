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
		try {
			{
				labelMarca = new JLabel();
				getPanelPrincipal().add(labelMarca);
				labelMarca.setText("Marca:");
				labelMarca.setBounds(17, 40, 33, 14);
			}
			{
				comboMarca = new JComboBox();
				getPanelPrincipal().add(comboMarca);
				comboMarca.setBounds(61, 37, 233, 21);
			}
			{
				labelModelo = new JLabel();
				getPanelPrincipal().add(labelModelo);
				labelModelo.setText("Modelo:");
				labelModelo.setBounds(11, 72, 38, 14);
			}
			{
				textModelo = new JTextField();
				getPanelPrincipal().add(textModelo);
				textModelo.setText(modelo.getNome());
				textModelo.setBounds(61, 67, 267, 21);
			}
			{
				botaoMarca = new JButton();
				getPanelPrincipal().add(botaoMarca);
				botaoMarca.setText("+");
				botaoMarca.setBounds(306, 37, 22, 21);
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
			comboMarca.setSelectedItem((Object) modelo.getMarca().getNome());

			if (modelo.getCodigo() != 0) {
				getLabelCodigo().setText(
						getLabelCodigo().getText() + "  " + modelo.getCodigo());
			}
	        
		}
		
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
						"Essa cor já se encontra na Base de Dados!");
			}			
		}
		
		/**
		 * Faz a Edição de um Modelo.
		 */
		private void editar(){
			if (existeModelo()) {
				modelo.setNome(textModelo.getText().trim());
				modelo.setMarca(buscarMarca());
				JOptionPane.showMessageDialog(DialogoModelo.this, 
						buscarMarca().getNome());
				
				modelo.edit();	
			} else{
				JOptionPane.showMessageDialog(DialogoModelo.this, 
						"Essa cor já se encontra na Base de Dados!");
			}
		}

		/**
		 * Carrega o Combobox com os nomes de todas as marcas cadastradas.
		 * 
		 * @param listaObjeto
		 */
		private void carregarComboMarca(ListaObjeto listaObjeto) {
			String[] comboArray = new String[listaObjeto.getSize()];
			for (int i = 0; i < listaObjeto.getSize(); i++) {
				Marca marca = (Marca) listaObjeto.getObjeto(i);
				comboArray[i] = marca.getNome();
				System.out.println(modelo.getMarca().getNome()+"bla");
			}
			ComboBoxModel comboMarcaModel = 
				new DefaultComboBoxModel(comboArray);
			comboMarca.setModel(comboMarcaModel);
		}
		
		/**
		 * Busca no banco uma Marca
		 * @return Marca
		 */
		private Marca buscarMarca(){
			ListaObjeto listaObjeto = marca.search("marca","Igual",(String)comboMarca.getSelectedItem());
			marca = (Marca)listaObjeto.getObjeto(0);
			return marca;
			
						
		}
		
		/**
		 * Instancia o Caso de Uso Marca.
		 * @param painelPadrao
		 * @param titulo
		 */
		private void showPainel(PainelPadrao painelPadrao, String titulo) {
			
			DialogoCRUD dialogoCRUD = new DialogoCRUD(TelaPrincipal.instancia,titulo,true);
			dialogoCRUD.setPainel(painelPadrao);

		}

		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == botaoMarca) {				
				showPainel(new PainelMarca(),"Cadastros de Marcas");	
				this.carregarComboMarca(marca.load());
				
			}
			else if (e.getSource() == getBotaoCancelar()) {
				dispose();
			} 
			else if (e.getSource() == getBotaoConfirmar()) {
				if (textModelo.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoModelo.this,
							"O campo Modelo é obrigatório!");
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
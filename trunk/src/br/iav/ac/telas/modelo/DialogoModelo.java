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
				textModelo.setBounds(61, 67, 267, 21);
			}
			{
				botaoMarca = new JButton();
				getPanelPrincipal().add(botaoMarca);
				botaoMarca.setText("+");
				botaoMarca.setBounds(306, 37, 22, 21);
			}
	        {
				if (this.modelo.getCodigo() != 0) {
					getLabelCodigo().setText(getLabelCodigo().getText() + "  " + this.modelo.getCodigo());
				}
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
			this.carregarComboMarca(getMarca().load());
		}

		private Marca getMarca() {
			if (marca == null) {
				marca = new Marca();
			}
			return marca;
		}

		private void carregarComboMarca(ListaObjeto listaObjeto) {
			String[] comboArray = new String[listaObjeto.getSize()];
			for (int i = 0; i < listaObjeto.getSize(); i++) {
				Marca marca = (Marca) listaObjeto.getObjeto(i);
				comboArray[i] = marca.getNome();
			}
			ComboBoxModel comboMarcaModel = 
				new DefaultComboBoxModel(comboArray);
			comboMarca.setModel(comboMarcaModel);
		}

		private void limparCampos() {
			textModelo.setText("");
		}
		
		private boolean existeModelo(){
			ListaObjeto listaObjeto = modelo.search("modelo","Igual",textModelo.getText());
			if (listaObjeto.getSize() > 0) {
				return false;
			}			
			return true;			
		}
		
		private Marca buscarMarca(){
			ListaObjeto listaObjeto = marca.search("marca","Igual",(String)comboMarca.getSelectedItem());
			marca = (Marca)listaObjeto.getObjeto(0);
			return marca;
			
						
		}

		private void showPainel(PainelPadrao painelPadrao, String titulo) {
			
			DialogoCRUD dialogoCRUD = new DialogoCRUD(null,titulo,true);
			dialogoCRUD.setPainel(painelPadrao);

		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == botaoMarca) {
				showPainel(new PainelMarca(),"Cadastros de Marcas");				
				
			}
			if (e.getSource() == getBotaoCancelar()) {
				dispose();
			} else if (e.getSource() == getBotaoConfirmar()) {
				
				if (textModelo.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoModelo.this, "O campo modelo é obrigatório!");
				} else {
					if (modelo.getCodigo() == 0) {
						if (existeModelo()) {
							modelo.setNome(textModelo.getText());
							modelo.setMarca(buscarMarca());
							modelo.insert();	
							carregarComboMarca(getMarca().load());
						} else {
							JOptionPane.showMessageDialog(DialogoModelo.this, "Essa modelo já se encontra na Base de Dados!");
						}
					} else {
						if (existeModelo()) {
							modelo.setNome(textModelo.getText().trim());
							modelo.edit();	
						} else{
							JOptionPane.showMessageDialog(DialogoModelo.this, "Essa modelo já se encontra na Base de Dados!");
						}
					}
					limparCampos();
					dispose();
				}
			}
		}
	}

	/*----------------------------------------------------------
	 * FIM DE CLASSE LIMITROFE
	 *----------------------------------------------------------*/

}
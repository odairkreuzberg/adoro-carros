package br.iav.ac.telas.modelo;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.iav.ac.telas.TelaPrincipal;
import br.iav.ac.telas.core.GridDialog;
import br.iav.ac.telas.marca.MarcaGrid;


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
 * Formulário de Cadastro de Modelo de um Veículo
 * 
 * @author Odair Kreuzberg
 */
public class ModeloForm extends JDialog {
	
	/*----------------------------------------------------------
	 * ATTRIBUTOS
	 *----------------------------------------------------------*/

	private static final long serialVersionUID = 1L;
	private JLabel labelModelo;
	private JLabel labelMarca;
	private JButton botaoMarca;
	private JComboBox comboMarca;
	private JButton botaoConfirmar;
	private JButton botaoCancelar;
	private JTextField textModelo;
	private FormHandle formHandle;

	/*----------------------------------------------------------
	 * FIM DE ATTRIBUTOS
	 *----------------------------------------------------------*/

	
	/*----------------------------------------------------------
	 * CONSTRUTOR
	 *----------------------------------------------------------*/

	public ModeloForm(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		initGUI();
		inicializarHandlers();
		this.setSize(329, 137);
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

		botaoCancelar.addActionListener(formHandle);

		botaoConfirmar.addActionListener(formHandle);

		botaoMarca.addActionListener(formHandle);
	}

	private void initGUI() {
		this.setLayout(null);
		this.setDefaultCloseOperation(ModeloForm.DISPOSE_ON_CLOSE);
		
		this.setResizable(false);
		try {
			{
				labelModelo = new JLabel();
				getContentPane().add(labelModelo);
				labelModelo.setText("Modelo:");
				labelModelo.setBounds(3, 47, 42, 14);
			}
			{
				textModelo = new JTextField();
				getContentPane().add(textModelo);
				textModelo.setBounds(57, 44, 251, 21);
			}
			{
				botaoCancelar = new JButton();
				getContentPane().add(botaoCancelar);
				botaoCancelar.setText("Cancelar");
				botaoCancelar.setBounds(230, 77, 79, 21);
			}
			{
				botaoConfirmar = new JButton();
				getContentPane().add(botaoConfirmar);
				botaoConfirmar.setText("Confirmar");
				botaoConfirmar.setBounds(122, 77, 97, 21);
			}
			{
				labelMarca = new JLabel();
				getContentPane().add(labelMarca);
				labelMarca.setText("Marca:");
				labelMarca.setBounds(12, 15, 33, 14);
			}
			{
				ComboBoxModel comboMarcaModel = 
					new DefaultComboBoxModel(
							new String[] { "Item One", "Item Two" });
				comboMarca = new JComboBox();
				getContentPane().add(comboMarca);
				comboMarca.setModel(comboMarcaModel);
				comboMarca.setBounds(54, 12, 217, 21);
			}
			{
				botaoMarca = new JButton();
				getContentPane().add(botaoMarca);
				botaoMarca.setText("...");
				botaoMarca.setBounds(283, 12, 26, 21);
			}
			pack();
		} catch(Exception e) {
			e.printStackTrace();
		}
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
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == botaoCancelar) {
				dispose();
				JOptionPane.showMessageDialog(ModeloForm.this, "Operção cancelada");
				
			} else if (e.getSource() == botaoConfirmar) {
				
			}  else if (e.getSource() == botaoMarca) {
				GridDialog  gridDialog = new GridDialog(TelaPrincipal.instancia,
						"Cadastro de Marcas", true);
				gridDialog.setCrudPanel(new MarcaGrid());
				gridDialog.setLocationRelativeTo(null);
				gridDialog.setVisible(true);
				
			} 
		}
	}

	/*----------------------------------------------------------
	 * FIM DE CLASSE LIMITROFE
	 *----------------------------------------------------------*/

}

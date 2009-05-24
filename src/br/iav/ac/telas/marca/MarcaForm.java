package br.iav.ac.telas.marca;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Formulário de Cadastro de Marca de um Veículo
 * 
 * @author Odair Kreuzberg
 */
public class MarcaForm extends JDialog {
	
	/*----------------------------------------------------------
	 * ATTRIBUTOS
	 *----------------------------------------------------------*/

	private static final long serialVersionUID = 1L;
	private JLabel labelMarca;
	private JButton botaoConfirmar;
	private JButton botaoCancelar;
	private JTextField textMarca;
	private FormHandle formHandle;

	/*----------------------------------------------------------
	 * FIM DE ATTRIBUTOS
	 *----------------------------------------------------------*/

	
	/*----------------------------------------------------------
	 * CONSTRUTOR
	 *----------------------------------------------------------*/

	public MarcaForm(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		initGUI();
		inicializarHandlers();
		this.setSize(324, 111);
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
	}

	private void initGUI() {
		this.setLayout(null);
		this.setDefaultCloseOperation(MarcaForm.DISPOSE_ON_CLOSE);
		
		this.setResizable(false);
		try {
			{
				labelMarca = new JLabel();
				getContentPane().add(labelMarca);
				labelMarca.setText("Marca:");
				labelMarca.setBounds(12, 15, 33, 14);
			}
			{
				textMarca = new JTextField();
				getContentPane().add(textMarca);
				textMarca.setBounds(57, 12, 246, 21);
			}
			{
				botaoCancelar = new JButton();
				getContentPane().add(botaoCancelar);
				botaoCancelar.setText("Cancelar");
				botaoCancelar.setBounds(219, 52, 79, 21);
			}
			{
				botaoConfirmar = new JButton();
				getContentPane().add(botaoConfirmar);
				botaoConfirmar.setText("Confirmar");
				botaoConfirmar.setBounds(111, 52, 97, 21);
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
				JOptionPane.showMessageDialog(MarcaForm.this, "Operção cancelada");
				
			} else if (e.getSource() == botaoConfirmar) {
				
			} 
		}
	}

	/*----------------------------------------------------------
	 * FIM DE CLASSE LIMITROFE
	 *----------------------------------------------------------*/

}

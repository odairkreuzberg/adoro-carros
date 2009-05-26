package br.iav.ac.telas.cor;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.iav.ac.negocio.Cor;

/**
 * Formulário de Cadastro de Cor de um Veículo
 * 
 * @author Odair Kreuzberg
 */
public class CorForm extends JDialog {
	
	/*----------------------------------------------------------
	 * ATTRIBUTOS
	 *----------------------------------------------------------*/

	private static final long serialVersionUID = 1L;
	private JLabel labelCor;
	private JButton botaoConfirmar;
	private JButton botaoCancelar;
	private JTextField textCor;
	private FormHandle formHandle;
	private Cor cor;

	/*----------------------------------------------------------
	 * FIM DE ATTRIBUTOS
	 *----------------------------------------------------------*/

	
	/*----------------------------------------------------------
	 * CONSTRUTOR
	 *----------------------------------------------------------*/

	public CorForm(Frame parent, String title, boolean modal) {
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
		this.setDefaultCloseOperation(CorForm.DISPOSE_ON_CLOSE);
		
		this.setResizable(false);
		try {
			{
				labelCor = new JLabel();
				getContentPane().add(labelCor);
				labelCor.setText("Cor:");
				labelCor.setBounds(12, 15, 33, 14);
			}
			{
				textCor = new JTextField();
				getContentPane().add(textCor);
				textCor.setBounds(57, 12, 246, 21);
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
		private void limparCampos(){
			textCor.setText("");
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == botaoCancelar) {
				dispose();
				JOptionPane.showMessageDialog(CorForm.this, "Operção cancelada");
				
			} else if (e.getSource() == botaoConfirmar) {
				cor = new Cor();
				if (textCor.getText().equals("")){
					JOptionPane.showMessageDialog(CorForm.this, "O Campo Cor é Obrigatório");
					
				}else{
					cor.setNome(textCor.getText());
					cor.insert();
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

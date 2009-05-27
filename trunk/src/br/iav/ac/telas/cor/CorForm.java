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
import br.iav.ac.negocio.ListaObjeto;

/**
 * Formul�rio de Cadastro de Cor de um Ve�culo
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
	private JLabel labelId;
	private JLabel labelCodigo;
	private FormHandle formHandle;
	private Cor cor;

	/*----------------------------------------------------------
	 * FIM DE ATTRIBUTOS
	 *----------------------------------------------------------*/

	/*----------------------------------------------------------
	 * CONSTRUTOR
	 *----------------------------------------------------------*/

	public CorForm(Frame parent, String title, boolean modal, Cor cor) {
		super(parent, title, modal);
		this.cor = cor;
		initGUI();
		inicializarHandlers();
		this.setSize(317, 128);
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
				labelCor.setBounds(28, 38, 21, 14);
			}
			{
				textCor = new JTextField(this.cor.getNome().trim());
				getContentPane().add(textCor);
				textCor.setBounds(51, 35, 246, 21);
			}
			{
				botaoCancelar = new JButton();
				getContentPane().add(botaoCancelar);
				botaoCancelar.setText("Cancelar");
				botaoCancelar.setBounds(218, 68, 79, 21);
			}
			{
				botaoConfirmar = new JButton();
				getContentPane().add(botaoConfirmar);
				botaoConfirmar.setText("Confirmar");
				botaoConfirmar.setBounds(116, 68, 97, 21);
			}
			{
				labelCodigo = new JLabel();
				getContentPane().add(labelCodigo);
				labelCodigo.setText("Codigo:");
				labelCodigo.setBounds(12, 12, 39, 14);
			}
			{
				labelId = new JLabel();
				getContentPane().add(labelId);
				if (this.cor.getCodigo() != 0)
					labelId.setText(this.cor.getCodigo() + "");
				labelId.setBounds(51, 14, 51, 10);
			}
			pack();
		} catch (Exception e) {
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

		private void limparCampos() {
			textCor.setText("");
		}
		
		private boolean existeCor(){
			ListaObjeto listaObjeto = cor.buscar("cor","Igual",textCor.getText());
			if (listaObjeto.getSize() > 0) {
				return false;
			}			
			return true;			
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == botaoCancelar) {
				dispose();
			} else if (e.getSource() == botaoConfirmar) {
				if (textCor.getText().equals("")) {
					JOptionPane.showMessageDialog(CorForm.this, "O Campo Cor � obrigat�rio!");
				} else {
					if (cor.getCodigo() == 0) {
						if (existeCor()) {
							cor.setNome(textCor.getText().trim());
							cor.insert();							
						} else {
							JOptionPane.showMessageDialog(CorForm.this, "Essa Cor j� se encontra na Base de Dados!");
						}
					} else {
						if (existeCor()) {
							cor.setNome(textCor.getText().trim());
							cor.edit();	
						} else{
							JOptionPane.showMessageDialog(CorForm.this, "Essa Cor j� se encontra na Base de Dados!");
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
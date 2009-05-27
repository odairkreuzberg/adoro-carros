package br.iav.ac.telas.cor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import br.iav.ac.negocio.Cor;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.telas.core.DialogoPadrao;

/**
 * Formulário de Cadastro de Cor de um Veículo
 * 
 * @author Odair Kreuzberg
 */
public class DialogoCor extends DialogoPadrao {

	/*----------------------------------------------------------
	 * ATTRIBUTOS
	 *----------------------------------------------------------*/

	private JLabel labelCor;
	private JTextField textCor;
	private FormHandle formHandle;
	private Cor cor;

	/*----------------------------------------------------------
	 * FIM DE ATTRIBUTOS
	 *----------------------------------------------------------*/

	/*----------------------------------------------------------
	 * CONSTRUTOR
	 *----------------------------------------------------------*/

	public DialogoCor(JFrame frame, String titulo, boolean modal, Cor cor) {
		super(frame, titulo, modal);
		this.cor = cor;
		try {
            {
            	labelCor = new JLabel();
            	getPanelPrincipal().add(labelCor);
            	labelCor.setText("Cor:");
            	labelCor.setBounds(28, 38, 21, 14);
	        }
	        {
	        	textCor = new JTextField(this.cor.getNome().trim());
	        	getPanelPrincipal().add(textCor);
	            textCor.setBounds(51, 35, 246, 21);
	        }
	        {
				if (this.cor.getCodigo() != 0) {
					getLabelCodigo().setText(getLabelCodigo().getText() + "  " + this.cor.getCodigo());
				}
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		getBotaoCancelar().addActionListener(formHandle);
		getBotaoConfirmar().addActionListener(formHandle);
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
			ListaObjeto listaObjeto = cor.search("cor","Igual",textCor.getText().trim());
			if (listaObjeto.getSize() > 0) {
				return false;
			}			
			return true;			
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == getBotaoCancelar()) {
				dispose();
			} else if (e.getSource() == getBotaoConfirmar()) {
				if (textCor.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoCor.this, "O campo cor é obrigatório!");
				} else {
					if (cor.getCodigo() == 0) {
						if (existeCor()) {
							cor.setNome(textCor.getText().trim());
							cor.insert();							
						} else {
							JOptionPane.showMessageDialog(DialogoCor.this, "Essa cor já se encontra na Base de Dados!");
						}
					} else {
						if (existeCor()) {
							cor.setNome(textCor.getText().trim());
							cor.edit();	
						} else{
							JOptionPane.showMessageDialog(DialogoCor.this, "Essa cor já se encontra na Base de Dados!");
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
package br.iav.ac.telas.marca;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import br.iav.ac.negocio.Marca;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.telas.padrao.DialogoPadrao;

/**
 * Formulário de Cadastro de Marca de um Veículo
 * 
 * @author Odair Kreuzberg
 */
public class DialogoMarca extends DialogoPadrao {

	/*----------------------------------------------------------
	 * ATTRIBUTOS
	 *----------------------------------------------------------*/

	private static final long serialVersionUID = 1L;
	private JLabel labelMarca;
	private JTextField textMarca;
	private FormHandle formHandle;
	private Marca marca;

	/*----------------------------------------------------------
	 * FIM DE ATTRIBUTOS
	 *----------------------------------------------------------*/

	/*----------------------------------------------------------
	 * CONSTRUTOR
	 *----------------------------------------------------------*/

	public DialogoMarca(JFrame frame, String titulo, boolean modal, Marca marca) {
		super(frame, titulo, modal);
		this.marca = marca;
		try {
            {
            	labelMarca = new JLabel();
            	getPanelPrincipal().add(labelMarca);
            	labelMarca.setText("Marca:");
            	labelMarca.setBounds(16, 38, 60, 20);
	        }
	        {
	        	textMarca = new JTextField(this.marca.getNome().trim());
	        	getPanelPrincipal().add(textMarca);
	            textMarca.setBounds(51, 35, 246, 21);
	        }
	        {
				if (this.marca.getCodigo() != 0) {
					getLabelCodigo().setText(getLabelCodigo().getText() + "  " + this.marca.getCodigo());
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
			textMarca.setText("");
		}
		
		private boolean existeMarca(){
			ListaObjeto listaObjeto = marca.search("marca","Igual",textMarca.getText().trim());
			if (listaObjeto.getSize() > 0) {
				return false;
			}			
			return true;			
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == getBotaoCancelar()) {
				dispose();
			} else if (e.getSource() == getBotaoConfirmar()) {
				if (textMarca.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoMarca.this, "O campo marca é obrigatório!");
				} else {
					if (marca.getCodigo() == 0) {
						if (existeMarca()) {
							marca.setNome(textMarca.getText().trim());							
							marca.insert();							
						} else {
							JOptionPane.showMessageDialog(DialogoMarca.this, "Essa marca já se encontra na Base de Dados!");
						}
					} else {
						if (existeMarca()) {
							marca.setNome(textMarca.getText().trim());
							marca.edit();	
						} else{
							JOptionPane.showMessageDialog(DialogoMarca.this, "Essa marca já se encontra na Base de Dados!");
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
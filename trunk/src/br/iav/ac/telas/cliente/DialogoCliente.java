package br.iav.ac.telas.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import br.iav.ac.negocio.Cliente;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.telas.padrao.DialogoPadrao;

public class DialogoCliente extends DialogoPadrao {

	/*----------------------------------------------------------
	 * ATTRIBUTOS
	 *----------------------------------------------------------*/

	private static final long serialVersionUID = 1L;
	private JLabel labelNome;
	private JTextField textNome;
	private FormHandle formHandle;
	private Cliente cliente;

	/*----------------------------------------------------------
	 * FIM DE ATTRIBUTOS
	 *----------------------------------------------------------*/

	/*----------------------------------------------------------
	 * CONSTRUTOR
	 *----------------------------------------------------------*/

	public DialogoCliente(JFrame frame, String titulo, boolean modal, Cliente cliente) {
		super(frame, titulo, modal);
		this.cliente = cliente;
		try {
            {
            	labelNome = new JLabel();
            	getPanelPrincipal().add(labelNome);
            	labelNome.setText("Nome:");
            	labelNome.setBounds(28, 38, 21, 14);
	        }
	        {
	        	textNome = new JTextField(this.cliente.getNome().trim());
	        	getPanelPrincipal().add(textNome);
	        	textNome.setBounds(51, 35, 246, 21);
	        }
	        {
				if (this.cliente.getCodigo() != 0) {
					getLabelCodigo().setText(getLabelCodigo().getText() + "  " + this.cliente.getCodigo());
				}
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		inicializarHandlers();
		this.setSize(400, 200);
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
			textNome.setText("");
		}
		
		private boolean existeCor(){
			ListaObjeto listaObjeto = cliente.search("cor","Igual",textNome.getText().trim());
			if (listaObjeto.getSize() > 0) {
				return false;
			}			
			return true;			
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == getBotaoCancelar()) {
				dispose();
			} else if (e.getSource() == getBotaoConfirmar()) {
				if (textNome.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoCliente.this, "O campo nome é obrigatório!");
				} else {
					if (cliente.getCodigo() == 0) {
						if (existeCor()) {
							cliente.setNome(textNome.getText().trim());
							cliente.insert();							
						} else {
							JOptionPane.showMessageDialog(DialogoCliente.this, "Esse cliente já se encontra na Base de Dados!");
						}
					} else {
						if (existeCor()) {
							cliente.setNome(textNome.getText().trim());
							cliente.edit();	
						} else{
							JOptionPane.showMessageDialog(DialogoCliente.this, "Esse cliente já se encontra na Base de Dados!");
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
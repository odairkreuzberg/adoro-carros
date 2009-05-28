package br.iav.ac.telas.marca;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import br.iav.ac.negocio.Marca;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.telas.TelaPrincipal;
import br.iav.ac.telas.padrao.DialogoPadrao;

/**
 * Formul�rio de Cadastro de Marca de um Ve�culo
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
		super(TelaPrincipal.instancia, titulo, modal);
		this.marca = marca;
		try {
            {
            	labelMarca = new JLabel();
            	getPanelPrincipal().add(labelMarca);
            	labelMarca.setText("Marca:");
            	labelMarca.setBounds(28, 38, 21, 14);
	        }
	        {
	        	textMarca = new JTextField();
	        	getPanelPrincipal().add(textMarca);
	            textMarca.setBounds(51, 35, 246, 21);
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
	 * METODOS DA CLASSE
	 *----------------------------------------------------------*/

	private void inicializarHandlers() {
		this.formHandle = new FormHandle();
		getBotaoCancelar().addActionListener(formHandle);
		getBotaoConfirmar().addActionListener(formHandle);
	}
	
	/*----------------------------------------------------------
	 * FIM DE METODOS DA CLASSE
	 *----------------------------------------------------------*/

	/*----------------------------------------------------------
	 * CLASSE LIMITROFE
	 *----------------------------------------------------------*/

	class FormHandle implements ActionListener {

		public FormHandle() {
			super();
			textMarca.setText(marca.getNome().trim());
			if (marca.getCodigo() != 0) {
				getLabelCodigo().setText(
						getLabelCodigo().getText() + "  " + marca.getCodigo());
			}

		}
		
		/**
		 * Retorna true se encontrar uma marca e false se nao encontrar.
		 * 
		 * @return boolean
		 */
		private boolean existeMarca() {
			ListaObjeto listaObjeto = marca.search("marca", "Igual", textMarca
					.getText().trim());
			if (listaObjeto.getSize() > 0) {
				return false;
			}
			return true;
		}
		
		/**
		 * Faz a Inser��o de uma Marca.
		 */		
		private void inserir(){
			if (existeMarca()) {
				marca.setNome(textMarca.getText().trim());
				marca.insert();							
			} else {
				JOptionPane.showMessageDialog(DialogoMarca.this, 
						"Essa marca j� se encontra na Base de Dados!");
			}			
		}
		
		/**
		 * Faz a Edi��o de uma Marca.
		 */
		private void editar(){
			if (existeMarca()) {
				marca.setNome(textMarca.getText().trim());
				marca.edit();	
			} else{
				JOptionPane.showMessageDialog(DialogoMarca.this, 
						"Essa marca j� se encontra na Base de Dados!");
			}
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == getBotaoCancelar()) {
				dispose();
			} else if (e.getSource() == getBotaoConfirmar()) {
				if (textMarca.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoMarca.this,
							"O campo marca � obrigat�rio!");
				} else {
					if (marca.getCodigo() == 0) {
						inserir();
					} else {
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
package br.iav.ac.telas.marca;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.iav.ac.negocio.Marca;
import br.iav.ac.telas.TelaPrincipal;
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

	private JTextField textCodigo;
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
		//25 de espaçamento entre cada atributo (JTextField e JLabel)
		int espacoEntreLinhas = 10;
		//Espaçamento dos JTextField
		int espacoDoTextField = 50;
		try {
			{
				textCodigo = new JTextField();
				getPanelPrincipal().add(textCodigo);
				textCodigo.setBounds(espacoDoTextField, espacoEntreLinhas, 35, 20);
				textCodigo.setEditable(false);
			}
            {
            	espacoEntreLinhas = espacoEntreLinhas + 25;
            	labelMarca = new JLabel();
            	getPanelPrincipal().add(labelMarca);
            	labelMarca.setText("Marca:*");
            	labelMarca.setBounds(10, espacoEntreLinhas, 80, 20);
	        }
	        {
	        	textMarca = new JTextField();
	        	getPanelPrincipal().add(textMarca);
	            textMarca.setBounds(espacoDoTextField, espacoEntreLinhas, 246, 20);
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
				textCodigo.setText(String.valueOf(marca.getCodigo()));
			}

		}
		
		private boolean validarCampos() {
		if (textMarca.getText().trim().length()>50) {
			JOptionPane.showMessageDialog(DialogoMarca.this, "O campo Nome estourou o limite de Caracter!");
			textMarca.requestFocus();
			return false;
		}else if (textMarca.getText().equals("")) {
			JOptionPane.showMessageDialog(DialogoMarca.this, "O campo Nome é obrigatorio!");
			textMarca.requestFocus();
			return false;
		}
		return true;
			
		}

		//Faz a Inserção de uma Cor.
		private void inserir(){	
			if(validarCampos()){
				marca.setNome(textMarca.getText().trim());
				if(marca.search("Nome", "Igual", marca.getNome()).getSize() == 0){					
					marca.insert();
					dispose();
				} else {
					JOptionPane.showMessageDialog(DialogoMarca.this, "Esta Marca já se Encontra na Base de Dados!");					
				}
			}
		}

		//Faz a Edição de uma Cor.
		private void editar() {
			if (validarCampos()) {
				marca.setNome(textMarca.getText().trim());
				if (!marca.existeMarca(marca)) {
					marca.edit();
					dispose();
				} else {
					JOptionPane.showMessageDialog(DialogoMarca.this, "Esta Marca já se Encontra na Base de Dados!");					
				}
			}
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == getBotaoCancelar()) {
				dispose();
			} else if (e.getSource() == getBotaoConfirmar()) {
				if (textMarca.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoMarca.this,
							"O campo marca é obrigatório!");
				} else {
					if (marca.getCodigo() == 0) {
						inserir();
					} else {
						editar();
					}
				}
			}
		}
	}

	/*----------------------------------------------------------
	 * FIM DE CLASSE LIMITROFE
	 *----------------------------------------------------------*/

}
package br.iav.ac.telas.cor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import br.iav.ac.negocio.Cor;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.telas.TelaPrincipal;
import br.iav.ac.telas.padrao.DialogoPadrao;

/**
 * Formulário de Cadastro de Cor de um Veículo
 * 
 * @author Odair Kreuzberg
 */
public class DialogoCor extends DialogoPadrao {

	/*----------------------------------------------------------
	 * ATTRIBUTOS
	 *----------------------------------------------------------*/

	private static final long serialVersionUID = 1L;
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
		super(TelaPrincipal.instancia, titulo, modal);
		this.cor = cor;
		try {
			{
				labelCor = new JLabel();
				getPanelPrincipal().add(labelCor);
				labelCor.setText("Cor:");
				labelCor.setBounds(28, 38, 21, 14);
			}
			{
				textCor = new JTextField();
				getPanelPrincipal().add(textCor);
				textCor.setBounds(51, 35, 246, 21);
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
			textCor.setText(cor.getNome().trim());
			if (cor.getCodigo() != 0) {
				getLabelCodigo().setText(
						getLabelCodigo().getText() + "  " + cor.getCodigo());
			}

		}

		/**
		 * Retorna true se encontrar uma cor e false se nao encontrar.
		 * 
		 * @return boolean
		 */
		private boolean existeCor() {
			ListaObjeto listaObjeto = cor.search("cor", "Igual", textCor
					.getText().trim());
			if (listaObjeto.getSize() > 0) {
				return false;
			}
			return true;
		}

		/**
		 * Faz a Inserção de uma Cor.
		 */
		private void inserir() {
			if (existeCor()) {
				cor.setNome(textCor.getText().trim());
				cor.insert();
			} else {
				JOptionPane.showMessageDialog(DialogoCor.this,
						"Essa cor já se encontra na Base de Dados!");
			}
		}

		/**
		 * Faz a Edição de uma Cor.
		 */
		private void editar() {
			if (existeCor()) {
				cor.setNome(textCor.getText().trim());
				cor.edit();
			} else {
				JOptionPane.showMessageDialog(DialogoCor.this,
						"Essa cor já se encontra na Base de Dados!");
			}
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == getBotaoCancelar()) {
				dispose();
			} else if (e.getSource() == getBotaoConfirmar()) {
				if (textCor.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoCor.this,
							"O campo cor é obrigatório!");
				} else {
					if (cor.getCodigo() == 0) {
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
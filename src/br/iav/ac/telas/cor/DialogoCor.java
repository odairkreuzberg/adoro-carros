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

	private JTextField textCodigo;
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
				labelCor = new JLabel();
				getPanelPrincipal().add(labelCor);
				labelCor.setText("Cor:");
				labelCor.setBounds(10, espacoEntreLinhas, 80, 20);
			}
			{
				textCor = new JTextField();
				getPanelPrincipal().add(textCor);
				textCor.setBounds(espacoDoTextField, espacoEntreLinhas, 246, 20);
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
				textCodigo.setText(String.valueOf(cor.getCodigo()));
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
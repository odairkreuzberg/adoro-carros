package br.iav.ac.telas.status;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.negocio.Status;
import br.iav.ac.telas.TelaPrincipal;
import br.iav.ac.telas.padrao.DialogoPadrao;

public class DialogoStatus extends DialogoPadrao {

	private JTextField textCodigo;
	private JLabel labelNome;
	private JTextField textNome;
	private FormHandle formHandle;
	private Status status;

	public DialogoStatus(JFrame frame, String titulo, boolean modal, Status status) {
		super(TelaPrincipal.instancia, titulo, modal);
		this.status = status;
		//25 de espaçamento entre cada atributo (JTextField e JLabel)
		int espacoEntreLinhas = 10;
		//Espaçamento dos JTextField
		int espacoDoTextField = 55;
		try {
			{
				textCodigo = new JTextField();
				getPanelPrincipal().add(textCodigo);
				textCodigo.setBounds(espacoDoTextField, espacoEntreLinhas, 35, 20);
				textCodigo.setEditable(false);
			}
			{
				espacoEntreLinhas = espacoEntreLinhas + 25;
				labelNome = new JLabel();
				getPanelPrincipal().add(labelNome);
				labelNome.setText("Nome:");
				labelNome.setBounds(10, espacoEntreLinhas, 60, 20);
			}
			{
				textNome = new JTextField();
				getPanelPrincipal().add(textNome);
				textNome.setBounds(espacoDoTextField, espacoEntreLinhas, 246, 20);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		inicializarHandlers();
		this.setSize(317, 128);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void inicializarHandlers() {
		this.formHandle = new FormHandle();
		getBotaoCancelar().addActionListener(formHandle);
		getBotaoConfirmar().addActionListener(formHandle);
	}

	class FormHandle implements ActionListener {

		public FormHandle() {
			super();
			textNome.setText(status.getNome().trim());
			if (status.getCodigo() != 0) {
				textCodigo.setText(String.valueOf(status.getCodigo()));
			}

		}

		/**
		 * Retorna true se encontrar um status e false se nao encontrar.
		 * 
		 * @return boolean
		 */
		private boolean existeCor() {
			ListaObjeto listaObjeto = status.search("Nome", "Igual", textNome.getText().trim());
			if (listaObjeto.getSize() > 0) {
				return false;
			}
			return true;
		}

		/**
		 * Faz a inserção de um status.
		 */
		private void inserir() {
			if (existeCor()) {
				status.setNome(textNome.getText().trim());
				status.insert();
			} else {
				JOptionPane.showMessageDialog(DialogoStatus.this, "Esse status já se encontra na Base de Dados!");
			}
		}

		/**
		 * Faz a edição de um status.
		 */
		private void editar() {
			if (existeCor()) {
				status.setNome(textNome.getText().trim());
				status.edit();
			} else {
				JOptionPane.showMessageDialog(DialogoStatus.this, "Esse status já se encontra na Base de Dados!");
			}
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == getBotaoCancelar()) {
				dispose();
			} else if (e.getSource() == getBotaoConfirmar()) {
				if (textNome.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoStatus.this, "O campo nome é obrigatório!");
				} else {
					if (status.getCodigo() == 0) {
						inserir();
					} else {
						editar();
					}
					dispose();
				}
			}
		}
		
	}
	
}
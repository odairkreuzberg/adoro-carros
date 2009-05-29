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
import br.iav.ac.telas.cor.DialogoCor;
import br.iav.ac.telas.padrao.DialogoPadrao;

public class DialogoStatus extends DialogoPadrao {

	private static final long serialVersionUID = 1L;
	private JLabel labelNome;
	private JTextField textNome;
	private FormHandle formHandle;
	private Status status;

	public DialogoStatus(JFrame frame, String titulo, boolean modal, Status status) {
		super(TelaPrincipal.instancia, titulo, modal);
		this.status = status;
		try {
			{
				labelNome = new JLabel();
				getPanelPrincipal().add(labelNome);
				labelNome.setText("Nome:");
				labelNome.setBounds(12, 38, 60, 14);
			}
			{
				textNome = new JTextField();
				getPanelPrincipal().add(textNome);
				textNome.setBounds(55, 35, 246, 21);
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
				getTextCodigo().setText(String.valueOf(status.getCodigo()));
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
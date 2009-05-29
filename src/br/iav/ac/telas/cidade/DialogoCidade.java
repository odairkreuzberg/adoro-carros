package br.iav.ac.telas.cidade;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import br.iav.ac.negocio.Cidade;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.telas.TelaPrincipal;
import br.iav.ac.telas.padrao.DialogoPadrao;

public class DialogoCidade extends DialogoPadrao {

	private static final long serialVersionUID = 1L;
	private JLabel labelNome;
	private JTextField textNome;
	private JLabel labelDdd;
	private JTextField textDdd;
	private FormHandle formHandle;
	private Cidade cidade;

	public DialogoCidade(JFrame frame, String titulo, boolean modal, Cidade cidade) {
		super(TelaPrincipal.instancia, titulo, modal);
		this.cidade = cidade;
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
			{
				labelDdd = new JLabel();
				getPanelPrincipal().add(labelDdd);
				labelDdd.setText("DDD:");
				labelDdd.setBounds(12, 63, 60, 14);
			}
			{
				textDdd = new JTextField();
				getPanelPrincipal().add(textDdd);
				textDdd.setBounds(55, 60, 246, 21);
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
			textNome.setText(cidade.getNome().trim());
			textDdd.setText(String.valueOf(cidade.getDdd()));
			if (cidade.getCodigo() != 0) {
				getTextCodigo().setText(String.valueOf(cidade.getCodigo()));
			}

		}

		/**
		 * Retorna true se encontrar uma cidade e false se nao encontrar.
		 * 
		 * @return boolean
		 */
		private boolean existeCor() {
			ListaObjeto listaObjeto = cidade.search("Nome", "Igual", textNome.getText().trim());
			if (listaObjeto.getSize() > 0) {
				return false;
			}
			return true;
		}

		/**
		 * Faz a inserção de uma cidade.
		 */
		private void inserir() {
			if (existeCor()) {
				cidade.setNome(textNome.getText().trim());
				cidade.setDdd(Integer.parseInt(textDdd.getText().trim()));
				cidade.insert();
			} else {
				JOptionPane.showMessageDialog(DialogoCidade.this, "Essa cidade já se encontra na Base de Dados!");
			}
		}

		/**
		 * Faz a edição de uma cidade.
		 */
		private void editar() {
			if (existeCor()) {
				cidade.setNome(textNome.getText().trim());
				cidade.setDdd(Integer.parseInt(textDdd.getText().trim()));
				cidade.edit();
			} else {
				JOptionPane.showMessageDialog(DialogoCidade.this, "Essa cidade já se encontra na Base de Dados!");
			}
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == getBotaoCancelar()) {
				dispose();
			} else if (e.getSource() == getBotaoConfirmar()) {
				if (textNome.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoCidade.this, "O campo nome é obrigatório!");
				} else if (textDdd.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoCidade.this, "O campo DDD é obrigatório!");
				} else {
					if (cidade.getCodigo() == 0) {
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
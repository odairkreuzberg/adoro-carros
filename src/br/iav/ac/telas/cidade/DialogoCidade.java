package br.iav.ac.telas.cidade;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.iav.ac.negocio.Cidade;
import br.iav.ac.telas.padrao.DialogoPadrao;

/**
 * Formulário de Cadastro de Cidade
 * 
 * @author Raphael Furlan
 */
public class DialogoCidade extends DialogoPadrao {

	private JTextField textCodigo;
	private JLabel labelNome;
	private JTextField textNome;
	private JLabel labelDdd;
	private JTextField textDdd;
	private FormHandle formHandle;
	private Cidade cidade;

	public DialogoCidade(JFrame frame, String titulo, boolean modal, Cidade cidade) {
		super(frame, titulo, modal);
		this.cidade = cidade;
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
				labelNome.setText("Nome:*");
				labelNome.setBounds(10, espacoEntreLinhas, 80, 20);
			}
			{
				textNome = new JTextField();
				getPanelPrincipal().add(textNome);
				textNome.setBounds(espacoDoTextField, espacoEntreLinhas, 246, 20);
			}
			{
				espacoEntreLinhas = espacoEntreLinhas +25;
				labelDdd = new JLabel();
				getPanelPrincipal().add(labelDdd);
				labelDdd.setText("DDD:");
				labelDdd.setBounds(10, espacoEntreLinhas, 80, 20);
			}
			{
				textDdd = new JTextField();
				getPanelPrincipal().add(textDdd);
				textDdd.setBounds(espacoDoTextField, espacoEntreLinhas, 246, 20);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		inicializarHandlers();
		this.setSize(317, 150);
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
			if (cidade.getCodigo() != 0) {
				textCodigo.setText(String.valueOf(cidade.getCodigo()));
				textNome.setText(cidade.getNome().trim());
				if(cidade.getDdd()!= 0)
				textDdd.setText(String.valueOf(cidade.getDdd()));
			}
		}

		

		/**
		 * Faz a inserção de uma cidade.
		 */
		private void inserir() {
			cidade.setNome(textNome.getText().trim());
			if (textDdd.getText().trim().equals("")) {
				cidade.setDdd(0);
			} else {
				cidade.setDdd(Integer.parseInt(textDdd.getText().trim()));
			}
			cidade.insert();

		}

		/**
		 * Faz a edição de uma cidade.
		 */
		private void editar() {
			cidade.setNome(textNome.getText().trim());
			if (textDdd.getText().trim().equals("")) {
				cidade.setDdd(0);
			} else {
				cidade.setDdd(Integer.parseInt(textDdd.getText().trim()));
			}
			cidade.edit();
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == getBotaoCancelar()) {
				dispose();
			} else if (e.getSource() == getBotaoConfirmar()) {
				if (textNome.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoCidade.this, "O campo nome é obrigatório!");
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
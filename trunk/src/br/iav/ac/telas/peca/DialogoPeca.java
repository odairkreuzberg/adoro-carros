package br.iav.ac.telas.peca;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.negocio.Peca;
import br.iav.ac.telas.cor.DialogoCor;
import br.iav.ac.telas.padrao.DialogoPadrao;

/**
 * Formulário de Cadastro de Peca de um Veículo
 * 
 * @author Odair Kreuzberg
 */
public class DialogoPeca extends DialogoPadrao {

	private FormHandle formHandle;
	private Peca peca;
	private JTextField textCodigo;
	private JTextField textPeca;
	private JLabel labelPeca;

	public DialogoPeca(JFrame frame, String titulo, boolean modal, Peca peca) {
		super(frame, titulo, modal);
		this.peca = peca;
		//25 de espaçamento entre cada atributo (JTextField e JLabel)
		int espacoEntreLinhas = 10;
		//Espaçamento dos JTextField
		int espacoDoTextField = 60;
		try {
			{
				textCodigo = new JTextField();
				getPanelPrincipal().add(textCodigo);
				textCodigo.setBounds(espacoDoTextField, espacoEntreLinhas, 35, 20);
				textCodigo.setEditable(false);
			}
			{
				espacoEntreLinhas = espacoEntreLinhas + 25;
				labelPeca = new JLabel();
				getPanelPrincipal().add(labelPeca);
				labelPeca.setText("Peça:*");
				labelPeca.setBounds(10, espacoEntreLinhas, 80, 20);
			}
			{
				textPeca = new JTextField();
				getPanelPrincipal().add(textPeca);
				textPeca.setText(peca.getNome());
				textPeca.setBounds(espacoDoTextField, espacoEntreLinhas, 267, 20);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		inicializarHandlers();
		this.setSize(350, 125);
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
			if (peca.getCodigo() != 0) {
				textCodigo.setText(String.valueOf(peca.getCodigo()));
			}
		}
		
		private boolean validarCampos() {
		if (textPeca.getText().trim().length()>50) {
			JOptionPane.showMessageDialog(DialogoPeca.this, "O campo Nome estourou o limite de Caracter!");
			textPeca.requestFocus();
			return false;
		}else if (textPeca.getText().equals("")) {
			JOptionPane.showMessageDialog(DialogoPeca.this, "O campo Nome é obrigatorio!");
			textPeca.requestFocus();
			return false;
		}
		return true;
			
		}

		//Faz a Inserção de uma Peça.
		private void inserir(){	
			if(validarCampos()){
				peca.setNome(textPeca.getText().trim());
				if(peca.search("Nome", "Igual", peca.getNome()).getSize() == 0){					
					peca.insert();
					dispose();
				} else {
					JOptionPane.showMessageDialog(DialogoPeca.this, "Esta Peça já se Encontra na Base de Dados!");					
				}
			}
		}

		//Faz a Edição de uma Peça.
		private void editar() {
			if (validarCampos()) {
				peca.setNome(textPeca.getText().trim());
				if (!peca.existePeca(peca)) {
					peca.edit();
					dispose();
				} else {
					JOptionPane.showMessageDialog(DialogoPeca.this, "Esta Peça já se Encontra na Base de Dados!");					
				}
			}
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == getBotaoCancelar()) {
				dispose();
			} 
			else if (e.getSource() == getBotaoConfirmar()) {
				if (textPeca.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoPeca.this,"O campo Peca é obrigatório!");
				} else {
					if (peca.getCodigo() == 0) {
						inserir();
					} 
					else {
						editar();
					}
				}
			}
		}
	}

}
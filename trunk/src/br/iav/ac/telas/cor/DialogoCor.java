package br.iav.ac.telas.cor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import br.iav.ac.negocio.Cor;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.telas.cargo.DialogoCargo;
import br.iav.ac.telas.padrao.DialogoPadrao;

/**
 * Formulário de Cadastro de Cor de um Veículo
 * 
 * @author Odair Kreuzberg
 */
public class DialogoCor extends DialogoPadrao {

	private JTextField textCodigo;
	private JLabel labelNome;
	private JTextField textNome;
	private FormHandle formHandle;
	private Cor cor;

	public DialogoCor(JFrame frame, String titulo, boolean modal, Cor cor) {
		super(frame, titulo, modal);
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
			textNome.setText(cor.getNome().trim());
			if (cor.getCodigo() != 0) {
				textCodigo.setText(String.valueOf(cor.getCodigo()));
			}
		}
		
		private boolean validarCampos() {
		if (textNome.getText().trim().length()>50) {
			JOptionPane.showMessageDialog(DialogoCor.this, "O campo Nome estourou o limite de Caracter!");
			textNome.requestFocus();
			return false;
		}else if (textNome.getText().equals("")) {
			JOptionPane.showMessageDialog(DialogoCor.this, "O campo Nome é obrigatorio!");
			textNome.requestFocus();
			return false;
		}
		return true;
			
		}

		//Faz a Inserção de uma Cor.
		private void inserir(){	
			if(validarCampos()){
				cor.setNome(textNome.getText().trim());
				if(cor.search("Nome", "Igual", cor.getNome()).getSize() == 0){					
					cor.insert();
					dispose();
				} else {
					JOptionPane.showMessageDialog(DialogoCor.this, "Esta Cor já se Encontra na Base de Dados!");					
				}
			}
		}

		//Faz a Edição de uma Cor.
		private void editar() {
			if (validarCampos()) {
				cor.setNome(textNome.getText().trim());
				if (!cor.existeCor(cor)) {
					cor.edit();
					dispose();
				} else {
					JOptionPane.showMessageDialog(DialogoCor.this, "Esta Cor já se Encontra na Base de Dados!");					
				}
			}
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == getBotaoCancelar()) {
				dispose();
			} else if (e.getSource() == getBotaoConfirmar()) {
				if (textNome.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoCor.this, "O campo nome é obrigatório!");
				} else {
					if (cor.getCodigo() == 0) {
						inserir();
					} else {
						editar();
					}
				}
			}
		}
		
	}

}
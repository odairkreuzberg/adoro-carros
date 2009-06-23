package br.iav.ac.telas.cargo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.iav.ac.negocio.Cargo;
import br.iav.ac.telas.padrao.DialogoPadrao;

/**
 * Formulário de Cadastro de Cargo de um Cliente
 * 
 * @author Fernando
 */
public class DialogoCargo extends DialogoPadrao {

	private JTextField textCodigo;
	private JLabel labelNome;
	private JTextField textNome;
	private JLabel labelDescricao;
	private JTextArea textDescricao;	
	private FormHandle formHandle;
	private Cargo cargo;

	public DialogoCargo(JFrame frame, String titulo, boolean modal, Cargo cargo) {
		super(frame, titulo, modal);
		this.cargo = cargo;
		//25 de espaçamento entre cada atributo (JTextField e JLabel)
		int espacoEntreLinhas = 10;
		//Espaçamento dos JTextField
		int espacoDoTextField = 75;
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
            	labelNome.setText("Nome: *");
            	labelNome.setBounds(10, espacoEntreLinhas, 80, 20);
	        }
	        {
	        	textNome = new JTextField();
	        	getPanelPrincipal().add(textNome);
	            textNome.setBounds(espacoDoTextField, espacoEntreLinhas, 246, 20);
	        }
	        {
	        	espacoEntreLinhas = espacoEntreLinhas + 25;
	        	labelDescricao = new JLabel();
	        	getPanelPrincipal().add(labelDescricao);
	        	labelDescricao.setText("Descrição:");
	        	labelDescricao.setBounds(10, espacoEntreLinhas, 80, 20);
        	}	        
	        {
	        	textDescricao = new JTextArea();
	        	JScrollPane barra = new JScrollPane(textDescricao);
	        	barra.setBounds(espacoDoTextField, espacoEntreLinhas, 290, 200);
	        	getPanelPrincipal().add(barra);
	        }	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		inicializarHandlers();
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}


	private void inicializarHandlers() {
		this.formHandle = new FormHandle();
		getBotaoCancelar().addActionListener(formHandle);
		getBotaoConfirmar().addActionListener(formHandle);
	}
	
	
	/*----------------------------------------------------------
	 * CLASSE LIMITROFE
	 *----------------------------------------------------------*/

	class FormHandle implements ActionListener {

		public FormHandle() {
			super();
			textNome.setText(cargo.getNome().trim());
			textNome.requestFocus();
			textDescricao.setText(cargo.getDescricao().trim());
			if (cargo.getCodigo() != 0) {
				textCodigo.setText(String.valueOf(cargo.getCodigo()));
			}
		}
		
		private boolean validarCampos() {
		if (textNome.getText().trim().length()>50) {
			JOptionPane.showMessageDialog(DialogoCargo.this, "O campo Nome estourou o limite de Caracter!");
			textNome.requestFocus();
			return false;
		}else if (textNome.getText().equals("")) {
			JOptionPane.showMessageDialog(DialogoCargo.this, "O campo Nome é obrigatorio!");
			textNome.requestFocus();
			return false;
		}else if (textDescricao.getText().trim().length()>100) {
			JOptionPane.showMessageDialog(DialogoCargo.this, "O campo Descrição estourou o limite de Caracter!");
			textDescricao.requestFocus();
			return false;
		}
		return true;
			
		}
		
		//Faz a Inserção de um cargo.
		private void inserir(){	
			if(validarCampos()){
				cargo.setNome(textNome.getText().trim());
				cargo.setDescricao(textDescricao.getText().trim());
				if(cargo.search("Nome", "Igual", cargo.getNome()).getSize() == 0){					
					cargo.insert();
					dispose();
				} else {
					JOptionPane.showMessageDialog(DialogoCargo.this, "Este Cargo já se Encontra na Base de Dados!");					
				}
			}
		}
		

		//Faz a Edição de um cargo.
		private void editar() {
			if (validarCampos()) {
				cargo.setNome(textNome.getText().trim());
				cargo.setDescricao(textDescricao.getText().trim());
				if (!cargo.existeCargo(cargo)) {
					cargo.edit();
					dispose();
				} else {
					JOptionPane.showMessageDialog(DialogoCargo.this, "Este Cargo já se Encontra na Base de Dados!");					
				}
			}
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == getBotaoCancelar()) {
				dispose();
			} else if (e.getSource() == getBotaoConfirmar()) {
				if (textNome.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoCargo.this,"O campo nome é obrigatório!");
				} else {
					if (cargo.getCodigo() == 0) {
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
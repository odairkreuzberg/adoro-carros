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
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.telas.padrao.DialogoPadrao;

/**
 * Formulário de Cadastro de Cargo de um Cliente
 * 
 * @author Fernando
 */
public class DialogoCargo extends DialogoPadrao {

	private static final long serialVersionUID = 1L;
	private JLabel labelNome;
	private JTextField textNome;
	private JLabel labelDescricao;
	private JTextArea textDescricao;	
	private FormHandle formHandle;
	private Cargo cargo;

	public DialogoCargo(JFrame frame, String titulo, boolean modal, Cargo cargo) {
		super(frame, titulo, modal);
		this.cargo = cargo;
		try {
            {
            	labelNome = new JLabel();
            	getPanelPrincipal().add(labelNome);
            	labelNome.setText("Nome:");
            	labelNome.setBounds(12, 38, 60, 20);
	        }
	        {
	        	textNome = new JTextField(this.cargo.getNome().trim());
	        	getPanelPrincipal().add(textNome);
	            textNome.setBounds(75, 35, 246, 21);
	        }
	        {
	        	labelDescricao = new JLabel();
	        	getPanelPrincipal().add(labelDescricao);
	        	labelDescricao.setText("Descrição:");
	        	labelDescricao.setBounds(12, 64, 100, 20);
        	}	        
	        {
	        	textDescricao = new JTextArea(this.cargo.getDescricao().trim());
	        	JScrollPane barra = new JScrollPane(textDescricao);
	        	barra.setBounds(75, 61, 290, 200);
	        	getPanelPrincipal().add(barra);
	        }	        
	        {
				if (this.cargo.getCodigo() != 0) {
					getTextCodigo().setText(String.valueOf(this.cargo.getCodigo()));
				}
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
			textDescricao.setText(cargo.getDescricao().trim());
			if (cargo.getCodigo() != 0) {
				getTextCodigo().setText(String.valueOf(cargo.getCodigo()));
			}

		}
		

		//Retorna true se encontrar um cargo e false se nao encontrar.
		private boolean existeCargo() {
			ListaObjeto listaObjeto = cargo.search("nome", "Igual", textNome.getText().trim());
			if (listaObjeto.getSize() > 0) {
				return false;
			}
			return true;
		}
		
		
		//Faz a Inserção de um cargo.
		private void inserir(){
			if (existeCargo()) {
				cargo.setNome(textNome.getText().trim());
				cargo.setDescricao(textDescricao.getText().trim());
				cargo.insert();							
			} else {
				JOptionPane.showMessageDialog(DialogoCargo.this, "Esse cargo já se encontra na Base de Dados!");
			}			
		}
		

		//Faz a Edição de um cargo.
		private void editar(){
			if (existeCargo()) {
				cargo.setNome(textNome.getText().trim());
				cargo.setDescricao(textDescricao.getText().trim());
				cargo.edit();	
			} else{
				JOptionPane.showMessageDialog(DialogoCargo.this, "Esse cargo já se encontra na Base de Dados!");
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
					dispose();
				}
			}
		}
	}

	/*----------------------------------------------------------
	 * FIM DE CLASSE LIMITROFE
	 *----------------------------------------------------------*/

}
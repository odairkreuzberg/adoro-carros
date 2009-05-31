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
            	labelNome.setText("Nome:");
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
			textDescricao.setText(cargo.getDescricao().trim());
			if (cargo.getCodigo() != 0) {
				textCodigo.setText(String.valueOf(cargo.getCodigo()));
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
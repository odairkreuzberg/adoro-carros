package br.iav.ac.telas.funcionario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.iav.ac.negocio.Cargo;
import br.iav.ac.negocio.Funcionario;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.telas.padrao.DialogoPadrao;

/**
 * Formulário de Cadastro de Cargo de um Funcionario
 * 
 * @author Fernando
 */
public class DialogoFuncionario extends DialogoPadrao {

	private static final long serialVersionUID = 1L;
	private JLabel labelNome;
	private JLabel labelTelefone;
	private JTextField textTelefone;
	private JTextField textNome;
	private JLabel labelCpf;
	private JTextField textCpf;
	private JLabel labelRg;
	private JTextField textRg;
	private JLabel labelDtNascimento;
	private JTextField textDtNascimento;
	private JLabel labelCargo;
	private JComboBox comboCargo;
	private JLabel labelRua;
	private JTextField textRua;
	private JLabel labelNumero;
	private JTextField textNumero;
	private JLabel labelBairro;
	private JTextField textBairro;
	private JLabel labelSalario;
	private JTextField textSalario;	
	private JLabel labelCep;
	private JTextField textCep;
	private JLabel labelComplemento;
	private JTextField textComplemento;
	private JLabel labelCidade;
	private JComboBox comboCidade;
	private FormHandle formHandle;
	private Funcionario funcionario;

	public DialogoFuncionario(JFrame frame, String titulo, boolean modal, Funcionario funcionario) {
		super(frame, titulo, modal);
		this.funcionario = funcionario;
		//25 de espaçamento
		try {
            {
            	labelNome = new JLabel();
            	getPanelPrincipal().add(labelNome);
            	labelNome.setText("Nome:");
            	labelNome.setBounds(12, 37, 80, 20);
	        }
	        {
	        	textNome = new JTextField(this.funcionario.getNome().trim());
	        	getPanelPrincipal().add(textNome);
	        	textNome.setBounds(90, 35, 246, 21);
	        }
            {
            	labelTelefone = new JLabel();
            	getPanelPrincipal().add(labelTelefone);
            	labelTelefone.setText("Telefone:");
            	labelTelefone.setBounds(12, 62, 80, 20);
	        }
	        {
	        	textTelefone = new JTextField(this.funcionario.getTelefone().trim());
	        	getPanelPrincipal().add(textTelefone);
	        	textTelefone.setBounds(90, 60, 246, 21);
	        }
            {
            	labelCpf = new JLabel();
            	getPanelPrincipal().add(labelCpf);
            	labelCpf.setText("CPF:");
            	labelCpf.setBounds(12, 87, 80, 20);
	        }
	        {
	        	textCpf = new JTextField(this.funcionario.getCpf().trim());
	        	getPanelPrincipal().add(textCpf);
	        	textCpf.setBounds(90, 85, 246, 21);
	        }
            {
            	labelRg = new JLabel();
            	getPanelPrincipal().add(labelRg);
            	labelRg.setText("RG:");
            	labelRg.setBounds(12, 112, 80, 20);
	        }
	        {
	        	textRg = new JTextField(this.funcionario.getRg().trim());
	        	getPanelPrincipal().add(textRg);
	        	textRg.setBounds(90, 110, 246, 21);
	        }
            {
            	labelDtNascimento = new JLabel();
            	getPanelPrincipal().add(labelDtNascimento);
            	labelDtNascimento.setText("Dt. Nasc.:");
            	labelDtNascimento.setBounds(12, 137, 80, 20);
	        }
	        {
	        	textDtNascimento = new JTextField(this.funcionario.getDataNascimento().toString());
	        	getPanelPrincipal().add(textDtNascimento);
	        	textDtNascimento.setBounds(90, 135, 246, 21);
	        }
            {
            	labelRua = new JLabel();
            	getPanelPrincipal().add(labelRua);
            	labelRua.setText("Rua:");
            	labelRua.setBounds(12, 162, 80, 20);
	        }
	        {
	        	textRua = new JTextField(this.funcionario.getEndereco().getRua().trim());
	        	getPanelPrincipal().add(textRua);
	        	textRua.setBounds(90, 160, 246, 21);
	        }
            {
            	labelNumero = new JLabel();
            	getPanelPrincipal().add(labelNumero);
            	labelNumero.setText("Número:");
            	labelNumero.setBounds(12, 187, 80, 20);
	        }
	        {
	        	textNumero = new JTextField(String.valueOf(this.funcionario.getEndereco().getNumero()));
	        	getPanelPrincipal().add(textNumero);
	        	textNumero.setBounds(90, 185, 246, 21);
	        }
            {
            	labelBairro = new JLabel();
            	getPanelPrincipal().add(labelBairro);
            	labelBairro.setText("Bairro:");
            	labelBairro.setBounds(12, 212, 80, 20);
	        }
	        {
	        	textBairro = new JTextField(this.funcionario.getEndereco().getBairro().trim());
	        	getPanelPrincipal().add(textBairro);
	        	textBairro.setBounds(90, 210, 246, 21);
	        }
            {
            	labelCep = new JLabel();
            	getPanelPrincipal().add(labelCep);
            	labelCep.setText("CEP:");
            	labelCep.setBounds(12, 237, 80, 20);
	        }
	        {
	        	textCep = new JTextField(this.funcionario.getEndereco().getCep().trim());
	        	getPanelPrincipal().add(textCep);
	        	textCep.setBounds(90, 235, 246, 21);
	        }
            {
            	labelComplemento = new JLabel();
            	getPanelPrincipal().add(labelComplemento);
            	labelComplemento.setText("Complemento:");
            	labelComplemento.setBounds(12, 262, 80, 20);
	        }
	        {
	        	textComplemento = new JTextField(this.funcionario.getEndereco().getComplemento().trim());
	        	getPanelPrincipal().add(textComplemento);
	        	textComplemento.setBounds(90, 260, 246, 21);
	        }
            {
            	labelSalario = new JLabel();
            	getPanelPrincipal().add(labelSalario);
            	labelSalario.setText("Salario:");
            	labelSalario.setBounds(12, 287, 80, 20);
	        }
	        {
	        	textSalario = new JTextField(String.valueOf(this.funcionario.getSalario()));
	        	getPanelPrincipal().add(textSalario);
	        	textSalario.setBounds(90, 285, 246, 21);
	        }	        
            {
            	labelCidade = new JLabel();
            	getPanelPrincipal().add(labelCidade);
            	labelCidade.setText("Cidade:");
            	labelCidade.setBounds(12, 312, 80, 20);
	        }
	        {
	        	comboCidade = new JComboBox();
	        	getPanelPrincipal().add(comboCidade);
	        	comboCidade.setSelectedItem(funcionario.getEndereco().getCidade().getNome().trim());
	        	comboCidade.setBounds(90, 310, 246, 21);
	        }
            {
            	labelCargo = new JLabel();
            	getPanelPrincipal().add(labelCargo);
            	labelCargo.setText("Cargo:");
            	labelCargo.setBounds(12, 337, 80, 20);
	        }
	        {
	        	comboCargo = new JComboBox();
	        	getPanelPrincipal().add(comboCargo);
	        	comboCargo.setSelectedItem(funcionario.getCargo().getNome().trim());
	        	comboCargo.setBounds(90, 335, 246, 21);
	        }	        
	        {
				if (this.funcionario.getCodigo() != 0) {
					getTextCodigo().setText(String.valueOf(this.funcionario.getCodigo()));
				}
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		inicializarHandlers();
		this.setSize(350, 420);
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
			textNome.setText(funcionario.getNome().trim());
			if (funcionario.getCodigo() != 0) {
				getTextCodigo().setText(String.valueOf(funcionario.getCodigo()));
			}

		}
		

		//Retorna true se encontrar um cargo e false se nao encontrar.
		private boolean existeCargo() {
			ListaObjeto listaObjeto = funcionario.search("nome", "Igual", textNome.getText().trim());
			if (listaObjeto.getSize() > 0) {
				return false;
			}
			return true;
		}
		
		
		//Faz a Inserção de um cargo.
		private void inserir(){
			if (existeCargo()) {
				funcionario.setNome(textNome.getText().trim());
				funcionario.insert();							
			} else {
				JOptionPane.showMessageDialog(DialogoFuncionario.this, "Esse Funcionario já se encontra na Base de Dados!");
			}			
		}
		

		//Faz a Edição de um cargo.
		private void editar(){
			if (existeCargo()) {
				funcionario.setNome(textNome.getText().trim());
				funcionario.edit();	
			} else{
				JOptionPane.showMessageDialog(DialogoFuncionario.this, "Esse Funcionario já se encontra na Base de Dados!");
			}
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == getBotaoCancelar()) {
				dispose();
			} else if (e.getSource() == getBotaoConfirmar()) {
				if (textNome.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoFuncionario.this,"O campo nome é obrigatório!");
				} else {
					if (funcionario.getCodigo() == 0) {
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
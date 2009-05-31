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

	private JTextField textCodigo;
	private JLabel labelNome;
	private JTextField textNome;
	private JLabel labelTelefone;
	private JTextField textTelefone;
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
		//25 de espaçamento entre cada atributo (JTextField e JLabel)
		int espacoEntreLinhas = 10;
		//Espaçamento dos JTextField
		int espacoDoTextField = 85;
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
	        	textNome = new JTextField(this.funcionario.getNome().trim());
	        	getPanelPrincipal().add(textNome);
	        	textNome.setBounds(espacoDoTextField, espacoEntreLinhas, 246, 20);
	        }
            {
            	espacoEntreLinhas = espacoEntreLinhas + 25;
            	labelTelefone = new JLabel();
            	getPanelPrincipal().add(labelTelefone);
            	labelTelefone.setText("Telefone:");
            	labelTelefone.setBounds(10, espacoEntreLinhas, 80, 20);
	        }
	        {
	        	textTelefone = new JTextField(this.funcionario.getTelefone().trim());
	        	getPanelPrincipal().add(textTelefone);
	        	textTelefone.setBounds(espacoDoTextField, espacoEntreLinhas, 246, 20);
	        }
            {
            	espacoEntreLinhas = espacoEntreLinhas + 25;
            	labelCpf = new JLabel();
            	getPanelPrincipal().add(labelCpf);
            	labelCpf.setText("CPF:");
            	labelCpf.setBounds(10, espacoEntreLinhas, 80, 20);
	        }
	        {
	        	textCpf = new JTextField(this.funcionario.getCpf().trim());
	        	getPanelPrincipal().add(textCpf);
	        	textCpf.setBounds(espacoDoTextField, espacoEntreLinhas, 246, 20);
	        }
            {
            	espacoEntreLinhas = espacoEntreLinhas + 25;
            	labelRg = new JLabel();
            	getPanelPrincipal().add(labelRg);
            	labelRg.setText("RG:");
            	labelRg.setBounds(10, espacoEntreLinhas, 80, 20);
	        }
	        {
	        	textRg = new JTextField(this.funcionario.getRg().trim());
	        	getPanelPrincipal().add(textRg);
	        	textRg.setBounds(espacoDoTextField, espacoEntreLinhas, 246, 20);
	        }
            {
            	espacoEntreLinhas = espacoEntreLinhas + 25;
            	labelDtNascimento = new JLabel();
            	getPanelPrincipal().add(labelDtNascimento);
            	labelDtNascimento.setText("Dt. Nasc.:");
            	labelDtNascimento.setBounds(10, espacoEntreLinhas, 80, 20);
	        }
	        {
	        	textDtNascimento = new JTextField(this.funcionario.getDataNascimento().toString());
	        	getPanelPrincipal().add(textDtNascimento);
	        	textDtNascimento.setBounds(espacoDoTextField, espacoEntreLinhas, 246, 20);
	        }
            {
            	espacoEntreLinhas = espacoEntreLinhas + 25;
            	labelRua = new JLabel();
            	getPanelPrincipal().add(labelRua);
            	labelRua.setText("Rua:");
            	labelRua.setBounds(10, espacoEntreLinhas, 80, 20);
	        }
	        {
	        	textRua = new JTextField(this.funcionario.getEndereco().getRua().trim());
	        	getPanelPrincipal().add(textRua);
	        	textRua.setBounds(espacoDoTextField, espacoEntreLinhas, 246, 20);
	        }
            {
            	espacoEntreLinhas = espacoEntreLinhas + 25;
            	labelNumero = new JLabel();
            	getPanelPrincipal().add(labelNumero);
            	labelNumero.setText("Número:");
            	labelNumero.setBounds(10, espacoEntreLinhas, 80, 20);
	        }
	        {
	        	textNumero = new JTextField(String.valueOf(this.funcionario.getEndereco().getNumero()));
	        	getPanelPrincipal().add(textNumero);
	        	textNumero.setBounds(espacoDoTextField, espacoEntreLinhas, 246, 20);
	        }
            {
            	espacoEntreLinhas = espacoEntreLinhas + 25;
            	labelBairro = new JLabel();
            	getPanelPrincipal().add(labelBairro);
            	labelBairro.setText("Bairro:");
            	labelBairro.setBounds(10, espacoEntreLinhas, 80, 20);
	        }
	        {
	        	textBairro = new JTextField(this.funcionario.getEndereco().getBairro().trim());
	        	getPanelPrincipal().add(textBairro);
	        	textBairro.setBounds(espacoDoTextField, espacoEntreLinhas, 246, 20);
	        }
            {
            	espacoEntreLinhas = espacoEntreLinhas + 25;
            	labelCep = new JLabel();
            	getPanelPrincipal().add(labelCep);
            	labelCep.setText("CEP:");
            	labelCep.setBounds(10, espacoEntreLinhas, 80, 20);
	        }
	        {
	        	textCep = new JTextField(this.funcionario.getEndereco().getCep().trim());
	        	getPanelPrincipal().add(textCep);
	        	textCep.setBounds(espacoDoTextField, espacoEntreLinhas, 246, 20);
	        }
            {
            	espacoEntreLinhas = espacoEntreLinhas + 25;
            	labelComplemento = new JLabel();
            	getPanelPrincipal().add(labelComplemento);
            	labelComplemento.setText("Complemento:");
            	labelComplemento.setBounds(10, espacoEntreLinhas, 80, 20);
	        }
	        {
	        	textComplemento = new JTextField(this.funcionario.getEndereco().getComplemento().trim());
	        	getPanelPrincipal().add(textComplemento);
	        	textComplemento.setBounds(espacoDoTextField, espacoEntreLinhas, 246, 20);
	        }
            {
            	espacoEntreLinhas = espacoEntreLinhas + 25;
            	labelSalario = new JLabel();
            	getPanelPrincipal().add(labelSalario);
            	labelSalario.setText("Salario:");
            	labelSalario.setBounds(10, espacoEntreLinhas, 80, 20);
	        }
	        {
	        	textSalario = new JTextField(String.valueOf(this.funcionario.getSalario()));
	        	getPanelPrincipal().add(textSalario);
	        	textSalario.setBounds(espacoDoTextField, espacoEntreLinhas, 246, 20);
	        }	        
            {
            	espacoEntreLinhas = espacoEntreLinhas + 25;
            	labelCidade = new JLabel();
            	getPanelPrincipal().add(labelCidade);
            	labelCidade.setText("Cidade:");
            	labelCidade.setBounds(10, espacoEntreLinhas, 80, 20);
	        }
	        {
	        	comboCidade = new JComboBox();
	        	getPanelPrincipal().add(comboCidade);
	        	comboCidade.setSelectedItem(funcionario.getEndereco().getCidade().getNome().trim());
	        	comboCidade.setBounds(espacoDoTextField, espacoEntreLinhas, 246, 20);
	        }
            {
            	espacoEntreLinhas = espacoEntreLinhas + 25;
            	labelCargo = new JLabel();
            	getPanelPrincipal().add(labelCargo);
            	labelCargo.setText("Cargo:");
            	labelCargo.setBounds(10, espacoEntreLinhas, 80, 20);
	        }
	        {
	        	comboCargo = new JComboBox();
	        	getPanelPrincipal().add(comboCargo);
	        	comboCargo.setSelectedItem(funcionario.getCargo().getNome().trim());
	        	comboCargo.setBounds(espacoDoTextField, espacoEntreLinhas, 246, 20);
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
				textCodigo.setText(String.valueOf(funcionario.getCodigo()));
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
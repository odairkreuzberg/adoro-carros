package br.iav.ac.telas.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import br.iav.ac.negocio.Cliente;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.telas.padrao.DialogoPadrao;

public class DialogoCliente extends DialogoPadrao {

	private JTextField textCodigo;
	private JLabel labelNome;
	private JTextField textNome;
	private JLabel labelTelefone;
	private JTextField textTelefone;
	private JLabel labelCpf;
	private JTextField textCpf;
	private JLabel labelRg;
	private JTextField textRg;
	private JLabel labelDataNascimento;
	private JTextField textDataNascimento;
	private JLabel labelProfissao;
	private JTextField textProfissao;
	private JLabel labelRua;
	private JTextField textRua;
	private JLabel labelNumero;
	private JTextField textNumero;
	private JLabel labelBairro;
	private JTextField textBairro;
	private JLabel labelCep;
	private JTextField textCep;
	private JLabel labelComplemento;
	private JTextField textComplemento;
	private JLabel labelCidade;
	private JComboBox comboCidade;
	private FormHandle formHandle;
	private Cliente cliente;

	public DialogoCliente(JFrame frame, String titulo, boolean modal, Cliente cliente) {
		super(frame, titulo, modal);
		this.cliente = cliente;
		//25 de espaçamento entre cada atributo (JTextField e JLabel)
		int espacoEntreLinhas = 10;
		//Espaçamento dos JTextField
		int espacoDoTextField = 90;
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
            	labelTelefone = new JLabel();
            	getPanelPrincipal().add(labelTelefone);
            	labelTelefone.setText("Telefone:");
            	labelTelefone.setBounds(10, espacoEntreLinhas, 80, 20);
	        }
	        {
	        	textTelefone = new JTextField();
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
	        	textCpf = new JTextField();
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
	        	textRg = new JTextField();
	        	getPanelPrincipal().add(textRg);
	        	textRg.setBounds(espacoDoTextField, espacoEntreLinhas, 246, 20);
	        }
            {
            	espacoEntreLinhas = espacoEntreLinhas + 25;
            	labelDataNascimento = new JLabel();
            	getPanelPrincipal().add(labelDataNascimento);
            	labelDataNascimento.setText("Dt. Nascimento:");
            	labelDataNascimento.setBounds(10, espacoEntreLinhas, 80, 20);
	        }
	        {
	        	textDataNascimento = new JTextField();
	        	getPanelPrincipal().add(textDataNascimento);
	        	textDataNascimento.setBounds(espacoDoTextField, espacoEntreLinhas, 246, 20);
	        }
            {
            	espacoEntreLinhas = espacoEntreLinhas + 25;
            	labelProfissao = new JLabel();
            	getPanelPrincipal().add(labelProfissao);
            	labelProfissao.setText("Profissão:");
            	labelProfissao.setBounds(10, espacoEntreLinhas, 80, 20);
	        }
	        {
	        	textProfissao = new JTextField();
	        	getPanelPrincipal().add(textProfissao);
	        	textProfissao.setBounds(espacoDoTextField, espacoEntreLinhas, 246, 20);
	        }
            {
            	espacoEntreLinhas = espacoEntreLinhas + 25;
            	labelRua = new JLabel();
            	getPanelPrincipal().add(labelRua);
            	labelRua.setText("Rua:");
            	labelRua.setBounds(10, espacoEntreLinhas, 80, 20);
	        }
	        {
	        	textRua = new JTextField();
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
	        	textNumero = new JTextField();
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
	        	textBairro = new JTextField();
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
	        	textCep = new JTextField();
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
	        	textComplemento = new JTextField();
	        	getPanelPrincipal().add(textComplemento);
	        	textComplemento.setBounds(espacoDoTextField, espacoEntreLinhas, 246, 20);
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
	        	comboCidade.setBounds(espacoDoTextField, espacoEntreLinhas, 246, 20);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		inicializarHandlers();
		this.setSize(350, 390);
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
		}

		private void limparCampos() {
			textNome.setText("");
		}
		
		private boolean existeCliente(){
			ListaObjeto listaObjeto = cliente.search("cor", "Igual", textNome.getText().trim());
			if (listaObjeto.getSize() > 0) {
				return false;
			}			
			return true;			
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == getBotaoCancelar()) {
				dispose();
			} else if (e.getSource() == getBotaoConfirmar()) {
				if (textNome.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoCliente.this, "O campo nome é obrigatório!");
				} else {
					if (cliente.getCodigo() == 0) {
						if (existeCliente()) {
							cliente.setNome(textNome.getText().trim());
							cliente.insert();							
						} else {
							JOptionPane.showMessageDialog(DialogoCliente.this, "Esse cliente já se encontra na Base de Dados!");
						}
					} else {
						if (existeCliente()) {
							cliente.setNome(textNome.getText().trim());
							cliente.edit();	
						} else{
							JOptionPane.showMessageDialog(DialogoCliente.this, "Esse cliente já se encontra na Base de Dados!");
						}
					}
					limparCampos();
					dispose();
				}
			}
		}
	}

}
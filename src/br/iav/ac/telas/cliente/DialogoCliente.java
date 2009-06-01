package br.iav.ac.telas.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.iav.ac.negocio.Cidade;
import br.iav.ac.negocio.Cliente;
import br.iav.ac.negocio.Endereco;
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
	private Cidade cidade;

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
			cidade = new Cidade();
			this.carregarComboCidade(cidade.load());
			comboCidade.setSelectedItem((Object) cliente.getEndereco().getCidade().getNome());
			if (cliente.getCodigo() != 0) {
				textCodigo.setText(String.valueOf(cliente.getCodigo()));
			}
		}

		private void limparCampos() {
			textNome.setText("");
			textTelefone.setText("");
			textCpf.setText("");
			textRg.setText("");
			textDataNascimento.setText("");
			textProfissao.setText("");
			textRua.setText("");
			textNumero.setText("");
			textBairro.setText("");
			textCep.setText("");
			textComplemento.setText("");
			comboCidade.setSelectedIndex(0);
		}
		
		private boolean existeCliente(){
			ListaObjeto listaObjeto = cliente.search("Código", "Igual", textCodigo.getText().trim());
			if (listaObjeto.getSize() > 0) {
				return false;
			}			
			return true;
		}
		
		/**
		 * Faz a inserção de um cliente.
		 */		
		private void inserir(){
			if (existeCliente()) {
				cliente.setNome(textNome.getText().trim());
				cliente.setTelefone(textTelefone.getText().trim());
				cliente.setCpf(textCpf.getText().trim());
				cliente.setRg(textRg.getText().trim());
				SimpleDateFormat converterDate = new SimpleDateFormat("dd/mm/yyyy");
				try {
					cliente.setDataNascimento(converterDate.parse(textDataNascimento.getText().trim()));
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(DialogoCliente.this, "O formato padrão de data utilizado é: \n\ndd/mm/aaaa");
				}
				cliente.setProfissao(textProfissao.getText().trim());
				Endereco endereco = new Endereco(textRua.getText().trim(),
												 Integer.valueOf(textNumero.getText().trim()),
												 textBairro.getText().trim(),
												 buscarCidade(),
												 textCep.getText().trim(),
												 textComplemento.getText().trim());
				cliente.setEndereco(endereco);
				cliente.insert();	
				carregarComboCidade(cidade.load());					
			} else {
				JOptionPane.showMessageDialog(DialogoCliente.this, "Esse cliente já se encontra na Base de Dados!");
			}			
		}
		
		/**
		 * Faz a edição de um cliente.
		 */
		private void editar(){
			if (existeCliente()) {
				cliente.setNome(textNome.getText().trim());
				cliente.setTelefone(textTelefone.getText().trim());
				cliente.setCpf(textCpf.getText().trim());
				cliente.setRg(textRg.getText().trim());
				//cliente.setDataNascimento(textDataNascimento.getText().trim());
				cliente.setProfissao(textProfissao.getText().trim());
				Endereco endereco = new Endereco(textRua.getText().trim(),
												 Integer.valueOf(textNumero.getText().trim()),
												 textBairro.getText().trim(),
												 buscarCidade(),
												 textCep.getText().trim(),
												 textComplemento.getText().trim());
				cliente.setEndereco(endereco);
				JOptionPane.showMessageDialog(DialogoCliente.this, buscarCidade().getNome());
				cliente.edit();
			} else {
				JOptionPane.showMessageDialog(DialogoCliente.this, "Esse cliente já se encontra na Base de Dados!");
			}
		}
		
		/**
		 * Carrega o Combobox com os nomes de todas as cidades cadastradas.
		 * 
		 * @param listaObjeto
		 */
		private void carregarComboCidade(ListaObjeto listaObjeto) {
			String[] comboArray = new String[listaObjeto.getSize()];
			for (int i = 0; i < listaObjeto.getSize(); i++) {
				Cidade cidade = (Cidade) listaObjeto.getObjeto(i);
				comboArray[i] = cidade.getNome();
				System.out.println(cliente.getEndereco().getCidade().getNome()+"bla");
			}
			ComboBoxModel comboMarcaModel = new DefaultComboBoxModel(comboArray);
			comboCidade.setModel(comboMarcaModel);
		}
		
		/**
		 * Busca no banco uma cidade
		 * @return Cidade
		 */
		private Cidade buscarCidade() {
			ListaObjeto listaObjeto = cidade.search("Nome", "Igual", (String)comboCidade.getSelectedItem());
			cidade = (Cidade)listaObjeto.getObjeto(0);
			return cidade;
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == getBotaoCancelar()) {
				dispose();
			} else if (e.getSource() == getBotaoConfirmar()) {
				if (textNome.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoCliente.this, "O campo nome é obrigatório!");
					textNome.requestFocus();
				} else if (textTelefone.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoCliente.this, "O campo telefone é obrigatório!");
					textTelefone.requestFocus();
				} else if (textCpf.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoCliente.this, "O campo CPF é obrigatório!");
					textCpf.requestFocus();
				} else if (textRg.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoCliente.this, "O campo RG é obrigatório!");
					textRg.requestFocus();
				} else if (textDataNascimento.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoCliente.this, "O campo data de nascimento é obrigatório!");
					textDataNascimento.requestFocus();
				} else if (textProfissao.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoCliente.this, "O campo profissão é obrigatório!");
					textProfissao.requestFocus();
				} else if (textRua.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoCliente.this, "O campo rua é obrigatório!");
					textRua.requestFocus();
				} else if (textNumero.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoCliente.this, "O campo número é obrigatório!");
					textNumero.requestFocus();
				} else if (textBairro.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoCliente.this, "O campo bairro é obrigatório!");
					textBairro.requestFocus();
				} else if (textCep.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoCliente.this, "O campo CEP é obrigatório!");
					textCep.requestFocus();
				} else if (textComplemento.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoCliente.this, "O campo complemento é obrigatório!");
					textComplemento.requestFocus();
				} else if (comboCidade.getSelectedItem().toString().equals("")) {
					JOptionPane.showMessageDialog(DialogoCliente.this, "O campo cidade é obrigatório!");
					comboCidade.requestFocus();
				} else {
					if (cliente.getCodigo() == 0) {
						inserir();
					} else {
						editar();
					}
					limparCampos();
					dispose();
				}
			}
		}
	}

}
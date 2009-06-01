package br.iav.ac.telas.fornecedor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.iav.ac.negocio.Cidade;
import br.iav.ac.negocio.Endereco;
import br.iav.ac.negocio.Fornecedor;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.telas.TelaPrincipal;
import br.iav.ac.telas.cidade.PainelCidade;
import br.iav.ac.telas.padrao.DialogoCRUD;
import br.iav.ac.telas.padrao.DialogoPadrao;

public class DialogoFornecedor extends DialogoPadrao {

	private JTextField textCodigo;
	private JLabel labelNomeFantazia;
	private JTextField textNomeFantazia;
	private JLabel labelRazaoSocial;
	private JTextField textRazaoSocial;
	private JLabel labelCnpj;
	private JFormattedTextField textCnpj;
	private MaskFormatter mascaraCnpj;	
	private JLabel labelTelefone;
	private JFormattedTextField textTelefone;
	private MaskFormatter mascaraTelefone;	
	private JLabel labelFax;
	private JFormattedTextField textFax;
	private MaskFormatter mascaraFax;	
	private JLabel labelCep;
	private JFormattedTextField textCep;
	private MaskFormatter mascaraCep;	
	private JTextField textRg;
	private JTextField textProfissao;
	private JLabel labelRua;
	private JTextField textRua;
	private JLabel labelNumero;
	private JTextField textNumero;
	private JLabel labelBairro;
	private JTextField textBairro;
	private JLabel labelComplemento;
	private JTextField textComplemento;
	private JLabel labelCidade;
	private JComboBox comboCidade;
	private FormHandle formHandle;
	private Fornecedor fornecedor;
	private Cidade cidade;
	private JButton botaoCidade;

	public DialogoFornecedor(JFrame frame, String titulo, boolean modal, Fornecedor fornecedor) {
		super(frame, titulo, modal);
		this.fornecedor = fornecedor;
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
            	labelNomeFantazia = new JLabel();
            	getPanelPrincipal().add(labelNomeFantazia);
            	labelNomeFantazia.setText("Nome Fantazia:");
            	labelNomeFantazia.setBounds(10, espacoEntreLinhas, 80, 20);
	        }
	        {
	        	textNomeFantazia = new JTextField();
	        	getPanelPrincipal().add(textNomeFantazia);
	        	textNomeFantazia.setBounds(espacoDoTextField, espacoEntreLinhas, 246, 20);
	        }
            {
            	espacoEntreLinhas = espacoEntreLinhas + 25;
            	labelRazaoSocial = new JLabel();
            	getPanelPrincipal().add(labelRazaoSocial);
            	labelRazaoSocial.setText("Razão Social:");
            	labelRazaoSocial.setBounds(10, espacoEntreLinhas, 80, 20);
	        }
	        {
	        	textRazaoSocial = new JTextField();
	        	getPanelPrincipal().add(textRazaoSocial);
	        	textRazaoSocial.setBounds(espacoDoTextField, espacoEntreLinhas, 246, 20);
	        }
            {
            	espacoEntreLinhas = espacoEntreLinhas + 25;
            	labelCnpj = new JLabel();
            	getPanelPrincipal().add(labelCnpj);
            	labelCnpj.setText("CNPJ:");
            	labelCnpj.setBounds(10, espacoEntreLinhas, 80, 20);
	        }
	        {
	            mascaraCnpj = new MaskFormatter("##.###.###/####-##");	            
	            mascaraCnpj.setPlaceholderCharacter('_');  
	        	textCnpj = new JFormattedTextField(mascaraCnpj);
	        	getPanelPrincipal().add(textCnpj);
	        	textCnpj.setBounds(espacoDoTextField, espacoEntreLinhas, 246, 20);
	        }
            {
            	espacoEntreLinhas = espacoEntreLinhas + 25;
            	labelTelefone = new JLabel();
            	getPanelPrincipal().add(labelTelefone);
            	labelTelefone.setText("Telefone:");
            	labelTelefone.setBounds(10, espacoEntreLinhas, 80, 20);
	        }
	        {
	            mascaraTelefone = new MaskFormatter("(##)####-####");	            
	            mascaraTelefone.setPlaceholderCharacter('_');  
	        	textTelefone = new JFormattedTextField(mascaraTelefone);
	        	getPanelPrincipal().add(textTelefone);
	        	textTelefone.setBounds(espacoDoTextField, espacoEntreLinhas, 246, 20);
	        }
            {
            	espacoEntreLinhas = espacoEntreLinhas + 25;
            	labelFax = new JLabel();
            	getPanelPrincipal().add(labelFax);
            	labelFax.setText("Fax:");
            	labelFax.setBounds(10, espacoEntreLinhas, 80, 20);
	        }
	        {
	            mascaraFax = new MaskFormatter("(##)####-####");	            
	            mascaraFax.setPlaceholderCharacter('_');  
	        	textFax = new JFormattedTextField(mascaraFax);
	        	getPanelPrincipal().add(textFax);
	        	textFax.setBounds(espacoDoTextField, espacoEntreLinhas, 246, 20);
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
	            mascaraCep = new MaskFormatter("#####-###");	            
	            mascaraCep.setPlaceholderCharacter('_');  
	        	textCep = new JFormattedTextField(mascaraCep);
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
	        	comboCidade.setBounds(espacoDoTextField, espacoEntreLinhas, 215, 20);
	        }
			{
				botaoCidade = new JButton();
				getPanelPrincipal().add(botaoCidade);
				botaoCidade.setText("+");
				botaoCidade.setBounds(314, espacoEntreLinhas, 22, 20);
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
		botaoCidade.addActionListener(formHandle);
	}

	class FormHandle implements ActionListener { 
		
		public FormHandle() {
			super();
			cidade = new Cidade();			
			textCnpj.setText(fornecedor.getCnpj().trim());
			textTelefone.setText(fornecedor.getTelefone().trim());
			textFax.setText(fornecedor.getFax().trim());
			textNomeFantazia.setText(fornecedor.getNome().trim());
			textRazaoSocial.setText(fornecedor.getRazaoSocial().trim());
			textRua.setText(fornecedor.getEndereco().getRua().trim());
			textBairro.setText(fornecedor.getEndereco().getBairro().trim());
			textCep.setText(fornecedor.getEndereco().getCep().trim());
			textComplemento.setText(fornecedor.getEndereco().getComplemento().trim());
			this.carregarComboCidade(cidade.load());
			comboCidade.setSelectedItem((Object) fornecedor.getEndereco().getCidade().getNome());
			if (fornecedor.getCodigo() != 0) {
				textCodigo.setText(String.valueOf(fornecedor.getCodigo()));
				textNumero.setText(String.valueOf(fornecedor.getEndereco().getNumero()));
			}
		}
		
		private boolean existeFornecedor(){
			ListaObjeto listaObjeto = fornecedor.search("Razão Social", "Igual", textRazaoSocial.getText().trim());
			if (listaObjeto.getSize() > 0) {
				return false;
			}			
			return true;
		}
		
		/**
		 * Faz a inserção de um fornecedor.
		 */		
		private void inserir(){
			if (existeFornecedor()) {
				fornecedor.setNome(textNomeFantazia.getText().trim());
				fornecedor.setCnpj(textCnpj.getText().trim());
				fornecedor.setFax(textFax.getText().trim());
				fornecedor.setRazaoSocial(textRazaoSocial.getText().trim());
				fornecedor.setTelefone(textTelefone.getText().trim());

				Endereco endereco = new Endereco(textRua.getText().trim(),
												 Integer.valueOf(textNumero.getText().trim()),
												 textBairro.getText().trim(),
												 buscarCidade(),
												 textCep.getText().trim(),
												 textComplemento.getText().trim());
				fornecedor.setEndereco(endereco);
				fornecedor.insert();					
			} else {
				JOptionPane.showMessageDialog(DialogoFornecedor.this, "Esse fornecedor já se encontra na Base de Dados!");
			}			
		}
		
		/**
		 * Faz a edição de um fornecedor.
		 */
		private void editar(){
			if (existeFornecedor()) {
				fornecedor.setNome(textNomeFantazia.getText().trim());
				fornecedor.setCnpj(textCnpj.getText().trim());
				fornecedor.setFax(textFax.getText().trim());
				fornecedor.setRazaoSocial(textRazaoSocial.getText().trim());
				fornecedor.setTelefone(textTelefone.getText().trim());

				Endereco endereco = new Endereco(textRua.getText().trim(),
												 Integer.valueOf(textNumero.getText().trim()),
												 textBairro.getText().trim(),
												 buscarCidade(),
												 textCep.getText().trim(),
												 textComplemento.getText().trim());
				fornecedor.setEndereco(endereco);
				fornecedor.edit();
			} else {
				JOptionPane.showMessageDialog(DialogoFornecedor.this, "Esse fornecedor já se encontra na Base de Dados!");
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
				System.out.println(fornecedor.getEndereco().getCidade().getNome()+"bla");
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
			}else if (e.getSource() == botaoCidade) {
				DialogoCRUD dialogoCRUD = new DialogoCRUD(TelaPrincipal.instancia,
					"Cadastro de Cidade", true);
				dialogoCRUD.setPainel(new PainelCidade());
				carregarComboCidade(cidade.load());
				
			} else if (e.getSource() == getBotaoConfirmar()) {
				if (textNomeFantazia.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoFornecedor.this, "O campo nome é obrigatório!");
					textNomeFantazia.requestFocus();
				} else if (textRazaoSocial.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoFornecedor.this, "O campo telefone é obrigatório!");
					textTelefone.requestFocus();
				} else if (textCnpj.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoFornecedor.this, "O campo CNPJ é obrigatório!");
					textCnpj.requestFocus();
				}else if (textRua.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoFornecedor.this, "O campo rua é obrigatório!");
					textRua.requestFocus();
				} else if (textNumero.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoFornecedor.this, "O campo número é obrigatório!");
					textNumero.requestFocus();
				} else if (textBairro.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoFornecedor.this, "O campo bairro é obrigatório!");
					textBairro.requestFocus();
				} else if (textCep.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoFornecedor.this, "O campo CEP é obrigatório!");
					textCep.requestFocus();
				}else {
					if (fornecedor.getCodigo() == 0) {
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
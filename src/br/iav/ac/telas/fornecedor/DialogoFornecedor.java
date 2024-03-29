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
import br.iav.ac.negocio.Objeto;
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
		//25 de espa�amento entre cada atributo (JTextField e JLabel)
		int espacoEntreLinhas = 10;
		//Espa�amento dos JTextField
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
            	labelNomeFantazia.setText("Nome Fantazia:*");
            	labelNomeFantazia.setBounds(8, espacoEntreLinhas, 90, 20);
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
            	labelRazaoSocial.setText("Raz�o Social:*");
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
            	labelCnpj.setText("CNPJ:*");
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
            	labelTelefone.setText("Telefone:*");
            	labelTelefone.setBounds(10, espacoEntreLinhas, 80, 20);
	        }
	        {
	            mascaraTelefone = new MaskFormatter("####-####");	            
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
	            mascaraFax = new MaskFormatter("####-####");	            
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
            	labelNumero.setText("N�mero:");
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
            	labelCidade.setText("Cidade:*");
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
			comboCidade.setEditable(true);
			comboCidade.setSelectedItem((Object) fornecedor.getEndereco().getCidade());
			comboCidade.setEditable(false);
			this.carregarComboCidade(cidade.load());			
			if (fornecedor.getCodigo() != 0) {
				textCodigo.setText(String.valueOf(fornecedor.getCodigo()));
				textNumero.setText(String.valueOf(fornecedor.getEndereco().getNumero()));
			}
		}
		
		/**
		 * Faz a inser��o de um fornecedor.
		 */		
		private void inserir() {
			fornecedor.setNome(textNomeFantazia.getText().trim());
			fornecedor.setCnpj(textCnpj.getText().trim());
			fornecedor.setFax(textFax.getText().trim());
			fornecedor.setRazaoSocial(textRazaoSocial.getText().trim());
			fornecedor.setTelefone(textTelefone.getText().trim());

			Endereco endereco = new Endereco();
			endereco.setBairro(textBairro.getText().trim());
			endereco.setCep(textCep.getText().trim());
			endereco.setCidade(buscarCidade());
			endereco.setComplemento(textComplemento.getText().trim());
			endereco.setRua(textRua.getText().trim());
			if (!textNumero.getText().trim().equals("")){
				endereco.setNumero(Integer.valueOf(textNumero.getText().trim()));
			}
			fornecedor.setEndereco(endereco);
			fornecedor.insert();

		}
		
		/**
		 * Faz a edi��o de um fornecedor.
		 */
		private void editar(){
			fornecedor.setNome(textNomeFantazia.getText().trim());
			fornecedor.setCnpj(textCnpj.getText().trim());
			fornecedor.setFax(textFax.getText().trim());
			fornecedor.setRazaoSocial(textRazaoSocial.getText().trim());
			fornecedor.setTelefone(textTelefone.getText().trim());
			Endereco endereco = new Endereco();
			endereco.setBairro(textBairro.getText().trim());
			endereco.setCep(textCep.getText().trim());
			endereco.setCidade(buscarCidade());
			endereco.setComplemento(textComplemento.getText().trim());
			endereco.setRua(textRua.getText().trim());
			if (!textNumero.getText().trim().equals("")){
				endereco.setNumero(Integer.valueOf(textNumero.getText().trim()));
			}
			fornecedor.setEndereco(endereco);
			fornecedor.edit();
			
		}
		
		/**
		 * Carrega o Combobox com os nomes de todas as cidades cadastradas.
		 * 
		 * @param listaObjeto
		 */
		private void carregarComboCidade(ListaObjeto listaObjeto) {
			Objeto[] comboArray = new Objeto[listaObjeto.getSize()];
			for (int i = 0; i < listaObjeto.getSize(); i++) {
				Cidade cidade = (Cidade) listaObjeto.getObjeto(i);
				comboArray[i] = cidade;
			}
			ComboBoxModel comboMarcaModel = new DefaultComboBoxModel(comboArray);
			comboCidade.setModel(comboMarcaModel);
		}
		
		/**
		 * Busca no banco uma cidade
		 * @return Cidade
		 */
		private Cidade buscarCidade() {
			ListaObjeto listaObjeto;
			if(comboCidade.getSelectedIndex() != -1){
				listaObjeto = cidade.search("Nome", "Igual", ((Cidade)comboCidade.getSelectedItem()).getNome());
			}else{
				listaObjeto = cidade.search("Nome", "Igual", "");
			}
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
					JOptionPane.showMessageDialog(DialogoFornecedor.this, "O campo Nome Fantasia � obrigat�rio!");
					textNomeFantazia.requestFocus();
				} else if (textRazaoSocial.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoFornecedor.this, "O campo Raz�o Social � obrigat�rio!");
					textRazaoSocial.requestFocus();
				} else if (textCnpj.getText().equals("__.___.___/____-__")) {
					JOptionPane.showMessageDialog(DialogoFornecedor.this, "O campo CNPJ � obrigat�rio!");
					textCnpj.requestFocus();
				} else if (textTelefone.getText().equals("____-____")) {
					JOptionPane.showMessageDialog(DialogoFornecedor.this, "O campo Telefone � obrigat�rio!");
					textTelefone.requestFocus();
				}else if (comboCidade.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(DialogoFornecedor.this, "O campo Cidade � obrigat�rio!");
					comboCidade.requestFocus();					
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
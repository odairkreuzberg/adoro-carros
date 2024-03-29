package br.iav.ac.telas.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import br.iav.ac.negocio.Cliente;
import br.iav.ac.negocio.Endereco;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.negocio.Objeto;
import br.iav.ac.telas.TelaPrincipal;
import br.iav.ac.telas.cidade.PainelCidade;
import br.iav.ac.telas.funcionario.DialogoFuncionario;
import br.iav.ac.telas.padrao.DialogoCRUD;
import br.iav.ac.telas.padrao.DialogoPadrao;
import br.iav.ac.telas.padrao.PainelPadrao;

public class DialogoCliente extends DialogoPadrao {

	private JTextField textCodigo;
	private JLabel labelNome;
	private JTextField textNome;
	private JLabel labelTelefone;
	private JTextField textTelefone;
	private JLabel labelCpf;
	private JFormattedTextField textCpf;
	private MaskFormatter mascaraCpf;	
	private JLabel labelRg;
	private JTextField textRg;
	private JLabel labelDataNascimento;
	private JFormattedTextField textDataNascimento;
	private MaskFormatter mascaraDtNascimento;
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
	private JButton botaoCidade;
	private FormHandle formHandle;
	private Cliente cliente;
	private Cidade cidade;

	public DialogoCliente(JFrame frame, String titulo, boolean modal, Cliente cliente) {		
		super(frame, titulo, modal);
		this.cliente = cliente;
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
            {
            	espacoEntreLinhas = espacoEntreLinhas + 25;
            	labelTelefone = new JLabel();
            	getPanelPrincipal().add(labelTelefone);
            	labelTelefone.setText("Telefone:*");
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
            	labelCpf.setText("CPF:*");
            	labelCpf.setBounds(10, espacoEntreLinhas, 80, 20);
	        }
	        {
	            mascaraCpf = new MaskFormatter("###.###.###-##");	            
	            mascaraCpf.setPlaceholderCharacter('_');  
	        	textCpf = new JFormattedTextField(mascaraCpf);
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
            	labelDataNascimento.setText("Dt. Nascimento:*");
            	labelDataNascimento.setBounds(8, espacoEntreLinhas, 90, 20);
	        }
	        {
	            mascaraDtNascimento = new MaskFormatter("##/##/####");	            
	            mascaraDtNascimento.setPlaceholderCharacter('_');  
	        	textDataNascimento = new JFormattedTextField(mascaraDtNascimento);
	        	getPanelPrincipal().add(textDataNascimento);
	        	textDataNascimento.setBounds(espacoDoTextField, espacoEntreLinhas, 246, 20);
	        }
            {
            	espacoEntreLinhas = espacoEntreLinhas + 25;
            	labelProfissao = new JLabel();
            	getPanelPrincipal().add(labelProfissao);
            	labelProfissao.setText("Profiss�o:");
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
            	labelCidade.setText("Cidade:*");
            	labelCidade.setBounds(10, espacoEntreLinhas, 80, 20);
	        }
	        {
	        	comboCidade = new JComboBox();
	        	getPanelPrincipal().add(comboCidade);
	        	comboCidade.setBounds(espacoDoTextField, espacoEntreLinhas, 220, 20);
	        }
			{
				botaoCidade = new JButton();
				getPanelPrincipal().add(botaoCidade);
				botaoCidade.setText("+");
				botaoCidade.setBounds(313, espacoEntreLinhas, 22, 20);
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
			this.carregarComboCidade(cidade.load());
			comboCidade.setEditable(true);
			comboCidade.setSelectedItem((Object) cliente.getEndereco().getCidade());
			comboCidade.setEditable(false);
			if (cliente.getCodigo() != 0) {
				this.carregarDados();
			}else{
				cliente = new Cliente();
			}
		}
		
		private void carregarDados(){
			textCodigo.setText(String.valueOf(cliente.getCodigo()));
			textNome.setText(cliente.getNome().trim());
			textTelefone.setText(cliente.getTelefone().trim());
			textCpf.setText(cliente.getCpf().trim());
			textRg.setText(cliente.getRg().trim());
			textDataNascimento.setText(cliente.dataNascimentoToString().trim());
			textProfissao.setText(cliente.getProfissao().trim());
			textRua.setText(cliente.getEndereco().getRua().trim());
			textNumero.setText(String.valueOf(cliente.getEndereco().getNumero()));
			textBairro.setText(cliente.getEndereco().getBairro().trim());
			textCep.setText(cliente.getEndereco().getCep().trim());
			textComplemento.setText(cliente.getEndereco().getComplemento().trim());
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
		
		/**
		 * Faz a inser��o de um cliente.
		 */		
		private void inserir(){
			//if (existeCliente()) {
				try {
					cliente.setNome(textNome.getText().trim());
					cliente.setTelefone(textTelefone.getText().trim());
					cliente.setCpf(textCpf.getText().trim());
					cliente.setRg(textRg.getText().trim());
					SimpleDateFormat converterDate = new SimpleDateFormat("dd/MM/yyyy");
					cliente.setDataNascimento(converterDate.parse(textDataNascimento.getText().trim()));
					cliente.setProfissao(textProfissao.getText().trim());
					cidade = ((Cidade)comboCidade.getSelectedItem()).clone();
					int numero = 0;
					if(!textNumero.getText().equals("")){
						numero = Integer.valueOf(textNumero.getText().trim());
					}
					Endereco endereco = new Endereco(textRua.getText().trim(),
													 numero,
													 textBairro.getText().trim(),
													 cidade,
													 textCep.getText().trim(),
													 textComplemento.getText().trim());
					cliente.setEndereco(endereco);
					cliente.insert();	
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(DialogoCliente.this, "O formato padr�o de data utilizado �: \n\ndd/mm/aaaa\n");
				}
			//} else {
			//	JOptionPane.showMessageDialog(DialogoCliente.this, "Esse cliente j� se encontra na Base de Dados!");
			//}			
		}
		
		/**
		 * Faz a edi��o de um cliente.
		 */
		private void editar(){
			//if (!existeCliente()) {
				try {
					cliente.setNome(textNome.getText().trim());
					cliente.setTelefone(textTelefone.getText().trim());
					cliente.setCpf(textCpf.getText().trim());
					cliente.setRg(textRg.getText().trim());
					SimpleDateFormat converterDate = new SimpleDateFormat("dd/MM/yyyy");
					cliente.setDataNascimento(converterDate.parse(textDataNascimento.getText().trim()));
					cliente.setProfissao(textProfissao.getText().trim());
					cidade = ((Cidade)comboCidade.getSelectedItem()).clone();
					int numero = 0;
					if(!textNumero.getText().equals("")){
						numero = Integer.valueOf(textNumero.getText().trim());
					}
					Endereco endereco = new Endereco(textRua.getText().trim(),
													 numero,
													 textBairro.getText().trim(),
													 cidade,
													 textCep.getText().trim(),
													 textComplemento.getText().trim());
					cliente.setEndereco(endereco);
					//JOptionPane.showMessageDialog(DialogoCliente.this, buscarCidade().getNome());
					cliente.edit();
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(DialogoCliente.this, "O formato padr�o de data utilizado �: \n\ndd/mm/aaaa\n");
				}
			//} else {
			//	JOptionPane.showMessageDialog(DialogoCliente.this, "Esse cliente j� se encontra na Base de Dados!");
			//}
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
			cidade = ((Cidade)comboCidade.getSelectedItem()).clone();
			ListaObjeto listaObjeto = cidade.search("C�digo", "Igual", String.valueOf(cidade.getCodigo()));
			cidade = (Cidade)listaObjeto.getObjeto(0);
			return cidade;
		}
		
		/**
		 * @param painelPadrao
		 * @param titulo
		 */
		private void showPainel(PainelPadrao painelPadrao, String titulo) {
			DialogoCRUD dialogoCRUD = new DialogoCRUD(TelaPrincipal.instancia, titulo, true);
			dialogoCRUD.setPainel(painelPadrao);
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == botaoCidade) {				
				showPainel(new PainelCidade(), "Cadastros de Cidades");	
				this.carregarComboCidade(cidade.load());
			} else if (e.getSource() == getBotaoCancelar()) {
				dispose();
			} else if (e.getSource() == getBotaoConfirmar()) {
				if (textNome.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoCliente.this, "O campo nome � obrigat�rio!");
					textNome.requestFocus();
				} else if (textTelefone.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoCliente.this, "O campo telefone � obrigat�rio!");
					textTelefone.requestFocus();
				} else if (textCpf.getText().equals("___.___.___-__")) {
					JOptionPane.showMessageDialog(DialogoCliente.this, "O campo CPF � obrigat�rio!");
					textCpf.requestFocus();
				} else if (textDataNascimento.getText().equals("__/__/____")) {
					JOptionPane.showMessageDialog(DialogoCliente.this, "O campo data de nascimento � obrigat�rio!");
					textDataNascimento.requestFocus();
				}else if ( ((cliente.getCodigo() == 0) && ( comboCidade.getSelectedIndex() == -1)) ||
						   ((cliente.getCodigo() != 0) && ( comboCidade.getSelectedItem() == null)) ){
					JOptionPane.showMessageDialog(DialogoCliente.this,"O campo Cidade � obrigat�rio!");		
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
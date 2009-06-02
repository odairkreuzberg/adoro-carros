package br.iav.ac.telas.funcionario;

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

import br.iav.ac.negocio.Cargo;
import br.iav.ac.negocio.Cidade;
import br.iav.ac.negocio.Endereco;
import br.iav.ac.negocio.Funcionario;
import br.iav.ac.negocio.ListaObjeto;
import br.iav.ac.negocio.Objeto;
import br.iav.ac.telas.TelaPrincipal;
import br.iav.ac.telas.cargo.PainelCargo;
import br.iav.ac.telas.cidade.PainelCidade;
import br.iav.ac.telas.padrao.DialogoCRUD;
import br.iav.ac.telas.padrao.DialogoPadrao;
import br.iav.ac.telas.padrao.PainelPadrao;

/**
 * Formulário de Cadastro de Cargo de um Funcionario
 * 
 * @author Fernando
 */
public class DialogoFuncionario extends DialogoPadrao {

	private static final long serialVersionUID = 1L;
	private JTextField textCodigo;
	private JLabel labelNome;
	private JTextField textNome;
	private JLabel labelTelefone;
	private JFormattedTextField textTelefone;
	private MaskFormatter mascaraTelefone;	
	private JLabel labelCpf;
	private JFormattedTextField textCpf;
	private MaskFormatter mascaraCpf;	
	private JLabel labelRg;
	private JTextField textRg;
	private JLabel labelDtNascimento;
	private JFormattedTextField textDtNascimento;
	private MaskFormatter mascaraDtNascimento;
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
	private Cidade cidade;
	private Cargo cargo;
	private JButton botaoCidade;
	private JButton botaoCargo;

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
	            mascaraTelefone = new MaskFormatter("####-####");	            
	            mascaraTelefone.setPlaceholderCharacter('_');  
	        	textTelefone = new JFormattedTextField(mascaraTelefone);
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
            	labelDtNascimento = new JLabel();
            	getPanelPrincipal().add(labelDtNascimento);
            	labelDtNascimento.setText("Dt. Nasc.:");
            	labelDtNascimento.setBounds(10, espacoEntreLinhas, 80, 20);
	        }
	        {
	            mascaraDtNascimento = new MaskFormatter("##/##/####");	            
	            mascaraDtNascimento.setPlaceholderCharacter('_');  
	        	textDtNascimento = new JFormattedTextField(mascaraDtNascimento);
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
            	labelSalario = new JLabel();
            	getPanelPrincipal().add(labelSalario);
            	labelSalario.setText("Salario:");
            	labelSalario.setBounds(10, espacoEntreLinhas, 80, 20);
	        }
	        {
	        	textSalario = new JTextField();
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
	        	comboCidade.setBounds(espacoDoTextField, espacoEntreLinhas, 220, 20);
	        }
	        {
				botaoCidade = new JButton();
				getPanelPrincipal().add(botaoCidade);
				botaoCidade.setText("+");
				botaoCidade.setBounds(313, espacoEntreLinhas, 22, 20);
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
	        	comboCargo.setBounds(espacoDoTextField, espacoEntreLinhas, 220, 20);
	        }
	        {
				botaoCargo = new JButton();
				getPanelPrincipal().add(botaoCargo);
				botaoCargo.setText("+");
				botaoCargo.setBounds(313, espacoEntreLinhas, 22, 20);
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
		botaoCargo.addActionListener(formHandle);
		botaoCidade.addActionListener(formHandle);
	}
	
	
	/*----------------------------------------------------------
	 * CLASSE LIMITROFE
	 *----------------------------------------------------------*/

	class FormHandle implements ActionListener {

		public FormHandle() {
			super();
			cargo = new Cargo();
			cidade = new Cidade();
			this.carregarComboCargo(cargo.load());
			this.carregarComboCidade(cidade.load());		
			if( (funcionario != null) && (funcionario.getCodigo() > 0)){
				this.carregarDados();
			}else{
				funcionario = new Funcionario();
			}
		}
		
		private void carregarDados(){
			if(funcionario.getEndereco() == null){
				funcionario.setEndereco(new Endereco());
			}
			if(funcionario.getEndereco().getCidade() == null){
				funcionario.getEndereco().setCidade(new Cidade());
			}
			if(funcionario.getCargo() == null){
				funcionario.setCargo(new Cargo());
			}
			textCodigo.setText(String.valueOf(funcionario.getCodigo()));
			textNome.setText(funcionario.getNome());
			textBairro.setText(funcionario.getEndereco().getBairro());
			textCep.setText(funcionario.getEndereco().getCep());
			textComplemento.setText(funcionario.getEndereco().getComplemento());
			textCpf.setText(funcionario.getCpf());
			textDtNascimento.setText(funcionario.dataNascimentoToString());
			textNumero.setText(String.valueOf(funcionario.getEndereco().getNumero()));
			textRg.setText(funcionario.getRg());
			textRua.setText(funcionario.getEndereco().getRua());
			textSalario.setText(String.valueOf(funcionario.getSalario()));
			textTelefone.setText(funcionario.getTelefone());
			comboCidade.setEditable(true);
			comboCargo.setEditable(true);
			comboCidade.setSelectedItem(((Object)funcionario.getEndereco().getCidade()));
			comboCargo.setSelectedItem((Object)funcionario.getCargo());
			comboCidade.setEditable(false);
			comboCargo.setEditable(false);
		}
		
		
		private void limparCampos(){
			textNome.setText("");
			textTelefone.setText("");
			textCpf.setText("");
			textRg.setText("");
			textDtNascimento.setText("");
			textRua.setText("");
			textNumero.setText("");
			textBairro.setText("");
			textCep.setText("");
			textComplemento.setText("");
			textSalario.setText("");
			comboCidade.setSelectedIndex(0);
			comboCargo.setSelectedIndex(0);
		}
		
		private void carregarComboCidade(ListaObjeto listaObjeto) {
			Objeto[] comboArray = new Objeto[listaObjeto.getSize()];
			for (int i = 0; i < listaObjeto.getSize(); i++) {
				Cidade cidade = (Cidade) listaObjeto.getObjeto(i);
				comboArray[i] = cidade;
			}
			ComboBoxModel comboMarcaModel = new DefaultComboBoxModel(comboArray);
			comboCidade.setModel(comboMarcaModel);
		}

		private void carregarComboCargo(ListaObjeto listaObjeto) {
			Objeto[] comboArray = new Objeto[listaObjeto.getSize()];
			for (int i = 0; i < listaObjeto.getSize(); i++) {
				Cargo cargo = (Cargo) listaObjeto.getObjeto(i);
				comboArray[i] = cargo;
			}
			ComboBoxModel comboMarcaModel = new DefaultComboBoxModel(comboArray);
			comboCargo.setModel(comboMarcaModel);
		}
				
		
		private boolean existeFuncionario() {
			ListaObjeto listaObjeto = funcionario.search("Código", "Igual", textCodigo.getText());
			if (listaObjeto.getSize() > 0) {
				return false;
			}
			return true;
		}
		
		
		private void inserir(){
			if (existeFuncionario()) {
				try {
					SimpleDateFormat converterDate = new SimpleDateFormat("dd/MM/yyyy");
					funcionario.setCpf(textCpf.getText());
					funcionario.setDataNascimento(converterDate.parse(textDtNascimento.getText().trim()));
					Endereco end = new Endereco();
					end.setBairro(textBairro.getText());
					end.setCep(textCep.getText());
					end.setComplemento(textComplemento.getText());
					if(!(textNumero.getText().equals(""))){
						end.setNumero(Integer.valueOf(textNumero.getText()));
					}
					end.setRua(textRua.getText());
					end.setCidade((Cidade)comboCidade.getSelectedItem());
					Cargo cargo = new Cargo();
					cargo = ((Cargo)comboCargo.getSelectedItem());
					funcionario.setCargo(cargo);
					funcionario.setEndereco(end);
					funcionario.setNome(textNome.getText());
					funcionario.setRg(textRg.getText());
					funcionario.setSalario(Float.valueOf(textSalario.getText()));
					funcionario.setTelefone(textTelefone.getText());
					funcionario.insert();
					this.limparCampos();
				} catch (ParseException e) {
						JOptionPane.showMessageDialog(DialogoFuncionario.this, "O formato padrão de data utilizado é: \n\ndd/mm/aaaa");
				}
			} else {
				JOptionPane.showMessageDialog(DialogoFuncionario.this, "Esse Funcionario já se encontra na Base de Dados!");
			}			
		}
		

		private void editar(){
			if (!existeFuncionario()) {
				try {
					SimpleDateFormat converterDate = new SimpleDateFormat("dd/MM/yyyy");
					funcionario.setCpf(textCpf.getText());
					funcionario.setDataNascimento(converterDate.parse(textDtNascimento.getText().trim()));
					Endereco end = new Endereco();
					end.setBairro(textBairro.getText());
					end.setCep(textCep.getText());
					end.setComplemento(textComplemento.getText());
					if(!(textNumero.getText().equals(""))){
						end.setNumero(Integer.valueOf(textNumero.getText()));
					}
					end.setRua(textRua.getText());
					end.setCidade((Cidade)comboCidade.getSelectedItem());
					Cargo cargo = new Cargo();
					cargo = ((Cargo)comboCargo.getSelectedItem());
					funcionario.setCargo(cargo);
					funcionario.setEndereco(end);
					funcionario.setNome(textNome.getText());
					funcionario.setRg(textRg.getText());
					funcionario.setSalario(Float.valueOf(textSalario.getText()));
					funcionario.setTelefone(textTelefone.getText());
					funcionario.edit();
					this.limparCampos();
				} catch (ParseException e) {
						JOptionPane.showMessageDialog(DialogoFuncionario.this, "O formato padrão de data utilizado é: \n\ndd/mm/aaaa");
				}
			} else {
				JOptionPane.showMessageDialog(DialogoFuncionario.this, "Esse Funcionario não se encontra na Base de Dados!");
			}
		}

		private void showPainel(PainelPadrao painelPadrao, String titulo) {
			DialogoCRUD dialogoCRUD = new DialogoCRUD(TelaPrincipal.instancia, titulo, true);
			dialogoCRUD.setPainel(painelPadrao);
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == botaoCidade) {				
				showPainel(new PainelCidade(), "Cadastros de Cidades");	
				this.carregarComboCidade(cidade.load());
			} else if (e.getSource() == botaoCargo) {
				showPainel(new PainelCargo(), "Cadastros de Cargos");	
				this.carregarComboCargo(cargo.load());				
			} else if (e.getSource() == getBotaoCancelar()) {
				dispose();
			} else if (e.getSource() == getBotaoConfirmar()) {
				if (textNome.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoFuncionario.this,"O campo nome é obrigatório!");
				}else if (textTelefone.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoFuncionario.this,"O campo telefone é obrigatório!");
				}else if (textCpf.getText().equals("___.___.___-__")) {
					JOptionPane.showMessageDialog(DialogoFuncionario.this,"O campo cpf é obrigatório!");
				}else if ( ((funcionario.getCodigo() == 0) && ( comboCargo.getSelectedIndex() == -1)) ||
						   ((funcionario.getCodigo() != 0) && ( comboCargo.getSelectedItem() == null)) ){
					JOptionPane.showMessageDialog(DialogoFuncionario.this,"O campo Cargo é obrigatório!");
				}else if (textDtNascimento.getText().equals("__/__/____")) {
					JOptionPane.showMessageDialog(DialogoFuncionario.this,"O campo Data de Nascimento é obrigatório!");
				}else if (textSalario.getText().equals("")) {
					JOptionPane.showMessageDialog(DialogoFuncionario.this,"O campo Salário é obrigatório!");
				}else if ( ((funcionario.getCodigo() == 0) && ( comboCidade.getSelectedIndex() == -1)) ||
						   ((funcionario.getCodigo() != 0) && ( comboCidade.getSelectedItem() == null)) ){
					JOptionPane.showMessageDialog(DialogoFuncionario.this,"O campo Cidade é obrigatório!");					
				} else {
					if (funcionario.getCodigo() == 0) {
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

	/*----------------------------------------------------------
	 * FIM DE CLASSE LIMITROFE
	 *----------------------------------------------------------*/

}

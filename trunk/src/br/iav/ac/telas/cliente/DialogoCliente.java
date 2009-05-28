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

	private JLabel labelNome;
	private JLabel labelTelefone;
	private JTextField textTelefone;
	private JTextField textNome;
	private JLabel labelCpf;
	private JTextField textCpf;
	private JLabel labelRg;
	private JTextField textRg;
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
		//25 de espaçamento
		try {
            {
            	labelNome = new JLabel();
            	getPanelPrincipal().add(labelNome);
            	labelNome.setText("Nome:");
            	labelNome.setBounds(12, 37, 80, 20);
	        }
	        {
	        	textNome = new JTextField(this.cliente.getNome().trim());
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
	        	textTelefone = new JTextField(this.cliente.getTelefone().trim());
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
	        	textCpf = new JTextField(this.cliente.getCpf().trim());
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
	        	textRg = new JTextField(this.cliente.getRg().trim());
	        	getPanelPrincipal().add(textRg);
	        	textRg.setBounds(90, 110, 246, 21);
	        }
            {
            	labelProfissao = new JLabel();
            	getPanelPrincipal().add(labelProfissao);
            	labelProfissao.setText("Profissão:");
            	labelProfissao.setBounds(12, 137, 80, 20);
	        }
	        {
	        	textProfissao = new JTextField(this.cliente.getProfissao().trim());
	        	getPanelPrincipal().add(textProfissao);
	        	textProfissao.setBounds(90, 135, 246, 21);
	        }
            {
            	labelRua = new JLabel();
            	getPanelPrincipal().add(labelRua);
            	labelRua.setText("Rua:");
            	labelRua.setBounds(12, 162, 80, 20);
	        }
	        {
	        	textRua = new JTextField(this.cliente.getEndereco().getRua().trim());
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
	        	textNumero = new JTextField(String.valueOf(this.cliente.getEndereco().getNumero()));
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
	        	textBairro = new JTextField(this.cliente.getEndereco().getBairro().trim());
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
	        	textCep = new JTextField(this.cliente.getEndereco().getCep().trim());
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
	        	textComplemento = new JTextField(this.cliente.getEndereco().getComplemento().trim());
	        	getPanelPrincipal().add(textComplemento);
	        	textComplemento.setBounds(90, 260, 246, 21);
	        }
            {
            	labelCidade = new JLabel();
            	getPanelPrincipal().add(labelCidade);
            	labelCidade.setText("Cidade:");
            	labelCidade.setBounds(12, 287, 80, 20);
	        }
	        {
	        	comboCidade = new JComboBox();
	        	getPanelPrincipal().add(comboCidade);
	        	comboCidade.setSelectedItem(cliente.getEndereco().getCidade().getNome().trim());
	        	comboCidade.setBounds(90, 285, 246, 21);
	        }
	        {
				if (this.cliente.getCodigo() != 0) {
					getTextCodigo().setText(String.valueOf(this.cliente.getCodigo()));
				}
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		inicializarHandlers();
		this.setSize(350, 380);
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
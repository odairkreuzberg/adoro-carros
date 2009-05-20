package br.iav.ac.telas.cliente;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class PanelClienteConsulta extends javax.swing.JPanel {
	private JPanel panelFiltro;
	private JPanel panelEndereco;
	private JLabel lblNome;
	private JTextField txtNome;
	private JLabel lblRg;
	private JTextField txtRg;
	private JLabel lblProfissao;
	private JTextField txtDataNasc;
	private JLabel lblDataNasc;
	private JTextField txtCep;
	private JTextField txtBairro;
	private JComboBox cbCidade;
	private JTextField txtNumero;
	private JTextField txtRua;
	private JLabel lblCep;
	private JLabel lblCidade;
	private JLabel lblBairro;
	private JLabel lblNumero;
	private JLabel lblRua;
	private JLabel lblTituloEndereco;
	private JTextField txtTelefone;
	private JLabel lblTelefone;
	private JTextField txtCpf;
	private JLabel lblCpf;
	private JTextField txtNumeroSocio;
	private JLabel lblNumeroSocio;
	private JTextField txtProfissao;
	private JLabel lblTitulo;

	public PanelClienteConsulta() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			this.setSize(549, 553);
			this.setLayout(null);
			{
				lblTitulo = new JLabel();
				this.add(lblTitulo);
				lblTitulo.setText("Consultar Clientes");
				lblTitulo.setBounds(12, 12, 129, 17);
				lblTitulo.setFont(new java.awt.Font("Tahoma",1,14));
			}
			{
				panelFiltro = new JPanel();
				this.add(panelFiltro);
				panelFiltro.setBounds(12, 35, 525, 284);
				panelFiltro.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				panelFiltro.setLayout(null);
				{
					lblNome = new JLabel();
					panelFiltro.add(lblNome);
					lblNome.setText("Nome: ");
					lblNome.setBounds(13, 40, 34, 14);
				}
				{
					txtNome = new JTextField();
					panelFiltro.add(txtNome);
					txtNome.setBounds(122, 37, 190, 21);
				}
				{
					txtProfissao = new JTextField();
					panelFiltro.add(txtProfissao);
					txtProfissao.setBounds(340, 91, 127, 21);
				}
				{
					lblProfissao = new JLabel();
					panelFiltro.add(lblProfissao);
					lblProfissao.setText("Profissão: ");
					lblProfissao.setBounds(282, 94, 53, 14);
				}
				{
					txtRg = new JTextField();
					panelFiltro.add(txtRg);
					txtRg.setBounds(122, 64, 127, 21);
				}
				{
					lblRg = new JLabel();
					panelFiltro.add(lblRg);
					lblRg.setText("RG: ");
					lblRg.setBounds(13, 67, 34, 14);
				}
				{
					lblNumeroSocio = new JLabel();
					panelFiltro.add(lblNumeroSocio);
					lblNumeroSocio.setText("Número de Sócio:");
					lblNumeroSocio.setBounds(13, 13, 84, 14);
				}
				{
					txtNumeroSocio = new JTextField();
					panelFiltro.add(txtNumeroSocio);
					txtNumeroSocio.setBounds(122, 10, 152, 21);
				}
				{
					lblCpf = new JLabel();
					panelFiltro.add(lblCpf);
					lblCpf.setText("CPF:");
					lblCpf.setBounds(282, 67, 23, 14);
				}
				{
					txtCpf = new JTextField();
					panelFiltro.add(txtCpf);
					txtCpf.setBounds(340, 64, 127, 21);
				}
				{
					lblTelefone = new JLabel();
					panelFiltro.add(lblTelefone);
					lblTelefone.setText("Telefone:");
					lblTelefone.setBounds(13, 94, 46, 14);
				}
				{
					txtTelefone = new JTextField();
					panelFiltro.add(txtTelefone);
					txtTelefone.setBounds(122, 91, 127, 21);
				}
				{
					panelEndereco = new JPanel();
					panelFiltro.add(panelEndereco);
					panelEndereco.setLayout(null);
					panelEndereco.setBounds(13, 158, 499, 113);
					panelEndereco.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
					{
						lblTituloEndereco = new JLabel();
						panelEndereco.add(lblTituloEndereco);
						lblTituloEndereco.setText("Endereço");
						lblTituloEndereco.setBounds(7, 7, 60, 14);
						lblTituloEndereco.setFont(new java.awt.Font("Tahoma",1,12));
					}
					{
						lblRua = new JLabel();
						panelEndereco.add(lblRua);
						lblRua.setText("Rua:");
						lblRua.setBounds(13, 33, 23, 14);
					}
					{
						lblNumero = new JLabel();
						panelEndereco.add(lblNumero);
						lblNumero.setText("Número:");
						lblNumero.setBounds(238, 33, 47, 14);
					}
					{
						lblBairro = new JLabel();
						panelEndereco.add(lblBairro);
						lblBairro.setText("Bairro:");
						lblBairro.setBounds(13, 59, 32, 14);
					}
					{
						lblCidade = new JLabel();
						panelEndereco.add(lblCidade);
						lblCidade.setText("Cidade:");
						lblCidade.setBounds(238, 59, 37, 14);
					}
					{
						lblCep = new JLabel();
						panelEndereco.add(lblCep);
						lblCep.setText("CEP:");
						lblCep.setBounds(13, 85, 23, 14);
					}
					{
						txtRua = new JTextField();
						panelEndereco.add(txtRua);
						txtRua.setBounds(54, 30, 166, 21);
					}
					{
						txtNumero = new JTextField();
						panelEndereco.add(txtNumero);
						txtNumero.setBounds(285, 30, 64, 21);
					}
					{
						ComboBoxModel cbCidadeModel = 
							new DefaultComboBoxModel(
									new String[] { "Foz do Iguaçu", "Cascavel" });
						cbCidade = new JComboBox();
						panelEndereco.add(cbCidade);
						cbCidade.setModel(cbCidadeModel);
						cbCidade.setBounds(285, 56, 139, 21);
					}
					{
						txtBairro = new JTextField();
						panelEndereco.add(txtBairro);
						txtBairro.setBounds(54, 56, 166, 21);
					}
					{
						txtCep = new JTextField();
						panelEndereco.add(txtCep);
						txtCep.setBounds(54, 82, 166, 21);
					}
				}
				{
					lblDataNasc = new JLabel();
					panelFiltro.add(lblDataNasc);
					lblDataNasc.setText("Data de Nascimento:");
					lblDataNasc.setBounds(13, 120, 100, 14);
				}
				{
					txtDataNasc = new JTextField();
					panelFiltro.add(txtDataNasc);
					txtDataNasc.setBounds(122, 117, 127, 21);
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao iniciar tela 'Consultar Clientes'.");
		}
	}

}

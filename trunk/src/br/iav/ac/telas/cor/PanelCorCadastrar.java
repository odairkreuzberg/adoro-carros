package br.iav.ac.telas.cor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
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
public class PanelCorCadastrar extends javax.swing.JPanel {

	private JLabel lblTitulo;
	private JPanel panelPrincipal;
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JButton btnVoltar;
	private JLabel lblNome;
	private JTextField txtNome;
	
	//JPanel Tela Principal
	private JPanel pTela;
	
	public PanelCorCadastrar(JPanel p) {
		super();
		initGUI();
		pTela = p;
	}
	
	private void initGUI() {
		try {
			this.setSize(549, 553);
			this.setLayout(null);
			{
				lblTitulo = new JLabel();
				this.add(lblTitulo);
				lblTitulo.setText("Cadastrar Cor");
				lblTitulo.setBounds(12, 12, 212, 17);
				lblTitulo.setFont(new java.awt.Font("Tahoma",1,14));
			}
			{
				panelPrincipal = new JPanel();
				this.add(panelPrincipal);
				panelPrincipal.setBounds(12, 35, 525, 67);
				panelPrincipal.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				panelPrincipal.setLayout(null);
				{
					lblCodigo = new JLabel();
					panelPrincipal.add(lblCodigo);
					lblCodigo.setText("Código: ");
					lblCodigo.setBounds(13, 13, 42, 14);
				}
				{
					txtCodigo = new JTextField();
					panelPrincipal.add(txtCodigo);
					txtCodigo.setBounds(88, 10, 51, 21);
					txtCodigo.setEnabled(false);
				}
				{
					lblNome = new JLabel();
					panelPrincipal.add(lblNome);
					lblNome.setText("Nome: ");
					lblNome.setBounds(13, 39, 34, 14);
				}
				{
					txtNome = new JTextField();
					panelPrincipal.add(txtNome);
					txtNome.setBounds(88, 36, 190, 21);
				}
			}
			{
				btnVoltar = new JButton();
				this.add(btnVoltar);
				btnVoltar.setText("Voltar");
				btnVoltar.setBounds(450, 518, 88, 24);
				btnVoltar.addMouseListener(new Mouse());
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao iniciar tela 'Cadastrar Cor'.");
		}
	}
	
	class Mouse extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == btnVoltar) {
				PanelCorCadastrar.this.removeAll();
				PanelCorCadastrar.this.add(new PanelCorPrincipal(pTela));
				PanelCorCadastrar.this.repaint();
			}
		}
	}

}

package br.iav.ac.telas.cliente;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

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
public class PanelClientePrincipal extends javax.swing.JPanel {

	private JLabel lblTitulo;
	private JTable tbPrincipal;
	private JScrollPane jspTabela;
	private JButton btnExcluir;
	private JButton btnEditar;
	private JButton btnCadastrar;
	
	//JPanel Tela Principal
	private JPanel pTela;

	public PanelClientePrincipal(JPanel p) {
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
				lblTitulo.setText("Clientes");
				lblTitulo.setBounds(12, 12, 62, 17);
				lblTitulo.setFont(new java.awt.Font("Tahoma",1,14));
			}
			{
				btnCadastrar = new JButton();
				this.add(btnCadastrar);
				btnCadastrar.setText("Cadastrar");
				btnCadastrar.setBounds(253, 518, 89, 24);
				btnCadastrar.addMouseListener(new Mouse());
			}
			{
				btnEditar = new JButton();
				this.add(btnEditar);
				btnEditar.setText("Editar");
				btnEditar.setEnabled(false);
				btnEditar.setBounds(352, 518, 89, 24);
				btnEditar.addMouseListener(new Mouse());
			}
			{
				btnExcluir = new JButton();
				this.add(btnExcluir);
				btnExcluir.setText("Excluir");
				btnExcluir.setEnabled(false);
				btnExcluir.setBounds(451, 518, 88, 24);
				btnExcluir.addMouseListener(new Mouse());
			}
			{
				jspTabela = new JScrollPane();
				this.add(jspTabela);
				jspTabela.setBounds(12, 42, 525, 464);
				{
					Object[][] tbData = new Object[][] { { "Furlan", "35243395" }, { "Fernando", "35235337" } };
					String[] tbTitulo = new String[] { "Nome", "Telefone" };
					tbPrincipal = new JTable(tbData, tbTitulo);
					jspTabela.setViewportView(tbPrincipal);
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao iniciar tela 'Clientes'.");
		}
	}
	
	class Mouse extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == btnCadastrar) {
				PanelClientePrincipal.this.pTela.removeAll();
				PanelClientePrincipal.this.pTela.add(new PanelClienteCadastrar(pTela));
				PanelClientePrincipal.this.pTela.repaint();
			} else if (e.getSource() == btnEditar) {
				
			} else if (e.getSource() == btnExcluir) {
			
			}
		}
	}

}

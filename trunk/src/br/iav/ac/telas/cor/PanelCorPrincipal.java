package br.iav.ac.telas.cor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;


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
public class PanelCorPrincipal extends javax.swing.JPanel {

	private JLabel lblTitulo;
	private JButton btnCadastrar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JTable tbPrincipal;

	public PanelCorPrincipal() {
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
				lblTitulo.setText("Cores");
				lblTitulo.setBounds(12, 12, 62, 17);
				lblTitulo.setFont(new java.awt.Font("Tahoma",1,14));
			}
			{
				String[][] tbData = new String[][] { { "Preto" }, { "Branco" } };
				String[] tbTitulo = new String[] { "Cor" };
				tbPrincipal = new JTable();
				this.add(tbPrincipal);
				tbPrincipal.setModel(new DefaultTableModel(tbData, tbTitulo));
				tbPrincipal.setBounds(12, 39, 525, 468);
				tbPrincipal.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
			}
			{
				btnCadastrar = new JButton();
				this.add(btnCadastrar);
				btnCadastrar.setText("Cadastrar");
				btnCadastrar.setBounds(253, 518, 89, 24);
			}
			{
				btnEditar = new JButton();
				this.add(btnEditar);
				btnEditar.setText("Editar");
				btnEditar.setBounds(352, 518, 89, 24);
			}
			{
				btnExcluir = new JButton();
				this.add(btnExcluir);
				btnExcluir.setText("Excluir");
				btnExcluir.setBounds(451, 518, 88, 24);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao iniciar tela 'Cores'.");
		}
	}

	class Mouse extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == btnCadastrar) {
				
			} else if (e.getSource() == btnEditar) {
				
			} else if (e.getSource() == btnExcluir) {
			
			}
		}
	}
	
}

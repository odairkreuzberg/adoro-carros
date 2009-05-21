package br.iav.ac.telas.cliente;

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
public class PanelClientePrincipal extends javax.swing.JPanel {

	private JLabel lblTitulo;
	private JTable tbPrincipal;

	public PanelClientePrincipal() {
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
				lblTitulo.setText("Clientes");
				lblTitulo.setBounds(12, 12, 62, 17);
				lblTitulo.setFont(new java.awt.Font("Tahoma",1,14));
			}
			{
				String[][] tbData = new String[][] { { "Furlan", "35243395" }, { "Fernando", "35235337" } };
				String[] tbTitulo = new String[] { "Nome", "Telefone" };
				tbPrincipal = new JTable();
				this.add(tbPrincipal);
				tbPrincipal.setModel(new DefaultTableModel(tbData, tbTitulo));
				tbPrincipal.setBounds(12, 39, 525, 452);
				tbPrincipal.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao iniciar tela 'Clientes'.");
		}
	}

}

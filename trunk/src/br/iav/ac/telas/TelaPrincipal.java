package br.iav.ac.telas;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import br.iav.ac.telas.cliente.*;
import br.iav.ac.telas.cor.PanelCorPrincipal;

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
public class TelaPrincipal extends javax.swing.JFrame {
	private JPanel panelOpções;
	private JPanel panelPrincipal;
	private PanelClientePrincipal panelClientePrincipal;
	private PanelCorPrincipal panelCorPrincipal;
	private JTree jtOpcoes;
	private JScrollPane jspOpcoes;

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				TelaPrincipal inst = new TelaPrincipal();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public TelaPrincipal() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			getContentPane().setLayout(null);
			this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			this.setResizable(false);
			this.setTitle("Adoro Carros");
			this.setSize(800, 600);
			{
				panelOpções = new JPanel();
				getContentPane().add(panelOpções);
				panelOpções.setBounds(10, 11, 215, 553);
				panelOpções.setLayout(null);
				panelOpções.setBackground(new java.awt.Color(0,64,128));
				{
					jspOpcoes = new JScrollPane();
					panelOpções.add(jspOpcoes);
					jspOpcoes.setBounds(10, 11, 195, 184);
					{
						DefaultMutableTreeNode nodoPrincipal = new DefaultMutableTreeNode("Adoro Carros");
						DefaultMutableTreeNode nodoClientes = new DefaultMutableTreeNode("Clientes");
						DefaultMutableTreeNode nodoFuncionarios = new DefaultMutableTreeNode("Funcionários");
						DefaultMutableTreeNode nodoCores = new DefaultMutableTreeNode("Cores");
						nodoPrincipal.add(nodoClientes);
						nodoPrincipal.add(nodoFuncionarios);
						nodoPrincipal.add(nodoCores);
						jtOpcoes = new JTree(nodoPrincipal);
						jtOpcoes.addTreeSelectionListener(new TreeSelectionHandler());
						jspOpcoes.setViewportView(jtOpcoes);
					}
				}
			}
			{
				panelPrincipal = new JPanel();
				getContentPane().add(panelPrincipal);
				panelPrincipal.setBounds(235, 11, 549, 553);
				panelPrincipal.setLayout(null);
				panelPrincipal.setSize(549, 553);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	class TreeSelectionHandler implements TreeSelectionListener {

		@Override
		public void valueChanged(TreeSelectionEvent e) {
			String path = e.getPath().toString().trim();
			if (path.equals("[Adoro Carros]")) {
				JOptionPane.showMessageDialog(TelaPrincipal.this, "Tela Principal");
			} else if (path.equals("[Adoro Carros, Clientes]")) {
				panelPrincipal.removeAll();
				if (panelClientePrincipal == null) {
					panelClientePrincipal = new PanelClientePrincipal();
				} else {
					
				}
				panelPrincipal.add(panelClientePrincipal);
				panelPrincipal.validate();
				panelPrincipal.repaint();
			} else if (path.equals("[Adoro Carros, Cores]")) {
				panelPrincipal.removeAll();
				if (panelCorPrincipal == null) {
					panelCorPrincipal = new PanelCorPrincipal();
				} else {
					
				}
				panelPrincipal.add(panelCorPrincipal);
				panelPrincipal.validate();
				panelPrincipal.repaint();
			} else if (path.equals("[Adoro Carros, Funcionários]")) {
				JOptionPane.showMessageDialog(TelaPrincipal.this, "Tela de Funcionários");
			} else {
				JOptionPane.showMessageDialog(TelaPrincipal.this, path);
			}
		}
	}

}

package br.iav.ac.telas;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import br.iav.ac.telas.cor.PainelCor;
import br.iav.ac.telas.padrao.PainelPadrao;

public class TelaPrincipal extends javax.swing.JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel panelOpcoes;
	private JPanel panelPrincipal;
	private JTree jtOpcoes;
	private JScrollPane jspOpcoes;
	private PainelCor corGrid;
	public static TelaPrincipal instancia;

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
				panelOpcoes = new JPanel();
				getContentPane().add(panelOpcoes);
				panelOpcoes.setBounds(10, 11, 215, 553);
				panelOpcoes.setLayout(null);
				panelOpcoes.setBackground(new java.awt.Color(0,64,128));
				{
					jspOpcoes = new JScrollPane();
					panelOpcoes.add(jspOpcoes);
					jspOpcoes.setBounds(10, 11, 195, 184);
					{
						DefaultMutableTreeNode nodoPrincipal = new DefaultMutableTreeNode("Adoro Carros");
						DefaultMutableTreeNode nodoClientes = new DefaultMutableTreeNode("Clientes");
						DefaultMutableTreeNode nodoFuncionarios = new DefaultMutableTreeNode("Funcionários");
						DefaultMutableTreeNode nodoCores = new DefaultMutableTreeNode("Cores");
						DefaultMutableTreeNode nodoMarcas = new DefaultMutableTreeNode("Marcas");
						DefaultMutableTreeNode nodoModelos = new DefaultMutableTreeNode("Modelos");
						nodoPrincipal.add(nodoClientes);
						nodoPrincipal.add(nodoFuncionarios);
						nodoPrincipal.add(nodoCores);
						nodoPrincipal.add(nodoMarcas);
						nodoPrincipal.add(nodoModelos);
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

		private PainelCor getCorGrid() {
			if (corGrid == null){
				corGrid = new PainelCor();
			}
			return corGrid;
		}

		private void showPainelGrid(PainelPadrao painelGrid) {
			panelPrincipal.removeAll();
			panelPrincipal.repaint();
			panelPrincipal.add(painelGrid);
			panelPrincipal.validate();

		}

		@Override
		public void valueChanged(TreeSelectionEvent e) {
			String path = e.getPath().toString().trim();
			if (path.equals("[Adoro Carros, Clientes]")) {

			} else if (path.equals("[Adoro Carros, Cores]")) {				
				showPainelGrid(getCorGrid());
			} else if (path.equals("[Adoro Carros, Marcas]")) {

			} else if (path.equals("[Adoro Carros, Modelos]")) {
				
			} else if (path.equals("[Adoro Carros, Funcionários]")) {

			} else {
				JOptionPane.showMessageDialog(TelaPrincipal.this, path);
			}
		}
	}

}

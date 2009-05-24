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

import br.iav.ac.telas.cliente.PanelClientePrincipal;
import br.iav.ac.telas.cor.PanelCorPrincipal;
import br.iav.ac.telas.core.PainelGrid;
import br.iav.ac.telas.marca.MarcaGrid;
import br.iav.ac.telas.modelo.ModeloGrid;

public class TelaPrincipal extends javax.swing.JFrame {
	
	private JPanel panelOpcoes;
	private JPanel panelPrincipal;
	
	private PanelClientePrincipal panelClientePrincipal;
	private PanelCorPrincipal panelCorPrincipal;
	private JTree jtOpcoes;
	private JScrollPane jspOpcoes;
	private MarcaGrid marcaGrid;
	private ModeloGrid modeloGrid;
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

		private MarcaGrid getgGridMarca() {
			if (marcaGrid == null){
				marcaGrid = new MarcaGrid();
			}
			return marcaGrid;
		}

		private ModeloGrid getgGridModelo() {
			if (modeloGrid == null){
				modeloGrid = new ModeloGrid();
			}
			return modeloGrid;
		}

		private void showPainelGrid(PainelGrid painelGrid) {
			panelPrincipal.removeAll();
			panelPrincipal.repaint();
			panelPrincipal.add(painelGrid);
			panelPrincipal.validate();

		}

		@Override
		public void valueChanged(TreeSelectionEvent e) {
			String path = e.getPath().toString().trim();
			if (path.equals("[Adoro Carros, Clientes]")) {
				panelPrincipal.removeAll();
				panelPrincipal.add(new PanelClientePrincipal(panelPrincipal));
				panelPrincipal.repaint();
			} else if (path.equals("[Adoro Carros, Cores]")) {
				//panelPrincipal.removeAll();
				panelPrincipal.add(new PanelCorPrincipal(panelPrincipal));
				panelPrincipal.repaint();
			} else if (path.equals("[Adoro Carros, Marcas]")) {
				
				showPainelGrid(getgGridMarca());
				
			} else if (path.equals("[Adoro Carros, Modelos]")) {
				
				showPainelGrid(getgGridModelo());
				
			}else if (path.equals("[Adoro Carros, Funcionários]")) {
				JOptionPane.showMessageDialog(TelaPrincipal.this, "Tela de Funcionários");
			} else {
				JOptionPane.showMessageDialog(TelaPrincipal.this, path);
			}
		}
	}

}

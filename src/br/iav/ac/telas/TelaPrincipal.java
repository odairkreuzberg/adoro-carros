package br.iav.ac.telas;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import br.iav.ac.telas.cargo.PainelCargo;
import br.iav.ac.telas.carro.PainelCarro;
import br.iav.ac.telas.cidade.PainelCidade;
import br.iav.ac.telas.cliente.PainelCliente;
import br.iav.ac.telas.cor.PainelCor;
import br.iav.ac.telas.estoque.PainelEstoque;
import br.iav.ac.telas.fornecedor.PainelFornecedor;
import br.iav.ac.telas.funcionario.PainelFuncionario;
import br.iav.ac.telas.marca.PainelMarca;
import br.iav.ac.telas.modelo.PainelModelo;
import br.iav.ac.telas.padrao.RelPadrao;
import br.iav.ac.telas.peca.PainelPeca;
import br.iav.ac.telas.status.PainelStatus;

public class TelaPrincipal extends javax.swing.JFrame {
	
	private JPanel panelOpcoes;
	private JPanel panelPrincipal;
	private JTree jtOpcoes;
	private JScrollPane jspOpcoes;
	private PainelApresentacao painelApresentacao;
	private PainelCor painelCor;
	private PainelMarca painelMarca;
	private PainelModelo painelModelo;
	private PainelCidade painelCidade;
	private PainelCliente painelCliente;
	private PainelCargo painelCargo;
	private PainelFuncionario painelFuncionario;
	private PainelStatus painelStatus;
	private PainelCarro painelCarro;
	private PainelFornecedor painelFornecedor;
	private PainelPeca painelPeca;
	private PainelEstoque painelEstoque;
	private RelPadrao painelRelCarro;
	public static TelaPrincipal instancia;
	
	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

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
		instancia = this;
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
						DefaultMutableTreeNode nodoPrincipal = new DefaultMutableTreeNode("SMA");
						DefaultMutableTreeNode nodoGeral = new DefaultMutableTreeNode("Geral");
						DefaultMutableTreeNode nodoServicos = new DefaultMutableTreeNode("Serviços");
						DefaultMutableTreeNode nodoRelatorios = new DefaultMutableTreeNode("Relatórios");
						DefaultMutableTreeNode nodoCargo = new DefaultMutableTreeNode("Cargo");
						DefaultMutableTreeNode nodoCarro = new DefaultMutableTreeNode("Carro");
						DefaultMutableTreeNode nodoCidade = new DefaultMutableTreeNode("Cidade");
						DefaultMutableTreeNode nodoCliente = new DefaultMutableTreeNode("Cliente");
						DefaultMutableTreeNode nodoCor = new DefaultMutableTreeNode("Cor");
						DefaultMutableTreeNode nodoFornecedor = new DefaultMutableTreeNode("Fornecedor");
						DefaultMutableTreeNode nodoFuncionario = new DefaultMutableTreeNode("Funcionário");
						DefaultMutableTreeNode nodoPeca = new DefaultMutableTreeNode("Peça");
						DefaultMutableTreeNode nodoMarca = new DefaultMutableTreeNode("Marca");
						DefaultMutableTreeNode nodoModelo = new DefaultMutableTreeNode("Modelo");
						DefaultMutableTreeNode nodoStatus = new DefaultMutableTreeNode("Status");
						DefaultMutableTreeNode nodoEstoque = new DefaultMutableTreeNode("Estoque");
						DefaultMutableTreeNode nodoRelCarro = new DefaultMutableTreeNode("Carro");
						nodoPrincipal.add(nodoGeral);
						nodoPrincipal.add(nodoServicos);
						nodoPrincipal.add(nodoRelatorios);
						nodoGeral.add(nodoCargo);
						nodoGeral.add(nodoCarro);
						nodoGeral.add(nodoCidade);
						nodoGeral.add(nodoCliente);
						nodoGeral.add(nodoCor);
						nodoGeral.add(nodoFornecedor);
						nodoGeral.add(nodoFuncionario);
						nodoGeral.add(nodoPeca);
						nodoGeral.add(nodoMarca);
						nodoGeral.add(nodoModelo);
						nodoGeral.add(nodoStatus);
						nodoRelatorios.add(nodoRelCarro);
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
				painelApresentacao = new PainelApresentacao();
				panelPrincipal.add(painelApresentacao);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	class TreeSelectionHandler implements TreeSelectionListener {

		private PainelApresentacao getPainelApresentacao() {
			if (painelApresentacao == null){
				painelApresentacao = new PainelApresentacao();
			}
			return painelApresentacao;
		}
		
		private RelPadrao getPainelRelCarro() {
			if (painelRelCarro == null){
				painelRelCarro = new RelPadrao("Carros");
			}
			return painelRelCarro;
		}
		
		private PainelCidade getPainelCidade() {
			if (painelCidade == null){
				painelCidade = new PainelCidade();
			}
			return painelCidade;
		}
		
		private PainelCliente getPainelCliente() {
			if (painelCliente == null){
				painelCliente = new PainelCliente();
			}
			return painelCliente;
		}
		
		private PainelCor getPainelCor() {
			if (painelCor == null){
				painelCor = new PainelCor();
			}
			return painelCor;
		}
		
		private PainelMarca getPainelMarca() {
			if (painelMarca == null){
				painelMarca = new PainelMarca();
			}
			return painelMarca;
		}
		
		private PainelCargo getPainelCargo() {
			if (painelCargo == null){
				painelCargo = new PainelCargo();
			}
			return painelCargo;
		}
		
		private PainelModelo getPainelModelo() {
			if (painelModelo == null){
				painelModelo = new PainelModelo();
			}
			return painelModelo;
		}

		private PainelFuncionario getPainelFuncionario() {
			if (painelFuncionario == null){
				painelFuncionario = new PainelFuncionario();
			}
			return painelFuncionario;
		}
		
		private PainelStatus getPainelStatus() {
			if (painelStatus == null){
				painelStatus = new PainelStatus();
			}
			return painelStatus;
		}
		
		private PainelCarro getPainelCarro() {
			if (painelCarro == null){
				painelCarro = new PainelCarro();
			}
			return painelCarro;
		}
		
		private PainelFornecedor getPainelFornecedor() {
			if (painelFornecedor == null){
				painelFornecedor = new PainelFornecedor();
			}
			return painelFornecedor;
		}
		
		private PainelPeca getPainelPeca() {
			if (painelPeca == null){
				painelPeca = new PainelPeca();
			}
			return painelPeca;
		}
		
		private PainelEstoque getPainelEstoque() {
			if (painelEstoque == null){
				painelEstoque = new PainelEstoque();
			}
			return painelEstoque;
		}
		
		private void showPainel(JPanel painelPadrao) {
			panelPrincipal.removeAll();
			panelPrincipal.repaint();
			panelPrincipal.add(painelPadrao);
			panelPrincipal.validate();
		}

		@Override
		public void valueChanged(TreeSelectionEvent e) {
			String path = e.getPath().toString().trim();
			if (path.equals("[SMA, Geral, Cargo]")) {		
				showPainel(getPainelCargo());
			} else if (path.equals("[SMA, Geral, Cidade]")) {
				showPainel(getPainelCidade());
			} else if (path.equals("[SMA, Geral, Cliente]")) {
				showPainel(getPainelCliente());
			} else if (path.equals("[SMA, Geral, Cor]")) {				
				showPainel(getPainelCor());
			} else if (path.equals("[SMA, Geral, Funcionário]")) {
				showPainel(getPainelFuncionario());
			} else if (path.equals("[SMA, Geral, Marca]")) {			
				showPainel(getPainelMarca());
			} else if (path.equals("[SMA, Geral, Modelo]")) {		
				showPainel(getPainelModelo());
			} else if (path.equals("[SMA, Geral, Status]")) {
				showPainel(getPainelStatus());
			} else if (path.equals("[SMA, Geral, Carro]")) {
				showPainel(getPainelCarro());
			} else if (path.equals("[SMA, Geral, Fornecedor]")) {
				showPainel(getPainelFornecedor());
			} else if (path.equals("[SMA, Geral, Estoque]")) {
				showPainel(getPainelEstoque());
			} else if (path.equals("[SMA, Geral, Peça]")) {
				showPainel(getPainelPeca());
			} else if (path.equals("[SMA, Relatórios, Carro]")) {
				showPainel(getPainelRelCarro());
			} else {
				showPainel(getPainelApresentacao());
			}
		}
	}

}

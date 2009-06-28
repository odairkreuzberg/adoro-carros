package br.iav.ac.telas;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import br.iav.ac.telas.atividade.PainelAtividade;
import br.iav.ac.telas.cargo.PainelCargo;
import br.iav.ac.telas.carro.PainelCarro;
import br.iav.ac.telas.cidade.PainelCidade;
import br.iav.ac.telas.cliente.PainelCliente;
import br.iav.ac.telas.cor.PainelCor;
import br.iav.ac.telas.estoque.DialogoFornecedorPeca;
import br.iav.ac.telas.estoque.PainelEstoque;
import br.iav.ac.telas.fornecedor.PainelFornecedor;
import br.iav.ac.telas.funcionario.PainelFuncionario;
import br.iav.ac.telas.marca.PainelMarca;
import br.iav.ac.telas.modelo.PainelModelo;
import br.iav.ac.telas.peca.PainelPeca;
import br.iav.ac.telas.relatorio.RelCarro;
import br.iav.ac.telas.relatorio.RelCliente;
import br.iav.ac.telas.relatorio.RelAtividade;
import br.iav.ac.telas.relatorio.RelServicoAtrasado;
import br.iav.ac.telas.servico.DialogoOrcamento;
import br.iav.ac.telas.servico.PainelOrcamento;
import br.iav.ac.telas.servico.PainelServico;

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
	private PainelCarro painelCarro;
	private PainelFornecedor painelFornecedor;
	private PainelPeca painelPeca;
	private PainelEstoque painelEstoque;
	private PainelAtividade painelAtividade;
	private RelCarro painelRelCarro;
	private RelCliente painelRelCliente;
	private RelAtividade painelRelAtividade;
	private PainelServico painelServico;
	private PainelOrcamento painelOrcamento;
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
					jspOpcoes.setBounds(10, 11, 195, 530);
					{
						DefaultMutableTreeNode nodoPrincipal = new DefaultMutableTreeNode("SMA");
						DefaultMutableTreeNode nodoCadastro = new DefaultMutableTreeNode("Cadastro");
						DefaultMutableTreeNode nodoMovimento = new DefaultMutableTreeNode("Movimento");
						DefaultMutableTreeNode nodoAlmox = new DefaultMutableTreeNode("Almoxarifado");
						DefaultMutableTreeNode nodoServico = new DefaultMutableTreeNode("Serviços");
						DefaultMutableTreeNode nodoRelatorios = new DefaultMutableTreeNode("Relatórios");
						DefaultMutableTreeNode nodoAtividade = new DefaultMutableTreeNode("Atividade");
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
						DefaultMutableTreeNode nodoEstoque = new DefaultMutableTreeNode("Estoque");
						DefaultMutableTreeNode nodoCompra = new DefaultMutableTreeNode("Aquisição de Material");
						DefaultMutableTreeNode nodoRelCarro = new DefaultMutableTreeNode("Carro");
						DefaultMutableTreeNode nodoRelCliente = new DefaultMutableTreeNode("Cliente");
						DefaultMutableTreeNode nodoRelAtividade = new DefaultMutableTreeNode("Atividade");
						DefaultMutableTreeNode nodoServicoConcluido = new DefaultMutableTreeNode("Serviços Concluídos");
						DefaultMutableTreeNode nodoOrdemServico = new DefaultMutableTreeNode("Ordem de Serviço");
						DefaultMutableTreeNode nodoOrcamento = new DefaultMutableTreeNode("Serviços não Concluídos");
						DefaultMutableTreeNode nodoRelServicoAtrasado = new DefaultMutableTreeNode("Serviços Atrasados");
						
						nodoPrincipal.add(nodoCadastro);
						nodoPrincipal.add(nodoMovimento);
						nodoPrincipal.add(nodoRelatorios);
						
						
						nodoCadastro.add(nodoAtividade);
						nodoCadastro.add(nodoCargo);
						nodoCadastro.add(nodoCarro);
						nodoCadastro.add(nodoCidade);
						nodoCadastro.add(nodoCliente);
						nodoCadastro.add(nodoCor);
						nodoCadastro.add(nodoFornecedor);
						nodoCadastro.add(nodoFuncionario);
						nodoCadastro.add(nodoPeca);
						nodoCadastro.add(nodoMarca);
						nodoCadastro.add(nodoModelo);
						
						nodoRelatorios.add(nodoRelAtividade);
						nodoRelatorios.add(nodoRelCarro);
						nodoRelatorios.add(nodoRelCliente);
						nodoRelatorios.add(nodoRelServicoAtrasado);

						nodoAlmox.add(nodoEstoque);
						nodoAlmox.add(nodoCompra);
						
						nodoServico.add(nodoOrdemServico);
						nodoServico.add(nodoOrcamento);
						nodoServico.add(nodoServicoConcluido);
						
						nodoMovimento.add(nodoAlmox);
						nodoMovimento.add(nodoServico);
						
						jtOpcoes = new JTree(nodoPrincipal);
						jtOpcoes.addTreeSelectionListener(new TreeSelectionHandler());
						jspOpcoes.setViewportView(jtOpcoes);
						jtOpcoes.setPreferredSize(new java.awt.Dimension(193, 245));
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
		
		private RelCarro getPainelRelCarro() {
			painelRelCarro = new RelCarro();
			return painelRelCarro;
		}
		
		private RelCliente getPainelRelCliente() {
			painelRelCliente = new RelCliente();
			return painelRelCliente;
		}
		
		private RelAtividade getPainelRelAtividade() {
			painelRelAtividade = new RelAtividade();
			return painelRelAtividade;
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
		
		private PainelAtividade getPainelAtividade() {
			if (painelAtividade == null){
				painelAtividade = new PainelAtividade();
			}
			return painelAtividade;
		}
		
		private PainelServico getPainelServico() {
			if (painelServico == null){
				painelServico = new PainelServico();
			}
			return painelServico;
		}
		
		private PainelOrcamento getPainelOrcamento() {
			if (painelOrcamento == null){
				painelOrcamento = new PainelOrcamento();
			}
			return painelOrcamento;
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
			if (path.equals("[SMA, Cadastro, Cargo]")) {		
				showPainel(getPainelCargo());
			} else if (path.equals("[SMA, Cadastro, Cidade]")) {
				showPainel(getPainelCidade());
			} else if (path.equals("[SMA, Cadastro, Cliente]")) {
				showPainel(getPainelCliente());
			} else if (path.equals("[SMA, Cadastro, Cor]")) {				
				showPainel(getPainelCor());
			} else if (path.equals("[SMA, Cadastro, Funcionário]")) {
				showPainel(getPainelFuncionario());
			} else if (path.equals("[SMA, Cadastro, Marca]")) {			
				showPainel(getPainelMarca());
			} else if (path.equals("[SMA, Cadastro, Modelo]")) {		
				showPainel(getPainelModelo());
			} else if (path.equals("[SMA, Cadastro, Carro]")) {
				showPainel(getPainelCarro());
			} else if (path.equals("[SMA, Cadastro, Fornecedor]")) {
				showPainel(getPainelFornecedor());
			} else if (path.equals("[SMA, Cadastro, Peça]")) {
				showPainel(getPainelPeca());
			} else if (path.equals("[SMA, Cadastro, Atividade]")) {
				showPainel(getPainelAtividade());
			} else if (path.equals("[SMA, Movimento, Serviços, Serviços não Concluídos]")) {
				showPainel(getPainelOrcamento());
			} else if (path.equals("[SMA, Movimento, Serviços, Serviços Concluídos]")) {
				showPainel(getPainelServico());
			} else if (path.equals("[SMA, Movimento, Serviços, Ordem de Serviço]")) {
				new DialogoOrcamento(TelaPrincipal.instancia, "Ordem de Serviço", true);
			} else if (path.equals("[SMA, Movimento, Almoxarifado, Estoque]")) {
				showPainel(getPainelEstoque());
			} else if (path.equals("[SMA, Movimento, Almoxarifado, Aquisição de Material]")) {
				new DialogoFornecedorPeca(TelaPrincipal.instancia,"Aquisição de Material", true);
			} else if (path.equals("[SMA, Relatórios, Atividade]")) {
				showPainel(getPainelRelAtividade());
			} else if (path.equals("[SMA, Relatórios, Carro]")) {
				showPainel(getPainelRelCarro());
			} else if (path.equals("[SMA, Relatórios, Cliente]")) {
				showPainel(getPainelRelCliente());
			} else if (path.equals("[SMA, Relatórios, Serviços Atrasados]")) {
				RelServicoAtrasado relatorioServicoAtrasado = new RelServicoAtrasado();
				relatorioServicoAtrasado.exibirRelatorio();
			} else {
				showPainel(getPainelApresentacao());
			}
		}
	}

}

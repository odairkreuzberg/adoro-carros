package br.iav.ac.telas.core;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import br.iav.ac.telas.cor.DialogoCor;

/**
 * Area de Consulta, Inserção, Exclusão e alteração de Marca de um Veículo
 * 
 * @author Odair Kreuzberg
 */
public class DialogoPadrao extends JDialog {
	
	/*----------------------------------------------------------
	 * ATTRIBUTOS
	 *----------------------------------------------------------*/

	private JButton botaoConfirmar;
	private JButton botaoCancelar;
	private JLabel labelCodigo;
	private PainelPadrao painelGrid;
	private JPanel panelPrincipal;
	private JPanel panelBotoes;

	/*----------------------------------------------------------
	 * FIM DE ATTRIBUTOS
	 *----------------------------------------------------------*/

	/*----------------------------------------------------------
	 * CONSTRUTOR
	 *----------------------------------------------------------*/

	public DialogoPadrao(JFrame frame, String titulo, boolean modal) {
		super(frame, titulo, modal);
		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(null);
		panelBotoes = new JPanel();
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(DialogoCor.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		try {
			{
				botaoConfirmar = new JButton();
				panelBotoes.add(botaoConfirmar);
				botaoConfirmar.setText("Confirmar");
				botaoConfirmar.setBounds(116, 68, 97, 21);
			}
			{
				botaoCancelar = new JButton();
				panelBotoes.add(botaoCancelar);
				botaoCancelar.setText("Cancelar");
				botaoCancelar.setBounds(218, 68, 79, 21);
			}
			{
				labelCodigo = new JLabel();
				panelPrincipal.add(labelCodigo);
				labelCodigo.setText("Código:");
				labelCodigo.setBounds(12, 12, 60, 20);
			}
			this.add(panelPrincipal, BorderLayout.CENTER);
			this.add(panelBotoes, BorderLayout.SOUTH);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*----------------------------------------------------------
	 * FIM DE CONSTRUTOR
	 *----------------------------------------------------------*/

	/*----------------------------------------------------------
	 * INTERFACE
	 *----------------------------------------------------------*/

	public PainelPadrao getPainelGridl() {
		return painelGrid;
	}

	public JPanel getPanelPrincipal() {
		return panelPrincipal;
	}

	public JLabel getLabelCodigo() {
		return labelCodigo;
	}

	public JButton getBotaoConfirmar() {
		return botaoConfirmar;
	}

	public JButton getBotaoCancelar() {
		return botaoCancelar;
	}

	public void setCrudPanel(PainelPadrao painelGrid) {
		this.setDefaultCloseOperation(DialogoPadrao.DISPOSE_ON_CLOSE);
		this.painelGrid = painelGrid;
		initGUI();
		this.setSize(549, 553);
	}

	private void initGUI() {
		this.setLayout(null);
		{
			getContentPane().add(this.painelGrid);
			this.painelGrid.setBounds(0, 0, 541, 482);
		}
		try {
			pack();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/*----------------------------------------------------------
	 * FIM DE INTERFACE
	 *----------------------------------------------------------*/

}
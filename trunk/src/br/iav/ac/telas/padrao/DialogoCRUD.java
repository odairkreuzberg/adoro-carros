package br.iav.ac.telas.padrao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import br.iav.ac.telas.TelaPrincipal;



public class DialogoCRUD extends JDialog {
	private JButton botaoConfirmar;
	private PainelPadrao painelPadrao;
	private JButton botaoCancelar;
	private DialogoHandler dialogoHandler;
	


	public DialogoCRUD(JFrame frame, String titulo, boolean modal) {
		super(TelaPrincipal.instancia,titulo,modal);
		
	}

	public void setPainel(PainelPadrao painelPadrao) {
		this.setDefaultCloseOperation(DialogoCRUD.DISPOSE_ON_CLOSE);
		this.painelPadrao = painelPadrao;
		dialogoHandler = new DialogoHandler();

		initGUI();

		botaoCancelar.addActionListener(dialogoHandler);
	}
	
	public JButton getBotaoConfirmar() {
		return botaoConfirmar;
	}
	
	public JButton getBotaoCancelar() {
		return botaoCancelar;
	}

	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				{
					botaoConfirmar = new JButton();
					getContentPane().add(botaoConfirmar);
					botaoConfirmar.setText("Confirmar");
					botaoConfirmar.setBounds(330, 553, 97, 21);
				}
				{
					botaoCancelar = new JButton();
					getContentPane().add(botaoCancelar);
					botaoCancelar.setText("Cancelar");
					botaoCancelar.setBounds(440, 553, 97, 21);
				}
				{
					getContentPane().add(this.painelPadrao);
					painelPadrao.setBounds(0, 0, 549, 553);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		setSize(555, 610);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	class DialogoHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			
		}
		
	}

}

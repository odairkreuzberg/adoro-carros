package br.iav.ac.telas.padrao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import br.iav.ac.telas.TelaPrincipal;



public class DialogoCRUD extends JDialog {
	private PainelPadrao painelPadrao;
	private JButton botaoFechar;
	private DialogoHandler dialogoHandler;
	


	public DialogoCRUD(JFrame frame, String titulo, boolean modal) {
		super(TelaPrincipal.instancia,titulo,modal);
		
	}

	public void setPainel(PainelPadrao painelPadrao) {
		this.setDefaultCloseOperation(DialogoCRUD.DISPOSE_ON_CLOSE);
		this.painelPadrao = painelPadrao;
		initGUI();
	}
	
	public JButton getBotaoFechar() {
		if (botaoFechar == null){
			botaoFechar = new JButton();
		}
		return botaoFechar;
	}

	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				{
					getContentPane().add(getBotaoFechar());
					botaoFechar.setText("Fechar");
					botaoFechar.setBounds(440, 553, 97, 21);
				}
				{
					getContentPane().add(this.painelPadrao);
					painelPadrao.setBounds(0, 0, 549, 553);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		dialogoHandler = new DialogoHandler();
		botaoFechar.addActionListener(dialogoHandler);
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

package br.iav.ac.telas.padrao;
import javax.swing.JButton;
import javax.swing.JDialog;


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
public class DialogoCRUD extends JDialog {
	private JButton botaoConfirmar;
	private PainelPadrao painelPadrao;
	private JButton botaoCancelar;
	


	public DialogoCRUD(JDialog dialog, String titulo, boolean modal) {
		super(dialog,titulo,modal);
		
	}
	public void setPainel(PainelPadrao painelPadrao){
		this.setDefaultCloseOperation(DialogoCRUD.DISPOSE_ON_CLOSE);
		this.painelPadrao = painelPadrao;
		initGUI();
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
					//painelPadrao = new JPanel();
					getContentPane().add(this.painelPadrao);
					painelPadrao.setBounds(0, 0, 549, 553);
				}
			}
			pack();
		} catch(Exception e) {
			e.printStackTrace();
		}
		setSize(555, 579);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}

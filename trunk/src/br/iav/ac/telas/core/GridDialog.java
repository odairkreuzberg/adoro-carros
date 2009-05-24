package br.iav.ac.telas.core;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * Area de Consulta, Inserção, Exclusão e alteração de Marca de um Veículo
 * 
 * @author Odair Kreuzberg
 */
public class GridDialog extends JDialog {
	
	/*----------------------------------------------------------
	 * ATTRIBUTOS
	 *----------------------------------------------------------*/

	private static final long serialVersionUID = 1L;
	private JButton botaoConfirmar;
	private JButton botaCancelar;
	private PainelGrid painelGrid;

	/*----------------------------------------------------------
	 * FIM DE ATTRIBUTOS
	 *----------------------------------------------------------*/

	/*----------------------------------------------------------
	 * CONSTRUTOR
	 *----------------------------------------------------------*/

	public GridDialog(JFrame frame, String titulo, boolean modal) {

		super(frame, titulo, modal);
		
	}

	/*----------------------------------------------------------
	 * FIM DE CONSTRUTOR
	 *----------------------------------------------------------*/


	/*----------------------------------------------------------
	 * INTERFACE
	 *----------------------------------------------------------*/

	public PainelGrid getPainelGridl() {
		return painelGrid;
	}

	public void setCrudPanel(PainelGrid painelGrid) {

		this.setDefaultCloseOperation(GridDialog.DISPOSE_ON_CLOSE);
		this.painelGrid = painelGrid;
		initGUI();
		this.setSize(549, 553);
	}

	private void initGUI() {
		this.setLayout(null);
		{
			botaoConfirmar = new JButton();
			getContentPane().add(botaoConfirmar);
			botaoConfirmar.setText("Confirmar");
			botaoConfirmar.setBounds(341, 494, 89, 21);
		}
		{
			botaCancelar = new JButton();
			getContentPane().add(botaCancelar);
			botaCancelar.setText("Cancelar");
			botaCancelar.setBounds(441, 494, 89, 21);
		}
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
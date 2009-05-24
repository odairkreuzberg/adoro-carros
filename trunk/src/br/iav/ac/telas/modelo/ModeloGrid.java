package br.iav.ac.telas.modelo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import br.iav.ac.telas.TelaPrincipal;
import br.iav.ac.telas.core.PainelGrid;

/**
 * Area de Consulta, Inserção, Exclusão e alteração de Modelo de um Veículo
 * 
 * @author Odair Kreuzberg
 */
public class ModeloGrid extends PainelGrid {
	
	/*----------------------------------------------------------
	 * ATTRIBUTOS
	 *----------------------------------------------------------*/

	private static final long serialVersionUID = 1L;
	private JButton botaoNovo;
	private JTable gridModelo;
	private JButton botaorExcluir;
	private JButton botaoEditar;
	private JScrollPane scrolModelo;
	private CadastroHandle cadastroHandle;

	/*----------------------------------------------------------
	 * FIM DE ATTRIBUTOS
	 *----------------------------------------------------------*/

	/*----------------------------------------------------------
	 * CONSTRUTOR
	 *----------------------------------------------------------*/

	public ModeloGrid() {
		super();
		initGUI();
		inicializarHandlers();
	}

	/*----------------------------------------------------------
	 * FIM DE CONSTRUTOR
	 *----------------------------------------------------------*/

	/*----------------------------------------------------------
	 * INTERFACE
	 *----------------------------------------------------------*/

	private void inicializarHandlers() {

		this.cadastroHandle = new CadastroHandle();

		botaoNovo.addActionListener(cadastroHandle);

		botaoEditar.addActionListener(cadastroHandle);

		botaorExcluir.addActionListener(cadastroHandle);
	}	

	@Override
	public JTable getGrid() {
		return this.gridModelo;
	}
	private void initGUI() {
		this.setSize(549, 553);
		this.setLayout(null);
		try {
			{
				botaoNovo = new JButton();
				this.add(botaoNovo);
				botaoNovo.setText("Novo");
				botaoNovo.setBounds(12, 12, 85, 27);
			}
			{
				botaoEditar = new JButton();
				this.add(botaoEditar);
				botaoEditar.setText("Editar");
				botaoEditar.setBounds(108, 12, 85, 27);
			}
			{
				botaorExcluir = new JButton();
				this.add(botaorExcluir);
				botaorExcluir.setText("Excluir");
				botaorExcluir.setBounds(204, 12, 85, 27);
			}
			{
				String col[] = { "ID", "Modelo" };

				String lin[][] = { { "", "" } };

				DefaultTableModel model = new DefaultTableModel(lin, col);
				model.setRowCount(25);

				gridModelo = new JTable(model);

				gridModelo.setShowVerticalLines(true);
				TableColumn column = gridModelo.getColumnModel().getColumn(0);
				column.setPreferredWidth(100);
				column = gridModelo.getColumnModel().getColumn(1);
				column.setPreferredWidth(500);
				scrolModelo = new JScrollPane();
				scrolModelo.setViewportView(gridModelo);
				this.add(scrolModelo);
				scrolModelo.setBounds(12, 50, 525, 491);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/*----------------------------------------------------------
	 * FIM DE INTERFACE
	 *----------------------------------------------------------*/

	

	/*----------------------------------------------------------
	 * CLASSE LIMITROFE
	 *----------------------------------------------------------*/

	class CadastroHandle implements ActionListener {

		public CadastroHandle() {
			super();
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == botaoNovo) {
				ModeloForm modeloForm = new ModeloForm(TelaPrincipal.instancia,
						"Cadastro de Modelo", true);
			} else if (e.getSource() == botaoEditar) {

			} else if (e.getSource() == botaorExcluir) {

			}
		}
	}

	/*----------------------------------------------------------
	 * FIM DE CLASSE LIMITROFE
	 *----------------------------------------------------------*/

}

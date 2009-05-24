package br.iav.ac.telas.marca;
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
 * Area de Consulta, Inserção, Exclusão e alteração de Marca de um Veículo
 * 
 * @author Odair Kreuzberg
 */
public class MarcaGrid extends PainelGrid {
	
	/*----------------------------------------------------------
	 * ATTRIBUTOS
	 *----------------------------------------------------------*/

	private static final long serialVersionUID = 1L;
	private JButton botaoNovo;
	private JTable gridMarca;
	private JButton botaorExcluir;
	private JButton botaoEditar;
	private JScrollPane scrolMarca;
	private CadastroHandle cadastroHandle;

	/*----------------------------------------------------------
	 * FIM DE ATTRIBUTOS
	 *----------------------------------------------------------*/

	/*----------------------------------------------------------
	 * CONSTRUTOR
	 *----------------------------------------------------------*/

	public MarcaGrid() {
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
		return this.gridMarca;
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
				String col[] = { "ID", "Marca" };

				String lin[][] = { { "", "" } };

				DefaultTableModel model = new DefaultTableModel(lin, col);
				model.setRowCount(25);

				gridMarca = new JTable(model);

				gridMarca.setShowVerticalLines(true);
				TableColumn column = gridMarca.getColumnModel().getColumn(0);
				column.setPreferredWidth(100);
				column = gridMarca.getColumnModel().getColumn(1);
				column.setPreferredWidth(500);
				scrolMarca = new JScrollPane();
				scrolMarca.setViewportView(gridMarca);
				this.add(scrolMarca);
				scrolMarca.setBounds(12, 50, 525, 491);
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
				MarcaForm marcaForm = new MarcaForm(TelaPrincipal.instancia,
						"Cadastro de Marca", true);
			} else if (e.getSource() == botaoEditar) {

			} else if (e.getSource() == botaorExcluir) {

			}
		}
	}

	/*----------------------------------------------------------
	 * FIM DE CLASSE LIMITROFE
	 *----------------------------------------------------------*/

}

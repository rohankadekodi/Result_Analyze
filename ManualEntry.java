import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;


public class ManualEntry extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final GUI homescreen;
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public ManualEntry(GUI g, DB_Init db_table) {
		homescreen = g;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 745, 485);
		contentPane = new JPanel();
		contentPane.setBorder(UIManager.getBorder("FileChooser.listViewBorder"));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*final tableModel table_model = new tableModel(data) ;
		final JTable table = new JTable(table_model);
		table.setBounds(10, 127, 709, 308);
		table.setRowSelectionAllowed(true);
		table.setSelectionForeground(Color.white);
		table.setSelectionBackground(Color.blue);
		table.getColumnModel().getColumn(0).setMaxWidth(75);*/
		
		
	}
		
}

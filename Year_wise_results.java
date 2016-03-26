import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;


public class Year_wise_results extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public Year_wise_results() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 745, 485);
		contentPane = new JPanel();
		contentPane.setBorder(UIManager.getBorder("FileChooser.listViewBorder"));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	
}

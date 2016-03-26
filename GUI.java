import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	//private SearchScreen new_search_screen;
	//static PDFtoDB_Insert begin;
	static GUI Mainframe = new GUI();
	DB_Init db_table = new DB_Init();
	/**
	 * Launch the application.
	 */	
	
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(
			        UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mainframe = new GUI();
					Mainframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 745, 485);
		contentPane = new JPanel();
		contentPane.setForeground(Color.DARK_GRAY);
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JButton importPDFbtn = new JButton("Import Result PDF");
		importPDFbtn.setBackground(new Color(102, 153, 204));
		//importPDFbtn.setBorder(UIManager.getBorder("EditorPane.border"));
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		importPDFbtn.setFont(new Font("Arial", Font.BOLD, 20));
		importPDFbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				branchSelection branchpage = new branchSelection(db_table);
				branchpage.setVisible(true);
			}
		});
		importPDFbtn.setBounds(164, 204, 442, 90);
		contentPane.add(importPDFbtn);
		
		
		
		JButton btnNewButton_1 = new JButton("View Existing Data");
		btnNewButton_1.setBackground(new Color(102, 153, 204));
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SearchScreen SearchFrame = new SearchScreen(Mainframe, db_table);
				SearchFrame.setVisible(true);
				Aggregation AggregationFrame = new Aggregation(Mainframe, db_table);
				AggregationFrame.setVisible(true);
				Mainframe.setVisible(false);			
				dispose();
			}
		});
		btnNewButton_1.setBounds(164, 318, 216, 90);
		contentPane.add(btnNewButton_1);
		
		/*JButton btnNewButton_2 = new JButton("Add New Data");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ManualEntry ManualFrame = new ManualEntry(Mainframe, db_table);
				ManualFrame.setVisible(true);
				Mainframe.setVisible(false);			
				dispose();
			}
		});
		btnNewButton_2.setBounds(386, 327, 197, 94);
		contentPane.add(btnNewButton_2);
		*/
		ImageIcon homePanel = new ImageIcon("IMG_4972.JPG");
		JLabel lblNewLabel = new JLabel(homePanel);
		lblNewLabel.setFont(new Font("Monotype Corsiva", Font.BOLD, 50));
		lblNewLabel.setText("");
		lblNewLabel.setForeground(new Color(204, 204, 153));
		lblNewLabel.setBounds(10, 11, 709, 160);
		contentPane.add(lblNewLabel);
		
		JButton btnClearExistingData = new JButton("Clear Existing Data");
		btnClearExistingData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				db_table.original_table.drop();
				db_table.filtered_collection.drop();
			}
		});
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		btnClearExistingData.setBounds(390, 318, 216, 90);
		btnClearExistingData.setFont(new Font("Arial", Font.BOLD, 20));
		btnClearExistingData.setBackground(new Color(102, 153, 204));
		contentPane.add(btnClearExistingData);
	}
}

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.DefaultComboBoxModel;


public class Aggregation extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private GUI homescreen;
	private Queries_GUI q_gui = new Queries_GUI();
	private JPanel topPanel;
	
	public Aggregation(GUI g, final DB_Init db_table) {
		homescreen = g;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(10, 10, 745, 485);
		contentPane = new JPanel();
		contentPane.setForeground(Color.DARK_GRAY);
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(UIManager.getBorder("FileChooser.listViewBorder"));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		ImageIcon homeIcon = new ImageIcon("home-icon.png");
		JButton homeBtn = new JButton(homeIcon);
		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				homescreen.setVisible(true);
				dispose();
			}
		});
		homeBtn.setBounds(10, 11, 72, 55);
		contentPane.add(homeBtn);
		
		List<String[]> data = q_gui.filterByCriteria("College", "Branch", "Batch", "Year", db_table);
		
		topPanel = new JPanel();
		topPanel.setForeground(Color.GRAY);
		topPanel.setBackground(Color.DARK_GRAY);
		topPanel.setBounds(0, 149, 729, 297);
		
		contentPane.add(topPanel);
		topPanel.setLayout(null);
		
		JLabel lblTotalStudentsAppeared = new JLabel("Total Students Appeared");
		lblTotalStudentsAppeared.setForeground(Color.LIGHT_GRAY);
		lblTotalStudentsAppeared.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotalStudentsAppeared.setBounds(38, 0, 260, 36);
		topPanel.add(lblTotalStudentsAppeared);
		
		JLabel lblFirstClassDistinction = new JLabel("First Class With Distinction");
		lblFirstClassDistinction.setForeground(Color.LIGHT_GRAY);
		lblFirstClassDistinction.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFirstClassDistinction.setBounds(38, 32, 260, 36);
		topPanel.add(lblFirstClassDistinction);
		
		JLabel lblHigherSecondClass = new JLabel("Higher Second Class");
		lblHigherSecondClass.setForeground(Color.LIGHT_GRAY);
		lblHigherSecondClass.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblHigherSecondClass.setBounds(38, 96, 260, 36);
		topPanel.add(lblHigherSecondClass);
		
		JLabel lblFirstClass = new JLabel("First Class");
		lblFirstClass.setForeground(Color.LIGHT_GRAY);
		lblFirstClass.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFirstClass.setBounds(38, 64, 260, 36);
		topPanel.add(lblFirstClass);
		
		JLabel lblSecondClass = new JLabel("Second Class");
		lblSecondClass.setForeground(Color.LIGHT_GRAY);
		lblSecondClass.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSecondClass.setBounds(38, 128, 260, 36);
		topPanel.add(lblSecondClass);
		
		JLabel lblPassClass = new JLabel("Pass Class");
		lblPassClass.setForeground(Color.LIGHT_GRAY);
		lblPassClass.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassClass.setBounds(38, 160, 260, 36);
		topPanel.add(lblPassClass);
		
		JLabel lblFailsWithAtkt = new JLabel("Fails With A.T.K.T.");
		lblFailsWithAtkt.setForeground(Color.LIGHT_GRAY);
		lblFailsWithAtkt.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFailsWithAtkt.setBounds(38, 192, 260, 36);
		topPanel.add(lblFailsWithAtkt);
		
		JLabel lblFails = new JLabel("Fails");
		lblFails.setForeground(Color.LIGHT_GRAY);
		lblFails.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFails.setBounds(38, 226, 260, 36);
		topPanel.add(lblFails);
		
		final JLabel lblTotalNo = new JLabel("");
		lblTotalNo.setForeground(Color.LIGHT_GRAY);
		lblTotalNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotalNo.setBounds(331, 0, 146, 36);
		topPanel.add(lblTotalNo);
		
		final JLabel lblDistNo = new JLabel("");
		lblDistNo.setForeground(Color.LIGHT_GRAY);
		lblDistNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDistNo.setBounds(331, 32, 146, 36);
		topPanel.add(lblDistNo);
		
		final JLabel lblFirstNo = new JLabel("");
		lblFirstNo.setForeground(Color.LIGHT_GRAY);
		lblFirstNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFirstNo.setBounds(331, 64, 146, 36);
		topPanel.add(lblFirstNo);
		
		final JLabel lblHighSecNo = new JLabel("");
		lblHighSecNo.setForeground(Color.LIGHT_GRAY);
		lblHighSecNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblHighSecNo.setBounds(331, 96, 146, 36);
		topPanel.add(lblHighSecNo);
		
		final JLabel lblSecondNo = new JLabel("");
		lblSecondNo.setForeground(Color.LIGHT_GRAY);
		lblSecondNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSecondNo.setBounds(331, 128, 146, 36);
		topPanel.add(lblSecondNo);
		
		final JLabel lblPassNo = new JLabel("");
		lblPassNo.setForeground(Color.LIGHT_GRAY);
		lblPassNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassNo.setBounds(331, 160, 146, 36);
		topPanel.add(lblPassNo);
		
		final JLabel lblAtktNo = new JLabel("");
		lblAtktNo.setForeground(Color.LIGHT_GRAY);
		lblAtktNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAtktNo.setBounds(331, 192, 146, 36);
		topPanel.add(lblAtktNo);
		
		final JLabel lblFailNo = new JLabel("");
		lblFailNo.setForeground(Color.LIGHT_GRAY);
		lblFailNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFailNo.setBounds(331, 226, 146, 36);
		topPanel.add(lblFailNo);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBackground(Color.WHITE);
		separator_1.setForeground(Color.WHITE);
		separator_1.setBounds(308, 0, 2, 304);
		topPanel.add(separator_1);
		
		final JLabel lblNotAvailablereservedothers = new JLabel("Not Available/Reserved/Others");
		lblNotAvailablereservedothers.setForeground(Color.LIGHT_GRAY);
		lblNotAvailablereservedothers.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNotAvailablereservedothers.setBounds(38, 258, 260, 36);
		topPanel.add(lblNotAvailablereservedothers);
		
		final JLabel lblNotAvailNo = new JLabel("");
		lblNotAvailNo.setForeground(Color.LIGHT_GRAY);
		lblNotAvailNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNotAvailNo.setBounds(331, 258, 146, 36);
		topPanel.add(lblNotAvailNo);
		
		final JLabel lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setBounds(496, 47, 223, 28);
		contentPane.add(lblError);
	
		final String collegeMenu[] = q_gui.collegeList(db_table);
		
		final JComboBox comboBox_1 = new JComboBox(collegeMenu);
		
		final JComboBox colg_list = new JComboBox(collegeMenu);
		
		colg_list.setName("");
		colg_list.setBorder(UIManager.getBorder("FileChooser.listViewBorder"));
		colg_list.setBounds(10, 94, 150, 28);
		contentPane.add(colg_list);
		
		final String branchMenu[] = q_gui.branchList(db_table);
		final JComboBox branch_list = new JComboBox(branchMenu);
		branch_list.setBorder(UIManager.getBorder("FileChooser.listViewBorder"));
		branch_list.setBounds(168, 94, 150, 28);
		contentPane.add(branch_list);
		
		final String batchMenu[] = q_gui.batchList(db_table);
		final JComboBox batch_list = new JComboBox(batchMenu);
		batch_list.setBorder(UIManager.getBorder("FileChooser.listViewBorder"));
		batch_list.setBounds(330, 94, 150, 28);
		contentPane.add(batch_list);
		
		final String yearMenu[] = {"Year", "FE", "SE", "TE", "BE"};
		final JComboBox year_list = new JComboBox(yearMenu);
		year_list.setModel(new DefaultComboBoxModel(new String[] {" Year", "FE", "SE", "TE", "BE"}));
		year_list.setBorder(UIManager.getBorder("FileChooser.listViewBorder"));
		year_list.setBounds(493, 94, 150, 28);
		contentPane.add(year_list);
		
		ImageIcon searchIcon = new ImageIcon("Search-icon.png");
		
		JButton btnNewButton = new JButton("Go");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hi");
				int[] c = q_gui.filterByCriteriaAndAggregate(colg_list.getSelectedItem().toString(), branch_list.getSelectedItem().toString(),batch_list.getSelectedItem().toString(), year_list.getSelectedItem().toString(), db_table);
				lblTotalNo.setText(Integer.toString(c[9]));
				lblDistNo.setText(Integer.toString(c[0]));
				lblFirstNo.setText(Integer.toString(c[1]));
				lblHighSecNo.setText(Integer.toString(c[2]));
				lblSecondNo.setText(Integer.toString(c[3]));
				lblPassNo.setText(Integer.toString(c[4]));
				lblAtktNo.setText(Integer.toString(c[5]));
				lblFailNo.setText(Integer.toString(c[6]));
				lblNotAvailNo.setText(Integer.toString(c[7]));
				if(c[8]>0)
					lblError.setText("Please Select Year For Aggregation!");
			}
		});
		btnNewButton.setBounds(653, 88, 66, 34);
		contentPane.add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 146, 729, 2);
		contentPane.add(separator);
	}
}
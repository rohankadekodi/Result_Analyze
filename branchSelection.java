import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class branchSelection extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField rollStart_1;
	private JTextField rollEnd_1;
	private JTextField rollStart_2;
	private JTextField rollEnd_2;
	private JTextField rollStart_3;
	private JTextField rollEnd_3;
	private JLabel lblStartRoll;
	private JLabel lblEndRoll;
	private JButton submitBtn;
	private JButton btnImportPDF;
	private JLabel labelBranch;

	final JFileChooser fc = new JFileChooser();
	static PDFtoDB_Insert begin;
	File file;

	public branchSelection(final DB_Init db_table) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(10, 10, 745, 485);
		contentPane = new JPanel();
		contentPane.setForeground(Color.DARK_GRAY);
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane
				.setBorder(UIManager.getBorder("FileChooser.listViewBorder"));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel Grad_label = new JLabel("  Enter Graduation Year of Batch:");
		Grad_label.setOpaque(true);
		Grad_label.setForeground(new Color(0, 0, 0));
		Grad_label.setAlignmentX(Component.CENTER_ALIGNMENT);
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
		Grad_label.setBounds(295, 37, 214, 41);
		Grad_label.setFont(new Font("Arial", Font.BOLD, 13));
		Grad_label.setBackground(new Color(230, 230, 250));
		contentPane.add(Grad_label);

		String batchArray[] = { "Batch", "2014", "2015", "2016", "2017",
				"2018", "2019", "2020" };
		final JComboBox batchCombo = new JComboBox(batchArray);
		batchCombo.setBounds(561, 37, 126, 41);
		contentPane.add(batchCombo);

		String branches[] = { "Branch", "F.E. Common", "Information Technology Engg.",
				"Computer Engg.", "Elec. & Telecomm.", "Mechanical Engg.",
				"Chemical Engg.", "Civil Engg.", "Electrical Engg.",
				"Electronics Engg.", "Instru. and Control Engg.",
				"Production Engg.", "Mech(S/W) Engg.", "Automobile Engg." };

		final JComboBox branchBox_1 = new JComboBox(branches);
		branchBox_1.setBounds(93, 182, 202, 35);
		contentPane.add(branchBox_1);

		final JComboBox branchBox_2 = new JComboBox(branches);
		branchBox_2.setBounds(93, 251, 202, 35);
		contentPane.add(branchBox_2);

		final JComboBox branchBox_3 = new JComboBox(branches);
		branchBox_3.setBounds(93, 321, 202, 35);
		contentPane.add(branchBox_3);

		rollStart_1 = new JTextField();
		rollStart_1.setBounds(359, 182, 126, 35);
		contentPane.add(rollStart_1);
		rollStart_1.setColumns(10);

		rollEnd_1 = new JTextField();
		rollEnd_1.setColumns(10);
		rollEnd_1.setBounds(561, 182, 126, 35);
		contentPane.add(rollEnd_1);

		rollStart_2 = new JTextField();
		rollStart_2.setColumns(10);
		rollStart_2.setBounds(359, 251, 126, 35);
		contentPane.add(rollStart_2);

		rollEnd_2 = new JTextField();
		rollEnd_2.setColumns(10);
		rollEnd_2.setBounds(561, 251, 126, 35);
		contentPane.add(rollEnd_2);

		rollStart_3 = new JTextField();
		rollStart_3.setColumns(10);
		rollStart_3.setBounds(359, 322, 126, 35);
		contentPane.add(rollStart_3);

		rollEnd_3 = new JTextField();
		rollEnd_3.setColumns(10);
		rollEnd_3.setBounds(561, 322, 126, 35);
		contentPane.add(rollEnd_3);

		lblStartRoll = new JLabel("  Start Roll No.");
		lblStartRoll.setOpaque(true);
		lblStartRoll.setBounds(359, 122, 126, 35);
		contentPane.add(lblStartRoll);

		lblEndRoll = new JLabel("  End Roll No.");
		lblEndRoll.setOpaque(true);
		lblEndRoll.setBounds(561, 122, 126, 35);
		contentPane.add(lblEndRoll);

		final JProgressBar progressBar = new JProgressBar(0, 5);
		progressBar.setBounds(-11, 432, 740, 20);
		contentPane.add(progressBar);

		progressBar.setString("Loading....");

		btnImportPDF = new JButton("  Import PDF...");
		btnImportPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				progressBar.setStringPainted(true);
				// Handle open button action.
				if (e.getSource() == btnImportPDF) {
					int returnVal = fc.showOpenDialog(branchSelection.this);

					if (returnVal == JFileChooser.APPROVE_OPTION) {
						file = fc.getSelectedFile();
					} else {
						// log.append("Open command cancelled by user.");
					}
				}
			}
		});
		btnImportPDF.setBounds(93, 38, 110, 41);
		contentPane.add(btnImportPDF);

		final ArrayList<String> branchArray = new ArrayList<String>();
		final ArrayList<String> rollStartArray = new ArrayList<String>();
		final ArrayList<String> rollEndArray = new ArrayList<String>();

		
		
		
		submitBtn = new JButton("  Submit");
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				begin = new PDFtoDB_Insert(file.getAbsolutePath());
				if (batchCombo.getSelectedItem().toString() != "Batch") {
					System.out.println("Box selection " + branchBox_1.getSelectedItem().toString());
					System.out.println("Box selection " + branchBox_2.getSelectedItem().toString());
					System.out.println("Box selection " + branchBox_3.getSelectedItem().toString());
					if (branchBox_1.getSelectedItem().toString() != "Branch") {
						branchArray.add(branchBox_1.getSelectedItem().toString());
						rollStartArray.add(rollStart_1.getText());
						rollEndArray.add(rollEnd_1.getText());
					}
					if (branchBox_2.getSelectedItem().toString() != "Branch") {
						branchArray.add(branchBox_2.getSelectedItem().toString());
						rollStartArray.add(rollStart_2.getText());
						rollEndArray.add(rollEnd_2.getText());
					}
					if (branchBox_3.getSelectedItem().toString() != "Branch") {
						branchArray.add(branchBox_3.getSelectedItem().toString());
						rollStartArray.add(rollStart_3.getText());
						rollEndArray.add(rollEnd_3.getText());
					}

					begin.start_insert(db_table, batchCombo.getSelectedItem()
							.toString(), branchArray, rollStartArray, rollEndArray);
					progressBar.setString("100" + "%");
					progressBar.setValue(100);
				}
			}
		});
		submitBtn.setBounds(309, 380, 176, 41);
		contentPane.add(submitBtn);

		labelBranch = new JLabel("  Branch");
		labelBranch.setOpaque(true);
		labelBranch.setBounds(93, 122, 202, 35);
		contentPane.add(labelBranch);

	}

}

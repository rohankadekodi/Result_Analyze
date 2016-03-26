import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.UIManager;

import org.bson.Document;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

/* MongoDB document format :
 * 	{
 * 		"Name" : xx ,
 * 		"College" : xx ,
 * 		"PRN" : xx,
 * 		"F" {
 * 			"Roll" : xx,
 * 			"Sub 1" : {
 * 					"Total":xx, "Scored":xx, "Status":[P/F]
 * 			} , "Sub 2" : xx..... ,
 * 			"Total" : xx, "Scored" : xx,  "Result" : "Distinction,etc"
 * 		},
 * 		"S" { .... } , "T" { ... } , "B" { .. }
 * }
 */

public class StudentScreen extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	DB_Queries db_q = new DB_Queries();

	@SuppressWarnings({ "deprecation", "unchecked" })
	public StudentScreen(String name, DB_Init db_table) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(10, 10, 745, 485);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(UIManager.getBorder("FileChooser.listViewBorder"));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblOverallAcademicRecord = new JLabel("Overall Academic Record");
		lblOverallAcademicRecord.setForeground(new Color(255, 255, 255));
		lblOverallAcademicRecord.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblOverallAcademicRecord.setBounds(265, 0, 274, 51);
		contentPane.add(lblOverallAcademicRecord);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(204, 204, 204));
		separator.setBounds(0, 216, 729, 4);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(new Color(204, 204, 204));
		separator_1.setBounds(0, 44, 729, 2);
		contentPane.add(separator_1);
		
		ImageIcon backIcon = new ImageIcon("back-icon.png");
		JButton btnBackBtn = new JButton(backIcon);
		try{
		btnBackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
//				prev_screen.setVisible(true);
				dispose();
			}
		});}
		catch(NullPointerException e) {
				e.printStackTrace();
		};
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setBackground(Color.LIGHT_GRAY);
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblName.setBounds(56, 44, 90, 35);
		contentPane.add(lblName);
		
		JLabel lblCollege = new JLabel("College");
		lblCollege.setForeground(new Color(255, 255, 255));
		lblCollege.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblCollege.setBackground(Color.LIGHT_GRAY);
		lblCollege.setBounds(56, 80, 90, 35);
		contentPane.add(lblCollege);
		
		JLabel lblPrn = new JLabel("PRN");
		lblPrn.setForeground(new Color(255, 255, 255));
		lblPrn.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblPrn.setBackground(Color.LIGHT_GRAY);
		lblPrn.setBounds(56, 116, 90, 35);
		contentPane.add(lblPrn);
		
		JLabel lblBranch = new JLabel("Branch");
		lblBranch.setForeground(new Color(255, 255, 255));
		lblBranch.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblBranch.setBackground(Color.LIGHT_GRAY);
		lblBranch.setBounds(56, 152, 90, 35);
		contentPane.add(lblBranch);
		
		JLabel label_3 = new JLabel("Name");
		label_3.setForeground(new Color(255, 255, 255));
		label_3.setFont(new Font("Times New Roman", Font.BOLD, 16));
		label_3.setBackground(Color.LIGHT_GRAY);
		label_3.setBounds(156, 44, 461, 35);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("Name");
		label_4.setForeground(new Color(255, 255, 255));
		label_4.setFont(new Font("Times New Roman", Font.BOLD, 16));
		label_4.setBackground(Color.LIGHT_GRAY);
		label_4.setBounds(156, 80, 461, 35);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("Name");
		label_5.setForeground(new Color(255, 255, 255));
		label_5.setFont(new Font("Times New Roman", Font.BOLD, 16));
		label_5.setBackground(Color.LIGHT_GRAY);
		label_5.setBounds(156, 116, 461, 35);
		contentPane.add(label_5);
		
		JLabel lblNA = new JLabel("NA");
		lblNA.setForeground(new Color(255, 255, 255));
		lblNA.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNA.setBackground(Color.LIGHT_GRAY);
		lblNA.setBounds(156, 152, 461, 35);
		contentPane.add(lblNA);
		
		JLabel lblMarksFE = new JLabel("NA");
		lblMarksFE.setForeground(new Color(255, 255, 255));
		lblMarksFE.setBounds(257, 255, 80, 30);
		contentPane.add(lblMarksFE);
		
		JLabel lblPercentageFE = new JLabel("NA");
		lblPercentageFE.setForeground(new Color(255, 255, 255));
		lblPercentageFE.setBounds(459, 255, 158, 30);
		contentPane.add(lblPercentageFE);
		
		JLabel lblMarksSE = new JLabel("NA");
		lblMarksSE.setForeground(new Color(255, 255, 255));
		lblMarksSE.setBounds(257, 305, 80, 30);
		contentPane.add(lblMarksSE);
		
		JLabel lblPecentageSE = new JLabel("NA");
		lblPecentageSE.setForeground(new Color(255, 255, 255));
		lblPecentageSE.setBounds(460, 305, 158, 30);
		contentPane.add(lblPecentageSE);
		
		JLabel lblMarksTE = new JLabel("NA");
		lblMarksTE.setForeground(new Color(255, 255, 255));
		lblMarksTE.setBounds(257, 355, 80, 30);
		contentPane.add(lblMarksTE);
		
		JLabel lblPecentageTE = new JLabel("NA");
		lblPecentageTE.setForeground(new Color(255, 255, 255));
		lblPecentageTE.setBounds(460, 355, 158, 30);
		contentPane.add(lblPecentageTE);
		
		JLabel lblMarksBE = new JLabel("NA");
		lblMarksBE.setForeground(new Color(255, 255, 255));
		lblMarksBE.setBounds(257, 405, 80, 30);
		contentPane.add(lblMarksBE);
		
		JLabel lblPercentageBE = new JLabel("NA");
		lblPercentageBE.setForeground(new Color(255, 255, 255));
		lblPercentageBE.setBounds(460, 405, 158, 30);
		contentPane.add(lblPercentageBE);
		
		JLabel lblMarksHeading = new JLabel("Marks");
		lblMarksHeading.setForeground(new Color(255, 255, 255));
		lblMarksHeading.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblMarksHeading.setBounds(257, 216, 46, 30);
		contentPane.add(lblMarksHeading);
		
		JLabel lblPercentageHeading = new JLabel("Percentage");
		lblPercentageHeading.setForeground(new Color(255, 255, 255));
		lblPercentageHeading.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblPercentageHeading.setBounds(459, 216, 128, 30);
		contentPane.add(lblPercentageHeading);
		
		JLabel lblBatch = new JLabel("Batch");
		lblBatch.setForeground(Color.WHITE);
		lblBatch.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblBatch.setBackground(Color.LIGHT_GRAY);
		lblBatch.setBounds(56, 185, 90, 35);
		contentPane.add(lblBatch);
		
		JLabel batchValue = new JLabel((String) null);
		batchValue.setForeground(Color.WHITE);
		batchValue.setFont(new Font("Times New Roman", Font.BOLD, 16));
		batchValue.setBackground(Color.LIGHT_GRAY);
		batchValue.setBounds(156, 185, 461, 35);
		contentPane.add(batchValue);
		
		
		System.out.println("StudentScreen : " + name);
		FindIterable<Document> student = db_q.filter_by_name(name, db_table.original_table);
		System.out.println(((MongoCollection<Document>) student).count());
		@SuppressWarnings("unchecked")
		final DBObject ob = ((Iterator<DBObject>) student).next();
		label_3.setText(ob.get("Name").toString());
		label_4.setText(ob.get("College").toString());
		label_5.setText(ob.get("PRN").toString());
		lblNA.setText(ob.get("Branch").toString());
		batchValue.setText(ob.get("Batch").toString());
		
		JButton btnFirstYear = new JButton("First Year");
		
		btnFirstYear.setBackground(new Color(204, 204, 204));
		btnFirstYear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				IndividualScreen s = new IndividualScreen(ob, "F" );
				s.setVisible(true);
			}
		});
		btnFirstYear.setBounds(57, 255, 108, 30);
		btnFirstYear.setEnabled(false);
		contentPane.add(btnFirstYear);
		
		JButton btnSecondYear = new JButton("Second Year");
		btnSecondYear.setBackground(new Color(204, 204, 204));
		btnSecondYear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				IndividualScreen s = new IndividualScreen(ob, "S" );
				s.setVisible(true);
			}
		});
		btnSecondYear.setBounds(57, 305, 108, 30);
		btnSecondYear.setEnabled(false);
		contentPane.add(btnSecondYear);
		
		JButton btnThirdYear = new JButton("Third Year");
		btnThirdYear.setBackground(new Color(204, 204, 204));
		btnThirdYear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				IndividualScreen s = new IndividualScreen(ob, "T" );
				s.setVisible(true);
			}
		});
		btnThirdYear .setBounds(57, 355, 108, 30);
		btnThirdYear.setEnabled(false);
		contentPane.add(btnThirdYear );
		
		JButton btnFourthYear = new JButton("Fourth Year");
		btnFourthYear.setBackground(new Color(204, 204, 204));
		btnFourthYear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				IndividualScreen s = new IndividualScreen(ob, "B" );
				s.setVisible(true);
			}
		});
		btnFourthYear.setBounds(57, 405, 108, 30);
		btnFourthYear.setEnabled(false);
		contentPane.add(btnFourthYear);
		
		if(ob.get("F")!=null)
		{
			String marks_FE = (((DBObject)ob.get("F")).get("Scored")).toString();
			marks_FE = marks_FE.concat("/");
			marks_FE = marks_FE.concat((((DBObject)ob.get("F")).get("Total")).toString());
			lblMarksFE.setText(marks_FE);
			
			float perFE = Float.parseFloat((((DBObject)ob.get("F")).get("Scored")).toString())/Float.parseFloat((((DBObject)ob.get("F")).get("Total")).toString())*100;
			String PerFE = Float.toString(perFE);
			lblPercentageFE.setText(PerFE);
			btnFirstYear.setEnabled(true);
		}
		if(ob.get("S")!=null)
		{
			String marks_SE = (((DBObject)ob.get("S")).get("Scored")).toString();
			marks_SE = marks_SE.concat("/");
			marks_SE = marks_SE.concat((((DBObject)ob.get("S")).get("Total")).toString());
			lblMarksSE.setText(marks_SE);
			
			float perSE = Float.parseFloat((((DBObject)ob.get("S")).get("Scored")).toString())/Float.parseFloat((((DBObject)ob.get("S")).get("Total")).toString())*100;
			String PerSE = Float.toString(perSE);
			lblPecentageSE.setText(PerSE);
			btnSecondYear.setEnabled(true);
		}
		if(ob.get("T")!=null)
		{
			String marks_TE = (((DBObject)ob.get("T")).get("Scored")).toString();
			marks_TE = marks_TE.concat("/");
			marks_TE = marks_TE.concat((((DBObject)ob.get("T")).get("Total")).toString());
			lblMarksTE.setText(marks_TE);
			
			float perTE = Float.parseFloat((((DBObject)ob.get("T")).get("Scored")).toString())/Float.parseFloat((((DBObject)ob.get("T")).get("Total")).toString())*100;
			String PerTE = Float.toString(perTE);
			lblPecentageTE.setText(PerTE);
			btnThirdYear.setEnabled(true);			
		}
		if(ob.get("B")!=null)
		{
			String marks_BE = (((DBObject)ob.get("B")).get("Scored")).toString();
			marks_BE = marks_BE.concat("/");
			marks_BE = marks_BE.concat((((DBObject)ob.get("B")).get("Total")).toString());
			lblMarksBE.setText(marks_BE);
			
			float perBE = Float.parseFloat((((DBObject)ob.get("B")).get("Scored")).toString())/Float.parseFloat((((DBObject)ob.get("B")).get("Total")).toString())*100;
			String PerBE = Float.toString(perBE);
			lblPercentageBE.setText(PerBE);
			btnFourthYear.setEnabled(true);
		}
	}
}


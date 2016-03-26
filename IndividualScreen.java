import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;


public class IndividualScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel topPanel;
	private JScrollPane scrollPane;

	/**
	 * Create the frame.
	 */
	public IndividualScreen(DBObject obj, String year) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(10, 10, 745, 485);
		contentPane = new JPanel();
		contentPane.setForeground(Color.DARK_GRAY);
		contentPane.setBackground(new Color(51, 51, 51));
		contentPane.setBorder(UIManager.getBorder("FileChooser.listViewBorder"));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		//List<String[]> data = q_gui.filterByCriteria("College", "Branch", "Batch", "Year", db_table);
		
		topPanel = new JPanel();
		topPanel.setForeground(Color.DARK_GRAY);
		topPanel.setBackground(new Color(153, 153, 153));
		topPanel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		topPanel.setBounds(0, 114, 729, 332);
		topPanel.setLayout( new BorderLayout());
		
		String[] columnNames = {"Subject Name", "Scored", "Max. Marks", "Status"};
		/*String[][] data = {
				{"Maths", "10", "20", "P"},
				{"Science", "10", "20", "F"}
		};*/
		//Pattern p1 = Pattern.compile("(.*?),", Pattern.DOTALL);
		//Matcher m1 = p1.matcher(student_block);
		//m1.find();
		
		//List<String []> data_list;
		
		
		
		JLabel lblName = new JLabel("Name:");
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName.setBounds(10, 11, 81, 33);
		contentPane.add(lblName);
		
		JLabel lblRollNo = new JLabel("Roll No.:");
		lblRollNo.setForeground(new Color(255, 255, 255));
		lblRollNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRollNo.setBounds(387, 11, 81, 33);
		contentPane.add(lblRollNo);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setForeground(new Color(255, 255, 255));
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotal.setBounds(10, 55, 109, 33);
		contentPane.add(lblTotal);
		
		JLabel lblResult = new JLabel("Result:");
		lblResult.setForeground(new Color(255, 255, 255));
		lblResult.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblResult.setBounds(328, 55, 81, 33);
		contentPane.add(lblResult);
		
		JLabel label = new JLabel("");
		label.setForeground(new Color(255, 255, 255));
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		label.setBounds(75, 11, 243, 33);
		contentPane.add(label);
		
		JLabel lblGrand = new JLabel("NA");
		lblGrand.setForeground(new Color(255, 255, 255));
		lblGrand.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGrand.setBounds(85, 55, 233, 33);
		contentPane.add(lblGrand);
		
		JLabel lblRoll = new JLabel("NA");
		lblRoll.setForeground(new Color(255, 255, 255));
		lblRoll.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRoll.setBounds(501, 11, 313, 33);
		contentPane.add(lblRoll);
		
		JLabel lblNa = new JLabel("NA");
		lblNa.setForeground(new Color(255, 255, 255));
		lblNa.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNa.setBounds(395, 55, 324, 33);
		contentPane.add(lblNa);
		
		label.setText(obj.get("Name").toString());
		
		BasicDBObject ob = (BasicDBObject)obj.get(year);
		String marks_SE = (ob.get("Scored")).toString();
		marks_SE = marks_SE.concat("/");
		marks_SE = marks_SE.concat((ob.get("Total")).toString());
		lblGrand.setText(marks_SE);
		lblNa.setText(ob.getString("Result").toString());
		
		lblRoll.setText(ob.get("Roll").toString());
		Map m = ob.toMap();
		Set<String> sett = m.keySet();
		List<String []> list_marks = new ArrayList<String []>();
		//System.out.println(m.keySet());
		for(String s : sett){
			System.out.println(s);
			String a , b, c , d;
			if(s.equals("Roll") || s.equals("Total") || s.equals("Scored") || s.equals("Result") ) 
				;
			else {
				
				System.out.println(s + "Inside xxx : " + ob.get(s));
				BasicDBObject inner = (BasicDBObject)ob.get(s);
				a = s;
				b = inner.get("Scored").toString();
				if(Integer.parseInt(b) == -1)
					b = "Absent";
				c = inner.get("Total").toString();
				d = inner.get("Status").toString();
				String [] arr = {a,b,c,d};
				list_marks.add(arr);
			}
		}
		
		String[][] array = new String[list_marks.size()][];
		for (int i = 0; i < list_marks.size(); i++) {
		    String[] row = list_marks.get(i);
		    array[i] = row;
		}
		
		final JTable table = new JTable(array,columnNames);
		
		table.setRowHeight(35);
		table.setBounds(10, 127, 709, 308);
		table.setFont(new Font("Arial", Font.PLAIN, 13));
		table.setBounds(10, 127, 709, 308);
		table.setRowSelectionAllowed(true);
		table.setBackground(new Color(51, 51, 51));
		table.setForeground(Color.white);
		table.setSelectionForeground(Color.white);
		table.setSelectionBackground(Color.gray);
		table.getColumnModel().getColumn(0).setMaxWidth(75);
		table.getColumnModel().getColumn(0).setMaxWidth(300);
		table.getColumnModel().getColumn(0).setPreferredWidth(300);
		table.getColumnModel().getColumn(0).setMinWidth(300);
		
		scrollPane = table.createScrollPaneForTable(table);
		topPanel.add(scrollPane, BorderLayout.CENTER);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		contentPane.add(topPanel);
		
	}
}

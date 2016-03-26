import java.util.List;

import javax.swing.table.AbstractTableModel;


public class tableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
			private List<String[]> records;

		    public tableModel(List<String[]> records) {
		        this.records = records;
		    }
		    
		    public void addTheseRows(List<String[]> new_data) {
		    	for(int i = 0; i < new_data.size(); ++i) {
		    		//System.out.println(new_data.get(i));
		    		records.add(new_data.get(i));
		    		fireTableDataChanged();
		    	}
		    }
		    
		    public String getName(int row)
		    {
		    	return records.get(row)[1];
		    }
		    
		    public void deleteAllRows(){
		    	//System.out.println(this.getRowCount());
		    	for(int i = this.getRowCount()-1; i >= 0; --i ) {
		    		records.remove(i);
		    		fireTableRowsDeleted(i, i);
		    	}
		    	//System.out.println("after" + Integer.toString(this.getRowCount()));
		    }
		    
		  

		    public int getColumnCount() {
		        // return however many columns you want
		    	return 4;
		    }

		    public int getRowCount() {		        
		        return records.size();
		    }
		    

		    public String getColumnName(int columnIndex) {
		        switch (columnIndex) {
		        case 0: return "Sr. No.";
		        case 1: return "Name";
		        case 2: return "College";
		        case 3: return "Branch";
		        default : return "Column out of range";
		        // ...
		        }
		    }

		    public String getValueAt(int rowIndex, int columnIndex) {
		        String[] student_rec = records.get(rowIndex);
		       // System.out.println(student_rec[3]);
		        switch (columnIndex) {
		        case 0: return student_rec[0];
		        case 1: return student_rec[1];
		        case 2: return student_rec[2];
		        case 3: return student_rec[3];
		        default : return "Error";
		        // ...
		        }
		    }

		}

public class CreateTable {

	DB_Init cas_init;
	String col1, col2, col3, col4, col5, col6;
	public CreateTable(DB_Init init, String s1, String s2, String s3, String s4, String s5, String s6) {
		/*init.command = "CREATE TABLE students ( ";
		init.command.concat(s1);
		init.command.concat(" text, ");
		init.command.concat(s2);
		init.command.concat(" text, ");
		init.command.concat(s3);
		init.command.concat(" text, ");
		init.command.concat(s4);
		init.command.concat(" text, ");
		init.command.concat(s5);
		init.command.concat(" text, ");
		init.command.concat(s6);
		init.command.concat(" text );");
		*/
		
		col1 = s1;
		col2 = s2;
		col3 = s3;
		col4 = s4;
		col5 = s5;
		col6 = s6;
		cas_init = init;
		
		init.command = "CREATE TABLE students ( "+"id int PRIMARY KEY, "+s1+" text, "+s2+" text, "+s3+" text, "+s4+" text, "+s5+" text, "+s6+" text, total int, scored int );";
		
		System.out.println(init.command);
		
		init.session.execute(init.command);

	}
	
	public void insertData(int ctr, String s1, String s2, String s3, String s4, String s5) {
		cas_init.command = null;
		cas_init.command = "INSERT INTO students (id, "+col1+", "+col2+", "+col3+", "+col4+", "+col5+" ) VALUES ("+ctr+", '"+s1+"', '"+s2+"', '"+s3+"', '"+s4+"', '"+s5+"' );";
		System.out.println(cas_init.command);
		cas_init.session.execute(cas_init.command);
	}
	
	public void createSubTable() {
		cas_init.command = null;
		cas_init.command = "CREATE TABLE subjects ( uuid int PRIMARY KEY, stu_id int, subject text, total_marks int, marks_scored int, status text );";
		cas_init.session.execute(cas_init.command);
	}
	
	public void updateResult(int stu_id, int[] marks_array) {
		int total = marks_array[0];
		int scored = marks_array[1];
		cas_init.command = null;
		cas_init.command = "UPDATE students SET total = "+total+", scored = "+scored+" WHERE id = "+stu_id+";";
		cas_init.session.execute(cas_init.command);
	}
	
/*	public void alterSubTable(String sub) {
		cas_init.command = null;
		cas_init.command = "ALTER TABLE subjects ADD "+sub+" text;";
		cas_init.session.execute(cas_init.command);
	}
	
	public void updateSubTable(int f_key, String sub) {
		cas_init.command = null;
		cas_init.command = ""
	}*/
	
	public void insertSubTable(int uuid, int id, String sub, int[] total_array, char status) {
		int total = total_array[0];
		int scored = total_array[1];
		cas_init.command = null;
		cas_init.command = "INSERT INTO subjects ( uuid, stu_id, subject, total_marks, marks_scored, status ) VALUES ( "+uuid+", "+id+", '"+sub+"', "+total+", "+scored+", '"+status+"' );";
		cas_init.session.execute(cas_init.command);
	}
}

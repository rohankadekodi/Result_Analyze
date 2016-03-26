//If any problem of scanning occurs, check if STUDENT_BLOCK string is correctly being updated and used.
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class PDF_Extract {
	Matcher m, m_sub, m3;
	Pattern p3;
	PDDocument pdf;
	// String student_block;
	String marks;
	ArrayList<String> result_array = new ArrayList<>();
	int start_page, end_page;

	// Stringstudent_name, total_total, total_scored,
	// tmp,
	// sub_total, sub_scored, sub_name, sub_type, sub_name_final, college,
	// marks, sub_marks;

	// Load the pdf file into a pddocument
	public PDF_Extract(String file_name) {
		try {
			File input = new File(file_name);
			pdf = PDDocument.load(input);
			// start_page = 1;
			// end_page = 2;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Get complete data in a string builder object. Set regex matcher pattern
	// in "m".
	public StringBuilder extract_complete_data() {
		StringBuilder sb = new StringBuilder();
		try {
			PDFTextStripper stripper = new PDFTextStripper();
			// stripper.setStartPage(start_page);
			// stripper.setEndPage(end_page);

			sb.append(stripper.getText(pdf));

			Pattern p = Pattern.compile(
					"[A-Z][0-9]\\d+(.*?)(\\d+/((\\d{4})|(\\d{3})))",
					Pattern.DOTALL);
			m = p.matcher(sb);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb;
	}

	// Function for getting student roll number
	public String get_roll(String student_block) {
		// Regex for finding roll number
		Pattern p1 = Pattern.compile("\\w\\d+\\s{2}", Pattern.DOTALL);
		Matcher m1 = p1.matcher(student_block);
		m1.find();
		String roll_no = m1.group().trim();
		System.out.println(roll_no);
		return roll_no;
	}

	// Function for getting student PRN
	public String get_prn_number(String student_block) {
		Pattern p1 = Pattern.compile("\\d{8}[A-Z]\\s{3}", Pattern.DOTALL);
		Matcher m1 = p1.matcher(student_block);
		m1.find();
		System.out.println(m1.group().trim());
		return m1.group().trim();

	}

	// Function for finding college
	public String get_college(String student_block) {
		// Regex for finding college
		System.out.println("Inside get_college : \n" + student_block);
		Pattern p0 = Pattern.compile(
				"(,\\s*\\w{2}[A-Z](.*?),\\s*\\w\\d{7,9}\\s*)|(,\\s*\\w\\d{7,9}\\s*,\\s*\\w{2}[A-Z](.*?)\\s*,)|(,\\s{3}\\w*\\s{7},)", Pattern.DOTALL);
		Matcher m0 = p0.matcher(student_block);
		m0.find();
		String college = m0.group().trim();
		if(college.charAt(college.length()-1) == ','){
			college = college.substring(0,college.length()-1).trim();
			college = college.substring(college.lastIndexOf(",")+1).trim();
		}
		else
			college = college.substring(1, college.lastIndexOf(",")).trim();
		// System.out.println(college);
		System.out.println(college);
		return college;
	}

	// Function for finding name
	public String get_name(String student_block) {
		// Regex for finding name
		Pattern p2 = Pattern.compile("\\s{3}(.*?)\\s{2}", Pattern.DOTALL);
		Matcher m_sub = p2.matcher(student_block);
		m_sub.find();
		String student_name = m_sub.group();

		// Strip leading and trailing whitespace
		student_name = student_name.trim();
		System.out.println(student_name);
		return student_name;
	}

	// Returns a tuple of total_total and total_scored
	public int[] get_total_marks(String student_block) {
		int slash_pos = student_block.lastIndexOf("/");
		int space_pos = student_block.lastIndexOf(" ");
		int[] total_array = new int[2];

		// Total total (1400/700)
		total_array[0] = Integer.parseInt(student_block.substring(
				slash_pos + 1, student_block.length()));
		// Total scored
		/*String marks_temp = student_block.substring(space_pos + 1, slash_pos);
		if (marks_temp.contains("+")) {
			String marks_before_plus = marks_temp.substring(0,
					marks_temp.lastIndexOf("+"));
			String marks_after_plus = marks_temp.substring(
					marks_temp.lastIndexOf("+") + 1).substring(1);

			total_array[1] = Integer.parseInt(marks_before_plus)
					+ Integer.parseInt(marks_after_plus);
		} else
			total_array[1] = Integer.parseInt(marks_temp);*/
		if(student_block.contains("+")){
			String marks_temp = student_block.substring(student_block.lastIndexOf("=")+1,student_block.lastIndexOf("+")).trim();
			String rem_marks = student_block.substring(student_block.lastIndexOf("+")+1, student_block.lastIndexOf("/")).trim();
			total_array[1] = Integer.parseInt(marks_temp) + Integer.parseInt(rem_marks);
		}
		else{
			String marks_temp = student_block.substring(space_pos + 1, slash_pos);
			total_array[1] = Integer.parseInt(marks_temp);
		}
		return total_array;
	}

	// Function to find all subject names for a student
	public String get_sub_name(String tmp) {
		// Get subject name
		p3 = Pattern.compile("[A-Z](.*?)(PP|PR|TW|OR)\\s{2}");
		m3 = p3.matcher(tmp);
		m3.find();
		String sub_name = m3.group().trim();
		sub_name = sub_name.toString().trim().replace(".", " ");
		String sub_type = sub_name.substring(sub_name.length() - 2,
				sub_name.length());
		sub_name = (sub_name.substring(0, sub_name.length() - 2)).trim();
		String sub_name_final = sub_name + " (" + sub_type + ")";

		System.out.println("subnamefinal" + sub_name_final);
		return sub_name_final;
	}

	// Get subject marks
	public int[] get_sub_details(String tmp) {

		int[] sub_details_array = new int[2];

		p3 = Pattern.compile("\\s{2}.\\d{2}(.*?).\\s(P|F)");
		m3 = p3.matcher(tmp);
		m3.find();
		marks = m3.group().trim();
		marks = marks.toString().trim();

		String sub_marks = marks.substring(0, marks.length() - 3);
		String sub_total = sub_marks.substring(0, 3);
		sub_total = sub_total.trim();

		String sub_scored = sub_marks.substring(sub_marks.length() - 3,
				sub_marks.length());
		sub_scored = sub_scored.trim();

		 System.out.println("Scored : " + sub_scored);
		if (sub_scored.equalsIgnoreCase("AA") == true) {
			System.out.println("----------");
			sub_scored = "-1";
		}

		sub_details_array[0] = Integer.parseInt(sub_total);
		sub_details_array[1] = Integer.parseInt(sub_scored);

		return sub_details_array;
	}

	// Get subject status i.e. P for Pass, F for Fail, etc
	public char get_sub_status() {
		char sub_status = marks.charAt(marks.length() - 1);

		return sub_status;
	}

	public ArrayList<String> get_result_statement(StringBuilder sb) {
		// Get Result From Each Block And Add Into An ArrayList, Update DB
		Pattern p4 = Pattern
				.compile(
						"(/750)|((RESULT:)(.*?)\\.\\s\\.\\s\\.)|((RESULT: )((.*?)ORDN))|(/700)|((Result : )((.*?)\\s{5}))",
						Pattern.DOTALL);
		Matcher m4 = p4.matcher(sb);
		//System.out.println(sb);
	//	int cnt = 1;
		while (m4.find()) {
			String result = m4.group().trim();
			//System.out.println("Before : " + cnt + "   " + result + "  ---- END OF RECORD ---");
			//cnt++;
			/*if(cnt == 2){
				System.out.println("Exiting ... ");
				System.exit(-1);
			}*/
				
		//	cnt++;
			if (result.charAt(1) == '7') {
				//System.out.println("END");
				//System.exit(-1);
				result = "NA";
			} else {
				if (result.contains("ORDN")) {
					result = result.substring(8, result.lastIndexOf("O"))
							.trim();
				} else if(result.contains("Result"))
					result = result.substring(8).trim();
				else if(result.contains(". .")){
					result = result.substring(7,result.length()-4).trim();
				}
				// result = result.substring(8, result.length()-1).trim();
			}
			//System.out.println("Result is : " + result + "END OF RES!");
			// System.out.println("Adding : " + result);
			result_array.add(result);
			// System.out.println(result);
		}
		return result_array;
	}

	public void end_pddoc() {
		try {
			pdf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

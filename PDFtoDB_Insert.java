import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
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
public class PDFtoDB_Insert {
	// DB_Init init = new DB_Init();
	PDF_Extract get;
	DB_Insert put = new DB_Insert();

	int db_size;
	//ArrayList<Boolean> is_duplicate = new ArrayList<>();

	public PDFtoDB_Insert(String pdf_file) {
		get = new PDF_Extract(pdf_file);
	}

	@SuppressWarnings("unchecked")
	public void start_insert(DB_Init init, String batch, ArrayList<String> branchArray,ArrayList<String> rollStartArray,ArrayList<String> rollEndArray) {
		int flag=0, flagsub = 0;
		StringBuilder sb = get.extract_complete_data();
		CreateTable stuTab = null;

		FindIterable<Document> prev_count_cursor = init.original_table.find();
		//db_size = (int) ((MongoCollection<Document>) prev_count_cursor).count();
		db_size = (int) init.db.getCollection("students").count();
		ArrayList<String[]> prn_year_duplicate = new ArrayList<String[]>(); // Stores prn, year(F/S/T/B), bool value for result statement
		
		// System.out.println(db_size);

		// Iterate through each student block one by one
		int ctr = 1;
		int subctr = 1;
		while (get.m.find()) {
			Document document = new Document();
			BasicDBObject inner_doc = new BasicDBObject();
			String student_block = get.m.group().trim();
		//	System.out.println(student_block);
			
			put.put_name(get.get_name(student_block), document);
			put.put_college(get.get_college(student_block), document);
			put.put_roll(get.get_roll(student_block), inner_doc);
			put.put_prn_number(get.get_prn_number(student_block), document);
			put.put_batch(batch, document);
			System.out.println(branchArray);
			put.put_branch(get.get_roll(student_block),document,init,branchArray, rollStartArray, rollEndArray);
			
			if(flag == 0) {
				stuTab = new CreateTable(init, "name", "roll", "college", "prn", "year", "branch");
				stuTab.createSubTable();
				flag = 1;
			}
			
			stuTab.insertData(ctr, get.get_name(student_block), get.get_roll(student_block), get.get_college(student_block), get.get_prn_number(student_block), batch);
			
			// Subject regex
			Pattern p_sub = Pattern.compile("\\d{2,6}\\w?\\s?\\.?\\s\\w(.*?)\\s[P|F]\\s",
					Pattern.DOTALL);
			Matcher m_sub = p_sub.matcher(student_block);

			// Iterate over each subject and insert into db
			while (m_sub.find()) {
				BasicDBObject sub_doc = new BasicDBObject();
				String tmp;
				tmp = m_sub.group().trim();

				put.put_subject_details(get.get_sub_details(tmp), sub_doc);
				put.put_subject_status(get.get_sub_status(), sub_doc);
				inner_doc.put(get.get_sub_name(tmp), sub_doc);
				
				stuTab.insertSubTable(subctr, ctr, get.get_sub_name(tmp), get.get_sub_details(tmp), get.get_sub_status());
				subctr++;
			}

			put.put_total_marks(get.get_total_marks(student_block), inner_doc);

			stuTab.updateResult(ctr, get.get_total_marks(student_block));
			inner_doc.put("Result", " ");

			// Insert into db
			//System.out.println("START OF DOCCC" + inner_doc);
			prn_year_duplicate.add(new String [] {document.get("PRN").toString(),inner_doc.get("Roll").toString().substring(0,1),put.insert_and_update(document, inner_doc,init)});
			ctr++;
		}
		
		// Result Statement Code:
		ArrayList<String> result_array = get.get_result_statement(sb);
		int pos = 0;
		for(String[] s : prn_year_duplicate) {
			if(s[2].equals("f")){
				FindIterable<Document> orig_ob = init.original_table.find(new BasicDBObject("PRN",s[0]));
				String year = s[1];
				if(s[1].equals("E"))
					year = "F";
				BasicDBObject resultClass = new BasicDBObject();
				resultClass.append("$set", new BasicDBObject(year+".Result", result_array.get(pos)));
				init.original_table.updateOne(orig_ob.first(), resultClass);
			}
			pos++;
		}
	}
}
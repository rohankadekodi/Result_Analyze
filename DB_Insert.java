import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

public class DB_Insert {

	public void put_name(String name, Document document) {
		document.put("Name", name);
	}

	public void put_prn_number(String prn, Document document) {
		document.put("PRN", prn);
	}

	public void put_batch(String batch, Document document) {
		document.put("Batch", batch);
	}

	public void put_roll(String roll, BasicDBObject document) {
		document.put("Roll", roll);
	}

	public void put_college(String college, Document document) {
		document.put("College", college);
	}

	public void put_branch(String roll, Document document, DB_Init db_table,
			ArrayList<String> branchArray, ArrayList<String> rollStartArray,
			ArrayList<String> rollEndArray) {
		String first_char = roll.substring(0, 1);
		System.out.println("Outside branch insertion module ");
		for (int i = 0; i < branchArray.size(); ++i) {
			String startRoll = rollStartArray.get(i);
			String endRoll = rollEndArray.get(i);
			System.out.println("startROll : " + startRoll + " endROll : "
					+ endRoll + "first_char : " + first_char + "   Roll: "
					+ roll);
			System.out.println(document);
			if (first_char.equals(startRoll.substring(0, 1))) {
				String temp_roll, temp_startRoll, temp_endRoll;
				temp_startRoll = startRoll.substring(1);
				temp_endRoll = endRoll.substring(1);
				temp_roll = roll.substring(1);
				long start = Integer.parseInt(temp_startRoll), end = Integer
						.parseInt(temp_endRoll), current_roll = Integer
						.parseInt(temp_roll);
				if (current_roll >= start && current_roll <= end) {
					if (document.containsKey("Branch") == true) {
						System.out.println("START OF DOC" + document);
						String id = document.get("_id").toString();
						BasicDBObject doc2  = new BasicDBObject();
						doc2.put("Branch", branchArray.get(i));
						db_table.original_table.updateOne(new BasicDBObject("_id", id), doc2);
					}
					document.put("Branch", branchArray.get(i));
					System.out.println("Inside branch insertion module "
							+ branchArray.get(i));
				}
			}
		}
	}

	public void put_total_marks(int[] total_array, BasicDBObject document) {
		document.put("Total", total_array[0]);
		document.put("Scored", total_array[1]);
	}

	public void put_subject_details(int[] sub_details_array,
			BasicDBObject sub_doc) {
		sub_doc.put("Total", sub_details_array[0]);
		sub_doc.put("Scored", sub_details_array[1]);
	}

	public void put_subject_status(char status, BasicDBObject sub_doc) {
		sub_doc.put("Status", status);
	}

	// Insert academic record into main if it isn't a duplicate and then insert
	// into db
	// Returns false if an entry/update is performed
	@SuppressWarnings("unchecked")
	public String insert_and_update(Document main, BasicDBObject acad_record, DB_Init db_table) {
		System.out.println("PRN = " + main.get("PRN"));
		System.out.println("Roll no = " + acad_record.get("Roll"));
		BasicDBObject query = new BasicDBObject("PRN", main.get("PRN"));
		//MongoCollection<Document> db = db_table.original_table;
		FindIterable<Document> query_prn = db_table.db.getCollection("students").find(query);
		String roll = (String) acad_record.get("Roll");
		String year = String.valueOf(roll.charAt(0));
		if (year.equals("E") == true)
			year = "F";
		//System.out.println(acad_record);
		// If the PRN doesn't exist add a new entry
		if (query_prn.first() == null) {
			main.put(year, acad_record);
			//db_table.db.getCollection("students").insertMany((List<? extends Document>) main);
			db_table.db.getCollection("students").insertOne(main);
			return "f";
		} else {
			System.out.println("Inside ELSE, not INSERTED");
			// Check to see if the entry for this academic year exists
			// If it doesn't exist , add
			if (query_prn.first().containsKey(year) == false) {
				query_prn.first().put(year, acad_record);
				// Change branch to new branch if earlier branch is FE
				if(query_prn.first().get("Branch").toString().equals("F.E. Common"))
					query_prn.first().put("Branch", main.get("Branch").toString());
				//((DBCollection) db).save(query_prn);
				return "f";
			} else
				return "t";	// not added anything
		}
	}
}
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class Queries_GUI {
	static DB_Queries q = new DB_Queries();
	FindIterable<Document> db_cursor;

	@SuppressWarnings("unchecked")
	String[] collegeList(DB_Init db_table) {
		ArrayList<String> colleges = new ArrayList<String>();
		colleges.add("College");
		db_cursor = db_table.original_table.find();
		try {
			while (db_cursor.iterator().hasNext()) {
				DBObject ob = ((Iterator<DBObject>) db_cursor).next();
				String coll = ob.get("College").toString();
				if (colleges.contains(coll) == false)
					colleges.add(coll);
			}
		} finally {
		}

		String[] colleges_string_arr = new String[colleges.size()];
		colleges_string_arr = colleges.toArray(colleges_string_arr);

		return colleges_string_arr;
	}

	@SuppressWarnings("unchecked")
	String[] branchList(DB_Init db_table) {
		ArrayList<String> branch = new ArrayList<String>();
		branch.add("Branch");
		db_cursor = db_table.original_table.find();
		try {
			while (db_cursor.iterator().hasNext()) {
				DBObject ob = ((Iterator<DBObject>) db_cursor).next();
				String bran = ob.get("Branch").toString();
				if (branch.contains(bran) == false)
					branch.add(bran);
			}
		} finally {
		}

		String[] branch_string_arr = new String[branch.size()];
		branch_string_arr = branch.toArray(branch_string_arr);

		return branch_string_arr;
	}

	String[] batchList(DB_Init db_table) {
		ArrayList<String> batch = new ArrayList<String>();
		batch.add("Batch");
		db_cursor = db_table.original_table.find();
		try {
			while (db_cursor.iterator().hasNext()) {
				Document ob = db_cursor.iterator().next();
				String coll = ob.get("Batch").toString();
				if (batch.contains(coll) == false)
					batch.add(coll);
			}
		} finally {
		}

		String[] batch_string_arr = new String[batch.size()];
		batch_string_arr = batch.toArray(batch_string_arr);

		return batch_string_arr;
	}

	@SuppressWarnings("unchecked")
	List<String[]> searchByName(String name, DB_Init db_table) {
		List<String[]> records = new ArrayList<String[]>();
		db_cursor = q.filter_by_name(name, db_table.filtered_collection);
		//db_table.filtered_collection = (MongoCollection<Document>) ((DBCursor) db_cursor).getCollection();

		int counter = 1;
		try {
			while (((Iterator<DBObject>) db_cursor).hasNext()) {
				DBObject ob = ((Iterator<DBObject>) db_cursor).next();
				// System.out.println(ob);
				records.add(new String[] { Integer.toString(counter),
						ob.get("Name").toString(),
						ob.get("College").toString(),
						ob.get("Branch").toString() });
				counter++;
			}
		} finally {
			((Cursor) db_cursor).close();
		}

		return records;
	}

	@SuppressWarnings("unchecked")
	List<String[]> searchByRoll(String roll, DB_Init db_table) {
		List<String[]> records = new ArrayList<String[]>();
		db_cursor = q.filter_by_roll(roll, db_table.filtered_collection);
		//db_table.filtered_collection = (MongoCollection<Document>) db_cursor.getCollection();

		int counter = 1;
		try {
			while (((Iterator<DBObject>) db_cursor).hasNext()) {
				DBObject ob = ((Iterator<DBObject>) db_cursor).next();
				System.out.println(ob.get(roll.substring(0, 1)));
				// records.add(new String[] {Integer.toString(counter),
				// ((BasicDBObject)ob.get(roll.substring(0,
				// 1))).get("Roll").toString(), ob.get("College").toString()});
				records.add(new String[] { Integer.toString(counter),
						ob.get("Name").toString(),
						ob.get("College").toString(),
						ob.get("Branch").toString() });
				counter++;
			}
		} finally {
			((Cursor) db_cursor).close();
		}

		return records;
	}

	@SuppressWarnings("unchecked")
	List<String[]> filterByCriteria(String college, String branch,
			String batch, String year, DB_Init db_table) {
		List<String[]> records = new ArrayList<String[]>();
		db_cursor = db_table.original_table.find(); // Default table
		db_table.filtered_collection.drop();
		System.out.println(college + " " + year);

		@SuppressWarnings("unchecked")
		MongoCursor<Document> db_list_main =  db_cursor.iterator();
		while(db_list_main.hasNext())
			db_table.filtered_collection.insertOne((Document) db_list_main.next());

		// Filters
		if (college != "College") {
			System.out.println("Inside filter by colg");
			@SuppressWarnings("unchecked")
			List<DBObject> db_list = (List<DBObject>) q.filter_by_college(college,
					db_table.original_table).iterator();
			System.out.println(db_list.size() + " ---  ");
			db_table.filtered_collection.drop();
			System.out.println(db_list.size());

			/*
			 * try{ while(db_cursor.hasNext()){
			 * db_table.filtered_collection.insert(db_cursor.next()); } }
			 * finally { db_cursor.close(); }
			 * System.out.println("After year filter : " +
			 * Long.toString(db_table.filtered_collection.count()));
			 */
			for (int i = 0; i < db_list.size(); ++i)
				db_table.filtered_collection.insertOne((Document) db_list.get(i));
			System.out.println("After college filter : "
					+ Long.toString(db_table.filtered_collection.count()));
		}
		if (branch != "Branch") {
			List<DBObject> db_list = (List<DBObject>) q.filter_by_branch(branch,
					db_table.filtered_collection).iterator();
			System.out.println(((MongoCollection<Document>) db_cursor).count() + " ---  ");
			db_table.filtered_collection.drop();
			System.out.println(db_list.size());

			for (int i = 0; i < db_list.size(); ++i)
				db_table.filtered_collection.insertOne((Document) db_list.get(i));
		}
		if (batch != "Batch") {

			List<DBObject> db_list = (List<DBObject>) q.filter_by_batch(batch,
					db_table.filtered_collection).iterator();
			System.out.println(((MongoCollection<Document>) db_cursor).count() + " ---  ");
			db_table.filtered_collection.drop();
			System.out.println(db_list.size());

			for (int i = 0; i < db_list.size(); ++i)
				db_table.filtered_collection.insertOne((Document) db_list.get(i));
		}
		if (year != "Year") {
			System.out.println(year);
			year = year.substring(0, 1);
			System.out.println("Before year filter : "
					+ Long.toString(db_table.filtered_collection.count()));
			List<DBObject> db_list = (List<DBObject>) q.filter_by_year(year,
					db_table.filtered_collection).iterator();
			System.out.println(((MongoCollection<Document>) db_cursor).count() + " ---  ");
			db_table.filtered_collection.drop();
			System.out.println(db_list.size());

			for (int i = 0; i < db_list.size(); ++i)
				db_table.filtered_collection.insertOne((Document) db_list.get(i));
			// System.out.println(db_cursor.count());
		}

		int counter = 1;
		db_cursor = db_table.filtered_collection.find();
		try {
			while (db_cursor.iterator().hasNext()) {
				Document ob = db_cursor.iterator().next();
				// System.out.println(ob);
				records.add(new String[] { Integer.toString(counter),
						ob.get("Name").toString(),
						ob.get("College").toString(),
						//ob.get("Branch").toString()
						});
				counter++;
			}
		} finally {
		}

		/*
		 * for(int i = 0; i < records.size(); ++i) {
		 * System.out.println(records.get(i)[1]); }
		 */
		System.out.println("Size after filter "
				+ Integer.toString(records.size()));
		return records;
	}

	@SuppressWarnings("unchecked")
	int[] filterByCriteriaAndAggregate(String college, String branch,
			String batch, String year, DB_Init db_table) {
		List<String[]> records = new ArrayList<String[]>();
		db_cursor = db_table.original_table.find(); // Default table
		db_table.filtered_collection.drop();
		// System.out.println(college + " " + year);

		@SuppressWarnings("unchecked")
		List<DBObject> db_list_main = (List<DBObject>) db_cursor.iterator();
		for (int i = 0; i < db_list_main.size(); ++i)
			db_table.filtered_collection.insertOne((Document) db_list_main.get(i));

		// Filters
		if (college != "College") {
			System.out.println("Inside filter by colg");
			@SuppressWarnings("unchecked")
			List<DBObject> db_list = (List<DBObject>) q.filter_by_college(college,
					db_table.original_table).iterator();
			System.out.println(db_list.size() + " ---  ");
			db_table.filtered_collection.drop();
			System.out.println(db_list.size());

			/*
			 * try{ while(db_cursor.hasNext()){
			 * db_table.filtered_collection.insert(db_cursor.next()); } }
			 * finally { db_cursor.close(); }
			 * System.out.println("After year filter : " +
			 * Long.toString(db_table.filtered_collection.count()));
			 */
			for (int i = 0; i < db_list.size(); ++i)
				db_table.filtered_collection.insertOne((Document) db_list.get(i));
			System.out.println("After college filter : "
					+ Long.toString(db_table.filtered_collection.count()));
		}
		if (branch != "Branch") {
			@SuppressWarnings("unchecked")
			List<DBObject> db_list = (List<DBObject>) q.filter_by_branch(branch,
					db_table.filtered_collection).iterator();
			System.out.println(((MongoCollection<Document>) db_cursor).count() + " ---  ");
			db_table.filtered_collection.drop();
			System.out.println(db_list.size());

			for (int i = 0; i < db_list.size(); ++i)
				db_table.filtered_collection.insertOne((Document) db_list.get(i));
		}
		if (batch != "Batch") {

			List<DBObject> db_list = (List<DBObject>) q.filter_by_batch(batch,
					db_table.filtered_collection).iterator();
			System.out.println(((MongoCollection<Document>) db_cursor).count() + " ---  ");
			db_table.filtered_collection.drop();
			System.out.println(db_list.size());

			for (int i = 0; i < db_list.size(); ++i)
				db_table.filtered_collection.insertOne((Document) db_list.get(i));
		}
		if (year != "Year") {
			// System.out.println(year);
			year = year.substring(0, 1);
			// System.out.println("Before year filter : " +
			// Long.toString(db_table.filtered_collection.count()));
			List<DBObject> db_list = (List<DBObject>) q.filter_by_year(year,
					db_table.filtered_collection).iterator();
			// System.out.println(db_cursor.count()+ " ---  ");
			db_table.filtered_collection.drop();
			// System.out.println(db_list.size());

			for (int i = 0; i < db_list.size(); ++i)
				db_table.filtered_collection.insertOne((Document) db_list.get(i));
			// System.out.println(db_cursor.count());
		}

		db_cursor = db_table.filtered_collection.find();
		try {
			while (((Iterator<DBObject>) db_cursor).hasNext()) {
				DBObject ob = ((Iterator<DBObject>) db_cursor).next();
				// System.out.println(ob);
				if (year != "Year") {
					BasicDBObject obj = (BasicDBObject) ob.get(year);
					records.add(new String[] { obj.get("Result").toString() });
					// System.out.println(obj.get("Result").toString());
				}
				else
				{
					records.add(new String[] { "Please Select Year!" });
				}
			}
		} finally {
			((Cursor) db_cursor).close();
		}

		int c1 = 0, c2 = 0, c3 = 0, c4 = 0, c5 = 0, c6 = 0, c7 = 0, c8 = 0, c9 = 0, c10 = 0;

		for (int i = 0; i < records.size(); ++i) {
			if (records.get(i)[0].toString().contains("DISTINCTION"))
				c1++;
			else if (records.get(i)[0].toString().contains("FIRST"))
				c2++;
			else if (records.get(i)[0].toString().contains("HIGHER"))
				c3++;
			else if (records.get(i)[0].toString().contains("SECOND"))
				c4++;
			else if (records.get(i)[0].toString().contains("PASS"))
				c5++;
			else if (records.get(i)[0].toString().contains("A.T.K.T"))
				c6++;
			else if (records.get(i)[0].toString().contains("FAIL"))
				c7++;
			else if (records.get(i)[0].toString().contains("NA"))
				c8++;
			else
				c9++;
		}
		c10 = c1 + c2 + c3 + c4 + c5 + c6 + c7 + c8 + c9;
		int[] c = { c1, c2, c3, c4, c5, c6, c7, c8, c9, c10 };
		// System.out.println("-->" + c1 + "-" + c2 + "-" + c3 + "-" + c4 + "-"
		// + c5 + "-" + c6 + "-" + c7 + "-" + c8 + "-" + c9 + "-" + c10);

		System.out.println("Size after filter "
				+ Integer.toString(records.size()));
		return c;
	}
}
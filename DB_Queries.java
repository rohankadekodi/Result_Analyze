import java.util.regex.Pattern;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

// year = 'F'/'S'/'T'/'B'
public class DB_Queries {

	DB_Init init = new DB_Init();

	public DBCursor filter_by_prn(String prn, DBCollection dbc) {
		return dbc.find(new BasicDBObject("PRN", prn));
	}

	public FindIterable<Document> filter_by_name(String name, MongoCollection<Document> filtered_collection) {
		return filtered_collection.find(new BasicDBObject("Name", java.util.regex.Pattern
				.compile("(.*?)" + name + "(.*?)", Pattern.CASE_INSENSITIVE)));
	}

	public FindIterable<Document> filter_by_roll(String roll, MongoCollection<Document> filtered_collection) {
		roll = roll.toUpperCase();
		return filtered_collection.find(new BasicDBObject(roll.charAt(0) + ".Roll", roll));
	}

	public FindIterable<Document> filter_by_branch(String branch, MongoCollection<Document> filtered_collection) {
		return filtered_collection.find(new BasicDBObject("Branch", branch));
	}

	public DBCursor filter_by_class(char year, String result_class,
			DBCollection dbc) {
		return dbc.find(new BasicDBObject(year + ".Result", result_class));

	}

	public FindIterable<Document> filter_by_college(String college, MongoCollection<Document> original_table) {
		// return dbc.find(new
		// BasicDBObject("College",java.util.regex.Pattern.compile("(.*?)"+college+"(.*?)",
		// Pattern.CASE_INSENSITIVE)));
		return original_table.find(new BasicDBObject("College", college));
	}

	public FindIterable<Document> filter_by_year(String year, MongoCollection<Document> filtered_collection) {
		DBObject query = new BasicDBObject(year, new BasicDBObject("$exists",
				true));
		FindIterable<Document> cursor = filtered_collection.find((Bson) query);
		System.out.println("Inside filter_by_year : "
				+ Integer.toString((int) ((DBCollection) cursor).count()));
		return cursor;
	}

	public FindIterable<Document> filter_by_batch(String batch, MongoCollection<Document> filtered_collection) {
		return filtered_collection.find(new BasicDBObject("Batch", batch));
	}

	public DBCursor sort_by_name(DBCollection dbc) {
		return dbc.find().sort(new BasicDBObject("Name", 1));
	}

	public DBCursor sort_by_college(DBCollection dbc) {
		return dbc.find().sort(new BasicDBObject("College", 1));
	}

	public DBCursor sort_by_roll(DBCollection dbc, char year) {
		return dbc.find().sort(new BasicDBObject(year + ".Roll", 1));
	}

	public DBCursor sort_by_scored(DBCollection dbc, char year) {
		return dbc.find().sort(new BasicDBObject(year + ".Scored", -1));
	}

	public DBCursor sort_by_subject(String sub_name, char year, DBCollection dbc) {
		return dbc.find().sort(
				new BasicDBObject(year + "." + sub_name + "." + "Scored", -1));
	}
}
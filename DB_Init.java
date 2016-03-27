import org.bson.Document;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DB_Init {

	/*MongoClient mongo;
	MongoDatabase db;
	MongoCollection<Document> original_table, filtered_collection;
	public DB_Init() {
		System.out.println("In db init");
		mongo = new MongoClient();
		System.out.println("After connection");
		db = mongo.getDatabase("testdb");

		//db = mongo.getDB("testdb");
		original_table = db.getCollection("students");
		filtered_collection = db.getCollection("filtered");
		System.out.println("exiting db init");
	}*/
	
	Cluster cluster;
	Session session;
	String command;
	MongoClient mongo;
	MongoDatabase db;
	MongoCollection<Document> original_table, filtered_collection;
	public DB_Init() {
		System.out.println("In db init");
		mongo = new MongoClient();
		cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
		session = cluster.connect("test");
		db = mongo.getDatabase("testdb");
		//db = mongo.getDB("testdb");
		original_table = db.getCollection("students");
		filtered_collection = db.getCollection("filtered");
		System.out.println("exiting db init");
	}
	

	
	
}
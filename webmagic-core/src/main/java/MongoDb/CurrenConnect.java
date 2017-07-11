package MongoDb;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import MongoDb.Connect;

public abstract class CurrenConnect {

	 protected MongoCollection<Document> collection;
	
	 private MongoDatabase database;
	 
	 protected Document document;
	 
	public CurrenConnect(String db) {
		database=Connect.getInstance();
		// TODO Auto-generated constructor stub
		collection =database.getCollection(db);

	}
}

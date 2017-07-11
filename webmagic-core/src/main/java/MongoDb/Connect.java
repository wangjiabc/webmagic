package MongoDb;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

public class Connect {

	private static MongoDatabase database;
	
	private Connect() {
		// TODO Auto-generated constructor stub
	}	

	public static MongoDatabase getInstance(){
	 if(database==null){
		//连接数据库 start
        MongoCredential credential = MongoCredential.createCredential("tonny", "mydb", "123".toCharArray());
            ServerAddress serverAddress;
        serverAddress = new ServerAddress("127.0.0.1", 27017);
        List<ServerAddress> addrs = new ArrayList<ServerAddress>();
        addrs.add(serverAddress);
        List<MongoCredential> credentials = new ArrayList<MongoCredential>();
        credentials.add(credential);

        MongoClient mongoClient = new MongoClient(addrs, credentials);
        database = mongoClient.getDatabase("mydb");
        System.out.println("database="+database);
        System.out.println("Connect to database successfully");
        return database;
	   }else{
		   return database;
	   }
	}
}

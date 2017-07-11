package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.bson.Document;
import org.junit.Test;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.MongoOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import Model.AssortDocument;
import MongoDb.CurrenConnect;


public class TestMongodb extends CurrenConnect{

	private static final String db="col";
	
	public TestMongodb() {
		super(db);
		// TODO Auto-generated constructor stub
	}

	@Test   
	    public void testMongodb()  
	    {  
		  MongoDatabase mongoDatabase=null;
		  try {  

			  
	            AssortDocument assortDocument=new AssortDocument();
	            
	            assortDocument.setTitle("bbb");
	            assortDocument.setContent("dsafffffffffffffffff");
	            assortDocument.setDate(new Date().toString());
	            
	            collection.insertOne(assortDocument.getDocument());
	                    System.out.println("文档插入成功");  
	            //连接数据库 end
	        } catch (Exception e) {  
	            System.err.println( e.getClass().getName() + ": " + e.getMessage() );  
	        }  
		/*  try{   
		      // 连接到 mongodb 服务
		      MongoClient mongoClient = new MongoClient( "192.168.10.100" , 27017 );
		         
		       
		      // 连接到数据库
		      System.out.println("Connect to database successfully");
		      mongoDatabase.createCollection("test");
		      System.out.println("集合创建成功");
		        
		      }catch(Exception e){
		        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		     }*/
		   }
	   
}
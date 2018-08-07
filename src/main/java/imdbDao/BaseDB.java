package imdbDao;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoDatabase;

public class BaseDB {
	public MongoDatabase getDB() {
		MongoClient mongoclient = new MongoClient("localhost", 27017);
		MongoCredential credential;
		credential = MongoCredential.createCredential("root", "imdb", "root".toCharArray());
		System.out.println("Connected to the database successfully");
		MongoDatabase database = mongoclient.getDatabase("imdb");
		return database;
	}

}

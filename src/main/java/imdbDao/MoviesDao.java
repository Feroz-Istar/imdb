package imdbDao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoDatabase;

import constants.CollectionName;
import daoimpl.DaoImpl;

public class MoviesDao extends BaseDB implements DaoImpl{
	public static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		
	public void insert(Object obj) {
		// TODO Auto-generated method stub
		MongoDatabase mongoDatabase =  getDB();
		Document doc = Document.parse(gson.toJson(obj));
		mongoDatabase.getCollection(CollectionName.MOVIE_COLLECTION).insertOne(doc);
	}

	public void insertMany(List<Object> objlist) {
		// TODO Auto-generated method stub
		MongoDatabase mongoDatabase =  getDB();
		List<Document> docs = new ArrayList<Document>();

		for(Object obj: objlist) {
			 Document doc = Document.parse(gson.toJson(obj));
			 docs.add(doc);
		}
		mongoDatabase.getCollection(CollectionName.MOVIE_COLLECTION).insertMany(docs);
	}

}

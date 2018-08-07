package imdbDao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import constants.CollectionName;
import daoimpl.DaoImpl;
import imdb.Roles;

public class RolesDao  extends BaseDB implements DaoImpl{
	public static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		
	public void insert(Object obj) {
		// TODO Auto-generated method stub
		MongoDatabase mongoDatabase =  getDB();
		Document doc = Document.parse(gson.toJson(obj));
		mongoDatabase.getCollection(CollectionName.ROLE_COLLECTION).insertOne(doc);
	}

	public void insertMany(List<Object> objlist) {
		// TODO Auto-generated method stub
		MongoDatabase mongoDatabase =  getDB();
		List<Document> docs = new ArrayList<Document>();

		for(Object obj: objlist) {
			 Document doc = Document.parse(gson.toJson(obj));
			 docs.add(doc);
		}
		mongoDatabase.getCollection(CollectionName.ROLE_COLLECTION).insertMany(docs);
	}

	
	public Roles find(String key, Object value) {
			Roles roles = new Roles();
			MongoDatabase db = getDB();
			BasicDBObject query = new BasicDBObject();
			query.put(key, value);
			
			//Long start_time = System.currentTimeMillis();
			FindIterable<Document> filter = db.getCollection("role_collection").find(query);
			MongoCursor<Document> cursor = filter.iterator();
			try {
				String obj = cursor.next().toJson();
				System.out.println(obj);
				roles = gson.fromJson(obj, Roles.class); 
			} catch (JsonSyntaxException jse) {
				jse.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				cursor.close();
			}
			
			//System.out.println(start_time - System.currentTimeMillis());
			return roles;

		}
	}		
	

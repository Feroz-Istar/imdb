package imdbDao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import constants.CollectionName;
import daoimpl.DaoImpl;
import imdb.Movies;
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
			CollectionName collectionName = new CollectionName();
			
			query.put(key, value);
			
			//Long start_time = System.currentTimeMillis();
			FindIterable<Document> filter = db.getCollection(collectionName.ROLE_COLLECTION).find(query);
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
	
	public List<Roles> findAll() {
		List<Roles> roles = new ArrayList<>();
		MongoDatabase db = getDB();
		CollectionName collectionName = new CollectionName();
		MongoCollection<Document> mongo_collection = db.getCollection(collectionName.ROLE_COLLECTION);
		FindIterable<Document> filter = mongo_collection.find().limit(300);
		MongoCursor<Document> cursor = filter.iterator();

		try {
			while (cursor.hasNext()) {
				// System.out.println(cursor.next().toJson());
				String obj = cursor.next().toJson();
				System.out.println(obj);
				Roles role = gson.fromJson(obj, Roles.class);
				roles.add(role);

			}
		} catch (JsonSyntaxException jse) {
			jse.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();

		}
		return roles;
	}
	
	public List<Roles> sortBy(String key) {
		List<Roles> roles = new ArrayList<>();
		CollectionName collectionName = new CollectionName();
		MongoDatabase db = getDB();
		MongoCollection<Document> mongo_collection = db.getCollection(collectionName.ROLE_COLLECTION);
		FindIterable<Document> filter = mongo_collection.find().sort(new BasicDBObject(key, 1)).limit(300);
		MongoCursor<Document> cursor = filter.iterator();

		try {
			while (cursor.hasNext()) {
				// System.out.println(cursor.next().toJson());
				String obj = cursor.next().toJson();
				System.out.println(obj);
				Roles role = gson.fromJson(obj, Roles.class);
				roles.add(role);

			}
		} catch (JsonSyntaxException jse) {
			jse.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();

		}
		return roles;
	}

	public List<Roles> findLike(String key,Object value){
		List<Roles> roles = new ArrayList<>();
		MongoDatabase db = getDB();
		CollectionName collectionName = new CollectionName();
		BasicDBObject regexQuery = new BasicDBObject();
		regexQuery.put(key, 
			new BasicDBObject("$regex", value)
			.append("$options", "i"));
				
		System.out.println(regexQuery.toString());
		
		FindIterable<Document> filter = db.getCollection(collectionName.ROLE_COLLECTION).find(regexQuery).limit(300);
		MongoCursor<Document> cursor = filter.iterator();
		try {
			while (cursor.hasNext()) {
				String obj = cursor.next().toJson();
				System.out.println(obj);
				Roles role = gson.fromJson(obj, Roles.class);
				roles.add(role);
			}
		} catch (JsonSyntaxException jse) {
			jse.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();

		}
		return roles;
	}

	
}		
	

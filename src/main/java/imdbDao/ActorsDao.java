package imdbDao;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.BSONObject;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;

import constants.CollectionName;
import daoimpl.DaoImpl;
import imdb.Actors;
import imdb.Directors;


public class ActorsDao extends BaseDB /*implements DaoImpl*/ {
	public static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	
	
	public void insert(Object obj) {
		// TODO Auto-generated method stub
		MongoDatabase mongoDatabase =  getDB();
		Document doc = Document.parse(gson.toJson(obj));
		mongoDatabase.getCollection(CollectionName.ACTOR_COLLECTION).insertOne(doc);
	}


	public void insertMany(List<Object> objlist) {
		// TODO Auto-generated method stub
		MongoDatabase mongoDatabase =  getDB();
		List<Document> docs = new ArrayList<Document>();

		for(Object obj: objlist) {
			 Document doc = Document.parse(gson.toJson(obj));
			 docs.add(doc);
		}
		mongoDatabase.getCollection(CollectionName.ACTOR_COLLECTION).insertMany(docs);
	}
	
	public Actors find(String key,Object value){
		Actors actor = new Actors();
		MongoDatabase db = getDB();
		BasicDBObject regexQuery = new BasicDBObject();
		regexQuery.put(key, value);
				
		System.out.println(regexQuery.toString());
		
		FindIterable<Document> filter = db.getCollection(CollectionName.ACTOR_COLLECTION).find(regexQuery);
		MongoCursor<Document> cursor = filter.iterator();
		try {
			while (cursor.hasNext()) {
				String obj = cursor.next().toJson();
				//System.out.println(obj);
				 actor = gson.fromJson(obj, Actors.class);
			}
		} catch (JsonSyntaxException jse) {
			jse.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();

		}
		return actor;
	}
	
	
	public List<Actors> findAll() {
		List<Actors> actors = new ArrayList<>();
		MongoDatabase db = getDB();
	//	CollectionName collectionName = new CollectionName();
		MongoCollection<Document> mongo_collection = db.getCollection(CollectionName.ACTOR_COLLECTION);
		FindIterable<Document> filter = mongo_collection.find().limit(300);
		MongoCursor<Document> cursor = filter.iterator();

		try {
			while (cursor.hasNext()) {
				// System.out.println(cursor.next().toJson());
				String obj = cursor.next().toJson();
				System.out.println(obj);
				Actors actor = gson.fromJson(obj, Actors.class);
				actors.add(actor);

			}
		} catch (JsonSyntaxException jse) {
			jse.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();

		}
		return actors;
	}
	
	public List<Actors> sortBy(String key) {
		List<Actors> actors = new ArrayList<>();
	//	CollectionName collectionName = new CollectionName();
		MongoDatabase db = getDB();
		MongoCollection<Document> mongo_collection = db.getCollection(CollectionName.ACTOR_COLLECTION);
		FindIterable<Document> filter = mongo_collection.find().sort(new BasicDBObject(key, 1)).limit(300);
		MongoCursor<Document> cursor = filter.iterator();

		try {
			while (cursor.hasNext()) {
				// System.out.println(cursor.next().toJson());
				String obj = cursor.next().toJson();
				System.out.println(obj);
				Actors actor = gson.fromJson(obj, Actors.class);
				actors.add(actor);

			}
		} catch (JsonSyntaxException jse) {
			jse.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();

		}
		return actors;
	}

	public List<Actors> findLike(String key,Object value){
		List<Actors> actors = new ArrayList<>();
		MongoDatabase db = getDB();
	//	CollectionName collectionName = new CollectionName();
		BasicDBObject regexQuery = new BasicDBObject();
		regexQuery.put(key, 
			new BasicDBObject("$regex", value)
			.append("$options", "i"));
				
		System.out.println(regexQuery.toString());
		
		FindIterable<Document> filter = db.getCollection(CollectionName.ACTOR_COLLECTION).find(regexQuery).limit(300);
		MongoCursor<Document> cursor = filter.iterator();
		try {
			while (cursor.hasNext()) {
				String obj = cursor.next().toJson();
				System.out.println(obj);
				Actors actor = gson.fromJson(obj, Actors.class);
				actors.add(actor);
			}
		} catch (JsonSyntaxException jse) {
			jse.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();

		}
		return actors;
	}
	
	public List<Actors> findAndSort(String key1,String key2, Object value1){
		List<Actors> actors = new ArrayList<>();
		MongoDatabase db = getDB();
	//	CollectionName collectionName = new CollectionName();
		BasicDBObject regexQuery = new BasicDBObject();
		regexQuery.put(key1, 
		new BasicDBObject("$regex", value1).append("$options", "i"));
		System.out.println(regexQuery.toString());	
		//MongoCollection<Document> mongo_collection = db.getCollection(CollectionName.ACTOR_COLLECTION);
		FindIterable<Document> filter = db.getCollection(CollectionName.ACTOR_COLLECTION).find(regexQuery).sort(new BasicDBObject(key2,1)).limit(5);
		MongoCursor<Document> cursor = filter.iterator();
		
		try {
			while (cursor.hasNext()) {
				String obj = cursor.next().toJson();
				System.out.println(obj);
				Actors actor = gson.fromJson(obj, Actors.class);
				actors.add(actor);
			}
		} catch (JsonSyntaxException jse) {
			jse.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();

		}
		return actors;
	}
		
	public List<Actors> findMulitple(HashMap<String, Object> map, Integer limit) {
		List<Actors> actors = new ArrayList<>();
		MongoDatabase db = getDB();
		BasicDBList query = new BasicDBList();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			query.add(new BasicDBObject(entry.getKey(), entry.getValue()));
		}
		System.out.println(new BasicDBObject("$or", query).toJson());
		FindIterable<Document> filter = db.getCollection("sampleCollection").find(new BasicDBObject("$or", query)).limit(limit);
		MongoCursor<Document> cursor = filter.iterator();
		try {
			while (cursor.hasNext()) {
				String obj = cursor.next().toJson();
				System.out.println(obj);
				Actors actor = gson.fromJson(obj, Actors.class);
				actors.add(actor);

			}
		} catch (JsonSyntaxException jse) {
			jse.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		return actors;
	}
	
}


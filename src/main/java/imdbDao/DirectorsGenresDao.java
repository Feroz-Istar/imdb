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
import imdb.DirectorsGenres;
import imdb.Roles;

public class DirectorsGenresDao  extends BaseDB implements DaoImpl {
	public static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		
	public void insert(Object obj) {
		// TODO Auto-generated method stub
		MongoDatabase mongoDatabase =  getDB();
		 Document doc = Document.parse(gson.toJson(obj));
		mongoDatabase.getCollection(CollectionName.DIRECTOR_GENRES).insertOne(doc);
	}

	public void insertMany(List<Object> objlist) {
		// TODO Auto-generated method stub
		MongoDatabase mongoDatabase =  getDB();
		List<Document> docs = new ArrayList<Document>();

		for(Object obj: objlist) {
			 Document doc = Document.parse(gson.toJson(obj));
			 docs.add(doc);
		}
		mongoDatabase.getCollection(CollectionName.DIRECTOR_GENRES).insertMany(docs);
	}
	
	public List<DirectorsGenres> findAll() {
		List<DirectorsGenres> directorsgenres = new ArrayList<>();
		MongoDatabase db = getDB();
		CollectionName collectionName = new CollectionName();
		MongoCollection<Document> mongo_collection = db.getCollection(CollectionName.DIRECTOR_GENRES);
		FindIterable<Document> filter = mongo_collection.find().limit(300);
		MongoCursor<Document> cursor = filter.iterator();

		try {
			while (cursor.hasNext()) {
				// System.out.println(cursor.next().toJson());
				String obj = cursor.next().toJson();
				System.out.println(obj);
				DirectorsGenres directorsgenre = gson.fromJson(obj, DirectorsGenres.class);
				directorsgenres.add(directorsgenre);

			}
		} catch (JsonSyntaxException jse) {
			jse.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();

		}
		return directorsgenres;
	}
	
	public List<DirectorsGenres> sortBy(String key) {
		List<DirectorsGenres> directorgenres = new ArrayList<>();
		CollectionName collectionName = new CollectionName();
		MongoDatabase db = getDB();
		MongoCollection<Document> mongo_collection = db.getCollection(CollectionName.DIRECTOR_GENRES);
		FindIterable<Document> filter = mongo_collection.find().sort(new BasicDBObject(key, 1)).limit(300);
		MongoCursor<Document> cursor = filter.iterator();

		try {
			while (cursor.hasNext()) {
				// System.out.println(cursor.next().toJson());
				String obj = cursor.next().toJson();
				System.out.println(obj);
				DirectorsGenres directorgenre = gson.fromJson(obj, DirectorsGenres.class);
				directorgenres.add(directorgenre);

			}
		} catch (JsonSyntaxException jse) {
			jse.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();

		}
		return directorgenres;
	}

	public List<DirectorsGenres> findLike(String key,Object value){
		List<DirectorsGenres> directorsgenres = new ArrayList<>();
		MongoDatabase db = getDB();
		CollectionName collectionName = new CollectionName();
		BasicDBObject regexQuery = new BasicDBObject();
		regexQuery.put(key, 
			new BasicDBObject("$regex", value)
			.append("$options", "i"));
				
		System.out.println(regexQuery.toString());
		
		FindIterable<Document> filter = db.getCollection(CollectionName.DIRECTOR_GENRES).find(regexQuery).limit(300);
		MongoCursor<Document> cursor = filter.iterator();
		try {
			while (cursor.hasNext()) {
				String obj = cursor.next().toJson();
				System.out.println(obj);
				DirectorsGenres directorsgenre = gson.fromJson(obj, DirectorsGenres.class);
				directorsgenres.add(directorsgenre);
			}
		} catch (JsonSyntaxException jse) {
			jse.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();

		}
		return directorsgenres;
	}
	
}
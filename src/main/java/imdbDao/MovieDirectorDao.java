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
import imdb.MovieDirector;
import imdb.Roles;

public class MovieDirectorDao  extends BaseDB implements DaoImpl {
	public static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		
	public void insert(Object obj) {
		// TODO Auto-generated method stub
		MongoDatabase mongoDatabase =  getDB();
		 Document doc = Document.parse(gson.toJson(obj));
		mongoDatabase.getCollection(CollectionName.MOVIE_DIRECTOR_COLLECTION).insertOne(doc);
	}

	public void insertMany(List<Object> objlist) {
		// TODO Auto-generated method stub
		MongoDatabase mongoDatabase =  getDB();
		List<Document> docs = new ArrayList<Document>();

		for(Object obj: objlist) {
			 Document doc = Document.parse(gson.toJson(obj));
			 docs.add(doc);
		}
		mongoDatabase.getCollection(CollectionName.MOVIE_DIRECTOR_COLLECTION).insertMany(docs);
	}
	
	public List<MovieDirector> findAll() {
		List<MovieDirector> moviedirectors = new ArrayList<>();
		MongoDatabase db = getDB();
		CollectionName collectionName = new CollectionName();
		MongoCollection<Document> mongo_collection = db.getCollection(CollectionName.MOVIE_DIRECTOR_COLLECTION);
		FindIterable<Document> filter = mongo_collection.find().limit(300);
		MongoCursor<Document> cursor = filter.iterator();

		try {
			while (cursor.hasNext()) {
				// System.out.println(cursor.next().toJson());
				String obj = cursor.next().toJson();
				System.out.println(obj);
				MovieDirector moviedirector = gson.fromJson(obj, MovieDirector.class);
				moviedirectors.add(moviedirector);

			}
		} catch (JsonSyntaxException jse) {
			jse.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();

		}
		return moviedirectors;
	}
	
	public List<MovieDirector> sortBy(String key) {
		List<MovieDirector> moviedirectors = new ArrayList<>();
		CollectionName collectionName = new CollectionName();
		MongoDatabase db = getDB();
		MongoCollection<Document> mongo_collection = db.getCollection(CollectionName.MOVIE_DIRECTOR_COLLECTION);
		FindIterable<Document> filter = mongo_collection.find().sort(new BasicDBObject(key, 1)).limit(300);
		MongoCursor<Document> cursor = filter.iterator();

		try {
			while (cursor.hasNext()) {
				// System.out.println(cursor.next().toJson());
				String obj = cursor.next().toJson();
				System.out.println(obj);
				MovieDirector moviedirector = gson.fromJson(obj, MovieDirector.class);
				moviedirectors.add(moviedirector);

			}
		} catch (JsonSyntaxException jse) {
			jse.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();

		}
		return moviedirectors;
	}

	public List<MovieDirector> findLike(String key,Object value){
		List<MovieDirector> moviedirectors = new ArrayList<>();
		MongoDatabase db = getDB();
		CollectionName collectionName = new CollectionName();
		BasicDBObject regexQuery = new BasicDBObject();
		regexQuery.put(key, 
			new BasicDBObject("$regex", value)
			.append("$options", "i"));
				
		System.out.println(regexQuery.toString());
		
		FindIterable<Document> filter = db.getCollection(CollectionName.MOVIE_DIRECTOR_COLLECTION).find(regexQuery).limit(300);
		MongoCursor<Document> cursor = filter.iterator();
		try {
			while (cursor.hasNext()) {
				String obj = cursor.next().toJson();
				System.out.println(obj);
				MovieDirector moviedirector = gson.fromJson(obj, MovieDirector.class);
				moviedirectors.add(moviedirector);
			}
		} catch (JsonSyntaxException jse) {
			jse.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();

		}
		return moviedirectors;
	}
	
	
	public List<MovieDirector> findById(String key,Object value){
		List<MovieDirector> moviedirectors = new ArrayList<>();
		MongoDatabase db = getDB();
		CollectionName collectionName = new CollectionName();
		BasicDBObject regexQuery = new BasicDBObject();
		regexQuery.put(key, value);
			
				
		System.out.println(regexQuery.toString());
		
		FindIterable<Document> filter = db.getCollection(CollectionName.MOVIE_DIRECTOR_COLLECTION).find(regexQuery).limit(300);
		MongoCursor<Document> cursor = filter.iterator();
		try {
			while (cursor.hasNext()) {
				String obj = cursor.next().toJson();
				System.out.println(obj);
				MovieDirector moviedirector = gson.fromJson(obj, MovieDirector.class);
				moviedirectors.add(moviedirector);
			}
		} catch (JsonSyntaxException jse) {
			jse.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();

		}
		return moviedirectors;
	}
	
	
	
	public List<MovieDirector> findListOfDirector(String key,Object value){
		MongoDatabase db = getDB();
		List<MovieDirector> moviedirectors = new ArrayList<>();

		BasicDBObject basicDBObject = new BasicDBObject();
		basicDBObject.put("$in", value);
		BasicDBObject query = new BasicDBObject();
		query.put(key, basicDBObject);
		System.out.println(query.toJson());
		
		FindIterable<Document> filter = db.getCollection(CollectionName.MOVIE_DIRECTOR_COLLECTION).find(query);
		MongoCursor<Document> cursor = filter.iterator();
		try {
			while (cursor.hasNext()) {
				String obj = cursor.next().toJson();
				//System.out.println(obj);
				MovieDirector moviedirector = gson.fromJson(obj, MovieDirector.class);
				moviedirectors.add(moviedirector);
			}
		} catch (JsonSyntaxException jse) {
			jse.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();

		}
		return moviedirectors;

	}
	
	
}
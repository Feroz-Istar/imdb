package imdbDao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.model.UpdateOneModel;
import com.mongodb.client.model.WriteModel;

import constants.CollectionName;
import daoimpl.DaoImpl;
import imdb.Movies;
import imdb.Roles;

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
	
	public Movies find(String key, Object value) {
		Movies movies = new Movies();
		MongoDatabase db = getDB();
		BasicDBObject query = new BasicDBObject();
		query.put(key, value);
		
		//Long start_time = System.currentTimeMillis();
		CollectionName collectionName = new CollectionName();
		FindIterable<Document> filter = db.getCollection(collectionName.MOVIE_COLLECTION).find(query).limit(300);
		MongoCursor<Document> cursor = filter.iterator();
		try {
			String obj = cursor.next().toJson();
			System.out.println(obj);
			movies = gson.fromJson(obj, Movies.class); 
		} catch (JsonSyntaxException jse) {
			jse.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		
		//System.out.println(start_time - System.currentTimeMillis());
		return movies;

	}

	public List<Movies> findAll() {
		List<Movies> movies = new ArrayList<>();
		MongoDatabase db = getDB();
		CollectionName collectionName = new CollectionName();
		MongoCollection<Document> mongo_collection = db.getCollection(collectionName.MOVIE_COLLECTION);
		FindIterable<Document> filter = mongo_collection.find().limit(300);
		MongoCursor<Document> cursor = filter.iterator();

		try {
			while (cursor.hasNext()) {
				// System.out.println(cursor.next().toJson());
				String obj = cursor.next().toJson();
				//System.out.println(obj);
				Movies movie = gson.fromJson(obj, Movies.class);
				movies.add(movie);

			}
		} catch (JsonSyntaxException jse) {
			jse.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();

		}
		return movies;
	}

	public List<Movies> sortBy(String key) {
		List<Movies> movies = new ArrayList<>();
		MongoDatabase db = getDB();
		MongoCollection<Document> mongo_collection = db.getCollection(CollectionName.MOVIE_COLLECTION);
		FindIterable<Document> filter = mongo_collection.find().sort(new BasicDBObject(key, 1)).limit(300);
		MongoCursor<Document> cursor = filter.iterator();

		try {
			while (cursor.hasNext()) {
				// System.out.println(cursor.next().toJson());
				String obj = cursor.next().toJson();
				System.out.println(obj);
				Movies movie = gson.fromJson(obj, Movies.class);
				movies.add(movie);

			}
		} catch (JsonSyntaxException jse) {
			jse.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();

		}
		return movies;
	}

	public List<Movies> findLike(String key,Object value){
		List<Movies> movies = new ArrayList<>();
		MongoDatabase db = getDB();
		BasicDBObject regexQuery = new BasicDBObject();
		regexQuery.put(key, 
			new BasicDBObject("$regex", value)
			.append("$options", "i"));
				
		System.out.println(regexQuery.toString());
		
		FindIterable<Document> filter = db.getCollection(CollectionName.MOVIE_COLLECTION).find(regexQuery).limit(300);
		MongoCursor<Document> cursor = filter.iterator();
		try {
			while (cursor.hasNext()) {
				String obj = cursor.next().toJson();
				System.out.println(obj);
				Movies movie = gson.fromJson(obj, Movies.class);
				movies.add(movie);
			}
		} catch (JsonSyntaxException jse) {
			jse.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();

		}
		return movies;
	}

	
	
	public void addNewField(String key, Object value) {
		BasicDBObject setNewFieldQuery = new BasicDBObject().append("$set", new BasicDBObject().append(key, value));
		MongoDatabase db = getDB();
		db.getCollection(CollectionName.MOVIE_COLLECTION).updateMany(new BasicDBObject(), setNewFieldQuery);
	}
	
	public void updateMovie(Movies movies) {
		MongoDatabase db = getDB();
		Bson filter = new Document("_id",new ObjectId(movies.get_id().get$oid()));
		BasicDBObject update_fields = new BasicDBObject();
		update_fields.append("name", movies.getName());
		update_fields.append("year", movies.getYear());
		update_fields.append("image", movies.getImage());
		update_fields.append("rank", movies.getRank());
		Bson update_operations = new Document("$set",update_fields);
		db.getCollection(CollectionName.MOVIE_COLLECTION).updateOne(filter, update_operations);

	}
	
	
	public void bulkWrite() {
		MongoDatabase db = getDB();
		Faker faker = new Faker();
		List<WriteModel<Document>> writes = new ArrayList<WriteModel<Document>>();

		List<Movies> movies_list = findAll();
		for(Movies movie: movies_list) {
			movie.setImage(faker.internet().image());
			writes.add( new UpdateOneModel<Document>( new Document("_id", new ObjectId(movie.get_id().get$oid())), new Document("$set", new Document("image", movie.getImage()))  ) )	;	
		}
		
		
		
		db.getCollection(CollectionName.MOVIE_COLLECTION).bulkWrite(writes);

	}
	
}		



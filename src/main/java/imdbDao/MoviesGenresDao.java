package imdbDao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.mongodb.BasicDBObject;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import constants.CollectionName;
import daoimpl.DaoImpl;
import imdb.Movies;
import imdb.MoviesGenres;
import imdb.Roles;

public class MoviesGenresDao  extends BaseDB implements DaoImpl{
	public static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		
	public void insert(Object obj) {
		// TODO Auto-generated method stub
		MongoDatabase mongoDatabase =  getDB();
		Document doc = Document.parse(gson.toJson(obj));
		mongoDatabase.getCollection(CollectionName.MOVIE_GENRES).insertOne(doc);
	}

	public void insertMany(List<Object> objlist) {
		// TODO Auto-generated method stub
		MongoDatabase mongoDatabase =  getDB();
		List<Document> docs = new ArrayList<Document>();

		for(Object obj: objlist) {
			 Document doc = Document.parse(gson.toJson(obj));
			 docs.add(doc);
		}
		mongoDatabase.getCollection(CollectionName.MOVIE_GENRES).insertMany(docs);
	}

	public List<MoviesGenres> findAll() {
		List<MoviesGenres> moviesgenres = new ArrayList<>();
		MongoDatabase db = getDB();
		CollectionName collectionName = new CollectionName();
		MongoCollection<Document> mongo_collection = db.getCollection(collectionName.MOVIE_GENRES);
		FindIterable<Document> filter = mongo_collection.find().limit(300);
		MongoCursor<Document> cursor = filter.iterator();

		try {
			while (cursor.hasNext()) {
				// System.out.println(cursor.next().toJson());
				String obj = cursor.next().toJson();
				//System.out.println(obj);
				MoviesGenres moviesgenre = gson.fromJson(obj, MoviesGenres.class);
				moviesgenres.add(moviesgenre);

			}
		} catch (JsonSyntaxException jse) {
			jse.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();

		}
		return moviesgenres;
	}

	public List<MoviesGenres> sortBy(String key) {
		List<MoviesGenres> moviesgenres = new ArrayList<>();
		MongoDatabase db = getDB();
		MongoCollection<Document> mongo_collection = db.getCollection(CollectionName.MOVIE_GENRES);
		FindIterable<Document> filter = mongo_collection.find().sort(new BasicDBObject(key, 1)).limit(300);
		MongoCursor<Document> cursor = filter.iterator();

		try {
			while (cursor.hasNext()) {
				// System.out.println(cursor.next().toJson());
				String obj = cursor.next().toJson();
				System.out.println(obj);
				MoviesGenres moviesgenre = gson.fromJson(obj, MoviesGenres.class);
				moviesgenres.add(moviesgenre);

			}
		} catch (JsonSyntaxException jse) {
			jse.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();

		}
		return moviesgenres;
	}

	public List<MoviesGenres> findLike(String key,Object value){
		List<MoviesGenres> moviesgenres = new ArrayList<>();
		MongoDatabase db = getDB();
		BasicDBObject regexQuery = new BasicDBObject();
		regexQuery.put(key, 
			new BasicDBObject("$regex", value)
			.append("$options", "i"));
				
		System.out.println(regexQuery.toString());
		
		FindIterable<Document> filter = db.getCollection(CollectionName.MOVIE_GENRES).find(regexQuery).limit(300);
		MongoCursor<Document> cursor = filter.iterator();
		try {
			while (cursor.hasNext()) {
				String obj = cursor.next().toJson();
				System.out.println(obj);
				MoviesGenres moviesgenre = gson.fromJson(obj, MoviesGenres.class);
				moviesgenres.add(moviesgenre);
			}
		} catch (JsonSyntaxException jse) {
			jse.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();

		}
		return moviesgenres;
	}
	
	
	public Set<String> getMovieDistinctCategory() {
		MongoDatabase db = getDB();
		Set<String> category = new HashSet<>();
		
		//Long start_time = System.currentTimeMillis();
		CollectionName collectionName = new CollectionName();
		DistinctIterable<String> filter = db.getCollection(CollectionName.MOVIE_GENRES).distinct("genre", String.class);
		MongoCursor<String> cursor = filter.iterator();
		try {
		while(cursor.hasNext()) {
			String obj = cursor.next();
			//System.out.println(obj);
			category.add(obj);
		}
		} catch (JsonSyntaxException jse) {
			jse.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		
		//System.out.println(start_time - System.currentTimeMillis());
		return category;

	}
	
	
	
	public List<MoviesGenres> find(Integer movie_id) {
		List<MoviesGenres> genres = new ArrayList<>();
		MongoDatabase db = getDB();
		BasicDBObject query = new BasicDBObject();
		
		query.put("movie_id", movie_id);
		
		//Long start_time = System.currentTimeMillis();
		FindIterable<Document> filter = db.getCollection(CollectionName.ROLE_COLLECTION).find(query);
		MongoCursor<Document> cursor = filter.iterator();
		try {
			String obj = cursor.next().toJson();
			//System.out.println(obj);
			MoviesGenres moviesGenres = gson.fromJson(obj, MoviesGenres.class);
			genres.add(moviesGenres);
		} catch (JsonSyntaxException jse) {
			jse.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		
		//System.out.println(start_time - System.currentTimeMillis());
		return genres;

	}


}
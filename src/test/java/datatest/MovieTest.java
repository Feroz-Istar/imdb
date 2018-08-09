package datatest;

import java.util.ArrayList;
import java.util.List;

import com.github.javafaker.Faker;

import imdb.Actors;
import imdb.Directors;
import imdb.MovieDirector;
import imdb.Movies;
import imdb.Roles;
import imdbDao.ActorsDao;
import imdbDao.DirectorsDao;
import imdbDao.MovieDirectorDao;
import imdbDao.MoviesDao;
import imdbDao.RolesDao;

public abstract class MovieTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//MoviesDao moviedao = new MoviesDao();
		/*Actors actors = new Actors();
		ActorsDao actordao = new ActorsDao();
		System.out.println(actordao.findAndSort("first_name", "id", "Michael"));*/
		
		
		MovieDirectorDao movieDirectorDao = new MovieDirectorDao();
		DirectorsDao directorsDao = new DirectorsDao();
		
		List<Integer> movie_ids = new ArrayList<>();
		movie_ids.add(1);
		movie_ids.add(32);
		
		
		List<MovieDirector> movieDirectors = movieDirectorDao.findListOfDirector("movie_id", movie_ids);
		for(MovieDirector director: movieDirectors) {
			System.out.println(director.getDirector_id());
			
			Directors directors =directorsDao.find("id", director.getDirector_id());
			System.out.println(directors.getFirst_name()+directors.getLast_name());
		}
		
		
		
		RolesDao rolesdao = new RolesDao();
		ActorsDao actorsdao = new ActorsDao();
		
		List<Integer> movieid = new ArrayList<>();
		movieid.add(2);
		
		List<Roles> roles = rolesdao.findListOfActors("movie_id", movieid);
		for(Roles role: roles) {
			System.out.println(role.getActor_id());
			Actors actor = actorsdao.find("id", role.getActor_id());
			System.out.println(actor.getFirst_name()+actor.getLast_name());
		}
		
		
		//moviedao.bulkWrite();
		/*Faker faker = new Faker();
		moviedao.addNewField("image", faker.internet().avatar());*/
		/*Long start_time = System.currentTimeMillis();
		System.out.println(moviedao.findAll());
		System.out.println(start_time - System.currentTimeMillis());
		
		start_time = System.currentTimeMillis();
		System.out.println(moviedao.sortBy("rank"));
		System.out.println(start_time - System.currentTimeMillis());
	
		start_time = System.currentTimeMillis();
		System.out.println(moviedao.findLike("name", "100"));
		System.out.println(start_time - System.currentTimeMillis());*/
	}

}

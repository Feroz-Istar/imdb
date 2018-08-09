package services;

import java.util.ArrayList;
import java.util.List;

import imdb.Actors;
import imdb.Directors;
import imdb.MovieDirector;
import imdb.Movies;
import imdb.MoviesGenres;
import imdb.Roles;
import imdbDao.ActorsDao;
import imdbDao.DirectorsDao;
import imdbDao.MovieDirectorDao;
import imdbDao.MoviesDao;
import imdbDao.MoviesGenresDao;
import imdbDao.RolesDao;
import responsepojo.MovieResponse;

public class MovieService {

	
	public List<MovieResponse> getMovieDetails(Integer movie_id) {
		List<MovieResponse> movieResponses = new ArrayList<>();

		MoviesDao moviesdao = new MoviesDao();
		MovieDirectorDao movieDirectorDao = new MovieDirectorDao();
		DirectorsDao directorsDao = new DirectorsDao();
		RolesDao rolesDao = new RolesDao();
		ActorsDao actorsDao = new ActorsDao();
		MoviesGenresDao moviesGenresDao = new MoviesGenresDao();
		MovieResponse movieResponse = new MovieResponse();

		List<Integer> movie_ids = new ArrayList<>();
		movie_ids.add(movie_id);
		List<MovieDirector> movieDirectors = movieDirectorDao.findListOfDirector("movie_id", movie_ids);
		List<Directors> directors = new ArrayList<>();

		Movies mov = moviesdao.find("id", movie_id);

		for (MovieDirector movieDirector : movieDirectors) {
			Directors director = directorsDao.find("id", movieDirector.getDirector_id());
			System.out.println(
					"Directors >>>>>>>>>>>>>>>>>>> " + director.getFirst_name() + " " + director.getLast_name());
			directors.add(director);
		}

		List<Roles> roles = rolesDao.findActors("movie_id", movie_id);
		List<Actors> actors = new ArrayList<>();
		for (Roles role : roles) {
			Integer actor_id = role.getActor_id();
			Actors actor = actorsDao.find("id", actor_id);
			System.out.println("Actor ************** " + actor.getFirst_name() + " " + actor.getLast_name());
			actors.add(actor);
		}

		List<MoviesGenres> moviesGenres = moviesGenresDao.find(movie_id);
		for (MoviesGenres moviesGenres2 : moviesGenres) {
			System.out.println("movie category ************ " + moviesGenres2.getGenre());
		}

		movieResponse = new MovieResponse(mov, directors, actors, moviesGenres);

		movieResponses.add(movieResponse);
		
		return movieResponses;
	}
	
}

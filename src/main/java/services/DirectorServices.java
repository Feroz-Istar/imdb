package services;

import java.util.ArrayList;
import java.util.List;

import imdb.MovieDirector;
import imdb.Movies;
import imdbDao.MovieDirectorDao;
import imdbDao.MoviesDao;
import responsepojo.DirectorResponse;

public class DirectorServices {

	public List<DirectorResponse> getDirectorDetails(Integer director_id){
		
		MovieDirectorDao moviedirectordao = new MovieDirectorDao();
		MoviesDao moviedao = new MoviesDao();
		List<DirectorResponse> directorResponses = new ArrayList<>();
		List<Movies> movie_list = new ArrayList<>();
		List<Movies> movies = new ArrayList<>();
		List<MovieDirector> director_list = moviedirectordao.findDirectors("director_id", director_id);
		
		
		for(MovieDirector movieDirector : director_list) {
			Integer movie_id = movieDirector.getMovie_id();
			movies.add(moviedao.find("id",movie_id));
		}
		
		for(Movies movie : movies) {
			Movies movie2 = moviedao.find("name", movie.getName());
			movie_list.add(movie2);
		}
		
		DirectorResponse directorResponse = new DirectorResponse(movie_list);
		directorResponses.add(directorResponse);
		
		return directorResponses;
		
	}
}
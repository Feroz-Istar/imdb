package responsepojo;

import java.util.ArrayList;
import java.util.List;

import imdb.Actors;
import imdb.Directors;
import imdb.Movies;
import imdb.MoviesGenres;

public class MovieResponse {
	private Movies movies;
	private List<Directors> list_of_directors;
	private List<Actors> list_of_actors;
	private List<MoviesGenres> moviesGenres;
	public MovieResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MovieResponse(Movies movies, List<Directors> list_of_directors, List<Actors> list_of_actors,
			List<MoviesGenres> moviesGenres) {
		super();
		this.movies = movies;
		this.list_of_directors = list_of_directors;
		this.list_of_actors = list_of_actors;
		this.moviesGenres = moviesGenres;
	}
	public Movies getMovies() {
		return movies;
	}
	public void setMovies(Movies movies) {
		this.movies = movies;
	}
	public List<Directors> getList_of_directors() {
		return list_of_directors;
	}
	public void setList_of_directors(List<Directors> list_of_directors) {
		this.list_of_directors = list_of_directors;
	}
	public List<Actors> getList_of_actors() {
		return list_of_actors;
	}
	public void setList_of_actors(List<Actors> list_of_actors) {
		this.list_of_actors = list_of_actors;
	}
	public List<MoviesGenres> getMoviesGenres() {
		return moviesGenres;
	}
	public void setMoviesGenres(List<MoviesGenres> moviesGenres) {
		this.moviesGenres = moviesGenres;
	}
	
	
}

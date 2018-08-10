package responsepojo;

import java.util.List;

import imdb.Actors;
import imdb.Directors;
import imdb.Movies;

public class MovieActorDirectorResponse {
	private Movies movies;
	private List<Directors> list_of_directors;
	private List<Actors> list_of_actors;
	public MovieActorDirectorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MovieActorDirectorResponse(Movies movies, List<Directors> list_of_directors, List<Actors> list_of_actors) {
		super();
		this.movies = movies;
		this.list_of_directors = list_of_directors;
		this.list_of_actors = list_of_actors;
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

}

package responsepojo;

import java.util.List;

import imdb.Movies;

public class ActorResponse {
	
	private List<Movies> list_of_movies;

	public ActorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ActorResponse(List<Movies> list_of_movies) {
		super();
		this.list_of_movies = list_of_movies;
	}

	public List<Movies> getList_of_movies() {
		return list_of_movies;
	}

	public void setList_of_movies(List<Movies> list_of_movies) {
		this.list_of_movies = list_of_movies;
	}

	
}

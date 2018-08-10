package services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import imdb.Actors;
import imdb.Directors;
import imdb.MovieDirector;
import imdb.Movies;
import imdb.Roles;
import imdbDao.ActorsDao;
import imdbDao.MoviesDao;
import imdbDao.RolesDao;
import responsepojo.ActorResponse;

public class ActorServices {
	
	public List<ActorResponse> getActorDetails(Integer actor_id) {
		
		RolesDao rolesdao = new RolesDao();
		MoviesDao moviesdao = new MoviesDao();
		List<Movies> movie_list = new ArrayList<>();
		List<ActorResponse> actorResponses = new ArrayList<>();
		List<Movies> movies = new ArrayList<>();
		List<Roles> roles =rolesdao.findActors("actor_id",actor_id);
		
		for (Roles roles2 : roles) {
			System.out.println(roles2.getMovie_id());
			movies.add(moviesdao.find("id",roles2.getMovie_id()));
		}
			
		for (Movies mov : movies) {
			Movies movie2 = moviesdao.find("name",mov.getName());
			movie_list.add(movie2);			
		}
						
		ActorResponse actorResponse = new ActorResponse(movie_list);
		actorResponses.add(actorResponse);
		
		return actorResponses;
	}

}

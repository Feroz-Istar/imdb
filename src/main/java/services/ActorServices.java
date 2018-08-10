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
		
		ActorsDao actordao = new ActorsDao();
		RolesDao rolesdao = new RolesDao();
		MoviesDao moviesdao = new MoviesDao();
		
		
		
		List<Roles> roles =rolesdao.findListOfActors("actor_id",actor_id);
		for (Roles roles2 : roles) {
			System.out.println(roles2.getMovie_id());
		}
		
		/*ActorResponse actorResponse = new ActorResponse();
		List<ActorResponse> actorResponses = new ArrayList<>();
		List<Movies> movies = new ArrayList<>();
		
		Roles role = new Roles();
		List<Roles> roles = new ArrayList<>();
		*/
		
		
				
		return new ArrayList<>();
	}

}

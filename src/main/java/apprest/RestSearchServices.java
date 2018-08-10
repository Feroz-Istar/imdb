package apprest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import imdb.Actors;
import imdb.Movies;
import imdbDao.ActorsDao;
import imdbDao.MoviesDao;

@Path("/search")
public class RestSearchServices {

private static final Gson gson = new Gson();
	
	@GET
	@Path("searchresults")
	public Response searchres(@QueryParam("query") String query) {
		
		Integer searchlimit=6;
		MoviesDao moviesdao = new MoviesDao();
		List<Movies> movies = moviesdao.findLike("name",query,searchlimit);
		if(movies.size()>searchlimit){
			return Response.status(200).entity(gson.toJson(movies)).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
		}
		else{
		 searchlimit -= movies.size();
		 ActorsDao actordao = new ActorsDao();
		 List<Actors> actors = actordao.findMultiple("first_name", query, searchlimit);
		 
		 
		 
		if(movies.size() + actors.size() >searchlimit){
			
		
		 
		 
		 if( movies.size() ==0 && actors.size() >searchlimit){
			 return Response.status(200).entity(gson.toJson(actors)).header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
			 
		}
			
	
		
		return Response.status(200).entity(gson.toJson("")).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
	}
}

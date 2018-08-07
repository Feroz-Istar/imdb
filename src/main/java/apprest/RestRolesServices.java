package apprest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import imdb.Movies;
import imdbDao.MoviesDao;

public class RestRolesServices {
	private static final Gson gson = new Gson();
	@GET
	@Path("getallmovies")
	public Response getMsg() {
		MoviesDao moviesdao = new MoviesDao();
		List<Movies> movies = moviesdao.findAll();
		String output = "Jersey say : ";
 
		return Response.status(200).entity(gson.toJson(movies)).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build()
;
 
	}
	@GET
	@Path("searchmovies")
	public Response searchMovie(@QueryParam("moviename") String moviename) {
		
		MoviesDao moviesdao = new MoviesDao();
		List<Movies> movies = moviesdao.findLike("name",moviename);
		String output = "Jersey say : ";
 
		return Response.status(200).entity(gson.toJson(movies)).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build()
;
 
	}

	

}

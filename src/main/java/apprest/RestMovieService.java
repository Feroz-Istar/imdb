package apprest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

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
import services.MovieService;
 
@Path("/movie")
public class RestMovieService {
 private static final Gson gson = new Gson();
	@GET
//	@Produces({MediaType.APPLICATION_JSON})
	@Path("getallmovies")
	public Response getMsg() {
		MoviesDao moviesdao = new MoviesDao();
		List<Movies> movies = moviesdao.findAll();
		
		return Response.status(200).entity(gson.toJson(movies)).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
 
	}
	@GET
	@Path("searchmovies")
	public Response searchMovie(@QueryParam("moviename") String moviename) {
		
		MoviesDao moviesdao = new MoviesDao();
		List<Movies> movies = moviesdao.findLike("name",moviename);
		String output = "Jersey say : ";
 
		return Response.status(200).entity(gson.toJson(movies)).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
 
	}
	
	@GET
	@Path("sortmovies")
	public Response sortMovie(@QueryParam("key") String key) {
		
		MoviesDao moviesdao = new MoviesDao();
		List<Movies> movies = moviesdao.sortBy(key);
		String output = "Jersey say : ";
 
		return Response.status(200).entity(gson.toJson(movies)).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
 
	}
	
	@GET
//	@Produces({MediaType.APPLICATION_JSON})
	@Path("getmoviesdetail")
	public Response getMovieIdMsg(@QueryParam("movie_id") String movie_id) {
		List<MovieResponse> movieResponses = new ArrayList<>();
		MovieService movieService = new MovieService();
		movieResponses = movieService.getMovieDetails(Integer.parseInt(movie_id));
		
		return Response.status(200).entity(gson.toJson(movieResponses)).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
 
	}
	
}
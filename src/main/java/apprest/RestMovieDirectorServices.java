package apprest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import imdb.MovieDirector;
import imdbDao.MovieDirectorDao;

@Path("/moviedirectors")
public class RestMovieDirectorServices {
	private static final Gson gson = new Gson();
	
	@GET
	@Path("getmoviedirectors")
	public Response getMsg() {
		MovieDirectorDao moviedirectordao = new MovieDirectorDao();
		List<MovieDirector> moviedirectors = moviedirectordao.findAll();
		String output = "Jersey say : ";
 
		return Response.status(200).entity(gson.toJson(moviedirectors)).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
	}
	
	@GET
	@Path("searchmoviedirector")
	public Response searchMovie(@QueryParam("directorid") String directorid) {
		
		MovieDirectorDao moviedirectordao = new MovieDirectorDao();
		List<MovieDirector> moviedirector = moviedirectordao.findById("director_id",Integer.parseInt(directorid));
		String output = "Jersey say : ";
 
		return Response.status(200).entity(gson.toJson(moviedirector)).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
	}

	@GET
	@Path("sortdirector")
	public Response sortMovie(@QueryParam("key") String key) {
		
		MovieDirectorDao moviedirectordao = new MovieDirectorDao();
		List<MovieDirector> moviedirector = moviedirectordao.sortBy(key);
		String output = "Jersey say : ";
 
		return Response.status(200).entity(gson.toJson(moviedirector)).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
	}
}

package apprest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import imdb.MoviesGenres;
import imdbDao.MoviesGenresDao;

@Path("/moviesbygenre")
public class RestMovieGenresServices {

		private static final Gson gson = new Gson();
		@GET
		@Path("getmoviesbygenre")
		public Response getMsg() {
			MoviesGenresDao moviesgenresdao = new MoviesGenresDao();
			List<MoviesGenres> moviesgenres = moviesgenresdao.findAll();
			String output = "Jersey say : ";
	 
			return Response.status(200).entity(gson.toJson(moviesgenres)).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
		}
		
		@GET
		@Path("searchmoviesbygenre")
		public Response searchMovie(@QueryParam("genreis") String genreis) {
			
			MoviesGenresDao moviesgenredao = new MoviesGenresDao();
			List<MoviesGenres> moviegenre = moviesgenredao.findLike("genre",genreis);
			String output = "Jersey say : ";
	 
			return Response.status(200).entity(gson.toJson(moviegenre)).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
		}

		@GET
		@Path("sortmovies")
		public Response sortMovie(@QueryParam("key") String key) {
			
			MoviesGenresDao moviesgenresdao = new MoviesGenresDao();
			List<MoviesGenres> moviesgenre = moviesgenresdao.sortBy(key);
			String output = "Jersey say : ";
	 
			return Response.status(200).entity(gson.toJson(moviesgenre)).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
		}
		

	}


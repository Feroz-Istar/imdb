package apprest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import imdb.DirectorsGenres;
import imdbDao.DirectorsGenresDao;

@Path("/directorsbygenre")
public class RestDirectorsGenreServices {
	private static final Gson gson = new Gson();
	
	@GET
	@Path("getdirectorsbygenre")
	public Response getMsg() {
		DirectorsGenresDao directorgenredao = new DirectorsGenresDao();
		List<DirectorsGenres> directorgenre = directorgenredao.findAll();
		String output = "Jersey say : ";
 
		return Response.status(200).entity(gson.toJson(directorgenre)).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
	}
	
	@GET
	@Path("searchdirectorsbygenre")
	public Response searchMovie(@QueryParam("genreis") String genreis) {
		
		DirectorsGenresDao directorgenredao = new DirectorsGenresDao();
		List<DirectorsGenres> directorgenre = directorgenredao.findLike("genre",genreis);
		String output = "Jersey say : ";
 
		return Response.status(200).entity(gson.toJson(directorgenre)).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
	}

	@GET
	@Path("sortmovies")
	public Response sortMovie(@QueryParam("key") String key) {
		
		DirectorsGenresDao directorgenredao = new DirectorsGenresDao();
		List<DirectorsGenres> directorgenre = directorgenredao.sortBy(key);
		String output = "Jersey say : ";
 
		return Response.status(200).entity(gson.toJson(directorgenre)).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
	}
	

}

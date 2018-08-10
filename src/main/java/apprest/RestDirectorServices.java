package apprest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import imdb.Directors;
import imdbDao.DirectorsDao;
import responsepojo.ActorResponse;
import responsepojo.DirectorResponse;
import services.ActorServices;
import services.DirectorServices;

@Path("/directors")
public class RestDirectorServices {
private static final Gson gson = new Gson();
	
	@GET
	@Path("getalldirectors")
	public Response getMsg() {
		DirectorsDao directorsdao = new DirectorsDao();
		List<Directors> directors = directorsdao.findAll();
		String output = "Jersey say : ";
 
		return Response.status(200).entity(gson.toJson(directors)).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
	}
	
	@GET
	@Path("searchdirectors")
	public Response searchMovie(@QueryParam("fname") String fname) {
		
		DirectorsDao directorsdao = new DirectorsDao();
		List<Directors> directors = directorsdao.findLike("first_name",fname);
		String output = "Jersey say : ";
 
		return Response.status(200).entity(gson.toJson(directors)).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
	}

	@GET
	@Path("sortdirectors")
	public Response sortMovie(@QueryParam("key") String key) {
		
		DirectorsDao directorsdao = new DirectorsDao();
		List<Directors> directors = directorsdao.sortBy(key);
		String output = "Jersey say : ";
 
		return Response.status(200).entity(gson.toJson(directors)).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
	}
	
	@GET
	@Path("getdirectorfilmography")
	public Response getDirectorDetails(@QueryParam("director_id") String director_id) {
		List<DirectorResponse> directorResponses = new ArrayList<>();
		DirectorServices directorService = new DirectorServices();
		directorResponses = directorService.getDirectorDetails(Integer.parseInt(director_id));
		
		return Response.status(200).entity(gson.toJson(directorResponses)).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
 
	}

	
}

package apprest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import imdb.Actors;
import imdbDao.ActorsDao;

@Path("/actors")
public class RestActorsServices {
private static final Gson gson = new Gson();
	
	@GET
	@Path("getallactors")
	public Response getMsg() {
		ActorsDao actorsdao = new ActorsDao();
		List<Actors> actors = actorsdao.findAll();
		String output = "Jersey say : ";
 
		return Response.status(200).entity(gson.toJson(actors)).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
	}
	
	@GET
	@Path("searchactors")
	public Response searchMovie(@QueryParam("fname") String fname) {
		
		ActorsDao actorsdao = new ActorsDao();
		List<Actors> actors = actorsdao.findLike("first_name",fname);
		String output = "Jersey say : ";
 
		return Response.status(200).entity(gson.toJson(actors)).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
	}

	@GET
	@Path("sortactors")
	public Response sortMovie(@QueryParam("key") String key) {
		
		ActorsDao actorsdao = new ActorsDao();
		List<Actors> actors = actorsdao.sortBy(key);
		String output = "Jersey say : ";
 
		return Response.status(200).entity(gson.toJson(actors)).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
	}
}

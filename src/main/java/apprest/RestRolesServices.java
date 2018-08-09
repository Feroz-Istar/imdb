package apprest;

import java.util.List;

import javax.management.relation.Role;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import imdb.Roles;
import imdbDao.RolesDao;


@Path("/role")
public class RestRolesServices {
	private static final Gson gson = new Gson();
	
	@GET
	@Path("getroles")
	public Response getMsg() {
		RolesDao rolesdao = new RolesDao();
		List<Roles> roles = rolesdao.findAll();
		String output = "Jersey say : ";
 
		return Response.status(200).entity(gson.toJson(roles)).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
	}
	
	@GET
	@Path("searchrole")
	public Response searchMovie(@QueryParam("rolename") String rolename) {
		
		RolesDao rolesdao = new RolesDao();
		List<Roles> roles = rolesdao.findLike("role",rolename);
		String output = "Jersey say : ";
 
		return Response.status(200).entity(gson.toJson(roles)).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
	}

	@GET
	@Path("sortmovies")
	public Response sortMovie(@QueryParam("key") String key) {
		
		RolesDao rolesdao = new RolesDao();
		List<Roles> roles = rolesdao.sortBy(key);
		String output = "Jersey say : ";
 
		return Response.status(200).entity(gson.toJson(roles)).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
	}
	

}

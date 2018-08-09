package datatest;

import java.util.List;

import imdb.Roles;
import imdbDao.RolesDao;

public class TestRolesDao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RolesDao dao = new RolesDao();
		
		List<Roles> roles = dao.findActors("movie_id", 280088);
		
		
		System.out.println("actors size is "+roles.size());
		
		
	}

}

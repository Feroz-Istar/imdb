package datatest;

import java.util.List;

import com.github.javafaker.Faker;

import imdb.Movies;
import imdbDao.MoviesDao;

public abstract class MovieTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MoviesDao moviedao = new MoviesDao();
		
		moviedao.bulkWrite();
		/*Faker faker = new Faker();
		moviedao.addNewField("image", faker.internet().avatar());*/
		/*Long start_time = System.currentTimeMillis();
		System.out.println(moviedao.findAll());
		System.out.println(start_time - System.currentTimeMillis());
		
		start_time = System.currentTimeMillis();
		System.out.println(moviedao.sortBy("rank"));
		System.out.println(start_time - System.currentTimeMillis());
	
		start_time = System.currentTimeMillis();
		System.out.println(moviedao.findLike("name", "100"));
		System.out.println(start_time - System.currentTimeMillis());*/
	}

}

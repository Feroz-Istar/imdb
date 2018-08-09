package datatest;

import java.util.List;
import java.util.Set;

import imdbDao.MoviesGenresDao;

public class TestMovieGenres {
	public static void main(String[] args) {

		MoviesGenresDao moviesGenresDao = new  MoviesGenresDao();
		Set<String> movie_category = moviesGenresDao.getMovieDistinctCategory();
		for(String category : movie_category) {
			System.out.println(category +" - "+category.length());
		}
		
		System.out.println("Total distinct movie category is "+movie_category.size());
	}
}

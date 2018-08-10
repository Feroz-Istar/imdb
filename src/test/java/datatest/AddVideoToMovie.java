package datatest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import imdb.Movies;
import imdbDao.MoviesDao;

public class AddVideoToMovie {
	public static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	public static void main(String[] args) throws Exception {
		MoviesDao moviedao = new MoviesDao();
		List<Movies> movies = moviedao.findAll();
		
		for(Movies mov: movies) {
			
		
		VideoResponse videoresponse =  sendGet() ;
		
		
		moviedao.updateMovie(mov,videoresponse.getUrl());
		
		}
	
	}
	
	private static VideoResponse sendGet() throws Exception {

		String url = "http://www.splashbase.co/api/v1/images/random?videos_only=true";
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
		
		VideoResponse  videoResponse =gson.fromJson(response.toString(), VideoResponse.class);
		
		return videoResponse;
	}
}

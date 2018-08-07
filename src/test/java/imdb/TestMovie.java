package imdb;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mongodb.util.JSON;

import imdbDao.MoviesDao;

public class TestMovie {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Object> movies = new ArrayList<Object>();
		MoviesDao moviesDao = new  MoviesDao();
		
		String csvFile = "C:\\Users\\istar\\Desktop\\movies.json";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        
        try {
        	BufferedReader reader = new BufferedReader(new FileReader(csvFile));
        	StringBuilder stringBuilder = new StringBuilder();
        	while ((line = reader.readLine()) != null) {
        		stringBuilder.append(line);
        	}
        	System.out.println(stringBuilder.toString());
        	List<Movies> mov = new Gson().fromJson(stringBuilder.toString(), new TypeToken<List<Movies>>(){}.getType());
        		System.out.println(mov.size());
        		System.out.println("******************************* end heres **********");
        		movies.addAll(mov);
            moviesDao.insertMany(movies);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
      }
        

}

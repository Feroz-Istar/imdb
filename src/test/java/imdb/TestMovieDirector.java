package imdb;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import imdbDao.DirectorsDao;
import imdbDao.MovieDirectorDao;

public class TestMovieDirector {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Object> directors = new ArrayList<Object>();
		MovieDirectorDao movieDirectorDao = new MovieDirectorDao();
		String csvFile = "C:\\Users\\istar\\Desktop\\movies_directors.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] director = line.split(cvsSplitBy);
               // System.out.println("id " + director[0]+" first_name "+director[1]);
                MovieDirector directors2 = new MovieDirector(Integer.parseInt(director[0].replaceAll("\"","")), Integer.parseInt(director[1].replaceAll("\"","")));
                directors.add(directors2);
            }
            //directors = directors.subList(880, 86880);
            
            movieDirectorDao.insertMany(directors);

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
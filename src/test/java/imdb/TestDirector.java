package imdb;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import imdbDao.DirectorsDao;

public class TestDirector {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Object> directors = new ArrayList<Object>();
		DirectorsDao directorsDao = new DirectorsDao();
		String csvFile = "C:\\Users\\Admin\\Desktop\\directors.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] director = line.split(cvsSplitBy);
                System.out.println("id " + director[0]+" first_name "+director[1]+" last_name "+director[2]);
                Directors directors2 = new Directors(Integer.parseInt(director[0].replaceAll("\"","")), director[1].replaceAll("\"",""), director[2].replaceAll("\"","")+"");
                directors.add(directors2);
            }
            directors = directors.subList(880, 86880);
            
            directorsDao.insertMany(directors);

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
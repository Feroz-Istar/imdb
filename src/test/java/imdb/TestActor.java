package imdb;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import imdbDao.ActorsDao;

public class TestActor {
	public static void main(String[] args) {
		List<Object> actors = new ArrayList<Object>();
		ActorsDao actorsDao = new  ActorsDao();
		
		String csvFile = "C:\\Users\\Admin\\Downloads\\actors.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] actor = line.split(cvsSplitBy);
                System.out.println(" actor id " + actor[0]+" first name "+actor[1]+" last_name "+actor[2]+" gender "+ actor[3]);
                Actors actors2 = new Actors(Integer.parseInt(actor[0].replaceAll("\"","")), actor[1].replaceAll("\"",""), actor[2].replaceAll("\"",""), actor[3].replaceAll("\"","")+"");
                actors.add(actors2);
                
            
            }
            
            
            actors = actors.subList(700000, 817718);
            
            actorsDao.insertMany(actors);

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

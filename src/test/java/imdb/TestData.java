package imdb;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import imdbDao.RolesDao;

public class TestData {

	public static void main(String[] args) {
		List<Object> directors = new ArrayList<Object>();
		RolesDao rolesDao = new RolesDao();
		String csvFile = "C:\\Users\\Admin\\Desktop\\roles.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] director = line.split(cvsSplitBy);
                //System.out.println("id " + director[0]+" first_name "+director[1]);
                Roles roles = new Roles(Integer.parseInt(director[0].replaceAll("\"","")),Integer.parseInt(director[1].replaceAll("\"","")),director[2].replaceAll("\"",""));
                directors.add(roles);
                
            }
            //3431966
            directors = directors.subList(3200000, 3431966);
            rolesDao.insertMany(directors);
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
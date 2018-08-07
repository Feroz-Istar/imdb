package imdb;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class ExcelReader {
    public static final String SAMPLE_XLSX_FILE_PATH = "C:\\Users\\Admin\\Downloads\\aa\\movies.xls";

    public static void main(String[] args) throws IOException, InvalidFormatException {

        // Creating a Workbook from an Excel file (.xls or .xlsx)
        Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));

        // Retrieving the number of sheets in the Workbook
        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

      
      
        /*
           ==================================================================
           Iterating over all the rows and columns in a Sheet (Multiple ways)
           ==================================================================
        */

        // Getting the Sheet at index zero
        Sheet sheet = workbook.getSheetAt(0);

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();

        // 1. You can obtain a rowIterator and columnIterator and iterate over them
       

        // 2. Or you can use a for-each loop to iterate over the rows and columns
        System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
        for (Row row: sheet) {
        	int temp =0;
            for(Cell cell: row) {
            	  String cellValue = dataFormatter.formatCellValue(cell);
            	switch(temp) {
            	case 0:
            		System.out.println("id is "+cell);
            		break;
            	case 1:
            		System.out.println("movie name is "+cell);

            		break;
            	case 2: 
            		System.out.println("year is "+cell);

            		break;
            	case 3:
            		System.out.println("rank is "+cell);

            		break;
            	}
            	
               temp++;
            }
            System.out.println();
        }

      
        // Closing the workbook
        workbook.close();
    }
}
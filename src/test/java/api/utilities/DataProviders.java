package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
   // in same class we can have N no of dataProviders() methods
	
	//DataProvider 1 to get all data from exel file
	
	@DataProvider(name="Data")
	public String [][] getAllData() throws IOException
	{
		String path=System.getProperty("user.dir")+"//testData//Userdata.xlsx";  // taking xl file from testData
	   
		ExcelUtility xl = new ExcelUtility(path); 
		
	    int rownum=xl.getRowCount("Sheet1");
	    int colcount=xl.getCellCount("Sheet1",1);
	  
	    // Create 2-D array which can store xl sheet data
	    
	    String apidata[][]=new String[rownum][colcount];
	    
	   // read data from xl that storing into 2-D array
	    for(int i=1;i<=rownum;i++)  // i=1 coz i=0 is header
	    {
	    	for(int j=0;j<colcount;j++)  // i is row and j is col
	    	{
	    	
				apidata[i-1][j]=xl.getCellData("Sheet1", i, j); // 1,0 
	    		// here i-1 at first 1-1=0 as array index starts from 0
	    	} 	
	    }
	       return apidata; // returning 2-D array
	}
	
	// DataProvider 2 to get only Usernames
	
	 @DataProvider(name="UserNames")
	 public String[] getUserNames() throws IOException 
	 {
		 String path=System.getProperty("user.dir")+"//testData//Userdata.xlsx";
		 ExcelUtility xl = new ExcelUtility(path); 
			
		    int rownum=xl.getRowCount("Sheet1");
		    
		    String apidata[]=new String[rownum];
		    
		    for(int i=1;i<=rownum;i++)
		    {
		    	apidata[i-1]= xl.getCellData("Sheet1",i, 1);
		    
		    }
		       return apidata;
	 
	 
	 }


}

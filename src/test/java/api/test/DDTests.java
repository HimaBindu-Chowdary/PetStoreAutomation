package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

/* Data Driven testing using Excel sheet, ExcelUtility.java class, DataProviders.java class
  DataProvider 1 method is used for Post Reuest and DataProvider 2 method is used for Delete Request by using UserNames

  */
public class DDTests {

	
	@Test (priority=1, dataProvider = "Data",dataProviderClass = DataProviders.class)  // if dataProvider is from another package we have to mention dataProvider class name also
	 public void  testPostUser(String userID, String userName,String fname,String lname,String useremail,String pwd,String Ph) // in same order as excel sheet
	  {
		  User userPayload =new User(); // here we get data from dataProvider 
		  
		  userPayload.setId(Integer.parseInt(userID));
		  userPayload.setUsername(userName);
		  userPayload.setFirstName(fname);
		  userPayload.setLastName(lname);
		  userPayload.setEmail(useremail);
		  userPayload.setPassword(pwd);
		  userPayload.setPhone(Ph);
		  
		
		  Response response = UserEndPoints.createUser(userPayload);
	 
		  response.then().log().all();
		  
		  Assert.assertEquals(response.getStatusCode(),200);
	  
	  }
	
	@Test (priority=2,dataProvider = "UserNames",dataProviderClass = DataProviders.class)
	 public void  testDeleteUserByName(String userName)
	 {
		  Response response = UserEndPoints.deleteUser(userName);
		  Assert.assertEquals(response.getStatusCode(),200);
	
	 }
	
}

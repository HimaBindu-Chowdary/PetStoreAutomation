package api.test;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import org.apache.logging.log4j.LogManager;
import io.restassured.response.Response;

// here we write validations code for all post,get,put and delete requests
public class UserTests {

	
// here we create data using faker class and calls methods by passing  data
	
	Faker faker;
	User userPayload;
	public Logger logger;  // for logs
	
	@BeforeClass
	public void setup()
	{
		faker=new Faker();  // create data here
		userPayload=new User(); // pass data here
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		// for logs
		logger=LogManager.getLogger(this.getClass());
	logger.debug("debugging......");
	
	}
	
	@Test (priority=1)
	 public void  testPostUser()
	  {
		  logger.info("********* Creating User **********");
		  Response response = UserEndPoints.createUser(userPayload);
	 
		  response.then().log().all();
		  
		  Assert.assertEquals(response.getStatusCode(),200);
		  logger.info("********* User is Created **********");
	  }
	
	@Test (priority=2)
	 public void  testGetUserByName()
	 {
		logger.info("********* Reading User Info **********");
		Response response = UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("********* User Info is Displayed **********");
	 }
	@Test (priority=3)
	 public void  tesUpdatetUserByName()
	  {
		logger.info("********* Updating User Info **********");
		
	 // Update data using payload 
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		  Response response = UserEndPoints.updateUser(this.userPayload.getUsername(),userPayload);
	 
		  response.then().log().body();
		  
		  Assert.assertEquals(response.getStatusCode(),200);
		  
		  // checking data after update
		  Response responseAfterUpdate = UserEndPoints.readUser(this.userPayload.getUsername());
		   
			Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
			logger.info("********* User Info is Updated **********");
	  }
	
	@Test (priority=4)
	 public void  testDeleteUserByName()
	 {
		logger.info("********* Deleting User **********");
		Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
		response.then().log().body().statusCode(200);
		
		logger.info("*********  User Deleted**********");
		// Assert.assertEquals(response.getStatusCode(),200);  Both status code validations are same
			
	 }
	
	
}

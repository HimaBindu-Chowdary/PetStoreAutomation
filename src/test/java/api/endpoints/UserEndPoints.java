package api.endpoints;
import static io.restassured.RestAssured.given;
import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


  // UserEndPoints.java
//   Created to perform Create, Read, Update, Delete requests for User module 

public class UserEndPoints {
	
	public static Response createUser(User payload)   // here User payload comes from User POJO class
	    {
		    Response response = given()
		    		                .headers("api_key", "allowme")
		                            .contentType(ContentType.JSON)
		                            .accept(ContentType.JSON)
		                            .body(payload)
		                      .when()
		                           .post(Routes.post_url);
		           
		          return response;
		    
	    }
	
	public static Response readUser(String userName)
    {
	    Response response = given()
	    		               .headers("api_key", "allowme") 
	    		               .pathParam("username", userName)
	                 .when()
	                      .get(Routes.get_url);
	           
	          return response;
	    
    }
	
	public static Response updateUser(String userName,User payload)
    {
	    Response response = given()
	    		              .headers("api_key", "allowme")
	                          .contentType(ContentType.JSON)
	                          .accept(ContentType.JSON)
	                          .pathParam("username", userName)
	                          .body(payload)
	                    .when()
	                          .put(Routes.update_url);
	           
	          return response;
	    
    }
	
	public static Response deleteUser(String userName)
    {
	    Response response = given()
	    		                .headers("api_key", "allowme")
	                            .pathParam("username", userName)
	                      .when()
	                            .delete(Routes.delete_url);
	           
	          return response;
	    
    }



}

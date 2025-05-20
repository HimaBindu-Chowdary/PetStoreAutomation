package api.endpoints;

  /*  from Swagger: URI - https://petstore.swagger.io/
   * Contains User Module, Store Module, Pet Module
   * 
   *  User module Url's:
   *  Create User (Post) : https://petstore.swagger.io/v2/user
      Get User (Get) : https://petstore.swagger.io/v2/user/{username}
      Update User (Put) : https://petstore.swagger.io/v2/user/{username}
      Delete User (Delete) : https://petstore.swagger.io/v2/user/{username}
   */


public class Routes {

	// Common url for all modules is making it as base url 
	public static String base_url="https://petstore.swagger.io/v2";
	
	// User Module
	public static String post_url= base_url+"/user";
	public static String get_url= base_url+"/user/{username}";
	public static String update_url= base_url+"/user/{username}";
	public static String delete_url= base_url+"/user/{username}";
	
	// Store Module
	              // Store Module urls
	  
	// Pet Module
	        // Pet Module urls
} 

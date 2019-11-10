package freeNowTest;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class freeNowTestNG {
	commonFunctions cf=new commonFunctions();  
	@Test
	public void findUser() {
		int id=0;
		//Fetches the response of the api - This will be used to fetch the list of users
	  Response responseUsers=cf.getRequest("https://jsonplaceholder.typicode.com/users");
	  
	  //Fetch the list of users into a string array to access the id of user Samantha
	  List<String> jsonResponse = responseUsers.jsonPath().getList("$");
	  System.out.println("array length "+jsonResponse.size());
	  
	  //Fetch the id of user Samantha
	  for (int i=0;i<jsonResponse.size();i++) {
		  String username=responseUsers.jsonPath().getString("username["+i+"]");
		  if (username.equalsIgnoreCase("Samantha")) {
			  id=responseUsers.jsonPath().getInt("id["+i+"]");
			  break;
		  }
	  }
	  System.out.println("Samantha's id is "+id);
	  
	  Response responsePosts=cf.getRequest("https://jsonplaceholder.typicode.com/posts?userId=3");
	  List<Integer> jsonIntResponse = responsePosts.jsonPath().getList("id");
	  
	  System.out.println("array length of posts "+jsonIntResponse.size());
	  
	  for (int j=0;j<jsonIntResponse.size();j++) {
		  int postId=responsePosts.jsonPath().getInt("id["+j+"]");
		  System.out.println("Post id "+postId);
		  
	  }
//		  given().
//          //param("username", "Samantha").
//  when().
//          get("https://jsonplaceholder.typicode.com/users").
//  then().
//	  }
//	  int arrayId[]=given().
//	          //param("username", "Samantha").
//	  when().
//	          get("https://jsonplaceholder.typicode.com/users").path("id", "3");
////	  then(). 
////	  extract().
////	          path("id");
//	  i=arrayId.length;
//	  System.out.println("array length "+i);
//
//	  String username =
//			  given().
//			          //param("username", "Samantha").
//			  when().
//			          get("https://jsonplaceholder.typicode.com/users").
//			  then().
//			  extract().
//			          path("username["+i+"]");
//	  System.out.println("Samantha's id is "+username);

//	  Response res = get("https://jsonplaceholder.typicode.com/users");
//	  assertEquals(200, res.getStatusCode());
//	  String json = res.asString();
//	  JsonPath jp = new JsonPath(json);
//	  assertEquals("onur@swtestacademy", jp.get("email"));
//	  assertEquals("Onur", jp.get("firstName"));
//	  assertEquals("Baskirt", jp.get("lastName"));
  }
}

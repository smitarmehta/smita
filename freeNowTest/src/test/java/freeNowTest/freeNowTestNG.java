package freeNowTest;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.Assert;
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
	  System.out.println("*****Fetching all users*****");
	  List<String> jsonResponse = responseUsers.jsonPath().getList("$");
	  System.out.println("Number of users are "+jsonResponse.size());
	  
	  //Fetch the id of user Samantha
	  System.out.println("*****Fetching Samantha's id*****");
	  for (int i=0;i<jsonResponse.size();i++) {
		  String username=responseUsers.jsonPath().getString("username["+i+"]");
		  if (username.equalsIgnoreCase("Samantha")) {
			  id=responseUsers.jsonPath().getInt("id["+i+"]");
			  break;
		  }
	  }
	  System.out.println("Samantha's id is "+id);
	  
	  System.out.println("*****Fetching Samantha's Posts*****");
	  //Fetch all the posts for Samantha's userId
	  Response responsePosts=cf.getRequest("https://jsonplaceholder.typicode.com/posts?userId=3");
	  List<Integer> jsonIntResponse = responsePosts.jsonPath().getList("id");
	  
	  System.out.println("Number of Samantha's posts are "+jsonIntResponse.size());
	  
	  //Fetch all the comments for all posts for Samantha's userId
	  System.out.println("*****Fetching comments on Samantha's Posts*****");
	  for (int j=0;j<jsonIntResponse.size();j++) {
		  int postId=responsePosts.jsonPath().getInt("id["+j+"]");
		  System.out.println("Post id "+postId);
		  Response responsePostComments=cf.getRequest("https://jsonplaceholder.typicode.com/posts/1/comments?postId="+postId);
		  
		//Fetch email ids from all the comments for all posts for Samantha's userId
		  List<String> jsonPostCommentsEmailResponse = responsePostComments.jsonPath().getList("email");
		  System.out.println("Number of comments for Postid "+postId+" is "+jsonPostCommentsEmailResponse.size());
		
		//Validate email ids from all the comments for all posts for Samantha's userId
		  System.out.println("*****Validating email ids from comments on Samantha's Posts*****");
		  for (int k=0;k<jsonPostCommentsEmailResponse.size();k++) {
			  String email=jsonPostCommentsEmailResponse.get(k);
			  System.out.println("Email id is "+email);
			  AssertJUnit.assertEquals(true, cf.validateEmail(email));
			  System.out.println("Email id is valid");
		  }
	  }
  }
}

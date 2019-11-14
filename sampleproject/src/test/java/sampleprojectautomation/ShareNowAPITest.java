package sampleprojectautomation;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class ShareNowAPITest {
	commonFunctions cf=new commonFunctions(); 
	@Test
	public void testdecklidstatus() {
		//Fetches the response of the api
		Response response= cf.getRequest("https://api.mercedes-benz.com/vehicledata_tryout/v1/vehicles/WDB111111ZZZ22222/resources/decklidstatus");
		int jsonResponseStatusCode = response.statusCode();
		//Fetching Response Body
		Map<String, String> decklidstatus = response.jsonPath().getMap("decklidstatus");
		String jsonResponseValue = decklidstatus.get("value");
		//Asserting status message and response body.
		System.out.println("Status code "+jsonResponseStatusCode);
		System.out.println("Response Body is "+response.asString());
		Assert.assertEquals(jsonResponseStatusCode, 200);
		Assert.assertEquals(jsonResponseValue, "false");
	}
	@Test
	public void testdoorstatusfrontlefts() {
	//Fetches the response of the api
	  Response response= cf.getRequest("https://api.mercedes-benz.com/vehicledata_tryout/v1/vehicles/WDB111111ZZZ22222/resources/doorstatusfrontleft");
	  int jsonResponseStatusCode = response.statusCode();
	//Fetching Response Body
	  Map<String, String> doorstatusfrontleft = response.jsonPath().getMap("doorstatusfrontleft");
	  String jsonResponseValue = doorstatusfrontleft.get("value");
	  String jsonResponseStatusMessage = response.asString();
	//Asserting status message and response body.
	  System.out.println("Status code "+jsonResponseStatusCode);
	  System.out.println("Status Body is "+jsonResponseStatusMessage);
	  Assert.assertEquals(jsonResponseStatusCode, 200);
	  Assert.assertEquals(jsonResponseValue, "false");  
  }
}

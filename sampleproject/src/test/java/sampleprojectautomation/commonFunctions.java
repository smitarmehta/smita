package sampleprojectautomation;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.util.regex.Pattern;
public class commonFunctions {
	public static Response getRequest(String endpoint) {
		RestAssured.defaultParser = Parser.JSON;

        return given().headers("Authorization",
                "Bearer " + "4c4c444c-v123-4123-s123-4c4c444c4c44","Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                        when().get(endpoint).
                        then().contentType(ContentType.JSON).extract().response();
    }
}

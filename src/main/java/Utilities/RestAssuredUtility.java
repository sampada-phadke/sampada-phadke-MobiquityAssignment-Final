package Utilities;
import io.restassured.path.json.JsonPath;
import static org.hamcrest.CoreMatchers.is;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import java.util.Map;

import org.apache.commons.validator.EmailValidator;
public class RestAssuredUtility {
	
	public static Response getWithNoParameters(String URI) {
		Response res = given().log().all().when().get(URI);
		return res;
	}
	
	public static void verifyStatusCode(Response res, int expectedStatusCode) {
		int status = res.getStatusCode();
		res.then().assertThat().statusCode(expectedStatusCode);
		
	}
	public static void verifyEmail(Response res, String email) {
		//int status = res.getStatusCode();
		res.then().assertThat().body("email", is(email));
	
		
	}
	public static Response getWithQueryparameters(String URI, String key,String value) {
		Response res = given().log().all().param(key, value).when().get(URI);
		return res;
		
		//System.out.println(res);
	}

	public static Response getWithQueryparameters(String URI, String authToken, Map queryParameters) {
		Response res = setAuthToken(authToken).params(queryParameters).when().get(URI);
		return res;
	}

	public static Response putWithQueryparameters(String authToken, Map queryParameters, String URI) {
		Response res = setAuthToken(authToken).params(queryParameters).when().put(URI);
		return res;
	}

	public static Response getWithPathParameters(String authToken, String URI) {
		Response res = setAuthToken(authToken).when().get(URI);
		return res;
	}
	
	public static RequestSpecification setAuthToken(String authToken) {
		return given().log().all().headers("iPlanetDirectoryPro", authToken);
	}
	
	public static Object getNodeValue(Response res, String node) {
		String json = res.asString();
		return JsonPath.with(json).get(node);
	}
	
	public static Response constructURL(String uri, String postid) {
		Response res = null;
		String[] replacements = postid.split(",");
	
		String url = uri;
	
		for (int i = 0; i < replacements.length; i++) {
			url = uri.replace(("postidparam"), replacements[i].trim());
			
			 res = RestAssuredUtility.getWithNoParameters(url);
			 
			
		}
		
		return res;
	}
	
	public static void verifyEmailAddress(Response res) {
		boolean isValidEmail = EmailValidator.getInstance().isValid(res.jsonPath().getString("email"));
		
	}
	
}

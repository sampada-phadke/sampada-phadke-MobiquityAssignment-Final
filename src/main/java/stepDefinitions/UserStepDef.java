package stepDefinitions;

import static org.testng.Assert.assertTrue;

import java.util.Map;

import org.testng.Reporter;

import Utilities.RestAssuredUtility;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
public class UserStepDef {
	
	private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

	private static String uid,postid,email;
	Response res;
	@Given("^I have access to UsersAPI$")
	public void i_have_access_to_UsersAPI() throws Throwable {
		
		 Reporter.log("I have access to Users API");
	}

	@When("^I send Get request for the user \"([^\"]*)\"$")
	public void i_send_Get_request_for_the_user(String username) throws Throwable {
		Reporter.log("Sending GET request to  users API to fetch details of user Delphine...");
		
		RestAssured.baseURI = BASE_URL+"/users";
		 Reporter.log("Sending request to.."+RestAssured.baseURI);
	    res =  RestAssuredUtility.getWithQueryparameters(RestAssured.baseURI, "username",username);
	    Reporter.log("Retrieve response of user Delphine...");
	    Reporter.log("Response is..."+res.asString());
		Reporter.log("Fetching the user id of Delphine from the response...");
		uid = res.jsonPath().getString("id").replaceAll("^.|.$", "");
		Reporter.log("The user id of Delphine is "+ uid);
	
		
			
	}

	@Then("^I should validate StatusCode$")
	public void i_should_validate_StatusCode() throws Throwable {
		Reporter.log("Validating the status code as 200");
		RestAssuredUtility.verifyStatusCode(res, 200);
		
	}
	@Given("^I have access to Posts API$")
	public void i_have_access_to_Posts_API() throws Throwable {
	   //
	}

	@When("^I send request to fetch Posts written by the user$")
	public void i_send_request_to_fetch_Posts_written_by_the_user() throws Throwable {
		RestAssured.baseURI = BASE_URL+"/users/"+uid+"/posts";
		 Reporter.log("Sending request to.."+RestAssured.baseURI);
		res =  RestAssuredUtility.getWithNoParameters(RestAssured.baseURI);
		  Reporter.log("Response is..."+res.asString());
		postid = res.jsonPath().getString("id").replaceAll("^.|.$", "");
		Reporter.log("The post id of user is "+ postid);
		//System.out.println("fetching the post id ... "+postid);
		
	}
	
	@Given("^I have access to Comments API$")
	public void i_have_access_to_Comments_API() throws Throwable {
	   
	}

	@When("^I send request to retrieve Comments written by the user$")
	public void i_send_request_to_retrieve_Comments_written_by_the_user() throws Throwable {
		RestAssured.baseURI = BASE_URL+"/posts/postidparam/comments";
		 Reporter.log("Sending request to.."+RestAssured.baseURI);
		 
		res  = RestAssuredUtility.constructURL(RestAssured.baseURI, postid);
		 Reporter.log("Response is..."+res.asString());
		 email = res.jsonPath().getString("email").replaceAll("^.|.$", "");
		
		//RestAssuredUtility.verifyEmail(res, email);
		
     	
	}
	
	@Then("^Validate emailID in comments section$")
	public void validate_emailID_in_comments_section() throws Throwable {
		//RestAssuredUtility.verifyEmail(res, email);
	}

}

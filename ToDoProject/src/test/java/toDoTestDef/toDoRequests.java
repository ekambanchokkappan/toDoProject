package toDoTestDef;
import org.json.simple.JSONObject;
import org.junit.Test;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;


public class toDoRequests {
		
	private static ResponseOptions<Response> resp = null;
	private JSONObject requestParams;
	private RestAssured request;
	public long postTimeTaken;
	public int postStatusCode;
	
	@Given("^I have End Point of toDo List API$")
	public void endPoint() throws Throwable {
		System.out.println("This is End point of toDo List : 'https://jsonplaceholder.typicode.com/todos'");
	}
	
	@When("^I GET the id '(\\d+)' details") 
	public void toDoGet(int testId) {
		resp=RestAssured.get("https://jsonplaceholder.typicode.com/todos/"+testId);
	}
	
	@And("^Validate the GET status code has '(\\d+)'$")
	public void getStatus(int ipCode) {
		int getCode= resp.getStatusCode();
		System.out.println("GET Status code is : " + getCode);
		Assert.assertEquals(getCode, ipCode);
		
	}
	@And("^Validate the time taken for GET request$")
	public void getTime() {
		long getTimeTaken = resp.getTime();
		System.out.println("Time taken for GET request is : " + getTimeTaken);		
	}

	@When("^I POST the new userid '(\\d+)', id '(\\d+)', title \"([^\"]*)\", completed \"([^\"]*)\" in to the list$")
	public void toDoPost(int userid, int id, String title, String comp)
	{		
		RestAssured.baseURI ="https://jsonplaceholder.typicode.com/todos";
		RequestSpecification request = RestAssured.given();
	 
		JSONObject requestParams = new JSONObject();
		requestParams.put("userId", userid); 
		requestParams.put("id", id);
		requestParams.put("title", title);
		requestParams.put("completed", comp);
	 
		request.body(requestParams.toJSONString());
		Response postResp = request.post("https://jsonplaceholder.typicode.com/todos/");
		postStatusCode = postResp.getStatusCode();
		postTimeTaken = postResp.getTime();
	}
	
	@And("^Validate the Post status code has '(\\d+)'$")
	public void postStatusCode(int ipStatusCode) {
		System.out.println("Post status Code is : " + postStatusCode);
		Assert.assertEquals(postStatusCode, ipStatusCode);		
	}
	
	@And("^Validate the time taken for POST request$")
	public void postTime() {
		System.out.println("Time taken for POST request is : " + postTimeTaken);			
	}
}

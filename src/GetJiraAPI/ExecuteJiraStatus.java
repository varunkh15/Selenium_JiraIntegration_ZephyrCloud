package GetJiraAPI;

import static io.restassured.RestAssured.given;
import java.net.URISyntaxException;
import org.testng.annotations.Test;
import com.thed.zephyr.cloud.rest.client.JWTGetExecutionList;
import com.thed.zephyr.cloud.rest.client.JWTUpdateExecution;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ExecuteJiraStatus {

	public String getJWT() throws URISyntaxException {
		String JwtToken;
		JWTGetExecutionList j = new JWTGetExecutionList();
		JwtToken = j.JWTToken();
		return JwtToken;
	}

	public String postJWT() throws URISyntaxException {
		String JwtToken;
		JWTUpdateExecution j = new JWTUpdateExecution();
		JwtToken = j.JWTToken();
		return JwtToken;
	}

	@Test(priority=1)
	public String getExecutionList() throws URISyntaxException {
		String token = getJWT();
		RestAssured.baseURI = "https://prod-api.zephyr4jiracloud.com";
		
		Response response = given().header("Authorization",new String(token))
				.header("zapiAccessKey","M2I5MTZiODgtYjUyZC0zODZiLThhOGUtOTk1YmJhNGFhMzQ3IDU1NzA1OCUzQWI0M2QwYTczLWI3NjMtNGRkZS05YTI2LTA1OTQ5NWIyYjIzZiBVU0VSX0RFRkFVTFRfTkFNRQ")
				.when().get("/connect/public/rest/api/1.0/executions?issueId=10001&projectId=10000");

		String executionId = response.jsonPath().getString("executions[2].execution.id");
		//	System.out.println(executionId);
		//	response.prettyPrint();	
			return executionId;
	}
	@Test(priority=2)
	public void UpdateExecutionStatus() throws URISyntaxException{
		String token = postJWT();
	    String getExecutionid = "[\""+getExecutionList()+"\"]";
		//System.out.println(s3);
		RestAssured.baseURI = "https://prod-api.zephyr4jiracloud.com";
		Response response =	given()
		.header("Authorization",new String(token))
		.header("Content-Type","application/json")
		.header("zapiAccessKey","M2I5MTZiODgtYjUyZC0zODZiLThhOGUtOTk1YmJhNGFhMzQ3IDU1NzA1OCUzQWI0M2QwYTczLWI3NjMtNGRkZS05YTI2LTA1OTQ5NWIyYjIzZiBVU0VSX0RFRkFVTFRfTkFNRQ")
		.body("{\"executions\":"+getExecutionid+",\"status\":1,\"clearDefectMappingFlag\":false,\"testStepStatusChangeFlag\":true,\"stepStatus\":-1}")
		.when()
		.post("/connect/public/rest/api/1.0/executions");	
	//	body(sample.payload.AddPlace())		
		response.prettyPrint();
	}
}

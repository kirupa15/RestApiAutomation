package com.api.createAccount;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.api.TestBase;
import com.api.models.AccountsBody;
import com.api.models.RequestBuilder;
import com.api.utils.reporter.ExtentLogger;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserRegisterwithExistingUser extends TestBase{

	private RequestSpecification rs;

	@BeforeTest
	public void beforeTest() {
		rs = RequestBuilder.buildRequest(); // this is added before test since we don't to have repeat in each test
											// method
	}

	@Test(description = "User Registeration Invalid Username")
	
	public void existingUserRegister() {
		
		
		AccountsBody authBody = AccountsBody
				.builder()
				.setUserName("Kirupa")
				.setEmailId("testuser1234@gmail.com")
				.setPassword("Welcome@123")
				.setRoleID("66c75e85dff4c1e686cca7c6")
				.setUserType(1).build();
		
		RequestSpecification requestSpecification = rs.body(authBody);
		Response response = requestSpecification.post("/CEDUA/users/user_registeration").then().extract().response();

		ExtentLogger.logRequest(requestSpecification);
		ExtentLogger.logResponse(response.prettyPrint());
		
		String errorResponse= response.jsonPath().getString("error");
		
		
		ExtentLogger.logResponseInReport("Response Message", errorResponse);
		
		if(errorResponse != null&&errorResponse.equalsIgnoreCase("Email id already exists")) {
			ExtentLogger.pass(" Success: Error response is valid with expected");
		}
		else {
			ExtentLogger.logFailureDetails(" Error in Response message : " + response.jsonPath().getString("message"));
		}
		
		if (response.statusCode() != 409) {
			ExtentLogger.logFailureDetails(response.statusCode() + " actual status code is not equal to 409");
		} else {
			ExtentLogger.pass("API Status code is 409 : Conflict response");
		}
		//Assert.assertEquals(response.statusCode(), 409);
	}

}

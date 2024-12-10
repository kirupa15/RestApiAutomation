package com.api.identitymanagement;

import io.restassured.http.Cookies;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.api.TestBase;
import com.api.models.RequestBuilder;
import com.api.models.UserAuthBody;
import com.api.utils.reporter.ExtentLogger;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AuthenticateUser extends TestBase {

	static Cookies authCookies;
	private RequestSpecification rs;

	@BeforeTest
	public void beforeTest() {
		rs = RequestBuilder.buildRequest(); // this is added before test since we don't to have repeat in each test
											// method
	}

	@Test
	public void authenticateUserwithValidEmail() {

		UserAuthBody authBody = UserAuthBody.builder().setEmailId("testuser1234@gmail.com").setPassword("Welcome@123")
				.setFingerPrint("zwedaszsdzw").build();

		RequestSpecification requestSpecification = rs.body(authBody);
		Response response = requestSpecification.post("/CEDUIdenty/identity/authenticate").then().extract().response();

		ExtentLogger.logRequest(requestSpecification);
		ExtentLogger.logResponse(response.prettyPrint());

		if (response.statusCode() != 200) {
			ExtentLogger.logFailureDetails(response.statusCode() + " actual status code is not equal to 200");
		} else {
			ExtentLogger.pass("API Status code is 200");
		}

		Assert.assertEquals(response.statusCode(), 200);

		// Extract and store cookies after authentication
		authCookies = response.detailedCookies(); // Store cookies in the variable
		System.out.println(authCookies);
	}

}

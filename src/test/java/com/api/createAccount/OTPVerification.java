package com.api.createAccount;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.api.TestBase;
import com.api.models.AccountsBody;
import com.api.models.OTPBody;
import com.api.models.RequestBuilder;
import com.api.utils.reporter.ExtentLogger;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class OTPVerification extends TestBase{

		private RequestSpecification rs;

		@BeforeTest
		public void beforeTest() {
			rs = RequestBuilder.buildRequest(); // this is added before test since we don't to have repeat in each test
												// method
		}

		@Test(description = "User Registeration with Valid Request")
		
		public void otpVerification() {
			
			ExtentLogger.logInfo(" == New User Registeration == ");
			
			OTPBody otpBody = OTPBody.builder().setEmailId("testuser1234@gmail.com").setOtp("123456").build();
			
			RequestSpecification requestSpecification = rs.body(otpBody);
			Response response = requestSpecification.post("/CEDUA/users/otp_verify").then().extract().response();

			ExtentLogger.logRequest(requestSpecification);
			
			if (response.statusCode() != 200) {
				ExtentLogger.logFailureDetails(response.statusCode() + " actual status code is not equal to 200");
			} else {
				ExtentLogger.pass("API Status code is 200");
			}
			Assert.assertEquals(response.statusCode(), 200);
		}

	}

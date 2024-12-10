package com.api.identitymanagement;

import com.api.TestBase;
import com.api.models.RequestBuilder;
import com.api.utils.reporter.ExtentLogger;

import io.restassured.http.Cookies;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;



public class AuthorizeUser extends TestBase {

    private RequestSpecification requestSpecification;
   

    @BeforeTest
    public void beforeTest() {
        requestSpecification = RequestBuilder.buildRequest().log().all();
    }

    @Test
    public void authorizeUser() {
        Response response = given().cookies(AuthenticateUser.authCookies).spec(requestSpecification)
                .get(TestBase.baseSetup.getAppApiUrl() + "/CEDUIdenty/identity/authorize");
        response.prettyPrint();

        ExtentLogger.logRequestInReport(TestBase.baseSetup.getAppApiUrl() + "/CEDUIdenty/identity/authorize");
        
        ExtentLogger.logRequest(requestSpecification);
        ExtentLogger.logResponse(response.prettyPrint());
        
        if (response.statusCode() != 200) {
            ExtentLogger.logFailureDetails(response.statusCode() + " actual status code is not equal to 200");
        } else {
            ExtentLogger.pass("API Status code is 200");
        }
        //Assert.assertEquals(response.statusCode(), 200);
    }
}


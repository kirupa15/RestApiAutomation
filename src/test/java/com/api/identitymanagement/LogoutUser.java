package com.api.identitymanagement;

import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.api.TestBase;
import com.api.models.RequestBuilder;
import com.api.utils.reporter.ExtentLogger;

import io.restassured.http.Cookies;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class LogoutUser extends TestBase {

    private RequestSpecification requestSpecification;

    @BeforeTest
    public void beforeTest() {
        requestSpecification = RequestBuilder.buildRequest().log().all();
    }

    @Test
    public void logoutUser() {
        Response response = given().cookies(AuthenticateUser.authCookies).spec(requestSpecification)
                .get(TestBase.baseSetup.getAppApiUrl() + "/CEDUIdenty/identity/logout");
        response.prettyPrint();

        ExtentLogger.logRequestInReport(TestBase.baseSetup.getAppApiUrl() + "/CEDUIdenty/identity/logout");
        
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


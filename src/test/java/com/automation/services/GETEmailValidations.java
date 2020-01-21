package com.automation.services;

import static io.restassured.RestAssured.when;
import org.hamcrest.Matchers;
import com.automation.utilities.ServiceHelper;

public class GETEmailValidations {

	/**
	 * searchUser()
	 * 
	 * This method will search for the specified user and 
	 * verifies the username if present
	 * 
	 */
	public void searchUser_SuccessTest() {
		try {
			 when().get(ServiceHelper.getUrlValue() + "users?username=Samantha")
			.then().statusCode(200).assertThat()
			.body("username", Matchers.hasItem("Samantha"));
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}

}

package com.automation.services;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.is;
import java.util.List;
import org.hamcrest.Matchers;
import com.automation.utilities.ServiceHelper;

public class GETEmailValidations {

	public static List<Integer> userId;

	/**
	 * searchUser()
	 * 
	 * This method will search for the specified user and verifies the username if
	 * to get userId present
	 * 
	 */
	public void searchUser_SuccessTest() {
		try {
			userId = when()
					.get(ServiceHelper.getUrlValue("userUrl") + "?username=" + ServiceHelper.getUser("socialUser"))
					.then().statusCode(200).assertThat()
					.body("username", Matchers.hasItem(ServiceHelper.getUser("socialUser")))
					.extract().jsonPath()
					.getList("id");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * searchUser_NotFoundTest()
	 * 
	 * This method will verify if calling invalid API fetches Not Found Status code
	 * 
	 */
	public void searchUser_NotFoundTest() {
		try {
			when().get(ServiceHelper.getUrlValue("userUrl") + "/username="+ServiceHelper.getUser("socialUser"))
			.then().statusCode(404).assertThat()
			.body("username", is(nullValue()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

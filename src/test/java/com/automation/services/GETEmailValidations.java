package com.automation.services;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Matchers;
import org.testng.Reporter;
import org.testng.SkipException;

import com.automation.utilities.ServiceHelper;

public class GETEmailValidations {

	public static List<Integer> userIds;

	/**
	 * searchUser_SuccessTest()
	 * 
	 * This method will search for the specified user and verifies the username 
	 * if present to get userId 
	 * 
	 */
	public void searchUser_SuccessTest() {
		try {
			userIds = when()
					.get(ServiceHelper.getUrlValue("userUrl") + "?username=" + ServiceHelper.getUser("socialUser"))
					.then().statusCode(200).assertThat()
					.body("username", Matchers.hasItem(ServiceHelper.getUser("socialUser"))).extract().jsonPath()
					.getList("id");
			if(userIds==null) {
				throw new SkipException("No user found with the name " +ServiceHelper.getUser("socialUser"));
			}
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
			when().get(ServiceHelper.getUrlValue("userUrl") + "/username=" + ServiceHelper.getUser("socialUser"))
			.then().statusCode(404).assertThat().body("username", is(nullValue()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * validateEmailsOfUser()
	 * 
	 * This method will perform email validations
	 */
	public void validateEmailsOfUser() {
		try {
			List<String> listOfInvalidEmails = new ArrayList<String>();
			
			for (int u = 0; u < userIds.size(); u++) {
				List<Integer> postIds = 
						 when()
						.get(ServiceHelper.getUrlValue("postUrl") + "?userId=" + userIds.get(u))
						.then().statusCode(200).extract().jsonPath().getList("id");
				if (postIds != null) {

					// loop through the posts
					for (int i = 0; i < postIds.size(); i++) {

						// get comment id's based on post Id's
						List<Integer> commentIds = when()
								.get(ServiceHelper.getUrlValue("commentUrl") + "?postId=" + postIds.get(i))
								.then().statusCode(200).extract().jsonPath().getList("id");

						// check if list of comment Id is null and loop through the list of comments to validate email's
						if (commentIds != null) {
							for (int j = 0; j < commentIds.size(); j++) {
								System.out
										.println(" Post Id: " + postIds.get(i) + " Comment  Id: " + commentIds.get(j));
								String email = 
										 when()
										.get(ServiceHelper.getUrlValue("commentUrl") + "?id=" + commentIds.get(j))
										.then().statusCode(200)
										.extract().jsonPath().get("[0].email").toString();

								// check if the email on the comments is valid
								if (ServiceHelper.validateEmail(email) == false) {
									listOfInvalidEmails.add(email);
								}
							}
						}
					} // to get the list of invalid emails
					if (!listOfInvalidEmails.isEmpty()) {
						Reporter.log(listOfInvalidEmails.toString(), true);
					}
				} else {
					throw new SkipException("No user found with the name " +ServiceHelper.getUser("socialUser"));
			  }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

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
	
	/**
	 * validateEmailsOfUser()
	 * 
	 * This method will perform email validations
	 */
	public void validateEmailsOfUser() {
		try {
			List<String> listOfInvalidEmails = new ArrayList<String>();
			
			// call search user service and get the userId if present
			searchUser_SuccessTest();
			
			
			for(int u=0;u<userId.size();u++) {
			List<Integer> postIds = when().get(ServiceHelper.getUrlValue("postUrl") + "?userId=" + userId.get(u)).then()
					.statusCode(200).extract().jsonPath().getList("id");
			if(postIds != null) {

				// loop through the posts
				for (int i = 0; i < postIds.size(); i++) {

					// get comment id's based on post Id's
					List<Integer> commentIds = when()
							.get(ServiceHelper.getUrlValue("commentUrl") + "?postId=" + postIds.get(i))
							.then()
							.statusCode(200)
							.extract().jsonPath()
							.getList("id");
					
					//check if list of comment Id is null
					if (commentIds != null) {
						for (int j = 0; j < commentIds.size(); j++) {

							System.out.println(
									
									" Post Id: "+postIds.get(i)+" Comment  Id: "+commentIds.get(j));
							
							String email = 
									 when()
									.get(ServiceHelper.getUrlValue("commentUrl") + "?id=" + commentIds.get(j))
									.then()
									.statusCode(200).extract().jsonPath()
									.get("[0].email").toString();

							// check if the email on the comments is valid
							if (ServiceHelper.validateEmail(email) == false) {
								listOfInvalidEmails.add(email);
							}
						}
					}
			   } //to get the list of invalid emails
				if(!listOfInvalidEmails.isEmpty()) {
					Reporter.log(listOfInvalidEmails.toString(), true);
				}
			} else {
				throw new SkipException("No posts found for the specified user");
			}
		  }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

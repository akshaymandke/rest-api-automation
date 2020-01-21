package com.automation.tests;

import org.testng.annotations.Test;

import com.automation.services.GETEmailValidations;

public class EmailValidationTests {

	@Test(groups = { "emailValidations" })
	public void verifyUserIsPresent() {
		GETEmailValidations getUser = new GETEmailValidations();
		getUser.searchUser_SuccessTest();
	}

	@Test(groups = { "emailValidations" })
	public void verifyInvalid_Users_Api() {
		GETEmailValidations invalidApi = new GETEmailValidations();
		invalidApi.searchUser_NotFoundTest();
	}
}

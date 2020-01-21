package com.automation.tests;

import org.testng.annotations.Test;

import com.automation.services.GETEmailValidations;

public class EmailValidationTests {

	@Test(groups = { "emailValidations"},priority=0,enabled=true)
	public void verifyUserIsPresent() {
		GETEmailValidations getUser = new GETEmailValidations();
		getUser.searchUser_SuccessTest();
	}

	@Test(groups = { "emailValidations"},priority=1,enabled=true)
	public void verifyInvalid_Users_Api() {
		GETEmailValidations invalidApi = new GETEmailValidations();
		invalidApi.searchUser_NotFoundTest();
	}
	
	@Test(groups= {"emailValidations"},priority=2,enabled=true)
	public void verifyEmails() {
		GETEmailValidations validateEmail=new GETEmailValidations();
		validateEmail.validateEmailsOfUser();
	}
}

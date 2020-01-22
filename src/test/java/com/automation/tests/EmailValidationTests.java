package com.automation.tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.automation.services.GETEmailValidations;

public class EmailValidationTests {

	@BeforeTest
	public void startExecution() {
		System.out.println("Test Execution Started");
	}
	
	@Test(groups = {"emailValidations"},priority=0,enabled=true)
	public void verifyUserIsPresent() {
		GETEmailValidations getUser = new GETEmailValidations();
		getUser.searchUser_SuccessTest();
	}

	@Test(groups = {"emailValidations"},priority=1, enabled=true)
	public void verifyInvalid_Users_Api() {
		GETEmailValidations invalidApi = new GETEmailValidations();
		invalidApi.searchUser_NotFoundTest();
	}
	
	@Test(groups= {"emailValidations"},priority=2,enabled=true)
	public void verifyEmails() {
		GETEmailValidations validateEmail=new GETEmailValidations();
		validateEmail.validateEmailsOfUser();
	}
	
	@AfterTest
	public void runCompletion() {
		System.out.println("Test Execution Completed");
	}
}

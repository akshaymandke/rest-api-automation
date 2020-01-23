package com.automation.tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.automation.services.GETEmailValidations;

public class EmailValidationTests {

	private GETEmailValidations emailValidate;

	@BeforeClass
	public void init() {
		emailValidate = new GETEmailValidations();
	}
	
	@BeforeTest
	public void startExecution() {
		System.out.println("Test Execution Started");
	}
	
	@Test(groups = {"emailValidations"},priority=0,enabled=true)
	public void verifyUserIsPresent() {
		emailValidate.searchUser_SuccessTest();
	}

	@Test(groups = {"emailValidations"},priority=2, enabled=true)
	public void verifyInvalid_Users_Api() {
		emailValidate.searchUser_NotFoundTest();
	}
	
	@Test(groups= {"emailValidations"},dependsOnMethods= {"verifyUserIsPresent"},priority=1,enabled=true)
	public void verifyEmailsOnComments() {
		emailValidate.validateEmailsOfUser();
	}
	
	@Test(groups = {"emailValidations"},priority=3, enabled=true)
	public void verifyApiPayload_EmptyQueryParameter() {
		emailValidate.emptyQueryParameter();
	}
	
	@Test(groups = {"emailValidations"},priority=4, enabled=true)
	public void verifyApiPayload_InvalidUsername() {
		emailValidate.wrongUsername();
	}
	
	@AfterTest
	public void runCompletion() {
		System.out.println("Test Execution Completed");
	}
}

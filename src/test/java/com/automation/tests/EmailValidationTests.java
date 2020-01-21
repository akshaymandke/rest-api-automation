package com.automation.tests;

import org.testng.annotations.Test;

import com.automation.services.GETEmailValidations;

public class EmailValidationTests {

	
	@Test(groups= {"emailValidations"})
	public void verifyUserIsPresent() {
		
		GETEmailValidations getUser=new GETEmailValidations();
		getUser.searchUser_SuccessTest();
	}
}

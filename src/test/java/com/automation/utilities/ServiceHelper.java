package com.automation.utilities;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceHelper {
	

	/**
	 * getUrlValue() 
	 * This method is used to fetch the api url value based
	 * on the parameter passed
	 * @param key
	 * @return {@url String}
	 */
	public static String getUrlValue(String key) {
		String returnValue = null;
		try {
			Properties property = new Properties();

			property.load(ServiceHelper.class.getClassLoader().getResourceAsStream("apiUrl.properties"));
			returnValue = property.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnValue;
	}
	
	/**
	 * getUser() 
	 * This method is used to fetch the username based 
	 * on the parameter passed
	 * @param key
	 * @return {@url String}
	 */
	public static String getUser(String key) {
		String returnValue = null;
		try {
			Properties property = new Properties();

			property.load(ServiceHelper.class.getClassLoader().getResourceAsStream("users.properties"));
			returnValue = property.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnValue;
	}
	
	/**
	 * validateEmail 
	 * This method is used to validate Emails
	 * 
	 * @return {@true/false boolean}
	 */
	public static boolean validateEmail(String email) {
		String emailRegex = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
		Pattern pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}

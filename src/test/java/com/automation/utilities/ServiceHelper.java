package com.automation.utilities;

import java.util.Properties;

public class ServiceHelper {
	

	/**GetValue()
	 * This method is used to fetch the api url value
	 * 
	 * @return {@url String}
	 */
	public static String getUrlValue() {
		String returnValue = null;
		try {
			Properties property = new Properties();

			property.load(ServiceHelper.class.getClassLoader().getResourceAsStream("apiUrl.properties"));
			returnValue = property.getProperty("baseUrl");
		} catch (Exception e) {
			e.printStackTrace();

		}
		return returnValue;

	}
}

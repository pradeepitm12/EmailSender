package com.app.Utility;

import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
	
	protected static Properties properties = null;
	public static String getProperty(String propertyName) {

		String propertyValue = "";
		try {
			if (properties == null)
			{
				properties = new Properties();
				InputStream in = PropertyReader.class
						.getResourceAsStream("/email_config.properties");
				properties.load(in);
				in.close();
			}

			propertyValue = properties.getProperty(propertyName);
			if (propertyValue != null)
				propertyValue = propertyValue.trim();
			else
				propertyValue = "";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return propertyValue;
	}

}

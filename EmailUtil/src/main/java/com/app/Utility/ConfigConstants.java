package com.app.Utility;

public final  class ConfigConstants {
	private ConfigConstants(){
	}

	public static final String SENDER_ID=PropertyReader.getProperty("sender_email").trim();
	public static final String SENDER_PASS=PropertyReader.getProperty("sender_password").trim();
	public static final String HOST=PropertyReader.getProperty("host").trim();
	public static final String SMTP_AUTH=PropertyReader.getProperty("smtp_auth").trim();
	public static final String PROTOCOL= PropertyReader.getProperty("protocol").trim();
	public static final String PORT =PropertyReader.getProperty("port").trim();
	public static final String ENABLE_START_TLS=PropertyReader.getProperty("enable_start_tls").trim();
	public static final String ADMIN_EMAIL_ADDRESS=PropertyReader.getProperty("admin_email_address").trim();
	
	
}

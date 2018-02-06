package com.app.Utility;

public class HalfHourDetailsUtil{
	String file;
	String[] cc;
	String[] bcc;
	String subject="Half Hourly Mail";
	String body="This mail contains detials of all the mails send in last half hour.";
	String[]to={ConfigConstants.ADMIN_EMAIL_ADDRESS};

	public HalfHourDetailsUtil(String file) {
		this.file=file;

	}
	public void send(){
		EmailUtility email=new EmailUtility(to,cc ,bcc,subject , "",file);
		Thread t=new Thread(email);
		t.start();
	}

}

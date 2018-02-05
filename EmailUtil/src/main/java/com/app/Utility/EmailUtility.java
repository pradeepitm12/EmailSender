package com.app.Utility;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailUtility implements Runnable{
	String[] to;
	String [] cc;
	String[] bcc;
	String subject;
	String body;
	String attachment;	
	public EmailUtility(String[] to,String [] cc, String[] bcc, String subject, String body,String attachment){
		this.to=to;
		this.cc=cc;
		this.bcc=bcc;
		this.subject=subject;
		this.body=body;
		this.attachment=attachment;
	}

	public void send(/*String[] to,String [] cc, String[] bcc, String subject, String body,String attachment*/){

		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", ConfigConstants.ENABLE_START_TLS);
		props.put("mail.smtp.host", ConfigConstants.HOST);
		props.put("mail.smtp.user", ConfigConstants.SENDER_ID );
		props.put("mail.smtp.password", ConfigConstants.SENDER_PASS);
		props.put("mail.smtp.port", ConfigConstants.PORT);
		props.put("mail.smtp.auth", ConfigConstants.SMTP_AUTH);

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(ConfigConstants.SENDER_ID ));
			InternetAddress[] toAddress = new InternetAddress[to.length];
			InternetAddress[] ccAddress = new InternetAddress[cc.length];
			InternetAddress[] bccAddress = new InternetAddress[bcc.length];

			// To get the array of addresses
			for( int i = 0; i < to.length; i++ ) {
				toAddress[i] = new InternetAddress(to[i]);
			}

			for( int i = 0; i < toAddress.length; i++) {
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}

			if(cc[0].length()>0){

				for( int i = 0; i < cc.length; i++ ) {
					ccAddress[i] = new InternetAddress(cc[i]);
				}
				for(int i= 0;i<ccAddress.length;i++){
					message.addRecipient(Message.RecipientType.CC, ccAddress[i]);

				}

			}

			if(bcc[0].length()>0){
				for( int i = 0; i < bcc.length; i++ ) {
					bccAddress[i] = new InternetAddress(bcc[i]);
				}
				for(int i= 0;i<ccAddress.length;i++){
					message.addRecipient(Message.RecipientType.BCC, bccAddress[i]);

				}

			}
			message.setSubject(subject);
			//message.setText(body);
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(body);
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			if(attachment!=null && attachment.length()>1){
				messageBodyPart = new MimeBodyPart();
				File file=new File(attachment);
				DataSource source = new FileDataSource(file);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName("Attachement");
				multipart.addBodyPart(messageBodyPart);
			}
			message.setContent(multipart);

			Transport transport = session.getTransport(ConfigConstants.PROTOCOL);
			transport.connect(ConfigConstants.HOST, ConfigConstants.SENDER_ID , ConfigConstants.SENDER_PASS);
			transport.sendMessage(message, message.getAllRecipients());
			System.out.println("SENT");
			transport.close();
			System.out.println("close");
		}
		catch (AddressException ae) {
			ae.printStackTrace();
		}
		catch (MessagingException me) {
			me.printStackTrace();
		}


	}
	public void run(){
		send();
	}



}

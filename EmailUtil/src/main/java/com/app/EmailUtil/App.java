package com.app.EmailUtil;

import java.sql.Time;
import java.util.Scanner;

import com.app.Utility.EmailUtility;

/**
 * Hello world!
 *
 */
public class App 
{
	static boolean hasNext=true;
	String [] to;
	String [] cc;
	String [] bcc;
	String subject;
	String body;
	String attachment;
	public static void main( String[] args )
	{
		
		while(hasNext){
			String [] to;
			String [] cc;
			String [] bcc;
			String subject;
			String body;
			String attachment;
		System.out.println("\t\t************ Email Service ************");
		System.out.println("To give multiple email address for To, CC, and Bcc just give input with comma "
				+ "\n Example :- "
				+ "\n To \t- abc@gmail.com, \t def@gmail.com \n"
				+ " cc \t- ghi@gmail.com, \t jkl@gmail.com\n"
				+ " bcc \t- mno@gamil.com, \t pqrst@gmail.com \n"
				+ "Note :- If dont want to give cc and bcc press enter");
		System.out.println(" \t\t*************************************");
		Scanner in =new Scanner(System.in);
		System.out.print("To :-");
		to=in.nextLine().split(",");
		System.out.println("");
		System.out.print("CC :-");
		cc=in.nextLine().split(",");
		System.out.println("");
		System.out.println("BCC :-");
		bcc=in.nextLine().split(",");
		System.out.println("Please enter the subject -:");
		subject=in.nextLine();
		System.out.println("Please enter the body -:");
		body=in.nextLine();
		System.out.println("Do you want to attach a file yes/no");

		if(in.nextLine().equalsIgnoreCase("yes")){
			System.out.println("Enter the file path");
			attachment=in.nextLine();
		}else{
			attachment=null;
			System.out.println("Attachment not applicable");
		}

		System.out.println("Want to send more emails yes/no");
		if(in.nextLine().equalsIgnoreCase("yes")){
			hasNext=true;
		}
		else
			hasNext=false;
		EmailUtility email=new EmailUtility(to,cc ,bcc, subject, body,attachment);
		Thread t=new Thread(email);
		t.start();
		
	
		
		}
	}
	

	
}

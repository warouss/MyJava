package com.warouss.apache.commons.mail;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class TestMail {

	public static void main(String[] args) {
		new TestMail().execute();
	}
	
	private void execute() {
		try {
			simpleTest();
			
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
	
	private void simpleTest() throws EmailException {
		System.out.println("START simpleTest() --------------------------------");
		Email email = new SimpleEmail();
//		email.setHostName("smtp.googlemail.com");
//		email.setSmtpPort(465);
//		email.setAuthenticator(new DefaultAuthenticator("username", "password"));
//		email.setSSLOnConnect(true);
		email.setHostName("localhost");
		email.setSmtpPort(25);
		email.setFrom("test@local.com");
		email.setSubject("TestMail");
		email.setMsg("This is a test mail ... :-)");
		email.addTo("warouss@netik.fr");
		email.send();
	}

}

package com.pats;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	
	private static final String DEFAULT_SMTP = "lonsrv-ex01.patsystems.com";
	private static final String DEFAULT_SENDER = "ROBOTFRAMEWORK@patsystems.com";
	
	/**
	 * Sends email message from defaut sender and to pre-configured users
	 * @param subject subject of the e-mail
	 * @param message message body
	 * @throws MessagingException 
	 */
	public static void sendMessage(String recipients, String subject, String message) 
			throws MessagingException{
		sendMessage(buildMessage(DEFAULT_SMTP, DEFAULT_SENDER, recipients, subject, message));
	}
	
	/**
	 * Sends specified email message
	 * @param message Message
	 */
	public static void sendMessage(Message message) throws MessagingException {
		Transport.send(message);
	}
	
	/**
	 * Builds email message based on given arguments. 
	 * @param smtp smtp server
	 * @param sender email address of sender
	 * @param addresses addresses of recipients
	 * @param subject subject of mail
	 * @param msgContent content of mail
	 * @return Message 
	 * @throws MessagingException
	 */
	public static Message buildMessage(String smtp, String sender, String addresses, 
			String subject, String msgContent) throws MessagingException{

		//Set the host smtp address
		Properties props = new Properties();
		props.put("mail.smtp.host", smtp);
		
		// create some properties and get the default Session
		Session session = Session.getDefaultInstance(props, null);
		
		// create a message
		Message msg = new MimeMessage(session);

		// set the from and to address
		msg.setFrom(new InternetAddress(sender));
		
		if (addresses!=null) {
			String[] addressArray = addresses.split(";");
			InternetAddress[] recipients = new InternetAddress[addressArray.length]; 
			for(int i=0;i<addressArray.length;i++){
				recipients[i] = new InternetAddress(addressArray[i]);
			}
			msg.setRecipients(Message.RecipientType.TO, recipients);
		}
		msg.setSubject(subject);
		msg.setContent(msgContent, "text/plain");
		
		return msg;
	}

}

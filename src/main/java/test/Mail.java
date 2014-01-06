package test;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

	public void sendBasicMail(String... recipientsList){

		String smtp = "lonsrv-ex01.patsystems.com";
		String sender = "Test@pats.com";
		String addresses = "damian.holda@patsystems.com;Jacob.Kennedy@patsystems.com";
		String subject = "Test Mail";
		String msgContent = "Just a small test";

		//Set the host smtp address
		Properties props = new Properties();
		props.put("mail.smtp.host", smtp);

		// create some properties and get the default Session
		Session session = Session.getDefaultInstance(props, null);

		// create a message
		Message msg = new MimeMessage(session);

		// set the from and to address
		try {
			msg.setFrom(new InternetAddress(sender));
			
			if (recipientsList==null||recipientsList.length==0) {
				recipientsList = addresses.split(";");
			}
			
			InternetAddress[] recipients = new InternetAddress[recipientsList.length]; 
			for(int i=0;i<recipientsList.length;i++){
				recipients[i] = new InternetAddress(recipientsList[i]);
			}
			msg.setRecipients(Message.RecipientType.TO, recipients);
			msg.setSubject(subject);
			msg.setContent(msgContent, "text/plain");
			
			Transport.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}

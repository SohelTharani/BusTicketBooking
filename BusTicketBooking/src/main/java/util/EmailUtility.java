package util;

import java.util.HashMap;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class EmailUtility {

	public static void sendEmail(String to, String subject, String body) {
		
	      // email ID of  Sender.
	      String sender = "systembusbooking@gmail.com";
	  
	      String host = "smtp.gmail.com";
	      final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	      // Getting system properties
	      Properties properties = System.getProperties();
	      properties.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
	      properties.setProperty("mail.smtp.port", "465");
	      properties.setProperty("mail.smtp.socketFactory.port", "465");
	      properties.setProperty("mail.smtps.auth", "true");
	      // Setting up mail server
	      properties.setProperty("mail.smtp.host", host);
	  
	      // creating session object to get properties
	      Session session = Session.getInstance(properties,
	    	         new javax.mail.Authenticator() {
	    	            protected PasswordAuthentication getPasswordAuthentication() {
	    	               return new PasswordAuthentication("systembusbooking@gmail.com", "java@123");
	    	            }
	    		});

	      try 
	      {
	         // MimeMessage object.
	         Message message = new MimeMessage(session);
	  
	         // Set From Field: adding senders email to from field.
	         message.setFrom(new InternetAddress(sender));
	  
	         // Set To Field: adding recipient's email to from field.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	  
	         // Set Subject: subject of the email
	         message.setSubject(subject);
	         Multipart multipart = new MimeMultipart("mixed");
	         // Html
	         final MimeBodyPart htmlPart = new MimeBodyPart();
	         HashMap<String,String> cids = new HashMap<String, String>();
	         htmlPart.setContent(body, "text/html");
	         multipart.addBodyPart(htmlPart);
	         // set body of the email.
	         message.setContent(multipart);
	  
	         // Send email.
	         Transport.send(message);
	         System.out.println("Mail successfully sent");
	      }
	      catch (MessagingException mex) 
	      {
	         mex.printStackTrace();
	      }
	
	}
}

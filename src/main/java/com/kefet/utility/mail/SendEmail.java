package com.kefet.utility.mail;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import java.security.Security;
import org.apache.log4j.Logger;
import com.sun.net.ssl.internal.ssl.Provider;

@org.springframework.stereotype.Service
public class SendEmail {
	
	//https://www.google.com/settings/security/lesssecureapps
	
	private static Logger log = Logger.getLogger(SendEmail.class);
	private static final String CLASS_NAME = "SendEmail ";
	private String methodNM = null;
        private String mailSubject;
	private String mailBody;
        
        
	public boolean sendEmail(String to, String mailSubject, String mailBody) throws MessagingException{
		methodNM = CLASS_NAME+".sendAuthenticationURL()";
		  log.info("in " + methodNM);

			Provider provider = new Provider();
			Security.addProvider(provider); 
			Properties props = new Properties();
			props.setProperty("mail.transport.protocol", "smtp"); 
			props.setProperty("mail.host", "smtp.gmail.com");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			props.put("mail.debug", "true");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
			
			final String gmailID="kefetmail";
			final String gmailPassword="Azad1611Kefet";
			
			Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator(){ 
                        @Override
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(gmailID, gmailPassword);
			}
			});
			
			session.setDebug(false); 
			Transport transport = session.getTransport();
			MimeMessage message = new MimeMessage(session);

			
			message.setSubject(mailSubject);
			message.setContent(mailBody, "text/plain");
			String sendTo [] = {to};
			if (sendTo != null) {
			InternetAddress[] addressTo = new InternetAddress[sendTo.length];
				for (int i = 0; i < sendTo.length; i++) {
					addressTo[i] = new InternetAddress(sendTo[i]);
					message.setRecipients(Message.RecipientType.TO, addressTo);
				}
				transport.connect();
				Transport.send(message);
				transport.close();
			}
			
	        
	        return true;
	}
	
	//********************************************************************************************************
	
	public void postMail( String recipients[], String subject, String message , String from) throws MessagingException
	{
	    boolean debug = false;

	     //Set the host smtp address
	     Properties props = new Properties();
	     props.put("mail.smtp.host", "smtp.jcom.net");

	    // create some properties and get the default Session
	    Session session = Session.getDefaultInstance(props, null);
	    session.setDebug(debug);

	    // create a message
	    Message msg = new MimeMessage(session);

	    // set the from and to address
	    InternetAddress addressFrom = new InternetAddress(from);
	    msg.setFrom(addressFrom);

	    InternetAddress[] addressTo = new InternetAddress[recipients.length]; 
	    for (int i = 0; i < recipients.length; i++)
	    {
	        addressTo[i] = new InternetAddress(recipients[i]);
	    }
	    msg.setRecipients(Message.RecipientType.TO, addressTo);
	   

	    // Optional : You can also set your custom headers in the Email if you Want
	    msg.addHeader("MyHeaderName", "myHeaderValue");

	    // Setting the Subject and Content Type
	    msg.setSubject(subject);
	    msg.setContent(message, "text/plain");
	    Transport.send(msg);
	}
        
        public void setMailSubject(String mailSubject){
            this.mailSubject = mailSubject;
        }
        
        public String getMailSubject(){
            return mailSubject;
        }
        
        public void setMailBody(String mailBody){
            this.mailBody = mailBody;
        }
        
        public String getMailBody(){
            return mailBody;
        }        
}

package com.kang.util;

import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
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
public class MailUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		SendMail();
		
		SendMailWithAttachment("this is the news", "Philly_news20120416.html");
		SendMailWithAttachment("this is the news", "USAToday_20120416sports_stories.html");
		SendMailWithAttachment("this is the news", "USAToday_20120416topstories.html");
		SendMailWithAttachment("this is the news", "USAToday_Money20120416_topstories.html"); 
		SendMailWithAttachment("this is the news", "USAToday_nba20120416_topstories.html");
		SendMailWithAttachment("this is the news", "USAToday_tech20120416_topstories.html");
		System.out.println("Done");
	}

//	public static void SendMail(String subject, String filename) {
//		Properties props = new Properties();
//		props.put("mail.smtp.host", "smtp.gmail.com");
//		props.put("mail.smtp.socketFactory.port", "465");
//		props.put("mail.smtp.socketFactory.class",
//				"javax.net.ssl.SSLSocketFactory");
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.port", "465");
// 
//		Session session = Session.getDefaultInstance(props,
//			new javax.mail.Authenticator() {
//				protected PasswordAuthentication getPasswordAuthentication() {
//					return new PasswordAuthentication("pesmith2012@gmail.com","pesmithmima");
//				}
//			});
// 
//		try {
// 
//			Message message = new MimeMessage(session);
//			message.setFrom(new InternetAddress("yinghuihu@yahoo.com"));
//			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("yinghuihu75@kindle.com"));
//			message.setSubject("Testing Subject");
//			message.setText("Dear Mail Crawler," + "\n\n No spam to my email, please!");
// 
//			Transport.send(message);
// 
//			System.out.println("Done");
// 
//		} catch (MessagingException e) {
//			throw new RuntimeException(e);
//		}
//	}
	
	public static void SendMailWithAttachment(String subject, String attachmengFileName) 
	{
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
 
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("pesmith2012@gmail.com","pesmithmima");
				}
			});
		       
		 
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("yinghuihu@yahoo.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("yinghuihu75@kindle.com"));
			message.setSubject(subject);
			message.setText("This is the news");

			MimeBodyPart messagePart = new MimeBodyPart();
			messagePart.setText("This is the news");

//			String filename = "output2.zip";
			//
			// Set the email attachment file
			//
			MimeBodyPart attachmentPart = new MimeBodyPart();
			FileDataSource fileDataSource = new FileDataSource(attachmengFileName) {
				@Override
				public String getContentType() {
					return "application/octet-stream";
				}
			};
			
			attachmentPart.setDataHandler(new DataHandler(fileDataSource));
			attachmentPart.setFileName(attachmengFileName);

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messagePart);
			multipart.addBodyPart(attachmentPart);

			message.setContent(multipart);

			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}

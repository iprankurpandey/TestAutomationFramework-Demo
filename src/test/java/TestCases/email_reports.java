package TestCases;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;

import javax.mail.Multipart;

import javax.mail.Session;
import javax.mail.Transport;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.security.auth.Refreshable;

import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

public class email_reports {

	@AfterSuite
	public void emailreportAPI() throws Exception {

		// Recipient's Mail id
		String receipientTo = "prankur.pandey@taritas.com";

		// Sender's Mail id
		String senderFrom = "prankur.pandey@taritas.com";

		// Path of PDF test report
		String path = "C:\\Users\\Taritas 9\\eclipse-workspace\\TestSuite-Full\\test-output\\emailable-report.html";

		// Getting System properties
		Properties prop = System.getProperties();
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", true);

		// Setting up smtp host
		prop.setProperty("mail.smtp.host", "smtp.office365.com");

		// Creating a new session for smtp
		Session session = Session.getDefaultInstance(prop);

		MimeMessage msg = new MimeMessage(session);

		// Instance of From Internet address
		InternetAddress frmAddress = new InternetAddress(senderFrom);

		// Instance of To Internet address
		InternetAddress toAddress = new InternetAddress(receipientTo);

		// Setting up sender's address
		msg.setFrom(frmAddress);

		// Setting up recipient's address
		msg.addRecipient(Message.RecipientType.TO, toAddress);

		// Setting email's subject
		msg.setSubject("Test Status Report");

		BodyPart msgBody = new MimeBodyPart();

		// Setting email's message body
		msgBody.setText("This is test report through mail");

		// Instance of second part
		Multipart multiPart = new MimeMultipart();

		multiPart.addBodyPart(msgBody);

		// Another mail body
		msgBody = new MimeBodyPart();

		// Path to pdf file for attachment
		DataSource source = new FileDataSource(path);

		DataHandler dataHandler = new DataHandler(source);

		msgBody.setDataHandler(dataHandler);

		msgBody.setFileName(path);

		multiPart.addBodyPart(msgBody);

		msg.setContent(multiPart);

		try {
			// Authentication and connection establishment to the sender's mail
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.office365.com", 587, "mail", "pwd");
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();

			System.out.println("Mail Sent");
			Reporter.log("Mail is sent have all the test cases reports");

		} catch (Exception e) {
			e.getStackTrace();
			System.out.println(e);
		}
	}
}

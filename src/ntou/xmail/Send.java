package ntou.xmail;

import java.io.IOException;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Send {
	private static  String to;
	private static  String from;
	private static String username;
	private static String password;
	private static String cont;
	
	public Send(String[] sendData,String Target,String cont)
	{
		from = username = sendData[0];
		password = sendData[1];
		to = Target;
		this.cont = cont;
	}
	
	
	public static void SendMAIL() throws MessagingException, IOException {

		

		Properties props = setProperties();

		// Get the Session object.
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Multipart multipart = new MimeMultipart();
			//multipart.addBodyPart((BodyPart)cont);
			// attachment 123.txt
			//Attachment.addAttachment(multipart, "123.txt");
			
			//add html
			//String fileName = FileChooser.chooseFile();
			//Html.addHtml(multipart, "123.html");
			/*
			//add image
			String fileName = FileChooser.chooseFile();
			Image.setImage(multipart, fileName);
			*/
			Message message = new MimeMessage(session);

			//set from
			message.setFrom(new InternetAddress(from));

			//set to
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			message.setSubject("Testing Subject");

			message.setContent(multipart);

			Transport.send(message);

			System.out.println("mail sent");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	private static Properties setProperties() {

		Properties props = new Properties();
		//海大信箱不用驗證、不用SSL、不用TTL 我覺得hen棒
		// props.put("mail.smtp.auth", "true");
		// props.put("mail.smtp.starttls.enable", "true");
		// props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.host", "mail.ntou.edu.tw");
		props.put("mail.smtp.port", "25");
		props.setProperty("mail.debug", "ture");

		return props;
	}
}
package ntou.xmail;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;

public class Attachment {
	
	public static void addAttachment(Multipart multipart, String fileName) throws MessagingException{
		BodyPart bodyPart = new MimeBodyPart();
		DataSource source = new FileDataSource(fileName);
		
		bodyPart.setText("test attachment");
		bodyPart.setDataHandler(new DataHandler(source));
		bodyPart.setFileName(fileName);
		multipart.addBodyPart(bodyPart);
	}
}

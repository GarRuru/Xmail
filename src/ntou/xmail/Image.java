package ntou.xmail;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;

public class Image {
	public static void setImage(Multipart multipart, String fileName) throws MessagingException{
		BodyPart bodyPart = new MimeBodyPart();
		String text = "<img src=\"" + fileName +"\">";
		bodyPart.setContent(text, "text/html");
		
		multipart.addBodyPart(bodyPart);
		bodyPart = new MimeBodyPart();
        DataSource fds = new FileDataSource(fileName);

        bodyPart.setDataHandler(new DataHandler(fds));
        bodyPart.setHeader("Content-ID", "<image>");
        
        multipart.addBodyPart(bodyPart);
	}
}

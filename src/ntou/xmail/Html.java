package ntou.xmail;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;

public class Html {
	
	public static void addHtml(Multipart multipart, String fileName) throws IOException, MessagingException{
		BodyPart bodyPart = new MimeBodyPart();
		InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName),"UTF-8");
		StringBuilder string = new StringBuilder();
		BufferedReader br = new BufferedReader(reader);
		while(br.ready())string.append(br.readLine());
		
		br.close();
		bodyPart.setContent(string.toString(),"text/html; charset=UTF-8");
		multipart.addBodyPart(bodyPart);
	}
}

package ntou.xmail;

import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

public class sendMail {
	private String sendto;
	private String fromAddr;
	private String msgBody;
	private String subject;
	private Multipart mpp;

	public sendMail(mailFormat mf, Multipart mp) {
		sendto = mf.getDate();
		fromAddr = mf.getSender();
		subject = mf.getSubject();
		msgBody = mf.getContext();
		mpp = mp;
		System.out.println("F:" + fromAddr + " T:" + sendto);
	}

	public boolean RUN() {
		Properties props = new Properties();
		props.put("mail.smtp.host", "mail.ntou.edu.tw"); // 設定SMTP Server
		props.setProperty("mail.debug", "true");
		Session session = Session.getDefaultInstance(props, null); // 取得與SMTP Server的連線
		try {
			Message msg = new MimeMessage(session); // 取得一Mime的Message
			// 設定自己的email帳號
			InternetAddress from = new InternetAddress(fromAddr);
			// InternetAddress from = new InternetAddress("cww5@mail.ncku.edu.tw", “陳威文”);
			msg.setFrom(from);
			// 設定接收者的帳號，可以有多個帳號同時傳送
			InternetAddress[] address = { new InternetAddress(sendto) };
			msg.setRecipients(Message.RecipientType.TO, address);
			/*
			 * 有需要時，可以加入CC /BCC， 如果只一位receipt可用 Address addr = new
			 * InternetAddress("cww@mail.hosp.ncku.edu.tw")
			 * msg.serReceipt(Message.RecipientType.TO, addr)
			 * 
			 * InternetAddress ccAddress = new InternetAddress("tlchen@mail.ncku.edu.tw");
			 * 
			 * InternetAddress bccAddress = new InternetAddress("jjh@mail.ncku.edu.tw");
			 * 
			 * msg.addRecipient(Message.RecipientType.CC, ccAddress);
			 * 
			 * msg.addRecipient(Message.RecipientType.BCC, bccAddress);
			 * 
			 */

			
			//TODO:BUG!!!
			msg.setSubject(subject);
			msg.setContent(msgBody, "text/plain;charset=big5");
			// get current system-time
			Date date = new Date();
			msg.setSentDate(date);
			msg.setFrom(new InternetAddress(sendto));
			Transport.send(msg);

			return true;
			/*
			 * 也可以如此做
			 * 
			 * msg.saveChanges();
			 * 
			 * Transport transport = session.getTransport("smtp");
			 * 
			 * transport.connect(“mail.ncku.edu.tw”, “UID”, “PWD”);
			 * 
			 * transport.sendMessage(msg, msg.getAllRecipients());
			 * 
			 * transport.close();
			 */
		} catch (MessagingException mex) {

			mex.printStackTrace();
			return false;
		} catch (Exception e) {
		}
		return false;
	}

}
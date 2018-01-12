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
		props.put("mail.smtp.host", "mail.ntou.edu.tw"); // �]�wSMTP Server
		props.setProperty("mail.debug", "true");
		Session session = Session.getDefaultInstance(props, null); // ���o�PSMTP Server���s�u
		try {
			Message msg = new MimeMessage(session); // ���o�@Mime��Message
			// �]�w�ۤv��email�b��
			InternetAddress from = new InternetAddress(fromAddr);
			// InternetAddress from = new InternetAddress("cww5@mail.ncku.edu.tw", �����¤塨);
			msg.setFrom(from);
			// �]�w�����̪��b���A�i�H���h�ӱb���P�ɶǰe
			InternetAddress[] address = { new InternetAddress(sendto) };
			msg.setRecipients(Message.RecipientType.TO, address);
			/*
			 * ���ݭn�ɡA�i�H�[�JCC /BCC�A �p�G�u�@��receipt�i�� Address addr = new
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
			 * �]�i�H�p����
			 * 
			 * msg.saveChanges();
			 * 
			 * Transport transport = session.getTransport("smtp");
			 * 
			 * transport.connect(��mail.ncku.edu.tw��, ��UID��, ��PWD��);
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
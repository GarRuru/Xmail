package ntou.xmail;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
 
public class sendMail {
	private String sendto;
	private String fromAddr;
	private String msgBody;
	private String subject;
	public sendMail(mailFormat mf)
	{
		sendto = mf.getDate();
		fromAddr = mf.getSender();
		subject = mf.getSubject();
		msgBody = mf.getContext();
		System.out.println("F:" + fromAddr + " T:" + sendto);
	}
	
	
     public  boolean RUN(){
         Properties props = new Properties();
         props.put("mail.smtp.host", "mail.ntou.edu.tw"); //�]�wSMTP Server
         Session session = Session.getDefaultInstance(props, null); //���o�PSMTP Server���s�u
         try {
              Message msg = new MimeMessage(session); //���o�@Mime��Message
//�]�w�ۤv��email�b��
              InternetAddress from = new InternetAddress(fromAddr);
              // InternetAddress from = new InternetAddress("cww5@mail.ncku.edu.tw", �����¤塨);
              msg.setFrom(from);
             //�]�w�����̪��b���A�i�H���h�ӱb���P�ɶǰe
              InternetAddress[] address = {new InternetAddress(sendto)};
              msg.setRecipients(Message.RecipientType.TO, address);
             /*���ݭn�ɡA�i�H�[�JCC /BCC�A
					�p�G�u�@��receipt�i��
					Address addr = new InternetAddress("cww@mail.hosp.ncku.edu.tw")
					msg.serReceipt(Message.RecipientType.TO, addr)
					
					InternetAddress ccAddress = new InternetAddress("tlchen@mail.ncku.edu.tw");
					
					InternetAddress bccAddress = new InternetAddress("jjh@mail.ncku.edu.tw");
					
					msg.addRecipient(Message.RecipientType.CC, ccAddress);
					
					msg.addRecipient(Message.RecipientType.BCC, bccAddress);

             */
              
              msg.setSubject(subject);
              msg.setContent(msgBody, "text/plain;charset=big5");
              //get current system-time
              Date date = new Date();
              //msg.setHeader("Date", date.toString());
              //msg.addHeader("Date", date.toString());
              msg.setSentDate(date);
              msg.setFrom(new InternetAddress(sendto));
              Transport.send(msg);
              return true;
             /*�]�i�H�p����

					msg.saveChanges(); 
					
					Transport transport = session.getTransport("smtp");
					
					transport.connect(��mail.ncku.edu.tw��, ��UID��, ��PWD��);
					
					transport.sendMessage(msg, msg.getAllRecipients());
					
					transport.close();
            */
         } catch (MessagingException mex) {
        	 
        	 mex.printStackTrace();
        	 return false;
         } catch (Exception e) {}
		return false;
     }

}
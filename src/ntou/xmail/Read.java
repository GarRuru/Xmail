package ntou.xmail;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.sun.mail.util.MailSSLSocketFactory;

public class Read extends JFrame {
	public static ArrayList<mailFormat> Storage = new ArrayList();
	static Properties props = System.getProperties();
	private static String userName;
	private static String password;
	static Store store;
	public static Folder[] folder;
	// ArrayList<String[]> inboxList = new ArrayList<String[]>();
	Vector inboxList = new Vector();

	public Read(String userName, String password) {
		this.userName = userName;
		this.password = password;
		// this.logintoServer();
	}

	public static boolean logintoServer() {

		try {
			props.setProperty("mail.pop3.host", "mail.ntou.edu.tw");
			props.setProperty("mail.pop3.port", "110");
			props.setProperty("mail.store.protocol", "pop3");
			// props.setProperty("mail.debug", "true");

			props.put("mail.pop3.auth", "true"); // STMP check
			MailSSLSocketFactory sf = null;
			try {
				sf = new MailSSLSocketFactory();
			} catch (GeneralSecurityException e) {
				// logger.fatal("error", e);
			}
			sf.setTrustAllHosts(true);
			props.put("mail.smtp.ssl.enable", "true");
			props.put("mail.smtp.ssl.socketFactory", sf);
			props.put("mail.smtp.socketFactory.fallback", "false");
			props.put("mail.smtp.socketFactory.port", "994");

			// String username = "00457053@mail.ntou.edu.tw";
			// String password = "";

			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userName, password);
				}
			});

			store = (Store) session.getStore("pop3");
			store.connect(userName, password);

			return true; // 準備跳轉
		}
		// POP3登入失敗
		catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "帳號或密碼不正確", "Login Failed", JOptionPane.ERROR_MESSAGE);
			return false; // 停在登入畫面

		}
	}

	public void getFolderList() throws IOException, MessagingException {
		folder = new Folder[5];
		folder[0] = store.getFolder("INBOX");
		folder[1] = store.getFolder("Sent Mail");
		folder[2] = store.getFolder("Draft");
		folder[3] = store.getFolder("Recycle Bin");
		folder[4] = store.getFolder("Spam");

		System.out.println("<Folder List>");
		for (Folder f : folder) {
			System.out.println(f.getName() + "(" + f.getMessageCount() + ")");
		}
		System.out.println("");
		this.ReadMailbox(folder[0]);
	}

	public void ReadMailbox(Folder F) throws IOException {
		try {
			if (F.exists())
				F.open(Folder.READ_ONLY);
			Message[] messages = F.getMessages();

			if (messages != null && messages.length > 0) {
				for (Message msg : messages) {
					mailFormat MF;
					Multipart multipart = null;
					System.out.println("Date: " + msg.getSentDate());
					System.out.println("From: " + msg.getFrom()[0]);
					System.out.println("Subject: " + msg.getSubject());
					System.out.println("Body:");
					if (!(msg.getContent() instanceof String)) {
						multipart = (Multipart) msg.getContent();
						BodyPart body = multipart.getBodyPart(0);
						System.out.println(body.getContent());
						MF = new mailFormat(msg.getFrom()[0].toString(), msg.getSentDate().toString(),
								msg.getSubject().toString(), body.getContent().toString(), 1);

						/*
						 * Enumeration header = msg.getAllHeaders(); while(header.hasMoreElements()) {
						 * System.out.println("EE " + header.nextElement()); }
						 */

					} else {
						// System.out.println("選項是0");
						String cont = new String();
						cont = msg.getContent().toString();
						System.out.println(cont);

						MF = new mailFormat(msg.getFrom()[0].toString(), msg.getSentDate().toString(),
								msg.getSubject().toString(), cont, 0);

					}

					System.out.println("----------------------------------END---------------------------");

					Storage.add(MF);
				}
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

}

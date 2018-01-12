package ntou.xmail;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeMultipart;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import data.SignatureManagerUI;

public class WriteNewMail {

	private String[] accountdata;
	private JFrame frame;
	private JTextField sendToField;
	private JTextField ccField;
	private JTextField SubjectField;
	private JTextArea contField;
	private Multipart multipart;

	public WriteNewMail(String[] arg) {
		/**
		 * @wbp.parser.constructor
		 */

		accountdata = new String[2];// arg;
		accountdata = arg;
		multipart = new MimeMultipart();
		initialize();
	}

	// reply-method
	/*
	 * public WriteNewMail(String[] arg,String val,String resub) {
	 * 
	 * accountdata = arg; initialize(); sendToField.setText(val); multipart = new
	 * MimeMultipart(); SubjectField.setText("Re: " + resub);
	 * 
	 * }
	 */
	// forward-method
	/*
	 * public WriteNewMail(String[] arg,String resub) {
	 * 
	 * accountdata = arg; multipart = new MimeMultipart(); initialize();
	 * SubjectField.setText("Fwd: " + resub);
	 * 
	 * }
	 */
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("撰寫郵件");
		frame.setBounds(100, 100, 700, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);

		JLabel lblNewLabel = new JLabel("\u6536\u4EF6\u4EBA:");
		lblNewLabel.setBounds(10, 39, 46, 15);
		frame.getContentPane().add(lblNewLabel);

		JLabel label = new JLabel("\u526F\u672C:");
		label.setBounds(10, 64, 46, 15);
		frame.getContentPane().add(label);

		JLabel label_1 = new JLabel("\u4E3B\u65E8:");
		label_1.setBounds(10, 89, 46, 15);
		frame.getContentPane().add(label_1);

		contField = new JTextArea();
		contField.setBounds(10, 150, 664, 201);
		frame.getContentPane().add(contField);
		SignatureManagerUI SMU;
		try {
			SMU = new SignatureManagerUI();
			contField.setText("\n\n\n--------------------\n\n"+SMU.getSignature());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		

		sendToField = new JTextField();
		sendToField.setBounds(66, 36, 608, 21);
		frame.getContentPane().add(sendToField);
		sendToField.setColumns(10);

		ccField = new JTextField();
		ccField.setColumns(10);
		ccField.setBounds(66, 61, 608, 21);
		frame.getContentPane().add(ccField);

		SubjectField = new JTextField();
		SubjectField.setColumns(10);
		SubjectField.setBounds(66, 86, 608, 21);
		frame.getContentPane().add(SubjectField);

		JButton sendButton = new JButton("\u5BC4\u51FA");
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Send mail
				mailFormat MFF = new mailFormat(accountdata[0], sendToField.getText(), SubjectField.getText(),
						contField.getText(), 0);
				sendMail ss = new sendMail(MFF, multipart);
				boolean result = ss.RUN();
				if (result) {
					JOptionPane.showMessageDialog(null, "信件已寄出!", "Success", JOptionPane.INFORMATION_MESSAGE);
					frame.dispose();
				}

			}
		});
		sendButton.setBounds(10, 6, 87, 23);
		frame.getContentPane().add(sendButton);

		JButton addAttachmentButton = new JButton("\u52A0\u5165\u9644\u4EF6..");
		addAttachmentButton.setBounds(108, 6, 87, 23);
		frame.getContentPane().add(addAttachmentButton);
		addAttachmentButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String seletedFile = FileChooser.chooseFile();
				if (!(seletedFile == null)) {
					try {
						System.out.println("Attachment Added!!!!!!!!!!!");
						Attachment.addAttachment(multipart, seletedFile);
					} catch (MessagingException e) {
						e.printStackTrace();
					}
				}

			}

		});
	}
}
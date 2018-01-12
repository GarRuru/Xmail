package ntou.xmail;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.awt.event.ActionEvent;

public class WriteNewMail {
	private String[] accountdata;
	private JFrame frame;
	private JTextField sendToField;
	private JTextField ccField;
	private JTextField SubjectField;
	private JTextArea contField;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WriteNewMail window = new WriteNewMail();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 * @wbp.parser.constructor 
	 */

	/**
	 * Create the application.
	 */
	public WriteNewMail(String [] arg) {
		accountdata = new String[2];// arg;
		accountdata = arg;
		initialize();
	}

	//reply-method
	public WriteNewMail(String[] arg,String val,String resub)
	{
		accountdata = arg;
		initialize();
		sendToField.setText(val);
		SubjectField.setText("Re: " + resub);
		
	}
	//forward-method
		public WriteNewMail(String[] arg,String resub)
		{
			accountdata = arg;
			initialize();
			SubjectField.setText("Fwd: " + resub);
			
		}
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
		contField.setBounds(10, 121, 664, 230);
		frame.getContentPane().add(contField);
		
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
		
		JButton button = new JButton("\u5BC4\u51FA");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Send mail
				mailFormat MFF = new mailFormat(accountdata[0],sendToField.getText(),SubjectField.getText(),contField.getText(),0);
				sendMail ss = new sendMail(MFF);
				boolean result = ss.RUN();
				if(result)
				{
					JOptionPane.showMessageDialog(null, "信件已寄出!", "Success", JOptionPane.INFORMATION_MESSAGE);
					frame.dispose();
				}
				
				
			}
		});
		button.setBounds(10, 6, 87, 23);
		frame.getContentPane().add(button);
	}

}
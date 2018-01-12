package ntou.xmail;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.UIManager;

public class LoginPage {
	private JFrame frame;
	private JTextField accountField;
	private JPasswordField passwordField;
	String logopath = "email.png";
	BufferedImage image;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage window = new LoginPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		setUIFont (new javax.swing.plaf.FontUIResource("微軟正黑體",Font.PLAIN,14));
		try {
			image = ImageIO.read(new File(logopath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		frame = new JFrame("Xmail");
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		accountField = new JTextField();
		accountField.setBounds(773, 165, 190, 35);
		frame.getContentPane().add(accountField);
		accountField.setColumns(10);
		
		
		final String[] mailList = {"Gmail", "Outlook" , "PPT" };
		JComboBox MailBoxList = new JComboBox(mailList);
		MailBoxList.setBounds(773, 88, 190, 35);
		frame.getContentPane().add(MailBoxList);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(773, 243, 190, 35);
		frame.getContentPane().add(passwordField);
		passwordField.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						JLabel capswarn = new JLabel("Caps Lock已經啟用");
						capswarn.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
						capswarn.setBounds(773, 251, 190, 35);
						System.out.println(Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK));
						//caps-lock active
						if(Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK))
						{
							capswarn.setVisible(true);
							frame.getContentPane().add(capswarn);
						}
						else capswarn.setVisible(false);
					}
			
				});
		
		JScrollPane scrollPane = new JScrollPane(new JLabel(new ImageIcon(image)));
		scrollPane.setBounds(0, 0, 745, 561);
		frame.getContentPane().add(scrollPane);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnLogin.setBounds(822, 314, 87, 23);
		frame.getContentPane().add(btnLogin);
		btnLogin.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						String account = accountField.getText();
						String password = passwordField.getText();
						System.out.println(account + "/" + password);
						
						
					}
			
				});
		
		
		
		JButton btnClear = new JButton("Clear");
		btnClear.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnClear.setBounds(822, 357, 87, 23);
		frame.getContentPane().add(btnClear);
		btnClear.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent ev) {
						//clear Account and password field
						accountField.setText("");
						passwordField.setText("");
						
					}
			
				}
		);
		JLabel label = new JLabel("\u8ACB\u9078\u64C7\u767B\u5165\u4FE1\u7BB1\u985E\u5225");
		label.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		label.setBounds(773, 63, 136, 15);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u4F7F\u7528\u8005\u5E33\u865F");
		label_1.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		label_1.setBounds(773, 132, 110, 23);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u4F7F\u7528\u8005\u5BC6\u78BC");
		label_2.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		label_2.setBounds(773, 210, 110, 23);
		frame.getContentPane().add(label_2);
	}
	
	//Font Properties
	public static void setUIFont (javax.swing.plaf.FontUIResource f){
	    java.util.Enumeration keys = UIManager.getDefaults().keys();
	    while (keys.hasMoreElements()) {
	      Object key = keys.nextElement();
	      Object value = UIManager.get (key);
	      if (value instanceof javax.swing.plaf.FontUIResource)
	        UIManager.put (key, f);
	      }
	    } 
}

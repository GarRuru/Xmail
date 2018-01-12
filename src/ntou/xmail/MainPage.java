package ntou.xmail;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.mail.MessagingException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.xml.xpath.XPath;
import javax.swing.JTable;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.awt.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JEditorPane;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class MainPage extends JFrame{
	private String account;
	private JFrame frame;
	private String[] accountINFO;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage window = new MainPage();
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
	
	public MainPage(String string,Read SA,String ACCOUNT,String PWD) {
		accountINFO = new String[2];
		accountINFO[0] =  ACCOUNT; accountINFO[1] = PWD;
		account = string;

		try {
			SA.getFolderList();
			//SA.ReadMailbox("INBOX");
		} catch (IOException e) {
			// TODO 自動產生的 catch 區塊
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO 自動產生的 catch 區塊
			e.printStackTrace();
		}
		
		initialize(SA);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Read SA) {
		frame = new JFrame(account);
		String mailUIDisplay = account.substring(7);
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 200, 561);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(200, 0, 301, 561);
		frame.getContentPane().add(panel_1);
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(500, 49, 484, 512);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JTextArea mpContext = new JTextArea();
		JScrollPane sp = new JScrollPane(mpContext);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		mpContext.setEditable(false);
		mpContext.setBounds(10, 84, 464, 418);
		panel_2.add(mpContext);
		panel_2.add(sp);
		
		JLabel mpSenderT = new JLabel("\u5BC4\u4EF6\u4EBA:");
		mpSenderT.setBounds(10, 10, 46, 15);
		panel_2.add(mpSenderT);
		
		JLabel mpSubjectT = new JLabel("\u4E3B\u65E8:");
		mpSubjectT.setBounds(10, 35, 46, 15);
		panel_2.add(mpSubjectT);
		
		JLabel mpTimeT = new JLabel("\u6642\u9593:");
		mpTimeT.setBounds(10, 59, 46, 15);
		panel_2.add(mpTimeT);
		
		JLabel mpSender = new JLabel("");
		mpSender.setBounds(66, 10, 408, 15);
		panel_2.add(mpSender);
		
		JLabel mpSubject = new JLabel("");
		mpSubject.setBounds(66, 35, 408, 15);
		panel_2.add(mpSubject);
		
		JLabel mpTime = new JLabel("");
		mpTime.setBounds(66, 59, 408, 15);
		panel_2.add(mpTime);
		panel_2.setVisible(false);
		frame.setVisible(true);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(500, 0, 484, 51);
		frame.getContentPane().add(panel_3);
		panel_3.setVisible(false);
		panel_3.setLayout(null);

		
		
		JLabel lblNewLabel = new JLabel("Mail: " + mailUIDisplay);
		lblNewLabel.setFont(UIManager.getFont("CheckBoxMenuItem.font"));
		lblNewLabel.setBounds(10, 10, 161, 33);
		panel.add(lblNewLabel);
		
		
		
		JButton searchButton = new JButton("\u641C\u5C0B");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		searchButton.setBounds(114, 49, 76, 39);
		panel.add(searchButton);
		
		JButton writeNewMail = new JButton("\u64B0\u5BEB\u90F5\u4EF6");
		writeNewMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WriteNewMail nm = new WriteNewMail(accountINFO);
			}
		});
		writeNewMail.setBounds(10, 50, 94, 38);
		panel.add(writeNewMail);
		panel_1.setLayout(null);
		
		//Vector mailVect = new Vector();
		//for(mailFormat m : SA.Storage)
		//	mailVect.add(m);
		
		//JList mailPreviewList = new JList(mailVect);
		DefaultListModel model = new DefaultListModel();
		for(mailFormat m: SA.Storage)
			model.addElement(m);
		JList mailPreviewList = new JList(model);
		mailPreviewList.setCellRenderer(new MyListCellRenderer());
		mailPreviewList.setBounds(10, 10, 281, 541);
		mailPreviewList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mailPreviewList.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		panel_1.add(mailPreviewList);
		mailPreviewList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(e.getButton() == MouseEvent.BUTTON1)
				{
					System.out.println(">>Click:MailList#" + mailPreviewList.getSelectedIndex());
					panel_3.setVisible(true);
					panel_2.setVisible(true);
					mpSender.setText(SA.Storage.get(mailPreviewList.getSelectedIndex()).getSenderEmail());
					mpSubject.setText(SA.Storage.get(mailPreviewList.getSelectedIndex()).getSubject());
					mpTime.setText(SA.Storage.get(mailPreviewList.getSelectedIndex()).getDate());
					mpContext.setText(SA.Storage.get(mailPreviewList.getSelectedIndex()).getContext());
				}
			}
		});
		
		
		
		JList mailFolderList = new JList(SA.folder);
		mailFolderList.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 18));
		mailFolderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mailFolderList.setBounds(10, 113, 180, 399);
		mailFolderList.setSelectedIndex(0);
		panel.add(mailFolderList);
		
		
		JButton deleteButton = new JButton("\u522A\u9664");
		deleteButton.setBounds(20, 18, 87, 23);
		panel_3.add(deleteButton);
		
		JButton replyButton = new JButton("\u56DE\u8986");
		replyButton.setIcon(new ImageIcon("/replyico.png"));
		replyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WriteNewMail wnm = new WriteNewMail(accountINFO,SA.Storage.get(mailPreviewList.getSelectedIndex()).getSenderEmail(),
																SA.Storage.get(mailPreviewList.getSelectedIndex()).getSubject());
			}
		});
		replyButton.setBounds(120, 18, 87, 23);
		panel_3.add(replyButton);
		
		JButton forwardButton = new JButton("\u8F49\u5BC4");
		forwardButton.setIcon(new ImageIcon("/forwardico.png"));
		forwardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WriteNewMail wnm = new WriteNewMail(accountINFO,SA.Storage.get(mailPreviewList.getSelectedIndex()).getSubject());
			}
		});
		forwardButton.setBounds(224, 18, 87, 23);
		panel_3.add(forwardButton);
		mailFolderList.addMouseListener(new MouseAdapter()
				{
					@Override
					public void mouseClicked(MouseEvent e)
					{
						if(e.getButton() == MouseEvent.BUTTON1)	//mouse left button
						{
							System.out.println(">>Click" + mailFolderList.getSelectedIndex());
							mailPreviewList.setVisible(true);
						}
					}
					
				});

		
		
	}
}



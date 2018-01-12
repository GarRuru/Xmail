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

public class MainPage extends JFrame{
	private String account;
	private JFrame frame;

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
	
	public MainPage(String string,Read SA) {
		// TODO 自動產生的建構子 Stub
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
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 200, 561);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(200, 0, 301, 561);
		frame.getContentPane().add(panel_1);
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(500, 0, 484, 561);
		frame.getContentPane().add(panel_2);
		frame.setVisible(true);
		
		
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
				WriteNewMail nm = new WriteNewMail();
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
		
		JList mailFolderList = new JList(SA.folder);
		mailFolderList.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 18));
		mailFolderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mailFolderList.setBounds(10, 113, 180, 399);
		panel.add(mailFolderList);
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



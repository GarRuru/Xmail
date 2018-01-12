package pttTest;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import ntou.xmail.PTTListCellRendener;
import javax.swing.ScrollPaneConstants;

public class pttMainPage {
	private PTTClient ptc;
	private JFrame frame;
	private JList inbox;
	List<String> Mbox;
	List<String[]> psList;
	BufferedImage image;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public pttMainPage(PTTClient ptc,String windowTitle) {
		this.ptc = ptc;
		try {
			this.LoadPTTMail();
		} catch (Exception e) {
			e.printStackTrace();
		}
		initialize(windowTitle);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String windowTitle) {
		try
		{
			image = ImageIO.read(new File("banner.png"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		frame = new JFrame(windowTitle);
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(new JLabel(new ImageIcon(image)));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(40, 20, 897, 41);
		frame.getContentPane().add(scrollPane);
		
		DefaultListModel model = new DefaultListModel();
		for(String s: Mbox)
		{
			System.out.println(">" + s);
			model.addElement(s);
		}
				inbox = new JList(model);
		inbox.setBounds(24, 71, 238, 464);
		inbox.setCellRenderer(new PTTListCellRendener());
		frame.getContentPane().add(inbox);
		
		
		JTextArea pttCont = new JTextArea();
		pttCont.setBounds(301, 157, 656, 370);
		frame.getContentPane().add(pttCont);
		
		JLabel pttFrom = new JLabel("\u4F86\u81EA\uFF1A");
		pttFrom.setBounds(301, 72, 46, 15);
		frame.getContentPane().add(pttFrom);
		
		JLabel pttTime = new JLabel("\u6642\u9593\uFF1A");
		pttTime.setBounds(301, 102, 46, 15);
		frame.getContentPane().add(pttTime);
		
		JLabel pttSubject = new JLabel("\u4E3B\u65E8\uFF1A");
		pttSubject.setBounds(301, 132, 46, 15);
		frame.getContentPane().add(pttSubject);
		
		JLabel pttFromX = new JLabel("");
		pttFromX.setBounds(357, 72, 400, 15);
		frame.getContentPane().add(pttFromX);
		
		JLabel pttTimeX = new JLabel("");
		pttTimeX.setBounds(357, 102, 400, 15);
		frame.getContentPane().add(pttTimeX);
		
		JLabel pttSubjectX = new JLabel("");
		pttSubjectX.setBounds(357, 132, 400, 15);
		frame.getContentPane().add(pttSubjectX);
		frame.setVisible(true);
		
		
		
		inbox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(e.getButton() == MouseEvent.BUTTON1)
				{
					pttFromX.setText(psList.get(inbox.getSelectedIndex())[0]);
					pttTimeX.setText(psList.get(inbox.getSelectedIndex())[2]);
					pttSubjectX.setText(psList.get(inbox.getSelectedIndex())[1]);
					pttCont.setText(psList.get(inbox.getSelectedIndex())[3]);
				}
			}
		});
		
		

	}
	
	private void LoadPTTMail() throws Exception
	{
		
		Mbox = new ArrayList<String>();
		Mbox = ptc.getMail();
		System.out.println(Mbox);
		parsePTTMail();
	}
	
	private void parsePTTMail()
	{
		psList = new ArrayList<String[]>();
		for(String ms :Mbox)
		{
			String[] s = new String[4];
			int index = ms.indexOf("作者：");
			s[0] = ms.substring(index + 3, index = ms.indexOf("標題："));
			//System.out.println(s[0]);
			s[1] = ms.substring(index + 3, index = ms.indexOf("時間："));
			//System.out.println(s[1]);
			s[2] = ms.substring(index + 3, index = ms.indexOf("內文："));
			//System.out.println(s[2]);
			s[3] = ms.substring(index + 3, ms.length());
			//System.out.println(s[3]);
			psList.add(s);
		}
	}
}

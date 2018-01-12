package data;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class SignatureManagerUI {
	private FileReader fr;
	private FileWriter fw;
	private JFrame frame;
	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	public void initialize() throws IOException {
		frame = new JFrame("個人簽名檔");
		frame.setBounds(100, 100, 450, 450);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 84, 414, 317);
		textArea.setLineWrap(true);
		frame.getContentPane().add(textArea);
		textArea.setText(this.getSignature());
		
		JButton saveButton = new JButton("\u4FDD\u5B58");
		saveButton.setBounds(337, 23, 87, 23);
		frame.getContentPane().add(saveButton);
		
		JLabel label = new JLabel("\u7C3D\u540D\u6A94\uFF1A");
		label.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		label.setBounds(10, 35, 97, 39);
		frame.getContentPane().add(label);
		saveButton.addActionListener(new ActionListener()
				{
				public void actionPerformed(ActionEvent e) {
					try {
						writeSignature(textArea.getText());
						frame.dispose();
					} catch (IOException e1) {
							e1.printStackTrace();
					}
				}
				});
		
		frame.setVisible(true);
	}
	

	

		
		public SignatureManagerUI() throws IOException {
			/*
			try {
				fr = new FileReader("Signature.txt");
			} catch (FileNotFoundException e) {
				fr = null;
				e.printStackTrace();
			}
			
			try {
				fw = new FileWriter("Signatrue.txt");
			} catch (IOException e) {
				fw = null;
				e.printStackTrace();
			}
			*/
			//initialize();
			
		}
		
		public String getSignature() throws IOException {
			/*
			if(readAvailable()) {
				BufferedReader br = new BufferedReader(fr);
				String s = "";
				
				while(br.ready()) {
					s += br.readLine();
				}
				
				return s;
			}*/
			FileReader fileReader = new FileReader("Signatrue.txt");
			BufferedReader br = new BufferedReader(fileReader);
			String s = "";
			
			while(br.ready()) {
				System.out.println("fuckyou");
				s += br.readLine();
			}
			
			br.close();
			fileReader.close();
			return s;
		}
		
		public void writeSignature(String s) throws IOException {
			FileWriter fileWriter = new FileWriter("Signatrue.txt");
			
				fileWriter.write(s);
				fileWriter.flush();
				fileWriter.close();
		}
	}




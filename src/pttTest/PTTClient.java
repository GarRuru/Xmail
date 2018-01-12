package pttTest;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PushbackReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class PTTClient {
	private Socket socket;
	private InputStream is = null;
	private OutputStream os = null;
	private BufferedReader br = null;
	private PushbackReader pr = null;
	private FileWriter fw = null;
	private final char c = 0x1B; //ESCAPE
	
	public PTTClient() throws UnknownHostException, IOException {
		connect();
		br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		pr = new PushbackReader(br, 128);
		fw = new FileWriter("fuckyou.txt");
	}
	
	public List<String> getMail() throws Exception {
		List<String> mails = new ArrayList<String>();
		
		int nc = 0;
		int index;
		char[] cb = new char[100000];
		Thread.sleep(500);
		send("mmmm");
		Thread.sleep(100);
		send("\r\n\r\n");
		Thread.sleep(500);
		nc = pr.read(cb);
		send("\r\n");
		while(true) {
			nc = pr.read(cb);
			StringBuilder sb = new StringBuilder();
			StringBuilder sb1 =new StringBuilder();
			for(int i = 0; i < nc;++i) {
				char c = cb[i];
				switch(c) {
				case 0x08:
					continue;
				case 0x0A:
				case 0X0D:
				case 0x1B:
					break;
					default:
						if(c<0x20||c==0x7F)continue;
						sb.append(c);
				}
			}
			String s = sb.toString();
			fw.write(s);
			fw.flush();
			//System.out.println(sb.toString());
			if(sb.toString().contains("[H[1;33m "))break;
			sb1.append("作者：" + s.substring(20, s.indexOf(")") + 1));
			index = 183;
			while(s.charAt(index) == ' ')index--;
			sb1.append("標題：" + s.substring(113, index + 1));
			index = 272;
			while(s.charAt(index) == ' ')index--;
			sb1.append("時間：" + s.substring(202, index + 1));
			sb1.append("內文：" + s.substring(632, s.indexOf("--  [32m※") - 2));
			//System.out.println(sb1.toString());
			mails.add(sb1.toString());
			send(c + "OA" + c +"OD");
			Thread.sleep(100);
			pr.read(cb);
			send(c + "OC");
			Thread.sleep(100);
		}
		fw.close();
		pr.close();
		br.close();
		
		return mails;
	}

	private void connect() throws UnknownHostException, IOException {
		socket = new Socket("ptt.cc", 23);
		socket.setKeepAlive(true);
		try {
			is = socket.getInputStream();
			os = socket.getOutputStream();
			//renderScreen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() throws IOException {
		socket.close();
	}

	public boolean login(String username, String password) throws Exception {
		
		int nc = 0;
		char[] cb = new char[10000];
		StringBuilder sb = new StringBuilder();
		nc = pr.read(cb);
		
		send(username + ",\r\n" + password + "\r\n");
		Thread.sleep(1000);
		
		nc = pr.read(cb);
		for(int i = 0; i < nc;++i) {
			char c = cb[i];
			switch(c) {
		 	case 0x08:
				continue;
			case 0x0A:
			case 0X0D:
			case 0x1B:
				break;
			default:
				if(c<0x20||c==0x7F)continue;
				sb.append(c);
			}
		}
		String s = sb.toString();
		if(s.contains("登入中")) {
			Thread.sleep(100);
			return true;
		}
		return false;
	}

	private void send(String message) throws IOException {
		os.write(message.getBytes());
		os.flush();
	}
}

package ntou.xmail;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import javax.mail.Message;

public class mailFormat {
	private Message msg;
	private String sender;
	private String[] senderEmail;
	private String date;
	private String subject;
	private String context;
	
	public mailFormat()
	{
		
	}
	public mailFormat(String sender,String date,String subject,String context,int option)
	{

		this.sender = sender;
		this.date = date;
		this.subject = subject;
		this.context = context;
		if(option==1)	
		{
			//System.out.println("魔術數字1");
			String temp[] = sender.split("<");
			this.senderEmail = temp[1].split(">");
		}
		else
		{
			//System.out.println("魔術數字0");
			this.senderEmail = new String[1];
			this.senderEmail[0] = sender;
		}
		//convert_UTF8();
	}
	
	@Override
	public String toString() 
	{
		return date + "\r\n" + sender + "|" + subject + "|" + context;
	}
	
	public void printContext()
	{
		System.out.println(context);
	}
	
	public String getDate()
	{
		return date;
	}
	
	public String getSender()
	{
		return sender;
	}
	public String getSenderEmail()
	{
		return senderEmail[0];
	}
	
	public String getSubject()
	{
		return subject;
	}
	
	public String getContext()
	{
		return context;
	}
	
	public Message getMSG()
	{
		return msg;
	}
	
	private void convert_UTF8()
	{
		//byte[] decode = sender.getBytes();
	
		/*try {
			sender = new String(decode,"UTF-8");
			System.out.println("!!" + sender);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}*/
	
		for (String encoding : Charset.availableCharsets().keySet()) {
	            try {
	                // 當初是哪個位元組陣列被解釋為 UTF-8 的？一個一個嘗試！

	            	byte[] decode = sender.getBytes(encoding);
	                System.out.printf("%s %s%n", 
					    encoding, new String(decode, "Big5"));
	            } catch (Exception e) {
	            }
	        }
		
		
		/*
		  byte[] binary = {(byte) 0xfe, (byte) 0xff, (byte)0x6e,
                  (byte) 0x2c, (byte) 0x8a, (byte)0x66};
			 System.out.println(new String(binary, "UTF-16"));
			 
			 binary = new byte[] {(byte) 0xe6, (byte) 0xb8, (byte) 0xac,
			                      (byte) 0xe8, (byte) 0xa9, (byte) 0xa6};
			 System.out.println(new String(binary, "UTF-8"));
			 
			 binary = new byte[] {(byte) 0xb4, (byte) 0xfa,
			                      (byte) 0xb8, (byte) 0xd5};
			 System.out.println(new String(binary, "Big5"));
			 System.out.println(new String(binary));
			 */
	}
}

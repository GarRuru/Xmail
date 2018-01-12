package ntou.xmail;

public class mailFormat {
	private String sender;
	private String date;
	private String subject;
	private String context;
	
	public mailFormat()
	{
		
	}
	public mailFormat(String sender,String date,String subject,String context)
	{
		this.sender = sender;
		this.date = date;
		this.subject = subject;
		this.context = context;
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
	public String getSubject()
	{
		return subject;
	}
	
	public String getContext()
	{
		return context;
	}
}

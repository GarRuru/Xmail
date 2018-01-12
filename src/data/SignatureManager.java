package data;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SignatureManager {
	private FileReader fr;
	private FileWriter fw;
	
	public SignatureManager() {
		try {
			fr = new FileReader("Signature.txt");
		} catch (FileNotFoundException e) {
			fr = null;
			e.printStackTrace();
		}
		
		try {
			fw = new FileWriter("Signature.txt");
		} catch (IOException e) {
			fw = null;
			e.printStackTrace();
		}
		
	}
	
	public String getSignature() throws IOException {
		if(readAvailable()) {
			char c[]=new char[1000];
			fr.read(c);
			return String.valueOf(c);
		}
		return "";
	}
	
	public void writeSignature(String s) throws IOException {
		if(writeAvailable()) {
			fw.write(s);
			fw.flush();
		}
	}
	
	private boolean readAvailable() {
		return !(fr == null);
	}
	
	private boolean writeAvailable() {
		return !(fw == null);
	}
}

package data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class AccountManager {
	private FileReader fr;
	private FileWriter fw;
	private List<Account> accountList;
	
	public AccountManager() {
		try {
			fr = new FileReader("Account.txt");
		} catch (FileNotFoundException e) {
			fr = null;
			e.printStackTrace();
		}
		
		try {
			fw = new FileWriter("Account.txt");
		} catch (IOException e) {
			fw = null;
			e.printStackTrace();
		}
	}
	
	public void addAccount(Account data) throws IOException {
		if(writeAvailable()) {
			fw.write(data.toString());
		}
	}
	
	public void deleteAccount(Account target) {
		boolean matched;
		for(Account a : accountList) {
			matched = true;
			for(int i=0;i<7;++i) {
				if(a.data[i] != target.data[i]) {
					matched = false;
				}
			}
			
			if(matched) {
				accountList.remove(a);
			}
		}
	}
	
	public void readAccount() throws IOException {
		if(readAvailable()) {
			BufferedReader br = new BufferedReader(fr);
			Account data = new Account();
			if(br.ready()){
				for(int i=0;i<7;++i) {
					data.data[i] = br.readLine();
				}
				accountList.add(data);
			}
		}
	}
	
	public void writeAccount() throws IOException {
		if(writeAvailable()) {
			for(Account data : accountList) {
				fw.write(data.toString());
			}
		}
	}
	
	private boolean readAvailable() {
		return !(fr == null);
	}
	
	private boolean writeAvailable() {
		return !(fw == null);
	}
	
	@Override
	public void finalize() throws IOException{
		writeAccount();
	}

}

package data;

public class Account {
	public String[] data = new String[7];
	
	@Override
	public String toString() {
		String s = "";
		
		for(int i=0;i<7;++i) {
			s += data[i] + '\n';
		}

		return s;
	}
}

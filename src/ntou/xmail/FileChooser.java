package ntou.xmail;

import javax.swing.JFileChooser;

public class FileChooser {

	public static String chooseFile() {
		JFileChooser chooser = new JFileChooser();
		// FileNameExtensionFilter filter = new FileNameExtensionFilter("ALL Files");
		// chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile().getName();
		}
		return null;
	}
}

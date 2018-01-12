package ntou.xmail;

import java.awt.Component;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class PTTListCellRendener extends DefaultListCellRenderer {
	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		String label = (String) value;
		
		int labelIndex = label.indexOf("�@�̡G");
		String[] s = new String[3];
		s[0] = label.substring(labelIndex + 3, labelIndex = label.indexOf("���D�G"));
		s[1] = label.substring(labelIndex + 3, labelIndex = label.indexOf("�ɶ��G"));
		s[2] = label.substring(labelIndex + 3, labelIndex = label.indexOf("����G"));
		String labelText = "<html>[ " + s[2].toString() + " ]<br/><b>" + s[1].toString() + "</b><br>"
				+ s[0].toString();
		setText(labelText);
		return this;
	}

}

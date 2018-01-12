package ntou.xmail;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class MyListCellRenderer extends DefaultListCellRenderer {
	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		mailFormat label = (mailFormat) value;
		// System.out.println("¤T¤p°Õ"+label.getDate());

		String labelText = "<html>[" + label.getDate().substring(4, 10) + "]<br/><b>" + label.getSenderEmail()
				+ "</b><br/>" + label.getSubject();
		setText(labelText);
		return this;
	}

}

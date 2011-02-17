package com.googlecode.penguin.renders;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import com.googlecode.penguin.utils.Container;
import com.googlecode.penguin.utils.Item;

public class MediaContentRender extends JLabel implements ListCellRenderer {
	private static final long serialVersionUID = -595708734285816793L;

	@Override
	public Component getListCellRendererComponent(JList list, Object object,
			int index, boolean isSelected, boolean cellHasFocus) {
		
		if (object instanceof Container) {
			Container container = (Container)object;
			setText(container.getTitle());
			setIcon(container.getIcon());
			
		} else if (object instanceof Item) {
			Item item = (Item)object;
			setText(item.getTitle());
			setIcon(item.getIcon());
		}
		
		if (isSelected) {
            setBorder(BorderFactory.createEtchedBorder(new Color(204, 102, 0), new Color(204, 102, 0)));
        } else {
            setBorder(null);
        }
		
		return this;
	}
}
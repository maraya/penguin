package com.googlecode.penguin.renders;

import java.awt.Color;
import java.awt.Component;
import java.net.MalformedURLException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.googlecode.penguin.devices.MediaRender;

public class MediaRenderIconRender extends JLabel implements ListCellRenderer {	
	private static final long serialVersionUID = 5991117255896371031L;
	private ImageIcon defaultIcon;
	
	public MediaRenderIconRender() {		
		defaultIcon = new ImageIcon(MediaRenderIconRender.class.getResource("/com/googlecode/penguin/resources/unknown_device_48x48.png"));		
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		try {
			MediaRender mediaRender = (MediaRender) value; 
			
			if (isSelected) {
	            setBorder(BorderFactory.createEtchedBorder(new Color(204, 102, 0), new Color(204, 102, 0)));
	        } else {
	            setBorder(null);
	        }
			
			setText(mediaRender.getFriendlyName());				
			setToolTipText(mediaRender.getManufacture() +" - "+ mediaRender.getModelName());			
			
			List<ImageIcon> iconList = mediaRender.getImageIconList();
			
			if (iconList.size() > 0) {
				ImageIcon icon = null;
				for (int i=0; i<iconList.size(); i++) {
					icon = iconList.get(i); 
					
					if (icon.getIconHeight() == 48 && icon.getIconWidth() == 48) {
						break;
					}
				}
				
				if (icon == null) {
					setIcon(defaultIcon);					
				} else {
					setIcon(icon);
				}
			} else {
				setIcon(defaultIcon);
			}
			
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		}
		
		return this;		
	}
}

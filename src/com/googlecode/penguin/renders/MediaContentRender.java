package com.googlecode.penguin.renders;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.googlecode.penguin.types.DIDLNode;

public class MediaContentRender extends JLabel implements ListCellRenderer {
	private static final long serialVersionUID = -595708734285816793L;

	@Override
	public Component getListCellRendererComponent(JList list, Object object,
			int index, boolean isSelected, boolean cellHasFocus) {
		
		if (object instanceof DIDLNode) {
			setText(((DIDLNode) object).getTitle());
			
			if (((DIDLNode) object).isContainer()) {
				setIcon(((DIDLNode) object).getContainerIcon());			
			} else if (((DIDLNode) object).isItem()) {
				
				if (((DIDLNode) object).isAudioItem()) {
					setIcon(((DIDLNode) object).getAudioIcon());
				} else if (((DIDLNode) object).isImageItem()) {
					setIcon(((DIDLNode) object).getImageIcon());
				} else if (((DIDLNode) object).isVideoItem()) {
					setIcon(((DIDLNode) object).getVideoIcon());
				}
			}
			
			if (isSelected) {
	            setBorder(BorderFactory.createEtchedBorder(new Color(204, 102, 0), new Color(204, 102, 0)));
	        } else {
	            setBorder(null);
	        }
		}
		return this;
	}
}

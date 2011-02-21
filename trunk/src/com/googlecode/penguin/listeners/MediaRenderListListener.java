package com.googlecode.penguin.listeners;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import com.googlecode.penguin.ServerFinder;
import com.googlecode.penguin.panels.MediaRenderPanel;

public class MediaRenderListListener implements ListSelectionListener {
	private JList mediaRenderList; 
		
	public MediaRenderListListener (MediaRenderPanel mediaRenderPanel) {
		this.mediaRenderList = mediaRenderPanel.mediaRenderList;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == false) {
			int index = mediaRenderList.getSelectedIndex();
			ServerFinder.setMediaRenderSelectedIndex(index);
			MediaRenderPanel.isMediaRenderSelected = true;			
		}
	}	
}

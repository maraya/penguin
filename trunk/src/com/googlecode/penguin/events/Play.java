package com.googlecode.penguin.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import com.googlecode.penguin.MediaRender;
import com.googlecode.penguin.ServerFinder;
import com.googlecode.penguin.listeners.ContentListListener;
import com.googlecode.penguin.panels.MediaServerPanel;
import com.googlecode.penguin.services.AVTransport;
import com.googlecode.penguin.utils.ActionException;
import com.googlecode.penguin.utils.DIDL;
import com.googlecode.penguin.utils.DIDLNode;
import com.googlecode.penguin.utils.ServiceException;

public class Play implements ActionListener{
	private JButton stopButton, pauseButton;	
	
	public Play (MediaServerPanel mediaServerPanel) {
		stopButton = mediaServerPanel.stopButton;
		pauseButton = mediaServerPanel.pauseButton;		
	}
	
	@Override
	public void actionPerformed (ActionEvent e) {			
		stopButton.setEnabled(true);
		pauseButton.setEnabled(true);
		
		try {
			int mediaRenderIndex = ServerFinder.getMediaRenderSelectedIndex();	
			MediaRender mediaRender = ServerFinder.getMediaRender(mediaRenderIndex);
			int index = ContentListListener.getSelectedItemIndex();
			DIDLNode didlNode = DIDL.getDIDLNode(index);
			
			AVTransport avTransport = new AVTransport(mediaRender);
			avTransport.setAVTransportURI(didlNode);
			avTransport.play();
			
		} catch (ServiceException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "MediaRender error: AVTransport service doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (ActionException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "MediaRender error: Cannot execute Play action", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}

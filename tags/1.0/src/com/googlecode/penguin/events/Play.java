package com.googlecode.penguin.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import com.googlecode.penguin.ServerFinder;
import com.googlecode.penguin.devices.MediaRender;
import com.googlecode.penguin.listeners.ContentListListener;
import com.googlecode.penguin.panels.MediaRenderPanel;
import com.googlecode.penguin.panels.MediaServerPanel;
import com.googlecode.penguin.services.AVTransport;
import com.googlecode.penguin.services.RenderingControl;
import com.googlecode.penguin.types.DIDL;
import com.googlecode.penguin.types.DIDLNode;
import com.googlecode.penguin.utils.ActionException;
import com.googlecode.penguin.utils.ServiceException;

public class Play implements ActionListener{
	private JButton stopButton, pauseButton;
	private JSlider volumeSlider;
	
	public Play (MediaServerPanel mediaServerPanel) {
		stopButton = mediaServerPanel.stopButton;
		pauseButton = mediaServerPanel.pauseButton;	
		volumeSlider = mediaServerPanel.volumeSlider;
	}
	
	@Override
	public void actionPerformed (ActionEvent e) {			
		stopButton.setEnabled(true);
		pauseButton.setEnabled(true);
		
		try {
			if (MediaRenderPanel.isMediaRenderSelected()) {
				
				int mediaRenderIndex = ServerFinder.getMediaRenderSelectedIndex();	
				MediaRender mediaRender = ServerFinder.getMediaRender(mediaRenderIndex);
				int index = ContentListListener.getSelectedItemIndex();
				DIDLNode didlNode = DIDL.getDIDLNode(index);
				
				RenderingControl renderControl = new RenderingControl(mediaRender);			
				volumeSlider.setValue(renderControl.getVolume());
				volumeSlider.setEnabled(true);
				
				AVTransport avTransport = new AVTransport(mediaRender);
				avTransport.setAVTransportURI(didlNode);
				avTransport.play();
			} else {				
				JOptionPane.showMessageDialog(null, "You must select a playback server from the MediaRenderers tab", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		} catch (ServiceException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "MediaRender error: AVTransport service doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (ActionException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "MediaRender error: Cannot execute Play action", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}

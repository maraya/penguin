package com.googlecode.penguin.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import com.googlecode.penguin.ServerFinder;
import com.googlecode.penguin.devices.MediaRender;
import com.googlecode.penguin.services.AVTransport;
import com.googlecode.penguin.utils.ActionException;
import com.googlecode.penguin.utils.ServiceException;

public class Pause implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			int mediaRenderIndex = ServerFinder.getMediaRenderSelectedIndex();	
			MediaRender mediaRender = ServerFinder.getMediaRender(mediaRenderIndex);
			AVTransport avTransport = new AVTransport(mediaRender);			
			avTransport.pause();
			
		} catch (ServiceException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "MediaRender error: AVTransport service doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (ActionException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "MediaRender error: Cannot execute Pause action", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}

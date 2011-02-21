package com.googlecode.penguin.listeners;

import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import com.googlecode.penguin.MediaRender;
import com.googlecode.penguin.ServerFinder;
import com.googlecode.penguin.services.RenderingControl;
import com.googlecode.penguin.utils.ActionException;
import com.googlecode.penguin.utils.ServiceException;

public class VolumeListener implements ChangeListener {

	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider volumeSlider = (JSlider)e.getSource();
		
        if (!volumeSlider.getValueIsAdjusting()) {
        	try {
	        	int volume = (int)volumeSlider.getValue();
	        	int index = ServerFinder.getMediaRenderSelectedIndex();
	        	MediaRender mediaRender = ServerFinder.getMediaRender(index);
	        	RenderingControl renderControl = new RenderingControl(mediaRender);
	        	renderControl.setVolume(volume);
	        	
        	} catch (ServiceException ex) {
        		System.out.println(ex.getMessage());
        		JOptionPane.showMessageDialog(null, "MediaRender error: RenderingControl service doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);
        	} catch (ActionException ex) {
        		System.out.println(ex.getMessage());
        		JOptionPane.showMessageDialog(null, "MediaRender error: Cannot execute SetVolume action", "Error", JOptionPane.ERROR_MESSAGE);
        	}	    
        } 		
	}
}

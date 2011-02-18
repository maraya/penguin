package com.googlecode.penguin.services;

import org.cybergarage.upnp.Action;
import org.cybergarage.upnp.Service;
import com.googlecode.penguin.MediaRender;
import com.googlecode.penguin.utils.ActionException;
import com.googlecode.penguin.utils.ServiceException;

public class RenderingControl {
	private MediaRender mediaRender;
	private Service service;
	
	public RenderingControl (MediaRender mediaRender) throws ServiceException {
		this.mediaRender = mediaRender;
		setService();
	}
	
	private void setService() throws ServiceException {
		try {			
			service = mediaRender.getService("urn:schemas-upnp-org:service:RenderingControl:1");
			String host = mediaRender.getCompleteHost();
			String controlURL = service.getControlURL();
			String eventSubURL = service.getEventSubURL();
			String scpdURL = service.getSCPDURL();
			
			if (!controlURL.startsWith("http://")) {
				if (controlURL.charAt(0) != '/') {
					controlURL = host +"/"+ controlURL;
				} else {
					controlURL = host + controlURL;
				}
			}
			
			if (!eventSubURL.startsWith("http://")) {
				if (eventSubURL.charAt(0) != '/') {
					eventSubURL = host +"/"+ eventSubURL;
				} else {
					eventSubURL = host +eventSubURL;
				}
			}
			
			if (!scpdURL.startsWith("http://")) {
				if (scpdURL.charAt(0) != '/') {
					scpdURL = host +"/"+ scpdURL;
				} else {
					scpdURL = host + scpdURL;
				}
			}
			
			service.setControlURL(controlURL);
			service.setEventSubURL(eventSubURL);
			service.setSCPDURL(scpdURL);
			
		} catch (Exception e) {
			throw new ServiceException("RenderingControl", e.getMessage());
		}		
	}
	
	public void setVolume (int volume) throws ActionException {
		Action action = service.getAction("SetVolume");
			
		action.setArgumentValue("InstanceID", "0");
		action.setArgumentValue("Channel", "Master");
		action.setArgumentValue("DesiredVolume", volume);
			
		if (action.postControlAction() == false) {
			throw new ActionException("SetVolume", action.getStatus().getDescription());	            
		}		
	}	
}

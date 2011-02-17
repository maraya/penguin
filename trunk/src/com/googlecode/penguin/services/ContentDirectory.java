package com.googlecode.penguin.services;

import org.cybergarage.upnp.Action;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.Service;
import com.googlecode.penguin.MediaServer;
import com.googlecode.penguin.utils.ActionException;
import com.googlecode.penguin.utils.ServiceException;

public class ContentDirectory {	
	private Device device;
	private String host;
	private Service service;
	
	public ContentDirectory(MediaServer mediaServer) throws ServiceException {
		this.device = mediaServer.getDevice();
		this.host = mediaServer.getCompleteHost();		
		
		setService();
	}
	
	private void setService() throws ServiceException {
		try {			
			service = device.getService("urn:schemas-upnp-org:service:ContentDirectory:1");
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
			throw new ServiceException("ContentDirectory", e.getMessage());
		}		
	}
	
	public String browse (String objectID) throws ActionException {
		String result = null;
		
		Action action = service.getAction("Browse");
			
		action.setArgumentValue("ObjectID", objectID);
		action.setArgumentValue("BrowseFlag", "BrowseDirectChildren");
		action.setArgumentValue("Filter", "*");
			
		if (action.postControlAction() == false) {
			throw new ActionException("Browse", action.getStatus().getDescription());	            
		}
		
	    result = action.getArgument("Result").getValue();        	    
		return result;
	}	
}

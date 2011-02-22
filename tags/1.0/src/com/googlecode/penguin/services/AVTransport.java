package com.googlecode.penguin.services;

import org.cybergarage.upnp.Action;
import org.cybergarage.upnp.Service;
import com.googlecode.penguin.devices.MediaRender;
import com.googlecode.penguin.types.DIDLNode;
import com.googlecode.penguin.utils.ActionException;
import com.googlecode.penguin.utils.ServiceException;

public class AVTransport {
	private MediaRender mediaRender;
	private Service service;
	
	public AVTransport (MediaRender mediaRender) throws ServiceException {
		this.mediaRender = mediaRender;
		setService();
	}
	
	private void setService() throws ServiceException {
		try {			
			service = mediaRender.getService("urn:schemas-upnp-org:service:AVTransport:1");
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
			throw new ServiceException("AVTransport", e.getMessage());
		}		
	}
	
	public void setAVTransportURI (DIDLNode didlNode) throws ActionException {
		Action action = service.getAction("SetAVTransportURI");
		String metaData = "<DIDL-Lite xmlns=\"urn:schemas-upnp-org:metadata-1-0/DIDL-Lite/\" xmlns:dc=\"http://purl.org/dc/elements/1.1/\" xmlns:upnp=\"urn:schemas-upnp-org:metadata-1-0/upnp/\" xmlns:dlna=\"urn:schemas-dlna-org:device-1-0\" xmlns:pv=\"http://www.pv.com/pvns/\">" +
						     didlNode.getNode().toString() +
						  "</DIDL-Lite>"; 
		String currentURI = null;
		
		if (didlNode.isAudioItem()) {
			currentURI = didlNode.getAudioItem().getAudioURI();
		} else if (didlNode.isVideoItem()) {
			currentURI = didlNode.getVideoItem().getVideoURI();
		}
		
		action.setArgumentValue("InstanceID", "0");
		action.setArgumentValue("CurrentURI", currentURI);
		action.setArgumentValue("CurrentURIMetaData", metaData);
			
		if (action.postControlAction() == false) {
			throw new ActionException("SetAVTransportURI", action.getStatus().getDescription());	            
		}		
	}	
	
	public void play() throws ActionException {
		Action action = service.getAction("Play");
		
		action.setArgumentValue("InstanceID", "0");		
		action.setArgumentValue("Speed", "1");
			
		if (action.postControlAction() == false) {
			throw new ActionException("Play", action.getStatus().getDescription());	            
		}
	}
	
	public void stop() throws ActionException {
		Action action = service.getAction("Stop");		
		action.setArgumentValue("InstanceID", "0");		
					
		if (action.postControlAction() == false) {
			throw new ActionException("Stop", action.getStatus().getDescription());	            
		}
	}
	
	public void pause() throws ActionException {
		Action action = service.getAction("Pause");		
		action.setArgumentValue("InstanceID", "0");		
			
		if (action.postControlAction() == false) {
			throw new ActionException("Pause", action.getStatus().getDescription());	            
		}
	}
}

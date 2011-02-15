package com.googlecode.penguin.services;

import org.cybergarage.upnp.Action;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.Service;
import com.googlecode.penguin.MediaServer;
import com.googlecode.penguin.utils.ActionException;
import com.googlecode.penguin.utils.DIDL;
import com.googlecode.penguin.utils.ServiceException;

public class ContentDirectory {	
	private Device device;
	private String host;
	private Service service;
	
	public ContentDirectory(MediaServer mediaServer) {
		this.device = mediaServer.getDevice();
		this.host = mediaServer.getCompleteHost();
	}
	
	public Service getService () throws ServiceException {		
		try {
			service = device.getService("urn:schemas-upnp-org:service:ContentDirectory:1");
			service.setControlURL(host + service.getControlURL());
			service.setEventSubURL(host + service.getEventSubURL());
			service.setSCPDURL(host + service.getSCPDURL());
			
		} catch (Exception e) {
			throw new ServiceException("ContentDirectory");
		}
		return service;
	}
	
	public DIDL browse () throws ActionException {
		String result = null;
		try {
			Action action = getService().getAction("Browse");
			
			action.setArgumentValue("ObjectID", "0$1$7");
			action.setArgumentValue("BrowseFlag", "BrowseDirectChildren");
			action.setArgumentValue("Filter", "*");
			
			if (action.postControlAction() == false) {
				throw new ActionException("Browse");
	            //System.out.println("-------->" + action.getStatus().getDescription());
	            //System.out.println("-------->" + action.getControlStatus().getDescription());
			}
	        result = action.getArgument("Result").getValue();
	        
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
		}
		
		return new DIDL(result);
	}
}

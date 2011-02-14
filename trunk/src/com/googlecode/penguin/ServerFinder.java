package com.googlecode.penguin;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import org.cybergarage.upnp.ControlPoint;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.device.InvalidDescriptionException;
import org.cybergarage.upnp.device.SearchResponseListener;
import org.cybergarage.upnp.ssdp.SSDPPacket;

public class ServerFinder extends ControlPoint implements SearchResponseListener {
	private List<MediaServer> mediaServerList;
	private List<String> mediaServerLocation, mediaRenderLocation;
	private DefaultListModel mediaServerModel;
	
	public ServerFinder (DefaultListModel mediaServerModel) {
        addSearchResponseListener(this);        
        mediaServerList = new ArrayList<MediaServer>();
        mediaRenderLocation = mediaServerLocation = new ArrayList<String>();
        this.mediaServerModel = mediaServerModel;
        
        try {
	        start();
	        search("urn:schemas-upnp-org:device:MediaServer:1");
	        search("urn:schemas-upnp-org:device:MediaRenderer:1");
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }        
	}

	@Override
	public void deviceSearchResponseReceived(SSDPPacket ssdpPacket) {
		String target = ssdpPacket.getST();
        String location = ssdpPacket.getLocation();
        
        if (target.equalsIgnoreCase("urn:schemas-upnp-org:device:MediaServer:1")) {
        	if (!mediaServerLocation.contains(location)) {
	            try {
	            	Device device = new Device(new URL(location).openStream());
	            	MediaServer mediaServer = new MediaServer(device, location);
	            	mediaServerList.add(mediaServer);
	            	mediaServerModel.addElement(mediaServer);
	            	
	            } catch (IOException e) {
	            	System.out.println(e.getMessage());
	            } catch (InvalidDescriptionException e) {
	            	System.out.println(e.getMessage());
	            } finally {
	            	mediaServerLocation.add(location);
	            }
        	}        	
        } else if (target.equalsIgnoreCase("urn:schemas-upnp-org:device:MediaRenderer:1")) {
        	if (!mediaRenderLocation.contains(location)) {
        		System.out.println(location);
        		
        		mediaRenderLocation.add(location);
        	}
        }
	}
}

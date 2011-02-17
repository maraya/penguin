package com.googlecode.penguin;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.cybergarage.upnp.ControlPoint;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.device.InvalidDescriptionException;
import org.cybergarage.upnp.device.SearchResponseListener;
import org.cybergarage.upnp.ssdp.SSDPPacket;
import com.googlecode.penguin.panels.MediaRenderPanel;
import com.googlecode.penguin.panels.MediaServerPanel;

public class ServerFinder extends ControlPoint implements SearchResponseListener {
	private static List<MediaServer> mediaServerList;
	private static List<MediaRender> mediaRenderList;
	private static int mediaServerIndex; 
	private List<String> mediaServerLocation, mediaRenderLocation;
	private MediaServerPanel mediaServerPanel;
	private MediaRenderPanel mediaRenderPanel;
	
	public ServerFinder (MediaServerPanel mediaServerPanel, MediaRenderPanel mediaRenderPanel) {
        addSearchResponseListener(this);        
        mediaServerList = new ArrayList<MediaServer>();
        mediaRenderList = new ArrayList<MediaRender>();
        mediaRenderLocation = mediaServerLocation = new ArrayList<String>();
        this.mediaServerPanel = mediaServerPanel;
        this.mediaRenderPanel = mediaRenderPanel;
                
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
	            MediaServer mediaServer = new MediaServer(location);
	            mediaServerList.add(mediaServer);
	            mediaServerPanel.mediaServerModel.addElement(mediaServer);	            
	            mediaServerLocation.add(location);
	            
        	}        	
        } else if (target.equalsIgnoreCase("urn:schemas-upnp-org:device:MediaRenderer:1")) {
        	if (!mediaRenderLocation.contains(location)) {        		
        		try {
        			Device device = new Device(new URL(location).openStream());
        			MediaRender mediaRender = new MediaRender(device, location);
        			mediaRenderList.add(mediaRender); 
        			mediaRenderPanel.mediaRenderModel.addElement(mediaRender);
        			
        		} catch (IOException e) {
	            	System.out.println(e.getMessage());
	            } catch (InvalidDescriptionException e) {
	            	System.out.println(e.getMessage());
	            } finally {
	            	mediaRenderLocation.add(location);
	            }        		
        	}
        }
	}
	
	public static MediaServer getMediaServer(int index) {
		return mediaServerList.get(index);
	}
	
	public static MediaRender getMediaRender(int index) {
		return mediaRenderList.get(index);
	}	
	
	public static void setMediaServerSelectedIndex(int index) {
		mediaServerIndex = index;
	}	
	
	public static int getMediaServerSelectedIndex() {
		return mediaServerIndex;
	}
}
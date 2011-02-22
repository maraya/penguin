package com.googlecode.penguin;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.cybergarage.upnp.ControlPoint;
import org.cybergarage.upnp.device.InvalidDescriptionException;
import org.cybergarage.upnp.device.SearchResponseListener;
import org.cybergarage.upnp.ssdp.SSDPPacket;
import com.googlecode.penguin.devices.MediaRender;
import com.googlecode.penguin.devices.MediaServer;
import com.googlecode.penguin.panels.MediaRenderPanel;
import com.googlecode.penguin.panels.MediaServerPanel;

public class ServerFinder extends ControlPoint implements SearchResponseListener {
	private static List<MediaServer> mediaServerList;
	private static List<MediaRender> mediaRenderList;
	private static int mediaServerIndex, mediaRenderIndex; 
	private List<String> mediaServerUuid, mediaRenderUuid;
	private MediaServerPanel mediaServerPanel;
	private MediaRenderPanel mediaRenderPanel;
	
	public ServerFinder (MediaServerPanel mediaServerPanel, MediaRenderPanel mediaRenderPanel) {
        addSearchResponseListener(this);        
        mediaServerList = new ArrayList<MediaServer>();
        mediaRenderList = new ArrayList<MediaRender>();
        mediaRenderUuid = mediaServerUuid = new ArrayList<String>();
        this.mediaServerPanel = mediaServerPanel;
        this.mediaRenderPanel = mediaRenderPanel;
                
        try {
	        start();
	        search("urn:schemas-upnp-org:device:MediaServer:1");
	        search("urn:schemas-upnp-org:device:MediaRenderer:1");
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, "There's another instance in execution", "Error", JOptionPane.ERROR_MESSAGE);        	
        	System.out.println(e.getMessage());
        }        
	}

	@Override
	public void deviceSearchResponseReceived(SSDPPacket ssdpPacket) {
		String target = ssdpPacket.getST();
        String location = ssdpPacket.getLocation();
        String uuid = ssdpPacket.getUSN();
        
        if (target.equalsIgnoreCase("urn:schemas-upnp-org:device:MediaServer:1")) {
        	if (!mediaServerUuid.contains(uuid)) {
        		try {
        			MediaServer mediaServer = new MediaServer(location);
        			mediaServerList.add(mediaServer);
        			mediaServerPanel.mediaServerModel.addElement(mediaServer);	            
        			
        		} catch (MalformedURLException e) {
        			System.out.println(e.getMessage());
        		} catch (InvalidDescriptionException e) {
        			System.out.println(e.getMessage());
        		} catch (IOException e) {
        			System.out.println(e.getMessage());
        		} finally {
        			mediaServerUuid.add(uuid);
        		}
        	}        	
        } else if (target.equalsIgnoreCase("urn:schemas-upnp-org:device:MediaRenderer:1")) {
        	if (!mediaRenderUuid.contains(uuid)) {
        		try {        			
        			MediaRender mediaRender = new MediaRender(location);
        			mediaRenderList.add(mediaRender); 
        			mediaRenderPanel.mediaRenderModel.addElement(mediaRender);
        			
        		} catch (IOException e) {
	            	System.out.println(e.getMessage());
	            } catch (InvalidDescriptionException e) {
	            	System.out.println(e.getMessage());
	            } finally {
	            	mediaRenderUuid.add(uuid);
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
	
	public static void setMediaRenderSelectedIndex(int index) {
		mediaRenderIndex = index;
	}	
	
	public static int getMediaRenderSelectedIndex() {
		return mediaRenderIndex;
	}	
}
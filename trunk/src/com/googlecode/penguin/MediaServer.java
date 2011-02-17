package com.googlecode.penguin;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.Icon;
import org.cybergarage.upnp.IconList;
import org.cybergarage.upnp.device.InvalidDescriptionException;

public class MediaServer {
	private Device device;
	private URL locationURL;
	
	public MediaServer (String location) {
		try {
			this.device = new Device(new URL(location).openStream());			 
			this.locationURL = new URL(location);
			
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		} catch (InvalidDescriptionException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public List<ImageIcon> getIconList () throws MalformedURLException {		
		IconList iconList = device.getIconList();
		List<ImageIcon> imageIconList = new ArrayList<ImageIcon>();
		
		for (int i=0; i<iconList.size(); i++) {
			Icon icon = iconList.getIcon(i);			
			String iconURL = locationURL.getProtocol() +"://"+ locationURL.getAuthority() + icon.getURL();
			imageIconList.add(new ImageIcon(new URL(iconURL)));			
		}
		return imageIconList;
	}
	
	public String getFriendlyName () {		
		return device.getFriendlyName();	
	}	
	
	public String getManufacture () {
		return device.getManufacture();
	}
	
	public String getModelDescription () {
		return device.getModelDescription();
	}
	
	public String getModelName () {
		return device.getModelName();
	}
	
	public String getCompleteHost() {
		return locationURL.getProtocol()+"://"+locationURL.getAuthority();
	}
	
	public Device getDevice() {
		return device;
	}
}

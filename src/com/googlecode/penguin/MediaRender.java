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

public class MediaRender extends Device {	
	private URL locationURL;
	
	public MediaRender (String location) throws MalformedURLException, InvalidDescriptionException, IOException {
		super(new URL(location).openStream());		
		this.locationURL = new URL(location);		
	}
		
	public List<ImageIcon> getImageIconList () throws MalformedURLException {		
		IconList iconList = getIconList();
		List<ImageIcon> imageIconList = new ArrayList<ImageIcon>();
		
		for (int i=0; i<iconList.size(); i++) {
			Icon icon = iconList.getIcon(i);			
			String iconURL = locationURL.getProtocol() +"://"+ locationURL.getAuthority() + icon.getURL();
			imageIconList.add(new ImageIcon(new URL(iconURL)));			
		}
		return imageIconList;
	}	
	
	public String getCompleteHost() {
		return locationURL.getProtocol()+"://"+locationURL.getAuthority();
	}	
}

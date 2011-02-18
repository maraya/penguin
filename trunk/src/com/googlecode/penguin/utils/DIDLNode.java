package com.googlecode.penguin.utils;

import javax.swing.ImageIcon;
import org.cybergarage.xml.Node;
import com.googlecode.penguin.utils.interfaces.Container;
import com.googlecode.penguin.utils.interfaces.Item;

public class DIDLNode implements Item, Container {
	private Node node;
	private ImageIcon itemIcon, containerIcon;
	
	public DIDLNode (Node node) {
		this.node = node;
		this.itemIcon = new ImageIcon(Container.class.getResource("/com/googlecode/penguin/resources/track.jpg"));
		this.containerIcon = new ImageIcon(Container.class.getResource("/com/googlecode/penguin/resources/container.jpg"));
	}	
	
	public boolean isContainer() {
		if (node.getName().equalsIgnoreCase("container")) {		
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isItem() {
		if (node.getName().equalsIgnoreCase("item")) {		
			return true;
		} else {
			return false;
		}	
	}
	
	public Node getNode() {
		return node;
	}

	@Override
	public String getID() {
		return node.getAttributeValue("id");
	}

	@Override
	public String getParentID() {
		return node.getAttributeValue("parentID");
	}

	@Override
	public String getTitle() {
		return node.getNodeValue("dc:title");
	}

	@Override
	public String getModificationTime() {
		return node.getNodeValue("pv:modificationTime");
	}

	@Override
	public String getUpnpClass() {
		return node.getNodeValue("upnp:class");
	}

	@Override
	public String getGenre() {
		return node.getNodeValue("upnp:genre");
	}

	@Override
	public String getArtist() {
		return node.getNodeValue("upnp:artist");
	}

	@Override
	public String getAlbum() {
		return node.getNodeValue("upnp:album");
	}
	
	@Override
	public String getCreator() {
		return node.getNodeValue("dc:creator");
	}
	
	@Override
	public String getLastPlayedTime() {
		return node.getNodeValue("pv:lastPlayedTime");
	}
	
	@Override
	public String getPlayCount() {
		return node.getNodeValue("pv:playcount");
	}
	
	@Override
	public String getAddedTime() {
		return node.getNodeValue("pv:addedTime");
	}
	
	@Override
	public String getLastUpdated() {
		return node.getNodeValue("pv:lastUpdated");				
	}
	
	@Override
	public String getAlbumArtURI() {
		return node.getNodeValue("upnp:albumArtURI");
	}
	
	@Override
	public AudioTrack getAudioTrack() {
		return new AudioTrack(node.getNode("res"));
	}
	
	@Override
	public ImageIcon getItemIcon() {
		return itemIcon;
	}

	@Override
	public String getChildCount() {
		return node.getAttributeValue("childCount");
	}

	@Override
	public ImageIcon getContainerIcon() {
		return containerIcon;
	}
}

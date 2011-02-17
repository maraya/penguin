package com.googlecode.penguin.utils;

import javax.swing.ImageIcon;

import org.cybergarage.xml.Node;

public class Item {
	private Node node;
	private ImageIcon icon;
	
	public Item (Node node) {			
		this.node = node;
		this.icon = new ImageIcon(Container.class.getResource("/com/googlecode/penguin/resources/track.jpg"));
	}
		
	public String getID() {
		return node.getAttributeValue("id");
	}
	
	public String getParentID() {
		return node.getAttributeValue("parentID");
	}
	
	public String getTitle() {
		return node.getNodeValue("dc:title");
	}
	
	public String getModificationTime() {
		return node.getNodeValue("pv:modificationTime");
	}
	
	public String getUpnpClass() {
		return node.getNodeValue("upnp:class");
	}
	
	public String getGenre() {
		return node.getNodeValue("upnp:genre");
	}
	
	public String getArtist() {
		return node.getNodeValue("upnp:artist");
	}
	
	public String getAlbum() {
		return node.getNodeValue("upnp:album");
	}
	
	public String getCreator() {
		return node.getNodeValue("dc:creator");
	}
	
	public String getLastPlayedTime() {
		return node.getNodeValue("pv:lastPlayedTime");
	}
	
	public String getPlayCount() {
		return node.getNodeValue("pv:playcount");
	}
	
	public String getAddedTime() {
		return node.getNodeValue("pv:addedTime");
	}
	
	public String getLastUpdated() {
		return node.getNodeValue("pv:lastUpdated");				
	}
	
	public String getAlbumArtURI() {
		return node.getNodeValue("upnp:albumArtURI");
	}
	
	public Node getNode() {
		return node;
	}
	
	public AudioTrack getAudioTrack() {
		return new AudioTrack(node.getNode("res"));
	}
	
	public boolean isItem () {
		if (node.getName().equalsIgnoreCase("item")) {		
			return true;
		} else {
			return false;
		}		
	}
	
	public ImageIcon getIcon() {
		return icon;
	}
}
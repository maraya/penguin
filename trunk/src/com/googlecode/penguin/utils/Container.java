package com.googlecode.penguin.utils;

import javax.swing.ImageIcon;
import org.cybergarage.xml.Node;

public class Container {
	private Node node;
	private ImageIcon icon;
	
	public Container (Node node) {		
		this.node = node;
		this.icon = new ImageIcon(Container.class.getResource("/com/googlecode/penguin/resources/container.jpg"));
	}	
	
	public String getChildCount() {
		return node.getAttributeValue("childCount");
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
	
	public boolean isContainer () {
		if (node.getName().equalsIgnoreCase("container")) {		
			return true;
		} else {
			return false;
		}		
	}
	
	public ImageIcon getIcon() {
		return icon;
	}
}
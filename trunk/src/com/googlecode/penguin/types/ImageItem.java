package com.googlecode.penguin.types;

import org.cybergarage.xml.Node;

public class ImageItem {
	private Node node;
		
	public ImageItem (Node node) {
		this.node = node;
	}
	
	public String getResolution() {
		return node.getAttributeValue("resolution");		
	}
	
	public String getProtocolInfo() {
		return node.getAttributeValue("protocolInfo");		
	}
}

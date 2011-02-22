package com.googlecode.penguin.types;

import org.cybergarage.xml.Node;

public class VideoItem {
	private Node node;
	
	public VideoItem (Node node) {
		this.node = node;
	}
	
	public String getSize() {
		return node.getAttributeValue("size");
	}
	
	public String getDuration() {
		return node.getAttributeValue("duration");		
	}
	
	public String getProtocolInfo() {
		return node.getAttributeValue("protocolInfo");		
	}
	
	public String getVideoURI() {
		return node.getValue();		
	}
	
	public String getBitrate() {
		return node.getAttributeValue("bitrate");		
	}
	
	public String getResolution() {
		return node.getAttributeValue("resolution");		
	}
}

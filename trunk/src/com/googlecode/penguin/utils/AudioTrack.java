package com.googlecode.penguin.utils;

import org.cybergarage.xml.Node;

public class AudioTrack {
	private Node node;
	
	public AudioTrack (Node node) {
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
	
	public String getTrackURI() {
		return node.getValue();		
	}
}
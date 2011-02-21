package com.googlecode.penguin.types;

import org.cybergarage.xml.Node;

public class AudioItem {
	private Node node;
	
	public AudioItem (Node node) {
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
	
	public String getAudioURI() {
		return node.getValue();		
	}
}
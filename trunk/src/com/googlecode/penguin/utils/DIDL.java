package com.googlecode.penguin.utils;

import java.util.ArrayList;
import java.util.List;
import org.cybergarage.xml.Node;
import org.cybergarage.xml.ParserException;
import org.cybergarage.xml.parser.XercesParser;

public class DIDL {
	private List<Container> containerList;
	private List<Item> itemList;
	
	public DIDL (String content) {
		containerList = new ArrayList<Container>();
		itemList = new ArrayList<Item>();
		XercesParser parser = new XercesParser();
		
		try {
			content = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\r\n" + content;
			Node node = parser.parse(content);
        
	        for (int i=0; i<=node.getNNodes()-1; i++) {
	        	Node subNode = node.getNode(i);	        	
	        	Container container = new Container(subNode);
	        	Item item = new Item(subNode);
	        	
	        	if (container.isContainer()) {
	        		containerList.add(container);
	        	} else if (item.isItem()) {
	        		itemList.add(item);
	        	}
	        }	        
		} catch (ParserException e) {
			System.out.println(e.getMessage());
		}		
	}	
	
	public List<Container> getContainerList() {	
		return containerList;
	}	
	
	public List<Item> getItemList() {
		return itemList;
	}
}

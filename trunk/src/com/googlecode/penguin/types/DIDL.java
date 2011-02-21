package com.googlecode.penguin.types;

import java.util.ArrayList;
import java.util.List;
import org.cybergarage.xml.Node;
import org.cybergarage.xml.ParserException;
import org.cybergarage.xml.parser.XercesParser;

public class DIDL {	
	private static List<DIDLNode> didlNodeList;
	
	public DIDL (String content) {
		didlNodeList = new ArrayList<DIDLNode>();
		XercesParser parser = new XercesParser();
				
		try {
			content = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\r\n" + content;
			Node node = parser.parse(content);
        
	        for (int i=0; i<=node.getNNodes()-1; i++) {
	        	Node subNode = node.getNode(i);	   
	        	DIDLNode didlNode = new DIDLNode(subNode);
	        	
	        	didlNodeList.add(didlNode);
	        }	        
		} catch (ParserException e) {
			System.out.println(e.getMessage());
		}		
	}	
	
	public List<DIDLNode> getDIDLNodeList() {
		return didlNodeList;
	}
	
	public static DIDLNode getDIDLNode(int index) {
		return didlNodeList.get(index);
	}	
}

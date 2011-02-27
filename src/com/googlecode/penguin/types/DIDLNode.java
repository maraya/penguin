package com.googlecode.penguin.types;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import org.cybergarage.xml.Node;

import com.googlecode.penguin.PenguinConstants;
import com.googlecode.penguin.utils.interfaces.Container;
import com.googlecode.penguin.utils.interfaces.Item;

public class DIDLNode implements Item, Container {
	private Node node;
	
	public DIDLNode (Node node) {
		this.node = node;		
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
	public AudioItem getAudioItem() {
		if (isAudioItem()) {
			return new AudioItem(node.getNode("res"));
		} else {
			return null;
		}
	}
	
	@Override
	public String getChildCount() {
		return node.getAttributeValue("childCount");
	}

	@Override
	public boolean isVideoItem() {
		Pattern pattern = Pattern.compile("videoItem");
		Matcher matcher = pattern.matcher(getUpnpClass());
		
		if (matcher.find()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isAudioItem() {
		Pattern pattern = Pattern.compile("audioItem");
		Matcher matcher = pattern.matcher(getUpnpClass());
		
		if (matcher.find()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public VideoItem getVideoItem() {
		if (isVideoItem()) {
			return new VideoItem(node.getNode("res"));
		} else {
			return null;
		}
	}
	
	@Override
	public ImageItem getImageItem() {
		if (isImageItem()) {
			return new ImageItem(node.getNode("res"));
		} else {
			return null;
		}
	}
	
	@Override
	public ImageIcon getContainerIcon() {
		return PenguinConstants.getContainerIcon();
	}
	
	@Override
	public ImageIcon getAudioIcon() {
		return PenguinConstants.getAudioIcon();		
	}

	@Override
	public ImageIcon getVideoIcon() {
		return PenguinConstants.getVideoIcon();		
	}

	@Override
	public ImageIcon getImageIcon() {
		return PenguinConstants.getImageIcon();
	}

	@Override
	public boolean isImageItem() {
		Pattern pattern = Pattern.compile("imageItem");
		Matcher matcher = pattern.matcher(getUpnpClass());
		
		if (matcher.find()) {
			return true;
		} else {
			return false;
		}
	}
}

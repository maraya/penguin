package com.googlecode.penguin.utils.interfaces;

import javax.swing.ImageIcon;
import com.googlecode.penguin.types.AudioItem;
import com.googlecode.penguin.types.ImageItem;
import com.googlecode.penguin.types.VideoItem;

public interface Item {
	
	public String getID();
	
	public String getParentID();
	
	public String getTitle();
	
	public String getModificationTime();
	
	public String getUpnpClass();
	
	public String getGenre();
	
	public String getArtist();
	
	public String getAlbum();
	
	public String getCreator();
	
	public String getLastPlayedTime();
	
	public String getPlayCount();
	
	public String getAddedTime();
	
	public String getLastUpdated();
	
	public String getAlbumArtURI();
	
	public AudioItem getAudioItem();
	
	public VideoItem getVideoItem();
	
	public ImageItem getImageItem();
	
	public ImageIcon getAudioIcon();
	
	public ImageIcon getVideoIcon();
	
	public ImageIcon getImageIcon();
	
	public boolean isVideoItem();
	
	public boolean isAudioItem();
	
	public boolean isImageItem();
}
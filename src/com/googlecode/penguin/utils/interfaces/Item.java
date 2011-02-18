package com.googlecode.penguin.utils.interfaces;

import javax.swing.ImageIcon;
import com.googlecode.penguin.utils.AudioTrack;

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
	
	public AudioTrack getAudioTrack();
	
	public ImageIcon getItemIcon();
}
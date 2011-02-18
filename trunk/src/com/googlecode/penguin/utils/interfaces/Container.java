package com.googlecode.penguin.utils.interfaces;

import javax.swing.ImageIcon;

public interface Container {
	
	public String getChildCount();
	
	public String getID();
	
	public String getParentID();
	
	public String getTitle();
	
	public String getModificationTime();
	
	public String getUpnpClass();
	
	public boolean isContainer();
	
	public ImageIcon getContainerIcon();	
}
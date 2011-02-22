package com.googlecode.penguin.types;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;

public class Folder extends JLabel {
	private static final long serialVersionUID = 986907859435731324L;
	private Map<String, Map<String, String>> folder;
	private String parentID, folderName;
	
	public Folder() {
		super();
		folder = new HashMap<String, Map<String, String>>();
	}
	
	public void putFolderInfo(String key, Map<String, String> value) {
		folder.put(key, value);
	}
	
	public Map<String, String> getFolderInfo(String key) {
		return folder.get(key);
	}
	
	public void setFolderParentID(String parentID) {
		this.parentID = parentID;
	}
	
	public String getFolderParentID() {
		return this.parentID;
	}
	
	public void setFolderParentName(String folderName) {
		this.folderName = folderName;
	}
	
	public String getFolderParentName() {
		return this.folderName;
	}	
}

package com.googlecode.penguin.listeners;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import com.googlecode.penguin.ServerFinder;
import com.googlecode.penguin.devices.MediaServer;
import com.googlecode.penguin.panels.MediaServerPanel;
import com.googlecode.penguin.services.ContentDirectory;
import com.googlecode.penguin.types.DIDL;
import com.googlecode.penguin.types.DIDLNode;
import com.googlecode.penguin.types.Folder;
import com.googlecode.penguin.utils.ActionException;
import com.googlecode.penguin.utils.ServiceException;

public class ContentListListener implements ListSelectionListener {
	private JList mediaServerContentList;
	private DefaultListModel mediaServerContentModel;
	private JButton playButton;
	private Folder folder;
	private static int selectedItemIndex;
	
	public ContentListListener(MediaServerPanel mediaServerPanel) {		
		this.mediaServerContentList = mediaServerPanel.mediaServerContentList;
		this.mediaServerContentModel = mediaServerPanel.mediaServerContentModel;
		this.playButton = mediaServerPanel.playButton;
		this.folder = mediaServerPanel.folder;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == false) {
			int index = mediaServerContentList.getSelectedIndex();
			
			try {
				if (index != -1) {
					int mediaServerIndex = ServerFinder.getMediaServerSelectedIndex();				
					
					if (DIDL.getDIDLNode(index).isContainer()) {
						mediaServerContentModel.clear();
						MediaServer mediaServer = ServerFinder.getMediaServer(mediaServerIndex);
						ContentDirectory contentDir = new ContentDirectory(mediaServer);
						
						String id = DIDL.getDIDLNode(index).getID();
						String parentID = DIDL.getDIDLNode(index).getParentID();
						String parentName = folder.getFolderParentName();
						
						Map<String, String> folderInfo = new HashMap<String, String>();
						folderInfo.put("parentID", parentID);
						folderInfo.put("parentName", parentName);
						folder.putFolderInfo(id, folderInfo);
						
						String folderTitle = DIDL.getDIDLNode(index).getTitle();						
						folder.setText(folderTitle);
						
						folder.setFolderParentID(id);
						folder.setFolderParentName(folderTitle);
						
						String result = contentDir.browse(id);				
						DIDL didl = new DIDL(result);
						
						List<DIDLNode> didlNodeList = didl.getDIDLNodeList();
						
						for (int i=0; i<didlNodeList.size(); i++) {
							DIDLNode didlNode = didlNodeList.get(i);
							mediaServerContentModel.addElement(didlNode);
						}
						
					} else if (DIDL.getDIDLNode(index).isItem()) {
						setSelectedItemIndex(index);
						playButton.setEnabled(true);
					}									
				}
				
			} catch (ActionException ex) {
				JOptionPane.showMessageDialog(null, "Imposible to establish connection", "Error", JOptionPane.ERROR_MESSAGE);
				System.out.println(ex.getMessage());
			} catch (ServiceException ex) {
				JOptionPane.showMessageDialog(null, "Imposible to obtain service", "Error", JOptionPane.ERROR_MESSAGE);
				System.out.println(ex.getMessage());
			}
		}
	}
	
	private void setSelectedItemIndex(int index) {
		selectedItemIndex = index;
	}
	
	public static int getSelectedItemIndex () {
		return selectedItemIndex;
	}	
}

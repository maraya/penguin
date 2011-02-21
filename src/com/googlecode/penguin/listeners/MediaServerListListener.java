package com.googlecode.penguin.listeners;

import java.awt.Cursor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
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

public class MediaServerListListener implements ListSelectionListener {
	private JList mediaServerList;
	private DefaultListModel mediaServerContentModel;
	private Folder folder;
	
	public MediaServerListListener(MediaServerPanel mediaServerPanel) {
		this.mediaServerList = mediaServerPanel.mediaServerList;
		this.mediaServerContentModel = mediaServerPanel.mediaServerContentModel;
		this.folder = mediaServerPanel.folder;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == false) {
			mediaServerContentModel.clear();
			int index = mediaServerList.getSelectedIndex();
			MediaServer mediaServer = ServerFinder.getMediaServer(index);			
			ServerFinder.setMediaServerSelectedIndex(index);			
			String friendlyName = mediaServer.getFriendlyName();
			String parentID = "0";
			 
			try {
				ContentDirectory contentDir = new ContentDirectory(mediaServer);
				folder.setCursor(new Cursor(Cursor.HAND_CURSOR));
				folder.setText(friendlyName);
				folder.setFolderParentID(parentID);
				folder.setFolderParentName(friendlyName);
								
				Map<String, String> folderInfo = new HashMap<String, String>();
				folderInfo.put("parentID", parentID);
				folderInfo.put("parentName", friendlyName);
				folder.putFolderInfo(parentID, folderInfo);
				
				String result = contentDir.browse("0");
				DIDL didl = new DIDL(result);
				List<DIDLNode> didlNodeList = didl.getDIDLNodeList();
				
				for (int i=0; i<didlNodeList.size(); i++) {
					DIDLNode didlNode = didlNodeList.get(i);
					mediaServerContentModel.addElement(didlNode);
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
}

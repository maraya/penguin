package com.googlecode.penguin.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import com.googlecode.penguin.ServerFinder;
import com.googlecode.penguin.devices.MediaServer;
import com.googlecode.penguin.panels.MediaServerPanel;
import com.googlecode.penguin.services.ContentDirectory;
import com.googlecode.penguin.types.DIDL;
import com.googlecode.penguin.types.DIDLNode;
import com.googlecode.penguin.types.Folder;
import com.googlecode.penguin.utils.ActionException;
import com.googlecode.penguin.utils.ServiceException;

public class FolderListener implements MouseListener {
	private Folder folder;
	private DefaultListModel mediaServerContentModel;
	
	public FolderListener (MediaServerPanel mediaServerPanel) {
		this.folder = mediaServerPanel.folder;
		this.mediaServerContentModel = mediaServerPanel.mediaServerContentModel;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			mediaServerContentModel.clear();
			int mediaServerIndex = ServerFinder.getMediaServerSelectedIndex();
			MediaServer mediaServer = ServerFinder.getMediaServer(mediaServerIndex);
			ContentDirectory contentDir = new ContentDirectory(mediaServer);
			
			String parentID = folder.getFolderParentID();				
			Map<String, String> folderInfo = folder.getFolderInfo(parentID);
			String parentName = folderInfo.get("parentName");
			String id = folderInfo.get("parentID");
			
			folder.setText(parentName);
			folder.setFolderParentName(parentName);
			folder.setFolderParentID(id);
						
			String result = contentDir.browse(id);			
			DIDL didl = new DIDL(result);
			
			List<DIDLNode> didlNodeList = didl.getDIDLNodeList();
			
			for (int i=0; i<didlNodeList.size(); i++) {
				DIDLNode didlNode = didlNodeList.get(i);
				mediaServerContentModel.addElement(didlNode);
			}
		
		} catch (ServiceException ex) {
			JOptionPane.showMessageDialog(null, "Imposible to establish connection", "Error", JOptionPane.ERROR_MESSAGE);
			System.out.println(ex.getMessage());
		} catch (ActionException ex) {
			JOptionPane.showMessageDialog(null, "Imposible to obtain service", "Error", JOptionPane.ERROR_MESSAGE);
			System.out.println(ex.getMessage());
		}
	}
	
	@Override public void mousePressed(MouseEvent e) {}
	@Override public void mouseReleased(MouseEvent e) {}
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}
}

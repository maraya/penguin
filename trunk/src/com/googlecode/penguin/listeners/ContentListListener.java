package com.googlecode.penguin.listeners;

import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import com.googlecode.penguin.MediaServer;
import com.googlecode.penguin.ServerFinder;
import com.googlecode.penguin.panels.MediaServerPanel;
import com.googlecode.penguin.services.ContentDirectory;
import com.googlecode.penguin.utils.ActionException;
import com.googlecode.penguin.utils.DIDL;
import com.googlecode.penguin.utils.DIDLNode;
import com.googlecode.penguin.utils.ServiceException;

public class ContentListListener implements ListSelectionListener {
	private JList mediaServerContentList;
	private DefaultListModel mediaServerContentModel;
	private JButton playButton;
	private static int selectedItemIndex;
	
	public ContentListListener(MediaServerPanel mediaServerPanel) {		
		this.mediaServerContentList = mediaServerPanel.mediaServerContentList;
		this.mediaServerContentModel = mediaServerPanel.mediaServerContentModel;
		this.playButton = mediaServerPanel.playButton;
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
				System.out.println(ex.getMessage());
				ex.printStackTrace();
			} catch (ServiceException ex) {
				System.out.println(ex.getMessage());
				ex.printStackTrace();
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

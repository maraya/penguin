package com.googlecode.penguin.listeners;

import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import com.googlecode.penguin.MediaServer;
import com.googlecode.penguin.ServerFinder;
import com.googlecode.penguin.services.ContentDirectory;
import com.googlecode.penguin.utils.ActionException;
import com.googlecode.penguin.utils.Container;
import com.googlecode.penguin.utils.DIDL;
import com.googlecode.penguin.utils.Item;
import com.googlecode.penguin.utils.ServiceException;

public class ContentListListener implements ListSelectionListener {
	private JList mediaServerContentList;
	private DefaultListModel mediaServerContentModel;
	
	public ContentListListener(JList mediaServerContentList, DefaultListModel mediaServerContentModel) {
		this.mediaServerContentList = mediaServerContentList;
		this.mediaServerContentModel = mediaServerContentModel;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == false) {
			int index = mediaServerContentList.getSelectedIndex();
						
			try {				
				int mediaServerIndex = ServerFinder.getMediaServerSelectedIndex();				
				MediaServer mediaServer = ServerFinder.getMediaServer(mediaServerIndex);				
				ContentDirectory contentDir = new ContentDirectory(mediaServer);
				
				String id = DIDL.getContainer(index).getID();
				
				//System.out.println(" ID ----------------> " + id);
				
				String result = contentDir.browse(id);				
				DIDL didl = new DIDL(result);
				
				List<Container> containerList = didl.getContainerList();
				List<Item> itemList = didl.getItemList();
				//mediaServerContentModel.clear();				
				
				for (int i=0; i<containerList.size(); i++) {
					Container container = containerList.get(i);
					//System.out.println(container.getTitle());
					
					mediaServerContentModel.addElement(container);
				}
				
				for (int i=0; i<itemList.size(); i++) {
					Item item = itemList.get(i);
					//System.out.println(item.getTitle());
					
					mediaServerContentModel.addElement(item);
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
}

package com.googlecode.penguin.listeners;

import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
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

public class ServerListListener implements ListSelectionListener {
	private JList mediaServerList;
	private DefaultListModel mediaServerContentModel;
	
	public ServerListListener(JList mediaServerList, DefaultListModel mediaServerContentModel) {
		this.mediaServerList = mediaServerList;
		this.mediaServerContentModel = mediaServerContentModel;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == false) {
			mediaServerContentModel.clear();
			int index = mediaServerList.getSelectedIndex();
			MediaServer mediaServer = ServerFinder.getMediaServer(index);
						
			ServerFinder.setMediaServerSelectedIndex(index);
			ContentDirectory contentDir;
			try {
				contentDir = new ContentDirectory(mediaServer);
			
			
				String result = contentDir.browse("0");
				DIDL didl = new DIDL(result);
				
				List<Container> containerList = didl.getContainerList();
				List<Item> itemList = didl.getItemList();
								
				for (int i=0; i<containerList.size(); i++) {
					Container container = containerList.get(i);
					mediaServerContentModel.addElement(container);
				}
				
				for (int i=0; i<itemList.size(); i++) {
					Item item = itemList.get(i);
					mediaServerContentModel.addElement(item);
					
					/*
					System.out.println(item.getTitle());
					System.out.println(item.getAddedTime());
					System.out.println(item.getAlbum());
					System.out.println(item.getAlbumArtURI());
					System.out.println(item.getArtist());
					System.out.println(item.getCreator());
					System.out.println(item.getGenre());
					System.out.println(item.getID());
					System.out.println(item.getLastPlayedTime());
					System.out.println(item.getLastUpdated());
					System.out.println(item.getModificationTime());
					System.out.println(item.getParentID());
					System.out.println(item.getPlayCount());
					System.out.println(item.getUpnpClass());
					
					AudioTrack audio = item.getAudioTrack();
					
					System.out.println("----> " + audio.getDuration());
					System.out.println("----> " + audio.getProtocolInfo());
					System.out.println("----> " + audio.getSize());
					System.out.println("----> " + audio.getTrackURI());
					
					
					System.out.println("---------------------------");
					*/
				}
			
			} catch (ActionException ex) {
				JOptionPane.showMessageDialog(null, "Imposible establecer conexión", "Error", JOptionPane.ERROR_MESSAGE);
				System.out.println(ex.getMessage());
			} catch (ServiceException ex) {
				JOptionPane.showMessageDialog(null, "Imposible obtener el servicio", "Error", JOptionPane.ERROR_MESSAGE);
				System.out.println(ex.getMessage());
			}
		}		
	}
}

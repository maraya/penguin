package com.googlecode.penguin.panels;

import java.awt.Color;
import java.awt.Cursor;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import com.googlecode.penguin.MediaServer;
import com.googlecode.penguin.ServerFinder;
import com.googlecode.penguin.renders.MediaServerIconRender;
import com.googlecode.penguin.services.ContentDirectory;
import com.googlecode.penguin.utils.ActionException;
import com.googlecode.penguin.utils.AudioTrack;
import com.googlecode.penguin.utils.Container;
import com.googlecode.penguin.utils.DIDL;
import com.googlecode.penguin.utils.Item;

public class MediaServerPanel extends JPanel implements ListSelectionListener {
	private static final long serialVersionUID = 3489153513717542820L;
	private JList mediaServerList;
	public DefaultListModel mediaServerModel, mediaServerContentModel;
	
	public MediaServerPanel () {
		setBackground(new Color(255, 255, 255));
		initComponents();
	}
	
	private void initComponents() {
		JScrollPane mediaServerScroll = new JScrollPane();
		JScrollPane mediaServerContentScroll = new JScrollPane();
		
		mediaServerModel = new DefaultListModel();
		mediaServerContentModel = new DefaultListModel();
		
		mediaServerList = new JList(mediaServerModel);
		mediaServerList.setCellRenderer(new MediaServerIconRender());
		mediaServerList.addListSelectionListener(this);
		mediaServerList.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		JList mediaServerContentList = new JList(mediaServerContentModel);
		
		mediaServerScroll.setViewportView(mediaServerList);
		mediaServerContentScroll.setViewportView(mediaServerContentList);

        GroupLayout layout = new GroupLayout(this);        
        setLayout(layout);
        
        layout.setHorizontalGroup(
        		layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(mediaServerScroll, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                    .addComponent(mediaServerContentScroll, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
                .addContainerGap())
        );
        
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mediaServerScroll, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(mediaServerContentScroll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(88, Short.MAX_VALUE))
        );
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == false) {
			int index = mediaServerList.getSelectedIndex();
			MediaServer mediaServer = ServerFinder.getMediaServer(index);
			
			ContentDirectory contentDir = new ContentDirectory(mediaServer);
			
			try {
				DIDL didl = contentDir.browse();
				List<Container> containerList = didl.getContainerList();
				List<Item> itemList = didl.getItemList();
				
				for (int i=0; i<containerList.size(); i++) {
					Container container = containerList.get(i);
					System.out.println(container.getTitle());
				}
				
				for (int i=0; i<itemList.size(); i++) {
					Item item = itemList.get(i);
					
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
				}
				
			} catch (ActionException ex) {
				System.out.println(ex.getMessage());
			}
			
			//mediaServerContentModel.addElement("asd");
			
			
			
		}
	}
}
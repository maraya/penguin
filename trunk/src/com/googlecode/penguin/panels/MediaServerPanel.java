package com.googlecode.penguin.panels;

import java.awt.Color;
import java.awt.Cursor;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout.Alignment;
import com.googlecode.penguin.listeners.ContentListListener;
import com.googlecode.penguin.listeners.ServerListListener;
import com.googlecode.penguin.renders.MediaContentRender;
import com.googlecode.penguin.renders.MediaServerIconRender;

public class MediaServerPanel extends JPanel {
	private static final long serialVersionUID = 3489153513717542820L;	
	public DefaultListModel mediaServerModel;
	
	public MediaServerPanel () {
		setBackground(new Color(255, 255, 255));
		initComponents();
	}
	
	private void initComponents() {
		JScrollPane mediaServerScroll = new JScrollPane();
		JScrollPane mediaServerContentScroll = new JScrollPane();
		
		mediaServerModel = new DefaultListModel();
		DefaultListModel mediaServerContentModel = new DefaultListModel();
		
		JList mediaServerList = new JList(mediaServerModel);
		mediaServerList.setCellRenderer(new MediaServerIconRender());
		mediaServerList.addListSelectionListener(new ServerListListener(mediaServerList, mediaServerContentModel));
		mediaServerList.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		JList mediaServerContentList = new JList(mediaServerContentModel);
		mediaServerContentList.addListSelectionListener(new ContentListListener(mediaServerContentList, mediaServerContentModel));
		mediaServerContentList.setCellRenderer(new MediaContentRender());
		
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
}
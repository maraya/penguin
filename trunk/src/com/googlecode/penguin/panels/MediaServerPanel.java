package com.googlecode.penguin.panels;

import java.awt.Color;
import java.awt.Cursor;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSlider;
import javax.swing.LayoutStyle.ComponentPlacement;
import com.googlecode.penguin.events.Pause;
import com.googlecode.penguin.events.Play;
import com.googlecode.penguin.events.Stop;
import com.googlecode.penguin.listeners.ContentListListener;
import com.googlecode.penguin.listeners.FolderListener;
import com.googlecode.penguin.listeners.MediaServerListListener;
import com.googlecode.penguin.listeners.VolumeListener;
import com.googlecode.penguin.renders.MediaContentRender;
import com.googlecode.penguin.renders.MediaServerIconRender;
import com.googlecode.penguin.types.Folder;

public class MediaServerPanel extends JPanel {
	private static final long serialVersionUID = 3489153513717542820L;	
	public DefaultListModel mediaServerModel;
	public JButton playButton, stopButton, pauseButton;
	public DefaultListModel mediaServerContentModel;
	public JList mediaServerContentList, mediaServerList;
	public JSlider volumeSlider;
	public Folder folder;
		
	public MediaServerPanel () {
		setBackground(new Color(255, 255, 255));
		initComponents();
	}
	
	private void initComponents() {
		playButton = new JButton("Play");
		stopButton = new JButton("Stop");
		pauseButton = new JButton("Pause");		
		folder = new Folder();
		JLabel volumeLabel = new JLabel("Volume");
		
		volumeSlider = new JSlider();
		volumeSlider.setValue(100);
		volumeSlider.addChangeListener(new VolumeListener());
		
		JScrollPane mediaServerScroll = new JScrollPane();
		JScrollPane mediaServerContentScroll = new JScrollPane();
		
		mediaServerModel = new DefaultListModel();
		mediaServerContentModel = new DefaultListModel();
		
		mediaServerList = new JList(mediaServerModel);
		mediaServerList.setCellRenderer(new MediaServerIconRender());
		mediaServerList.addListSelectionListener(new MediaServerListListener(this));
		mediaServerList.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		mediaServerContentList = new JList(mediaServerContentModel);
		mediaServerContentList.addListSelectionListener(new ContentListListener(this));
		mediaServerContentList.setCellRenderer(new MediaContentRender());
		mediaServerContentList.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		mediaServerScroll.setViewportView(mediaServerList);
		mediaServerContentScroll.setViewportView(mediaServerContentList);

		playButton.setEnabled(false);
		stopButton.setEnabled(false);
		pauseButton.setEnabled(false);
		volumeSlider.setEnabled(false);
		
		playButton.addActionListener(new Play(this));
		stopButton.addActionListener(new Stop());
		pauseButton.addActionListener(new Pause());
		folder.addMouseListener(new FolderListener(this));
		
		GroupLayout layout = new GroupLayout(this);
	    layout.setHorizontalGroup(
	            layout.createParallelGroup(Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(layout.createParallelGroup(Alignment.LEADING)
	                    .addComponent(mediaServerScroll, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
	                    .addComponent(mediaServerContentScroll, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
	                    .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
	                        .addComponent(playButton)
	                        .addPreferredGap(ComponentPlacement.UNRELATED)
	                        .addComponent(stopButton)
	                        .addPreferredGap(ComponentPlacement.UNRELATED)
	                        .addComponent(pauseButton)
	                        .addGap(31, 31, 31)
	                        .addComponent(volumeLabel)
	                        .addPreferredGap(ComponentPlacement.RELATED)
	                        .addComponent(volumeSlider, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
	                        .addGap(25, 25, 25))
	                    .addComponent(folder))
	                .addContainerGap())
	    );
	    
	    layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(mediaServerScroll, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
	                .addComponent(folder)
	                .addPreferredGap(ComponentPlacement.RELATED)
	                .addComponent(mediaServerContentScroll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(Alignment.LEADING)
	                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
	                        .addComponent(volumeLabel)
	                        .addComponent(playButton)
	                        .addComponent(stopButton)
	                        .addComponent(pauseButton))
	                    .addComponent(volumeSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	                .addGap(28, 28, 28))
	    );
	    
	    setLayout(layout);		
	}
}
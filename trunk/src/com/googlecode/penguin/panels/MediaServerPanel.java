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
import com.googlecode.penguin.listeners.ServerListListener;
import com.googlecode.penguin.renders.MediaContentRender;
import com.googlecode.penguin.renders.MediaServerIconRender;

public class MediaServerPanel extends JPanel {
	private static final long serialVersionUID = 3489153513717542820L;	
	public DefaultListModel mediaServerModel;
	public JButton playButton, stopButton, pauseButton;
	public DefaultListModel mediaServerContentModel;
	public JList mediaServerContentList;
	
	public MediaServerPanel () {
		setBackground(new Color(255, 255, 255));
		initComponents();
	}
	
	private void initComponents() {
		playButton = new JButton("Play");
		stopButton = new JButton("Stop");
		pauseButton = new JButton("Pause");
		JLabel volumeLabel = new JLabel("Volume");
		JSlider volumeSlider = new JSlider(); 
		
		JScrollPane mediaServerScroll = new JScrollPane();
		JScrollPane mediaServerContentScroll = new JScrollPane();
		
		mediaServerModel = new DefaultListModel();
		mediaServerContentModel = new DefaultListModel();
		
		JList mediaServerList = new JList(mediaServerModel);
		mediaServerList.setCellRenderer(new MediaServerIconRender());
		mediaServerList.addListSelectionListener(new ServerListListener(mediaServerList, mediaServerContentModel));
		mediaServerList.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		mediaServerContentList = new JList(mediaServerContentModel);
		mediaServerContentList.addListSelectionListener(new ContentListListener(this));
		mediaServerContentList.setCellRenderer(new MediaContentRender());
		
		mediaServerScroll.setViewportView(mediaServerList);
		mediaServerContentScroll.setViewportView(mediaServerContentList);

		playButton.setEnabled(false);
		stopButton.setEnabled(false);
		pauseButton.setEnabled(false);
		
		playButton.addActionListener(new Play(this));
		stopButton.addActionListener(new Stop());
		pauseButton.addActionListener(new Pause());
		
		GroupLayout layout = new GroupLayout(this);        
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(Alignment.LEADING)
                            .addComponent(mediaServerScroll, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                            .addComponent(mediaServerContentScroll, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(playButton)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(stopButton)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(pauseButton)
                        .addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addComponent(volumeLabel)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(volumeSlider, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mediaServerScroll, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(mediaServerContentScroll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(playButton)
                        .addComponent(stopButton)
                        .addComponent(pauseButton)
                        .addComponent(volumeLabel))
                    .addComponent(volumeSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        setLayout(layout);		
	}
}
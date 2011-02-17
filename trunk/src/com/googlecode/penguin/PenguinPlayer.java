package com.googlecode.penguin;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import com.googlecode.penguin.panels.MediaRenderPanel;
import com.googlecode.penguin.panels.MediaServerPanel;

public class PenguinPlayer extends JFrame {
	private static final long serialVersionUID = 924017654194370291L;
	
	public PenguinPlayer() {		
		setTitle("Penguin Player");	
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);		
		setIconImage(new ImageIcon(PenguinPlayer.class.getResource("resources/penguin_icon.png")).getImage());
		initComponents();			
	}
	
	private void initComponents() {
		JPanel mainPanel = new JPanel();
		JTabbedPane tabs = new JTabbedPane();
		mainPanel.setBackground(new Color(255, 255, 255));
		
        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
        	mainPanelLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(tabs, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
        	mainPanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(tabs, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, 351, GroupLayout.PREFERRED_SIZE)
        );
                
        pack();
        
        MediaServerPanel mediaServerPanel = new MediaServerPanel();
        MediaRenderPanel mediaRenderPanel = new MediaRenderPanel();
        
        tabs.addTab("MediaServers", mediaServerPanel);
        tabs.addTab("MediaRenderers", mediaRenderPanel);
        
        new ServerFinder(mediaServerPanel, mediaRenderPanel);
	}
	
	public static void main (String args[]) {
		EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PenguinPlayer().setVisible(true);
            }
        });
	}
}

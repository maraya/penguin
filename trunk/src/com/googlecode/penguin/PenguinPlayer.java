package com.googlecode.penguin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import com.googlecode.penguin.dialogs.NewMediaRenderDialog;
import com.googlecode.penguin.dialogs.NewMediaServerDialog;
import com.googlecode.penguin.panels.MediaRenderPanel;
import com.googlecode.penguin.panels.MediaServerPanel;

public class PenguinPlayer extends JFrame {
	private static final long serialVersionUID = 924017654194370291L;
	
	public PenguinPlayer() {		
		setTitle("Penguin");	
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);			
		setIconImage(PenguinConstants.getPenguinIcon());
		initComponents();			
	}
	
	private void initComponents() {
		JPanel mainPanel = new JPanel();
		JTabbedPane tabs = new JTabbedPane();		
		JMenuBar menuBar = new JMenuBar();		
		JMenu mainMenu = new JMenu("File");
		
		MediaServerPanel mediaServerPanel = new MediaServerPanel();
        MediaRenderPanel mediaRenderPanel = new MediaRenderPanel();
		menuBar.add(mainMenu);
		
		JMenuItem newMediaServer = new JMenuItem(new NewMediaServerDialog(mediaServerPanel, this));
		newMediaServer.setText("New MediaServer from location");
		newMediaServer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mainMenu.add(newMediaServer);
		
		JMenuItem newMediaRender = new JMenuItem(new NewMediaRenderDialog(mediaRenderPanel, this));
		newMediaRender.setText("New MediaRender from location");
		newMediaRender.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		mainMenu.add(newMediaRender);

        setJMenuBar(menuBar);
		mainPanel.setBackground(new Color(255, 255, 255));
		
        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);        
        mainPanelLayout.setHorizontalGroup(
        	mainPanelLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(tabs, GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
        	mainPanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(tabs, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanel.setLayout(mainPanelLayout);
        
        GroupLayout layout = new GroupLayout(getContentPane());        
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );        
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, 351, GroupLayout.PREFERRED_SIZE)
        );        
        getContentPane().setLayout(layout);
               
        pack();
        
        tabs.setBackground(new Color(255, 255, 255));
        tabs.addTab("MediaServers", mediaServerPanel);
        tabs.addTab("MediaRenderers", mediaRenderPanel);
        
        new ServerFinder(mediaServerPanel, mediaRenderPanel);
	}
	
	public static void main (String args[]) {
		EventQueue.invokeLater(new Runnable() {
            public void run() {
            	new PenguinConstants();
                new PenguinPlayer().setVisible(true);
            }
        });
	}
}

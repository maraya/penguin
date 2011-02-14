package com.googlecode.penguin;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

public class PenguinPlayer extends JFrame {
	private static final long serialVersionUID = 924017654194370291L;
	
	public PenguinPlayer() {		
		setTitle("pruebaaaa!");	
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);		
		setIconImage(new ImageIcon(PenguinPlayer.class.getResource("resources/penguin_icon.png")).getImage());
		initComponents();			
	}
	
	private void initComponents() {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));	
		
		JScrollPane scroll = new JScrollPane();
		DefaultListModel mediaServerModel = new DefaultListModel();
		
		JList mediaServerList = new JList(mediaServerModel);
		mediaServerList.setCellRenderer(new MediaServerIconRender());
		mediaServerList.setCursor(new Cursor(Cursor.HAND_CURSOR));
        scroll.setViewportView(mediaServerList);

        GroupLayout layout = new GroupLayout(panel);
        
        panel.setLayout(layout);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scroll, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scroll, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(209, Short.MAX_VALUE))
        );

        GroupLayout layout2 = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout2);
        layout2.setHorizontalGroup(
            layout2.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );        
        layout2.setVerticalGroup(
        	layout2.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
        pack();
        
        new ServerFinder(mediaServerModel);
	}
	
	public static void main (String args[]) {
		EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PenguinPlayer().setVisible(true);
            }
        });
	}
}

package com.googlecode.penguin.panels;

import java.awt.Color;
import java.awt.Cursor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import com.googlecode.penguin.listeners.MediaRenderListListener;
import com.googlecode.penguin.renders.MediaRenderIconRender;

public class MediaRenderPanel extends JPanel {
	private static final long serialVersionUID = 6896573822180577413L;
	public JList mediaRenderList;
	public DefaultListModel mediaRenderModel;
	public static boolean isMediaRenderSelected;
	
	public MediaRenderPanel () {
		mediaRenderModel = new DefaultListModel();
		isMediaRenderSelected = false;
		setBackground(new Color(255, 255, 255));
		initComponents();
	}
	
	private void initComponents() {
		JScrollPane scroll = new JScrollPane();		
        mediaRenderList = new JList(mediaRenderModel);
        mediaRenderList.setCellRenderer(new MediaRenderIconRender());
        mediaRenderList.addListSelectionListener(new MediaRenderListListener(this));
        mediaRenderList.setCursor(new Cursor(Cursor.HAND_CURSOR));
        scroll.setBorder(null);
        scroll.setViewportView(mediaRenderList);

        GroupLayout layout = new GroupLayout(this);        
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addComponent(scroll, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addComponent(scroll, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );        
	}
	
	public static boolean isMediaRenderSelected () {
		return isMediaRenderSelected;
	}
}
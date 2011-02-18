package com.googlecode.penguin.panels;

import java.awt.Color;
import java.awt.Cursor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import com.googlecode.penguin.ServerFinder;
import com.googlecode.penguin.renders.MediaRenderIconRender;

public class MediaRenderPanel extends JPanel implements ListSelectionListener {
	private static final long serialVersionUID = 6896573822180577413L;
	private JList mediaRenderList;
	public DefaultListModel mediaRenderModel;
	
	public MediaRenderPanel () {
		mediaRenderModel = new DefaultListModel();
		setBackground(new Color(255, 255, 255));
		initComponents();
	}
	
	private void initComponents() {
		JScrollPane scroll = new JScrollPane();		
        mediaRenderList = new JList(mediaRenderModel);
        mediaRenderList.setCellRenderer(new MediaRenderIconRender());
        mediaRenderList.addListSelectionListener(this);
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

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == false) {
			int index = mediaRenderList.getSelectedIndex();
			ServerFinder.setMediaRenderSelectedIndex(index);			
		}
	}
}
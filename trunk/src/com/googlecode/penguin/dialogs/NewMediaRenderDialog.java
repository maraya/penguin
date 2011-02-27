package com.googlecode.penguin.dialogs;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import org.cybergarage.upnp.device.InvalidDescriptionException;
import com.googlecode.penguin.PenguinConstants;
import com.googlecode.penguin.PenguinPlayer;
import com.googlecode.penguin.ServerFinder;
import com.googlecode.penguin.devices.MediaRender;
import com.googlecode.penguin.panels.MediaRenderPanel;

public class NewMediaRenderDialog extends AbstractAction {
	private static final long serialVersionUID = -2303423729588952264L;
	private JDialog dialog;
	private JTextField urlField;
	private DefaultListModel mediaRenderModel; 
	private PenguinPlayer penguinPlayer;
		
	public NewMediaRenderDialog (MediaRenderPanel mediaRenderPanel, PenguinPlayer penguinPlayer) {
		mediaRenderModel = mediaRenderPanel.mediaRenderModel;
		this.penguinPlayer = penguinPlayer;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		initComponents();
	}
	
	private void initComponents () {
		dialog = new JDialog();
		dialog.setIconImage(PenguinConstants.getPenguinIcon());
		
		JPanel panel = new JPanel();
		JLabel urlLabel = new JLabel("URL:");		
		urlField = new JTextField();
		JButton submitButton = new JButton("Submit");
		
		GroupLayout layout = new GroupLayout(panel);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(urlLabel)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(urlField, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addComponent(submitButton)
                .addContainerGap(99, Short.MAX_VALUE))
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(urlField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(urlLabel)
                    .addComponent(submitButton))
                .addContainerGap(266, Short.MAX_VALUE))
        );
		
        panel.setBackground(new Color(255, 255, 255));	
        panel.setLayout(layout);
        
		dialog.setSize(340, 90);
		dialog.add(panel);
		dialog.setTitle("New MediaRender");
		
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String urlValue = urlField.getText();
				boolean canDialogClose = false;
				
				if (urlValue.equals("")) {
					JOptionPane.showMessageDialog(null, "URL should not be empty", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						URL url = new URL(urlValue);						
						MediaRender mediaRender = new MediaRender(url.toString());
						mediaRenderModel.addElement(mediaRender);
						ServerFinder.putMediaRenderList(mediaRender);
						canDialogClose = true;						
						
					} catch (MalformedURLException ex) {
						JOptionPane.showMessageDialog(null, "Invalid URL", "Error", JOptionPane.ERROR_MESSAGE);						
						System.out.println(ex.getMessage());
						
					} catch (InvalidDescriptionException ex) {						
						JOptionPane.showMessageDialog(null, "Invalid MediaRender description file", "Error", JOptionPane.ERROR_MESSAGE);				
						System.out.println(ex.getMessage());
						
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(null, "I/O Error", "Error", JOptionPane.ERROR_MESSAGE);						
						System.out.println(ex.getMessage());
					} finally {
						if (canDialogClose) {
							dialog.setVisible(false);
						}
					}
				}
			}			
		});
		
		dialog.setLocationRelativeTo(penguinPlayer);
		dialog.setResizable(false);		
		dialog.setVisible(true);		
	}	
}

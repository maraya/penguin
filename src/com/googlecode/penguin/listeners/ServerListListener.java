package com.googlecode.penguin.listeners;

import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import com.googlecode.penguin.MediaServer;
import com.googlecode.penguin.ServerFinder;
import com.googlecode.penguin.services.ContentDirectory;
import com.googlecode.penguin.utils.ActionException;
import com.googlecode.penguin.utils.DIDL;
import com.googlecode.penguin.utils.DIDLNode;
import com.googlecode.penguin.utils.ServiceException;

public class ServerListListener implements ListSelectionListener {
	private JList mediaServerList;
	private DefaultListModel mediaServerContentModel;
	
	public ServerListListener(JList mediaServerList, DefaultListModel mediaServerContentModel) {
		this.mediaServerList = mediaServerList;
		this.mediaServerContentModel = mediaServerContentModel;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == false) {
			mediaServerContentModel.clear();
			int index = mediaServerList.getSelectedIndex();
			MediaServer mediaServer = ServerFinder.getMediaServer(index);
			ServerFinder.setMediaServerSelectedIndex(index);
			 
			try {
				ContentDirectory contentDir = new ContentDirectory(mediaServer);
				
				String result = contentDir.browse("0");
				DIDL didl = new DIDL(result);
				List<DIDLNode> didlNodeList = didl.getDIDLNodeList();
				
				for (int i=0; i<didlNodeList.size(); i++) {
					DIDLNode didlNode = didlNodeList.get(i);
					mediaServerContentModel.addElement(didlNode);
				}
						
			} catch (ActionException ex) {
				JOptionPane.showMessageDialog(null, "Imposible establecer conexión", "Error", JOptionPane.ERROR_MESSAGE);
				System.out.println(ex.getMessage());
			} catch (ServiceException ex) {
				JOptionPane.showMessageDialog(null, "Imposible obtener el servicio", "Error", JOptionPane.ERROR_MESSAGE);
				System.out.println(ex.getMessage());
			}
		}		
	}
}

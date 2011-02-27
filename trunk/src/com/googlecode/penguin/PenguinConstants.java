package com.googlecode.penguin;

import java.awt.Image;
import javax.swing.ImageIcon;

public class PenguinConstants {
	private static Image penguinIcon;
	private static ImageIcon audioIcon, imageIcon, videoIcon, containerIcon;
	
	public PenguinConstants() {
		penguinIcon = new ImageIcon(PenguinConstants.class.getResource("resources/icon_audio.gif")).getImage();
		audioIcon = new ImageIcon(PenguinConstants.class.getResource("/com/googlecode/penguin/resources/icon_audio.gif"));
		imageIcon = new ImageIcon(PenguinConstants.class.getResource("/com/googlecode/penguin/resources/icon_image.gif"));
		videoIcon = new ImageIcon(PenguinConstants.class.getResource("/com/googlecode/penguin/resources/icon_video.gif"));		
		containerIcon = new ImageIcon(PenguinConstants.class.getResource("/com/googlecode/penguin/resources/icon_folder.gif"));
	}
		
	public static Image getPenguinIcon () {
		return penguinIcon;
	}
	
	public static ImageIcon getAudioIcon () {
		return audioIcon;
	}
	
	public static ImageIcon getImageIcon () {
		return imageIcon;
	}	
	
	public static ImageIcon getVideoIcon () {
		return videoIcon;
	}
	
	public static ImageIcon getContainerIcon () {
		return containerIcon;
	}	
}

package gui;

import java.awt.HeadlessException;

import javax.swing.JFrame;

import gui.startpage.StartpagePanel;

public class ShelterFrame extends JFrame implements GUIConstants {
	
	public ShelterFrame() throws HeadlessException {
		super(APP_TITEL);

		setBounds(FRAME_BOUNDS);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		CaretakerPanel caretakerPanel = new CaretakerPanel();
		StartpagePanel startpagePanel = new StartpagePanel();
		add(startpagePanel);
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		new ShelterFrame();
	}

}

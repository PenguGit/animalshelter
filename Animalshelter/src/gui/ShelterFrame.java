package gui;

import java.awt.HeadlessException;

import javax.swing.JFrame;

public class ShelterFrame extends JFrame implements GUIConstants {
	
	public ShelterFrame() throws HeadlessException {
		super(APP_TITEL);

		setBounds(FRAME_BOUNDS);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ShelterPanel shelterPanel = new ShelterPanel();
		add(shelterPanel);
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		new ShelterFrame();
	}

}

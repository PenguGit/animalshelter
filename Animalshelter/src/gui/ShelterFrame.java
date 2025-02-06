package gui;

import java.awt.HeadlessException;

import javax.swing.JFrame;

import bl.DTOManager;
import gui.startpage.MainPanel;
import gui.startpage.StartpagePanel;

public class ShelterFrame extends JFrame implements GUIConstants {
	DTOManager dtoManager;
	
	public ShelterFrame() throws HeadlessException {
		super(APP_TITEL);
		dtoManager = new DTOManager();
		setBounds(FRAME_BOUNDS);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		CaretakerPanel caretakerPanel = new CaretakerPanel();
		StartpagePanel startpagePanel = new StartpagePanel();
		MainPanel mainPanel = new MainPanel();
		add(mainPanel);
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		new ShelterFrame();
	}
}

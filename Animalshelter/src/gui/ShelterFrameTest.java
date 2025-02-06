package gui;

import java.awt.HeadlessException;

import javax.swing.JFrame;

import bl.DTOManager;
import gui.animalview.AnimalViewPanel;
import gui.startpage.StartpagePanel;

public class ShelterFrameTest extends JFrame implements GUIConstants {
	
	DTOManager dtoManager;
	
	public ShelterFrameTest() throws HeadlessException {
		super(APP_TITEL);
		dtoManager = new DTOManager();
		setBounds(FRAME_BOUNDS);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		CaretakerPanel caretakerPanel = new CaretakerPanel();
		StartpagePanel startpagePanel = new StartpagePanel();
		ShelterImagePanel sip = new ShelterImagePanel(dtoManager.loadAnimalById(3).getImage());
		AnimalViewPanel avp = new AnimalViewPanel();
		
		add(avp);
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		new ShelterFrameTest();
	}

}

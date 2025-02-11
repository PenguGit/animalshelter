package gui;

import javax.swing.JButton;

import gui.util.GUIConstants;

public class ShelterButton extends JButton implements GUIConstants {

	public ShelterButton() {
		super();
		setFont(FONT_BUTTON);
	}
	
	public ShelterButton(String string) {
		this();
		setText(string);
	}
	
}

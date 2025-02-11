package gui;

import javax.swing.JRadioButton;

import gui.util.GUIConstants;

public class ShelterRadioButton extends JRadioButton implements GUIConstants {
	
	public ShelterRadioButton() {
		super();
		setFont(FONT_BUTTON);
	}

	public ShelterRadioButton(String string) {
		this();
		setText(string);
	}

}

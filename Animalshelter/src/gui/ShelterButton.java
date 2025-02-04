package gui;

import javax.swing.JButton;

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

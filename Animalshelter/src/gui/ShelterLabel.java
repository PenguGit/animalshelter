package gui;

import javax.swing.JLabel;

public class ShelterLabel extends JLabel implements GUIConstants {

	public ShelterLabel() {
		super();
		setFont(FONT_LABELS);
		setHorizontalAlignment(LEFT);
	}

	public ShelterLabel(String string) {
		this();
		this.setText(string);
	}
}

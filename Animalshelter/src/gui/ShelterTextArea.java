package gui;

import javax.swing.JTextArea;

public class ShelterTextArea extends JTextArea implements GUIConstants {
	
	/**
	 * Empties the Area
	 */
	public void reset() {
		setText("");
	}
	
	public ShelterTextArea(String string) {
		this();
		setFont(FONT_TEXTFIELD);
		setText(string);
	}
	
	public ShelterTextArea() {
		super();
		setFont(FONT_TEXTFIELD);
	}

	public ShelterTextArea(int i, int j) {
		super(i,j);
		setFont(FONT_TEXTFIELD);
	}
	
}

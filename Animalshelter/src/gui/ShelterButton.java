package gui;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

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

	public ShelterButton(Icon icon) {
		super(icon);
		setFont(FONT_BUTTON);
	}
	
	public ShelterButton(String string, Icon icon) {
		this(icon);
		setVerticalTextPosition(SwingConstants.BOTTOM);
        setHorizontalTextPosition(SwingConstants.CENTER);
		setText(string);
	}
}

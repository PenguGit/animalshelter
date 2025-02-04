package gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class ShelterTextField extends JTextField implements GUIConstants {
	
	public ShelterTextField(String string) {
		this();
		setText(string);
	}
	
	public ShelterTextField() {
		super();
		setFont(FONT_TEXTFIELD);
	}

	public ShelterTextField(int i) {
		super(i);
		setFont(FONT_TEXTFIELD);
	}

	public ShelterTextField(String string, int i) {
		super(string, i);
		setFont(FONT_TEXTFIELD);
	}
	
	/**
	 * Changes the textfield border to red if it is not valid/filled
	 * @param valid
	 */
	public void setFilled(boolean filled) {
        if (filled) {
            setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        } else {
            setBorder(BorderFactory.createLineBorder(Color.RED));
        }
    }
	/**
     * Checks if the text field is filled (e.g., not empty).
     * 
     * @return True if filled, false otherwise.
     */
	public boolean isFilled() {
        if (this.getText().isEmpty()) {
            setFilled(false);
            return false;
        } else {
            setFilled(true);
            return true;
        }
    }
}

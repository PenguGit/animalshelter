package gui;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class ShelterTextField extends JTextField implements GUIConstants {
	private final int MAX_LENGTH = 45;
	
	public ShelterTextField(String string) {
		super(string);
		setFont(FONT_TEXTFIELD);
		addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if(getText().length() >= MAX_LENGTH)
		            e.consume(); 
		    }  
		});
	}
	
	public ShelterTextField() {
		super();
		setFont(FONT_TEXTFIELD);
		addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if(getText().length() >= MAX_LENGTH)
		            e.consume(); 
		    }  
		});
	}

	public ShelterTextField(int i) {
		super(i);
		setFont(FONT_TEXTFIELD);
		addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if(getText().length() >= MAX_LENGTH)
		            e.consume(); 
		    }  
		});
	}

	public ShelterTextField(String string, int i) {
		super(string, i);
		setFont(FONT_TEXTFIELD);
		addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if(getText().length() >= MAX_LENGTH)
		            e.consume(); 
		    }  
		});
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

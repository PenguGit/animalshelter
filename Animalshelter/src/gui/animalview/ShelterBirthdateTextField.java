package gui.animalview;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.UIManager;

import gui.ShelterTextField;

public class ShelterBirthdateTextField extends ShelterTextField {

	
	public ShelterBirthdateTextField() {
		super(10);
        setCaretPosition(0); // Set cursor to start
        setPreferredSize(new Dimension(20, getPreferredSize().height));
        
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                int pos = getCaretPosition();

                if ((!Character.isDigit(c) && c != '\b') || getText().length() >= 10) {
                    e.consume(); 
                    return;
                }

                if ((pos == 2 || pos == 5) && c != '\b') {
                    setText(getText().substring(0, pos) + "." + getText().substring(pos));
                    setCaretPosition(pos + 1); 
                }

                if (pos == 10) {
                    e.consume(); 
                }
            }
        });
	}
	
	
	 /**
     * Retrieves the date value from the text field.
     *
     * @return The LocalDate represented in the text field, or null if parsing fails.
     */
	public LocalDate getDate() {
		try {
			String strDate = getText();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
			LocalDate locDat = LocalDate.parse(strDate, formatter);
			return locDat;
		} catch (DateTimeParseException e) {
			return null;
		}
	}
	
	/**
     * Sets the date value of the text field.
     *
     * @param locDate The LocalDate to set in the text field.
     */
	public void setDate(LocalDate locDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		setText(locDate.format(formatter));
	}
	
	
	 /**
     * Changes the status (editable and focusable) of the text field.
     * When enabled, the background is reset to the default TextField background.
     * When disabled, the background is set to gray.
     *
     * @param bool True to enable editing and focus, false to disable.
     */
	public void changeStatus(boolean bool) {
		setEditable(bool);
		setFocusable(bool);
		if (bool) {
			setBackground(UIManager.getColor("TextField.background"));
		} else {
			setBackground(Color.LIGHT_GRAY);
		}
	}
}

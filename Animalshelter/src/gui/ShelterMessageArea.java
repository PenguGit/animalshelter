package gui;

import java.awt.Color;

public class ShelterMessageArea extends ShelterTextArea {
	/**
	 * Shows Success
	 * @param msg
	 */
	public void showMessage(String msg) {
		setForeground(Color.GREEN);
		setText(msg);
	}
	
	/**
	 * Shows ErrorMessage
	 * @param err
	 */
	public void showError(String err) {
		setForeground(Color.RED);
		setText(err);
	}
}

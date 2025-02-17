package gui.startpage;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;

import gui.ShelterButton;

public class StartpagePanelButton extends ShelterButton {
	private String defaultText;
	
	/**
	 * Constructs a new button for the start page panel.
	 * @param text The text to display.
	 * @param defaultIcon The default icon to display.
	 * @param largeIcon The larger icon to display when the button is hovered over.
	 */
	public StartpagePanelButton(String text, Icon defaultIcon, Icon largeIcon) {
		super(text, defaultIcon);
		defaultText = text;
		
		addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		        setText("");
		        setIcon(largeIcon);
		    }

		    public void mouseExited(MouseEvent evt) {
		        setText(defaultText);
		        setIcon(defaultIcon);
		    }
		});
	}
}

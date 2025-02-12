package gui.startpage;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import gui.ShelterButton;

public class StartpagePanelButton extends ShelterButton {
	private String defaultText;
	private Icon defaultIcon;
	private Icon scaledIcon;
	
	public StartpagePanelButton(String text, Icon icon) {
		super(text, icon);
		defaultText = text;
		defaultIcon = icon;
		
		Image defaultImage = ((ImageIcon)icon).getImage();
		scaledIcon = new ImageIcon(defaultImage.getScaledInstance((int)(defaultImage.getWidth(null) * 0.75), (int)(defaultImage.getHeight(null) * 0.75), Image.SCALE_SMOOTH));
		setIcon(scaledIcon);
		
		addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		        setText("");
		        setIcon(defaultIcon);
		    }

		    public void mouseExited(MouseEvent evt) {
		        setText(defaultText);
		        setIcon(scaledIcon);
		    }
		});
	}
}

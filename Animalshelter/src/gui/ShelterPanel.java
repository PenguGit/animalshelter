package gui;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import gui.util.GUIConstants;


public class ShelterPanel extends JPanel implements GUIConstants {

	public ShelterPanel() {
		super();
		setBackground(COLOR_PANEL);
	}

	public ShelterPanel(FlowLayout flowLayout) {
		super(flowLayout);
	}
	
}
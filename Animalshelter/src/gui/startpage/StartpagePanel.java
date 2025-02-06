package gui.startpage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;

import gui.ShelterButton;
import gui.ShelterLabel;
import gui.ShelterPanel;

public class StartpagePanel extends ShelterPanel {
	public ShelterButton animalsButton;
	public ShelterButton roomsButton;
	public ShelterButton adoptionsButton;
	public ShelterButton patronsButton;
	public ShelterButton caretakersButton;
	public ShelterButton vetsButton;
	
	public StartpagePanel() {
		setLayout(new BorderLayout());
        
        ShelterPanel buttonPanel = new ShelterPanel();
        buttonPanel.setLayout(new GridBagLayout());  // 2x3 grid, spacing
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.insets = new java.awt.Insets(30, 30, 30, 30);
        
        int index = 1;
        animalsButton = new ShelterButton("Tiere");
        animalsButton.setPreferredSize(new Dimension(200, 200));
        buttonPanel.add(animalsButton, gbc);
        if (index++ % 3 == 0) {
            gbc.gridx = 0;
            gbc.gridy++;
        } else {
            gbc.gridx++;
        }
        
        roomsButton = new ShelterButton("Räume");
        roomsButton.setPreferredSize(new Dimension(200, 200));
        buttonPanel.add(roomsButton, gbc);
        if (index++ % 3 == 0) {
            gbc.gridx = 0;
            gbc.gridy++;
        } else {
            gbc.gridx++;
        }
        
        adoptionsButton = new ShelterButton("Adoption");
        adoptionsButton.setPreferredSize(new Dimension(200, 200));
        buttonPanel.add(adoptionsButton, gbc);
        if (index++ % 3 == 0) {
            gbc.gridx = 0;
            gbc.gridy++;
        } else {
            gbc.gridx++;
        }
        
        patronsButton = new ShelterButton("Paten");
        patronsButton.setPreferredSize(new Dimension(200, 200));
        buttonPanel.add(patronsButton, gbc);
        if (index++ % 3 == 0) {
            gbc.gridx = 0;
            gbc.gridy++;
        } else {
            gbc.gridx++;
        }
        
        caretakersButton = new ShelterButton("Pfleger");
        caretakersButton.setPreferredSize(new Dimension(200, 200));
        buttonPanel.add(caretakersButton, gbc);
        if (index++ % 3 == 0) {
            gbc.gridx = 0;
            gbc.gridy++;
        } else {
            gbc.gridx++;
        }
        
        vetsButton = new ShelterButton("Ärzte");
        vetsButton.setPreferredSize(new Dimension(200, 200));
        buttonPanel.add(vetsButton, gbc);
        if (index++ % 3 == 0) {
        	gbc.gridx = 0;
        	gbc.gridy++;
        } else {
        	gbc.gridx++;
        }
        
        add(buttonPanel, BorderLayout.CENTER);
	}
}

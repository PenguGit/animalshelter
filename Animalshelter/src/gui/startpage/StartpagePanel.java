package gui.startpage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;

import gui.ShelterPanel;
import gui.util.IconManager;
import gui.util.IconManager.Size;
import gui.util.IconManager.Type;

public class StartpagePanel extends ShelterPanel {
	public StartpagePanelButton animalsButton;
	public StartpagePanelButton roomsButton;
	public StartpagePanelButton adoptionsButton;
	public StartpagePanelButton patronsButton;
	public StartpagePanelButton caretakersButton;
	public StartpagePanelButton vetsButton;
	
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
        
        animalsButton = new StartpagePanelButton("Tiere", IconManager.getIcon(Type.ANIMAL, Size.DEFAULT), IconManager.getIcon(Type.ANIMAL, Size.LARGE));
        animalsButton.setPreferredSize(new Dimension(200, 200));
        animalsButton.setMinimumSize(new Dimension(200, 200));
        animalsButton.setBackground(new Color(144, 238, 144));
        animalsButton.setFocusable(false);
        buttonPanel.add(animalsButton, gbc);
        if (index++ % 3 == 0) {
            gbc.gridx = 0;
            gbc.gridy++;
        } else {
            gbc.gridx++;
        }
        
        roomsButton = new StartpagePanelButton("Räume", IconManager.getIcon(Type.ROOMS, Size.DEFAULT), IconManager.getIcon(Type.ROOMS, Size.LARGE));
        roomsButton.setPreferredSize(new Dimension(200, 200));
        roomsButton.setMinimumSize(new Dimension(200, 200));
        roomsButton.setBackground(new Color(216, 191, 216));
        roomsButton.setFocusable(false);
        buttonPanel.add(roomsButton, gbc);
        if (index++ % 3 == 0) {
            gbc.gridx = 0;
            gbc.gridy++;
        } else {
            gbc.gridx++;
        }
        
        adoptionsButton = new StartpagePanelButton("Adoption", IconManager.getIcon(Type.ADOPTION, Size.DEFAULT), IconManager.getIcon(Type.ADOPTION, Size.LARGE));
        adoptionsButton.setPreferredSize(new Dimension(200, 200));
        adoptionsButton.setMinimumSize(new Dimension(200, 200));
        adoptionsButton.setBackground(new Color(240, 128, 128)); 
        adoptionsButton.setFocusable(false);
        buttonPanel.add(adoptionsButton, gbc);
        if (index++ % 3 == 0) {
            gbc.gridx = 0;
            gbc.gridy++;
        } else {
            gbc.gridx++;
        }
        
        patronsButton = new StartpagePanelButton("Paten", IconManager.getIcon(Type.PATRON, Size.DEFAULT), IconManager.getIcon(Type.PATRON, Size.LARGE));
        patronsButton.setPreferredSize(new Dimension(200, 200));
        patronsButton.setMinimumSize(new Dimension(200, 200));
        patronsButton.setBackground(new Color(255, 182, 193)); 
        patronsButton.setFocusable(false);
        buttonPanel.add(patronsButton, gbc);
        if (index++ % 3 == 0) {
            gbc.gridx = 0;
            gbc.gridy++;
        } else {
            gbc.gridx++;
        }
        
        caretakersButton = new StartpagePanelButton("Pfleger", IconManager.getIcon(Type.CARETAKER, Size.DEFAULT), IconManager.getIcon(Type.CARETAKER, Size.LARGE));
        caretakersButton.setPreferredSize(new Dimension(200, 200));
        caretakersButton.setMinimumSize(new Dimension(200, 200));
        caretakersButton.setBackground(new Color(255, 204, 153)); 
        caretakersButton.setFocusable(false);
        buttonPanel.add(caretakersButton, gbc);
        if (index++ % 3 == 0) {
            gbc.gridx = 0;
            gbc.gridy++;
        } else {
            gbc.gridx++;
        }
        
        vetsButton = new StartpagePanelButton("Ärzte", IconManager.getIcon(Type.VET, Size.DEFAULT), IconManager.getIcon(Type.VET, Size.LARGE));
        vetsButton.setPreferredSize(new Dimension(200, 200));
        vetsButton.setMinimumSize(new Dimension(200, 200));
        vetsButton.setBackground(new Color(173, 216, 230)); 
        vetsButton.setFocusable(false);
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

package gui.startpage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import gui.ShelterPanel;

public class StartpagePanel extends ShelterPanel {
	public StartpagePanelButton animalsButton;
	public StartpagePanelButton roomsButton;
	public StartpagePanelButton adoptionsButton;
	public StartpagePanelButton patronsButton;
	public StartpagePanelButton caretakersButton;
	public StartpagePanelButton vetsButton;
	
	private static final Dimension DEFAULT_ICON_SIZE = new Dimension(92, 92);
	
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
        
        ImageIcon animalIcon = new ImageIcon("resources/animal.png");
        animalIcon = new ImageIcon(animalIcon.getImage().getScaledInstance(DEFAULT_ICON_SIZE.width, DEFAULT_ICON_SIZE.height, Image.SCALE_SMOOTH));
        animalsButton = new StartpagePanelButton("Tiere", animalIcon);
        animalsButton.setPreferredSize(new Dimension(200, 200));
        animalsButton.setBackground(new Color(144, 238, 144));
        animalsButton.setFocusable(false);
        buttonPanel.add(animalsButton, gbc);
        if (index++ % 3 == 0) {
            gbc.gridx = 0;
            gbc.gridy++;
        } else {
            gbc.gridx++;
        }
        
        ImageIcon roomsIcon = new ImageIcon("resources/rooms.png");
        roomsIcon = new ImageIcon(roomsIcon.getImage().getScaledInstance(DEFAULT_ICON_SIZE.width, DEFAULT_ICON_SIZE.height, Image.SCALE_SMOOTH));
        roomsButton = new StartpagePanelButton("Räume", roomsIcon);
        roomsButton.setPreferredSize(new Dimension(200, 200));
        roomsButton.setBackground(new Color(216, 191, 216));
        roomsButton.setFocusable(false);
        buttonPanel.add(roomsButton, gbc);
        if (index++ % 3 == 0) {
            gbc.gridx = 0;
            gbc.gridy++;
        } else {
            gbc.gridx++;
        }
        
        ImageIcon adoptionIcon = new ImageIcon("resources/adoption.png");
        adoptionIcon = new ImageIcon(adoptionIcon.getImage().getScaledInstance(DEFAULT_ICON_SIZE.width, DEFAULT_ICON_SIZE.height, Image.SCALE_SMOOTH));
        adoptionsButton = new StartpagePanelButton("Adoption", adoptionIcon);
        adoptionsButton.setPreferredSize(new Dimension(200, 200));
        adoptionsButton.setBackground(new Color(240, 128, 128)); 
        adoptionsButton.setFocusable(false);
        buttonPanel.add(adoptionsButton, gbc);
        if (index++ % 3 == 0) {
            gbc.gridx = 0;
            gbc.gridy++;
        } else {
            gbc.gridx++;
        }
        
        ImageIcon patronIcon = new ImageIcon("resources/patron.png");
        patronIcon = new ImageIcon(patronIcon.getImage().getScaledInstance(DEFAULT_ICON_SIZE.width, DEFAULT_ICON_SIZE.height, Image.SCALE_SMOOTH));
        patronsButton = new StartpagePanelButton("Paten", patronIcon);
        patronsButton.setPreferredSize(new Dimension(200, 200));
        patronsButton.setBackground(new Color(255, 182, 193)); 
        patronsButton.setFocusable(false);
        buttonPanel.add(patronsButton, gbc);
        if (index++ % 3 == 0) {
            gbc.gridx = 0;
            gbc.gridy++;
        } else {
            gbc.gridx++;
        }
        
        ImageIcon caretakerIcon = new ImageIcon("resources/caretaker.png");
        caretakerIcon = new ImageIcon(caretakerIcon.getImage().getScaledInstance(DEFAULT_ICON_SIZE.width, DEFAULT_ICON_SIZE.height, Image.SCALE_SMOOTH));
        caretakersButton = new StartpagePanelButton("Pfleger", caretakerIcon);
        caretakersButton.setPreferredSize(new Dimension(200, 200));
        caretakersButton.setBackground(new Color(255, 204, 153)); 
        caretakersButton.setFocusable(false);
        buttonPanel.add(caretakersButton, gbc);
        if (index++ % 3 == 0) {
            gbc.gridx = 0;
            gbc.gridy++;
        } else {
            gbc.gridx++;
        }
        
        
        ImageIcon vetIcon = new ImageIcon("resources/vet.png");
        vetIcon = new ImageIcon(vetIcon.getImage().getScaledInstance(DEFAULT_ICON_SIZE.width, DEFAULT_ICON_SIZE.height, Image.SCALE_SMOOTH));
        vetsButton = new StartpagePanelButton("Ärzte", vetIcon);
        vetsButton.setPreferredSize(new Dimension(200, 200));
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

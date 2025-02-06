package gui.startpage;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;

import gui.PlaceholderPanel;
import gui.ShelterPanel;

public class MainPanel extends ShelterPanel {
	CardLayout cardLayout;
	
	public MainPanel() {
		cardLayout = new CardLayout();
		setLayout(cardLayout);
		
        StartpagePanel startPagePanel = new StartpagePanel();
        
        startPagePanel.animalsButton.addActionListener((ActionEvent e) -> {
        	onNavigationButtonPressed(e, "animals");
        });
        
        startPagePanel.roomsButton.addActionListener((ActionEvent e) -> {
        	onNavigationButtonPressed(e, "rooms");
        });
        
        startPagePanel.adoptionsButton.addActionListener((ActionEvent e) -> {
        	onNavigationButtonPressed(e, "adoptions");
        });
        
        startPagePanel.patronsButton.addActionListener((ActionEvent e) -> {
        	onNavigationButtonPressed(e, "patrons");
        });
        
        startPagePanel.caretakersButton.addActionListener((ActionEvent e) -> {
        	onNavigationButtonPressed(e, "caretakers");
        });
        
        startPagePanel.vetsButton.addActionListener((ActionEvent e) -> {
        	onNavigationButtonPressed(e, "vets");
        });
        
        add(startPagePanel, "start");
        
        PlaceholderPanel animalsPlaceholderPanel = new PlaceholderPanel("Tiere");
        animalsPlaceholderPanel.backButton.addActionListener((ActionEvent e) -> {
        	onNavigationButtonPressed(e, "start");
        });
        add(animalsPlaceholderPanel, "animals");
        
        PlaceholderPanel roomsPlaceholderPanel = new PlaceholderPanel("Räume");
        roomsPlaceholderPanel.backButton.addActionListener((ActionEvent e) -> {
        	onNavigationButtonPressed(e, "start");
        });
        add(roomsPlaceholderPanel, "rooms");
        
        PlaceholderPanel adoptionsPlaceholderPanel = new PlaceholderPanel("Adoption");
        adoptionsPlaceholderPanel.backButton.addActionListener((ActionEvent e) -> {
        	onNavigationButtonPressed(e, "start");
        });
        add(adoptionsPlaceholderPanel, "adoptions");
        
        PlaceholderPanel patronsPlaceholderPanel = new PlaceholderPanel("Paten");
        patronsPlaceholderPanel.backButton.addActionListener((ActionEvent e) -> {
        	onNavigationButtonPressed(e, "start");
        });
        add(patronsPlaceholderPanel, "patrons");
        
        PlaceholderPanel caretakersPlaceholderPanel = new PlaceholderPanel("Pfleger");
        caretakersPlaceholderPanel.backButton.addActionListener((ActionEvent e) -> {
        	onNavigationButtonPressed(e, "start");
        });
        add(caretakersPlaceholderPanel, "caretakers");
        
        PlaceholderPanel vetsPlaceholderPanel = new PlaceholderPanel("Ärzte");
        vetsPlaceholderPanel.backButton.addActionListener((ActionEvent e) -> {
        	onNavigationButtonPressed(e, "start");
        });
        add(vetsPlaceholderPanel, "vets");
	}
	
	private void onNavigationButtonPressed(ActionEvent e, String name) {
		cardLayout.show(this, name);
	}
}

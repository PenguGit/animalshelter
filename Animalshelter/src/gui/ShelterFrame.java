package gui;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

import bl.DTOManager;
import gui.startpage.StartpagePanel;

public class ShelterFrame extends JFrame implements GUIConstants {
	DTOManager dtoManager;
	CardLayout cardLayout;
	
	ShelterPanel mainPanel;
	ShelterPanel topBarPanel;
	ShelterButton backButton;
	ShelterLabel titleLabel;
	ShelterPanel cardPanel;
	
	public ShelterFrame() throws HeadlessException {
		super(APP_TITEL);
		dtoManager = new DTOManager();
		
		setBounds(FRAME_BOUNDS);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPanel = new ShelterPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		add(mainPanel);
		
		topBarPanel = new ShelterPanel();
		topBarPanel.setLayout(new BoxLayout(topBarPanel, BoxLayout.X_AXIS));
		topBarPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
		mainPanel.add(topBarPanel);
		
		backButton = new ShelterButton("<");
		backButton.setFocusable(false);
		backButton.setVisible(false);
		backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		backButton.addActionListener((ActionEvent e) -> {
        	onNavigationButtonPressed(e, "start", GREETING);
        });
        topBarPanel.add(backButton);
		
        topBarPanel.add(Box.createRigidArea(new Dimension(16, 0)));
        
        titleLabel = new ShelterLabel(GREETING);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        topBarPanel.add(titleLabel);

        topBarPanel.add(Box.createHorizontalGlue());

		cardLayout = new CardLayout();
        
        cardPanel = new ShelterPanel();
		cardPanel.setLayout(cardLayout);
		
        StartpagePanel startPagePanel = new StartpagePanel();
        
        startPagePanel.animalsButton.addActionListener((ActionEvent e) -> {
        	onNavigationButtonPressed(e, "animals", "Tiere");
        });
        
        startPagePanel.roomsButton.addActionListener((ActionEvent e) -> {
        	onNavigationButtonPressed(e, "rooms", "Räume");
        });
        
        startPagePanel.adoptionsButton.addActionListener((ActionEvent e) -> {
        	onNavigationButtonPressed(e, "adoptions", "Adoption");
        });
        
        startPagePanel.patronsButton.addActionListener((ActionEvent e) -> {
        	onNavigationButtonPressed(e, "patrons", "Paten");
        });
        
        startPagePanel.caretakersButton.addActionListener((ActionEvent e) -> {
        	onNavigationButtonPressed(e, "caretakers", "Pfleger");
        });
        
        startPagePanel.vetsButton.addActionListener((ActionEvent e) -> {
        	onNavigationButtonPressed(e, "vets", "Ärzte");
        });
        
        cardPanel.add(startPagePanel, "start");
        
        PlaceholderPanel animalsPlaceholderPanel = new PlaceholderPanel("Tiere");
        cardPanel.add(animalsPlaceholderPanel, "animals");
        
        PlaceholderPanel roomsPlaceholderPanel = new PlaceholderPanel("Räume");
        cardPanel.add(roomsPlaceholderPanel, "rooms");
        
        PlaceholderPanel adoptionsPlaceholderPanel = new PlaceholderPanel("Adoption");
        cardPanel.add(adoptionsPlaceholderPanel, "adoptions");
        
        PlaceholderPanel patronsPlaceholderPanel = new PlaceholderPanel("Paten");
        cardPanel.add(patronsPlaceholderPanel, "patrons");
        
        CaretakerPanel caretakerPanel = new CaretakerPanel();
        cardPanel.add(caretakerPanel, "caretakers");
        
        PlaceholderPanel vetsPlaceholderPanel = new PlaceholderPanel("Ärzte");
        cardPanel.add(vetsPlaceholderPanel, "vets");
		
        mainPanel.add(cardPanel);
		
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		new ShelterFrame();
	}
	
	private void onNavigationButtonPressed(ActionEvent e, String name, String title) {
		backButton.setVisible(!name.equals("start"));
		titleLabel.setText(title);
		cardLayout.show(cardPanel, name);
	}
}

package gui;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

import bl.DTOManager;
import gui.adoptions.AdoptionsPanel;
import gui.animalview.AnimalViewPanel;
import gui.persons.CaretakerPanel;
import gui.persons.PatronPanel;
import gui.persons.VetPanel;
import gui.rooms.RoomsPanel;
import gui.startpage.StartpagePanel;
import gui.util.GUIConstants;
import gui.util.IconManager;
import gui.util.IconManager.Size;

public class ShelterFrame extends JFrame implements GUIConstants {
	DTOManager dtoManager;
	CardLayout cardLayout;

	AnimalViewPanel animalViewPanel;
	RoomsPanel roomsPanel;
	AdoptionsPanel adoptionsPanel;

	ShelterPanel mainPanel;
	ShelterPanel topBarPanel;
	ShelterButton backButton;
	ShelterLabel titleLabel;
	ShelterPanel cardPanel;
	private PatronPanel patronPanel;
	private CaretakerPanel caretakerPanel;
	private VetPanel vetPanel;

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
		backButton.addActionListener((ActionEvent _) -> {
			onNavigationButtonPressed("start", GREETING);
		});
		topBarPanel.add(backButton);

		topBarPanel.add(Box.createRigidArea(new Dimension(16, 0)));

		titleLabel = new ShelterLabel(GREETING, IconManager.getIcon(IconManager.Type.APP, Size.LARGE));
		titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		topBarPanel.add(titleLabel);

		topBarPanel.add(Box.createHorizontalGlue());

		cardLayout = new CardLayout();

		cardPanel = new ShelterPanel();
		cardPanel.setLayout(cardLayout);

		StartpagePanel startPagePanel = new StartpagePanel();

		startPagePanel.animalsButton.addActionListener((ActionEvent _) -> {
			onNavigationButtonPressed("animals", "Tiere");
		});

		startPagePanel.roomsButton.addActionListener((ActionEvent _) -> {
			onNavigationButtonPressed("rooms", "Räume");
		});

		startPagePanel.adoptionsButton.addActionListener((ActionEvent _) -> {
			onNavigationButtonPressed("adoptions", "Adoptionen");
		});

		startPagePanel.patronsButton.addActionListener((ActionEvent _) -> {
			onNavigationButtonPressed("patrons", "Paten");
		});

		startPagePanel.caretakersButton.addActionListener((ActionEvent _) -> {
			onNavigationButtonPressed("caretakers", "Pfleger");
		});

		startPagePanel.vetsButton.addActionListener((ActionEvent _) -> {
			onNavigationButtonPressed("vets", "Ärzte");
		});

		cardPanel.add(startPagePanel, "start");

		animalViewPanel = new AnimalViewPanel();
		cardPanel.add(animalViewPanel, "animals");

		roomsPanel = new RoomsPanel();

		roomsPanel.getAnimalsList().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					onNavigationButtonPressed("animals", "Tiere");
					animalViewPanel.selectAnimalById(roomsPanel.getAnimalsList().getSelectedValue().getId());
				}
			}
		});

		cardPanel.add(roomsPanel, "rooms");

		adoptionsPanel = new AdoptionsPanel();
		cardPanel.add(adoptionsPanel, "adoptions");

		patronPanel = new PatronPanel();
		cardPanel.add(patronPanel, "patrons");

		caretakerPanel = new CaretakerPanel();
		cardPanel.add(caretakerPanel, "caretakers");

		vetPanel = new VetPanel();
		cardPanel.add(vetPanel, "vets");

		mainPanel.add(cardPanel);

		pack();
		setVisible(true);
	}

	private void onNavigationButtonPressed(String name, String title) {
		backButton.setVisible(!name.equals("start"));
		if(name.equals("start")) {
			titleLabel.setIcon( IconManager.getIcon(IconManager.Type.APP, Size.LARGE));
		}
		if (name.equals("animals")) {
			titleLabel.setIcon( IconManager.getIcon(IconManager.Type.ANIMAL, Size.SMALL));
			animalViewPanel.clearForm();
		} else if (name.equals("rooms")) {
			titleLabel.setIcon( IconManager.getIcon(IconManager.Type.ROOMS, Size.SMALL));
			roomsPanel.updateRoomsListData();
		} else if (name.equals("adoptions")) {
			titleLabel.setIcon( IconManager.getIcon(IconManager.Type.ADOPTION, Size.SMALL));
			adoptionsPanel.updateAnimalsListData();
		} else if (name.equals("patrons")) {
			titleLabel.setIcon( IconManager.getIcon(IconManager.Type.PATRON, Size.SMALL));
			patronPanel.clearForm();
		} else if (name.equals("caretakers")) {
			titleLabel.setIcon( IconManager.getIcon(IconManager.Type.CARETAKER, Size.SMALL));
			caretakerPanel.clearForm();
		} else if (name.equals("vets")) {
			titleLabel.setIcon( IconManager.getIcon(IconManager.Type.VET, Size.SMALL));
			vetPanel.clearForm();
		}
		titleLabel.setText(title);
		cardLayout.show(cardPanel, name);
	}
}

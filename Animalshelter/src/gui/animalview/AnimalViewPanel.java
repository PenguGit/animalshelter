package gui.animalview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

import bl.DTOManager;
import bl.entities.AnimalDTO;
import bl.entities.ExaminationDTO;
import bl.entities.IncidentDTO;
import gui.PersonListCellRenderer;
import gui.ShelterButton;
import gui.ShelterImagePanel;
import gui.ShelterLabel;
import gui.ShelterList;
import gui.ShelterPanel;
import gui.ShelterTextArea;
import gui.ShelterTextField;

public class AnimalViewPanel extends ShelterPanel {
	DTOManager dtoManager;

	private ShelterList<AnimalDTO> animalList;
	private ShelterList<IncidentDTO> incidentList;
	private ShelterList<ExaminationDTO> examinationList;
	private ShelterButton addIncidentButton;
	private ShelterButton saveButton;
	private ShelterButton deleteButton;

	private ShelterTextField roomField;
	private ShelterTextField genderField;
	private ShelterTextField nameField;
	private ShelterLabel nameLabel;
	private ShelterTextField birthDateField;
	private ShelterLabel birthDateLabel;

	private ShelterTextField additionalInfoField;
	private ShelterLabel additionalInfoLabel;
	private ShelterTextArea additionalInfoArea;

	private ShelterImagePanel imagePanel;

	public AnimalViewPanel() {
		setLayout(new BorderLayout());
		dtoManager = new DTOManager();
		initOutput();
		setBackground(Color.DARK_GRAY);
		initInput();
		initLists();
	}

	private void initOutput() {
		// Main output panel setup
		ShelterPanel outputPanel = new ShelterPanel();
		outputPanel.setLayout(new BorderLayout());

		// Create and arrange the three main components
		ShelterImagePanel imagePanel = new ShelterImagePanel(dtoManager.loadAnimalById(3).getImage());
		ShelterPanel additionalInfoPanel = createAdditionalInfoPanel();

		// Arrange components in the center panel
		ShelterPanel centerPanel = createCenterPanel(imagePanel);

		// Add all components to the output panel
		outputPanel.add(additionalInfoPanel, BorderLayout.SOUTH);
		outputPanel.add(centerPanel, BorderLayout.CENTER);

		// Add output panel to the main container
		add(outputPanel, BorderLayout.CENTER);
	}

	private ShelterPanel createAdditionalInfoPanel() {
		ShelterPanel panel = new ShelterPanel();
		panel.setLayout(new BorderLayout());
		panel.setBackground(new Color(245, 245, 245));

		// Add additional info section
		additionalInfoLabel = new ShelterLabel("Additional Info:");
		additionalInfoArea = new ShelterTextArea(5, 20);
		panel.add(additionalInfoLabel, BorderLayout.NORTH);
		panel.add(new JScrollPane(additionalInfoArea), BorderLayout.WEST);

		// Add patron section
		panel.add(createPatronPanel(), BorderLayout.SOUTH);

		return panel;
	}

	private ShelterPanel createPatronPanel() {
		ShelterPanel panel = new ShelterPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.setBackground(Color.GRAY);

		ShelterLabel patronLabel = new ShelterLabel("Patron:");
		JComboBox<String> patronDropdown = new JComboBox<>(new String[] { "Patron 1", "Patron 2", "Patron 3" });

		panel.add(patronLabel);
		panel.add(patronDropdown);

		return panel;
	}

	private ShelterPanel createCenterPanel(ShelterImagePanel imagePanel) {
		ShelterPanel centerPanel = new ShelterPanel();
		centerPanel.setLayout(new GridBagLayout());
		centerPanel.setBackground(Color.LIGHT_GRAY);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL; // Important for consistent width

		// Image Panel
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 2; // Span 3 rows
		centerPanel.add(imagePanel, gbc);

		gbc.gridheight = 1; // Reset gridheight

		// Name Label
		gbc.gridx = 1;
		gbc.gridy = 0;
		centerPanel.add(new ShelterLabel("Name:"), gbc);

		// Name Field
		gbc.gridx = 2; // Next column
		gbc.gridy = 0;
		centerPanel.add(nameField = new ShelterTextField(15), gbc);

		// Birthdate Label
		gbc.gridx = 1;
		gbc.gridy = 1;
		centerPanel.add(new ShelterLabel("Birthdate:"), gbc);

		// Birthdate Field
		gbc.gridx = 2;
		gbc.gridy = 1;
		centerPanel.add(birthDateField = new ShelterTextField(15), gbc);

		// Gender Label
		gbc.gridx = 0;
		gbc.gridy = 2;
		centerPanel.add(new ShelterLabel("Gender:"), gbc);

		// Gender Field
		gbc.gridx = 1;
		gbc.gridy = 2;
		centerPanel.add(genderField = new ShelterTextField(15), gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		centerPanel.add(new ShelterLabel("Room:"), gbc);

		// Gender Field
		gbc.gridx = 1;
		gbc.gridy = 3;

		centerPanel.add(roomField = new ShelterTextField(15), gbc);

		return centerPanel;
	}

	private void initInput() {
		ShelterPanel buttonPanel = new ShelterPanel();
		buttonPanel.setBackground(new Color(230, 230, 230));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.add(Box.createHorizontalGlue());

		saveButton = new ShelterButton("Save");
		deleteButton = new ShelterButton("Delete");

		buttonPanel.add(saveButton);
		buttonPanel.add(deleteButton);

		add(buttonPanel, BorderLayout.SOUTH);
	}

	private void initLists() {
		// Create main list panel
		ShelterPanel listPanel = new ShelterPanel();
		listPanel.setLayout(new BorderLayout());

		// Add side list to the main panel
		initSideList(listPanel);

		// Create and add right panel with incidents and examinations
		ShelterPanel rightPanel = createRightPanel();
		listPanel.add(rightPanel, BorderLayout.EAST);

		// Add list panel to the main container
		add(listPanel, BorderLayout.EAST);
	}

	private void initSideList(ShelterPanel listPanel) {
		DefaultListModel<AnimalDTO> listModel = new DefaultListModel<>();
		listModel.addAll(dtoManager.loadAnimals());
		animalList = new ShelterList<AnimalDTO>(listModel);
		animalList.setCellRenderer(new PersonListCellRenderer());
		animalList.setFont(FONT_LIST);
		JScrollPane sideListScrollPane = new JScrollPane(animalList);
		sideListScrollPane.setPreferredSize(new Dimension(250, 300));
		
		add(sideListScrollPane, BorderLayout.WEST);
	}

	private ShelterPanel createRightPanel() {
		ShelterPanel rightPanel = new ShelterPanel();
		rightPanel.setLayout(new BorderLayout());

		// Add incident section
		rightPanel.add(createIncidentPanel(), BorderLayout.NORTH);
		rightPanel.add(new JScrollPane(incidentList), BorderLayout.CENTER);

		// Add examination section
		rightPanel.add(createExaminationPanel(), BorderLayout.SOUTH);

		return rightPanel;
	}

	private ShelterPanel createIncidentPanel() {
		// Create incident header panel
		ShelterPanel incidentPanel = new ShelterPanel();
		incidentPanel.setLayout(new BoxLayout(incidentPanel, BoxLayout.X_AXIS));
		incidentPanel.add(Box.createHorizontalGlue());
		incidentPanel.setBackground(Color.MAGENTA);

		// Create and style components
		ShelterLabel incidentLabel = new ShelterLabel("Incidents:");
		DefaultListModel<IncidentDTO> listModel = new DefaultListModel<>();
		listModel.addAll(dtoManager.loadIncidents()); //TODO Something bad here?
		incidentList = new ShelterList<IncidentDTO>(listModel);
		incidentList.setCellRenderer(new PersonListCellRenderer());
		incidentList.setFont(FONT_LIST);
		addIncidentButton = new ShelterButton("+");

		// Add components to panel
		incidentPanel.add(incidentLabel);
		incidentPanel.add(addIncidentButton);

		return incidentPanel;
	}

	private ShelterPanel createExaminationPanel() {
		ShelterPanel examinationPanel = new ShelterPanel();
		examinationPanel.setLayout(new BorderLayout());
		examinationPanel.setBackground(Color.CYAN);
		
		// Create and style components
		ShelterLabel examinationLabel = new ShelterLabel("Examination:");
		DefaultListModel<ExaminationDTO> listModel = new DefaultListModel<>();
		listModel.addAll(dtoManager.loadExaminations()); //TODO Something bad here?
		examinationList = new ShelterList<ExaminationDTO>(listModel);
		examinationList.setCellRenderer(new PersonListCellRenderer());
		examinationList.setFont(FONT_LIST);

		// Add components to panel
		examinationPanel.add(examinationLabel, BorderLayout.NORTH);
		examinationPanel.add(new JScrollPane(examinationList), BorderLayout.CENTER);

		return examinationPanel;
	}

}
package gui.animalview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import bl.DTOManager;
import bl.entities.AnimalDTO;
import bl.entities.AnimalTypeDTO;
import bl.entities.EntityDTO;
import bl.entities.ExaminationDTO;
import bl.entities.IncidentDTO;
import bl.entities.PatronDTO;
import bl.entities.RoomDTO;
import gui.PersonListCellRenderer;
import gui.ShelterButton;
import gui.ShelterComboBox;
import gui.ShelterImagePanel;
import gui.ShelterLabel;
import gui.ShelterList;
import gui.ShelterPanel;
import gui.ShelterRadioButton;
import gui.ShelterTextArea;
import gui.ShelterTextField;

public class AnimalViewPanel extends ShelterPanel {
	DTOManager dtoManager;
	private AnimalDTO animal;

	private ShelterList<AnimalDTO> animalList;
	private ShelterList<IncidentDTO> incidentList;
	private ShelterList<ExaminationDTO> examinationList;
	DefaultListModel<AnimalDTO> animalListModel;
	DefaultListModel<IncidentDTO> incidentListModel;
	DefaultListModel<ExaminationDTO> examinationListModel;
	DefaultComboBoxModel<PatronDTO> patronComboBoxModel;
	
	private ShelterButton addIncidentButton;
	private ShelterButton saveButton;
	private ShelterButton deleteButton;
	private ShelterButton adoptionButton;

	private ShelterComboBox<AnimalTypeDTO> animalTypeComboBox;
	private ShelterComboBox<RoomDTO> roomComboBox;
	private ShelterComboBox<PatronDTO> patronComboBox;
	
	private ArrayList<ShelterRadioButton> radioButtonList;

	private ShelterTextField nameField;
	private ShelterLabel nameLabel;
	private ShelterBirthdateTextField birthDateField;
	private ShelterLabel birthDateLabel;
	private ShelterLabel animalTypeLabel;

	private ShelterTextField additionalInfoField;
	private ShelterLabel additionalInfoLabel;
	private ShelterTextArea additionalInfoArea;

	private ShelterImagePanel imagePanel;
	private ButtonGroup genderButtonGroup;
	

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

		additionalInfoLabel = new ShelterLabel("Zusätzliche Info: ");
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

		ShelterLabel patronLabel = new ShelterLabel("Pate: ");
		patronComboBoxModel = new DefaultComboBoxModel<>();
		patronComboBoxModel.addAll(dtoManager.loadPatrons());
		patronComboBox = new ShelterComboBox<>(patronComboBoxModel);
		patronComboBox.setRenderer(new PersonListCellRenderer());

		panel.add(patronLabel);
		panel.add(patronComboBox);

		return panel;
	}

	private ShelterPanel createCenterPanel(ShelterImagePanel imagePanel) {
		ShelterPanel centerPanel = new ShelterPanel();
		centerPanel.setLayout(new GridBagLayout());
		centerPanel.setBackground(Color.LIGHT_GRAY);

		genderButtonGroup = new ButtonGroup();
		ShelterPanel genderPanel = new ShelterPanel(new FlowLayout(FlowLayout.LEFT, 2, 2));

		genderPanel.setBackground(centerPanel.getBackground());
		
		radioButtonList = new ArrayList<>();
		
		ShelterRadioButton maleRadioButton = new ShelterRadioButton("M");
		ShelterRadioButton femaleRadioButton = new ShelterRadioButton("W");
		ShelterRadioButton unknownRadioButton = new ShelterRadioButton("N/A");
		maleRadioButton.setBackground(centerPanel.getBackground());
		femaleRadioButton.setBackground(centerPanel.getBackground());
		unknownRadioButton.setBackground(centerPanel.getBackground());
		genderButtonGroup.add(maleRadioButton);
		genderButtonGroup.add(femaleRadioButton);
		genderButtonGroup.add(unknownRadioButton);
		genderPanel.add(maleRadioButton);
		genderPanel.add(femaleRadioButton);
		genderPanel.add(unknownRadioButton);
		
		radioButtonList.add(maleRadioButton);
		radioButtonList.add(femaleRadioButton);
		radioButtonList.add(unknownRadioButton);

		DefaultComboBoxModel<RoomDTO> roomBoxModel = new DefaultComboBoxModel<>();
		roomBoxModel.addAll(dtoManager.loadRooms());
		roomComboBox = new ShelterComboBox<>(roomBoxModel);
		roomComboBox.setRenderer(new PersonListCellRenderer());

		DefaultComboBoxModel<AnimalTypeDTO> animalTypeBoxModel = new DefaultComboBoxModel<>();
		animalTypeBoxModel.addAll(dtoManager.loadAnimalTypes());
		animalTypeComboBox = new ShelterComboBox<>(animalTypeBoxModel);
		animalTypeComboBox.setRenderer(new PersonListCellRenderer());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL; // Important for consistent width

		// Image Panel
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 3; // Span 3 rows
		centerPanel.add(imagePanel, gbc);

		gbc.gridheight = 1; // Reset gridheight

		// Name Label
		gbc.gridx = 1;
		gbc.gridy = 0;
		centerPanel.add(new ShelterLabel("Name: "), gbc);

		// Name Field
		gbc.gridx = 2; // Next column
		gbc.gridy = 0;
		centerPanel.add(nameField = new ShelterTextField(15), gbc);

		// Birthdate Label
		gbc.gridx = 1;
		gbc.gridy = 1;
		centerPanel.add(new ShelterLabel("Geburtsdatum: "), gbc);

		// Birthdate Field
		gbc.gridx = 2;
		gbc.gridy = 1;
		centerPanel.add(birthDateField = new ShelterBirthdateTextField(), gbc);

		// Gender Label
		gbc.gridx = 1;
		gbc.gridy = 2;
		centerPanel.add(new ShelterLabel("Geschlecht: "), gbc);

		// Gender Field
		gbc.gridx = 2;
		gbc.gridy = 2;
		centerPanel.add(genderPanel, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		centerPanel.add(new ShelterLabel("Typ: "), gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		centerPanel.add(animalTypeComboBox, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		centerPanel.add(new ShelterLabel("Raum: "), gbc);

		// Room Combobox Field
		gbc.gridx = 1;
		gbc.gridy = 4;

		centerPanel.add(roomComboBox, gbc);

		return centerPanel;
	}

	private void initInput() {
		ShelterPanel buttonPanel = new ShelterPanel();
		buttonPanel.setBackground(new Color(230, 230, 230));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.add(Box.createHorizontalGlue());

		adoptionButton = new ShelterButton("Adoptieren");
		saveButton = new ShelterButton("Speichern");
		deleteButton = new ShelterButton("Löschen");

		saveButton.addActionListener(e -> {
			saveAnimal();
		});

		buttonPanel.add(adoptionButton);
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
		animalListModel = new DefaultListModel<>();
		animalListModel.addAll(dtoManager.loadAnimals());
		animalList = new ShelterList<AnimalDTO>(animalListModel);
		animalList.setCellRenderer(new PersonListCellRenderer());
		animalList.setFont(FONT_LIST);
		animalList.addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				AnimalDTO selectedAnimal = animalList.getSelectedValue();

				if (selectedAnimal != null) {
					loadAnimal(selectedAnimal);
				}
			}
		});
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
		incidentListModel = new DefaultListModel<>();
		incidentListModel.addAll(dtoManager.loadIncidents()); // TODO Something bad here?
		incidentList = new ShelterList<IncidentDTO>(incidentListModel);
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
		examinationListModel = new DefaultListModel<>();
		examinationListModel.addAll(dtoManager.loadExaminations()); // TODO Something bad here?
		examinationList = new ShelterList<ExaminationDTO>(examinationListModel);
		examinationList.setCellRenderer(new PersonListCellRenderer());
		examinationList.setFont(FONT_LIST);

		// Add components to panel
		examinationPanel.add(examinationLabel, BorderLayout.NORTH);
		examinationPanel.add(new JScrollPane(examinationList), BorderLayout.CENTER);

		return examinationPanel;
	}

	public static <T> void refreshListModel(DefaultListModel<T> model, ArrayList<T> newItems) {
		model.clear();
		model.addAll(newItems);
	}
	
	public static <T> void refreshBoxModel(DefaultComboBoxModel<T> model, ArrayList<T> newItems) {
        model.removeAllElements(); // Clear existing items
        for (T item : newItems) {
            model.addElement(item);
        }
    }

	// Assuming AnimalDTO is defined with relevant fields and constructor
	public void saveAnimal() {
		// Retrieve text from fields

		if (animal == null) {

			String name = nameField.getText().trim();
			LocalDate birthDate = birthDateField.getDate();
			String additionalInfo = additionalInfoArea.getText().trim();

			// Get selected patron from ComboBox (if any)
			PatronDTO patron = (PatronDTO) patronComboBox.getSelectedItem();
			AnimalTypeDTO animalType = (AnimalTypeDTO) animalTypeComboBox.getSelectedItem();
			RoomDTO room = (RoomDTO) roomComboBox.getSelectedItem();

			byte[] image;
			image = null;

			animal = new AnimalDTO(name, AnimalDTO.Gender.fromValue(0), birthDate, additionalInfo, animalType, patron,
					room, image);

			// Validate and save or process the object
			if (validateAnimal(animal)) {
				// Save or perform operations with animalDTO
				dtoManager.saveAnimal(animal);
			} else {
				JOptionPane.showMessageDialog(null, "Please fill all required fields correctly.", "Validation Error",
						JOptionPane.ERROR_MESSAGE);
			}
			refreshListModel(animalListModel, dtoManager.loadAnimals());
			clearForm();
		}

		if (animal != null) {

			String name = nameField.getText().trim();
			LocalDate birthDate = birthDateField.getDate();
			String additionalInfo = additionalInfoArea.getText().trim();

			// Get selected patron from ComboBox (if any)
			PatronDTO patron = (PatronDTO) patronComboBox.getSelectedItem();
			AnimalTypeDTO animalType = (AnimalTypeDTO) animalTypeComboBox.getSelectedItem();
			RoomDTO room = (RoomDTO) roomComboBox.getSelectedItem();

			byte[] image;
			image = null;

			animal = new AnimalDTO(animal.getId(), name, AnimalDTO.Gender.fromValue(getGenderFromRadio()), birthDate, additionalInfo,
					animalType, patron, room, image);

			// Validate and save or process the object
			if (validateAnimal(animal)) {
				// Save or perform operations with animalDTO
				dtoManager.saveAnimal(animal);
			} else {
				JOptionPane.showMessageDialog(null, "Please fill all required fields correctly.", "Validation Error",
						JOptionPane.ERROR_MESSAGE);
			}
			refreshListModel(animalListModel, dtoManager.loadAnimals());
			clearForm();
		}
	}

	public void clearForm() {
		// Clear text fields
		nameField.setText("");
		birthDateField.setText("");
		additionalInfoArea.setText("");
		

		// Reset combo boxes
		roomComboBox.setSelectedIndex(-1);
		patronComboBox.setSelectedIndex(-1);
		animalTypeComboBox.setSelectedIndex(-1);

		incidentListModel.clear();
		examinationListModel.clear();
		genderButtonGroup.clearSelection();
		animal = null;
	}

	// Example validation logic
	private boolean validateAnimal(AnimalDTO animalDTO) {
		return !animalDTO.getName().isEmpty() && animalDTO.getDateOfBirth() != null && animalDTO.getRoom() != null
				&& animalDTO.getGender() != null && animalDTO.getAnimalType() != null;
	}
	
	
    private int getGenderFromRadio() {
        if (genderButtonGroup.getSelection() != null) {
            for (int i = 0; i < radioButtonList.size(); i++) {
                if (radioButtonList.get(i).isSelected()) {
                    return i;
                }
            }
        }
        return -1;
    }

    
    private void setRadioFromGender() {
        for (int i = 0; i < radioButtonList.size(); i++) {
            if (animal.getGender().getValue() == i) {
                radioButtonList.get(i).setSelected(true);
            }
        }
    }
    

	public void loadAnimal(AnimalDTO animal) {
		this.animal = animal;
		refreshBoxModel(patronComboBoxModel, dtoManager.loadPatrons());
		// Populate text fields
		nameField.setText(animal.getName());
		birthDateField.setDate(animal.getDateOfBirth());
		additionalInfoArea.setText(animal.getAdditionalInfo());
		setRadioFromGender();

		// Select the matching room in the ComboBox
		selectComboBoxItemById(roomComboBox, animal.getRoom());
		selectComboBoxItemById(animalTypeComboBox, animal.getAnimalType());
		// Select the matching patron in the ComboBox if available
		if (animal.getPatron() != null) {
			selectComboBoxItemById(patronComboBox, animal.getPatron());
		}
	}

	// Utility method to select an item in a ComboBox by ID
	private <T extends EntityDTO> void selectComboBoxItemById(JComboBox<T> comboBox, T entity) {
		if (entity == null)
			return;
		for (int i = 0; i < comboBox.getItemCount(); i++) {
			T item = comboBox.getItemAt(i);
			if (item.getId() == entity.getId()) {
				comboBox.setSelectedIndex(i);
				break;
			}
		}
	}

}
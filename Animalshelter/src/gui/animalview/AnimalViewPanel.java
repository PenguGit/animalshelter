package gui.animalview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;

import bl.DTOManager;
import bl.entities.AnimalDTO;
import bl.entities.AnimalTypeDTO;
import bl.entities.EntityDTO;
import bl.entities.ExaminationDTO;
import bl.entities.IncidentDTO;
import bl.entities.PatronDTO;
import bl.entities.RoomDTO;
import gui.ExaminationPopupPanel;
import gui.IncidentPopupPanel;
import gui.ShelterListCellRenderer;
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
	private ShelterButton addExaminationButton;
	private ShelterButton saveButton;
	private ShelterButton deleteButton;
	private ShelterButton adoptionButton;
	private ShelterButton newButton;

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
		
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		initOutput();
		initInput();
		initLists();
	}

	private void initOutput() {
		// Main output panel setup
		ShelterPanel outputPanel = new ShelterPanel();
		outputPanel.setLayout(new BorderLayout());

		// Create and arrange the three main components
		ShelterImagePanel imagePanel = new ShelterImagePanel(new byte[] {});
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
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
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

		ShelterLabel patronLabel = new ShelterLabel("Pate: ");
		patronComboBoxModel = new DefaultComboBoxModel<>();
		patronComboBoxModel.addAll(dtoManager.loadPatrons());
		patronComboBox = new ShelterComboBox<>(patronComboBoxModel);
		patronComboBox.setRenderer(new ShelterListCellRenderer());

		panel.add(patronLabel);
		panel.add(patronComboBox);

		return panel;
	}

	private ShelterPanel createCenterPanel(ShelterImagePanel imagePanel) {
		ShelterPanel centerPanel = new ShelterPanel();
		centerPanel.setLayout(new GridBagLayout());
		centerPanel.setBackground(Color.LIGHT_GRAY);
		centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

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
		roomComboBox.setRenderer(new ShelterListCellRenderer());

		DefaultComboBoxModel<AnimalTypeDTO> animalTypeBoxModel = new DefaultComboBoxModel<>();
		animalTypeBoxModel.addAll(dtoManager.loadAnimalTypes());
		animalTypeComboBox = new ShelterComboBox<>(animalTypeBoxModel);
		animalTypeComboBox.setRenderer(new ShelterListCellRenderer());

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
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		newButton = new ShelterButton(NEW_BUTTON);
		
		adoptionButton = new ShelterButton("Adoptieren");
		adoptionButton.setVisible(false);
		saveButton = new ShelterButton(SAVE_BUTTON);
		deleteButton = new ShelterButton(DELETE_BUTTON);
		deleteButton.setVisible(false);
		
		newButton.addActionListener(_ -> {
			clearForm();
		});
		
		saveButton.addActionListener(_ -> {
			saveAnimal();
		});
		
		deleteButton.addActionListener(_ -> {
			deleteAnimal();
		});

		buttonPanel.add(newButton);
		buttonPanel.add(adoptionButton);
		buttonPanel.add(saveButton);
		buttonPanel.add(deleteButton);

		add(buttonPanel, BorderLayout.SOUTH);
	}

	private void initLists() {
		// Add side list to the main panel
		initSideList();

		// Create and add right panel with incidents and examinations
		ShelterPanel rightPanel = createRightPanel();

		// Add list panel to the main container
		add(rightPanel, BorderLayout.EAST);
	}

	private void initSideList() {
		animalListModel = new DefaultListModel<>();
		animalListModel.addAll(dtoManager.loadAnimals());
		animalList = new ShelterList<AnimalDTO>(animalListModel);
		animalList.addListSelectionListener((ListSelectionEvent e) -> {
			onAnimalListSelectionChanged(e);
		});
		JScrollPane sideListScrollPane = new JScrollPane(animalList);
		sideListScrollPane.setPreferredSize(new Dimension(250, 300));

		add(sideListScrollPane, BorderLayout.WEST);
	}

	private ShelterPanel createRightPanel() {
		ShelterPanel rightPanel = new ShelterPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

		// Add incident section
		rightPanel.add(createIncidentPanel());

		// Add examination section
		rightPanel.add(createExaminationPanel());

		return rightPanel;
	}

	private ShelterPanel createIncidentPanel() {
		// Create incident header panel
		ShelterPanel incidentPanel = new ShelterPanel();
		incidentPanel.setLayout(new BorderLayout());

		ShelterPanel incidentHeaderPanel = new ShelterPanel();
		incidentHeaderPanel.setLayout(new BoxLayout(incidentHeaderPanel, BoxLayout.X_AXIS));
		incidentHeaderPanel.setBackground(Color.MAGENTA);
		incidentPanel.add(incidentHeaderPanel, BorderLayout.NORTH);
		
		// Create and style components
		ShelterLabel incidentLabel = new ShelterLabel("Vorkommnisse:");
		incidentListModel = new DefaultListModel<>();
		incidentList = new ShelterList<IncidentDTO>(incidentListModel);
		addIncidentButton = new ShelterButton("+");
		addIncidentButton.setFocusable(false);
		addIncidentButton.setEnabled(false);
		addIncidentButton.addActionListener((ActionEvent _) -> {
			onNewIncidentButtonPressed();
		});

		// Add components to panel
		incidentHeaderPanel.add(incidentLabel);
		incidentHeaderPanel.add(Box.createHorizontalGlue());
		incidentHeaderPanel.add(addIncidentButton);
		JScrollPane scrollPane = new JScrollPane(incidentList);
		scrollPane.setPreferredSize(new Dimension(250, 300));
		incidentPanel.add(scrollPane, BorderLayout.CENTER);

		return incidentPanel;
	}

	private ShelterPanel createExaminationPanel() {
		ShelterPanel examinationPanel = new ShelterPanel();
		examinationPanel.setLayout(new BorderLayout());

		ShelterPanel examinationHeaderPanel = new ShelterPanel();
		examinationHeaderPanel.setLayout(new BoxLayout(examinationHeaderPanel, BoxLayout.X_AXIS));
		examinationHeaderPanel.setBackground(Color.CYAN);
		examinationPanel.add(examinationHeaderPanel, BorderLayout.NORTH);
		
		// Create and style components
		ShelterLabel examinationLabel = new ShelterLabel("Untersuchungen:");
		examinationListModel = new DefaultListModel<>();
		examinationList = new ShelterList<ExaminationDTO>(examinationListModel);
		addExaminationButton = new ShelterButton("+");
		addExaminationButton.setFocusable(false);
		addExaminationButton.setEnabled(false);
		addExaminationButton.addActionListener((ActionEvent _) -> {
			onNewExaminationButtonPressed();
		});
		
		// Add components to panel
		examinationHeaderPanel.add(examinationLabel);
		examinationHeaderPanel.add(Box.createHorizontalGlue());
		examinationHeaderPanel.add(addExaminationButton);
		JScrollPane scrollPane = new JScrollPane(examinationList);
		scrollPane.setPreferredSize(new Dimension(250, 300));
		examinationPanel.add(scrollPane, BorderLayout.CENTER);

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
		String name = nameField.getText().trim();
		LocalDate birthDate = birthDateField.getDate();
		String additionalInfo = additionalInfoArea.getText().trim();

		// Get selected patron from ComboBox (if any)
		PatronDTO patron = (PatronDTO) patronComboBox.getSelectedItem();
		AnimalTypeDTO animalType = (AnimalTypeDTO) animalTypeComboBox.getSelectedItem();
		RoomDTO room = (RoomDTO) roomComboBox.getSelectedItem();
		
		byte[] image;
		image = null;
		
		if (animal == null) {
			animal = new AnimalDTO(name, AnimalDTO.Gender.fromValue(getGenderFromRadio()), birthDate, additionalInfo, animalType, patron,
					room, image);
		}
		else {
			animal.setName(name);
			animal.setGender(AnimalDTO.Gender.fromValue(getGenderFromRadio()));
			animal.setDateOfBirth(birthDate);
			animal.setAdditionalInfo(additionalInfo);
			animal.setAnimalType(animalType);
			animal.setPatron(patron);
			animal.setRoom(room);
			animal.setImage(image);
		}
		
		// Validate and save or process the object
		if (validateAnimal(animal)) {
			dtoManager.saveAnimal(animal);
			refreshListModel(animalListModel, dtoManager.loadAnimals());
			clearForm();
		} else {
			JOptionPane.showMessageDialog(null, "Please fill all required fields correctly.", "Validation Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void deleteAnimal() {
		if(animal == null) {
			return;
		}
		
		dtoManager.deleteAnimal(animal);
		
		refreshListModel(animalListModel, dtoManager.loadAnimals());
		clearForm();
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
		
		animalList.clearSelection();
	}

	// Example validation logic
	private boolean validateAnimal(AnimalDTO animalDTO) {
		return !animalDTO.getName().isEmpty() && animalDTO.getDateOfBirth() != null && animalDTO.getRoom() != null
				&& animalDTO.getGender() != null && animalDTO.getAnimalType() != null && getGenderFromRadio() >= 0;
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

		// Select the matching room and animal type in the ComboBox
		selectComboBoxItemById(roomComboBox, animal.getRoom());
		selectComboBoxItemById(animalTypeComboBox, animal.getAnimalType());
		// Select the matching patron in the ComboBox if available
		if (animal.getPatron() != null) {
			selectComboBoxItemById(patronComboBox, animal.getPatron());
		}
		
		updateIncidentList();
		updateExaminationList();
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
	
	private void onAnimalListSelectionChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			animal = animalList.getSelectedValue();

			if (animal != null) {
				loadAnimal(animal);
			}
			
			addIncidentButton.setEnabled(animal != null);
			addExaminationButton.setEnabled(animal != null);
			deleteButton.setVisible(animal != null);
			adoptionButton.setVisible(animal != null);
		}
	}

	private void onNewIncidentButtonPressed() {
		if(animal == null) {
			return;
		}
		
		JDialog dialog = new JDialog((JFrame)SwingUtilities.getWindowAncestor(this), "Neues Vorkommnis", true);
        dialog.setSize(200, 150);
        dialog.setLocationRelativeTo(this);
        dialog.setResizable(false);

        IncidentPopupPanel panel = new IncidentPopupPanel(animal);
        dialog.add(panel);

        panel.getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        
        panel.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	IncidentDTO incident = panel.getIncident();
            	if(incident != null) {
            		dtoManager.saveIncident(incident);
            		dialog.dispose();
                    updateIncidentList();
            	}
            }
        });

        dialog.pack();
        dialog.setVisible(true);
	}
	
	private void updateIncidentList() {
		animal = animalList.getSelectedValue();
		
		if(animal == null) {
			return;
		}
		
		incidentListModel.clear();
		incidentListModel.addAll(dtoManager.loadIncidentsByAnimalId(animal.getId()));
	}
	
	private void onNewExaminationButtonPressed() {
		if(animal == null) {
			return;
		}
		
		JDialog dialog = new JDialog((JFrame)SwingUtilities.getWindowAncestor(this), "Neue Untersuchung", true);
        dialog.setSize(200, 150);
        dialog.setLocationRelativeTo(this);
        dialog.setResizable(false);

        ExaminationPopupPanel panel = new ExaminationPopupPanel(animal);
        dialog.add(panel);

        panel.cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        
        panel.saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	ExaminationDTO incident = panel.getExamination();
            	if(incident != null) {
            		dtoManager.saveExamination(incident);
            		dialog.dispose();
                    updateExaminationList();
            	}
            }
        });

        dialog.pack();
        dialog.setVisible(true);
	}
	
	private void updateExaminationList() {
		animal = animalList.getSelectedValue();
		
		if(animal == null) {
			return;
		}
		
		examinationListModel.clear();
		examinationListModel.addAll(dtoManager.loadExaminationsByAnimalId(animal.getId()));
	}
	
	public void selectAnimalById(int id) {
		for(int i = 0; i < animalListModel.getSize(); i++) {
			AnimalDTO animal = animalListModel.getElementAt(i);
			if(animal.getId() == id) {
				animalList.setSelectedIndex(i);
				return;
			}
		}
		animalList.setSelectedIndex(-1);
	}
}
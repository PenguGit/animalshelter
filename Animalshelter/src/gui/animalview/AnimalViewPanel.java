package gui.animalview;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.filechooser.FileNameExtensionFilter;

import bl.DTOManager;
import bl.entities.AnimalDTO;
import bl.entities.AnimalTypeDTO;
import bl.entities.EntityDTO;
import bl.entities.ExaminationDTO;
import bl.entities.IncidentDTO;
import bl.entities.PatronDTO;
import bl.entities.RoomDTO;
import gui.ShelterBirthdateTextField;
import gui.ShelterButton;
import gui.ShelterComboBox;
import gui.ShelterImagePanel;
import gui.ShelterLabel;
import gui.ShelterList;
import gui.ShelterPanel;
import gui.ShelterRadioButton;
import gui.ShelterSearchField;
import gui.ShelterTextArea;
import gui.ShelterTextField;
import gui.adoptions.AdoptionPopupDialog;
import gui.events.ExaminationCreateDialog;
import gui.events.ExaminationDisplayDialog;
import gui.events.IncidentCreateDialog;
import gui.events.IncidentDisplayDialog;

/**
 * A panel for viewing and managing animal information. This panel allows users
 * to create, edit, and view animal details, including name, birthdate, gender,
 * type, room assignment, and additional notes. It also includes functionality
 * for uploading images and associating animals with patrons.
 */
public class AnimalViewPanel extends ShelterPanel {
	/**
	 * Manages Data Transfer Objects (DTOs) for handling data operations. Used
	 * throughout the class to facilitate CRUD operations and ensure data
	 * consistency.
	 */
	private DTOManager dtoManager;

	/**
	 * Represents the currently selected animal. Needed across multiple
	 * interactions, such as editing, saving, and deleting records.
	 */
	private AnimalDTO animal;

	/**
	 * Holds the list of available animal types. Used to populate dropdown menus
	 * dynamically and maintain consistency in selections.
	 */
	private ArrayList<AnimalTypeDTO> animalTypeList;

	/**
	 * Stores animals that have not yet been adopted. Helps in filtering and
	 * displaying relevant data within the UI.
	 */
	private ArrayList<AnimalDTO> animalsWithoutAdoptionList;

	/**
	 * Maintains a collection of all animals in the shelter. Acts as a central data
	 * source for searches, filtering, and UI updates.
	 */
	private ShelterList<AnimalDTO> animalList;

	/**
	 * Keeps track of all reported incidents related to animals. Ensures incident
	 * records are easily accessible and manageable.
	 */
	private ShelterList<IncidentDTO> incidentList;

	/**
	 * Contains a history of medical examinations performed on animals. Provides
	 * easy access to examination details for reference and updates.
	 */
	private ShelterList<ExaminationDTO> examinationList;

	/**
	 * Data model backing the UI list of animals. Ensures that the displayed list
	 * updates dynamically as data changes.
	 */
	private DefaultListModel<AnimalDTO> animalListModel;

	/**
	 * Data model for the list of incidents. Keeps the UI in sync with any changes
	 * in reported incidents.
	 */
	private DefaultListModel<IncidentDTO> incidentListModel;

	/**
	 * Data model for displaying examination records. Allows real-time updates when
	 * new records are added or modified.
	 */
	private DefaultListModel<ExaminationDTO> examinationListModel;

	/**
	 * Model for the patron selection dropdown. Keeps the list of available patrons
	 * up to date for accurate assignment.
	 */
	private DefaultComboBoxModel<PatronDTO> patronComboBoxModel;

	/**
	 * Buttons used for various actions within the UI. These components are
	 * referenced frequently to handle user interactions and are dynamically
	 * enabled, disabled, or triggered based on application state.
	 */
	private ShelterButton addIncidentButton, addExaminationButton, saveButton, adoptionButton, newButton,
			uploadImageButton, editButton, cancelButton;

	/**
	 * Dropdown for selecting an animal type. Requires persistent storage as it is
	 * dynamically populated and referenced.
	 */
	private ShelterComboBox<AnimalTypeDTO> animalTypeComboBox;

	/**
	 * Dropdown for selecting an animal's assigned room. Keeps track of available
	 * rooms and updates dynamically.
	 */
	private ShelterComboBox<RoomDTO> roomComboBox;

	/**
	 * Dropdown for selecting a patron. Needs to stay up to date with patron
	 * availability and assignments.
	 */
	private ShelterComboBox<PatronDTO> patronComboBox;

	/**
	 * Dropdown for filtering animals by type. Used throughout the UI for searching
	 * and categorization.
	 */
	private ShelterComboBox<AnimalTypeDTO> animalTypeSelectionComboBox;

	/**
	 * Collection of radio buttons for selecting various animal attributes. Grouped
	 * together for easier management of mutually exclusive options.
	 */
	private ArrayList<ShelterRadioButton> radioButtonList;

	/**
	 * Input field for entering an animal's name. Accessed frequently for validation
	 * and data binding.
	 */
	private ShelterTextField nameField;

	/**
	 * Input field for specifying an animal's birthdate. Ensures proper date
	 * formatting and validation.
	 */
	private ShelterBirthdateTextField birthDateField;

	/**
	 * Text area for additional information about the animal. Used across multiple
	 * interactions for storing and displaying descriptive details.
	 */
	private ShelterTextArea additionalInfoArea;

	/**
	 * Panel for displaying and managing the animal's image. Requires persistent
	 * reference as the image updates dynamically.
	 */
	private ShelterImagePanel imagePanel;

	/**
	 * Grouping of radio buttons for gender selection. Ensures only one option can
	 * be selected at a time.
	 */
	private ButtonGroup genderButtonGroup;

	/**
	 * File chooser for selecting images. Used in file-related operations and
	 * requires persistent reference.
	 */
	private JFileChooser fileChooser;

	/**
	 * Search field for filtering animals in the UI. Keeps track of search queries
	 * and updates the display dynamically.
	 */
	private ShelterSearchField animalSearchField;

	/**
	 * The current mode of the AnimalViewPanel, used to determine control states.
	 * {@link #NONE} {@link #SELECTED} {@link #EDIT} {@link #CREATE}
	 */
	private enum Mode {
		/**
		 * The default mode, with nothing selected or being edited.
		 */
		NONE,
		/**
		 * Some animal is selected but not being edited.
		 */
		SELECTED,
		/**
		 * The currently selected animal is being edited.
		 */
		EDIT,
		/**
		 * A new animal was created and is being edited.
		 */
		CREATE
	}

	/**
	 * Constructs a new AnimalViewPanel.Initializes the layout, data access objects,
	 * file chooser, and UI components.
	 */
	public AnimalViewPanel() {
		setLayout(new BorderLayout());
		dtoManager = new DTOManager();
		fileChooser = new JFileChooser();
		animalsWithoutAdoptionList = dtoManager.loadAnimalsNotAdopted();
		animalTypeList = dtoManager.loadAnimalTypes();
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		initOutput();
		initInput();
		initLists();
		changeFormState(Mode.NONE);
	}

	/**
	 * Initializes the output components, primarily the center panel for displaying
	 * animal details.
	 */
	private void initOutput() {
		add(createCenterPanel(), BorderLayout.CENTER);
	}

	/**
	 * Creates and configures the center panel of the layout, containing input
	 * fields and labels for animal data. Uses a GridBagLayout for precise component
	 * placement.
	 *
	 * @return The created ShelterPanel representing the center panel.
	 */
	private ShelterPanel createCenterPanel() {
		ShelterPanel centerPanel = new ShelterPanel();
		centerPanel.setLayout(new GridBagLayout());
		centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.BOTH;

		// --- Image Panel ---
		imagePanel = new ShelterImagePanel(null);
		gbc.gridheight = 2;
		gbc.gridwidth = 2;
		addComponent(centerPanel, gbc, imagePanel, 0, 0);

		// --- Upload Image Button ---
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		uploadImageButton = new ShelterButton("Upload");
		uploadImageButton.addActionListener(_ -> onUploadImageButtonPressed());
		addComponent(centerPanel, gbc, uploadImageButton, 0, 2);

		// --- Name Label ---
		addComponent(centerPanel, gbc, new ShelterLabel("Name: "), 2, 0);

		// --- Name Field ---
		nameField = new ShelterTextField(15);
		addComponent(centerPanel, gbc, nameField, 3, 0);

		// --- Birthdate Label ---
		addComponent(centerPanel, gbc, new ShelterLabel("Geburtsdatum: "), 2, 1);

		// --- Birthdate Field ---
		birthDateField = new ShelterBirthdateTextField();
		addComponent(centerPanel, gbc, birthDateField, 3, 1);

		// --- Gender Label ---
		addComponent(centerPanel, gbc, new ShelterLabel("Geschlecht: "), 2, 2);

		// --- Gender Field (Gender Panel creation remains the same) ---
		ShelterPanel genderPanel = new ShelterPanel(new FlowLayout(FlowLayout.LEFT, 1, 2));
		genderButtonGroup = new ButtonGroup();
		radioButtonList = new ArrayList<>();
		String[] genders = { "M", "W", "N/A" };
		for (String gender : genders) {
			ShelterRadioButton radioButton = new ShelterRadioButton(gender);
			genderButtonGroup.add(radioButton);
			genderPanel.add(radioButton);
			radioButtonList.add(radioButton);
		}
		addComponent(centerPanel, gbc, genderPanel, 3, 2);

		// --- Animal Type Label ---
		addComponent(centerPanel, gbc, new ShelterLabel("Typ: "), 0, 3);

		// --- Animal Type ComboBox ---
		DefaultComboBoxModel<AnimalTypeDTO> animalTypeBoxModel = new DefaultComboBoxModel<>();
		animalTypeBoxModel.addAll(animalTypeList);
		animalTypeComboBox = new ShelterComboBox<>(animalTypeBoxModel);
		addComponent(centerPanel, gbc, animalTypeComboBox, 1, 3);

		// --- Room Label ---
		addComponent(centerPanel, gbc, new ShelterLabel("Raum: "), 0, 4);

		// --- Room ComboBox ---
		DefaultComboBoxModel<RoomDTO> roomBoxModel = new DefaultComboBoxModel<>();
		roomBoxModel.addAll(dtoManager.loadRooms());
		roomComboBox = new ShelterComboBox<>(roomBoxModel);
		addComponent(centerPanel, gbc, roomComboBox, 1, 4);
		// --- Additional Info Label ---
		gbc.gridwidth = 2;
		addComponent(centerPanel, gbc, new ShelterLabel("Beschreibung: "), 0, 5);

		// --- Additional Info Area ---
		additionalInfoArea = new ShelterTextArea(5, 20);
		additionalInfoArea.setLineWrap(true);
		additionalInfoArea.setWrapStyleWord(true);
		gbc.gridheight = 2;
		addComponent(centerPanel, gbc, new JScrollPane(additionalInfoArea), 0, 6);

		// --- Patron Label ---
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		addComponent(centerPanel, gbc, new ShelterLabel("Pate: "), 0, 9);

		// --- Patron ComboBox ---
		patronComboBoxModel = new DefaultComboBoxModel<>();
		patronComboBoxModel.addAll(dtoManager.loadPatrons());
		patronComboBox = new ShelterComboBox<>(patronComboBoxModel);
		addComponent(centerPanel, gbc, patronComboBox, 1, 9);

		return centerPanel;
	}

	/**
	 * Adds a component to a Panel in a GridbagLayout at the given coordinates.
	 * 
	 * @param panel     The Panel to add the component to
	 * @param gbc       The constraints that are used for the GridBagLayout
	 * @param component The Component to be added
	 * @param gridx     The column to add the item into
	 * @param gridy     The row to add the item into
	 */
	private void addComponent(ShelterPanel panel, GridBagConstraints gbc, Component component, int gridx, int gridy) {
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		panel.add(component, gbc);
	}

	/**
	 * Initializes the input components, specifically the button panel at the bottom
	 * of the view. This panel contains buttons for new, cancel, edit, save, and
	 * delete operations.
	 */
	private void initInput() {
		ShelterPanel buttonPanel = new ShelterPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		newButton = new ShelterButton(NEW_BUTTON);
		newButton.addActionListener(_ -> {
			onNewButtonPressed();
		});

		cancelButton = new ShelterButton(CANCEL_BUTTON);
		cancelButton.setVisible(false);
		cancelButton.addActionListener(_ -> {
			onCancelButtonPressed();
		});

		editButton = new ShelterButton(EDIT_BUTTON);
		editButton.setVisible(false);
		editButton.addActionListener(_ -> {
			onEditButtonPressed();
		});

		adoptionButton = new ShelterButton("Adoptieren");
		adoptionButton.setVisible(false);
		adoptionButton.addActionListener(_ -> {
			onAdoptionButtonPressed();
		});

		saveButton = new ShelterButton(SAVE_BUTTON);
		saveButton.setVisible(false);
		saveButton.addActionListener(_ -> {
			saveAnimal();
		});

		buttonPanel.add(adoptionButton);
		buttonPanel.add(cancelButton);
		buttonPanel.add(editButton);
		buttonPanel.add(newButton);
		buttonPanel.add(saveButton);

		add(buttonPanel, BorderLayout.SOUTH);
	}

	/**
	 * Initializes the lists within the panel, including the main animal list on the
	 * left and the incident/examination lists on the right.
	 */
	private void initLists() {
		add(initAnimalListPanel(), BorderLayout.WEST);

		add(createRightPanel(), BorderLayout.EAST);
	}

	/**
	 * Initializes the animal list displaying animals that are not yet adopted. This
	 * list allows users to select animals for viewing and editing. It also
	 * initializes the search field and the ComboBox to filter for Animal-Type
	 */
	private ShelterPanel initAnimalListPanel() {
		ShelterPanel animalListPanel = new ShelterPanel();
		animalListPanel.setLayout(new BoxLayout(animalListPanel, BoxLayout.Y_AXIS));

		DefaultComboBoxModel<AnimalTypeDTO> animalTypeBoxModel = new DefaultComboBoxModel<>();
		animalTypeBoxModel.addElement(new AnimalTypeDTO(0, "Alle Tiere"));
		animalTypeBoxModel.addAll(animalTypeList);
		animalTypeSelectionComboBox = new ShelterComboBox<>(animalTypeBoxModel);
		animalTypeSelectionComboBox.setMaximumSize(new Dimension(250, 10));
		animalListPanel.add(animalTypeSelectionComboBox);
		animalTypeSelectionComboBox.addActionListener(_ -> {
			performSearch(animalSearchField.getText());
		});

		animalSearchField = new ShelterSearchField(this::performSearch);
		animalSearchField.setMaximumSize(new Dimension(250, 10));
		animalListPanel.add(animalSearchField);

		animalListModel = new DefaultListModel<>();
		animalListModel.addAll(dtoManager.loadAnimalsNotAdopted());
		animalList = new ShelterList<AnimalDTO>(animalListModel);
		animalList.addListSelectionListener((ListSelectionEvent e) -> {
			onAnimalListSelectionChanged(e);
		});
		JScrollPane animalListScrollPane = new JScrollPane(animalList);
		animalListScrollPane.setPreferredSize(new Dimension(250, 300));

		animalListPanel.add(animalListScrollPane);

		return animalListPanel;
	}

	/**
	 * Performs a search on the list of animals based on the provided query and the
	 * selected animal type. The search is case-insensitive and filters the list
	 * based on both the animal type and the name. The results are then used to
	 * update the displayed list of animals.
	 *
	 * @param query The search query string. Can be empty but not null.
	 */
	public void performSearch(String query) {
		List<AnimalDTO> filtered;
		if (animalTypeSelectionComboBox.getSelectedIndex() != 0) {
			filtered = animalsWithoutAdoptionList.stream()
					.filter(animal -> animal.getAnimalType()
							.getId() == ((AnimalTypeDTO) animalTypeSelectionComboBox.getSelectedItem()).getId())
					.collect(Collectors.toList());
		} else {
			filtered = animalsWithoutAdoptionList;
		}
		if (!animalSearchField.getText().isEmpty()) {
			filtered = filtered.stream().filter(animal -> animal.getName().toLowerCase().contains(query.toLowerCase()))
					.collect(Collectors.toList());
		}
		if (animal != null) {
			for (AnimalDTO a : filtered) {
				if (a.getId() == animal.getId()) {
					refreshListModel(animalListModel, filtered);
					animalList.setSelectedValue(a, true);
					return;
				}
			}
		}
		clearForm();
		refreshListModel(animalListModel, filtered);
		changeFormState(Mode.NONE);
	}

	/**
	 * Creates and configures the right panel, which contains the incident and
	 * examination panels. Uses a BoxLayout for vertical arrangement.
	 *
	 * @return The created ShelterPanel representing the right panel.
	 */
	private ShelterPanel createRightPanel() {
		ShelterPanel rightPanel = new ShelterPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

		rightPanel.add(createIncidentPanel());
		rightPanel.add(createExaminationPanel());

		return rightPanel;
	}

	/**
	 * Creates and configures the incident panel, including a header, a list to
	 * display incidents, and a button to add new incidents.
	 *
	 * @return The created ShelterPanel representing the incident panel.
	 */
	private ShelterPanel createIncidentPanel() {
		// Create incident header panel
		ShelterPanel incidentPanel = new ShelterPanel();
		incidentPanel.setLayout(new BorderLayout());

		ShelterPanel incidentHeaderPanel = new ShelterPanel();
		incidentHeaderPanel.setLayout(new BoxLayout(incidentHeaderPanel, BoxLayout.X_AXIS));
		incidentPanel.add(incidentHeaderPanel, BorderLayout.NORTH);

		// Create and style components
		ShelterLabel incidentLabel = new ShelterLabel("Vorkommnisse:");
		incidentListModel = new DefaultListModel<>();
		incidentList = new ShelterList<IncidentDTO>(incidentListModel);
		incidentList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					onIncidentDoubleClicked();
				}
			}
		});

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

	/**
	 * Creates and configures the examination panel, similar to the incident panel,
	 * including a header, a list for examinations, and a button to add new ones.
	 *
	 * @return The created ShelterPanel representing the examination panel.
	 */
	private ShelterPanel createExaminationPanel() {
		ShelterPanel examinationPanel = new ShelterPanel();
		examinationPanel.setLayout(new BorderLayout());

		ShelterPanel examinationHeaderPanel = new ShelterPanel();
		examinationHeaderPanel.setLayout(new BoxLayout(examinationHeaderPanel, BoxLayout.X_AXIS));
		examinationPanel.add(examinationHeaderPanel, BorderLayout.NORTH);

		// Create and style components
		ShelterLabel examinationLabel = new ShelterLabel("Untersuchungen:");
		examinationListModel = new DefaultListModel<>();
		examinationList = new ShelterList<ExaminationDTO>(examinationListModel);
		examinationList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					onExaminationDoubleClicked();
				}
			}
		});

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

	/**
	 * Fills the animal details form with the data of the currently selected animal.
	 * This method populates the text fields, combo boxes, radio buttons, and image
	 * panel with the information from the 'animal' object. It also refreshes the
	 * incident and examination lists.
	 */
	public void fillForm() {
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
		} else {
			patronComboBox.setSelectedIndex(-1);
		}
		imagePanel.clearImageData();
		if (animal.getImage() != null) {
			imagePanel.setImageData(animal.getImage());
		}

		refreshListModel(incidentListModel, dtoManager.loadIncidentsByAnimalId(animal.getId()));
		refreshListModel(examinationListModel, dtoManager.loadExaminationsByAnimalId(animal.getId()));
	}

	/**
	 * Saves the animal data entered in the form. This method retrieves the values
	 * from the input fields, sets them on the 'animal' object, validates the data,
	 * and then saves the animal using the DTOManager. It also refreshes the animal
	 * list, clears the form, and updates the button and form states.
	 */
	public void saveAnimal() {
		String name = nameField.getText().trim();
		LocalDate birthDate = birthDateField.getDate();
		String additionalInfo = additionalInfoArea.getText().trim();

		// Get selected patron from ComboBox (if any)
		PatronDTO patron = (PatronDTO) patronComboBox.getSelectedItem();
		AnimalTypeDTO animalType = (AnimalTypeDTO) animalTypeComboBox.getSelectedItem();
		RoomDTO room = (RoomDTO) roomComboBox.getSelectedItem();

		animal.setName(name);
		animal.setGender(AnimalDTO.Gender.fromValue(getGenderFromRadio()));
		animal.setDateOfBirth(birthDate);
		animal.setAdditionalInfo(additionalInfo);
		animal.setAnimalType(animalType);
		animal.setPatron(patron);
		animal.setRoom(room);

		// Validate and save or process the object
		if (validateAnimal(animal)) {
			if (animal.getId() < 1) {
				dtoManager.saveAnimal(animal);
				animalsWithoutAdoptionList = (dtoManager.loadAnimalsNotAdopted());
				refreshListModel(animalListModel, animalsWithoutAdoptionList);
				clearAll();
				changeFormState(Mode.NONE);
			} else {
				dtoManager.saveAnimal(animal);
				changeFormState(Mode.SELECTED);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Please fill all required fields correctly.", "Validation Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Selects an animal in the list by its ID. This method iterates through the
	 * animal list model to find an animal with the matching ID and selects it in
	 * the list. This is used in roomsPanel to make a selection in the AnimalList
	 * there, be transferred to AnimalViewPanel
	 *
	 * @param id The ID of the animal to select.
	 */
	public void selectAnimalById(int id) {
		for (int i = 0; i < animalListModel.getSize(); i++) {
			AnimalDTO animal = animalListModel.getElementAt(i);
			if (animal.getId() == id) {
				animalList.setSelectedIndex(i);
				return;
			}
		}
		animalList.setSelectedIndex(-1);
	}

	/**
	 * Handles the event when the selection in the animal list changes. This method
	 * retrieves the selected animal, fills the form with its details, and updates
	 * the button and form states.
	 *
	 * @param e The ListSelectionEvent object.
	 */
	private void onAnimalListSelectionChanged(ListSelectionEvent e) {

		if (!e.getValueIsAdjusting() && animalList.isEnabled()) {
			animal = animalList.getSelectedValue();

			if (animal != null) {
				fillForm();
			}

			changeFormState(Mode.SELECTED);
		}
	}

	/**
	 * Handles the event when the "New Incident" button is pressed. This method
	 * opens a dialog to create a new incident for the selected animal.
	 */
	private void onNewIncidentButtonPressed() {
		IncidentCreateDialog dialog = new IncidentCreateDialog(animal, (JFrame) SwingUtilities.getWindowAncestor(this),
				"Neues Vorkommnis", true);
		dialog.pack();
		boolean incidentSuccess = dialog.showDialog();
		if (incidentSuccess) {
			refreshListModel(incidentListModel, dtoManager.loadIncidentsByAnimalId(animal.getId()));
		}
	}

	/**
	 * Handles the even when an entry in the incident list is double clicked,
	 * opening the display dialog.
	 */
	private void onIncidentDoubleClicked() {
		IncidentDisplayDialog dialog = new IncidentDisplayDialog(incidentList.getSelectedValue(),
				(JFrame) SwingUtilities.getWindowAncestor(this), "Vorkommnis", true);
		dialog.pack();
		dialog.showDialog();
	}

	/**
	 * Handles the event when the "New Examination" button is pressed. This method
	 * opens a dialog to create a new examination for the selected animal.
	 */
	private void onNewExaminationButtonPressed() {
		ExaminationCreateDialog dialog = new ExaminationCreateDialog(animal,
				(JFrame) SwingUtilities.getWindowAncestor(this), "Neue Untersuchung", true);
		dialog.pack();
		boolean examinationSuccess = dialog.showDialog();
		if (examinationSuccess) {
			refreshListModel(examinationListModel, dtoManager.loadExaminationsByAnimalId(animal.getId()));
		}
	}

	/**
	 * Handles the even when an entry in the examination list is double clicked,
	 * opening the display dialog.
	 */
	private void onExaminationDoubleClicked() {
		ExaminationDisplayDialog dialog = new ExaminationDisplayDialog(examinationList.getSelectedValue(),
				(JFrame) SwingUtilities.getWindowAncestor(this), "Untersuchung", true);
		dialog.pack();
		dialog.showDialog();
	}

	/**
	 * Handles the event when the "Adopt" button is pressed. This method opens a
	 * dialog to handle the adoption process for the selected animal.
	 */
	private void onAdoptionButtonPressed() {
		AdoptionPopupDialog dialog = new AdoptionPopupDialog(animal, (JFrame) SwingUtilities.getWindowAncestor(this),
				"Tier adoptieren", true);
		dialog.pack();
		boolean adoptionSuccess = dialog.showDialog();
		if (adoptionSuccess) {
			animalsWithoutAdoptionList = dtoManager.loadAnimalsNotAdopted();
			refreshListModel(animalListModel, animalsWithoutAdoptionList);
		}
	}

	/**
	 * Converts an image from a Path to a byte array.
	 *
	 * @param imagePath The Path of the image file.
	 * @return A byte array representing the image data.
	 * @throws IOException If an I/O error occurs while reading the file.
	 */
	public byte[] imagePathToByteArray(Path imagePath) throws IOException {
		return Files.readAllBytes(imagePath);
	}

	/**
	 * Opens a file chooser dialog to select an image file. Filters the file
	 * selection to only allow image files (jpg, png, bmp, jpeg).
	 *
	 * @return The Path of the selected image file, or null if no file is selected.
	 */
	private Path getPathFromFileChooser() {
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setDialogTitle("Wähle eine Bild-Datei aus.");
		FileNameExtensionFilter pictureExtensionFilter = new FileNameExtensionFilter("Nur Bild Dateien", "jpg", "png",
				"bmp", "jpeg");
		fileChooser.addChoosableFileFilter(pictureExtensionFilter);

		if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile().toPath();
		}
		return null;
	}

	/**
	 * Validates the animal data before saving. Checks if all required fields are
	 * filled and valid.
	 *
	 * @param animalDTO The AnimalDTO object to validate.
	 * @return True if the animal data is valid, false otherwise.
	 */
	private boolean validateAnimal(AnimalDTO animalDTO) {
		return !animalDTO.getName().isEmpty() && animalDTO.getDateOfBirth() != null && animalDTO.getRoom() != null
				&& animalDTO.getGender() != null && animalDTO.getAnimalType() != null && getGenderFromRadio() >= 0;
	}

	/**
	 * Gets the selected gender from the radio button group.
	 *
	 * @return The index of the selected gender radio button (0 for male, 1 for
	 *         female, etc.), or -1 if no gender is selected.
	 */
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

	/**
	 * Sets the selected gender radio button based on the animal's gender.
	 */
	private void setRadioFromGender() {
		for (int i = 0; i < radioButtonList.size(); i++) {
			if (animal.getGender().getValue() == i) {
				radioButtonList.get(i).setSelected(true);
			}
		}
	}

	/**
	 * Clears the animal details form. Resets all input fields, combo boxes, radio
	 * buttons, and the image panel. Also clears the incident and examination lists
	 * and deselects any animal in the list.
	 */
	public void clearForm() {
		// Clear text fields
		animal = null;
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
		imagePanel.clearImageData();

		animalList.clearSelection();
		changeFormState(Mode.NONE);
	}

	/**
	 * Clears both fields that are associated with the search method
	 */
	public void clearSearch() {
		animalSearchField.setText("");
		animalTypeSelectionComboBox.setSelectedIndex(0);
	}

	/**
	 * Clears all fields in the form
	 */
	public void clearAll() {
		clearForm();
		clearSearch();
	}

	/**
	 * Selects an item in a combo box based on the ID of an entity. Iterates through
	 * the combo box items and selects the item whose ID matches the entity's ID.
	 *
	 * @param comboBox The combo box to search within.
	 * @param entity   The entity whose ID is used for selection.
	 * @param <T>      The type of the items in the combo box, which must extend
	 *                 EntityDTO.
	 */
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

	/**
	 * Refreshes a list model with new items. Clears the existing model and adds all
	 * the new items.
	 *
	 * @param model    The list model to refresh.
	 * @param newItems The new items to add to the model.
	 * @param <T>      The type of the items in the list model.
	 */
	public <T> void refreshListModel(DefaultListModel<T> model, List<T> newItems) {
		model.clear();
		model.addAll(newItems);
	}

	/**
	 * Refreshes a combo box model with new items. Removes all existing elements and
	 * adds the new items.
	 *
	 * @param model    The combo box model to refresh.
	 * @param newItems The new items to add to the model.
	 * @param <T>      The type of the items in the combo box model.
	 */
	public <T> void refreshBoxModel(DefaultComboBoxModel<T> model, ArrayList<T> newItems) {
		model.removeAllElements();
		for (T item : newItems) {
			model.addElement(item);
		}
	}

	/**
	 * Handles the "New" button press. Clears the form, sets the mode to create new
	 * animal, and updates the button and form states.
	 */
	private void onNewButtonPressed() {
		clearAll();
		changeFormState(Mode.CREATE);
		animal = new AnimalDTO();
	}

	/**
	 * Handles the "Cancel" button press. Resets the mode to neither edit nor
	 * create, and updates button and form states.
	 */
	private void onCancelButtonPressed() {
		if (animalList.getSelectedValue() == null) {
			changeFormState(Mode.NONE);
		} else {
			changeFormState(Mode.SELECTED);
		}
	}

	/**
	 * Handles the "Edit" button press. Sets the mode to edit existing animal, and
	 * updates button and form states.
	 */
	private void onEditButtonPressed() {
		changeFormState(Mode.EDIT);
	}

	/**
	 * Handles the "Upload Image" button press. Opens a file chooser to select an
	 * image, converts it to a byte array, sets it to the animal object and updates
	 * the image panel.
	 */
	private void onUploadImageButtonPressed() {
		try {
			byte[] imageAsByteArray = imagePathToByteArray(getPathFromFileChooser());
			animal.setImage(imageAsByteArray);
			imagePanel.setImageData(imageAsByteArray);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error bitte noch einmal versuchen.", "Validation Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Changes the state of the form components (text fields, combo boxes, buttons
	 * etc.) based on the mode being passed in.
	 * 
	 * @param mode The mode determining the state of each control.
	 */
	private void changeFormState(Mode mode) {
		switch (mode) {
		case NONE:
			animalList.setEnabled(true);
			editButton.setVisible(false);
			cancelButton.setVisible(false);
			saveButton.setVisible(false);
			newButton.setVisible(true);
			addIncidentButton.setEnabled(false);
			addExaminationButton.setEnabled(false);
			adoptionButton.setVisible(false);
			nameField.setEnabled(false);
			birthDateField.setEnabled(false);
			additionalInfoArea.setEnabled(false);
			for (ShelterRadioButton rb : radioButtonList) {
				rb.setEnabled(false);
			}
			animalTypeComboBox.setEnabled(false);
			patronComboBox.setEnabled(false);
			roomComboBox.setEnabled(false);
			uploadImageButton.setEnabled(false);
			animalSearchField.setEnabled(true);
			animalTypeSelectionComboBox.setEnabled(true);

			break;
		case SELECTED:
			animalList.setEnabled(true);
			editButton.setVisible(true);
			cancelButton.setVisible(false);
			saveButton.setVisible(false);
			newButton.setVisible(true);
			addIncidentButton.setEnabled(true);
			addExaminationButton.setEnabled(true);
			adoptionButton.setVisible(true);
			nameField.setEnabled(false);
			birthDateField.setEnabled(false);
			additionalInfoArea.setEnabled(false);
			for (ShelterRadioButton rb : radioButtonList) {
				rb.setEnabled(false);
			}
			animalTypeComboBox.setEnabled(false);
			patronComboBox.setEnabled(false);
			roomComboBox.setEnabled(false);
			uploadImageButton.setEnabled(false);
			animalSearchField.setEnabled(true);
			animalTypeSelectionComboBox.setEnabled(true);
			break;
		case EDIT:
			animalList.setEnabled(false);
			editButton.setVisible(false);
			cancelButton.setVisible(true);
			saveButton.setVisible(true);
			newButton.setVisible(false);
			addIncidentButton.setEnabled(true);
			addExaminationButton.setEnabled(true);
			adoptionButton.setVisible(false);
			nameField.setEnabled(true);
			birthDateField.setEnabled(true);
			additionalInfoArea.setEnabled(true);
			for (ShelterRadioButton rb : radioButtonList) {
				rb.setEnabled(true);
			}
			animalTypeComboBox.setEnabled(true);
			patronComboBox.setEnabled(true);
			roomComboBox.setEnabled(true);
			uploadImageButton.setEnabled(true);
			animalSearchField.setEnabled(false);
			animalTypeSelectionComboBox.setEnabled(false);
			break;
		case CREATE:
			animalList.setEnabled(false);
			editButton.setVisible(false);
			cancelButton.setVisible(true);
			saveButton.setVisible(true);
			newButton.setVisible(false);
			addIncidentButton.setEnabled(false);
			addExaminationButton.setEnabled(false);
			adoptionButton.setVisible(false);
			nameField.setEnabled(true);
			birthDateField.setEnabled(true);
			additionalInfoArea.setEnabled(true);
			for (ShelterRadioButton rb : radioButtonList) {
				rb.setEnabled(true);
			}
			animalTypeComboBox.setEnabled(true);
			patronComboBox.setEnabled(true);
			roomComboBox.setEnabled(true);
			uploadImageButton.setEnabled(true);
			animalSearchField.setEnabled(false);
			animalTypeSelectionComboBox.setEnabled(false);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + mode);
		}
	}
}
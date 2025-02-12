package gui.persons;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

import bl.DTOManager;
import bl.entities.CaretakerDTO;
import bl.entities.PersonDTO;
import gui.ShelterButton;
import gui.ShelterLabel;
import gui.ShelterList;
import gui.ShelterListCellRenderer;
import gui.ShelterPanel;
import gui.ShelterTextField;

public class CaretakerPanel extends ShelterPanel {
	private ShelterPanel mainContainer;
	private ShelterList<PersonDTO> personList;
	private DefaultListModel<PersonDTO> personListModel;

	private DTOManager dtoManager;

	private GridBagConstraints gbc = new GridBagConstraints();

	private ShelterPanel dataLayoutPanel;
	private ShelterLabel firstNameLabel;
	private ShelterTextField firstNameTextField;
	private ShelterLabel lastNameLabel;
	private ShelterTextField lastNameTextField;
	private ShelterLabel emailLabel;
	private ShelterTextField emailTextField;
	private ShelterLabel phoneLabel;
	private ShelterTextField phoneTextField;

	private ShelterPanel buttonLayoutPanel;
	private ShelterButton editButton;
	private ShelterButton deleteButton;
	private ShelterButton cancelButton;
	private ShelterButton saveButton;
	private ShelterButton newButton;

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

	public CaretakerPanel() {
		dtoManager = new DTOManager();

		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		mainContainer = new ShelterPanel();
		mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.X_AXIS));
		add(mainContainer);

		initializeListComponents();
		initializePersonDataComponents();
		initializeButtons();
	}

	/**
	 * Initializes the various components of the person list.
	 */
	private void initializeListComponents() {
		personListModel = new DefaultListModel<PersonDTO>();
		personListModel.addAll(dtoManager.loadCaretakers());
		personList = new ShelterList<PersonDTO>(personListModel);
		personList.setCellRenderer(new ShelterListCellRenderer());
		personList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		personList.setSelectedIndex(-1);

		personList.addListSelectionListener((ListSelectionEvent e) -> {
			onListSelectionChanged(e);
		});

		JScrollPane scrollPane = new JScrollPane(personList);
		mainContainer.add(scrollPane);
		scrollPane.setPreferredSize(new Dimension(250, 0));
		add(scrollPane, BorderLayout.WEST);
	}

	/**
	 * Initializes the various text fields for displaying person data.
	 */
	private void initializePersonDataComponents() {
		dataLayoutPanel = new ShelterPanel();
		dataLayoutPanel.setLayout(new GridBagLayout());
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		add(dataLayoutPanel, BorderLayout.CENTER);
		firstNameLabel = new ShelterLabel("Vorname:");
		firstNameTextField = new ShelterTextField();
		firstNameTextField.setPreferredSize(new Dimension(250, 30));
		firstNameTextField.setEnabled(false);

		lastNameLabel = new ShelterLabel("Nachname:");
		lastNameTextField = new ShelterTextField();
		lastNameTextField.setPreferredSize(new Dimension(250, 30));
		lastNameTextField.setEnabled(false);

		emailLabel = new ShelterLabel("E-Mail:");
		emailTextField = new ShelterTextField();
		emailTextField.setPreferredSize(new Dimension(250, 30));
		emailTextField.setEnabled(false);

		phoneLabel = new ShelterLabel("Telefon-Nr.:");
		phoneTextField = new ShelterTextField();
		phoneTextField.setPreferredSize(new Dimension(250, 30));
		phoneTextField.setEnabled(false);

		addLabelAndField(dataLayoutPanel, gbc, 0, firstNameLabel, firstNameTextField);
		addLabelAndField(dataLayoutPanel, gbc, 1, lastNameLabel, lastNameTextField);
		addLabelAndField(dataLayoutPanel, gbc, 2, emailLabel, emailTextField);
		addLabelAndField(dataLayoutPanel, gbc, 3, phoneLabel, phoneTextField);
	}

	/**
	 * Adds {@code label} and {@code field} next to each other in {@code panel}
	 * using the given GridBagConstraints.
	 * 
	 * @param panel The panel to which the elements should be added.
	 * @param gbc   The GridBagConstraints to be used.
	 * @param row   The specific row that should be used for {@code gbc}.
	 * @param label The label to be added.
	 * @param field The text field to be added.
	 */
	private void addLabelAndField(ShelterPanel panel, GridBagConstraints gbc, int row, ShelterLabel label,
			ShelterTextField field) {
		gbc.gridy = row;
		gbc.gridx = 0;
		panel.add(label, gbc);
		gbc.gridx = 1;
		panel.add(field, gbc);
	}

	/**
	 * Initializes the buttons used to control the form.
	 */
	private void initializeButtons() {
		buttonLayoutPanel = new ShelterPanel();
		mainContainer.add(buttonLayoutPanel);

		buttonLayoutPanel.setLayout(new BoxLayout(buttonLayoutPanel, BoxLayout.X_AXIS));
		add(buttonLayoutPanel, BorderLayout.PAGE_END);
		editButton = new ShelterButton(EDIT_BUTTON);
		editButton.addActionListener((ActionEvent _) -> {
			onEditButtonPressed();
		});
		editButton.setFocusable(false);
		editButton.setVisible(false);
		buttonLayoutPanel.add(editButton);
		buttonLayoutPanel.add(Box.createHorizontalGlue());

		deleteButton = new ShelterButton(DELETE_BUTTON);
		deleteButton.addActionListener((ActionEvent _) -> {
			onDeleteButtonPressed();
		});
		deleteButton.setFocusable(false);
		deleteButton.setVisible(false);
		buttonLayoutPanel.add(deleteButton);
		buttonLayoutPanel.add(Box.createHorizontalGlue());

		cancelButton = new ShelterButton(CANCEL_BUTTON);
		cancelButton.addActionListener((ActionEvent _) -> {
			onCancelButtonPressed();
		});
		cancelButton.setFocusable(false);
		cancelButton.setVisible(false);
		buttonLayoutPanel.add(cancelButton);
		buttonLayoutPanel.add(Box.createHorizontalGlue());

		saveButton = new ShelterButton(SAVE_BUTTON);
		saveButton.addActionListener((ActionEvent _) -> {
			onSaveButtonPressed();
		});
		saveButton.setFocusable(false);
		saveButton.setVisible(false);
		buttonLayoutPanel.add(saveButton);
		buttonLayoutPanel.add(Box.createHorizontalGlue());

		newButton = new ShelterButton(NEW_BUTTON);
		newButton.addActionListener((ActionEvent _) -> {
			onNewButtonPressed();
		});
		newButton.setFocusable(false);
		buttonLayoutPanel.add(newButton);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		dataLayoutPanel.add(buttonLayoutPanel, gbc);

		add(dataLayoutPanel, BorderLayout.CENTER);
	}

	/**
	 * Handles the event when the selection in the animal list changes. This method
	 * retrieves the selected person, fills the form with their details, and sets
	 * the form state to Mode.SELECTED.
	 *
	 * @param e The ListSelectionEvent object.
	 * @see Mode#SELECTED
	 */
	private void onListSelectionChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) {
			return;
		}

		fillForm();
		changeFormState(Mode.SELECTED);
	}

	/**
	 * Fills the person details form with the data of the currently selected person.
	 */
	private void fillForm() {
		CaretakerDTO selectedItem = (CaretakerDTO) personList.getSelectedValue();
		if (selectedItem == null) {
			return;
		}
		firstNameTextField.setText(selectedItem.getFirstName());
		lastNameTextField.setText(selectedItem.getLastName());
		emailTextField.setText(selectedItem.getEmail());
		phoneTextField.setText(selectedItem.getPhoneNumber());
	}

	/**
	 * Handles the "Edit" button press. Sets the form state to Mode.EDIT.
	 * 
	 * @see Mode#EDIT
	 */
	private void onEditButtonPressed() {
		changeFormState(Mode.EDIT);
	}

	/**
	 * Handles the "Delete" button press. Sets the form state to Mode.NONE.
	 * 
	 * @see Mode#NONE
	 */
	private void onDeleteButtonPressed() {
		CaretakerDTO selectedItem = (CaretakerDTO) personList.getSelectedValue();
		if (selectedItem == null) {
			return;
		}
		dtoManager.deleteCaretaker(selectedItem);

		personList.clearSelection();
		updateTableData();
		changeFormState(Mode.NONE);
	}

	/**
	 * Handles the "Cancel" button press. Sets the form state to Mode.SELECTED if a
	 * person is select, Mode.NODE otherwise.
	 * 
	 * @see Mode#SELECTED
	 * @see Mode#NONE
	 */
	private void onCancelButtonPressed() {
		if (personList.getSelectedValue() == null) {
			clearForm();
			changeFormState(Mode.NONE);
		} else {
			fillForm();
			changeFormState(Mode.SELECTED);
		}
	}

	/**
	 * Handles the "Save" button press. This method retrieves the values from the
	 * input fields, builds a new person object and saves it to the database. Sets
	 * the form state to Mode.NONE.
	 * 
	 * @see Mode#NONE
	 */
	private void onSaveButtonPressed() {
		CaretakerDTO activePerson = (CaretakerDTO) personList.getSelectedValue();
		if (firstNameTextField.getText().isBlank() || lastNameTextField.getText().isBlank()
				|| emailTextField.getText().isBlank() || phoneTextField.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Please fill all required fields correctly.", "Validation Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (activePerson == null) {
			activePerson = new CaretakerDTO(lastNameTextField.getText(), firstNameTextField.getText(),
					phoneTextField.getText(), emailTextField.getText());
		} else {
			activePerson.setFirstName(firstNameTextField.getText());
			activePerson.setLastName(lastNameTextField.getText());
			activePerson.setEmail(emailTextField.getText());
			activePerson.setPhoneNumber(phoneTextField.getText());
		}

		dtoManager.saveCaretaker(activePerson);

		personList.clearSelection();
		updateTableData();
		changeFormState(Mode.NONE);
	}

	/**
	 * Handles the "New" button press. Sets the form state to Mode.CREATE.
	 * 
	 * @see Mode#CREATE
	 */
	private void onNewButtonPressed() {
		clearForm();
		changeFormState(Mode.CREATE);
	}

	/**
	 * Gets all current person data from the database and updates the person list
	 * model with it.
	 */
	public void updateTableData() {
		personListModel = new DefaultListModel<PersonDTO>();
		personListModel.addAll(dtoManager.loadCaretakers());
		personList.setModel(personListModel);
		personList.clearSelection();
	}

	/**
	 * Clears and resets the entire form. Sets the form state to Mode.NONE.
	 * 
	 * @see Mode#NONE
	 */
	public void clearForm() {
		firstNameTextField.setText("");
		lastNameTextField.setText("");
		emailTextField.setText("");
		phoneTextField.setText("");
		personList.clearSelection();
		changeFormState(Mode.NONE);
	}

	/**
	 * Changes the state of the form components (text fields, buttons etc.) based on
	 * the mode being passed in.
	 * 
	 * @param mode The mode determining the state of each control.
	 */
	public void changeFormState(Mode mode) {
		switch (mode) {
		case NONE:
			personList.setEnabled(true);
			editButton.setVisible(false);
			deleteButton.setVisible(false);
			cancelButton.setVisible(false);
			saveButton.setVisible(false);
			newButton.setVisible(true);
			firstNameTextField.setEnabled(false);
			lastNameTextField.setEnabled(false);
			emailTextField.setEnabled(false);
			phoneTextField.setEnabled(false);
			break;
		case SELECTED:
			personList.setEnabled(true);
			editButton.setVisible(true);
			deleteButton.setVisible(true);
			cancelButton.setVisible(false);
			saveButton.setVisible(false);
			newButton.setVisible(true);
			firstNameTextField.setEnabled(false);
			lastNameTextField.setEnabled(false);
			emailTextField.setEnabled(false);
			phoneTextField.setEnabled(false);
			break;
		case EDIT:
			personList.setEnabled(false);
			editButton.setVisible(false);
			deleteButton.setVisible(false);
			cancelButton.setVisible(true);
			saveButton.setVisible(true);
			newButton.setVisible(false);
			firstNameTextField.setEnabled(true);
			lastNameTextField.setEnabled(true);
			emailTextField.setEnabled(true);
			phoneTextField.setEnabled(true);
			break;
		case CREATE:
			personList.setEnabled(false);
			editButton.setVisible(false);
			deleteButton.setVisible(false);
			cancelButton.setVisible(true);
			saveButton.setVisible(true);
			newButton.setVisible(false);
			firstNameTextField.setEnabled(true);
			lastNameTextField.setEnabled(true);
			emailTextField.setEnabled(true);
			phoneTextField.setEnabled(true);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + mode);
		}
	}
}

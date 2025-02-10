package gui;

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
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

import bl.DTOManager;
import bl.entities.VetDTO;
import bl.entities.PersonDTO;

public class VetPanel extends ShelterPanel {
	private boolean isInEditMode = false;
	private boolean isInCreateMode = false;
	
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

	public VetPanel() {
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

	private void initializeListComponents() {
		personListModel  = new DefaultListModel<PersonDTO>();
		personListModel.addAll(dtoManager.loadVets());
		personList = new ShelterList<PersonDTO>(personListModel);
		personList.setCellRenderer(new ShelterListCellRenderer());
		personList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		personList.setSelectedIndex(-1);

		personList.addListSelectionListener((ListSelectionEvent e) -> {
			onListSelectionChanged(e);
		});

		JScrollPane scrollPane = new JScrollPane(personList);
		mainContainer.add(scrollPane);
		scrollPane.setPreferredSize(new Dimension(150, 0));
		add(scrollPane, BorderLayout.WEST);
	}

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

	private void addLabelAndField(ShelterPanel panel, GridBagConstraints gbc, int row, ShelterLabel label, ShelterTextField field) {
        gbc.gridy = row;
        gbc.gridx = 0;
        panel.add(label, gbc);
        gbc.gridx = 1;
        panel.add(field, gbc);
    }

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

	private void onListSelectionChanged(ListSelectionEvent e) {
		if(e.getValueIsAdjusting()) {
			return;
		}
		
		changeButtonState();
		changeTextFieldState();
	}

	private void onEditButtonPressed() {
		isInEditMode = true;
		isInCreateMode = false;
		changeButtonState();
		changeTextFieldState();
	}

	private void onDeleteButtonPressed() {
		VetDTO selectedItem = (VetDTO)personList.getSelectedValue();
		if(selectedItem == null) {
			return;
		}
		dtoManager.deleteVet(selectedItem);
		
		personList.clearSelection();
		isInEditMode = false;
		isInCreateMode = false;
		changeButtonState();
		changeTextFieldState();
		updateTableData();
	}

	private void onCancelButtonPressed() {
		personList.clearSelection();
		isInEditMode = false;
		isInCreateMode = false;
		changeButtonState();
		changeTextFieldState();
	}

	private void onSaveButtonPressed() {
		VetDTO activePerson = (VetDTO)personList.getSelectedValue();
		if(firstNameTextField.getText().isBlank()) {
			return;
		}
		if(lastNameTextField.getText().isBlank()) {
			return;
		}
		if(emailTextField.getText().isBlank()) {
			return;
		}
		if(phoneTextField.getText().isBlank()) {
			return;
		}
		
		if(activePerson == null) {
			activePerson = new VetDTO(lastNameTextField.getText(), firstNameTextField.getText(), phoneTextField.getText(), emailTextField.getText());
		}
		else {
			activePerson.setFirstName(firstNameTextField.getText());
			activePerson.setLastName(lastNameTextField.getText());
			activePerson.setEmail(emailTextField.getText());
			activePerson.setPhoneNumber(phoneTextField.getText());
		}
		
		dtoManager.saveVet(activePerson);
		
		personList.clearSelection();
		isInEditMode = false;
		isInCreateMode = false;
		changeButtonState();
		changeTextFieldState();
		updateTableData();
	}

	private void onNewButtonPressed() {
		personList.clearSelection();
		isInEditMode = false;
		isInCreateMode = true;
		changeButtonState();
		changeTextFieldState();
	}

	public void changeButtonState() {
		boolean hasSelectedItem = personList.getSelectedIndex() >= 0;
		
		personList.setEnabled(!isInEditMode && !isInCreateMode);
		editButton.setVisible(hasSelectedItem && !isInEditMode && !isInCreateMode);
		deleteButton.setVisible(hasSelectedItem && !isInEditMode && !isInCreateMode);
		cancelButton.setVisible(isInEditMode || isInCreateMode);
		saveButton.setVisible(isInEditMode || isInCreateMode);
		newButton.setVisible(!isInEditMode && !isInCreateMode);
	}
	
	public void changeTextFieldState() {
		firstNameTextField.setEnabled(isInEditMode || isInCreateMode);
		lastNameTextField.setEnabled(isInEditMode || isInCreateMode);
		emailTextField.setEnabled(isInEditMode || isInCreateMode);
		phoneTextField.setEnabled(isInEditMode || isInCreateMode);
		
		VetDTO selectedItem = (VetDTO)personList.getSelectedValue();
		if(selectedItem == null || isInCreateMode) {
			firstNameTextField.setText("");
			lastNameTextField.setText("");
			emailTextField.setText("");
			phoneTextField.setText("");
		}
		else {
			firstNameTextField.setText(selectedItem.getFirstName());
			lastNameTextField.setText(selectedItem.getLastName());
			emailTextField.setText(selectedItem.getEmail());
			phoneTextField.setText(selectedItem.getPhoneNumber());
		}
	}
	
	public void updateTableData() {
		personListModel  = new DefaultListModel<PersonDTO>();
		personListModel.addAll(dtoManager.loadVets());
		personList.setModel(personListModel);
		personList.setSelectedIndex(-1);
	}
}


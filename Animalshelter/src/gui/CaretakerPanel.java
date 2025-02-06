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
import bl.entities.CaretakerDTO;
import bl.entities.PersonDTO;

public class CaretakerPanel extends ShelterPanel {
	private ShelterPanel mainContainer;
	private ShelterList<PersonDTO> caretakerList;
	private DefaultListModel<PersonDTO> caretakerListModel;
	
	DTOManager dtoManager;

	private GridBagConstraints gbc = new GridBagConstraints();
	
	private ShelterPanel dataLayoutPanel;
	private ShelterLabel firstNameLabel;
	private ShelterLabel firstNameDataLabel;
	private ShelterTextField firstNameTextField;
	private ShelterLabel lastNameLabel;
	private ShelterLabel lastNameDataLabel;
	private ShelterTextField lastNameTextField;
	private ShelterLabel emailLabel;
	private ShelterLabel emailDataLabel;
	private ShelterTextField emailTextField;
	private ShelterLabel phoneLabel;
	private ShelterLabel phoneDataLabel;
	private ShelterTextField phoneTextField;

	private ShelterPanel buttonLayoutPanel;
	private ShelterButton editButton;
	private ShelterButton deleteButton;
	private ShelterButton cancelButton;
	private ShelterButton saveButton;
	private ShelterButton newButton;
	
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

	private void initializeListComponents() {
		caretakerListModel  = new DefaultListModel<PersonDTO>();
		caretakerListModel.addAll(dtoManager.loadCaretakers());
		caretakerList = new ShelterList<PersonDTO>(caretakerListModel);
		caretakerList.setCellRenderer(new PersonListCellRenderer());
		caretakerList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		caretakerList.setSelectedIndex(-1);
		
		caretakerList.addListSelectionListener((ListSelectionEvent e) -> {
			onListSelectionChanged(e);
		});
		
		JScrollPane scrollPane = new JScrollPane(caretakerList);
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
		add(dataLayoutPanel, BorderLayout.LINE_END);
		firstNameLabel = new ShelterLabel("Vorname:");
		firstNameDataLabel = new ShelterLabel("");
		firstNameTextField = new ShelterTextField();
		firstNameTextField.setVisible(false);
		
		lastNameLabel = new ShelterLabel("Nachname:");
		lastNameDataLabel = new ShelterLabel("");
		lastNameTextField = new ShelterTextField();
		lastNameTextField.setVisible(false);
		
		emailLabel = new ShelterLabel("E-Mail:");
		emailDataLabel = new ShelterLabel("");
		emailTextField = new ShelterTextField();
		emailTextField.setVisible(false);
		
		phoneLabel = new ShelterLabel("Telefon-Nr.:");
		phoneDataLabel = new ShelterLabel("");
		phoneTextField = new ShelterTextField();
		phoneTextField.setVisible(false);
		
		addLabelAndField(dataLayoutPanel, gbc, 0, firstNameLabel, firstNameDataLabel, firstNameTextField);
        addLabelAndField(dataLayoutPanel, gbc, 1, lastNameLabel, lastNameDataLabel, lastNameTextField);
        addLabelAndField(dataLayoutPanel, gbc, 2, emailLabel, emailDataLabel, emailTextField);
        addLabelAndField(dataLayoutPanel, gbc, 3, phoneLabel, phoneDataLabel, phoneTextField);
	}
	
	private void addLabelAndField(ShelterPanel panel, GridBagConstraints gbc, int row, ShelterLabel label, ShelterLabel dataLabel, ShelterTextField field) {
        gbc.gridy = row;
        gbc.gridx = 0;
        panel.add(label, gbc);
        gbc.gridx = 1;
        panel.add(dataLabel, gbc);
        panel.add(field, gbc);
    }
	
	private void initializeButtons() {
		buttonLayoutPanel = new ShelterPanel();
		mainContainer.add(buttonLayoutPanel);
		
		buttonLayoutPanel.setLayout(new BoxLayout(buttonLayoutPanel, BoxLayout.X_AXIS));
		add(buttonLayoutPanel, BorderLayout.PAGE_END);
		editButton = new ShelterButton("Edit");
		editButton.addActionListener((ActionEvent _) -> {
			onEditButtonPressed();
		});
		editButton.setVisible(false);
		buttonLayoutPanel.add(editButton);
		buttonLayoutPanel.add(Box.createHorizontalGlue());

		deleteButton = new ShelterButton("Delete");
		deleteButton.addActionListener((ActionEvent _) -> {
			onDeleteButtonPressed();
		});
		deleteButton.setVisible(false);
		buttonLayoutPanel.add(deleteButton);
		buttonLayoutPanel.add(Box.createHorizontalGlue());
		
		cancelButton = new ShelterButton("Cancel");
		cancelButton.addActionListener((ActionEvent _) -> {
			onCancelButtonPressed();
		});
		cancelButton.setVisible(false);
		buttonLayoutPanel.add(cancelButton);
		buttonLayoutPanel.add(Box.createHorizontalGlue());
		
		saveButton = new ShelterButton("Save");
		saveButton.addActionListener((ActionEvent _) -> {
			onSaveButtonPressed();
		});
		saveButton.setVisible(false);
		buttonLayoutPanel.add(saveButton);
		buttonLayoutPanel.add(Box.createHorizontalGlue());
		
		newButton = new ShelterButton("New");
		newButton.addActionListener((ActionEvent _) -> {
			onNewButtonPressed();
		});
		buttonLayoutPanel.add(newButton);
        
		gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        dataLayoutPanel.add(buttonLayoutPanel, gbc);
        
        add(dataLayoutPanel, BorderLayout.CENTER);
	}

	private void onListSelectionChanged(ListSelectionEvent e) {
		PersonDTO selected = caretakerList.getSelectedValue();
		if(selected == null) {
			cancelButton.setVisible(false);
			editButton.setVisible(false);
			deleteButton.setVisible(false);
		}
		else {
			editButton.setVisible(true);
			deleteButton.setVisible(true);
			
			firstNameDataLabel.setText(selected.getFirstName());
			lastNameDataLabel.setText(selected.getLastName());
			emailDataLabel.setText(selected.getEmail());
			phoneDataLabel.setText(selected.getPhoneNumber());
		}
	}
	
	private void onEditButtonPressed() {
	}
	
	private void onDeleteButtonPressed() {
	}
	
	private void onCancelButtonPressed() {
		caretakerList.setSelectedIndex(-1);
		
		caretakerList.setEnabled(true);
		cancelButton.setVisible(false);
		saveButton.setVisible(false);
		
		firstNameDataLabel.setVisible(true);
		lastNameDataLabel.setVisible(true);
		emailDataLabel.setVisible(true);
		phoneDataLabel.setVisible(true);
		
		firstNameTextField.setVisible(false);
		lastNameTextField.setVisible(false);
		emailTextField.setVisible(false);
		phoneTextField.setVisible(false);
	}
	
	private void onSaveButtonPressed() {
	}
	
	private void onNewButtonPressed() {
		caretakerList.setSelectedIndex(-1);
		
		caretakerList.setEnabled(false);
		editButton.setVisible(false);
		deleteButton.setVisible(false);
		cancelButton.setVisible(true);
		saveButton.setVisible(true);
		
		firstNameDataLabel.setVisible(false);
		lastNameDataLabel.setVisible(false);
		emailDataLabel.setVisible(false);
		phoneDataLabel.setVisible(false);
		
		firstNameTextField.setVisible(true);
		lastNameTextField.setVisible(true);
		emailTextField.setVisible(true);
		phoneTextField.setVisible(true);
	}

	public void updateTableData() {
		caretakerListModel  = new DefaultListModel<PersonDTO>();
		caretakerListModel.addAll(dtoManager.loadCaretakers());
		caretakerList.setModel(caretakerListModel);
		caretakerList.setSelectedIndex(-1);
	}
}

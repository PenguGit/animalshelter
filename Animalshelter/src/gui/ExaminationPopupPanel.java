package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;

import bl.DTOManager;
import bl.entities.AnimalDTO;
import bl.entities.VetDTO;
import gui.animalview.ShelterBirthdateTextField;

public class ExaminationPopupPanel extends ShelterPanel {
	private DTOManager dtoManager;
	
	private GridBagConstraints gbc = new GridBagConstraints();
	
	private ShelterTextField titleTextField;
	private ShelterTextField descriptionTextField;
	private ShelterBirthdateTextField dateField;
	private ShelterComboBox<AnimalDTO> animalComboBox;
	private ShelterComboBox<VetDTO> vetComboBox;
	
	public ShelterButton cancelButton;
	public ShelterButton saveButton;
	
	public ExaminationPopupPanel() {
		dtoManager = new DTOManager();

		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

		initializeComponents();
	}

	private void initializeComponents() {;
		titleTextField = new ShelterTextField();
		titleTextField.setPreferredSize(new Dimension(250, 30));
	
		descriptionTextField = new ShelterTextField();
		descriptionTextField.setPreferredSize(new Dimension(250, 30));
		
		dateField = new ShelterBirthdateTextField();
		dateField.setPreferredSize(new Dimension(250, 30));
		
		DefaultComboBoxModel<AnimalDTO> animalComboBoxModel = new DefaultComboBoxModel<>();
		animalComboBoxModel.addAll(dtoManager.loadAnimals());
		animalComboBox = new ShelterComboBox<AnimalDTO>(animalComboBoxModel);
		animalComboBox.setRenderer(new PersonListCellRenderer());
		animalComboBox.setPreferredSize(new Dimension(250, 30));
		
		DefaultComboBoxModel<VetDTO> vetComboBoxModel = new DefaultComboBoxModel<>();
		vetComboBoxModel.addAll(dtoManager.loadVets());
		vetComboBox = new ShelterComboBox<VetDTO>(vetComboBoxModel);
		vetComboBox.setRenderer(new PersonListCellRenderer());
		vetComboBox.setPreferredSize(new Dimension(250, 30));
		
		gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        addLabelAndField(gbc, "Bezeichnung:", titleTextField, 0);
        addLabelAndField(gbc, "Beschreibung:", descriptionTextField, 1);
        addLabelAndField(gbc, "Datum:", dateField, 2);
        addLabelAndField(gbc, "Tier:", animalComboBox, 3);
        addLabelAndField(gbc, "Arzt:", vetComboBox, 4);
        
        gbc.gridy = 5;
        gbc.gridx = 1;
        cancelButton = new ShelterButton("Cancel");
        cancelButton.addActionListener((ActionEvent _) -> {
        	onCancelButtonPressed();
		});
        
        add(cancelButton, gbc);
        
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.EAST;
        saveButton = new ShelterButton("Save");
        saveButton.addActionListener((ActionEvent _) -> {
			onSaveButtonPressed();
		});
        
        add(saveButton, gbc);
	}
	
	private void addLabelAndField(GridBagConstraints gbc, String labelText, JComponent field, int yPos) {
        ShelterLabel label = new ShelterLabel(labelText);

        gbc.gridx = 0;
        gbc.gridy = yPos;
        gbc.gridwidth = 1;
        add(label, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(field, gbc);
    }
	
	
	
	private void onCancelButtonPressed() {
		
	}
	
	private void onSaveButtonPressed() {
		
	}
}


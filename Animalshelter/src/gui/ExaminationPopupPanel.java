package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

import bl.DTOManager;
import bl.entities.AnimalDTO;
import bl.entities.ExaminationDTO;
import bl.entities.VetDTO;
import gui.animalview.ShelterBirthdateTextField;

public class ExaminationPopupPanel extends ShelterPanel {
	private DTOManager dtoManager;
	
	private GridBagConstraints gbc = new GridBagConstraints();
	
	private ShelterTextField titleTextField;
	private ShelterTextField descriptionTextField;
	private ShelterBirthdateTextField dateField;
	private ShelterLabel animalLabel;
	private ShelterComboBox<VetDTO> vetComboBox;
	
	public ShelterButton cancelButton;
	public ShelterButton saveButton;
	
	private AnimalDTO animal;
	
	public ExaminationPopupPanel(AnimalDTO animal) {
		this.animal = animal;
		
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
		animalLabel = new ShelterLabel(animal.getName());
		animalLabel.setPreferredSize(new Dimension(250, 30));
		
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
        addLabelAndField(gbc, "Tier:", animalLabel, 3);
        addLabelAndField(gbc, "Arzt:", vetComboBox, 4);
        
        gbc.gridwidth = 1;
        gbc.gridy = 5;
        gbc.gridx = 0;
        cancelButton = new ShelterButton(CANCEL_BUTTON);
        
        add(cancelButton, gbc);
        
        gbc.gridx = 1;
        saveButton = new ShelterButton(SAVE_BUTTON);
        
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
	
	private boolean validateForm() {
		return !titleTextField.getText().isBlank() && !descriptionTextField.getText().isBlank() && dateField.getDate() != null && vetComboBox.getSelectedItem() != null;
	}
	
	public ExaminationDTO getExamination() {
		if(!validateForm()) {
			JOptionPane.showMessageDialog(null, "Please fill all required fields correctly.", "Validation Error",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}
		return new ExaminationDTO(titleTextField.getText(), dateField.getDate(), descriptionTextField.getText(), (VetDTO)vetComboBox.getSelectedItem(), animal);
	}
}


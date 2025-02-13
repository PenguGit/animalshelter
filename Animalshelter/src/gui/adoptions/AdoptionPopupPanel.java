package gui.adoptions;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

import bl.entities.AdopterDTO;
import bl.entities.AdoptionDTO;
import bl.entities.AnimalDTO;
import gui.ShelterBirthdateTextField;
import gui.ShelterButton;
import gui.ShelterLabel;
import gui.ShelterPanel;
import gui.ShelterTextField;

public class AdoptionPopupPanel extends ShelterPanel {
	
	private GridBagConstraints gbc = new GridBagConstraints();
	
	private ShelterTextField firstNameTextField;
	private ShelterTextField lastNameTextField;
	private ShelterTextField emailTextField;
	private ShelterTextField phoneTextField;
	private ShelterBirthdateTextField dateField;
	
	private ShelterLabel animalLabel;
	
	public ShelterButton cancelButton;
	public ShelterButton saveButton;
	
	private AnimalDTO animal;
	
	public AdoptionPopupPanel(AnimalDTO animal) {
		this.animal = animal;
		
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

		initializeComponents();
	}

	private void initializeComponents() {;
		firstNameTextField = new ShelterTextField();
		firstNameTextField.setPreferredSize(new Dimension(250, 30));
	
		lastNameTextField = new ShelterTextField();
		lastNameTextField.setPreferredSize(new Dimension(250, 30));
		
		emailTextField = new ShelterTextField();
		emailTextField.setPreferredSize(new Dimension(250, 30));
		
		phoneTextField = new ShelterTextField();
		phoneTextField.setPreferredSize(new Dimension(250, 30));
		
		dateField = new ShelterBirthdateTextField();
		dateField.setPreferredSize(new Dimension(250, 30));
		
		animalLabel = new ShelterLabel(animal.getName());
		animalLabel.setPreferredSize(new Dimension(250, 30));
		
		gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        addLabelAndField(gbc, "Vorname:", firstNameTextField, 0);
        addLabelAndField(gbc, "Nachname:", lastNameTextField, 1);
        addLabelAndField(gbc, "E-Mail:", emailTextField, 2);
        addLabelAndField(gbc, "Telefon-Nr.:", phoneTextField, 3);
        addLabelAndField(gbc, "Datum:", dateField, 4);
        addLabelAndField(gbc, "Tier:", animalLabel, 5);
        
        gbc.gridwidth = 1;
        gbc.gridy = 6;
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
		return !firstNameTextField.getText().isBlank() && !lastNameTextField.getText().isBlank() && !emailTextField.getText().isBlank() && !phoneTextField.getText().isBlank() && dateField.getDate() != null;
	}
	
	public AdoptionDTO getAdoption() {
		if(!validateForm()) {
			JOptionPane.showMessageDialog(null, "Please fill all required fields correctly.", "Validation Error",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}
		return new AdoptionDTO(dateField.getDate(), new AdopterDTO(lastNameTextField.getText(), firstNameTextField.getText(), phoneTextField.getText(), emailTextField.getText()), animal);
	}
}


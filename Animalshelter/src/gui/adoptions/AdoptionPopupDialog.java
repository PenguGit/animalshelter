package gui.adoptions;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import bl.DTOManager;
import bl.entities.AdopterDTO;
import bl.entities.AdoptionDTO;
import bl.entities.AnimalDTO;
import gui.ShelterBirthdateTextField;
import gui.ShelterButton;
import gui.ShelterLabel;
import gui.ShelterPanel;
import gui.ShelterTextField;
import gui.util.GUIConstants;

public class AdoptionPopupDialog extends JDialog implements GUIConstants {
	private DTOManager dtoManager;
	
	private GridBagConstraints gbc = new GridBagConstraints();
	
	private ShelterPanel panel;
	
	private ShelterTextField firstNameTextField;
	private ShelterTextField lastNameTextField;
	private ShelterTextField emailTextField;
	private ShelterTextField phoneTextField;
	private ShelterBirthdateTextField dateField;
	
	private ShelterLabel animalLabel;
	
	public ShelterButton cancelButton;
	public ShelterButton saveButton;
	
	private AnimalDTO animal;
	
	private boolean resultSuccess;
	
	/**
	 * Constructs a new adoption popup.
	 * @param animal The animal to which the adoption would apply.
	 * @param owner The frame from which the dialog was opened.
	 * @param title The dialog title.
	 * @param modal If true, the resulting popup is modal (i.e. it blocks on setVisible(true) until setVisible(false) and it blocks input to the underlying window.)
	 */
	public AdoptionPopupDialog(AnimalDTO animal, Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		
		this.animal = animal;
		
		setSize(200, 150);
		setLocationRelativeTo(owner);
		setResizable(false);
		
		dtoManager = new DTOManager();

		panel = new ShelterPanel();
		add(panel);
		
		panel.setLayout(new GridBagLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

		initializeComponents();
	}

	/**
	 * Initializes the various input fields for the panel and places them within the layout.
	 */
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
        cancelButton.addActionListener((_) -> {
        	onCancelButtonPressed();
        });
        
        panel.add(cancelButton, gbc);
        
        gbc.gridx = 1;
        saveButton = new ShelterButton(SAVE_BUTTON);
        saveButton.addActionListener((_) -> {
        	onSaveButtonPressed();
        });
        
        panel.add(saveButton, gbc);
	}
	
	/**
	 * A helper method to place fields with corresponding labels in a GridBagLayout.
	 * @param gbc The GridBagConstraints to use.
	 * @param labelText The label to place.
	 * @param field The field to place.
	 * @param yPos The row in the layout the elements should occupy.
	 */
	private void addLabelAndField(GridBagConstraints gbc, String labelText, JComponent field, int yPos) {
        ShelterLabel label = new ShelterLabel(labelText);

        gbc.gridx = 0;
        gbc.gridy = yPos;
        gbc.gridwidth = 1;
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(field, gbc);
    }
	
	/**
	 * Closes the dialog without saving anything, indirectly reporting a failure result in the process.
	 */
	private void onCancelButtonPressed() {
		resultSuccess = false;
		setVisible(false);
		dispose();
	}
	
	/**
	 * Validates the form input fields, saves the resulting objects to the database, then sets the result and closes the dialog.
	 */
	private void onSaveButtonPressed() {
		if(!validateForm()) {
			JOptionPane.showMessageDialog(this, "Please fill all required fields correctly.", "Validation Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		AdopterDTO adopter = new AdopterDTO(lastNameTextField.getText(), firstNameTextField.getText(), phoneTextField.getText(), emailTextField.getText());
		dtoManager.saveAdopter(adopter);
		
		AdoptionDTO adoption = new AdoptionDTO(dateField.getDate(), adopter, animal);
		dtoManager.saveAdoption(adoption);

		resultSuccess = true;
		setVisible(false);
		dispose();
	}
	
	/**
     * Validates the adoption data before saving.
     * Checks if all required fields are filled and valid.
     *
     * @return True if the adoption data is valid, false otherwise.
     */
	private boolean validateForm() {
		return !firstNameTextField.getText().isBlank() && !lastNameTextField.getText().isBlank() && !emailTextField.getText().isBlank() && !phoneTextField.getText().isBlank() && dateField.getDate() != null;
	}
	
	/**
	 * Shows the dialog and (assuming it is modal) returns success or failure of the dialogs' operation AFTER setVisible(false) has been called.
	 * 
	 * @return True if the new adopter and adoption were saved successfully, false otherwise.
	 */
	public boolean showDialog() {
		setVisible(true);
		return resultSuccess;
	}
}


package gui.events;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import bl.DTOManager;
import bl.entities.AnimalDTO;
import bl.entities.ExaminationDTO;
import bl.entities.VetDTO;
import gui.ShelterBirthdateTextField;
import gui.ShelterButton;
import gui.ShelterComboBox;
import gui.ShelterLabel;
import gui.ShelterListCellRenderer;
import gui.ShelterPanel;
import gui.ShelterTextArea;
import gui.ShelterTextField;
import gui.util.GUIConstants;

public class ExaminationPopupDialog extends JDialog implements GUIConstants {
	private DTOManager dtoManager;
	
	private GridBagConstraints gbc = new GridBagConstraints();
	
	private ShelterPanel panel;
	
	private ShelterTextField titleTextField;
	private ShelterTextArea descriptionTextArea;
	private ShelterBirthdateTextField dateField;
	private ShelterLabel animalLabel;
	private ShelterComboBox<VetDTO> vetComboBox;
	
	private ShelterButton cancelButton;
	private ShelterButton saveButton;
	
	private AnimalDTO animal;
	
	private boolean resultSuccess;
	
	/**
	 * Constructs a new examination popup.
	 * @param animal The animal to which the examination would apply.
	 * @param owner The frame from which the dialog was opened.
	 * @param title The dialog title.
	 * @param modal If true, the resulting popup is modal (i.e. it blocks on setVisible(true) until setVisible(false) and it blocks input to the underlying window.)
	 */
	public ExaminationPopupDialog(AnimalDTO animal, Frame owner, String title, boolean modal) {
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
		titleTextField = new ShelterTextField();
		titleTextField.setPreferredSize(new Dimension(250, 30));
	
		descriptionTextArea = new ShelterTextArea();
		JScrollPane descriptionTextAreaScrollPane = new JScrollPane(descriptionTextArea);
		descriptionTextAreaScrollPane.setPreferredSize(new Dimension(250, 120));
		
		dateField = new ShelterBirthdateTextField();
		dateField.setPreferredSize(new Dimension(250, 30));
		
		animalLabel = new ShelterLabel(animal.getName());
		animalLabel.setPreferredSize(new Dimension(250, 30));
		
		DefaultComboBoxModel<VetDTO> vetComboBoxModel = new DefaultComboBoxModel<>();
		vetComboBoxModel.addAll(dtoManager.loadVets());
		vetComboBox = new ShelterComboBox<VetDTO>(vetComboBoxModel);
		vetComboBox.setRenderer(new ShelterListCellRenderer());
		vetComboBox.setPreferredSize(new Dimension(250, 30));
		
		gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        addLabelAndField(gbc, "Bezeichnung:", titleTextField, 0, 1);
        addLabelAndField(gbc, "Beschreibung:", descriptionTextAreaScrollPane, 1, 3);
        addLabelAndField(gbc, "Datum:", dateField, 4, 1);
        addLabelAndField(gbc, "Tier:", animalLabel, 5, 1);
        addLabelAndField(gbc, "Arzt:", vetComboBox, 6, 1);
        
        gbc.gridwidth = 1;
        gbc.gridy = 7;
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
	 * @param height The number of rows the field should occupy. Usually 1.
	 */
	private void addLabelAndField(GridBagConstraints gbc, String labelText, JComponent field, int yPos, int height) {
        ShelterLabel label = new ShelterLabel(labelText);

        gbc.gridx = 0;
        gbc.gridy = yPos;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = height;
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
	 * Validates the form input fields, saves the resulting object to the database, then sets the result and closes the dialog.
	 */
	private void onSaveButtonPressed() {
		if(!validateForm()) {
			JOptionPane.showMessageDialog(this, "Please fill all required fields correctly.", "Validation Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		ExaminationDTO examination = new ExaminationDTO(titleTextField.getText(), dateField.getDate(), descriptionTextArea.getText(), (VetDTO)vetComboBox.getSelectedItem(), animal);
		
		dtoManager.saveExamination(examination);

		resultSuccess = true;
		setVisible(false);
		dispose();
	}
	
	/**
     * Validates the examination data before saving.
     * Checks if all required fields are filled and valid.
     *
     * @return True if the examination data is valid, false otherwise.
     */
	private boolean validateForm() {
		return !titleTextField.getText().isBlank() && !descriptionTextArea.getText().isBlank() && dateField.getDate() != null && vetComboBox.getSelectedItem() != null;
	}
	
	/**
	 * Shows the dialog and (assuming it is modal) returns success or failure of the dialogs' operation AFTER setVisible(false) has been called.
	 * 
	 * @return True if the new examination was saved successfully, false otherwise.
	 */
	public boolean showDialog() {
		setVisible(true);
		return resultSuccess;
	}
}


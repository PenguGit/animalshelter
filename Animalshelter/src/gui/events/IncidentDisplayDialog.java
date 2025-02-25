package gui.events;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JScrollPane;

import bl.DTOManager;
import bl.entities.IncidentDTO;
import gui.ShelterBirthdateTextField;
import gui.ShelterButton;
import gui.ShelterLabel;
import gui.ShelterPanel;
import gui.ShelterTextArea;
import gui.ShelterTextField;
import gui.util.GUIConstants;

public class IncidentDisplayDialog extends JDialog implements GUIConstants {
	private GridBagConstraints gbc = new GridBagConstraints();
	
	private ShelterPanel panel;
	
	private ShelterTextField titleTextField;
	private ShelterTextArea descriptionTextArea;
	private ShelterBirthdateTextField dateField;
	private ShelterLabel animalLabel;
	private ShelterLabel caretakerLabel;
	
	private ShelterButton closeButton;
	
	private IncidentDTO incident;
	
	/**
	 * Constructs a new examination display popup.
	 * @param examination The examination data to display.
	 * @param owner The frame from which the dialog was opened.
	 * @param title The dialog title.
	 * @param modal If true, the resulting popup is modal (i.e. it blocks on setVisible(true) until setVisible(false) and it blocks input to the underlying window.)
	 */
	public IncidentDisplayDialog(IncidentDTO incident, Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		
		this.incident = incident;
		
		setSize(200, 150);
		setLocationRelativeTo(owner);
		setResizable(false);

		panel = new ShelterPanel();
		add(panel);
		
		panel.setLayout(new GridBagLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

		initializeComponents();
		fillForm();
	}

	/**
	 * Initializes the various input fields for the panel and places them within the layout.
	 */
	private void initializeComponents() {;
		titleTextField = new ShelterTextField();
		titleTextField.setPreferredSize(new Dimension(250, 30));
		titleTextField.setEnabled(false);
	
		descriptionTextArea = new ShelterTextArea();
		JScrollPane descriptionTextAreaScrollPane = new JScrollPane(descriptionTextArea);
		descriptionTextAreaScrollPane.setPreferredSize(new Dimension(250, 120));
		descriptionTextArea.setEnabled(false);
		
		dateField = new ShelterBirthdateTextField();
		dateField.setPreferredSize(new Dimension(250, 30));
		dateField.setEnabled(false);
		
		animalLabel = new ShelterLabel();
		animalLabel.setPreferredSize(new Dimension(250, 30));
		
		caretakerLabel = new ShelterLabel();
		caretakerLabel.setPreferredSize(new Dimension(250, 30));
		
		gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        addLabelAndField(gbc, "Bezeichnung:", titleTextField, 0, 1);
        addLabelAndField(gbc, "Beschreibung:", descriptionTextAreaScrollPane, 1, 3);
        addLabelAndField(gbc, "Datum:", dateField, 4, 1);
        addLabelAndField(gbc, "Tier:", animalLabel, 5, 1);
        addLabelAndField(gbc, "Pfleger:", caretakerLabel, 6, 1);
        
        gbc.gridwidth = 1;
        gbc.gridy = 7;
        gbc.gridx = 0;
        closeButton = new ShelterButton(CANCEL_BUTTON);
        closeButton.addActionListener((_) -> {
        	onCloseButtonPressed();
        });
        
        panel.add(closeButton, gbc);
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
	private void onCloseButtonPressed() {
		setVisible(false);
		dispose();
	}
	
	/**
	 * Shows the dialog and (assuming it is modal) returns success or failure of the dialogs' operation AFTER setVisible(false) has been called.
	 * 
	 * @return True if the new examination was saved successfully, false otherwise.
	 */
	public void showDialog() {
		setVisible(true);
	}
	
	/**
	 * Fills the form with values from the examination with which it was initialized.
	 */
	private void fillForm() {
		titleTextField.setText(incident.getTitle());
		descriptionTextArea.setText(incident.getDescription());
		dateField.setDate(incident.getDate());
		animalLabel.setText(incident.getAnimal().getName());
		caretakerLabel.setText(incident.getCaretaker().getLastName() + ", " + incident.getCaretaker().getFirstName());
	}
}


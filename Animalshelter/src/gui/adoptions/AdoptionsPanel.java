package gui.adoptions;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

import bl.DTOManager;
import bl.entities.AdopterDTO;
import bl.entities.AdoptionDTO;
import gui.ShelterLabel;
import gui.ShelterList;
import gui.ShelterListCellRenderer;
import gui.ShelterPanel;

public class AdoptionsPanel extends ShelterPanel {
	private ShelterList<AdoptionDTO> animalsList;
	private DefaultListModel<AdoptionDTO> animalsListModel;
	
	private ShelterPanel dataLayoutPanel;
	private ShelterLabel firstNameLabel;
	private ShelterLabel firstNameTextLabel;
	private ShelterLabel lastNameLabel;
	private ShelterLabel lastNameTextLabel;
	private ShelterLabel emailLabel;
	private ShelterLabel emailTextLabel;
	private ShelterLabel phoneLabel;
	private ShelterLabel phoneTextLabel;
	private ShelterLabel dateLabel;
	private ShelterLabel dateTextLabel;
	
	private DTOManager dtoManager;

	private GridBagConstraints gbc = new GridBagConstraints();
	
	public AdoptionsPanel() {
		dtoManager = new DTOManager();

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

		initializeAnimalsList();
		initializePersonDataComponents();
	}
	
	/**
	 * Initializes and places the list of animals.
	 */
	private void initializeAnimalsList() {
		animalsListModel  = new DefaultListModel<AdoptionDTO>();
		animalsListModel.addAll(dtoManager.loadAdoptions());
		animalsList = new ShelterList<AdoptionDTO>(animalsListModel);
		animalsList.setCellRenderer(new ShelterListCellRenderer());
		animalsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		animalsList.addListSelectionListener((ListSelectionEvent e) -> {
			onAnimalsListSelectionChanged(e);
		});
		
		JScrollPane animalsScrollPane = new JScrollPane(animalsList);
		animalsScrollPane.setPreferredSize(new Dimension(400, 0));
		add(animalsScrollPane);
	}

	/**
	 * Initializes and places the various components that make up the adoption data for a given animal.
	 */
	private void initializePersonDataComponents() {
		dataLayoutPanel = new ShelterPanel();
		dataLayoutPanel.setLayout(new GridBagLayout());
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        add(dataLayoutPanel, BorderLayout.CENTER);
		firstNameLabel = new ShelterLabel("Vorname:");
		firstNameTextLabel = new ShelterLabel();
		firstNameTextLabel.setPreferredSize(new Dimension(250, 30));

		lastNameLabel = new ShelterLabel("Nachname:");
		lastNameTextLabel = new ShelterLabel();
		lastNameTextLabel.setPreferredSize(new Dimension(250, 30));

		emailLabel = new ShelterLabel("E-Mail:");
		emailTextLabel = new ShelterLabel();
		emailTextLabel.setPreferredSize(new Dimension(250, 30));

		phoneLabel = new ShelterLabel("Telefon-Nr.:");
		phoneTextLabel = new ShelterLabel();
		phoneTextLabel.setPreferredSize(new Dimension(250, 30));
		
		dateLabel = new ShelterLabel("Datum:");
		dateTextLabel = new ShelterLabel();
		dateTextLabel.setPreferredSize(new Dimension(250, 30));

		addLabels(dataLayoutPanel, gbc, 0, firstNameLabel, firstNameTextLabel);
		addLabels(dataLayoutPanel, gbc, 1, lastNameLabel, lastNameTextLabel);
        addLabels(dataLayoutPanel, gbc, 2, emailLabel, emailTextLabel);
        addLabels(dataLayoutPanel, gbc, 3, phoneLabel, phoneTextLabel);
        addLabels(dataLayoutPanel, gbc, 4, dateLabel, dateTextLabel);
	}

	/**
	 * A helper method to place fields with corresponding labels in a GridBagLayout.
	 * @param panel The panel to which they should be added.
	 * @param gbc The GridBagConstraints to use.
	 * @param label The label to place.
	 * @param field The field to place.
	 * @param row The row in the layout the elements should occupy.
	 */
	private void addLabels(ShelterPanel panel, GridBagConstraints gbc, int row, ShelterLabel label, ShelterLabel field) {
        gbc.gridy = row;
        gbc.gridx = 0;
        panel.add(label, gbc);
        gbc.gridx = 1;
        panel.add(field, gbc);
    }
	
	/**
	 * Called when a different animal is selected, to update the corresponding adoption data.
	 * @param e The event fired by the list change.
	 */
	private void onAnimalsListSelectionChanged(ListSelectionEvent e) {
		if(e.getValueIsAdjusting()) {
			return;
		}
		
		fillAdoptionData();
	}
	
	/**
	 * Fills in the adoption data for the currently selected animal, if any.
	 */
	private void fillAdoptionData() {
		AdoptionDTO selectedAdoption = animalsList.getSelectedValue();
		
		if(selectedAdoption == null) {
			return;
		}
		
		AdopterDTO adopter = selectedAdoption.getAdopter();
		firstNameTextLabel.setText(adopter.getFirstName());
		lastNameTextLabel.setText(adopter.getLastName());
		emailTextLabel.setText(adopter.getEmail());
		phoneTextLabel.setText(adopter.getPhoneNumber());
		dateTextLabel.setText(selectedAdoption.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
	}
	
	/**
	 * Updates the animal list with data from the database.
	 */
	public void updateAnimalsListData() {
		animalsListModel.clear();
		animalsListModel.addAll(dtoManager.loadAdoptions());
		animalsList.clearSelection();
	}
}


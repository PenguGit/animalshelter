package gui;

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

	public ShelterList<AdoptionDTO> getAnimalsList() {
		return animalsList;
	}
	
	private void initializeAnimalsList() {
		animalsListModel  = new DefaultListModel<AdoptionDTO>();
		animalsListModel.addAll(dtoManager.loadAdoptions());
		animalsList = new ShelterList<AdoptionDTO>(animalsListModel);
		animalsList.setCellRenderer(new ShelterListCellRenderer());
		animalsList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		
		animalsList.addListSelectionListener((ListSelectionEvent e) -> {
			onAnimalsListSelectionChanged(e);
		});
		
		JScrollPane animalsScrollPane = new JScrollPane(animalsList);
		animalsScrollPane.setPreferredSize(new Dimension(400, 0));
		add(animalsScrollPane);
	}

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

	private void addLabels(ShelterPanel panel, GridBagConstraints gbc, int row, ShelterLabel label, ShelterLabel field) {
        gbc.gridy = row;
        gbc.gridx = 0;
        panel.add(label, gbc);
        gbc.gridx = 1;
        panel.add(field, gbc);
    }
	
	private void onAnimalsListSelectionChanged(ListSelectionEvent e) {
		if(e.getValueIsAdjusting()) {
			return;
		}
		
		fillAdoptionData();
	}
	
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
	
	public void updateAnimalsListData() {
		animalsListModel.clear();
		animalsListModel.addAll(dtoManager.loadAdoptions());
		animalsList.clearSelection();
	}
}


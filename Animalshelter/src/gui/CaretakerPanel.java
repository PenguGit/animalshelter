package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;

import bl.DTOManager;
import bl.entities.AdopterDTO;
import bl.entities.CaretakerDTO;

public class CaretakerPanel extends ShelterPanel {
	private ShelterPanel mainContainer;
	private ShelterList<CaretakerDTO> caretakerList;
	private DefaultListModel<CaretakerDTO> caretakerListModel;
	DTOManager dtoManager;
	
	private ShelterPanel buttonLayoutPanel;
	private ShelterButton editButton;
	private ShelterButton deleteButton;
	
	public CaretakerPanel() {
		dtoManager = new DTOManager();
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		mainContainer = new ShelterPanel();
		mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.X_AXIS));
		add(mainContainer);
		
		initializeListComponents();
		initializeButtons();
	}

	private void initializeListComponents() {
		caretakerListModel  = new DefaultListModel<CaretakerDTO>();
		caretakerListModel.addAll(dtoManager.loadCaretakers());
		caretakerList = new ShelterList<CaretakerDTO>(caretakerListModel);
		caretakerList.setSelectedIndex(-1);
		
		JScrollPane scrollPane = new JScrollPane(caretakerList);
		mainContainer.add(scrollPane);
	}
	
	private void initializeButtons() {
		buttonLayoutPanel = new ShelterPanel();
		mainContainer.add(buttonLayoutPanel);
		
		editButton = new ShelterButton("Edit");
		editButton.addActionListener((ActionEvent _) -> {
			onEditButtonPressed();
		});
		buttonLayoutPanel.add(editButton);
		
		deleteButton = new ShelterButton("Delete");
		deleteButton.addActionListener((ActionEvent _) -> {
			onDeleteButtonPressed();
		});
		buttonLayoutPanel.add(deleteButton);
	}

	private void onEditButtonPressed() {
	}
	
	private void onDeleteButtonPressed() {
	}

	public void updateTableData() {
		caretakerListModel  = new DefaultListModel<CaretakerDTO>();
		caretakerListModel.addAll(dtoManager.loadCaretakers());
		caretakerList.setModel(caretakerListModel);
		caretakerList.setSelectedIndex(-1);
	}
}

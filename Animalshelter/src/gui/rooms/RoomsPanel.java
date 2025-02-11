package gui.rooms;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

import bl.DTOManager;
import bl.entities.AnimalDTO;
import bl.entities.RoomDTO;
import gui.ShelterList;
import gui.ShelterListCellRenderer;
import gui.ShelterPanel;

public class RoomsPanel extends ShelterPanel {
	private ShelterList<RoomDTO> roomsList;
	private DefaultListModel<RoomDTO> roomsListModel;

	private ShelterList<AnimalDTO> animalsList;
	private DefaultListModel<AnimalDTO> animalsListModel;
	
	private ArrayList<AnimalDTO> animals;
	
	private DTOManager dtoManager;

	public RoomsPanel() {
		dtoManager = new DTOManager();

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

		animals = dtoManager.loadAnimals();

		initializeRoomsList();
		add(Box.createHorizontalGlue());
		initializeAnimalsList();
	}

	public ShelterList<AnimalDTO> getAnimalsList() {
		return animalsList;
	}

	private void initializeRoomsList() {
		roomsListModel  = new DefaultListModel<RoomDTO>();
		roomsList = new ShelterList<RoomDTO>(roomsListModel);
		updateRoomsListData();
		roomsList.setCellRenderer(new ShelterListCellRenderer());
		roomsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		roomsList.addListSelectionListener((ListSelectionEvent e) -> {
			onRoomsListSelectionChanged(e);
		});

		JScrollPane roomsScrollPane = new JScrollPane(roomsList);
		roomsScrollPane.setPreferredSize(new Dimension(400, 0));
		add(roomsScrollPane);
	}
	
	private void initializeAnimalsList() {
		animalsListModel  = new DefaultListModel<AnimalDTO>();
		animalsList = new ShelterList<AnimalDTO>(animalsListModel);
		animalsList.setCellRenderer(new ShelterListCellRenderer());
		animalsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane animalsScrollPane = new JScrollPane(animalsList);
		animalsScrollPane.setPreferredSize(new Dimension(400, 0));
		add(animalsScrollPane);
	}

	private void onRoomsListSelectionChanged(ListSelectionEvent e) {
		if(e.getValueIsAdjusting()) {
			return;
		}
		
		updateAnimalsListData();
	}
	
	public void updateRoomsListData() {
		roomsListModel.clear();
		roomsListModel.addAll(dtoManager.loadRooms());
		roomsList.clearSelection();
		animals = dtoManager.loadAnimals();
	}
	
	private void updateAnimalsListData() {
		RoomDTO selectedRoom = roomsList.getSelectedValue();
		
		animalsListModel.clear();
		if(selectedRoom == null) {
			return;
		}
		
		animalsListModel.addAll(animals.stream().filter(e -> e.getRoom().getId() == selectedRoom.getId()).collect(Collectors.toList()));
	}
}


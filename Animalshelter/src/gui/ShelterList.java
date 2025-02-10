package gui;

import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import bl.entities.EntityDTO;

public class ShelterList<E extends EntityDTO> extends JList<E> implements GUIConstants {
	public ShelterList(ListModel<E> dataModel) {
		super(dataModel);
		setCellRenderer(new ShelterListCellRenderer());
		setFont(FONT_LIST);
		setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	}
	public ShelterList() {
		super();
		setCellRenderer(new ShelterListCellRenderer());
		setFont(FONT_LIST);
		setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	}
}

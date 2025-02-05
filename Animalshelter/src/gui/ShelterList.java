package gui;

import javax.swing.JList;
import javax.swing.ListModel;

import bl.entities.EntityDTO;

public class ShelterList<E extends EntityDTO> extends JList<E> {
	public ShelterList(ListModel<E> dataModel) {
		super(dataModel);
	}
}

package gui;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

import bl.entities.EntityDTO;

public class ShelterComboBox<E extends EntityDTO> extends JComboBox<E> {
	public ShelterComboBox(ComboBoxModel<E> dataModel) {
		super(dataModel);
		setRenderer(new ShelterListCellRenderer());
	}
	public ShelterComboBox() {
		super();
		setRenderer(new ShelterListCellRenderer());
	}
}

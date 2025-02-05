package gui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import bl.entities.PersonDTO;

public class PersonListCellRenderer extends DefaultListCellRenderer {
	@Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof PersonDTO personDTO) {
            setText(personDTO.getLastName() + ", " + personDTO.getFirstName());
        }
        return this;
    }
}

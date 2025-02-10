package gui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import bl.entities.AnimalDTO;
import bl.entities.AnimalTypeDTO;
import bl.entities.ExaminationDTO;
import bl.entities.IncidentDTO;
import bl.entities.PersonDTO;
import bl.entities.RoomDTO;

public class ShelterListCellRenderer extends DefaultListCellRenderer {
	@Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof PersonDTO personDTO) {
            setText(personDTO.getLastName() + ", " + personDTO.getFirstName());
        }
        if (value instanceof AnimalDTO AnimalDTO) {
            setText(AnimalDTO.getName());
        }
        if (value instanceof IncidentDTO IncidentDTO) {
            setText(IncidentDTO.getDate().toString() + " " + IncidentDTO.getTitle());
        }
        if (value instanceof ExaminationDTO ExaminationDTO) {
            setText(ExaminationDTO.getDate().toString() + " " + ExaminationDTO.getTitle());
        }
        if (value instanceof RoomDTO RoomDTO) {
            setText(RoomDTO.getName());
        }
        if (value instanceof AnimalTypeDTO AnimalTypeDTO) {
            setText(AnimalTypeDTO.getName());
        }
        return this;
    }
}

package gui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import bl.entities.AdoptionDTO;
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
        if (value instanceof AnimalDTO animalDTO) {
            setText(animalDTO.getName());
        }
        if (value instanceof IncidentDTO incidentDTO) {
            setText(incidentDTO.getDate().toString() + " " + incidentDTO.getTitle());
        }
        if (value instanceof ExaminationDTO examinationDTO) {
            setText(examinationDTO.getDate().toString() + " " + examinationDTO.getTitle());
        }
        if (value instanceof RoomDTO roomDTO) {
            setText(roomDTO.getName());
        }
        if (value instanceof AnimalTypeDTO animalTypeDTO) {
            setText(animalTypeDTO.getName());
        }
        if (value instanceof AdoptionDTO adoptionDTO) {
            setText(adoptionDTO.getAnimal().getName());
        }
        return this;
    }
}

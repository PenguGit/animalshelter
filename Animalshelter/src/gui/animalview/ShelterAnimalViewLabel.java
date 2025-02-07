package gui.animalview;

import bl.entities.AnimalTypeDTO;
import gui.ShelterLabel;

public class ShelterAnimalViewLabel extends ShelterLabel {
	
	AnimalTypeDTO animalTypeDTO;
	
	public ShelterAnimalViewLabel(AnimalTypeDTO animalType) {
		super(animalType.getName());
		animalTypeDTO = animalType;
	}
	
}

package bl.entities;

import java.time.LocalDate;

public class AdoptionDTO extends EntityDTO {
	private LocalDate date;
	private AdopterDTO adopter;
	private AnimalDTO animal;
	
	public AdoptionDTO(LocalDate date, AdopterDTO adopter, AnimalDTO animal) {
		this.date = date;
		this.adopter = adopter;
		this.animal = animal;
	}
	
	public AdoptionDTO(int id, LocalDate date, AdopterDTO adopter, AnimalDTO animal) {
		this.id = id;
		this.date = date;
		this.adopter = adopter;
		this.animal = animal;
	}
	
	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public AdopterDTO getAdopter() {
		return adopter;
	}

	public void setAdopter(AdopterDTO adopter) {
		this.adopter = adopter;
	}

	public AnimalDTO getAnimal() {
		return animal;
	}

	public void setAnimal(AnimalDTO animal) {
		this.animal = animal;
	}
}

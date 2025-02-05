package bl.entities;

import java.time.LocalDate;

public class ExaminationDTO extends EntityDTO {
	private String title;
	private LocalDate date;
	private String description;
	private VetDTO vet;
	private AnimalDTO animal;
	
	public ExaminationDTO(String title, LocalDate date, String description, VetDTO vet, AnimalDTO animal) {
		this.title = title;
		this.date = date;
		this.description = description;
		this.vet = vet;
		this.animal = animal;
	}
	
	public ExaminationDTO(int id, String title, LocalDate date, String description, VetDTO vet, AnimalDTO animal) {
		this.id = id;
		this.title = title;
		this.date = date;
		this.description = description;
		this.vet = vet;
		this.animal = animal;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public VetDTO getVet() {
		return vet;
	}

	public void setVet(VetDTO vet) {
		this.vet = vet;
	}

	public AnimalDTO getAnimal() {
		return animal;
	}

	public void setAnimal(AnimalDTO animal) {
		this.animal = animal;
	}
}

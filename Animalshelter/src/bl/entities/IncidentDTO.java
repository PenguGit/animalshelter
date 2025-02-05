package bl.entities;

import java.time.LocalDate;

public class IncidentDTO extends EntityDTO {
	private String title;
	private LocalDate date;
	private String description;
	private CaretakerDTO caretaker;
	private AnimalDTO animal;
	
	public IncidentDTO(String title, LocalDate date, String description, CaretakerDTO caretaker, AnimalDTO animal) {
		this.title = title;
		this.date = date;
		this.description = description;
		this.caretaker = caretaker;
		this.animal = animal;
	}
	
	public IncidentDTO(int id, String title, LocalDate date, String description, CaretakerDTO caretaker, AnimalDTO animal) {
		this.id = id;
		this.title = title;
		this.date = date;
		this.description = description;
		this.caretaker = caretaker;
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

	public CaretakerDTO getCaretaker() {
		return caretaker;
	}

	public void setCaretaker(CaretakerDTO caretaker) {
		this.caretaker = caretaker;
	}

	public AnimalDTO getAnimal() {
		return animal;
	}

	public void setAnimal(AnimalDTO animal) {
		this.animal = animal;
	}
}

package util;

import java.sql.Date;

import bl.entities.AdopterDTO;
import bl.entities.AdoptionDTO;
import bl.entities.AnimalDTO;
import bl.entities.AnimalTypeDTO;
import bl.entities.CaretakerDTO;
import bl.entities.ExaminationDTO;
import bl.entities.IncidentDTO;
import bl.entities.PatronDTO;
import bl.entities.RoomDTO;
import bl.entities.VetDTO;
import data.entities.Adopter;
import data.entities.Adoption;
import data.entities.Animal;
import data.entities.AnimalType;
import data.entities.Caretaker;
import data.entities.Examination;
import data.entities.Incident;
import data.entities.Patron;
import data.entities.Room;
import data.entities.Vet;

public class DataObjectMapper {
	public static AdopterDTO getAdopterDTOFromAdopter(Adopter adopter) {
		return new AdopterDTO(adopter.getId(), adopter.getLastName(), adopter.getFirstName(), adopter.getPhoneNumber(),
				adopter.getEmail());
	}

	public static Adopter getAdopterFromAdopterDTO(AdopterDTO adopterDTO) {
		return new Adopter(adopterDTO.getId(), adopterDTO.getLastName(), adopterDTO.getFirstName(),
				adopterDTO.getPhoneNumber(), adopterDTO.getEmail());
	}

	public static AdoptionDTO getAdoptionDTOFromAdoption(Adoption adoption) {
		return new AdoptionDTO(adoption.getId(), adoption.getDate().toLocalDate(),
				getAdopterDTOFromAdopter(adoption.getAdopter()), getAnimalDTOFromAnimal(adoption.getAnimal()));
	}

	public static Adoption getAdoptionFromAdoptionDTO(AdoptionDTO adoptionDTO) {
		return new Adoption(adoptionDTO.getId(), Date.valueOf(adoptionDTO.getDate()),
				getAdopterFromAdopterDTO(adoptionDTO.getAdopter()), getAnimalFromAnimalDTO(adoptionDTO.getAnimal()));
	}

	public static AnimalDTO getAnimalDTOFromAnimal(Animal animal) {
		return new AnimalDTO(animal.getId(), animal.getName(),
				AnimalDTO.Gender.fromValue(animal.getGender().getValue()), animal.getDateOfBirth().toLocalDate(),
				animal.getAdditionalInfo(), getAnimalTypeDTOFromAnimalType(animal.getAnimalType()),
				getPatronDTOFromPatron(animal.getPatron()), getRoomDTOFromRoom(animal.getRoom()));
	}

	public static Animal getAnimalFromAnimalDTO(AnimalDTO animalDTO) {
		return new Animal(animalDTO.getId(), animalDTO.getName(),
				Animal.Gender.fromValue(animalDTO.getGender().getValue()), Date.valueOf(animalDTO.getDateOfBirth()),
				animalDTO.getAdditionalInfo(), getAnimalTypeFromAnimalTypeDTO(animalDTO.getAnimalType()),
				getPatronFromPatronDTO(animalDTO.getPatron()), getRoomFromRoomDTO(animalDTO.getRoom()));
	}

	public static AnimalTypeDTO getAnimalTypeDTOFromAnimalType(AnimalType animalType) {
		return new AnimalTypeDTO(animalType.getId(), animalType.getName());
	}

	public static AnimalType getAnimalTypeFromAnimalTypeDTO(AnimalTypeDTO animalTypeDTO) {
		return new AnimalType(animalTypeDTO.getId(), animalTypeDTO.getName());
	}

	public static CaretakerDTO getCaretakerDTOFromCaretaker(Caretaker caretaker) {
		return new CaretakerDTO(caretaker.getId(), caretaker.getLastName(), caretaker.getFirstName(),
				caretaker.getPhoneNumber(), caretaker.getEmail());
	}

	public static Caretaker getCaretakerFromCaretakerDTO(CaretakerDTO caretakerDTO) {
		return new Caretaker(caretakerDTO.getId(), caretakerDTO.getLastName(), caretakerDTO.getFirstName(),
				caretakerDTO.getPhoneNumber(), caretakerDTO.getEmail());
	}

	public static ExaminationDTO getExaminationDTOFromExamination(Examination examination) {
		return new ExaminationDTO(examination.getId(), examination.getTitle(), examination.getDate().toLocalDate(),
				examination.getDescription(), getVetDTOFromVet(examination.getVet()),
				getAnimalDTOFromAnimal(examination.getAnimal()));
	}

	public static Examination getExaminationFromExaminationDTO(ExaminationDTO examinationDTO) {
		return new Examination(examinationDTO.getId(), examinationDTO.getTitle(), Date.valueOf(examinationDTO.getDate()),
				examinationDTO.getDescription(), getVetFromVetDTO(examinationDTO.getVet()),
				getAnimalFromAnimalDTO(examinationDTO.getAnimal()));
	}

	public static IncidentDTO getIncidentDTOFromIncident(Incident incident) {
		return new IncidentDTO(incident.getId(), incident.getTitle(), incident.getDate().toLocalDate(),
				incident.getDescription(), getCaretakerDTOFromCaretaker(incident.getCaretaker()),
				getAnimalDTOFromAnimal(incident.getAnimal()));
	}

	public static Incident getIncidentFromIncidentDTO(IncidentDTO incidentDTO) {
		return new Incident(incidentDTO.getId(), incidentDTO.getTitle(), Date.valueOf(incidentDTO.getDate()),
				incidentDTO.getDescription(), getCaretakerFromCaretakerDTO(incidentDTO.getCaretaker()),
				getAnimalFromAnimalDTO(incidentDTO.getAnimal()));
	}

	public static PatronDTO getPatronDTOFromPatron(Patron patron) {
		return new PatronDTO(patron.getId(), patron.getLastName(), patron.getFirstName(), patron.getPhoneNumber(),
				patron.getEmail());
	}

	public static Patron getPatronFromPatronDTO(PatronDTO patronDTO) {
		return new Patron(patronDTO.getId(), patronDTO.getLastName(), patronDTO.getFirstName(),
				patronDTO.getPhoneNumber(), patronDTO.getEmail());
	}

	public static RoomDTO getRoomDTOFromRoom(Room room) {
		return new RoomDTO(room.getId(), room.getName());
	}

	public static Room getRoomFromRoomDTO(RoomDTO roomDTO) {
		return new Room(roomDTO.getId(), roomDTO.getName());
	}

	public static VetDTO getVetDTOFromVet(Vet vet) {
		return new VetDTO(vet.getId(), vet.getLastName(), vet.getFirstName(), vet.getPhoneNumber(), vet.getEmail());
	}

	public static Vet getVetFromVetDTO(VetDTO vetDTO) {
		return new Vet(vetDTO.getId(), vetDTO.getLastName(), vetDTO.getFirstName(), vetDTO.getPhoneNumber(),
				vetDTO.getEmail());
	}
}

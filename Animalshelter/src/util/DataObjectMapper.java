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
	/**
	 * Converts an Adopter object to an AdopterDTO object.
	 *
	 * @param adopter the Adopter object to convert
	 * @return the converted AdopterDTO object
	 */
	public static AdopterDTO getAdopterDTOFromAdopter(Adopter adopter) {
		return new AdopterDTO(adopter.getId(), adopter.getLastName(), adopter.getFirstName(), adopter.getPhoneNumber(),
				adopter.getEmail());
	}

	/**
	 * Converts an AdopterDTO object to an Adopter object.
	 *
	 * @param adopterDTO the AdopterDTO object to convert
	 * @return the converted Adopter object
	 */
	public static Adopter getAdopterFromAdopterDTO(AdopterDTO adopterDTO) {
		return new Adopter(adopterDTO.getId(), adopterDTO.getLastName(), adopterDTO.getFirstName(),
				adopterDTO.getPhoneNumber(), adopterDTO.getEmail());
	}

	/**
	 * Converts an Adoption object to an AdoptionDTO object.
	 *
	 * @param adoption the Adoption object to convert
	 * @return the converted AdoptionDTO object
	 */
	public static AdoptionDTO getAdoptionDTOFromAdoption(Adoption adoption) {
		return new AdoptionDTO(adoption.getId(), adoption.getDate().toLocalDate(),
				getAdopterDTOFromAdopter(adoption.getAdopter()), getAnimalDTOFromAnimal(adoption.getAnimal()));
	}

	/**
	 * Converts an AdoptionDTO object to an Adoption object.
	 *
	 * @param adoptionDTO the AdoptionDTO object to convert
	 * @return the converted Adoption object
	 */
	public static Adoption getAdoptionFromAdoptionDTO(AdoptionDTO adoptionDTO) {
		return new Adoption(adoptionDTO.getId(), Date.valueOf(adoptionDTO.getDate()),
				getAdopterFromAdopterDTO(adoptionDTO.getAdopter()), getAnimalFromAnimalDTO(adoptionDTO.getAnimal()));
	}

	/**
	 * Converts an Animal object to an AnimalDTO object.
	 *
	 * @param animal the Animal object to convert
	 * @return the converted AnimalDTO object
	 */
	public static AnimalDTO getAnimalDTOFromAnimal(Animal animal) {
		return new AnimalDTO(animal.getId(), animal.getName(),
				AnimalDTO.Gender.fromValue(animal.getGender().getValue()), animal.getDateOfBirth().toLocalDate(),
				animal.getAdditionalInfo(), getAnimalTypeDTOFromAnimalType(animal.getAnimalType()),
				getPatronDTOFromPatron(animal.getPatron()), getRoomDTOFromRoom(animal.getRoom()), animal.getImage());
	}

	/**
	 * Converts an AnimalDTO object to an Animal object.
	 *
	 * @param animalDTO the AnimalDTO object to convert
	 * @return the converted Animal object
	 */
	public static Animal getAnimalFromAnimalDTO(AnimalDTO animalDTO) {
		return new Animal(animalDTO.getId(), animalDTO.getName(),
				Animal.Gender.fromValue(animalDTO.getGender().getValue()), Date.valueOf(animalDTO.getDateOfBirth()),
				animalDTO.getAdditionalInfo(), getAnimalTypeFromAnimalTypeDTO(animalDTO.getAnimalType()),
				getPatronFromPatronDTO(animalDTO.getPatron()), getRoomFromRoomDTO(animalDTO.getRoom()), animalDTO.getImage());
	}

	/**
	 * Converts an AnimalType object to an AnimalTypeDTO object.
	 *
	 * @param animalType the AnimalType object to convert
	 * @return the converted AnimalTypeDTO object
	 */
	public static AnimalTypeDTO getAnimalTypeDTOFromAnimalType(AnimalType animalType) {
		return new AnimalTypeDTO(animalType.getId(), animalType.getName());
	}

	/**
	 * Converts an AnimalTypeDTO object to an AnimalType object.
	 *
	 * @param animalTypeDTO the AnimalTypeDTO object to convert
	 * @return the converted AnimalType object
	 */
	public static AnimalType getAnimalTypeFromAnimalTypeDTO(AnimalTypeDTO animalTypeDTO) {
		return new AnimalType(animalTypeDTO.getId(), animalTypeDTO.getName());
	}

	/**
	 * Converts a Caretaker object to a CaretakerDTO object.
	 *
	 * @param caretaker the Caretaker object to convert
	 * @return the converted CaretakerDTO object
	 */
	public static CaretakerDTO getCaretakerDTOFromCaretaker(Caretaker caretaker) {
		return new CaretakerDTO(caretaker.getId(), caretaker.getLastName(), caretaker.getFirstName(),
				caretaker.getPhoneNumber(), caretaker.getEmail());
	}

	/**
	 * Converts a CaretakerDTO object to a Caretaker object.
	 *
	 * @param caretakerDTO the CaretakerDTO object to convert
	 * @return the converted Caretaker object
	 */
	public static Caretaker getCaretakerFromCaretakerDTO(CaretakerDTO caretakerDTO) {
		return new Caretaker(caretakerDTO.getId(), caretakerDTO.getLastName(), caretakerDTO.getFirstName(),
				caretakerDTO.getPhoneNumber(), caretakerDTO.getEmail());
	}

	/**
	 * Converts an Examination object to an ExaminationDTO object.
	 *
	 * @param examination the Examination object to convert
	 * @return the converted ExaminationDTO object
	 */
	public static ExaminationDTO getExaminationDTOFromExamination(Examination examination) {
		return new ExaminationDTO(examination.getId(), examination.getTitle(), examination.getDate().toLocalDate(),
				examination.getDescription(), getVetDTOFromVet(examination.getVet()),
				getAnimalDTOFromAnimal(examination.getAnimal()));
	}

	/**
	 * Converts an ExaminationDTO object to an Examination object.
	 *
	 * @param examinationDTO the ExaminationDTO object to convert
	 * @return the converted Examination object
	 */
	public static Examination getExaminationFromExaminationDTO(ExaminationDTO examinationDTO) {
		return new Examination(examinationDTO.getId(), examinationDTO.getTitle(), Date.valueOf(examinationDTO.getDate()),
				examinationDTO.getDescription(), getVetFromVetDTO(examinationDTO.getVet()),
				getAnimalFromAnimalDTO(examinationDTO.getAnimal()));
	}

	/**
	 * Converts an Incident object to an IncidentDTO object.
	 *
	 * @param incident the Incident object to convert
	 * @return the converted IncidentDTO object
	 */
	public static IncidentDTO getIncidentDTOFromIncident(Incident incident) {
		return new IncidentDTO(incident.getId(), incident.getTitle(), incident.getDate().toLocalDate(),
				incident.getDescription(), getCaretakerDTOFromCaretaker(incident.getCaretaker()),
				getAnimalDTOFromAnimal(incident.getAnimal()));
	}

	/**
	 * Converts an IncidentDTO object to an Incident object.
	 *
	 * @param incidentDTO the IncidentDTO object to convert
	 * @return the converted Incident object
	 */
	public static Incident getIncidentFromIncidentDTO(IncidentDTO incidentDTO) {
		return new Incident(incidentDTO.getId(), incidentDTO.getTitle(), Date.valueOf(incidentDTO.getDate()),
				incidentDTO.getDescription(), getCaretakerFromCaretakerDTO(incidentDTO.getCaretaker()),
				getAnimalFromAnimalDTO(incidentDTO.getAnimal()));
	}

	/**
	 * Converts a Patron object to a PatronDTO object.
	 *
	 * @param patron the Patron object to convert
	 * @return the converted PatronDTO object, or null if the patron is null
	 */
	public static PatronDTO getPatronDTOFromPatron(Patron patron) {
		return patron == null ? null : new PatronDTO(patron.getId(), patron.getLastName(), patron.getFirstName(), patron.getPhoneNumber(),
				patron.getEmail());
	}

	/**
	 * Converts a PatronDTO object to a Patron object.
	 *
	 * @param patronDTO the PatronDTO object to convert
	 * @return the converted Patron object, or null if the patronDTO is null
	 */
	public static Patron getPatronFromPatronDTO(PatronDTO patronDTO) {
		return patronDTO == null ? null : new Patron(patronDTO.getId(), patronDTO.getLastName(), patronDTO.getFirstName(),
				patronDTO.getPhoneNumber(), patronDTO.getEmail());
	}

	/**
	 * Converts a Room object to a RoomDTO object.
	 *
	 * @param room the Room object to convert
	 * @return the converted RoomDTO object
	 */
	public static RoomDTO getRoomDTOFromRoom(Room room) {
		return new RoomDTO(room.getId(), room.getName());
	}

	/**
	 * Converts a RoomDTO object to a Room object.
	 *
	 * @param roomDTO the RoomDTO object to convert
	 * @return the converted Room object
	 */
	public static Room getRoomFromRoomDTO(RoomDTO roomDTO) {
		return new Room(roomDTO.getId(), roomDTO.getName());
	}

	/**
	 * Converts a Vet object to a VetDTO object.
	 *
	 * @param vet the Vet object to convert
	 * @return the converted VetDTO object
	 */
	public static VetDTO getVetDTOFromVet(Vet vet) {
		return new VetDTO(vet.getId(), vet.getLastName(), vet.getFirstName(), vet.getPhoneNumber(), vet.getEmail());
	}

	/**
	 * Converts a VetDTO object to a Vet object.
	 *
	 * @param vetDTO the VetDTO object to convert
	 * @return the converted Vet object
	 */
	public static Vet getVetFromVetDTO(VetDTO vetDTO) {
		return new Vet(vetDTO.getId(), vetDTO.getLastName(), vetDTO.getFirstName(), vetDTO.getPhoneNumber(),
				vetDTO.getEmail());
	}
}

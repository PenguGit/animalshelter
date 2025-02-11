package bl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

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
import data.DataManager;
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
import util.DataObjectMapper;

public class DTOManager {
	// Adopter
	/**
	 * Saves an adopter entity.
	 * @param adopterDTO the adopter data transfer object to be saved.
	 */
	public void saveAdopter(AdopterDTO adopterDTO) {
		Adopter adopter = DataObjectMapper.getAdopterFromAdopterDTO(adopterDTO);
		adopterDTO.setId(DataManager.getInstance().saveEntity(adopter));
	}

	/**
	 * Loads an adopter by its ID.
	 * @param id the ID of the adopter to be loaded.
	 * @return the adopter data transfer object.
	 */
	public AdopterDTO loadAdopterById(int id) {
		return DataObjectMapper.getAdopterDTOFromAdopter(DataManager.getInstance().loadEntityById(Adopter.class, id));
	}

	/**
	 * Loads all adopters.
	 * @return a list of adopter data transfer objects.
	 */
	public ArrayList<AdopterDTO> loadAdopters() {
		ArrayList<AdopterDTO> adopterDTOs = new ArrayList<>();
		for (Adopter adopter : DataManager.getInstance().loadEntities(Adopter.class)) {
			adopterDTOs.add(DataObjectMapper.getAdopterDTOFromAdopter(adopter));
		}
		return adopterDTOs;
	}

	/**
	 * Deletes an adopter entity.
	 * @param adopterDTO the adopter data transfer object to be deleted.
	 */
	public void deleteAdopter(AdopterDTO adopterDTO) {
		Adopter adopter = DataObjectMapper.getAdopterFromAdopterDTO(adopterDTO);
	    DataManager.getInstance().deleteEntity(adopter);
	}
	
	// Adoption
	/**
	 * Saves an adoption entity.
	 * @param adoptionDTO the adoption data transfer object to be saved.
	 */
	public void saveAdoption(AdoptionDTO adoptionDTO) {
	    Adoption adoption = DataObjectMapper.getAdoptionFromAdoptionDTO(adoptionDTO);
	    adoptionDTO.setId(DataManager.getInstance().saveEntity(adoption));
	}

	/**
	 * Loads an adoption by its ID.
	 * @param id the ID of the adoption to be loaded.
	 * @return the adoption data transfer object.
	 */
	public AdoptionDTO loadAdoptionById(int id) {
	    return DataObjectMapper.getAdoptionDTOFromAdoption(DataManager.getInstance().loadEntityById(Adoption.class, id));
	}

	/**
	 * Loads all adoptions.
	 * @return a list of adoption data transfer objects.
	 */
	public ArrayList<AdoptionDTO> loadAdoptions() {
	    ArrayList<AdoptionDTO> adoptionDTOs = new ArrayList<>();
	    for (Adoption adoption : DataManager.getInstance().loadEntities(Adoption.class)) {
	        adoptionDTOs.add(DataObjectMapper.getAdoptionDTOFromAdoption(adoption));
	    }
	    return adoptionDTOs;
	}

	/**
	 * Deletes an adoption entity.
	 * @param adoptionDTO the adoption data transfer object to be deleted.
	 */
	public void deleteAdoption(AdoptionDTO adoptionDTO) {
	    Adoption adoption = DataObjectMapper.getAdoptionFromAdoptionDTO(adoptionDTO);
	    DataManager.getInstance().deleteEntity(adoption);
	}

	// Animal
	/**
	 * Saves an animal entity.
	 * @param animalDTO the animal data transfer object to be saved.
	 */
	public void saveAnimal(AnimalDTO animalDTO) {
	    Animal animal = DataObjectMapper.getAnimalFromAnimalDTO(animalDTO);
	    animalDTO.setId(DataManager.getInstance().saveEntity(animal));
	}
	
	/**
	 * Loads an animal by its ID.
	 * @param id the ID of the animal to be loaded.
	 * @return the animal data transfer object.
	 */
	public AnimalDTO loadAnimalById(int id) {
	    return DataObjectMapper.getAnimalDTOFromAnimal(DataManager.getInstance().loadEntityById(Animal.class, id));
	}

	/**
	 * Loads all animals.
	 * @return a list of animal data transfer objects.
	 */
	public ArrayList<AnimalDTO> loadAnimals() {
	    ArrayList<AnimalDTO> animalDTOs = new ArrayList<>();
	    for (Animal animal : DataManager.getInstance().loadEntities(Animal.class)) {
	        animalDTOs.add(DataObjectMapper.getAnimalDTOFromAnimal(animal));
	    }
	    return animalDTOs;
	}
	
	/**
	 * Loads all animals that have not been adopted.
	 * @return a list of animal data transfer objects.
	 */
	public ArrayList<AnimalDTO> loadAnimalsNotAdopted() {
		HashSet<Integer> adoptedAnimalIds = new HashSet<Integer>(loadAdoptions().stream().map(e -> e.getAnimal().getId()).collect(Collectors.toSet()));
		
	    ArrayList<AnimalDTO> animalDTOs = new ArrayList<>();
	    for (Animal animal : DataManager.getInstance().loadEntities(Animal.class)) {
	    	if(adoptedAnimalIds.contains(animal.getId())) {
	    		continue;
	    	}
	        animalDTOs.add(DataObjectMapper.getAnimalDTOFromAnimal(animal));
	    }
	    return animalDTOs;
	}
	
	/**
	 * Loads all animals of a certain type.
	 * @return a list of animal data transfer objects.
	 */
	public ArrayList<AnimalDTO> loadAnimalsByType(AnimalTypeDTO type) {
	    ArrayList<AnimalDTO> animalDTOs = new ArrayList<>();
	    for (Animal animal : DataManager.getInstance().loadEntities(Animal.class).stream().filter(e -> e.getAnimalType().getId() == type.getId()).collect(Collectors.toList())) {
	        animalDTOs.add(DataObjectMapper.getAnimalDTOFromAnimal(animal));
	    }
	    return animalDTOs;
	}

	/**
	 * Deletes an animal entity.
	 * @param animalDTO the animal data transfer object to be deleted.
	 */
	public void deleteAnimal(AnimalDTO animalDTO) {
	    Animal animal = DataObjectMapper.getAnimalFromAnimalDTO(animalDTO);
	    DataManager.getInstance().deleteEntity(animal);
	}

	// AnimalType
	/**
	 * Saves an animal type entity.
	 * @param animalTypeDTO the animal type data transfer object to be saved.
	 */
	public void saveAnimalType(AnimalTypeDTO animalTypeDTO) {
	    AnimalType animalType = DataObjectMapper.getAnimalTypeFromAnimalTypeDTO(animalTypeDTO);
	    animalTypeDTO.setId(DataManager.getInstance().saveEntity(animalType));
	}

	/**
	 * Loads an animal type by its ID.
	 * @param id the ID of the animal type to be loaded.
	 * @return the animal type data transfer object.
	 */
	public AnimalTypeDTO loadAnimalTypeById(int id) {
	    return DataObjectMapper.getAnimalTypeDTOFromAnimalType(DataManager.getInstance().loadEntityById(AnimalType.class, id));
	}

	/**
	 * Loads all animal types.
	 * @return a list of animal type data transfer objects.
	 */
	public ArrayList<AnimalTypeDTO> loadAnimalTypes() {
	    ArrayList<AnimalTypeDTO> animalTypeDTOs = new ArrayList<>();
	    for (AnimalType animalType : DataManager.getInstance().loadEntities(AnimalType.class)) {
	        animalTypeDTOs.add(DataObjectMapper.getAnimalTypeDTOFromAnimalType(animalType));
	    }
	    return animalTypeDTOs;
	}

	/**
	 * Deletes an animal type entity.
	 * @param animalTypeDTO the animal type data transfer object to be deleted.
	 */
	public void deleteAnimalType(AnimalTypeDTO animalTypeDTO) {
	    AnimalType animalType = DataObjectMapper.getAnimalTypeFromAnimalTypeDTO(animalTypeDTO);
	    DataManager.getInstance().deleteEntity(animalType);
	}

	// Caretaker
	/**
	 * Saves a caretaker entity.
	 * @param caretakerDTO the caretaker data transfer object to be saved.
	 */
	public void saveCaretaker(CaretakerDTO caretakerDTO) {
	    Caretaker caretaker = DataObjectMapper.getCaretakerFromCaretakerDTO(caretakerDTO);
	    caretakerDTO.setId(DataManager.getInstance().saveEntity(caretaker));
	}

	/**
	 * Loads a caretaker by its ID.
	 * @param id the ID of the caretaker to be loaded.
	 * @return the caretaker data transfer object.
	 */
	public CaretakerDTO loadCaretakerById(int id) {
	    return DataObjectMapper.getCaretakerDTOFromCaretaker(DataManager.getInstance().loadEntityById(Caretaker.class, id));
	}

	/**
	 * Loads all caretakers.
	 * @return a list of caretaker data transfer objects.
	 */
	public ArrayList<CaretakerDTO> loadCaretakers() {
	    ArrayList<CaretakerDTO> caretakerDTOs = new ArrayList<>();
	    for (Caretaker caretaker : DataManager.getInstance().loadEntities(Caretaker.class)) {
	        caretakerDTOs.add(DataObjectMapper.getCaretakerDTOFromCaretaker(caretaker));
	    }
	    return caretakerDTOs;
	}

	/**
	 * Deletes a caretaker entity.
	 * @param caretakerDTO the caretaker data transfer object to be deleted.
	 */
	public void deleteCaretaker(CaretakerDTO caretakerDTO) {
	    Caretaker caretaker = DataObjectMapper.getCaretakerFromCaretakerDTO(caretakerDTO);
	    DataManager.getInstance().deleteEntity(caretaker);
	}

	// Examination
	/**
	 * Saves an examination entity.
	 * @param examinationDTO the examination data transfer object to be saved.
	 */
	public void saveExamination(ExaminationDTO examinationDTO) {
	    Examination examination = DataObjectMapper.getExaminationFromExaminationDTO(examinationDTO);
	    examinationDTO.setId(DataManager.getInstance().saveEntity(examination));
	}

	/**
	 * Loads an examination by its ID.
	 * @param id the ID of the examination to be loaded.
	 * @return the examination data transfer object.
	 */
	public ExaminationDTO loadExaminationById(int id) {
	    return DataObjectMapper.getExaminationDTOFromExamination(DataManager.getInstance().loadEntityById(Examination.class, id));
	}

	/**
	 * Loads all examinations.
	 * @return a list of examination data transfer objects.
	 */
	private ArrayList<ExaminationDTO> loadExaminations() {
	    ArrayList<ExaminationDTO> examinationDTOs = new ArrayList<>();
	    for (Examination examination : DataManager.getInstance().loadEntities(Examination.class)) {
	        examinationDTOs.add(DataObjectMapper.getExaminationDTOFromExamination(examination));
	    }
	    return examinationDTOs;
	}
	
	/**
	 * Loads all examinations for a specific animal by its ID.
	 * @param id the ID of the animal.
	 * @return a list of examination data transfer objects.
	 */
	public ArrayList<ExaminationDTO> loadExaminationsByAnimalId(int id) {
	    return new ArrayList<ExaminationDTO>(
	    		loadExaminations()
	    			.stream()
	    			.filter(e -> e.getAnimal().getId() == id)
	    			.collect(Collectors.toList()));
	}

	/**
	 * Deletes an examination entity.
	 * @param examinationDTO the examination data transfer object to be deleted.
	 */
	public void deleteExamination(ExaminationDTO examinationDTO) {
	    Examination examination = DataObjectMapper.getExaminationFromExaminationDTO(examinationDTO);
	    DataManager.getInstance().deleteEntity(examination);
	}

	// Incident
	/**
	 * Saves an incident entity.
	 * @param incidentDTO the incident data transfer object to be saved.
	 */
	public void saveIncident(IncidentDTO incidentDTO) {
	    Incident incident = DataObjectMapper.getIncidentFromIncidentDTO(incidentDTO);
	    incidentDTO.setId(DataManager.getInstance().saveEntity(incident));
	}

	/**
	 * Loads an incident by its ID.
	 * @param id the ID of the incident to be loaded.
	 * @return the incident data transfer object.
	 */
	public IncidentDTO loadIncidentById(int id) {
	    return DataObjectMapper.getIncidentDTOFromIncident(DataManager.getInstance().loadEntityById(Incident.class, id));
	}

	/**
	 * Loads all incidents.
	 * @return a list of incident data transfer objects.
	 */
	private ArrayList<IncidentDTO> loadIncidents() {
	    ArrayList<IncidentDTO> incidentDTOs = new ArrayList<>();
	    for (Incident incident : DataManager.getInstance().loadEntities(Incident.class)) {
	        incidentDTOs.add(DataObjectMapper.getIncidentDTOFromIncident(incident));
	    }
	    return incidentDTOs;
	}
	
	/**
	 * Loads all incidents for a specific animal by its ID.
	 * @param id the ID of the animal.
	 * @return a list of incident data transfer objects.
	 */
	public ArrayList<IncidentDTO> loadIncidentsByAnimalId(int id) {
	    return new ArrayList<IncidentDTO>(
	    		loadIncidents()
	    			.stream()
	    			.filter(e -> e.getAnimal().getId() == id)
	    			.collect(Collectors.toList()));
	}

	/**
	 * Deletes an incident entity.
	 * @param incidentDTO the incident data transfer object to be deleted.
	 */
	public void deleteIncident(IncidentDTO incidentDTO) {
	    Incident incident = DataObjectMapper.getIncidentFromIncidentDTO(incidentDTO);
	    DataManager.getInstance().deleteEntity(incident);
	}

	// Patron
	/**
	 * Saves a patron entity.
	 * @param patronDTO the patron data transfer object to be saved.
	 */
	public void savePatron(PatronDTO patronDTO) {
	    Patron patron = DataObjectMapper.getPatronFromPatronDTO(patronDTO);
	    patronDTO.setId(DataManager.getInstance().saveEntity(patron));
	}

	/**
	 * Loads a patron by its ID.
	 * @param id the ID of the patron to be loaded.
	 * @return the patron data transfer object.
	 */
	public PatronDTO loadPatronById(int id) {
	    return DataObjectMapper.getPatronDTOFromPatron(DataManager.getInstance().loadEntityById(Patron.class, id));
	}

	/**
	 * Loads all patrons.
	 * @return a list of patron data transfer objects.
	 */
	public ArrayList<PatronDTO> loadPatrons() {
	    ArrayList<PatronDTO> patronDTOs = new ArrayList<>();
	    for (Patron patron : DataManager.getInstance().loadEntities(Patron.class)) {
	        patronDTOs.add(DataObjectMapper.getPatronDTOFromPatron(patron));
	    }
	    return patronDTOs;
	}

	/**
	 * Deletes a patron entity.
	 * @param patronDTO the patron data transfer object to be deleted.
	 */
	public void deletePatron(PatronDTO patronDTO) {
	    Patron patron = DataObjectMapper.getPatronFromPatronDTO(patronDTO);
	    DataManager.getInstance().deleteEntity(patron);
	}

	// Room
	/**
	 * Saves a room entity.
	 * @param roomDTO the room data transfer object to be saved.
	 */
	public void saveRoom(RoomDTO roomDTO) {
	    Room room = DataObjectMapper.getRoomFromRoomDTO(roomDTO);
	    roomDTO.setId(DataManager.getInstance().saveEntity(room));
	}

	/**
	 * Loads a room by its ID.
	 * @param id the ID of the room to be loaded.
	 * @return the room data transfer object.
	 */
	public RoomDTO loadRoomById(int id) {
	    return DataObjectMapper.getRoomDTOFromRoom(DataManager.getInstance().loadEntityById(Room.class, id));
	}

	/**
	 * Loads all rooms.
	 * @return a list of room data transfer objects.
	 */
	public ArrayList<RoomDTO> loadRooms() {
	    ArrayList<RoomDTO> roomDTOs = new ArrayList<>();
	    for (Room room : DataManager.getInstance().loadEntities(Room.class)) {
	        roomDTOs.add(DataObjectMapper.getRoomDTOFromRoom(room));
	    }
	    return roomDTOs;
	}

	/**
	 * Deletes a room entity.
	 * @param roomDTO the room data transfer object to be deleted.
	 */
	public void deleteRoom(RoomDTO roomDTO) {
	    Room room = DataObjectMapper.getRoomFromRoomDTO(roomDTO);
	    DataManager.getInstance().deleteEntity(room);
	}

	// Vet
	/**
	 * Saves a vet entity.
	 * @param vetDTO the vet data transfer object to be saved.
	 */
	public void saveVet(VetDTO vetDTO) {
	    Vet vet = DataObjectMapper.getVetFromVetDTO(vetDTO);
	    vetDTO.setId(DataManager.getInstance().saveEntity(vet));
	}

	/**
	 * Loads a vet by its ID.
	 * @param id the ID of the vet to be loaded.
	 * @return the vet data transfer object.
	 */
	public VetDTO loadVetById(int id) {
	    return DataObjectMapper.getVetDTOFromVet(DataManager.getInstance().loadEntityById(Vet.class, id));
	}

	/**
	 * Loads all vets.
	 * @return a list of vet data transfer objects.
	 */
	public ArrayList<VetDTO> loadVets() {
	    ArrayList<VetDTO> vetDTOs = new ArrayList<>();
	    for (Vet vet : DataManager.getInstance().loadEntities(Vet.class)) {
	        vetDTOs.add(DataObjectMapper.getVetDTOFromVet(vet));
	    }
	    return vetDTOs;
	}

	/**
	 * Deletes a vet entity.
	 * @param vetDTO the vet data transfer object to be deleted.
	 */
	public void deleteVet(VetDTO vetDTO) {
	    Vet vet = DataObjectMapper.getVetFromVetDTO(vetDTO);
	    DataManager.getInstance().deleteEntity(vet);
	}
}

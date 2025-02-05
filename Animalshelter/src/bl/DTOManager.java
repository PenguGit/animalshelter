package bl;

import java.util.ArrayList;

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
	private static DTOManager instance;

	private DTOManager() {
		super();
	}

	public static DTOManager getInstance() {
		if (instance == null) {
			instance = new DTOManager();
		}
		return instance;
	}

	// Adopter
	public static void saveAdopter(AdopterDTO adopterDTO) {
		Adopter adopter = DataObjectMapper.getAdopterFromAdopterDTO(adopterDTO);
		DataManager.getInstance().saveEntity(adopter);
	}

	public static AdopterDTO loadAdopterById(int id) {
		return DataObjectMapper.getAdopterDTOFromAdopter(DataManager.getInstance().loadEntityById(Adopter.class, id));
	}

	public static ArrayList<AdopterDTO> loadAdopters() {
		ArrayList<AdopterDTO> adopterDTOs = new ArrayList<>();
		for (Adopter adopter : DataManager.getInstance().loadEntities(Adopter.class)) {
			adopterDTOs.add(DataObjectMapper.getAdopterDTOFromAdopter(adopter));
		}
		return adopterDTOs;
	}

	public static void deleteAdopter(AdopterDTO adopterDTO) {
		Adopter adopter = DataObjectMapper.getAdopterFromAdopterDTO(adopterDTO);
	    DataManager.getInstance().deleteEntity(adopter);
	}
	
	// Adoption
	public static void saveAdoption(AdoptionDTO adoptionDTO) {
	    Adoption adoption = DataObjectMapper.getAdoptionFromAdoptionDTO(adoptionDTO);
	    DataManager.getInstance().saveEntity(adoption);
	}

	public static AdoptionDTO loadAdoptionById(int id) {
	    return DataObjectMapper.getAdoptionDTOFromAdoption(DataManager.getInstance().loadEntityById(Adoption.class, id));
	}

	public static ArrayList<AdoptionDTO> loadAdoptions() {
	    ArrayList<AdoptionDTO> adoptionDTOs = new ArrayList<>();
	    for (Adoption adoption : DataManager.getInstance().loadEntities(Adoption.class)) {
	        adoptionDTOs.add(DataObjectMapper.getAdoptionDTOFromAdoption(adoption));
	    }
	    return adoptionDTOs;
	}

	public static void deleteAdoption(AdoptionDTO adoptionDTO) {
	    Adoption adoption = DataObjectMapper.getAdoptionFromAdoptionDTO(adoptionDTO);
	    DataManager.getInstance().deleteEntity(adoption);
	}

	// Animal
	public static void saveAnimal(AnimalDTO animalDTO) {
	    Animal animal = DataObjectMapper.getAnimalFromAnimalDTO(animalDTO);
	    DataManager.getInstance().saveEntity(animal);
	}

	public static AnimalDTO loadAnimalById(int id) {
	    return DataObjectMapper.getAnimalDTOFromAnimal(DataManager.getInstance().loadEntityById(Animal.class, id));
	}

	public static ArrayList<AnimalDTO> loadAnimals() {
	    ArrayList<AnimalDTO> animalDTOs = new ArrayList<>();
	    for (Animal animal : DataManager.getInstance().loadEntities(Animal.class)) {
	        animalDTOs.add(DataObjectMapper.getAnimalDTOFromAnimal(animal));
	    }
	    return animalDTOs;
	}

	public static void deleteAnimal(AnimalDTO animalDTO) {
	    Animal animal = DataObjectMapper.getAnimalFromAnimalDTO(animalDTO);
	    DataManager.getInstance().deleteEntity(animal);
	}

	// AnimalType
	public static void saveAnimalType(AnimalTypeDTO animalTypeDTO) {
	    AnimalType animalType = DataObjectMapper.getAnimalTypeFromAnimalTypeDTO(animalTypeDTO);
	    DataManager.getInstance().saveEntity(animalType);
	}

	public static AnimalTypeDTO loadAnimalTypeById(int id) {
	    return DataObjectMapper.getAnimalTypeDTOFromAnimalType(DataManager.getInstance().loadEntityById(AnimalType.class, id));
	}

	public static ArrayList<AnimalTypeDTO> loadAnimalTypes() {
	    ArrayList<AnimalTypeDTO> animalTypeDTOs = new ArrayList<>();
	    for (AnimalType animalType : DataManager.getInstance().loadEntities(AnimalType.class)) {
	        animalTypeDTOs.add(DataObjectMapper.getAnimalTypeDTOFromAnimalType(animalType));
	    }
	    return animalTypeDTOs;
	}

	public static void deleteAnimalType(AnimalTypeDTO animalTypeDTO) {
	    AnimalType animalType = DataObjectMapper.getAnimalTypeFromAnimalTypeDTO(animalTypeDTO);
	    DataManager.getInstance().deleteEntity(animalType);
	}

	// Caretaker
	public static void saveCaretaker(CaretakerDTO caretakerDTO) {
	    Caretaker caretaker = DataObjectMapper.getCaretakerFromCaretakerDTO(caretakerDTO);
	    DataManager.getInstance().saveEntity(caretaker);
	}

	public static CaretakerDTO loadCaretakerById(int id) {
	    return DataObjectMapper.getCaretakerDTOFromCaretaker(DataManager.getInstance().loadEntityById(Caretaker.class, id));
	}

	public static ArrayList<CaretakerDTO> loadCaretakers() {
	    ArrayList<CaretakerDTO> caretakerDTOs = new ArrayList<>();
	    for (Caretaker caretaker : DataManager.getInstance().loadEntities(Caretaker.class)) {
	        caretakerDTOs.add(DataObjectMapper.getCaretakerDTOFromCaretaker(caretaker));
	    }
	    return caretakerDTOs;
	}

	public static void deleteCaretaker(CaretakerDTO caretakerDTO) {
	    Caretaker caretaker = DataObjectMapper.getCaretakerFromCaretakerDTO(caretakerDTO);
	    DataManager.getInstance().deleteEntity(caretaker);
	}

	// Examination
	public static void saveExamination(ExaminationDTO examinationDTO) {
	    Examination examination = DataObjectMapper.getExaminationFromExaminationDTO(examinationDTO);
	    DataManager.getInstance().saveEntity(examination);
	}

	public static ExaminationDTO loadExaminationById(int id) {
	    return DataObjectMapper.getExaminationDTOFromExamination(DataManager.getInstance().loadEntityById(Examination.class, id));
	}

	public static ArrayList<ExaminationDTO> loadExaminations() {
	    ArrayList<ExaminationDTO> examinationDTOs = new ArrayList<>();
	    for (Examination examination : DataManager.getInstance().loadEntities(Examination.class)) {
	        examinationDTOs.add(DataObjectMapper.getExaminationDTOFromExamination(examination));
	    }
	    return examinationDTOs;
	}

	public static void deleteExamination(ExaminationDTO examinationDTO) {
	    Examination examination = DataObjectMapper.getExaminationFromExaminationDTO(examinationDTO);
	    DataManager.getInstance().deleteEntity(examination);
	}

	// Incident
	public static void saveIncident(IncidentDTO incidentDTO) {
	    Incident incident = DataObjectMapper.getIncidentFromIncidentDTO(incidentDTO);
	    DataManager.getInstance().saveEntity(incident);
	}

	public static IncidentDTO loadIncidentById(int id) {
	    return DataObjectMapper.getIncidentDTOFromIncident(DataManager.getInstance().loadEntityById(Incident.class, id));
	}

	public static ArrayList<IncidentDTO> loadIncidents() {
	    ArrayList<IncidentDTO> incidentDTOs = new ArrayList<>();
	    for (Incident incident : DataManager.getInstance().loadEntities(Incident.class)) {
	        incidentDTOs.add(DataObjectMapper.getIncidentDTOFromIncident(incident));
	    }
	    return incidentDTOs;
	}

	public static void deleteIncident(IncidentDTO incidentDTO) {
	    Incident incident = DataObjectMapper.getIncidentFromIncidentDTO(incidentDTO);
	    DataManager.getInstance().deleteEntity(incident);
	}

	// Patron
	public static void savePatron(PatronDTO patronDTO) {
	    Patron patron = DataObjectMapper.getPatronFromPatronDTO(patronDTO);
	    DataManager.getInstance().saveEntity(patron);
	}

	public static PatronDTO loadPatronById(int id) {
	    return DataObjectMapper.getPatronDTOFromPatron(DataManager.getInstance().loadEntityById(Patron.class, id));
	}

	public static ArrayList<PatronDTO> loadPatrons() {
	    ArrayList<PatronDTO> patronDTOs = new ArrayList<>();
	    for (Patron patron : DataManager.getInstance().loadEntities(Patron.class)) {
	        patronDTOs.add(DataObjectMapper.getPatronDTOFromPatron(patron));
	    }
	    return patronDTOs;
	}

	public static void deletePatron(PatronDTO patronDTO) {
	    Patron patron = DataObjectMapper.getPatronFromPatronDTO(patronDTO);
	    DataManager.getInstance().deleteEntity(patron);
	}

	// Room
	public static void saveRoom(RoomDTO roomDTO) {
	    Room room = DataObjectMapper.getRoomFromRoomDTO(roomDTO);
	    DataManager.getInstance().saveEntity(room);
	}

	public static RoomDTO loadRoomById(int id) {
	    return DataObjectMapper.getRoomDTOFromRoom(DataManager.getInstance().loadEntityById(Room.class, id));
	}

	public static ArrayList<RoomDTO> loadRooms() {
	    ArrayList<RoomDTO> roomDTOs = new ArrayList<>();
	    for (Room room : DataManager.getInstance().loadEntities(Room.class)) {
	        roomDTOs.add(DataObjectMapper.getRoomDTOFromRoom(room));
	    }
	    return roomDTOs;
	}

	public static void deleteRoom(RoomDTO roomDTO) {
	    Room room = DataObjectMapper.getRoomFromRoomDTO(roomDTO);
	    DataManager.getInstance().deleteEntity(room);
	}

	// Vet
	public static void saveVet(VetDTO vetDTO) {
	    Vet vet = DataObjectMapper.getVetFromVetDTO(vetDTO);
	    DataManager.getInstance().saveEntity(vet);
	}

	public static VetDTO loadVetById(int id) {
	    return DataObjectMapper.getVetDTOFromVet(DataManager.getInstance().loadEntityById(Vet.class, id));
	}

	public static ArrayList<VetDTO> loadVets() {
	    ArrayList<VetDTO> vetDTOs = new ArrayList<>();
	    for (Vet vet : DataManager.getInstance().loadEntities(Vet.class)) {
	        vetDTOs.add(DataObjectMapper.getVetDTOFromVet(vet));
	    }
	    return vetDTOs;
	}

	public static void deleteVet(VetDTO vetDTO) {
	    Vet vet = DataObjectMapper.getVetFromVetDTO(vetDTO);
	    DataManager.getInstance().deleteEntity(vet);
	}
}

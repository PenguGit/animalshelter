package bl.entities;

import java.time.LocalDate;

public class AnimalDTO extends EntityDTO {
	public enum Gender {
		UNKNOWN,
		FEMALE,
		MALE;
		
	    public int getValue() {
	    	switch(this) {
		    	case UNKNOWN:
					return 0;
		    	case FEMALE:
					return 1;
				case MALE:
					return 2;
				default:
					return -1;
	    	}
	    }
	    
	    public static Gender fromValue(int value) {
	    	switch(value) {
		    	case 0:
		    		return Gender.UNKNOWN;
		    	case 1:
		    		return Gender.FEMALE;
		    	default:
		    		return Gender.MALE;
	    	}
	    }
	}
	
	private String name;
	private Gender gender;
	private LocalDate dateOfBirth;
	private String additionalInfo;
	private AnimalTypeDTO animalType;
	private PatronDTO patron;
	private RoomDTO room;
	private byte[] image;
	
	public AnimalDTO(String name, Gender gender, LocalDate dateOfBirth, String additionalInfo, AnimalTypeDTO animalType,
			PatronDTO patron, RoomDTO room, byte[] image) {
		this.name = name;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.additionalInfo = additionalInfo;
		this.animalType = animalType;
		this.patron = patron;
		this.room = room;
		this.image = image;
	}
	
	public AnimalDTO(int id, String name, Gender gender, LocalDate dateOfBirth, String additionalInfo, AnimalTypeDTO animalType,
			PatronDTO patron, RoomDTO room, byte[] image) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.additionalInfo = additionalInfo;
		this.animalType = animalType;
		this.patron = patron;
		this.room = room;
		this.image = image;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	public AnimalTypeDTO getAnimalType() {
		return animalType;
	}
	public void setAnimalType(AnimalTypeDTO animalType) {
		this.animalType = animalType;
	}
	public PatronDTO getPatron() {
		return patron;
	}
	public void setPatron(PatronDTO patron) {
		this.patron = patron;
	}
	public RoomDTO getRoom() {
		return room;
	}
	public void setRoom(RoomDTO room) {
		this.room = room;
	}
	
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
}

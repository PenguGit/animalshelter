package bl.entities;

public class AnimalTypeDTO extends EntityDTO {
	private String name;
	
	public AnimalTypeDTO(String name) {
		this.name = name;
	}
	
	public AnimalTypeDTO(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

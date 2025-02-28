package bl.entities;

/**
 * The {@code EntityDTO} class serves as an abstract base class for Data Transfer Objects (DTOs) representing entities in the business logic layer.
 * It provides a common structure for entity DTOs, including an ID field and associated getter and setter methods.
 * This class is intended to be extended by specific DTO classes that correspond to data entities and facilitate data transfer between layers of the application.
 */
public abstract class EntityDTO {
	protected int id = -1;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}

package bl.entities;

public class RoomDTO extends EntityDTO {
	private String name;
	
	public RoomDTO(String name) {
		this.name = name;
	}
	
	public RoomDTO(int id, String name) {
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

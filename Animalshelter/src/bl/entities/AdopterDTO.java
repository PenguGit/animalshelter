package bl.entities;

public class AdopterDTO extends PersonDTO {
	public AdopterDTO(String lastName, String firstName, String phoneNumber, String email) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	
	public AdopterDTO(int id, String lastName, String firstName, String phoneNumber, String email) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
}

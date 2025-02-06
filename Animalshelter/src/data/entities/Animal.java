package data.entities;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;

import data.DataManager;

public class Animal extends Entity {
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
	private Date dateOfBirth;
	private String additionalInfo;
	private AnimalType animalType;
	private Patron patron;
	private Room room;
	private byte[] image;
	
	public Animal() {};
	
	public Animal(ResultSet resultSet) throws SQLException {
		this.id = resultSet.getInt("animal.id");
		this.name = resultSet.getString("animal.name");
		this.gender = Gender.fromValue(resultSet.getInt("animal.gender"));
		this.dateOfBirth = resultSet.getDate("animal.date_of_birth");
		this.additionalInfo = resultSet.getString("animal.additional_info");
		
		this.animalType = DataManager.getInstance().loadEntityById(AnimalType.class, resultSet.getInt("animal.animaltype_id"));
		this.patron = DataManager.getInstance().loadEntityById(Patron.class, resultSet.getInt("animal.patron_id"));
		this.room = DataManager.getInstance().loadEntityById(Room.class, resultSet.getInt("animal.room_id"));
		
		try {
	        Blob blob = resultSet.getBlob("animal.image"); // Get the image as a Blob
	        if (blob != null) { // Check if an image exists
	            try (InputStream inputStream = blob.getBinaryStream()) {
	                this.image = inputStream.readAllBytes(); // Read the bytes into the byte array
	            }
	        } else {
	            this.image = null; // Set image to null if it doesn't exist
	        }
	    } catch (SQLException | IOException e) {
	        // Handle the exception appropriately (e.g., log it, re-throw, etc.)
	        e.printStackTrace(); // Example: print the error
	        this.image = null; // Or set to a default image if you have one
	    }
	}
	
	public Animal(String name, Gender gender, Date dateOfBirth, String additionalInfo, AnimalType animalType,
			Patron patron, Room room, byte[] image) {
		this.name = name;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.additionalInfo = additionalInfo;
		this.animalType = animalType;
		this.patron = patron;
		this.room = room;
		this.image = image;
	}
	
	public Animal(int id, String name, Gender gender, Date dateOfBirth, String additionalInfo, AnimalType animalType,
			Patron patron, Room room, byte[] image) {
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
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	public AnimalType getAnimalType() {
		return animalType;
	}
	public void setAnimalType(AnimalType animalType) {
		this.animalType = animalType;
	}
	public Patron getPatron() {
		if (patron == null)
			patron = new Patron();
		return patron;
	}
	public void setPatron(Patron patron) {
		this.patron = patron;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	
	@Override
	public PreparedStatement getSqlInsertStatement(Connection connection) {
		String sql = "INSERT INTO animal(name, gender, date_of_birth, additional_info, AnimalType_id, Patron_id, Room_id, image) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, name);
			statement.setInt(2, gender.getValue());
			statement.setDate(3, dateOfBirth);
			if(additionalInfo.isBlank()) {
				statement.setNull(4, Types.VARCHAR);
			} else {
				statement.setString(4, additionalInfo);
			}
			statement.setInt(5, animalType.getId());
			if(patron == null || patron.getId()==-1) { //TODO getID -1 checked because changing DTOMapper to catch give null is more time
				statement.setNull(6, Types.INTEGER);
			} else {
				statement.setInt(6, patron.getId());
			}
			statement.setInt(7, room.getId());
			if (image != null) { // Check if an image exists
	            try (ByteArrayInputStream inputStream = new ByteArrayInputStream(image)) {
	                statement.setBinaryStream(8, inputStream, image.length);
	            } catch (IOException e) {
					e.printStackTrace();
				}
	        } else {
	            statement.setNull(8, Types.BLOB); // Set to null if no image
	        }
			return statement;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public PreparedStatement getSqlUpdateStatement(Connection connection) {
		String sql = "UPDATE animal SET name = ?, gender = ?, date_of_birth = ?, additional_info = ?, AnimalType_id = ?, Patron_id = ?, Room_id = ?, image = ? WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			statement.setInt(2, gender.getValue());
			statement.setDate(3, dateOfBirth);
			if(additionalInfo.isBlank()) {
				statement.setNull(4, Types.VARCHAR);
			} else {
				statement.setString(4, additionalInfo);
			}
			statement.setInt(5, animalType.getId());
			if(patron == null) {
				statement.setNull(6, Types.INTEGER);
			} else {
				statement.setInt(6, patron.getId());
			}
			statement.setInt(7, room.getId());
			if (image != null) { // Check if an image exists
	            try (ByteArrayInputStream inputStream = new ByteArrayInputStream(image)) {
	                statement.setBinaryStream(8, inputStream, image.length);
	            } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        } else {
	            statement.setNull(8, Types.BLOB); // Set to null if no image
	        }
			statement.setInt(9, id);
			return statement;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

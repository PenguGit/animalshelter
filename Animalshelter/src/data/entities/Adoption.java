package data.entities;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import data.DataManager;

/**
 * Represents the 'adoption' table in the database.
 */
public class Adoption extends Entity {
	private Date date;
	private Adopter adopter;
	private Animal animal;
	
	public Adoption() {};
	
	/**
	 * Initializes a new object from the values in a database ResultSet.
	 * @param resultSet The ResultSet containing the relevant fields.
	 * @throws SQLException Occurs, e.g., if the named field is not present in the ResultSet for some reason.
	 */
	public Adoption(ResultSet resultSet) throws SQLException {
		this.id = resultSet.getInt("id");
		String tempString = resultSet.getString("date");
		this.date = Date.valueOf(tempString);
		
		this.adopter = DataManager.getInstance().loadEntityById(Adopter.class, resultSet.getInt("adopter_id"));
		this.animal = DataManager.getInstance().loadEntityById(Animal.class, resultSet.getInt("animal_id"));
	}
	
	public Adoption(Date date, Adopter adopter, Animal animal) {
		this.date = date;
		this.adopter = adopter;
		this.animal = animal;
	}
	
	public Adoption(int id, Date date, Adopter adopter, Animal animal) {
		this.id = id;
		this.date = date;
		this.adopter = adopter;
		this.animal = animal;
	}
	
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Adopter getAdopter() {
		return adopter;
	}

	public void setAdopter(Adopter adopter) {
		this.adopter = adopter;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	@Override
	public PreparedStatement getSqlInsertStatement(Connection connection) {
		String sql = "INSERT INTO adoption(date, Adopter_id, Animal_id) VALUES(?, ?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, date.toString());
			statement.setInt(2, adopter.getId());
			statement.setInt(3, animal.getId());
			return statement;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PreparedStatement getSqlUpdateStatement(Connection connection) {
		String sql = "UPDATE adoption SET date = ?, Adopter_id = ?, Animal_id = ? WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, date.toString());
			statement.setInt(2, adopter.getId());
			statement.setInt(3, animal.getId());
			statement.setInt(4, id);
			return statement;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

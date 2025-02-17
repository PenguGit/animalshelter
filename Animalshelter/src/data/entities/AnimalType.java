package data.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Represents the 'animal_type' table in the database.
 */
public class AnimalType extends Entity {
	private String name;
	
	public AnimalType() {};
	
	/**
	 * Initializes a new object from the values in a database ResultSet.
	 * @param resultSet The ResultSet containing the relevant fields.
	 * @throws SQLException Occurs, e.g., if the named field is not present in the ResultSet for some reason.
	 */
	public AnimalType(ResultSet resultSet) throws SQLException {
		this.id = resultSet.getInt("animaltype.id");
		this.name = resultSet.getString("animaltype.name");
	}
	
	public AnimalType(String name) {
		this.name = name;
	}
	
	public AnimalType(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public PreparedStatement getSqlInsertStatement(Connection connection) {
		String sql = "INSERT INTO animaltype(name) VALUES(?)";
		try {
			PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, name);
			return statement;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PreparedStatement getSqlUpdateStatement(Connection connection) {
		String sql = "UPDATE animaltype SET name = ? WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			statement.setInt(2, id);
			return statement;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

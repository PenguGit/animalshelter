package data.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Represents the 'adopter' table in the database.
 */
public class Adopter extends Person {
	public Adopter() {};
	
	/**
	 * Initializes a new object from the values in a database ResultSet.
	 * @param resultSet The ResultSet containing the relevant fields.
	 * @throws SQLException Occurs, e.g., if the named field is not present in the ResultSet for some reason.
	 */
	public Adopter(ResultSet resultSet) throws SQLException {
		this.id = resultSet.getInt("adopter.id");
		this.lastName = resultSet.getString("adopter.last_name");
		this.firstName = resultSet.getString("adopter.first_name");
		this.phoneNumber = resultSet.getString("adopter.phone_number");
		this.email = resultSet.getString("adopter.email");
	}
	
	public Adopter(String lastName, String firstName, String phoneNumber, String email) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	
	public Adopter(int id, String lastName, String firstName, String phoneNumber, String email) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	
	@Override
	public PreparedStatement getSqlInsertStatement(Connection connection) {
		String sql = "INSERT INTO adopter(last_name, first_name, phone_number, email) VALUES(?, ?, ?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, lastName);
			statement.setString(2, firstName);
			statement.setString(3, phoneNumber);
			statement.setString(4, email);
			return statement;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PreparedStatement getSqlUpdateStatement(Connection connection) {
		String sql = "UPDATE adopter SET last_name = ?, first_name = ?, phone_number = ?, email = ? WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, lastName);
			statement.setString(2, firstName);
			statement.setString(3, phoneNumber);
			statement.setString(4, email);
			statement.setInt(5, id);
			return statement;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

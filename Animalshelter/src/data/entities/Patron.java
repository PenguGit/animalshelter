package data.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Represents the 'patron' table in the database.
 */
public class Patron extends Person {
	public Patron() {};
	
	/**
	 * Initializes a new object from the values in a database ResultSet.
	 * @param resultSet The ResultSet containing the relevant fields.
	 * @throws SQLException Occurs, e.g., if the named field is not present in the ResultSet for some reason.
	 */
	public Patron(ResultSet resultSet) throws SQLException {
		this.id = resultSet.getInt("id");
		this.lastName = resultSet.getString("last_name");
		this.firstName = resultSet.getString("first_name");
		this.phoneNumber = resultSet.getString("phone_number");
		this.email = resultSet.getString("email");
	}
	
	public Patron(String lastName, String firstName, String phoneNumber, String email) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	
	public Patron(int id, String lastName, String firstName, String phoneNumber, String email) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	
	@Override
	public PreparedStatement getSqlInsertStatement(Connection connection) {
		String sql = "INSERT INTO patron(last_name, first_name, phone_number, email) VALUES(?, ?, ?, ?)";
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
		String sql = "UPDATE patron SET last_name = ?, first_name = ?, phone_number = ?, email = ? WHERE id = ?";
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

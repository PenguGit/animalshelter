package data.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Vet extends Person {
	public Vet() {};
	
	public Vet(ResultSet resultSet) throws SQLException {
		this.id = resultSet.getInt("vet.id");
		this.lastName = resultSet.getString("vet.last_name");
		this.firstName = resultSet.getString("vet.first_name");
		this.phoneNumber = resultSet.getString("vet.phone_number");
		this.email = resultSet.getString("vet.email");
	}
	
	public Vet(String lastName, String firstName, String phoneNumber, String email) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	
	public Vet(int id, String lastName, String firstName, String phoneNumber, String email) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	
	@Override
	public PreparedStatement getSqlInsertStatement(Connection connection) {
		String sql = "INSERT INTO vet(last_name, first_name, phone_number, email) VALUES(?, ?, ?, ?)";
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
		String sql = "UPDATE vet SET last_name = ?, first_name = ?, phone_number = ?, email = ? WHERE id = ?";
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

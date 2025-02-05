package data.entities;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Incident extends Entity {
	private String title;
	private Date date;
	private String description;
	private Caretaker caretaker;
	private Animal animal;
	
	public Incident(ResultSet resultSet) throws SQLException {
		this.id = resultSet.getInt("incident.id");
		this.title = resultSet.getString("incident.name");
		this.date = resultSet.getDate("incident.date");
		this.description = resultSet.getString("incident.description");
		this.caretaker = new Caretaker(resultSet);
		this.animal = new Animal(resultSet);
	}
	
	public Incident(String title, Date date, String description, Caretaker caretaker, Animal animal) {
		this.title = title;
		this.date = date;
		this.description = description;
		this.caretaker = caretaker;
		this.animal = animal;
	}
	
	public Incident(int id, String title, Date date, String description, Caretaker caretaker, Animal animal) {
		this.id = id;
		this.title = title;
		this.date = date;
		this.description = description;
		this.caretaker = caretaker;
		this.animal = animal;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Caretaker getCaretaker() {
		return caretaker;
	}

	public void setCaretaker(Caretaker caretaker) {
		this.caretaker = caretaker;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	@Override
	public PreparedStatement getSqlInsertStatement(Connection connection) {
		String sql = "INSERT INTO incident(title, date, description, Caretaker_id, Animal_id) VALUES(?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, title);
			statement.setDate(2, date);
			statement.setString(3, description);
			statement.setInt(4, caretaker.getId());
			statement.setInt(5, animal.getId());
			return statement;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PreparedStatement getSqlUpdateStatement(Connection connection) {
		String sql = "UPDATE incident SET title = ?, date = ?, description = ?, Caretaker_id = ?, Animal_id = ? WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, title);
			statement.setDate(2, date);
			statement.setString(3, description);
			statement.setInt(4, caretaker.getId());
			statement.setInt(5, animal.getId());
			statement.setInt(6, id);
			return statement;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

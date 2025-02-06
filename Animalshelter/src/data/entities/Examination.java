package data.entities;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import data.DataManager;

public class Examination extends Entity {
	private String title;
	private Date date;
	private String description;
	private Vet vet;
	private Animal animal;
	
	public Examination() {};
	
	public Examination(ResultSet resultSet) throws SQLException {
		this.id = resultSet.getInt("examination.id");
		this.title = resultSet.getString("examination.title");
		this.date = resultSet.getDate("examination.date");
		this.description = resultSet.getString("examination.description");
		
		this.vet = DataManager.getInstance().loadEntityById(Vet.class, resultSet.getInt("examination.vet_id"));
		this.animal = DataManager.getInstance().loadEntityById(Animal.class, resultSet.getInt("examination.animal_id"));
	}
	
	public Examination(String title, Date date, String description, Vet vet, Animal animal) {
		this.title = title;
		this.date = date;
		this.description = description;
		this.vet = vet;
		this.animal = animal;
	}
	
	public Examination(int id, String title, Date date, String description, Vet vet, Animal animal) {
		this.id = id;
		this.title = title;
		this.date = date;
		this.description = description;
		this.vet = vet;
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

	public Vet getVet() {
		return vet;
	}

	public void setVet(Vet vet) {
		this.vet = vet;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	@Override
	public PreparedStatement getSqlInsertStatement(Connection connection) {
		String sql = "INSERT INTO examination(title, date, description, Vet_id, Animal_id) VALUES(?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, title);
			statement.setDate(2, date);
			statement.setString(3, description);
			statement.setInt(4, vet.getId());
			statement.setInt(5, animal.getId());
			return statement;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PreparedStatement getSqlUpdateStatement(Connection connection) {
		String sql = "UPDATE examination SET title = ?, date = ?, description = ?, Vet_id = ?, Animal_id = ? WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, title);
			statement.setDate(2, date);
			statement.setString(3, description);
			statement.setInt(4, vet.getId());
			statement.setInt(5, animal.getId());
			statement.setInt(6, id);
			return statement;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

package data.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class Entity {
	protected int id = -1;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public abstract PreparedStatement getSqlInsertStatement(Connection connection);
	public abstract PreparedStatement getSqlUpdateStatement(Connection connection);
	
	public PreparedStatement getSqlDeleteStatement(Connection connection) {
		String sql = "DELETE FROM " + this.getClass().getSimpleName() + " WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			return statement;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public PreparedStatement getSqlSelectByIdStatement(Connection connection) {
		String sql = "SELECT * FROM " + this.getClass().getSimpleName() + " WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			return statement;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public PreparedStatement getSqlSelectAllStatement(Connection connection) {
		String sql = "SELECT * FROM " + this.getClass().getSimpleName();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			return statement;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

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
	
	/**
	 * Generates a PreparedStatement which can be used to insert the entity into the database.
	 * @param connection The database connection used to create the PreparedStatement.
	 * @return The PreparedStatement for inserting the entity into the database.
	 */
	public abstract PreparedStatement getSqlInsertStatement(Connection connection);
	/**
	 * Generates a PreparedStatement which can be used to update the entity in the database.
	 * @param connection The database connection used to create the PreparedStatement.
	 * @return The PreparedStatement for updating the entity in the database.
	 */
	public abstract PreparedStatement getSqlUpdateStatement(Connection connection);
	
	/**
	 * Generates a PreparedStatement which can be used to delete the entity from the database.
	 * @param connection The database connection used to create the PreparedStatement.
	 * @return The PreparedStatement for deleting the entity from the database.
	 */
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
	
	/**
	 * Generates a PreparedStatement which can be used to select an entity from the database by id.
	 * @param connection The database connection used to create the PreparedStatement.
	 * @return The PreparedStatement for selecting the entity from the database.
	 */
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
	
	/**
	 * Generates a PreparedStatement which can be used to select all entities of the current type from the database.
	 * @param connection The database connection used to create the PreparedStatement.
	 * @return The PreparedStatement for selecting the entities from the database.
	 */
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

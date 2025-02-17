package data;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import data.entities.Entity;
import util.Constants;

public class DataManager {
	private static DataManager instance;
	
	private DataManager() {
		super();
	}

	/**
	 * Gets the single instance of DataManger, and optionally creates it if it doesn't exist yet.
	 * @return The singleton instance of DataManager.
	 */
	public static DataManager getInstance() {
		if (instance == null) {
			instance = new DataManager();
		}
		return instance;
	}
	
	/**
	 * Saves an entity to the database.
	 * @param entity The entity to be saved.
	 */
	public int saveEntity(Entity entity) {
		int newId = entity.getId();
		try (Connection connection = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PASSWORD)){
			boolean isUpdate = entity.getId() > 0;
			PreparedStatement statement = isUpdate ? entity.getSqlUpdateStatement(connection) : entity.getSqlInsertStatement(connection);
			statement.executeUpdate();
			if(!isUpdate) {
				ResultSet rs = statement.getGeneratedKeys();
				if(rs.next()) {
					entity.setId(rs.getInt(1));
					newId = rs.getInt(1);
				}
				rs.close();
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return newId;
	}
	
	/**
	 * Loads and returns a specific entity of a certain class by its id.
	 * @param <T> The generic type of the instance to be returned, a subclass of Entity
	 * @param entityType The class of the entity which should be loaded.
	 * @param id The id of the target entity.
	 * @return An instance of type T.
	 * @see Entity
	 */
	public <T extends Entity> T loadEntityById(Class<T> entityType, int id) {
		T entity = null;
		
		try (Connection connection = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PASSWORD)){
			T tempEntity = entityType.getDeclaredConstructor().newInstance();
			tempEntity.setId(id);
			PreparedStatement statement = tempEntity.getSqlSelectByIdStatement(connection);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				var constructor = entityType.getDeclaredConstructor(ResultSet.class);
				entity = constructor.newInstance(rs);
			}
			rs.close();
			statement.close();
		} catch (SQLException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		return entity;
	}
	
	/**
	 * Loads and returns all entities of a certain type.
	 * @param <T> The generic type of the list to be returned, a subclass of Entity
	 * @param entityType The class of the entities which should be loaded.
	 * @return An ArrayList of type T.
	 * @see Entity
	 */
	public <T extends Entity> ArrayList<T> loadEntities(Class<T> entityType) {
		ArrayList<T> result = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PASSWORD)) {
			PreparedStatement statement = entityType.getDeclaredConstructor().newInstance().getSqlSelectAllStatement(connection);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				var constructor = entityType.getDeclaredConstructor(ResultSet.class);
				result.add(constructor.newInstance(rs));
			}
			rs.close();
			statement.close();
		} catch (SQLException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	/**
	 * Deletes an entity from the database.
	 * @param entity The entity to be deleted.
	 */
	public void deleteEntity(Entity entity) {
		try (Connection connection = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PASSWORD)) {
			PreparedStatement statement = entity.getSqlDeleteStatement(connection);
			statement.executeUpdate();
		} catch (SQLException | IllegalArgumentException | SecurityException e) {
			e.printStackTrace();
		}
	}
	
}

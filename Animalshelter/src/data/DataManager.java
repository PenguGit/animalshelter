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

	public static DataManager getInstance() {
		if (instance == null) {
			instance = new DataManager();
		}
		return instance;
	}
	
	public void saveEntity(Entity entity) {
		try (Connection connection = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PASSWORD)){
			PreparedStatement statement = entity.getId() > 0 ? entity.getSqlUpdateStatement(connection) : entity.getSqlInsertStatement(connection);
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.first()) {
				entity.setId(rs.getInt(1));
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public <T extends Entity> T loadEntityById(Class<T> entityType, int id) {
		T entity = null;
		
		try (Connection connection = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PASSWORD)){
			PreparedStatement statement = entityType.getDeclaredConstructor().newInstance().getSqlSelectByIdStatement(connection);
			ResultSet rs = statement.executeQuery();
			if(rs.first()) {
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
	
	public void deleteEntity(Entity entity) {
		try (Connection connection = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PASSWORD)) {
			PreparedStatement statement = entity.getSqlDeleteStatement(connection);
			statement.executeUpdate();
		} catch (SQLException | IllegalArgumentException | SecurityException e) {
			e.printStackTrace();
		}
	}
}

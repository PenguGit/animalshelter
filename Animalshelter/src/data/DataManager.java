package data;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import data.entities.Animal;
import data.entities.AnimalType;
import data.entities.Entity;
import data.entities.Patron;
import data.entities.Room;
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
	
	public int saveEntity(Entity entity) {
		int newId = -1;
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

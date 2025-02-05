package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}

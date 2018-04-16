package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	private Connection connection;
	private Statement state;

	public Database(Config cfg) throws SQLException, ClassNotFoundException {
		Class.forName(Config.driver);
		connection = DriverManager.getConnection(cfg.getConnectionString(),
				cfg.user, cfg.pwd);
		state = connection.createStatement();
	}

	public ResultSet exec(String query, boolean isupdate) throws SQLException {
		if (isupdate) {
			state.executeUpdate(query);
			return null;
		}

		return state.executeQuery(query);
	}

}

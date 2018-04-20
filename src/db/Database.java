package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import objects.Quote;

public class Database {
	private Connection connection;
	private Statement state;

	public Database() throws SQLException, ClassNotFoundException {
		this(Config.d_cfg);
	}

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
		} else {
			return state.executeQuery(query);
		}
	}

	public void publishQuote(Quote q) throws SQLException, ParseException {
		String query = "INSERT INTO q_quote(idAuthor, "
				+ (q.getIdUser() == null ? "" : "idUser, ") + "content) "
				+ "VALUES (" + q.getIdAuthor() + ", "
				+ (q.getIdUser() == null ? "" : q.getIdUser() + ",")
				+ "'" + q.getContent() + "')";
		System.out.println(query);
		exec(query, true);
	}
	
	public void getUser(String login) {
		
	}
}

package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;

import objects.Quote;
import objects.User;

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
		String query = "INSERT INTO q_quote(idAuthor, content) " + "VALUES ("
				+ q.getAuthor().getIdUser() + ", '" + q.getContent() + "')";
		System.out.println(query);
		exec(query, true);
	}

	public User getUser(Integer idu) throws SQLException {
		ResultSet r = exec("SELECT * FROM q_user WHERE idUser = " + idu, false);
		if (r.next()) {
			return new User(idu, r.getBoolean("isAdmin"), r.getString("login"),
					r.getString("pwd"), r.getString("f_name"),
					r.getString("l_name"), r.getString("email"));
		} else {
			return null;
		}
	}

	public ArrayList<Quote> getHomeQuotes(User u) {
		return getHomeQuotes(u, null);
	}

	public ArrayList<Quote> getHomeQuotes(User u, Integer limit) {
		ArrayList<Quote> list = new ArrayList<Quote>();
		String query = "select distinct qq.idQuote, qq.created, qq.idAuthor, qq.content, qs.idUser  "
				+ "from q_quote qq, q_user qu, q_relation qr, q_share qs  "
				+ "where  qu.idUser = "
				+ u.getIdUser()
				+ " and qu.idUser = qr.idFollower and "
				+ " (qq.idQuote = qs.idQuote or qq.idAuthor = qu.idUser) and  "
				+ " (qs.idUser = qr.idFollowing or"
				+ " qs.idUser = qr.idFollower or"
				+ " qq.idAuthor = qr.idFollowing or"
				+ " qq.idAuthor = qr.idFollower)" + " group by (idQuote) order by created desc";
		System.out.println(query);
		try {
			ResultSet tmp = new Database().exec(query, false);
			while (tmp.next()) {
				list.add(new Quote(tmp.getInt("idQuote"), tmp
						.getInt("idAuthor"), tmp.getDate("created"), tmp
						.getString("content")));
				if (limit != null) {
					if (limit != 0)
						limit--;
					else
						break;
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return list;
	}
}

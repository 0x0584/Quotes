package ctrl;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.Config;
import db.Database;

@SuppressWarnings("serial")
@WebServlet("/Auth")
public class Auth extends HttpServlet {
	public Auth() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// this would be
		String login = request.getParameter("login");
		String pwd = request.getParameter("pwd");
		ResultSet tmp = null;

		try {
			tmp = new Database(Config.d_cfg).exec("SELECT * FROM q_user "
					+ "WHERE login = '" + login + "' AND pwd = '" + pwd + "'",
					false);
		} catch (ClassNotFoundException | SQLException e1) {
			response.getWriter().println("this is bad");
		}

		try {
			if (tmp.next()) {
				response.sendRedirect(tmp.getString("isAdmin") == "t" ? "admin.jsp"
						: "index.jsp");
			} else {
				response.sendRedirect("welcome.jsp?err=1");
			}
		} catch (SQLException e2) {
		}
	}
}

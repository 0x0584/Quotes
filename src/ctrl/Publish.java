package ctrl;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import objects.Quote;
import objects.User;
import db.Database;

@SuppressWarnings("serial")
@WebServlet("/Publish")
public class Publish extends HttpServlet {
	public Publish() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/* retrieve the logged-in user from the session */
		User user = (User) request.getSession().getAttribute("current_user");

		if (user == null) {
			response.sendRedirect("welcome.jsp");
		}

		try {
			new Database().publishQuote(new Quote(user.getIdUser(),
					null, request.getParameter("content")));
			response.sendRedirect("index.jsp?published=1");
		} catch (ClassNotFoundException | SQLException | ParseException e) {
			response.getWriter().println("this is not good");
			response.getWriter().println(e.getMessage());
		}
	}
}

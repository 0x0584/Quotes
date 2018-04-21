<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@page import="java.util.ArrayList"%>
<%@page import="objects.*"%>
<%@page import="db.Database"%>
<%
	if (request.getSession().getAttribute("current_user") == null)
		response.sendRedirect("welcome.jsp");
	/*
	if (((String) request.getAttribute("published")).compareTo("1") == 0) {
		out.println("posted");
	}
	 */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/960.css">

<style></style>
<title>Quotes - share your ideas</title>
</head>

<body>
	<div class=" header container_16">
		<!-- logo -->
		<div class="left grid_4 logo">
			<h1>Quotes</h1>
		</div>
		<!-- menu -->
		<div class="right grid_12 ">
			<ul>
				<li><a href="#">Home</a></li>
				<li><a
					href="profile.jsp?user = <%out.print(request.getSession().getAttribute("current_user"));%>"><%
							out.print(request.getSession().getAttribute("current_user"));
						%></a></li>
				<li><a href="#">Logout</a></li>
				<li><input type="text" name="search" placeholder="Search ..."
					class="input-group"> <!-- this is what i called "useful comment!" -->
					<!-- this should be done using tags [user]:this --> <input
					type="submit" value="Find" name="dosearch" class="btn-search">
				</li>
			</ul>
		</div>

	</div>

	<div id="main-" class="container_16">
		<!-- posting  -->
		<div class="post- grid_16" align="center">
			<form action="Publish" method="post">
				<div>
					<textarea name="content" placeholder="what's on your mind?"></textarea>
				</div>
				<input type="submit" name="submission" class="btn" value="Quote!">
			</form>
		</div>

		<!-- publishing -->

		<%
			User foo = (User) request.getSession().getAttribute("current_user");
			if (foo != null) {
				ArrayList<Quote> list = new Database().getHomeQuotes(foo);
				for (Quote q : list) {
					out.println(q.toHtmlTag(null));
				}
			}
		%>
	</div>

	<!-- this should be on one line, without the bullets -->
	<!-- yeah.. but wait, what? -->
	<footer class="container_16"> </footer>

</body>
</html>

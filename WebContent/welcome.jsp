<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%
	if (request.getSession().getAttribute("current_user") != null)
		response.sendRedirect("index.jsp");
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
<link rel="stylesheet" type="text/css" href="css/login.css" />
<style>
</style>
</head>

<body>
	<div class="container_16">


		<!-- ==================| BEGIN LOGO |================== -->
		<div class="logo grid_16" align="center">
			<h1>Quotes</h1>
			<p>
				<em> This is a place to share quotes with the world <br>
					Whether they are yours or belong to someone else
				</em>
			</p>
			<br>
		</div>
		<!-- ==================| END LOGO |================== -->
		<!-- this is signup side -->
		<div class="left grid_8">
			<span class="welcome-msg"> <br>
			<em>Welcome,<br> you can <a href="signup.html">create
						an account</a></em>
			</span>
<%
	if (request.getParameter("err") == "1") {
%>
		<p> this is an err, and would be reported. </p>
<%
	}
%>
		</div>
		<br>
		<!-- this is login side -->
		<div style="float: right" class=" sign-in grid_80">
			<form class="forms" method="POST" action="Auth">
				<div class="login-info">
					<div class="group_form">
						<input class="form_control" type="text" name="login" id="usrname"
							autofocus placeholder="Username / Email ..." required>
					</div>
					<div class="group_form has-border">
						<input class="form_control" type="Password" name="pwd"
							id="Password" placeholder="Password ..." required>
					</div>
				</div>
				<a href="#" class="forgot_pass" target="">Forgot your password ?</a>
				<input class="buttons" type="submit" name="submit" value="Sign In">

			</form>

		</div>

	</div>
</body>
</html>
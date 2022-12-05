<%@page import="Model.BEAN.User"%>
<%@page import="Model.BEAN.Skin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update User Form</title>
</head>
<body>
	<h2>Update User</h2>
	
	<form method="post" action="UserServlet?action=update">
		<%
		User user = (User) request.getAttribute("user");
		out.println("<input name='userID' type='text' id='userID' value='" + user.getId()
				+ "' style=\"visibility: hidden; position: absolute; top: 0;\">");
		%>
		<table width="400" border="0" cellspacing="1" cellpadding="2">
			<tr>
				<td width='100'>Id</td>
				<td>
					<%
					out.println("<input name='Id' type='text' id='Id' value='" + user.getId() + "' disabled>");
					%>
				</td>
			</tr>
			<tr>
				<td width='100'>Username</td>
				<td>
					<%
					out.println("<input name='Username' type='text' id='Username' value='" + user.getUsername() + "' placeholder='username'>");
					%>
				</td>
			</tr>
			<tr>
				<td width='100'>Password</td>
				<td>
					<%
					out.println("<input name='Password' type='text' id='Password' value='" + user.getPassword() + "' placeholder='Password'>");
					%>
				</td>
			</tr>
			<tr>
				<td width='100'>Balance</td>
				<td>
					<%
					out.println("<input name='Balance' type='number' id='Balance' value='" + user.getBalance() + "' placeholder='Balance'>");
					%>
				</td>
			</tr>
			<tr>
				<td width="100"></td>
				<td><input name="submit" type="submit" id="submit"
					value="Update"></td>
			</tr>
		</table>
	</form>
</body>
</html>
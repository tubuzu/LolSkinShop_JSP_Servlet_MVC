<%@page import="Model.BEAN.User"%>
<%@page import="Model.BEAN.Skin"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage User</title>
<style>
.hide-input {
	visibility: hidden;
	position: absolute;
	top: 0;
}

.search-container {
	margin-bottom: 20px;
}
</style>
</head>
<body>
	<h1 style="text-align: center;">Quan li user</h1>
	<div class="search-container">
		<form name="searchForm" id="searchForm" method="POST"
			action="UserServlet?action=guiManage">
			Username <input name='searchUsername' type='text' id='searchUsername'
				placeholder='username'> Min Balance <input name='minBalance'
				type='number' id='minBalance' placeholder='minBalance'> <input
				name="searchBtn" form="searchForm" type="submit" id="searchBtm"
				value="Search">
		</form>
	</div>
	<form name="formDelete" id="formDelete"
		action="UserServlet?action=deleteMany" method="POST">
		<table width="100%" border="1">
			<tr>
				<th>ID</th>
				<th>Username</th>
				<th>Password</th>
				<th>Balance</th>
				<th>Delete user</th>
				<th>Update user</th>
			</tr>
			<%
			ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");
			for (User nv : users) {
				out.println("<tr>");
				out.println("<td>" + nv.getId() + "</td>");
				out.println("<td>" + nv.getUsername() + "</td>");
				out.println("<td>" + nv.getPassword() + "</td>");
				out.println("<td>" + nv.getBalance() + "</td>");
				out.println("<td><input type='checkbox' name='" + nv.getId() + "' value='" + nv.getId() + "'></td>");
				out.println("<td><a href='UserServlet?action=guiUpdate&updateUserID=" + nv.getId() + "'>Update</a></td>");
				out.println("</tr>");
			}
			%>
			<tr>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
				<th><input type="submit" form="formDelete" value="Delete">
				</th>
				<th></th>
			</tr>
		</table>
	</form>
</body>
</html>
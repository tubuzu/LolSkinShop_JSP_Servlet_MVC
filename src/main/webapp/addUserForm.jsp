<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add user</title>
</head>
<body>
	<h2>Add user</h2>
	<form method="post" action="UserServlet?action=add">
		<table width="400" border="0" cellspacing="1" cellpadding="2">
			<tr>
				<td width='100'>Username</td>
				<td><input name='Username' type='text' id='Username'
					placeholder='Username'></td>
			</tr>
			<tr>
				<td width='100'>Password</td>
				<td><input name='Password' type='text' id='Password'
					placeholder='Password'></td>
			</tr>
			<tr>
				<td width="100"></td>
				<td><input name="submit" type="submit" id="submit"
					value="Add"></td>
			</tr>
		</table>
	</form>
</body>
</html>
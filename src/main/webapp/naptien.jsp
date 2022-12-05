<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nap tien</title>
</head>
<body>
	<h2>Nap tien</h2>
	<form method="post" action="UserServlet?action=incBalance">
		<table width="400" border="0" cellspacing="1" cellpadding="2">
			<tr>
				<td width='100'>Amount</td>
				<td><input name='Amount' type='number' id='Amount'
					placeholder='Amount'></td>
			</tr>
			<tr>
				<td width="100"></td>
				<td><input name="submit" type="submit" id="submit"
					value="Nap"></td>
			</tr>
		</table>
	</form>
</body>
</html>
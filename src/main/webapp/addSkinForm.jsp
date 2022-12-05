<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add skin</title>
</head>
<body>
	<h2>Add skin</h2>
	<form method="post" action="SkinServlet?action=add">
		<table width="400" border="0" cellspacing="1" cellpadding="2">
			<tr>
				<td width='100'>Name</td>
				<td><input name='Name' type='text' id='Name'
					placeholder='skin name'></td>
			</tr>
			<tr>
				<td width='100'>Img</td>
				<td><input name='Img' type='text' id='Img'
					placeholder='Img url'></td>
			</tr>
			<tr>
				<td width='100'>Price</td>
				<td><input name='Price' type='number' id='Price'
					placeholder='Price'></td>
			</tr>
			<tr>
				<td width='100'>Tier</td>
				<td><select name="Tier" id="Tier">
						<option value="1" selected>Classic</option>
						<option value="2">Standard</option>
						<option value="3">Epic</option>
						<option value="4">Legendary</option>
						<option value="5">Mythic</option>
						<option value="6">Ultimate</option>
				</select></td>
			</tr>
			<tr>
				<td width='100'>Champion</td>
				<td><input name='Champion' type='text' id='Champion'
					placeholder='Champion'></td>
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
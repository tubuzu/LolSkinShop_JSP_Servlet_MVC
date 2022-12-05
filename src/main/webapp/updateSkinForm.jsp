<%@page import="Model.BEAN.Skin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Update skin</h2>
	
	<form method="post" action="SkinServlet?action=update">
		<%
		Skin skin = (Skin) request.getAttribute("skin");
		out.println("<input name='skinID' type='text' id='skinID' value='" + skin.getId()
				+ "' style=\"visibility: hidden; position: absolute; top: 0;\">");
		%>
		<table width="400" border="0" cellspacing="1" cellpadding="2">
			<tr>
				<td width='100'>Id</td>
				<td>
					<%
					out.println("<input name='Id' type='text' id='Id' value='" + skin.getId() + "' disabled>");
					%>
				</td>
			</tr>
			<tr>
				<td width='100'>Name</td>
				<td>
					<%
					out.println("<input name='Name' type='text' id='Name' value='" + skin.getName() + "' placeholder='skin name'>");
					%>
				</td>
			</tr>
			<tr>
				<td width='100'>Img</td>
				<td>
					<%
					out.println("<input name='Img' type='text' id='Img' value='" + skin.getImg() + "' placeholder='Img url'>");
					%>
				</td>
			</tr>
			<tr>
				<td width='100'>Price</td>
				<td>
					<%
					out.println("<input name='Price' type='number' id='Price' value='" + skin.getPrice() + "' placeholder='Price'>");
					%>
				</td>
			</tr>
			<tr>
				<td width='100'>Tier</td>
				<td><select name='Tier' id='Tier'>
						<!-- 						<option value="1">Classic</option> -->
						<!-- 						<option value="2">Standard</option> -->
						<!-- 						<option value="3">Epic</option> -->
						<!-- 						<option value="4">Legendary</option> -->
						<!-- 						<option value="5">Mythic</option> -->
						<!-- 						<option value="6">Ultimate</option> -->
						<%
						String[] tiers = new String[] { "Classic", "Standard", "Epic", "Legendary", "Mythic", "Ultimate" };
						for (int i = 0; i < tiers.length; i++) {
							if (i + 1 == skin.getTier())
								out.println("<option value='" + (i + 1) + "' selected>" + tiers[i] + "</option>");
							else
								out.println("<option value='" + (i + 1) + "'>" + tiers[i] + "</option>");
						}
						%>
				</select></td>
			</tr>
			<tr>
				<td width='100'>Champion</td>
				<td>
					<%
					out.println(
							"<input name='Champion' type='text' id='Champion' value='" + skin.getChampion() + "' placeholder='Champion'>");
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
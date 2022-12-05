<%@page import="Model.BEAN.Skin"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Skin</title>
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
	<h1 style="text-align: center;">Quan li skin</h1>
	<div class="search-container">
		<form name="searchForm" id="searchForm" method="POST" action="SkinServlet?action=guiManage">
			Name <input name='skinName' type='text' id='skinName' placeholder='skin name'>
			Tier <select name="skinTier" id="skinTier">
				<option value="0" selected>All</option>
				<option value="1">Classic</option>
				<option value="2">Standard</option>
				<option value="3">Epic</option>
				<option value="4">Legendary</option>
				<option value="5">Mythic</option>
				<option value="6">Ultimate</option>
			</select> <input name="searchBtn" form="searchForm" type="submit" id="searchBtm" value="Search">
		</form>
	</div>
	<form name="formDelete" id="formDelete"
		action="SkinServlet?action=deleteMany" method="POST">
		<table width="100%" border="1">
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Img</th>
				<th>Price</th>
				<th>Tier</th>
				<th>Champion</th>
				<th>Delete skin</th>
				<th>Update skin</th>
			</tr>
			<%
			ArrayList<Skin> skins = (ArrayList<Skin>) request.getAttribute("skins");
			for (Skin nv : skins) {
				out.println("<tr>");
				out.println("<td>" + nv.getId() + "</td>");
				out.println("<td>" + nv.getName() + "</td>");
				out.println("<td>" + nv.getImg() + "</td>");
				out.println("<td>" + nv.getPrice() + "</td>");
				out.println("<td>" + nv.getTier() + "</td>");
				out.println("<td>" + nv.getChampion() + "</td>");
				out.println("<td><input type='checkbox' name='" + nv.getId() + "' value='" + nv.getId() + "'></td>");
				// 				out.println("<td><input type='submit' form='formUpdate' onClick='setUpdateID(" + nv.getId() + ")' name='"
				// 				+ nv.getId() + "' value='Update'></td>");
				out.println("<td><a href='SkinServlet?action=guiUpdate&updateSkinID=" + nv.getId() + "'>Update</a></td>");
				out.println("</tr>");
			}
			%>
			<tr>
				<th></th>
				<th></th>
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
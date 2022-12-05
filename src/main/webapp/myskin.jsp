<%@page import="Model.BO.SkinBO"%>
<%@page import="Model.BEAN.Transaction"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.BEAN.Skin"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Skin list</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@300;500;600;700&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css"
	integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<link rel="stylesheet" href="index.css">
<body>
	<h1 style="text-align: center;">Skin list</h1>
	<div class="skin-container">
		<%
		ArrayList<Transaction> transactions = (ArrayList<Transaction>) request.getAttribute("transactions");
		SkinBO skinBO = new SkinBO();
		String[] badges = {"", "", "Epic_Skin.png", "Legendary_Skin.png", "Hextech_Skin.png", "Ultimate_Skin.png"};
		for (Transaction nv : transactions) {
			Skin skin = skinBO.getSkinByID(nv.getSkinId()).get(0);
			out.println("<div class=\"skin\" style=\"background-image: url(" + skin.getImg() + ")\">");
			out.println("<p class=\"skin-name\">" + skin.getName() + "</p>");
			out.println("<img class=\"skin-badge\" src='./assets/"+badges[skin.getTier()-1]+"'>");
			out.println("</div>");
		}
		%>
	</div>
</body>
</html>
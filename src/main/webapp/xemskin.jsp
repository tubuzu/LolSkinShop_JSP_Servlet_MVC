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
	<div class="current-balance">
		<%
		if (request.getAttribute("balance") != null) {
			out.print(request.getAttribute("balance").toString() + " RP");
		}
		%>
	</div>
	<div class="search-container">
		<form method="POST" action="SkinServlet">
			Name <input name='Name' type='text' id='Name' placeholder='skin name'>
			Tier <select name="Tier" id="Tier">
				<option value="0" selected>All</option>
				<option value="1">Classic</option>
				<option value="2">Standard</option>
				<option value="3">Epic</option>
				<option value="4">Legendary</option>
				<option value="5">Mythic</option>
				<option value="6">Ultimate</option>
			</select> <input name="search" type="submit" id="search" value="Search">
		</form>
	</div>
	<div class="skin-container">
		<%
		ArrayList<Skin> skins = (ArrayList<Skin>) request.getAttribute("skins");
		String[] badges = {"", "", "Epic_Skin.png", "Legendary_Skin.png", "Hextech_Skin.png", "Ultimate_Skin.png"};
		for (Skin nv : skins) {
			out.println("<div class=\"skin\" style=\"background-image: url(" + nv.getImg() + ")\">");
			out.println("<p class=\"skin-name\">" + nv.getName() + "</p>");
			out.println("<p class=\"skin-price\">" + nv.getPrice() + " RP</p>");
			out.println("<img class=\"skin-badge\" src='./assets/"+badges[nv.getTier()-1]+"'>");
			out.println("<div class=\"overlay\"><button class=\"btn buy-btn\" onClick=\"selectSkin('" + nv.getId() + "','"
			+ nv.getName() + "','" + nv.getPrice() + "')\">Buy</button></div>");
			out.println("</div>");
		}
		%>
	</div>
	<div class="modal-overlay">
		<div class="modal-buy">

			<i class="fa-solid fa-circle-xmark close-icon"></i>
			<div class="modal-content">
				<%
				out.println("<p class=\"modal-question\"></p>");
				%>
				<div class="modal-btn-container">
					<form method="post" action="TransactionServlet?action=buySkin">
						<input name='Id' type='text' id='selectedSkinId'
							style="display: none;">
						<button class="btn modal-btn ok-btn" type="submit">OK</button>
						<button class="btn modal-btn cancel-btn">Cancel</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"
		integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA=="
		crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script>
		let selectSkin = (id, name, price) => {
			console.log(id,name,price);
			$("#selectedSkinId").val(id);
			$(".modal-question").text("Bạn có muốn mua skin "+name+" với giá "+price+" RP?");
			$(".modal-overlay").css("display", "flex");
		}
		$(".cancel-btn, .close-icon").click(function(e) {
			e.preventDefault();
			$(".modal-overlay").css("display", "none");
			$("#selectedSkinId").val("");
		})
	</script>
</body>
</html>
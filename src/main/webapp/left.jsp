<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Document</title>
<style>
* {
	box-sizing: border-box;
}

.link {
	text-decoration: none;
	font-weight: bold;
	font-size: 18px;
	display: block;
	padding: 12px 8px;
	width: 100%;
}

body {
	margin: 0;
}

.link:hover {
	background-color: rgb(218, 216, 216);
}
</style>
</head>
<body>
	<a class="link" href="index.html" target="_parent">Home</a>
	<a class="link" target="content" href="SkinServlet">Skin List</a>
	<a class="link" target="content" href="SkinServlet?action=guiAdd">Add
		skin</a>
	<a class="link" target="content" href="SkinServlet?action=guiManage">Manage skin</a> 
	<a class="link" target="content" href="UserServlet?action=guiAdd">Add user</a>
	<a class="link" target="content" href="UserServlet?action=guiManage">Manage user</a>
	<a class="link" target="content" href="UserServlet?action=guiMySkin">My skin</a>
	<a class="link" target="content" href="UserServlet?action=guiRecharge">Recharge</a>
	<!--     <a class="link" target="content" href="PhongbanServlet">Xem phòng ban</a>  -->
	<!--     <a class="link" target="content" href="NhanvienServlet?action=search">Tìm kiếm</a>  -->
	<!--     <a class="link" target="content" href="PhongbanServlet">Cập nhật thông tin</a> -->
	<!--     <a class="link" target="content" href="NhanvienServlet?action=guiDelete1">Xoá</a> -->
	<!--     <a class="link" target="content" href="NhanvienServlet?action=guiDeleteMany">Xoá tất cả</a> -->
	<%
	if (request.getSession().getAttribute("login") != null) {
		out.println("<a class=\"link\" target=\"content\" href=\"LogoutServlet\" onClick=\"reloadMenu()\">Logout</a>");
	} else
		out.println("<a class=\"link\" target=\"content\" href=\"login.jsp\">Login</a>");
	%>
	<a id="reload-link" target="left" href="left.jsp"
		style="visibility: hidden; position: absolute; top: 0;"></a>
	<a class="link" target="left" href="left.jsp">Reload</a>
	<script>
		let reloadMenu = () => {
			var link = document.getElementById('reload-link');
			link.click();
		}
	</script>
</body>
</html>
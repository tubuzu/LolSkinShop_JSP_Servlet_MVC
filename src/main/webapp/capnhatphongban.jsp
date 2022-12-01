<%@page import="Model.BEAN.Phongban"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cập nhật phòng ban</title>
</head>
<body>
	<% Phongban phongban = (Phongban)request.getAttribute("phongban"); %>
	<h1 style="text-align:center">Cập nhật phòng ban</h1>
		<% 
		String notify = (String)request.getAttribute("notify"); 
		out.println("<h4 style='color:red'>" + notify + "</h4>");
	%>
	<form action="PhongbanServlet?action=update" method="POST">
		<div>
			<input type="text" readonly name="IDPB" value="<% out.println(phongban.getIDPB()); %>">
		</div>
		<table>
			<tr>
				<th>Tên phòng ban: </th>
				<td>
					<input type="text" name="Tenpb" value="<% out.println(phongban.getTenpb()); %>">
				</td>
			</tr>
			
			<tr>
				<th>Mô tả</th>
				<td>
					<input type="text" name="Mota" value="<% out.println(phongban.getMota()); %>">
				</td>
			</tr>
			<tr>
				<th></th>
				<td>
					<input type="submit" value="Cập nhật">
				</td>
			</tr>
		</table>
		
	</form>
</body>
</html>
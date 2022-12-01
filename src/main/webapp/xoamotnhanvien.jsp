<%@page import="java.util.ArrayList"%>
<%@page import="Model.BEAN.Nhanvien"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Xem nhân viên</title>
</head>

<body>
	<h1 style="text-align: center;">Xoá nhân viên</h1>
	<table width="100%" border="1">
		<tr>
			<th>IDNV</th>
			<th>Họ tên</th>
			<th>Địa chỉ</th>
			<th>IDPB</th>
			<th>Xoá nhân viên</th>
		</tr>
		<%
			ArrayList<Nhanvien> nhanviens = (ArrayList<Nhanvien>)request.getAttribute("nhanviens");
			for(Nhanvien nv : nhanviens) {
				out.println("<tr>");
				out.println("<td>" + nv.getIDNV() +"</td>");
				out.println("<td>" + nv.getHoten() +"</td>");
				out.println("<td>" + nv.getDiachi() +"</td>");
				out.println("<td>" + nv.getIDPB() +"</td>");
				out.println("<td><a href='NhanvienServlet?action=delete1&IDNV=" + nv.getIDNV() +"'>Xoá nhân viên</a></td>");
				out.println("</tr>");
			}
		%>
	</table>
</body>
</html>
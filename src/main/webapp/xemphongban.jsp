<%@page import="Model.BEAN.Phongban"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 style="text-align: center;">Xem phòng ban</h1>
	<table width="100%" border="1">
		<tr>
			<th>IDpb</th>
			<th>Tên phòng ban</th>
			<th>Mô tả</th>
			<th>Chi tiết</th>
			<th>Cập nhật</th>
		</tr>
		<%
			ArrayList<Phongban> phongbans = (ArrayList<Phongban>)request.getAttribute("phongbans");
			for(Phongban pb : phongbans) {
				out.println("<tr>");
				out.println("<td>" + pb.getIDPB() +"</td>");
				out.println("<td>" + pb.getTenpb() +"</td>");
				out.println("<td>" + pb.getMota() +"</td>");
				out.println("<td><a href='PhongbanServlet?action=detail&IDPB=" + pb.getIDPB() +"'>Xem chi tiết</a></td>");
				out.println("<td><a href='PhongbanServlet?action=update&IDPB=" + pb.getIDPB() +"'>Cập nhật</a></td>");
				out.println("</tr>");
			}
		%>
	</table>
</body>
</html>
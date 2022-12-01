<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Model.BEAN.Nhanvien"%>
<%@ page language="java" contentType="text/html; charset=UTF8"
    pageEncoding="UTF8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>Tìm kiếm thông tin nhân viên</h1>
    <form action="NhanvienServlet?action=search" method="GET" onsubmit="return check()" name="formSearch">
        <input type="text" value="search" name="action" hidden>
        Tìm kiếm nhân viên theo: 
        <input type="radio" name="searchBy" value="IDNV">IDNV
        <input type="radio" name="searchBy" value="Hoten">Họ tên
        <input type="radio" name="searchBy" value="Diachi">Địa chỉ
        <input type="radio" name="searchBy" value="All">Tất cả<br>
        Nhập thông tin tìm kiếm: <input type="text" name="searchVal"><br>
        <input type="reset" value="Reset">        
        <input type="submit" value="OK">        
    </form>


    <table width="100%" border="1">
		<tr>
			<th>IDNV</th>
			<th>Họ tên</th>
			<th>Địa chỉ</th>
			<th>IDPB</th>
		</tr>
		<%
			ArrayList<Nhanvien> nhanviens = (ArrayList<Nhanvien>)request.getAttribute("nhanviens");
			for(Nhanvien nv : nhanviens) {
				out.println("<tr>");
				out.println("<td>" + nv.getIDNV() +"</td>");
				out.println("<td>" + nv.getHoten() +"</td>");
				out.println("<td>" + nv.getDiachi() +"</td>");
				out.println("<td>" + nv.getIDPB() +"</td>");
				out.println("</tr>");
			}
		%>
	</table>
</body>
</html>
<script>
    function check() {
        if(document.formSearch.searchBy.value == '') {
            alert("Vui lòng chọn tiêu chí tìm kiếm");
            return false;
        }
        return true;
    }
</script>
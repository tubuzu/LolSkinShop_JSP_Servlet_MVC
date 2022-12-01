<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Chào mừng bạn đến với ứng dụng QUẢN LÝ PHÒNG BAN, hãy login để thực hiện các thao tác</h1>
	<form action="CheckLoginServlet" method="POST">
		<div>
			Username: <input type="text" name="username">
		</div>
		<div>
			Password: <input type="password" name="password">
		</div>
		<input type="submit" value="Login">
	</form>
</body>
</html>
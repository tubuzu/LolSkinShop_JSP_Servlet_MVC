<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Hãy login để
		thực hiện các thao tác</h1>
	<form action="CheckLoginServlet" method="POST">
		<div>
			Username: <input type="text" name="username">
		</div>
		<div>
			Password: <input type="password" name="password">
		</div>
		<input type="submit" value="Login" onClick="reloadMenu()">
	</form>
	<a id="reload-link" target="left" href="left.jsp"
		style="visibility: hidden; position: absolute; top: 0;"></a>
	<script>
		let reloadMenu = () => {
			var link = document.getElementById('reload-link');
			link.click();
		}
	</script>
</body>
</html>
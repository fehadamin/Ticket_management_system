<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	ServletContext ctx = getServletContext();
	String Url = ctx.getInitParameter("url");
	String viewpath = ctx.getInitParameter("viewpath");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>form</title>
<link rel="stylesheet" href="<%=Url%>resources/css/login.css" />
</head>

<body>
	<div class="login-box">
		<img src="<%=Url%>resources/images/login.png" class="image">
		<h1>login here</h1>
		<form action="auth.htm" method="post">
			<p>Email</p>
			<input type="text" autocomplete="off" name="email"
				placeholder="Enter The Email id">
			<p>Password</p>
			<input type="password" autocomplete="off" name="password"
				placeholder="Enter password"> <input type="submit"
				name="submit" value="Login">
		</form>
		<p>
			<c:if test="${message != null}">
				<h1><c:out value="${message}" /></h1>
			</c:if>
		</p>
	</div>

</body>
</html>
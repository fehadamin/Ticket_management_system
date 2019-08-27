<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
    	ServletContext ctx = getServletContext();
    	String Url= ctx.getInitParameter("url");
    	String viewpath=ctx.getInitParameter("viewpath");
    %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
		<link rel="stylesheet" href="<%=Url%>resources/css/main.css" />
	</head>
<body>


	Home page
	
	this is index
	<a href="<%=Url%>login.htm"> LOGIN LINK</a>
</body>
</html>

<script src="<%=Url%>resources/js/main.js"></script>
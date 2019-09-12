<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
    <%
    	ServletContext ctx = getServletContext();
    	String Url= ctx.getInitParameter("url");
    	String viewpath=ctx.getInitParameter("viewpath");
    %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>admin panel</title>
    <link rel="stylesheet" href="<%=Url%>resources/css/dashboard.css" />
    <link rel="stylesheet" href="<%=Url%>resources/css/create_employee.css" />
 
    <link rel="stylesheet" href="<%=Url%>resources/css/tables.css" />
  	<link rel="stylesheet" href="<%=Url%>resources/css/create_department.css" />
    <link rel="stylesheet" href="<%=Url%>resources/css/create_ticket.css" />
	<link rel="stylesheet" href="<%=Url%>resources/css/common.css" />
	<link rel="stylesheet" href="<%=Url%>resources/css/jqueryui.css" />	
	<link rel="stylesheet" href="<%=Url%>resources/css/grid.css" />	
		
</head>
<body>
     
     
     <jsp:include page="include/header.jsp"/>
     <jsp:include page="include/sidebar.jsp"/>
     
	    <div class="data"><br>
	      
	      	<jsp:include page="pages/${pageName}.jsp"/>
	      
	    </div> 

    <jsp:include page="include/footer.jsp"/>
    
</body>
</html>

<script
  src="https://code.jquery.com/jquery-3.4.1.min.js"
  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
  crossorigin="anonymous"></script>
  
<script
  src="https://code.jquery.com/ui/1.12.0/jquery-ui.min.js"
  integrity="sha256-eGE6blurk5sHj+rmkfsGYeKyZx3M4bG+ZlFyA7Kns7E="
  crossorigin="anonymous"></script>
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script src="<%=Url%>resources/js/toexcel.js"></script>  
  
<script src="<%=Url%>resources/js/main.js"></script>
<script src="<%=Url%>resources/js/validations.js"></script>







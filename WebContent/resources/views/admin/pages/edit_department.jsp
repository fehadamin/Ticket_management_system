<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
      import="java.util.*,com.ticket.entity.*"%>
<%
    	ServletContext ctx = getServletContext();
    	String Url= ctx.getInitParameter("url");
    	String viewpath=ctx.getInitParameter("viewpath");
    	
    	Department d= (Department) request.getAttribute("department");
    %>


	<form action="admin-department-edit-store.htm" method="post" >
    <div class="box">
        
            <h1> Edit department</h1>
            <div class="form-group">
            <label> Name</label>
            <input class="form-control" type="text" name="departmentname" value="<%= d.getDepartmentName() %>" placeholder="Enter department name"> 
            </div>
            <input type="hidden" name="id" value="<%= d.getDepartmentId()%>">
                <input type="submit" name="submit" class="form-btn" value="edit department">
    </div>
    </form>

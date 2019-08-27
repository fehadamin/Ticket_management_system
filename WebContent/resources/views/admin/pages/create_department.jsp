<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	ServletContext ctx = getServletContext();
	String Url = ctx.getInitParameter("url");
	String viewpath = ctx.getInitParameter("viewpath");
%>


<form action="admin-department-store.htm" method="post"
	onclick="return department_validation(event);">
	<div class="box">

		<h1>create department</h1>
		
		
		<%-- <input type="hidden" name="url" value="<%=Url%>" id="url"/> --%>
		<div class="form-group">
			<label> name<span class="alert">*</span></label> <input type="text"
				name="departmentname" class="form-control" id="departmentname"
				required placeholder="Enter department name">
		</div>
		<input type="submit" name="submit" class="form-btn"
			value="create department">

		<div id="eresult" style="color: firebrick; display: block;"></div>
	</div>

</form>


<script>
	
	
	
	
	
	
</script>
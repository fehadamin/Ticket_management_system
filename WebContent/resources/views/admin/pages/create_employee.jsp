<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.*,com.ticket.entity.*"%>
<%
   	ServletContext ctx = getServletContext();
   	String Url= ctx.getInitParameter("url");
   	String viewpath=ctx.getInitParameter("viewpath");
   	 List<Department> departments = (ArrayList)request.getAttribute("departments");  
%>

<form action="admin-employee-store.htm" method="POST"
	onsubmit=" return employee_validation();">
	<div class="box">

		<h1>create Employee</h1>
		<div class="form-group">
			<div id="eresult" style="color: firebrick; display: block;"></div>
			<label> name* <span class="alert">*</span></label> <input type="text"
				class="form-control" name="name" id="name" placeholder="Enter name">
		</div>

		<div class="form-group">
			<label> username* <span class="alert">*</span></label> <input
				type="text" name="username" class="form-control" id="username"
				placeholder="enter username" />
		</div>
		<div class="form-group">
			<label> email* <span class="alert">*</span></label> <input
				type="email" name="email" class="form-control" id="email"
				placeholder="enter email" />
		</div>
		<div class="form-group">
			<label> password* <span class="alert">*</span></label> <input
				type="password" required name="password" id="password"
				class="form-control" placeholder="enter password" />
		</div>
		<div class="form-group">
			<label> home company * <span class="alert">*</span></label> <input
				type="text" required name="homecompany" id="homecompany"
				class="form-control" placeholder="enter homecompany" />
		</div>
		<div class="form-group">
			<label>Department <span class="alert">*</span></label> <select
				class="form-control" name="department" id="department" required>
				<% for(Department d:departments){ %>
					<option value="<%=d.getDepartmentId()%>"><%=d.getDepartmentName() %></option>
				<%} %>
				
			</select>
		</div>
		<div class="form-group">
			<label>Role <span class="alert">*</span></label> <select
				class="form-control" name="role" required>
				<option value="admin">Admin</option>
				<option value="employee">Employee</option>
			</select>
		</div>
		<br>
		<input type="submit" name="submit" class="form-btn" value="create User">
		
	<div id="eresult2" style="color: firebrick; display: block;"></div>
	</div>
</form>



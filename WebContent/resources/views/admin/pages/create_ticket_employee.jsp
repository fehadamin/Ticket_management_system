<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.ticket.entity.*,java.util.*"%>
<%
    	ServletContext ctx = getServletContext();
    	String Url= ctx.getInitParameter("url");
    	String viewpath=ctx.getInitParameter("viewpath");

    	%>
    

<form action="" method="POST"
	onclick=" return ticket_validation();">
	<div class="box">
		
		<h1>create ticket</h1>
		
		
		<div class="form-group">
		<label>password <span class="alert">*</span></label> 
		<input type="password" class="form-control" required name="password" id="password" placeholder="Enter password name">
		</div>
		
		<div class="form-group">
		<label>Name <span class="alert">*</span></label> 
		<input type="text" required name="name"  class="form-control" id="name" placeholder="Enter name">
		</div>
		
		<div class="form-group">
		<label>User Name <span class="alert">*</span></label> 
		<input type="text" required name="username" id="username" class="form-control" placeholder="Enter user name">
		</div>
		
		<div class="form-group">
		<label>Email <span class="alert">*</span></label> 
		<input type="email" required name="email" id="email" class="form-control" placeholder="Enter email">
		</div>
		
		<div class="form-group">
		<label>Role  <span class="alert">*</span></label>
            <select  class="form-control" required>
                    <option value="admin">Admin</option>
                    <option value="employee">Employee</option>
            </select>
		</div>
		

		 <input type="submit" name="submit" value="createticket" class="form-btn">
		
		<div id="eresult" style="color: firebrick; display: block;"></div>
	</div>
</form>

    	
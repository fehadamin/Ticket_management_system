<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
    	ServletContext ctx = getServletContext();
    	String Url= ctx.getInitParameter("url");
    	String viewpath=ctx.getInitParameter("viewpath");
    %>


    <form action="admin-tickettype-store.htm" method="POST" onclick="return tickettypevalidation()">
    <div class="box">
        
            <h1> create ticket types</h1>
            <div class="form-group">
            <label> name<span class="alert">*</span> </label>
            <input type="text" required class="form-control" name="tickettypename" id="tickettypename" placeholder="Enter ticket type name"> 
            	<div id="eresult" style="color: firebrick; display: block;"></div>
            </div>
                <input type="submit" name="submit" value="create ticket type" class="form-btn">
                
   
            </div>
    
</form>
 

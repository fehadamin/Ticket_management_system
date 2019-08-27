<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
      import="java.util.*,com.ticket.entity.*"%>
<%
    	ServletContext ctx = getServletContext();
    	String Url= ctx.getInitParameter("url");
    	String viewpath=ctx.getInitParameter("viewpath");
    	
    	TicketType d= (TicketType) request.getAttribute("tickettype");
    %>


	<form action="admin-tickettype-edit-store.htm" method="post" >
    <div class="box">
        
            <h1> Edit tickettype</h1>
              <div class="form-group">
            <p> name</p>
            <input type="text" name="tickettypename" value="<%= d.getTicketName() %>" placeholder="Enter product name"> 
            </div>
            
            <input type="hidden" name="id" value="<%= d.getTicketTypeId()%>">
                <input type="submit" name="submit" value="edit " class="form-btn">
    </div>
    </form>

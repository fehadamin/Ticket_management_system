<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

        <%
    	ServletContext ctx = getServletContext();
    	String Url= ctx.getInitParameter("url");
    	String viewpath=ctx.getInitParameter("viewpath");
    %>
    
        <footer>
        
        <div class="alert" style="color:blue; width:75px;
    height:30px;">
        	<p>${message} </p>
        
        
        </div>
        <center>
		<p>
		<% if(request.getAttribute("message")==null){
			
			
		} else
			{
			out.println(request.getAttribute("message"));
			
			}%>

		</p>
		</center>
    </footer>
    
    <style>
    
    p{
    font-size:20px;
    color:#fff;
    
    }
    </style>
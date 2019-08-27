<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    
      <%
    	ServletContext ctx = getServletContext();
    	String Url= ctx.getInitParameter("url");
    	String viewpath=ctx.getInitParameter("viewpath");
    %>
    <!--sidebar-->
    <div class="sidebar">
        <div class="menu">
        <ul>
            <li> <a href="<%=Url%>employee-dashboard.htm"><span class="fa fa-home"></span>DASHBOARD</a>   </li>
            <li> <a href="#"><span class="fa fa-home"></span>TICKET</a>  
                    <ul>
                            <li> <a href="<%=Url%>employee-ticket-all.htm"><span class="fa fa-home">SHOW TICKET</span></a>  </li>
                             <li> <a href="<%=Url%>employee-ticket-form.htm"><span class="fa fa-home">CREATE TICKET</span></a>  </li>
                    </ul>
            
            </li>
            
            <li> <a href="<%=Url%>logout.htm"><span class="fa fa-home"></span>LOG OUT</a>   </li>



        </ul>




    </div>
    
    </div>
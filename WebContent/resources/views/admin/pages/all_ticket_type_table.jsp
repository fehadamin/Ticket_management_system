<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
      import="java.util.*,com.ticket.entity.*"%>
<%
    	ServletContext ctx = getServletContext();
    	String Url= ctx.getInitParameter("url");
    	String viewpath=ctx.getInitParameter("viewpath");
    %>

    
    
     <div class="res-tab">
        <h1>   all  ticket table types</h1>

        <table class="myTable" id="mytable">
            <tbody>
                <tr>
                	<th>ticket id </th>
                    <th> ticket name</th>
                    <th>action</th>
                   
                </tr>
               
            </tbody>
 			
				<%
					List<TicketType> tickettype = (ArrayList)request.getAttribute("tickettypes");
					for(TicketType d : tickettype){%>
						
							<tr>
								<td><%=d.getTicketTypeId()%></td>
								<td><%=d.getTicketName()%></td>
								<td>
									<a href="<%=Url%>admin-tickettype-edit-form.htm?id=<%=d.getTicketTypeId()%>">edit</a>
									<a href="<%=Url%>admin-tickettype-remove.htm?id=<%=d.getTicketTypeId()%>">delete</a>
								</td>
								
							</tr>
				<%}%>
        </table>
    </div>

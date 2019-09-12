<%@ page language="java" contentType="text/html; charget=ISO-8859-1"
    pageEncoding="ISO-8859-1"
     import="java.util.*,com.ticket.entity.*"%>
<%
    	ServletContext ctx = getServletContext();
    	String Url= ctx.getInitParameter("url");
    	String viewpath=ctx.getInitParameter("viewpath");
 %>



    <div class="res-tab">
        <h1>  all  employee table</h1>
       <!--  <button id="downloadReport">Download</button>  id="mytable" -->
        <table class="myTable" >
            <thead>
                    <th> name</th>
                    <th> username</th>
                    <th> email</th>
                    <th> homecompany</th>
                    <th> role</th>   
                    <th> Assigned to</th>
                    <th>Action</th>
                    
               
            </thead>
 			<tbody>
                <% 
                List<User> users = (ArrayList) request.getAttribute("users"); 
                for(User t:users){%>
                     <tr>
	                    <td><%=t.getName()%></td>
						<td><%=t.getUserName()%></td>
						<td><%=t.getEmail()%></td>
						
						<td><%=t.getHomeCompany()%></td>
						<td><%=t.getRole()%></td>
						<td><%=t.getProducts_assigned()%></td>
						<td>
							<a href="<%=Url%>admin-employee-edit-form.htm?id=<%=t.getUserId()%>">edit</a>
							<a href="<%=Url%>admin-employee-remove.htm?id=<%=t.getUserId()%>">delete</a>
						</td>
						
	                </tr>
            	<%} %>
            </tbody>
        </table>
    </div>
    
<!-- 
</body>
</html> -->
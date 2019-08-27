<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
      import="java.util.*,com.ticket.entity.*"%>
   
<%
    	ServletContext ctx = getServletContext();
    	String Url= ctx.getInitParameter("url");
    	String viewpath=ctx.getInitParameter("viewpath");
    %>

				
    <div class="res-tab">
        <h1>  departments table</h1>
        
       
        <table id="mytable" class="myTable">
            <tbody>
                <tr>
                	<th>department id </th>
                    <th> department name</th>
                    <th>action</th>
                   
                </tr>
               
            </tbody>
 			
				<%
					List<Department> departments = (ArrayList)request.getAttribute("departments");
					for(Department d : departments){%>
						
							<tr>
								<td><%=d.getDepartmentId()%></td>
								<td><%=d.getDepartmentName()%></td>
								<td>
									<a href="<%=Url%>admin-department-edit-form.htm?id=<%=d.getDepartmentId()%>">edit</a>
									<a href="<%=Url%>admin-department-remove.htm?id=<%=d.getDepartmentId()%>">delete</a>
								</td>
								
							</tr>
				<%}%>
        </table>
    </div>
    
 
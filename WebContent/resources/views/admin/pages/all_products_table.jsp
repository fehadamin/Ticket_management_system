<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,com.ticket.entity.*"%>
<%
	ServletContext ctx = getServletContext();
	String Url = ctx.getInitParameter("url");
	String viewpath = ctx.getInitParameter("viewpath");
%>


<div class="res-tab">
	<h1>products table</h1>
	       
        <table id="mytable" class="myTable">
		<tbody>
			<tr>
				<th>Product id</th>
				<th>Product name</th>
				<th>Parent</th>
				<th>Default assignee</th>
				<th>Action</th>


			</tr>

		</tbody>

		<%
			List<Product> products = (ArrayList) request.getAttribute("products");
			for (Product d : products) {
		%>

		<tr>
			<td><%=d.getProductId()%></td>
			<td><%=d.getProductName()%></td>
			<td>
				<%
					if (d.getParent() == 0) {
							out.println("none");
						}
					else
					{
						out.println(d.getParent());	
					}
				%>
			</td>
			<td><%=d.getDefaultAssignee()%></td>
			<td><a
				href="<%=Url%>admin-product-edit-form.htm?id=<%=d.getProductId()%>">edit</a>
				<a href="<%=Url%>admin-product-remove.htm?id=<%=d.getProductId()%>">delete</a>
			</td>

		</tr>
		<%
			}
		%>
	</table>
</div>

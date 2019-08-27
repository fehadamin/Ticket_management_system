<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,com.ticket.entity.*"%>
<%
	ServletContext ctx = getServletContext();
	String Url = ctx.getInitParameter("url");
	String viewpath = ctx.getInitParameter("viewpath");

	Product d = (Product) request.getAttribute("product");

	List<User> users = (ArrayList) request.getAttribute("users");

	List<Product> products = (ArrayList) request.getAttribute("parents");
%>


<form action="admin-product-edit-store.htm" method="post">
	<div class="box">

		<h1>Edit Product</h1>
		<div class="form-group">
			<label> name</label> <input type="text" class="form-control"
				name="productname" value="<%=d.getProductName()%>"
				placeholder="Enter product name">
		</div>

		<div class="form-group">
			<label> assignee </label>
				<select
				class="form-control" name="defaultassignee" required>
				<option value="0">Select ... </option>
				<% for(User u:users){ %>
					<option value="<%=u.getName()%>"><%=u.getName()%></option>
				<%} %>
			</select>
		</div>
		
		
		

		<%-- <div class="form-group">
			<label> parent </label> <input type="text" class="form-control"
				name="parentname" value="<%=d.getProductName()%>"
				placeholder="Enter parent name">
		</div>
		 --%>
		
		<label> parent<span class="alert">*</span></label> <select
				class="form-control" name="parent" required>
					<option value="0">None</option>
				<%
					for (Product p : products) {
						if (p.getParent() == 0) {
				%>
				<option value="<%=p.getProductId()%>"><%=p.getProductName()%></option>
				<%
					}
					}
				%>
			</select>
			
		
		

		<input type="hidden" name="id" value="<%=d.getProductId()%>">
		<input type="submit" name="submit" class="form-btn"
			value="edit product">
	</div>
</form>

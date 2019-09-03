<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,com.ticket.entity.*"%>
<%
	ServletContext ctx = getServletContext();
	String Url = ctx.getInitParameter("url");
	String viewpath = ctx.getInitParameter("viewpath");

	List<User> users = (ArrayList) request.getAttribute("users");
	List<Product> products = (ArrayList) request.getAttribute("parents");
%>

<form method="post" action="admin-product-store.htm"
	onclick="return product_validation();">
	<div class="box">

		<h1>create Product</h1>
		<div class="form-group">
			<label> Product name*<span class="alert">*</span></label> <input
				type="text" class="form-control" name="productname" required
				id="productname" placeholder="Enter product name">

		</div>
		<div class="form-group">
			<label> default assignee<span class="alert">*</span></label> <select
				class="form-control" name="defaultassignee" required>

				<%
					for (User u : users) {
				%>
				<option value="<%=u.getName()%>"><%=u.getName()%></option>
				<%
					}
				%>
			</select>
		</div>


		<div class="form-group">
			<!-- <label>parent <span class="alert">*</span></label> <select
				class="form-control" name="parent" required>
				<option value="0">None</option>
				<option value="1">PSUPTUI</option>
				<option value="3">PSUPCORE</option>
			</select> -->
			<label> parent<span class="alert">*</span></label> <select
				class="form-control" name="parent" required>
					<option value="0">None</option>
				<%
					for (Product d : products) {
						if (d.getParent() == 0) {
				%>
				<option value="<%=d.getProductId()%>"><%=d.getProductName()%></option>
				<%
					}
					}
				%>
			</select>
			
			
			
			
			
			 <input type="submit" name="submit" class="form-btn"
				value="create product">
		</div>
		
		
</form>
</div>	
<div id="eresult" style="color: firebrick; display: block;"></div>

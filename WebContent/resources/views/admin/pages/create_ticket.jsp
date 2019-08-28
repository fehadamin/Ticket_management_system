<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.ticket.entity.*,java.util.*"%>
<%
	ServletContext ctx = getServletContext();
	String Url = ctx.getInitParameter("url");
	String viewpath = ctx.getInitParameter("viewpath");

	List<Department> departments = (ArrayList) request.getAttribute("departments");
	List<Product> products = (ArrayList) request.getAttribute("products");
	List<TicketType> tickettypes = (ArrayList) request.getAttribute("tickettypes");

	int uid = (int) request.getAttribute("userId");
	String name = (String) request.getAttribute("user");

	List<User> users = (ArrayList) request.getAttribute("users");
%>


<form action="admin-ticket-store.htm" method="POST"
	onsubmit=" return ticket_validation()">

	<div class="box">
		<h1>create ticket</h1>

		<div class="form-group">
			<div id="eresult" style="color: firebrick; display: block;"></div>
			<label>Ticket Type <span class="alert">*</span></label> <select
				class="form-control" name="tickettype" id="tickett"
				onchange="return compns()" required>
				<option value="0">Select ...</option>
				<%
					for (TicketType tt : tickettypes) {
				%>
				<option value="<%=tt.getTicketTypeId()%>"><%=tt.getTicketName()%></option>
				<%
					}
				%>
			</select>
		</div>


		<div class="form-group">

			<label>department <span class="alert">*</span></label> <select
				class="form-control" name="departmentname" id="departmentname"
				required>
				<option value="0">Select ...</option>
				<%
					for (Department d : departments) {
				%>
				<option value="<%=d.getDepartmentId()%>"><%=d.getDepartmentName()%></option>
				<%
					}
				%>
			</select>
		</div>

		<div class="form-group">

			<label>Product <span class="alert">*</span></label> <select
				name="product" class="form-control" id="product" required>
				<option value="0">Select ...</option>
				<%
					for (Product d : products) {
						if (d.getParent() == 0) {
				%>
				<option value="<%=d.getProductId()%>"><%=d.getProductName()%></option>
				<%
					}}
				%>
			</select>
			<div id="pds" style="display:none;">
				<%
					for (Product d : products) {
						if (d.getParent() == 0) {
				%>
					<lavel><%=d.getProductName()%></lavel>
					<input type="checkbox" name="products" value="<%=d.getProductId()%>" />
				<%} }%>
			</div>
		</div>

		<div class="form-group" id="comp" style="visibility: visible;">

			<label>Component <span class="alert">*</span></label> <select
				name="component" class="form-control" required>
				<option value="0">Select ...</option>
				<%
					for (Product d : products) {
						if (d.getParent() != 0) {
				%>
				<option value="<%=d.getProductId()%>" data-parent="<%=d.getParent()%>"><%=d.getProductName()%></option>
				<%
					}
					}
				%>
			</select>
		</div>



		<div class="form-group">

			<label>Summary </label>
			<textarea class="form-control" style="width: 100%; height: 40px;"
				name="summary"></textarea>
		</div>

		<div class="form-group">

			<label>priority <span class="alert">*</span></label> <select
				class="form-control" name="priority" id="priority" required>
				<option value="0">Select ...</option>
				<option value="Blocker">Blocker</option>
				<option value="Critical">Critical</option>
				<option value="Major">Major</option>
			</select>
		</div>    
		<div class="form-group">

			<label>Due date </label> <input type="text" class="form-control"
				name="dueDate" id="dueDate" autocomplete="off"  required />
		</div>
		<div class="form-group">
			<label>Assignee<span class="alert">*</span></label> <select
				class="form-control" name="assignee" id="assignee" >
				<option value="0">Select ...</option>
				<%
					for (User u : users) {
				%>
				<option value="<%=u.getName()%>"><%=u.getName()%></option>
				<%
					}
				%>
			</select>
			<span >Leave blank for default</span>
		</div>

		<div class="form-group">
			<label>Reporter<span class="alert">*</span></label> <select
				class="form-control" name="reporter" id="reporter" required>
				<option value="0">Select ...</option>
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

			<label>status</label> <input type="text" class="form-control"
				name="status" value="open" disabled />
		</div>




		<input type="submit" name="submit" class="form-btn"
			value="createticket">
		<div id="eresult2" style="color: firebrick; display: block;"></div>
	</div>

</form>


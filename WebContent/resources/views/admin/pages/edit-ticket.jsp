<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.ticket.entity.*,java.util.*"%>
<%
	ServletContext ctx = getServletContext();
	String Url = ctx.getInitParameter("url");
	String viewpath = ctx.getInitParameter("viewpath");

	List<Product> products = (ArrayList) request.getAttribute("products");
	List<TicketType> tickettypes = (ArrayList) request.getAttribute("tickettypes");
	List<User> users = (ArrayList) request.getAttribute("users");
	List<Department> departments = (ArrayList) request.getAttribute("departments");
	int uid = (int) request.getAttribute("userId");
	String name = (String) request.getAttribute("user");
	Ticket ticket = (Ticket) request.getAttribute("ticket");
	
%>


<form action="admin-ticket-edit-store.htm" method="POST" id="ticketForm" data-form-type="edit">
	<div class="box">
		<h1>Edit Ticket</h1>
		
		<input type="hidden" value="<%=Url%>admin-ticket-all.htm" id="url">
		<div class="form-group">
			<div id="eresult" style="color: firebrick; display: block;"></div>
			<label>Ticket Type <span class="alert">*</span></label> <select
				class="form-control" name="tickettype" id="tickett" required>
				<option value="0">Select ...</option>
				<%
					for (TicketType d : tickettypes) {
				%>
				<option value="<%=d.getTicketTypeId()%>"
					<%if (ticket.getTicketTypeId() == d.getTicketTypeId())
					out.println("selected");%>>
					<%=d.getTicketName()%>
				</option>
				<%
					}
				%>
			</select>
			<span class="tooltiptext" id="tickett-tip">Please select one</span>
		</div>
		
		<div class="form-group">

			<label>Product <span class="alert">*</span></label> <select
				name="product" class="form-control" id="product" required>
				<option value="0">Select ...</option>
				<%
					for (Product p : products) {
						if (p.getParent() == 0) {
				%>
				<option value="<%=p.getProductId()%>"
					<%if (p.getProductId() == ticket.getProductId())
						out.println("selected");%>><%=p.getProductName()%></option>
				<%
					}
					}
				%>
			</select>
			<span class="tooltiptext" id="product-tip">Please select one</span>
		</div>


		<div class="form-group">

			<label>Component <span class="alert">*</span></label> <select
				name="component" class="form-control" id="comp"required>
				<option value="0">Select ...</option>
				<%
					for (Product d : products) {
						if (d.getParent() != 0) {
				%>
				<option value="<%=d.getProductId()%>" data-parent="<%=d.getParent()%>"
					<%if (ticket.getComponent() == d.getProductId())
						out.println("selected");%>><%=d.getProductName()%></option>
				<%
					}
					}
				%>
			</select>
			<span class="tooltiptext" id="comp-tip">Please select one</span>
		</div>

		<div class="form-group">
			<label>Summary <span class="alert">*</span></label>
			<textarea style="width: 100%; height: 40px;" class="form-control"
				name="summary" id="summary"><%=ticket.getSummary()%></textarea>
				<span class="tooltiptext" id="summary-tip">Please select one</span>
		</div>
		<div class="form-group">
			<label>Due date *</label> <input type="text" name="dueDate"
				id="dueDate" class="form-control" value="<%=ticket.getDueDate()%>"
				 required />
				<span class="tooltiptext" id="dueDate-tip">Due date should >= created date</span>
		</div>
		<div class="form-group">
			<label>Created date *</label> <input type="text" name="createdDate"
				id="createdDate" class="form-control" value="<%=ticket.getCreated()%>"
				required />
				<span class="tooltiptext" id="createdDate-tip">Please select one</span>
		</div>

		<div class="form-group">
			<label>priority <span class="alert">*</span></label> <select
				class="form-control" name="priority" id="priority"required>
				<option value="Blocker"
					<%if (ticket.getPriority().equals("Blocker"))
				out.println("selected");%>>Blocker</option>
				<option value="Critical"
					<%if (ticket.getPriority().equals("Critical"))
				out.println("selected");%>>Critical</option>
				<option value="Major"
					<%if (ticket.getPriority().equals("Major"))
				out.println("selected");%>>Major</option>
			</select>
			<span class="tooltiptext" id="priority-tip">Please select one</span>
		</div>

		<div class="form-group">
			<label>Assignee<span class="alert">*</span></label> <select
				class="form-control" name="assignee" id="assignee" required>
				<option value="0">Select ...</option>
				<%
					for (User u : users) {
				%>
				<option value="<%=u.getName()%>"
					<%if (ticket.getAssignee().equals(u.getName()))
					out.println("selected");%>><%=u.getName()%></option>
				<%
					}
				%>
			</select>
			<span class="tooltiptext" id="assignee-tip">Please select one</span>
		</div>

		<div class="form-group">
			<label>Reporter<span class="alert">*</span></label> <select
				class="form-control" name="reporter" id="reporter" required>
				<option value="0">Select ...</option>
				<%
					for (User u : users) {
				%>
				<option value="<%=u.getName()%>"
					<%if (u.getName().equals(ticket.getReporter()))
					out.println("selected");%>><%=u.getName()%></option>
				<%
					}
				%>
			</select>
			<span class="tooltiptext" id="reporter-tip">Please select one</span>
		</div>

		<div class="form-group">
			<label>status <span class="alert">*</span></label> <select
				class="form-control" name="status" id="status" required>
				<option value="Open"
					<%if (ticket.getStatus().equals("Blocker"))
				out.println("selected");%>>Open</option>
				<option value="In Progress"
					<%if (ticket.getStatus().equals("In Progress"))
				out.println("selected");%>>In
					Progress</option>
				<option value="Resolved"
					<%if (ticket.getStatus().equals("Resolved"))
				out.println("selected");%>>Resolved</option>
				<option value="Closed"
					<%if (ticket.getStatus().equals("Closed"))
				out.println("selected");%>>Closed</option>
			</select>
			<span class="tooltiptext" id="status-tip">Please select one</span>
		</div>

		<input type="submit" name="submit" class="form-btn"
			value="Update ticket"> 
		<a href="<%=Url%>admin-dashboard.htm"class="form-btn" >Cancel</a>
		<input type="hidden" name="id"
			value="<%=ticket.getTicketId()%>" />

	</div>
</form>

